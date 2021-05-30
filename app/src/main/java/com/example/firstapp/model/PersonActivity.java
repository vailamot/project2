package com.example.firstapp.model;

import java.sql.Time;
import java.util.Date;

public class PersonActivity {
    private int mId;
    private String mName;
    private String mLocate;
    private String mDate;
    private String mTime;

    public PersonActivity() {
    }

    public PersonActivity(String mName, String mLocate, String mDate, String mTime) {
        this.mName = mName;
        this.mLocate = mLocate;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public PersonActivity(int mId, String mName, String mLocate, String mDate, String mTime) {
        this.mId = mId;
        this.mName = mName;
        this.mLocate = mLocate;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmLocate() {
        return mLocate;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmLocate(String mLocate) {
        this.mLocate = mLocate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
