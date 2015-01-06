package com.intouchapp.todolistapp.models;

/**
 * Created by jay on 06/01/15.
 */
public class TodoItem {

    private String mTodoText;

    public TodoItem(String todoText) {
        mTodoText = todoText;
    }

    public String getTodoText() {
        return mTodoText;
    }
}