<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>Library Homepage</title>
</head>
<body>
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <h1 class="text-3xl font-bold">Welcome back, <c:out value="${loggedInUser.name}" default="Alice"/>!</h1>
        </header>
        
        <!-- Count borrowed books -->
        <c:set var="borrowedCount" value="0"/>
        <c:forEach var="item" items="${borrowingHistory}">
            <c:if test="${item.status == 'Borrowed'}">
                <c:set var="borrowedCount" value="${borrowedCount + 1}" />
            </c:if>
        </c:forEach>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
        
            <!-- Borrowed Books Table -->
            <div class="col-span-2 bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Recently Borrowed</h2>
                </div>
                
                <div class="bg-white shadow-md rounded-lg overflow-hidden">
                    <div class="overflow-x-auto">
                        <table class="w-full">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Book Title</th>
                                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Borrow Date</th>
                                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Due Date</th>
                                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Return Date</th>
                                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                                </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                                <c:forEach var="item" items="${borrowingHistory}" varStatus="status">
                                    <c:if test="${status.index < 3}"> <!-- Only show the first 3 items -->
                                        <tr>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><c:out value="${item.title}" /></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out value="${item.borrowDate}"/></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><c:out value="${item.dueDate}"/></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                                <c:choose>
                                                    <c:when test="${item.returnDate == ''}">
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
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Quick Actions and Account Overview -->
            <div class="bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Quick Actions</h2>
                </div>
                <div class="p-4 space-y-2">
                    <div class="w-full flex justify-start border border-gray-300 rounded-md p-2 transform transition duration-200 hover:scale-105">
                        <i class="fa-solid fa-magnifying-glass mr-3 mt-1"></i>
                        <a href="BookConnection" class="flex-1">Browse Catalog</a>
                    </div>
                    <div class="w-full flex justify-start border border-gray-300 rounded-md p-2 transform transition duration-200 hover:scale-105">
                        <i class="fa-solid fa-plus mr-3 mt-1"></i>
                        <a href="Reservations" class="flex-1">Reservations</a>
                    </div>
                    <div class="w-full flex justify-start border border-gray-300 rounded-md p-2 transform transition duration-200 hover:scale-105">
                        <i class="fa-solid fa-dollar-sign mr-3 mt-1"></i>
                        <a href="Fines" class="flex-1">Pay Fines</a>
                    </div>
                </div>
            </div>
            
            <div class="col-span-2 bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Recommended for You</h2>
                    <p class="text-sm text-gray-500">Based on your reading history</p>
                </div>
                
               <table class="w-full bg-white">
					    <thead class="bg-gray-50">
					        <tr>
					            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Book Title</th>
					            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Authors</th>
					            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Genre</th>
					            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ISBN</th>
					        </tr>
					    </thead>
					    <tbody>
					        <c:set var="count" value="0"/>
					        <c:forEach var="book" items="${availableBooks}">
					            <!-- Check if the book is not in borrowingHistory -->
					            <c:set var="isBorrowed" value="false"/>
					            <c:forEach var="borrowedBook" items="${borrowingHistory}">
					                <c:if test="${borrowedBook.title == book.title}">
					                    <c:set var="isBorrowed" value="true"/>
					                </c:if>
					            </c:forEach>
					
					            <!-- Display the book only if it's not borrowed and count is less than 3 -->
					            <c:if test="${!isBorrowed && count < 3}">
					                <tr class="border-b border-gray-300">
					                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${book.title}</td>
					                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${book.author}</td>
					                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${book.genre}</td>
					                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">${book.ISBN}</td>
					                </tr>
					                <!-- Increment the count after displaying a book -->
					                <c:set var="count" value="${count + 1}"/>
					            </c:if>
					        </c:forEach>
					    </tbody>
				</table>
               
               
                
                
            </div>

            <div class="bg-white shadow rounded-lg">
    <div class="p-4 border-b">
        <h2 class="text-xl font-semibold">Account Overview</h2>
    </div>
    <div class="p-4 space-y-4 mt-2">
        <div class="flex items-center space-x-4">
            <div class="flex mr-3">
                <i class="fa-solid fa-user text-4xl"></i>
            </div>
            <div>
                <p class="text-lg font-semibold"><c:out value="${loggedInUser.name}"/></p>
                <p class="text-lg text-gray-500">Member since ${loggedInUser.joinDate.substring(0, 4)}</p>
            </div>
        </div>
        
        <!-- Books Currently Borrowed Section -->
        <div class="mt-4">
            <c:choose>
                <c:when test="${borrowedCount == 0}">
                    <p class="text-lg font-semibold text-gray-500">No currently borrowed books!</p>
                </c:when>
                <c:otherwise>
                    <p class="text-lg font-semibold">Books Currently Borrowed: <c:out value="${borrowedCount}"/></p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- Fines Section -->
        <div class="flex items-center mt-3">
            <c:choose>
                <c:when test="${fines.stream().filter(f -> f.paidStatus != 'Paid').map(f -> f.fineAmount).sum() == 0}">
                    <p class="text-lg font-semibold text-gray-500">No outstanding fines!</p>
                </c:when>
                <c:otherwise>
                    <p class="text-lg font-semibold mr-2">Outstanding Fines:</p>
                    <p class="text-lg font-semibold text-gray-900">
                        <fmt:formatNumber value="${fines.stream().filter(f -> f.paidStatus != 'Paid').map(f -> f.fineAmount).sum()}" type="currency" currencySymbol="$" />
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
            
        </div>
    </div>
</body>
</html>


