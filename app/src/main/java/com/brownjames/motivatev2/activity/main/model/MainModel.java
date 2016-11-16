package com.brownjames.motivatev2.activity.main.model;

import com.brownjames.motivatev2.activity.main.MVP_Main;
import com.brownjames.motivatev2.data.DAO;
import com.brownjames.motivatev2.data.Task;

import java.util.ArrayList;

/**
 * Created by james on 22/09/16.
 */

public class MainModel implements MVP_Main.ProvidedModelOps {

    public ArrayList<Task> mTasks;

    // Presenter reference
    private MVP_Main.RequiredPresenterOps mPresenter;
    private DAO mDAO;


    /**
     * !!! Constructor for use in testing purposes only !!!
     * @param presenter The Presenter this model is linked with.
     * @param dao The DAO for this Model.
     */
    public MainModel(MVP_Main.RequiredPresenterOps presenter, DAO dao) {
        this.mPresenter = presenter;
        this.mDAO = dao;
    }

    @Override
    public int insertTask(Task task) {
        Task insertedTask = mDAO.insertTask(task);

        if(insertedTask != null) {
            loadData();
            return getTaskPosition(insertedTask);
        }

        return -1;
    }

    /**
     * Gets a specific Task from the Tasks List using its array position.
     * @param position Array position.
     * @return The Task from the List.
     */
    @Override
    public Task getTask(int position) {
        return mTasks.get(position);
    }

    /**
     * Get a Task's position in the ArrayList.
     * @param task Task to check.
     * @return Position in ArrayList. If the Task is not in the ArrayList, -1 is returned.
     */
    @Override
    public int getTaskPosition(Task task) {
        for(int i = 0; i < mTasks.size(); i++) {
            if(task.getId() == mTasks.get(i).getId()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Deletes the given Task from the database.
     * @param task The Task to delete.
     * @param adapterPos The Tasks position in the List.
     * @return Returns True if Task deletion was successful.
     */
    @Override
    public boolean deleteTask(Task task, int adapterPos) {
        long res = mDAO.deleteTask(task);

        if(res > 0) {
            mTasks.remove(adapterPos);
            return true;
        }

        return false;
    }

    @Override
    public int getTasksCount() {
        return mTasks.size();
    }

    @Override
    public boolean loadData() {
        this.mTasks = mDAO.getAllTasks();
        return mTasks != null;
    }
}
