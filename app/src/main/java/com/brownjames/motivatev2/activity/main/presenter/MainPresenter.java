package com.brownjames.motivatev2.activity.main.presenter;

import android.content.Context;

import com.brownjames.motivatev2.activity.main.MVP_Main;
import com.brownjames.motivatev2.activity.main.model.MainModel;
import com.brownjames.motivatev2.data.Task;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by james on 22/09/16.
 */

public class MainPresenter implements MVP_Main.ProvidedPresenterOps,
        MVP_Main.RequiredPresenterOps {

    /**
     * View reference. We use a WeakReference because the Activity could be destroyed
     * at any time, and we don't want to create memory leaks.
     */
    private WeakReference<MVP_Main.RequiredViewOps> mView;

    // Model reference
    private MVP_Main.ProvidedModelOps mModel;


    @Override
    public void setView(MVP_Main.RequiredViewOps view) {
        this.mView = view;
    }

    @Override
    public int getTaskCount() {
        return mModel.getTasksCount();
    }

    @Override
    public void clickNewTask() {
        // Create new Task here
    }

    @Override
    public Context getAppContext() {
        return mView.getAppContext();
    }

    @Override
    public Context getActivityContext() {
        return mView.getActivityContext();
    }

    public void setModel(MainModel model) {
    }
}
