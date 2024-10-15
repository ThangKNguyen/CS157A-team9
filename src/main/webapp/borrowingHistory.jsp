<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Borrowing History</title>
</head>
<body>
	<h2>Welcome, ${name}!</h2>
	<h3>Your Borrowing History:</h3>

    <table border="1">
        <thead>
            <tr>
                <th>Borrow ID</th>
                <th>Book Title</th>
                <th>Borrow Date</th>
                <th>Due Date</th>
                <th>Return Date</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${borrowingHistory}">
                <tr>
                    <td>${item.borrowId}</td>
                    <td>${item.title}</td>
                    <td>${item.borrowDate}</td>
                    <td>${item.dueDate}</td>
                    <td>${item.returnDate == null ? "Not returned yet" : item.returnDate}</td>
                    <td>${item.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>