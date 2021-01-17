/*
 * Created by Androhub on 14/1/21
 * Copyright (c) 2021 . All rights reserved.
 */

package com.androhub.roomdatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Employee> employeeList;
    private LayoutInflater inflater;

    public EmployeeListAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int i) {
        return employeeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void removeData(int index){
        employeeList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Employee employee = employeeList.get(i);


        viewHolder.bindData(employee.user);

        return view;
    }

    private static class ViewHolder {
        private CheckBox label;

        public ViewHolder(View view) {
            label = view.findViewById(R.id.item_data);
        }

        void bindData(User user) {
            label.setText(user.getName() + " ---- " + user.getEmail() + " ---- " + user.getNumber());
        }
    }

}
