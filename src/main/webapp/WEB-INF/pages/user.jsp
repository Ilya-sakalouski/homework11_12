<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addUsers" method="post">
    Username:<label>
    <input name="username" type="text" minlength="1" maxlength="40" required>
</label><br>
    Password:<label>
    <input name="password" type="password" minlength="1" maxlength="40" required>
</label><br>
    CreatedBy:<label>
    <input name="createdBy" type="text" minlength="1" maxlength="40" required>
</label><br>
    Role:<label>
    <select v-model="selected">
        <option disabled value="">choose value</option>
        <option>USER</option>
        <option>ADMIN</option>
    </select></label><br>
    </label><br>
    Description:<label>
    <input name="description" type="text" maxlength="100" required>
</label> <br>
    <input type="submit" value="Add">
</form>
</body>
</html>
