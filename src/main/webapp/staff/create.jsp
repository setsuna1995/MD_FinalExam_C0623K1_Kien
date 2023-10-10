<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/10/2023
  Time: 10:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form style="text-align: justify" METHOD="post">
    <h1>CREATE NEW EMPLOYEE</h1>
    <div class="form-group" style="margin: 20px">
        <label for="exampleInputName">Name</label>
        <br>
        <input type="text" class="form-control" id="exampleInputName" name="name" placeholder="Enter name">
    </div>
    <div class="form-group" style="margin: 20px">
        <label for="exampleInputAddress">Address</label><br>
        <input type="text" class="form-control" id="exampleInputAddress" name="address" placeholder="Enter address">
    </div>
    <div class="form-group" style="margin: 20px">
        <label for="exampleInputPhoneNumber">Phone Number</label><br>
        <input type="text" class="form-control" id="exampleInputPhoneNumber" aria-describedby="emailHelp"
               placeholder="Enter phone number">
    </div>
    <div class="form-group" style="margin: 20px">
        <label for="exampleInputSalary">Salary</label><br>
        <input type="text" class="form-control" id="exampleInputSalary" name="salary" placeholder="Enter salary">
    </div>
    <div class="form-group" style="margin: 20px">
        <label for="exampleInputDepartmentID">Department</label><br>
        <input type="text" class="form-control" id="exampleInputDepartmentID" aria-describedby="emailHelp"
               name="departmentID" placeholder="Enter department id">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
