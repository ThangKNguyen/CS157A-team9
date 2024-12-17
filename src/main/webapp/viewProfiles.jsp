<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="adminHeader.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibTrack User Profiles</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script>
    // Toggle popup visibility
    function toggleAddPopup() {
        const popup = document.getElementById('addPopup');
        popup.classList.toggle('hidden');
    }
    
    function toggleRemovePopup() {
        const popup = document.getElementById('removePopup');
        popup.classList.toggle('hidden');
    }

    // Update the button's state and style
    function updateButtonState() {
        const name = document.getElementById('name').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const address = document.getElementById('address').value.trim();
        const memberId = document.getElementById('memberId').value.trim();

        const addButton = document.getElementById('confirmAddButton');
        const addFieldsFilled = name && email && password && phone && address;
        
        const removeButton = document.getElementById('confirmRemoveButton');
        const removeFieldsFilled = !!memberId;

        if (addFieldsFilled) {
            addButton.classList.remove('bg-blue-400');
            addButton.classList.add('bg-blue-600');
            addButton.disabled = false;
        } else {
        	addButton.classList.remove('bg-blue-600');
        	addButton.classList.add('bg-blue-400');
        	addButton.disabled = true;
        }
        
        if (removeFieldsFilled) {
        	removeButton.classList.remove('bg-blue-400');
        	removeButton.classList.add('bg-blue-600');
        	removeButton.disabled = false;
        } else {
        	removeButton.classList.remove('bg-blue-600');
        	removeButton.classList.add('bg-blue-400');
        	removeButton.disabled = true;
        }
    }
    </script>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <h1 class="text-3xl font-bold text-gray-800">LibTrack Users</h1>
        </header>

        <main>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <div class="overflow-x-auto">
                    <table class="w-full">
                        <thead class="bg-gray-50">
                            <tr>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Member ID</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">E-mail</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Password</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone Number</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Address</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Join Date</th>
                            </tr>
                        </thead>
                        <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach var="member" items="${members}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${member.name}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${member.memberId}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${member.email}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${member.password}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${member.phone}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                        <c:out value="${member.address}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                        <c:out value="${member.joinDate}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

           <div class="container mx-auto px-4 py-8">
    <div class="bg-white shadow-lg rounded-lg w-full max-w-md mx-auto">
        <!-- Header -->
        <div class="p-6 border-b">
            <h2 class="text-2xl font-bold">Manage Users</h2>
            <p class="text-gray-600 text-base mt-1">Add or remove a user</p>
             
        </div>

        <!-- Footer w/management options -->
        <div class="p-6 border-t flex justify-center space-x-5">
            <button onclick="toggleAddPopup()" class="flex items-center justify-center bg-black text-white px-6 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-plus mr-3 mt-1"></i>
                Add a new user
            </button>
            <button onclick="toggleRemovePopup()" class="flex items-center justify-center bg-black text-white px-6 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-minus mr-3 mt-1"></i>
                Remove a user
            </button>
        </div>
        
    </div>

    <!-- Popup for Pay Fines -->
    <div id="addPopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Add a new user</h2>
            <p class="text-sm text-gray-600 mt-1">Enter user information below.</p>
        </div>
        <form action="ViewProfiles" method="post">
            <div class="mb-4">
                <label for="name" class="block text-sm font-medium text-gray-700 mb-2">Name</label>
                <input type="text" id="name" name="name"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="email" class="block text-sm font-medium text-gray-700 mb-2">Email</label>
                <input type="email" id="email" name="email"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="library@libtrack.com" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="password" class="block text-sm font-medium text-gray-700 mb-2">Password</label>
                <input type="text" id="password" name="password" minlength="6"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="phone" class="block text-sm font-medium text-gray-700 mb-2">Phone</label>
                <input type="tel" id="phone" name="phone"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="(123)-456-7890" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="address" class="block text-sm font-medium text-gray-700 mb-2">Address</label>
                <input type="text" id="address" name="address"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="1234 LibTrack Ave." oninput="updateButtonState()">
            </div>
            <div class="flex justify-end">
                <button type="button" onclick="toggleAddPopup()" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-black">
                    Cancel
                </button>
                <button id="confirmAddButton" type="submit" class="bg-blue-400 text-white px-4 py-2 rounded-lg hover:bg-blue-700" disabled>
                    Confirm
                </button>
            </div>
        </form>
    </div>
</div>

<div id="removePopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Remove a user</h2>
            <p class="text-sm text-gray-600 mt-1">Enter user ID below</p>
        </div>
        <form action="ViewProfiles" method="post">
            <div class="mb-4">
                <label for="memberId" class="block text-sm font-medium text-gray-700 mb-2">Member ID</label>
                <input type="number" id="memberId" name="memberId"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: 30853" oninput="updateButtonState()">
            </div>
            <div class="flex justify-end">
                <button type="button" onclick="toggleRemovePopup()" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-black">
                    Cancel
                </button>
                <button id="confirmRemoveButton" type="submit" class="bg-blue-400 text-white px-4 py-2 rounded-lg hover:bg-blue-700" disabled>
                    Confirm
                </button>
            </div>
        </form>
    </div>
</div>


</div>
</body>
</html>