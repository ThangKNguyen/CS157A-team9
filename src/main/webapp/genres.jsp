<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Genres</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="flex h-screen">
        <!-- Sidebar -->
        <aside class="w-64 bg-white shadow-md">
            <div class="p-4">
                <h2 class="text-2xl font-bold text-gray-800">Genres</h2>
            </div>
            <nav class="mt-4">
                <ul>
                    <c:forEach var="genre" items="${genres}">
                        <li>
                            <a href="BooksByGenre?genreId=${genre.id}"
                               class="block py-2 px-4 text-gray-700 hover:bg-gray-200">
                                ${genre.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="flex-1 p-8 overflow-y-auto">
            <div class="container mx-auto px-4 py-8">
                <%
                if (request.getParameter("genreId") == null) {
                %>
                <h2 class="text-3xl font-bold text-gray-800 mb-6">No genre selected</h2>
                <p class="text-gray-600 mb-4">Please select a genre to view the books.</p>
                <%
                } else {
                %>
                <h2 class="text-3xl font-bold text-gray-800 mb-6">${genreName} Books</h2>
                <c:choose>
                    <c:when test="${empty books}">
                        <div class="w-full text-center py-12">
                            <p class="text-2xl font-semibold text-gray-700">No books in this genre.</p>
                            <p class="mt-2 text-gray-500">Please check back later or try another genre.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                            <c:forEach var="book" items="${books}">
                                <div class="bg-white rounded-lg shadow-md overflow-hidden">
                                    <div class="p-6">
                                        <h3 class="text-xl font-semibold text-gray-800 mb-2">${book.title}</h3>
                                        <p class="text-gray-600 mb-2">Author: ${book.author}</p>
                                        <p class="text-gray-600 mb-2">Genre: ${book.genre}</p>
                                        <p class="text-gray-300 mb-4">ISBN: ${book.ISBN}</p>
                                        <div class="flex justify-between items-center">
                                            <span
                                                class="px-3 py-1 rounded-full text-sm font-semibold ${book.availability eq 'Available' ? 'bg-green-100 text-green-800' : book.availability eq 'Reserved' ? 'bg-orange-100 text-orange-800' : 'bg-red-100 text-red-800'}">
                                                ${book.availability}
                                            </span>
                                            <c:choose>
                                                <c:when test="${book.availability eq 'Available' && not empty loggedInUser}">
                                                    <button onclick="openBorrowModal('${book.bookId}')"
                                                            class="border border-blue-500 text-blue-500 hover:bg-blue-100 font-semibold py-1 px-3 rounded focus:outline-none focus:shadow-outline transition duration-150 ease-in-out">
                                                        Borrow
                                                    </button>
                                                </c:when>
                                                <c:when test="${book.availability eq 'Borrowed' && not empty loggedInUser}">
                                                    <button onclick="openReserveModal('${book.bookId}')"
                                                            class="border border-orange-500 text-orange-500 hover:bg-orange-100 font-semibold py-1 px-3 rounded focus:outline-none focus:shadow-outline transition duration-150 ease-in-out">
                                                        Reserve
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
                <%
                }
                %>
            </div>
        </main>
    </div>

    <!-- Borrow Confirmation Modal -->
    <div id="borrowModal" class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full hidden" role="dialog" aria-labelledby="borrowModalTitle" aria-modal="true">
        <div class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
            <div class="mt-3 text-center">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="borrowModalTitle">Confirm Borrow</h3>
                <div class="mt-2 px-7 py-3">
                    <p class="text-sm text-gray-500">Are you sure you want to borrow this book?</p>
                    <div class="mt-4 flex justify-center">
                        <form action="BookBorrow" method="post" id="borrowForm">
                            <input type="hidden" id="borrowBookId" name="bookId">
                            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2">
                                Confirm
                            </button>
                        </form>
                        <button onclick="closeBorrowModal()" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Reserve Confirmation Modal -->
    <div id="reserveModal" class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full hidden" role="dialog" aria-labelledby="reserveModalTitle" aria-modal="true">
        <div class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
            <div class="mt-3 text-center">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="reserveModalTitle">Confirm Reservation</h3>
                <div class="mt-2 px-7 py-3">
                    <p class="text-sm text-gray-500">Are you sure you want to reserve this book?</p>
                    <div class="mt-4 flex justify-center">
                        <form action="BookReserve" method="post" id="reserveForm">
                            <input type="hidden" id="reserveBookId" name="bookId">
                            <button type="submit" class="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2">
                                Confirm
                            </button>
                        </form>
                        <button onclick="closeReserveModal()" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function openBorrowModal(bookId) {
            document.getElementById('borrowBookId').value = bookId;
            document.getElementById('borrowModal').classList.remove('hidden');
        }

        function closeBorrowModal() {
            document.getElementById('borrowModal').classList.add('hidden');
        }

        function openReserveModal(bookId) {
            document.getElementById('reserveBookId').value = bookId;
            document.getElementById('reserveModal').classList.remove('hidden');
        }

        function closeReserveModal() {
            document.getElementById('reserveModal').classList.add('hidden');
        }
    </script>
</body>
</html>
