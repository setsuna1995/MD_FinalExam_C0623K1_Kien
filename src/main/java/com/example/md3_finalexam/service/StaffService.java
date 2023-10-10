package com.example.md3_finalexam.service;

import com.example.md3_finalexam.model.Department;
import com.example.md3_finalexam.model.Staff;
import com.example.md3_finalexam.ultil.ConnectionUtil;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffService extends ConnectionUtil {
    public boolean deleteStaff(int id) throws SQLException {
        boolean rowDeleted = false;
        String DELETE_USERS_SQL = "delete from staff where id = ?;";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(DELETE_USERS_SQL);
            mPreparedStatement.setInt(1, id);
            rowDeleted = mPreparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        String sql = "update staff set name = ?,address= ?, phoneNumber =?, salary =?, departmentID = ? where id = ?";
        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setString(1, staff.getName());
            mPreparedStatement.setString(2, staff.getAddress());
            mPreparedStatement.setString(3, staff.getPhoneNumber());
            mPreparedStatement.setFloat(4, staff.getSalary());
            mPreparedStatement.setInt(5, staff.getDepartment().getId());
            mPreparedStatement.setInt(6, staff.getId());
            rowUpdated = mPreparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return rowUpdated;
    }

    public List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        try {
            open();
            String sql = "Select * from staff join department on staff.departmentID = department.id";
            mPreparedStatement = mConnection.prepareStatement(sql);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("id");
                String name = mResultSet.getString("name");
                String address = mResultSet.getString("address");
                String phoneNumber = mResultSet.getString("phoneNumber");
                float salary = mResultSet.getFloat("salary");
                Department department = new Department();
                department.setId(mResultSet.getInt("departmentID"));
                department.setName(mResultSet.getString("department.name"));
                staffList.add(new Staff(id, name, address, phoneNumber, salary, department));
            }
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return staffList;
    }

    public void insert (Staff staff) {
        String sql = "INSERT INTO staff ( `name`, address, phoneNumber, salary, departmentID) VALUES (?, ? , ? , ? , ?)";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, staff.getName());
            mPreparedStatement.setString(2, staff.getAddress());
            mPreparedStatement.setString(3, staff.getPhoneNumber());
            mPreparedStatement.setFloat(4, staff.getSalary());
            mPreparedStatement.setInt(5, staff.getDepartment().getId());
            mPreparedStatement.executeUpdate();
            mResultSet = mPreparedStatement.getGeneratedKeys();
            close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
