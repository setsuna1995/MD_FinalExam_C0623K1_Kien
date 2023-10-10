package com.example.md3_finalexam.controller;

import com.example.md3_finalexam.model.Department;
import com.example.md3_finalexam.model.Staff;
import com.example.md3_finalexam.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "staffServlet", urlPatterns = "/staff-servlet")
public class StaffServlet extends HttpServlet {
    StaffService staffService = new StaffService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editStaffForm(req, resp);
                break;
            case "delete":
                try {
                    deleteStaff(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                createStaffForm(req, resp);
                break;
            case "find":
                findByNameForm(req, resp);
            default:
                listStaff(req, resp);
                break;
        }

    }

    private void findByNameForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff/find.jsp");
            requestDispatcher.forward(req, resp);
    }

    private void createStaffForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void listStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff/list.jsp");
        List<Staff> staffList = staffService.findAll();
        req.setAttribute("staffs", staffList);
        requestDispatcher.forward(req, resp);
    }

    private void deleteStaff(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        staffService.deleteStaff(id);
        listStaff(req, resp);
    }

    private void editStaffForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    editStaff(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create":
                createStaff(req, resp);
                break;
            case "find":
                findByName(req, resp);
            default:
                break;
        }
    }

    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchStaff = req.getParameter("searchStaff");
        List<Staff> staffList = staffService.findAllToSearch(searchStaff);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff/list.jsp");
        req.setAttribute("staffs", staffList);
        requestDispatcher.forward(req, resp);
    }

    private void createStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        float salary = Float.parseFloat(req.getParameter("salary"));
        Department department = new Department();
        department.setId(Integer.parseInt(req.getParameter("departmentID")));
        Staff staff = new Staff( name, address, phoneNumber, salary, department);
        staffService.insert(staff);
        listStaff(req, resp);
    }

    private void editStaff(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        float salary = Float.parseFloat(req.getParameter("salary"));
        Department department = new Department();
        department.setId(Integer.parseInt(req.getParameter("departmentID")));
        Staff staff = new Staff(id, name, address, phoneNumber, salary, department);
        staffService.updateStaff(staff);
        listStaff(req, resp);
    }
}