<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/10/2023
  Time: 10:03 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
            integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
            crossorigin="anonymous"></script>
</head>
<body>
<a STYLE="text-align: center" href="staff-servlet?action=create">CREATE</a>
<a STYLE="text-align: center" href="staff-servlet?action=find">FIND</a>
<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Phone Number</th>
        <th scope="col">Salary</th>
        <th scope="col">Department</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="staff" items="${staffs}">
    <tr>
        <th scope="row">${staff.id}</th>
        <td>${staff.name}</td>
        <td>${staff.address}</td>
        <td>${staff.phoneNumber}</td>
        <td>${staff.salary}</td>
        <td>${staff.department.name}</td>
        <td><a href="staff-servlet?action=edit&id=${staff.id}">EDIT</a></td>
        <td><a href="staff-servlet?action=delete&id=${staff.id}">DELETE</a></td>
    </tr>
    </c:forEach>


</table>
</body>
</html>
