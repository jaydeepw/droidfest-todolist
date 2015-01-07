package com.intouchapp.todolistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.intouchapp.todolistapp.models.TodoItem;

import java.util.ArrayList;

/**
 * Created by jay on 06/01/15.
 */
public class DbManager {

    private Context mContext;
    private ArrayList<TodoItem> items;

    public DbManager(Context context) {
        mContext = context;
    }

    public long insert(TodoItem todoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConfig.COL_DATA, todoItem.getTodoText());
        return DbHelper.getInstance(mContext).insert(DbConfig.TABLE_TODO, contentValues);
    }

    public ArrayList<TodoItem> getItems() {
        // example of a raw query.
        // Cursor cursor = DbHelper.getInstance(mContext).execSQL("select * from " + DbConfig.TABLE_TODO, null);

        // alternatively you are also query as below.
        Cursor cursor = DbHelper.getInstance(mContext).select(DbConfig.TABLE_TODO, null, null, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        ArrayList<TodoItem> items = new ArrayList<TodoItem>();

        while (cursor.moveToNext()) {
            String todoText = cursor.getString(cursor.getColumnIndex(DbConfig.COL_DATA));
            items.add(new TodoItem(todoText));
        }   // end while

        return items;
    }

    /**
     * Removes all the items from the table
     * @return Number of rows affected.
     */
    public int removeAll() {
        return DbHelper.getInstance(mContext).delete(DbConfig.TABLE_TODO, null, null);
    }
}
