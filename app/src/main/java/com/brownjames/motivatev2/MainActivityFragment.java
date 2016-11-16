package com.brownjames.motivatev2;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.brownjames.motivatev2.activity.main.MVP_Main;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements
        MVP_Main.RequiredViewOps{

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void showAlert(AlertDialog dialog) {
        dialog.show();
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {

    }

    @Override
    public void notifyItemRemoved(int position) {

    }

    @Override
    public void notifyDataSetChanged() {

    }


}
