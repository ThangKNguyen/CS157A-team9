<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<li><a href="BooksByGenre?genreId=${genre.id}"
							class="block py-2 px-4 text-gray-700 hover:bg-gray-200">
								${genre.name} </a></li>
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
				<h2 class="text-3xl font-bold text-gray-800 mb-6">No genre
					selected</h2>
				<p class="text-gray-600 mb-4">Please select a genre to view the
					books.</p>
				<%
				} else {
				%>
				<h2 class="text-3xl font-bold text-gray-800 mb-6">${genreName}
					Books</h2>
				<c:choose>
					<c:when test="${empty books}">
						<div class="w-full text-center py-12">
							<p class="text-2xl font-semibold text-gray-700">No books in
								this genre.</p>
							<p class="mt-2 text-gray-500">Please check back later or try
								another genre.</p>
						</div>
					</c:when>
					<c:otherwise>
						<div
							class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
							<c:forEach var="book" items="${books}">
								<div class="bg-white rounded-lg shadow-md overflow-hidden">
									<div class="p-6">
										<h3 class="text-xl font-semibold text-gray-800 mb-2">${book.title}</h3>
										<p class="text-gray-600 mb-2">Author: ${book.author}</p>
										<p class="text-gray-600 mb-2">Genre: ${book.genre}</p>
										<p class="text-gray-300 mb-4">ISBN: ${book.ISBN}</p>
										<div class="flex justify-between items-center">
											<span
												class="px-3 py-1 rounded-full text-sm font-semibold ${book.availability eq 'Available' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}">
												${book.availability} </span>
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
</body>
</html>