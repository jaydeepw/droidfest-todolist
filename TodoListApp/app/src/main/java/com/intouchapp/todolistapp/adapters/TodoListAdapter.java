package com.intouchapp.todolistapp.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.intouchapp.todolistapp.R;
import com.intouchapp.todolistapp.models.TodoItem;

import java.util.ArrayList;

/**
 * Created by jay on 21/10/14.
 */
public class TodoListAdapter extends ArrayAdapter<TodoItem> {

    public TodoListAdapter(Context context, int textViewResourceId, ArrayList<TodoItem> todoItems) {
        super(context, textViewResourceId, todoItems);
    }

    private static class ViewHolder {
        TextView mTodoText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        TodoItem todoItem = getItem(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            holder.mTodoText = (TextView) convertView.findViewById(R.id.todo_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTodoText.setText(todoItem.getTodoText());

        return convertView;
    }
}
