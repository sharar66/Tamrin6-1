package com.example.reza66.tamin6.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by www.NooR26.com on 1/11/2019.
 */

public class Task {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mDone;

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    private String mDescription;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }



    public Task() {
        mId = UUID.randomUUID();
        mDate= new Date();

    }
}
