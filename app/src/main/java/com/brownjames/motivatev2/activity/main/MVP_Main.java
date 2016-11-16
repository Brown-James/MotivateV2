package com.brownjames.motivatev2.activity.main;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.brownjames.motivatev2.data.Task;

/**
 * Created by james on 22/09/16.
 */

public interface MVP_Main {
    // These will be called by the Presenter on the View
    // View must implement these
    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
        void showToast(Toast toast);
        void showAlert(AlertDialog dialog);
        void notifyItemInserted(int layoutPosition);
        void notifyItemRemoved(int position);
        void notifyDataSetChanged();
    }

    // These will be called by the View on the Presenter
    // Presenter must implement these
    interface ProvidedPresenterOps {
        void setView(RequiredViewOps view);
        int getTaskCount();
        void clickNewTask();
    }

    // These will be called by the Model on the Presenter
    // Presenter must implement these
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    // These will be called by the Presenter on the Model
    // Model must implement these
    interface ProvidedModelOps {
        int insertTask(Task task);
        Task getTask(int position);
        int getTaskPosition(Task task);
        boolean deleteTask(Task task, int adapterPos);
        int getTasksCount();
        boolean loadData();
    }
}
