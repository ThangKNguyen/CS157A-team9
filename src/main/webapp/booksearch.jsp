<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LibTrack - Search</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-gray-50">
    <!-- Search Bar Section -->
    <div class="flex flex-col items-center p-6">
        <div class="flex items-center space-x-3 mb-4">
            <i class="fa-brands fa-readme text-4xl text-black"></i>
            <h2 class="text-4xl font-bold mb-2 ml-1">Welcome to LibTrack</h2>
        </div>
        <form action="BookConnection" method="GET" class="flex items-center space-x-2 w-full max-w-3xl">
            <div class="relative flex-grow">
                <input 
                    type="text" 
                    name="bookName" 
                    placeholder="Search for a book or author..." 
                    class="w-full h-12 pl-12 pr-4 text-lg border-2 border-gray-300 rounded-full focus:border-blue-400 focus:ring focus:ring-blue-200 focus:ring-opacity-50"
                />
                
                <i class="fa-brands fa-searchengin absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
            </div>
           
        </form>
    </div>

    <!-- Results Section -->
    <div class="p-6">
        <c:choose>
            <c:when test="${not empty searchResults}">
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <c:forEach items="${searchResults}" var="book">
                        <div class="bg-white rounded-lg shadow-md p-6">
                            <h3 class="text-xl font-bold mb-4">${book.title}</h3>
                            <p class="text-gray-600">Author: ${authorName}</p>
                            <p class="text-gray-600">Genre: ${genreName}</p>
                            <p class="text-gray-600">ISBN: ${book.ISBN}</p>
                            <div class="mt-4">
                                <span class="px-2 py-1 text-sm rounded-full 
                                    ${book.availability == 'Available' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}">
                                    ${book.availability}
                                </span>
                            </div>
                            <div class="mt-4">
                                <form action="ReserveBook" method="post">
                                    <input type="hidden" name="bookId" value="${book.bookId}">
                                    <button type="submit" 
                                        class="w-full px-4 py-2 text-sm text-blue-600 border border-blue-600 rounded-lg hover:bg-blue-50 
                                        ${book.availability != 'Available' ? 'opacity-50 cursor-not-allowed' : ''}"
                                        ${book.availability != 'Available' ? 'disabled' : ''}>
                                        Reserve
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center text-gray-600">
                    <p>${errorMessage}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>


