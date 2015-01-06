package com.intouchapp.todolistapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.intouchapp.todolistapp.R;

/**
 * Created by jay on 06/01/15.
 */
public class TodoListFragment extends Fragment {

    private EditText mTodoData;
    private View mAdd;

    public TodoListFragment() {
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

        initOnAddClick();
    }

    private void initOnAddClick() {
        if(mAdd != null) {
            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem();
                }
            });
        }
    }

    private void addItem() {
        Toast.makeText(getActivity(), "add clicked", Toast.LENGTH_SHORT).show();
    }
}