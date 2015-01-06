package com.intouchapp.todolistapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.intouchapp.todolistapp.R;
import com.intouchapp.todolistapp.database.DbManager;
import com.intouchapp.todolistapp.models.TodoItem;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by jay on 06/01/15.
 */
public class TodoListFragment extends Fragment {

    private DbManager mDbManager;

    private EditText mTodoData;
    private View mAdd;
    private ListView mTodoList;
    private ArrayList<TodoItem> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbManager = new DbManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTodoData = (EditText) view.findViewById(R.id.todo_data);
        mAdd = view.findViewById(R.id.btn_add);
        mTodoList = (ListView) view.findViewById(R.id.todo_list);

        listItems();
        initOnAddClick();
    }

    /**
     * Lists the items in the already saved to-do items
     */
    private void listItems() {
        ArrayList<TodoItem> todoItems = getItems();
    }

    private void initOnAddClick() {
        if (mAdd != null) {
            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem();
                }
            });
        }
    }

    private void addItem() {
        if (mTodoData.getText() == null || TextUtils.isEmpty(mTodoData.getText().toString())) {
            Crouton.makeText(getActivity(), getActivity().getString(R.string.message_item_not_empty), Style.INFO).show();
            return;
        }

        String todoText = mTodoData.getText().toString();

        long rowId = mDbManager.insert(new TodoItem(todoText));

        if (rowId == -1) {
            // some issue in adding item.
            Crouton.makeText(getActivity(), getActivity().getString(R.string.add_item_failed), Style.ALERT).show();
        } else {
            // successfully added
            mTodoData.setText(null);
            Crouton.makeText(getActivity(), getActivity().getString(R.string.add_item_success), Style.INFO).show();
        }
    }

    public ArrayList<TodoItem> getItems() {
        return mDbManager.getItems();
    }
}