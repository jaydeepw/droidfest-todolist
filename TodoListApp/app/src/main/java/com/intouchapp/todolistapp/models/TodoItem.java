package com.intouchapp.todolistapp.models;

/**
 * Created by jay on 01/01/15.
 */
public class TodoItem {

    /**
     * To uniquely identify
     */
    private int mId;
    private String mTodoText;

    public TodoItem(String todoText) {
        mTodoText = todoText;
    }

    public String getTodoText() {
        return mTodoText;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}