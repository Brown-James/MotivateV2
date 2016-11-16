package com.brownjames.motivatev2.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brownjames.motivatev2.Util;
import com.brownjames.motivatev2.activity.main.model.MainModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by james on 22/09/16.
 */
public class DAO {
    private DBSchema mHelper;
    private Context mContext;

    // SELECTIONS
    private static final String SELECT_ID_BASED = DBSchema.TB_TASKS.ID + " = ?";
    private static final String PROJECTION_ALL = " * ";
    public static final String  SORT_ORDER_DEFAULT = DBSchema.TB_TASKS.ID + " DESC";


    public DAO(Context context) {
        this.mContext = context;
        mHelper = new DBSchema(mContext);
    }

    private SQLiteDatabase getReadDatabase() {
        return mHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWriteDatabase() {
        return mHelper.getWritableDatabase();
    }

    public Task insertTask(Task task) {
        SQLiteDatabase db = getWriteDatabase();
        long id = db.insert(
                DBSchema.TABLE_TASKS,
                null,
                task.getValues()
        );

        Task insertedTask = getTask((int)id);
        db.close();
        return insertedTask;
    }

    public Task getTask(long id) {
        SQLiteDatabase db = getReadDatabase();
        Cursor c = db.query(
                DBSchema.TABLE_TASKS,
                null,
                SELECT_ID_BASED,
                new String[]{Long.toString(id)},
                null,
                null,
                null
        );

        if (c != null) {
            c.moveToFirst();
            Task task = new Task();
            task.setId(c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.ID)));
            task.setTitle(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.TITLE)));
            task.setCompleteBy(c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.COMPLETE_BY)));
            task.setValue(c.getDouble(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.CURRENCY_VALUE)));
            task.setCurrencyType(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.CURRENCY_TYPE)));
            return task;
        }

        return null;
    }

    public ArrayList<Task> getAllTasks() {
        SQLiteDatabase db = getReadDatabase();

        Cursor c = db.query(DBSchema.TABLE_TASKS,
                null,
                null,
                null,
                null,
                null,
                SORT_ORDER_DEFAULT);

        if (c != null) {
            c.moveToFirst();

            ArrayList<Task> tasks = new ArrayList<Task>();
            while(!c.isAfterLast()) {
                Task t = new Task(
                        c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.ID)),
                        c.getString(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.TITLE)),
                        c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.COMPLETE_BY)),
                        c.getDouble(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.CURRENCY_VALUE)),
                        c.getString(c.getColumnIndexOrThrow(DBSchema.TB_TASKS.CURRENCY_TYPE))
                );

                tasks.add(t);
                c.moveToNext();
            }

            c.close();
            db.close();
            return tasks;
        } else {
            return null;
        }
    }

    public long deleteTask(Task task) {
        SQLiteDatabase db = getWriteDatabase();
        long res = db.delete(
                DBSchema.TABLE_TASKS,
                SELECT_ID_BASED,
                new String[]{Integer.toString(task.getId())}
                );

        db.close();
        return res;
    }
}
