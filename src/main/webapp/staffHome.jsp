<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff Profile - LibTrack</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <h1 class="text-3xl font-bold text-gray-800 mb-6">User Profile</h1>
        
        <div class="bg-white shadow-md rounded-lg p-6 mb-6">
            <h2 class="text-2xl font-semibold text-gray-700 mb-4">Personal Information</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                    <p class="text-sm text-gray-600">Name</p>
                    <p class="text-lg font-medium text-gray-800">${loggedInUser.name}</p>
                </div>
                <div>
                    <p class="text-sm text-gray-600">Email</p>
                    <p class="text-lg font-medium text-gray-800">${loggedInUser.email}</p>
                </div>
                <div>
                    <p class="text-sm text-gray-600">Phone</p>
                    <p class="text-lg font-medium text-gray-800">${loggedInUser.phone}</p>
                </div>
                <div>
                    <p class="text-sm text-gray-600">Address</p>
                    <p class="text-lg font-medium text-gray-800">${loggedInUser.address}</p>
                </div>
                
                
            </div>
        </div>
        
        <div class="bg-white shadow-md rounded-lg p-6">
            <h2 class="text-2xl font-semibold text-gray-700 mb-4">Library Activities</h2>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
                <a href="BorrowingHistory" class="block p-4 bg-blue-100 rounded-lg hover:bg-blue-200 transition duration-300">
                    <h3 class="text-lg font-semibold text-blue-800 mb-2">Borrowing History</h3>
                    <p class="text-sm text-blue-600">View your past and current borrows</p>
                </a>
                <a href="Fines" class="block p-4 bg-red-100 rounded-lg hover:bg-red-200 transition duration-300">
                    <h3 class="text-lg font-semibold text-red-800 mb-2">Fines</h3>
                    <p class="text-sm text-red-600">Check and pay your outstanding fines</p>
                </a>
                <a href="Reservations" class="block p-4 bg-green-100 rounded-lg hover:bg-green-200 transition duration-300">
                    <h3 class="text-lg font-semibold text-green-800 mb-2">Reservations</h3>
                    <p class="text-sm text-green-600">Manage your book reservations</p>
                </a>
            </div>
        </div>
    </div>
</body>
</html>