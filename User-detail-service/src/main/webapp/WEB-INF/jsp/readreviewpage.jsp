<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Review Application!!</title>
</head>
<body>
<table>
    <c:forEach var = "read" items ="${reviews}">
    <tr>
        <td>     <c:out value="${read.reviewdesc}"/> </td>
        </tr>
   </c:forEach>
   </table>
</body>
</html>