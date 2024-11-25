<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Fines - LibTrack</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script>
    // Toggle popup visibility
    function togglePopup() {
        const popup = document.getElementById('payFinesPopup');
        popup.classList.toggle('hidden');
    }

    // Update the button's state and style
    function updateButtonState() {
        const amount = document.getElementById('amount').value.trim();
        const cardNumber = document.getElementById('cardNumber').value.trim();
        const expiryDate = document.getElementById('expiryDate').value.trim();
        const cvv = document.getElementById('cvv').value.trim();

        const button = document.getElementById('confirmButton');
        const allFieldsFilled = amount && cardNumber && expiryDate && cvv;

        if (allFieldsFilled) {
            button.classList.remove('bg-blue-400');
            button.classList.add('bg-blue-600');
            button.disabled = false;
        } else {
            button.classList.remove('bg-blue-600');
            button.classList.add('bg-blue-400');
            button.disabled = true;
        }
    }

    // Validate form on submission
    function validateForm() {
        const amount = document.getElementById('amount').value;
        const cardNumber = document.getElementById('cardNumber').value;
        const expiryDate = document.getElementById('expiryDate').value;
        const cvv = document.getElementById('cvv').value;

        if (!amount || parseFloat(amount) <= 0) {
            alert("Please enter a valid payment amount.");
            return false;
        }

        if (!/^\d{16}$/.test(cardNumber)) {
            alert("Please enter a valid 16-digit card number.");
            return false;
        }

        if (!/^\d{2}\/\d{2}$/.test(expiryDate)) {
            alert("Please enter a valid expiry date in MM/YY format.");
            return false;
        }

        if (!/^\d{3}$/.test(cvv)) {
            alert("Please enter a valid 3-digit CVV.");
            return false;
        }

        return true;
    }
    </script>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <h1 class="text-3xl font-bold text-gray-800">Your Fines</h1>
        </header>

        <main>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <div class="overflow-x-auto">
                    <table class="w-full">
                        <thead class="bg-gray-50">
                            <tr>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Book Title</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fine Amount</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Payment Status</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Issued Date</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Due Date</th>
                            </tr>
                        </thead>
                        <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach var="fine" items="${fines}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${fine.bookTitle}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <fmt:formatNumber value="${fine.fineAmount}" type="currency" currencySymbol="$" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                                            ${fine.paidStatus == 'Paid' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}">
                                            <c:out value="${fine.paidStatus}" />
                                        </span>
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <c:out value="${fine.issuedDate}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                        <c:out value="${fine.finesDueDate}" />
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
            <h2 class="text-2xl font-bold">Current Fines</h2>
            <p class="text-gray-600 text-base mt-1">Review and pay your outstanding library fines</p>
             
        </div>

        <!-- Content -->
        <div class="p-6">
            <div class="flex items-center justify-between py-4">
                <span class="text-2xl font-semibold">Total Due:</span>
                <span class="text-2xl font-bold text-red-600">
                    <fmt:formatNumber value="${fines.stream().filter(f -> f.paidStatus != 'Paid').map(f -> f.fineAmount).sum()}" type="currency" currencySymbol="$" />
                </span>
            </div>
        </div>

        <!-- Footer with Pay Fines Button -->
        <div class="p-6 border-t flex justify-center">
            <button onclick="togglePopup()" class="flex items-center justify-center bg-black text-white px-6 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-dollar-sign mr-3 mt-1"></i>
                Pay Fines
            </button>
        </div>
    </div>

    <!-- Popup for Pay Fines -->
    <div id="payFinesPopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Pay Fines</h2>
            <p class="text-sm text-gray-600 mt-1">Enter your payment details to settle your fines.</p>
        </div>
        <form action="Fines" method="post" onsubmit="return validateForm()">
            <div class="mb-4">
                <label for="amount" class="block text-sm font-medium text-gray-700 mb-2">Amount</label>
                <input type="number" id="amount" name="amount" min="0.01" step="0.01"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="0.00" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="cardNumber" class="block text-sm font-medium text-gray-700 mb-2">Card Number</label>
                <input type="text" id="cardNumber" name="cardNumber" maxlength="16"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="1234 5678 9012 3456" oninput="updateButtonState()">
            </div>
            <div class="mb-4 grid grid-cols-2 gap-4">
                <div>
                    <label for="expiryDate" class="block text-sm font-medium text-gray-700 mb-2">Expiry Date</label>
                    <input type="text" id="expiryDate" name="expiryDate" maxlength="5"
                           class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                           required placeholder="MM/YY" oninput="updateButtonState()">
                </div>
                <div>
                    <label for="cvv" class="block text-sm font-medium text-gray-700 mb-2">CVV</label>
                    <input type="text" id="cvv" name="cvv" maxlength="3"
                           class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                           required placeholder="123" oninput="updateButtonState()">
                </div>
            </div>
            <div class="flex justify-end">
                <button type="button" onclick="togglePopup()" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-black">
                    Cancel
                </button>
                <button id="confirmButton" type="submit" class="bg-blue-400 text-white px-4 py-2 rounded-lg hover:bg-blue-700" disabled>
                    Confirm Payment
                </button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
