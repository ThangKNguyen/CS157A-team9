<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="staffHeader.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibTrack Books</title>
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
    
    function toggleUpdatePopup() {
        const popup = document.getElementById('updatePopup');
        popup.classList.toggle('hidden');
    }

    // Update the button's state and style
    function updateButtonState() {
        const title = document.getElementById('title').value.trim();
        const author = document.getElementById('author').value.trim();
        const category = document.getElementById('category').value.trim();
        const genre = document.getElementById('genre').value.trim();
        const isbn = document.getElementById('isbn').value.trim();
       
        const searchISBN = document.getElementById('searchISBN').value.trim();
        
        const uTitle = document.getElementById('updateTitle').value.trim();
        const uAuthor = document.getElementById('updateAuthor').value.trim();
        const uCategory = document.getElementById('updateCategory').value.trim();
        const uGenre = document.getElementById('updateGenre').value.trim();
        const uISBN = document.getElementById('updateISBN').value.trim();
        const uAvailability = document.getElementById('updateAvailability').value.trim();

        const addButton = document.getElementById('confirmAddButton');
        const addFieldsFilled = title && author && category && genre && isbn;
        
        const removeButton = document.getElementById('confirmRemoveButton');
        const removeFieldsFilled = !!searchISBN;
        
        const updateButton = document.getElementById('confirmUpdateButton');
        const updateFieldsFilled = uTitle && uAuthor && uCategory && uGenre && uISBN && uAvailability;

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
        
        if (updateFieldsFilled) {
        	updateButton.classList.remove('bg-blue-400');
        	updateButton.classList.add('bg-blue-600');
        	updateButton.disabled = false;
        } else {
        	updateButton.classList.remove('bg-blue-600');
        	updateButton.classList.add('bg-blue-400');
        	updateButton.disabled = true;
        }
    }
    </script>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <h1 class="text-3xl font-bold text-gray-800">Book Inventory</h1>
        </header>

        <main>
            <div class="bg-white shadow-md rounded-lg overflow-hidden">
                <div class="overflow-x-auto">
                    <table class="w-full">
                        <thead class="bg-gray-50">
                            <tr>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Title</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Author</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Category</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Genre</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ISBN</th>
                                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Availability</th>
                            </tr>
                        </thead>
                        <tbody class="bg-white divide-y divide-gray-200">
                            <c:forEach var="book" items="${books}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${book.title}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${book.author}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${book.category}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                        <c:out value="${book.genre}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                        <c:out value="${book.ISBN}" />
                                    </td>
                                    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                        <c:out value="${book.availability}" />
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
            <h2 class="text-2xl font-bold">Manage Books</h2>
            <p class="text-gray-600 text-base mt-1">Add, remove, or update books</p>
             
        </div>

        <!-- Footer w/management options -->
        <div class="p-6 border-t flex justify-center space-x-5">
            <button onclick="toggleAddPopup()" class="flex items-center justify-center bg-black text-white px-3 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-plus mr-3 mt-1"></i>
                Add a book
            </button>
            <button onclick="toggleRemovePopup()" class="flex items-center justify-center bg-black text-white px-3 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-minus mr-3 mt-1"></i>
                Remove a book
            </button>
            <button onclick="toggleUpdatePopup()" class="flex items-center justify-center bg-black text-white px-3 py-3 rounded-lg shadow-md w-full transform transition duration-200 hover:scale-105">
                <i class="fa-solid fa-book mr-3 mt-1"></i>
                Update book information
            </button>
        </div>
        
    </div>

    <!-- Popup for Add Books -->
    <div id="addPopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Add a new book</h2>
            <p class="text-sm text-gray-600 mt-1">Enter book details below.</p>
        </div>
        <form action="ViewBooks" method="post">
            <div class="mb-4">
                <label for="title" class="block text-sm font-medium text-gray-700 mb-2">Book Title</label>
                <input type="text" id="title" name="title"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="author" class="block text-sm font-medium text-gray-700 mb-2">Author</label>
                <input type="text" id="author" name="author"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: John Doe" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="category" class="block text-sm font-medium text-gray-700 mb-2">Category</label>
                <input type="text" id="category" name="category" minlength="6"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: Non-Fiction" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="genre" class="block text-sm font-medium text-gray-700 mb-2">Genre</label>
                <input type="text" id="genre" name="genre"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: Fantasy" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="isbn" class="block text-sm font-medium text-gray-700 mb-2">ISBN</label>
                <input type="number" id="isbn" name="isbn"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="13-digit ISBN" oninput="updateButtonState()">
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

<!-- Popup for Add Books -->
<div id="removePopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Remove a book</h2>
            <p class="text-sm text-gray-600 mt-1">Search by ISBN</p>
        </div>
        <form action="ViewBooks" method="post">
            <div class="mb-4">
                <label for="searchISBN" class="block text-sm font-medium text-gray-700 mb-2">ISBN</label>
                <input type="number" id="searchISBN" name="searchISBN"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="13-digit ISBN code" oninput="updateButtonState()">
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

<!-- Popup for Update Books -->
    <div id="updatePopup" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
    <div class="bg-white rounded-lg p-6 w-96 shadow-lg">
        <div class="mb-4">
            <h2 class="text-lg font-bold text-gray-800">Update Book Listing</h2>
            <p class="text-sm text-gray-600 mt-1">Enter new information below.</p>
        </div>
        <form action="ViewBooks" method="post">
            <div class="mb-4">
                <label for="updateISBN" class="block text-sm font-medium text-gray-700 mb-2">ISBN</label>
                <input type="number" id="updateISBN" name="updateISBN"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ISBN of the book to be updated" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="updateTitle" class="block text-sm font-medium text-gray-700 mb-2">Book Title</label>
                <input type="text" id="updateTitle" name="updateTitle"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="updateAuthor" class="block text-sm font-medium text-gray-700 mb-2">Author</label>
                <input type="text" id="updateAuthor" name="updateAuthor"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: John Doe" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="updateCategory" class="block text-sm font-medium text-gray-700 mb-2">Category</label>
                <input type="text" id="updateCategory" name="updateCategory" minlength="6"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: Non-Fiction" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="updateGenre" class="block text-sm font-medium text-gray-700 mb-2">Genre</label>
                <input type="text" id="updateGenre" name="updateGenre"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="ex: Fantasy" oninput="updateButtonState()">
            </div>
            <div class="mb-4">
                <label for="updateAvailability" class="block text-sm font-medium text-gray-700 mb-2">Availability</label>
                <input type="text" id="updateAvailability" name="updateAvailability"
                       class="block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500" 
                       required placeholder="Available, Borrowed, Returned" oninput="updateButtonState()">
            </div>
            <div class="flex justify-end">
                <button type="button" onclick="toggleUpdatePopup()" class="bg-gray-500 text-white px-4 py-2 rounded-lg mr-2 hover:bg-black">
                    Cancel
                </button>
                <button id="confirmUpdateButton" type="submit" class="bg-blue-400 text-white px-4 py-2 rounded-lg hover:bg-blue-700" disabled>
                    Confirm
                </button>
            </div>
        </form>
    </div>
</div>


</div>
</body>
</html>