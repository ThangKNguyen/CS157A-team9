<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div class="col-span-2 bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Recently Borrowed</h2>
                    
                </div>
                <div class="p-4">
                    <ul class="space-y-4">
                        <c:forEach var="book" items="${recentlyBorrowed}">
                            <li class="flex justify-between items-center">
                                <div>
                                    <h3 class="font-semibold"><c:out value="${book.title}"/></h3>
                                    <p class="text-sm text-gray-500"><c:out value="${book.author}"/></p>
                                </div>
                                <div class="flex items-center space-x-2 text-sm text-gray-500">
                                    <svg class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m0-4l3 3m-3-3h-4m3-3V8m-3 3h-4m0 0H9m6 0H12" /></svg>
                                    <span>Due: <c:out value="${book.dueDate}"/></span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Quick Actions</h2>
                </div>
                <div class="p-4 space-y-2">
                    <button class="w-full flex justify-start border border-gray-300 rounded-md p-2">
                       <i class="fa-solid fa-magnifying-glass mr-3 mt-1"></i>
                        Browse Catalog
                    </button>
                    <button class="w-full flex justify-start border border-gray-300 rounded-md p-2">
                        <i class="fa-solid fa-plus mr-3 mt-1"></i>
                        Reservations
                    </button>
                    <button class="w-full flex justify-start border border-gray-300 rounded-md p-2">
                       <i class="fa-solid fa-dollar-sign mr-3 mt-1"></i>
                       
                       Pay Fines
                    </button>
                </div>
            </div>

            <div class="col-span-2 bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Recommended for You</h2>
                    <p class="text-sm text-gray-500">Based on your reading history</p>
                </div>
                <div class="p-4">
                    <div class="flex space-x-4 overflow-x-auto pb-4">
                        <c:forEach var="book" items="${recommendedBooks}">
                            <div class="flex-none w-32">
                                <img src="<c:out value='${book.imageUrl}'/>" alt="<c:out value='${book.title}'/>" class="w-full h-40 object-cover rounded-md mb-2" />
                                <h3 class="font-semibold text-sm"><c:out value="${book.title}"/></h3>
                                <p class="text-xs text-gray-500"><c:out value="${book.author}"/></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="bg-white shadow rounded-lg">
                <div class="p-4 border-b">
                    <h2 class="text-xl font-semibold">Account Overview</h2>
                </div>
                <div class="p-4 space-y-4">
                    <div class="flex items-center space-x-4">
                        <div class="flex mr-3">
                            <i class="fa-solid fa-user text-3xl"></i>
                        </div>
                        <div>
                            <p class="font-semibold"><c:out value="${loggedInUser.name}"/></p>
                            <p class="text-sm text-gray-500">Member since 2021</p>
                        </div>
                    </div>
                    <div>
                        <p class="text-sm font-semibold">Current Loans: 3</p>
                        <p class="text-sm font-semibold">Current Fees: 3</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

