/*
 * Created by Androhub on 14/1/21
 * Copyright (c) 2021 . All rights reserved.
 */

package com.androhub.roomdatabasedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeActivity extends AppCompatActivity {

    private ListView listView;
    private EditText searchInput;
    private EmployeeListAdapter employeeListAdapter;
    private MyDatabase db;

    //executorService to run the task in background thread
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    //handler to touch UI from background thread
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        db = MyApplication.getMyDatabase();

        listView = findViewById(R.id.employeeListView);
        searchInput = findViewById(R.id.etSearchInput);

        fetchAllEmployees();
    }

    private void fetchAllEmployees() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Employee> employeeList = db.employeeDao().getAllEmployees();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        employeeListAdapter = new EmployeeListAdapter(EmployeeActivity.this, employeeList);
                        listView.setAdapter(employeeListAdapter);
                    }
                });

            }
        });
    }

    public void doSearch(View view) {
        String query = searchInput.getText().toString().trim();
        if (TextUtils.isEmpty(query)){
            fetchAllEmployees();
        }else{
            searchFilerData(query);
        }
    }

    private  void searchFilerData(String query){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Employee> employeeList = db.employeeDao().getAllEmployees(query);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        employeeListAdapter = new EmployeeListAdapter(EmployeeActivity.this, employeeList);
                        listView.setAdapter(employeeListAdapter);
                    }
                });

            }
        });
    }
}
