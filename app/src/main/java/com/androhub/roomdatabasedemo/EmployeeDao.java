/*
 * Created by Androhub on 14/1/21
 * Copyright (c) 2021 . All rights reserved.
 */

package com.androhub.roomdatabasedemo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao extends BaseDao<Employee> {

    @Query("select * from employee")
    List<Employee> getAllEmployees();


    @Query("select * from employee where name= :query")
    List<Employee> getAllEmployees(String query);

}
