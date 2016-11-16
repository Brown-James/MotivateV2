package com.brownjames.motivatev2;

import android.content.Context;

import com.brownjames.motivatev2.activity.main.model.MainModel;
import com.brownjames.motivatev2.activity.main.presenter.MainPresenter;
import com.brownjames.motivatev2.data.DAO;
import com.brownjames.motivatev2.data.Task;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class MainModelTest {

    private MainModel mModel;
    private DAO mDAO;

    @Before
    public void setup() {
        // Using RuntimeEnvironment.application will permit us to access a Context and create a real
        // DAO inserting data that will be saved temporarily.
        Context context = RuntimeEnvironment.application;
        mDAO = new DAO(context);

        // We mock the presenter, which will allow us to verify if certain methods were called
        // in the Presenter.
        MainPresenter mockPresenter = Mockito.mock(MainPresenter.class);

        mModel = new MainModel(mockPresenter, mDAO);

        // Reset the mock Presenter so that our method verification remains consistent between
        // the tests.
        reset(mockPresenter);
    }

    private Task createTask(String title, int completeBy, int value, String currencyType) {
        Task t = new Task();
        t.setTitle(title);
        t.setCompleteBy(completeBy);
        t.setValue(value);
        t.setCurrencyType(currencyType);

        return t;
    }

    // Verify getTask
    @Test
    public void getTask() {
        Task t = createTask("taskText", 1000, 10, "GBP");

        int pos = mModel.insertTask(t);
        Task fromModel = mModel.getTask(pos);

        assertTrue("Returned Task did not exist.", fromModel != null);
        assertTrue("Returned Task is not the same as the Task inserted.", t.equals(fromModel));
    }

    // Verify loadData
    @Test
    public void loadData() {
        int tasksSize = 10;

        // inserting data directly using DAO
        for(int i = 0; i < tasksSize; i++) {
            mDAO.insertTask(createTask("task_" + Integer.toString(i), 1000, 10, "GBP"));
        }

        // Calling load method
        mModel.loadData();

        // Verify if mTasks, an ArrayList that recieves the Notes
        // have the same size as the quantity of Tasks inserted
        assertEquals(mModel.mTasks.size(), tasksSize);
    }

    // Verify insertTask
    @Test
    public void insertTask() {
        int pos = mModel.insertTask(createTask("taskText", 1000, 10, "GBP"));
        assertTrue("Task insertion returned -1 indicating failure", pos > -1);
    }

    // Verify deleteTask
    @Test
    public void deleteTask() {
        // Add a task to the database
        Task t = createTask("taskText", 1000, 10, "GBP");

        // Reload that task
        long id = mModel.insertTask(t);
        mModel.loadData();
        assertTrue("Data not successfully inserted", mModel.getTasksCount() == 1);

        // Get the task to delete from the database
        Task delete = mModel.getTask((int)id);
        boolean success = mModel.deleteTask(delete, 0);
        assertTrue("deleteTask returned false.", success);

        mModel.loadData();
        assertTrue("Task was not deleted successfully.", mModel.getTasksCount() == 0);
    }
}