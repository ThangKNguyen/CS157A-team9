<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrowing History</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <h1 class="text-3xl font-bold text-gray-800">Welcome, <c:out value="${loggedInUser.name}" /></h1>
        </header>

        <main>
            <h2 class="text-2xl font-semibold text-gray-700 mb-4">Your Borrowing History</h2>

            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <div class="overflow-x-auto">
                    <table class="w-full">
                        <thead class="bg-gray-50">
                            <tr>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Borrow ID</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Book Title</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Borrow Date</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Due Date</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Return Date</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                            </tr>
                        </thead>
                        <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach var="item" items="${borrowingHistory}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out value="${item.borrowId}" /></td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><c:out value="${item.title}" /></td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out value="${item.borrowDate}"/></td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out value="${item.dueDate}"/></td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <c:choose>
                                            <c:when test="${item.returnDate == \"\"}">
                                                <span class="text-yellow-600">Not returned yet</span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${item.returnDate}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                                            ${item.status == 'Returned' ? 'bg-green-100 text-green-800' : 
                                              item.status == 'Overdue' ? 'bg-red-100 text-red-800' : 
                                              'bg-yellow-100 text-yellow-800'}">
                                            <c:out value="${item.status}" />
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</body>
</html>