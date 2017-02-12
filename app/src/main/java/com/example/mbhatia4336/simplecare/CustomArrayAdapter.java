package com.example.mbhatia4336.simplecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mbhatia4336 on 2/11/17.
 */
public class CustomArrayAdapter extends ArrayAdapter<Task> {
    public CustomArrayAdapter(Context context, Task [] tasks) {
        //what does this exactly mean?
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        Task task = getItem(position);
        if(view == null) {
            //look into LayoutInflater constructor
            view = LayoutInflater.from(getContext()).inflate(R.layout.rows, container, false);
        }
        TextView myTask = (TextView) view.findViewById(R.id.task);
        myTask.setText(task.getName());
        return view;

    }
}
