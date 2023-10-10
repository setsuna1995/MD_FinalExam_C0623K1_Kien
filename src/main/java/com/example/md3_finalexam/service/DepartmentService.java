package com.example.md3_finalexam.service;

import com.example.md3_finalexam.model.Department;
import com.example.md3_finalexam.model.Staff;
import com.example.md3_finalexam.ultil.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService extends ConnectionUtil {
    public Department convert (ResultSet mResultSet) throws SQLException {
        Department department = new Department();
        department.setId(mResultSet.getInt("id"));
        department.setName(mResultSet.getString("name"));
        return department;
    }
    public Department findByID (int id) {
        Department department = new Department();
        String sql = "Select * from department where id = ? ";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, id);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                department = convert(mResultSet);
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return department;
    }
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        try {
            open();
            String sql = "Select * from department";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("id");
                String name = mResultSet.getString("name");
                departmentList.add(new Department(id, name));
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }
}
