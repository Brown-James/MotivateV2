package com.brownjames.motivatev2.data;

import android.content.ContentValues;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Created by james on 22/09/16.
 */

public class Task {

    private int id = -1;
    private String mTitle;
    private long mCompleteBy;           // Stored as milliseconds since UNIX epoch.
    private double mCurrencyValue;      // The amount the user has bet against themselves (i.e 1.25).
    private String mCurrencyType;       // The type of currency the user has used to bet against
                                        // themselves. Stored as ISO 4217 code.

    public Task() {}

    /**
     * Creates a Task with the given parameters
     * @param id The id of the Task in the database
     * @param title The description of the task to be completed
     * @param completeBy The date (milliseconds since UNIX epoch) that this task is supposed
     *                   to be completed by.
     * @param value The amount of money the user has bet against themselves to complete the
     *              task.
     */
    public Task (int id, String title, long completeBy, double value, String currencyType) {
        this.id = id;
        this.mTitle = title;
        this.mCompleteBy = completeBy;
        this.mCurrencyValue = value;
        this.mCurrencyType = currencyType;
    }

    public int getId() {
        return id;
    }

    /**
     * Gets the title of the Task
     * @return The title of the Task
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets the title of the Task
     * @param mTitle The value to set the Title to
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public long getCompleteBy() {
        return mCompleteBy;
    }

    public void setCompleteBy(long mCompleteBy) {
        this.mCompleteBy = mCompleteBy;
    }

    public double getValue() {
        return mCurrencyValue;
    }

    public void setValue(double mValue) {
        this.mCurrencyValue = mValue;
    }

    public ContentValues getValues() {
        ContentValues cv = new ContentValues();

        if (id != -1) {
            cv.put(DBSchema.TB_TASKS.ID, id);
        }

        cv.put(DBSchema.TB_TASKS.TITLE, mTitle);
        cv.put(DBSchema.TB_TASKS.COMPLETE_BY, mCompleteBy);
        cv.put(DBSchema.TB_TASKS.CURRENCY_TYPE, mCurrencyType);
        cv.put(DBSchema.TB_TASKS.CURRENCY_VALUE, mCurrencyValue);

        return cv;
    }

    public String getCurrencyType() {
        return mCurrencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.mCurrencyType = currencyType;
    }

    public boolean equals(Task t) {
        return t.getTitle().equals(mTitle) &&
                t.getCompleteBy() == mCompleteBy &&
                t.getValue() == mCurrencyValue &&
                t.getCurrencyType().equals(mCurrencyType);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + " - " + mTitle + " - " + mCompleteBy + " - " + mCurrencyType + " - " + mCurrencyValue;
    }
}
