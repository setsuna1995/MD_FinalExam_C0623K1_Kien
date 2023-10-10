package com.example.md3_finalexam.service;

import com.example.md3_finalexam.model.Department;
import com.example.md3_finalexam.model.Staff;
import com.example.md3_finalexam.ultil.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffService extends ConnectionUtil {
    DepartmentService departmentService = new DepartmentService();
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
    public Staff convert (ResultSet mResultSet) throws SQLException {
        Staff staff = new Staff();
        staff.setId(mResultSet.getInt("id"));
        staff.setName(mResultSet.getString("name"));
        staff.setAddress(mResultSet.getString("address"));
        staff.setPhoneNumber(mResultSet.getString("phoneNumber"));
        staff.setSalary(mResultSet.getFloat("salary"));
        Department department = new Department();
        department.setId(mResultSet.getInt("departmentID"));
        try {
            department.setName(mResultSet.getString("department.name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        staff.setDepartment(department);
        return staff;
    }
    public List<Staff> findAllToSearch(String searchStaff) {
            List<Staff> staffList = new ArrayList<>();
            String sql = "Select * " +
                    "from staff join department on staff.departmentID = department.id " +
                    "where staff.name like ? ";

            try {
                open();
                mPreparedStatement = mConnection.prepareStatement(sql);
                mPreparedStatement.setString(1, "%" + searchStaff + "%");
                mResultSet = mPreparedStatement.executeQuery();
                while (mResultSet.next()) {
                    Staff staff = convert(mResultSet);
                    staffList.add(staff);
                }
                close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return staffList;
        }
    public Staff findByID(int id) {
        Staff staff = new Staff();
        String sql = "Select * " +
                "from staff join department on staff.departmentID = department.id " +
                "where staff.id = ? ";

        try {
            open();
            mPreparedStatement = mConnection.prepareStatement(sql);
            mPreparedStatement.setInt(1, id);
            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                staff = convert(mResultSet);
            }
            close();
            Department department = departmentService.findByID(staff.getDepartment().getId());
            staff.setDepartment(department);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return staff;
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
