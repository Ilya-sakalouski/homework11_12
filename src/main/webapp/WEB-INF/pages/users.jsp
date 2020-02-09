<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users table</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm">
        </div>
        <div class="col-sm">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                <tr>
                    <td>Username</td>
                    <td>Password</td>
                    <td>CreatedBy</td>
                    <td>Role</td>
                    <td>Description</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>

                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.createdBy}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.description}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <с:choose>
                <с:when test="${fn:length(users) eq 0}">
                    No users!
                </с:when>
                <с:otherwise>
                    That's all users!
                </с:otherwise>
            </с:choose>
            <a href="${pageContext.request.contextPath}/addUsers">Add user</a><br>
        </div>
        <div class="col-sm">
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
