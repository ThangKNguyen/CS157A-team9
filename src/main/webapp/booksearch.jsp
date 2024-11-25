<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>LibTrack - Search</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <!--  navbar -->
    <nav class ="bg-black">
		<div class = "py-3 max-h-7xl mx-auto w-full bg-gray/80 border-b border-slate-200">
			<form action= "BookConnection" method="GET">
			<div class = "flex justify-between">
				<div class = "mx-3 text-white flex items-center" id = "logo">
					<h1 class="text-4xl font-bold">LibTrack</h1>
					<a class = "mx-2" href = "">Search</a>
				</div>
					  <!-- search bar -->
					<div class="flex justify-center items-center space-x-4 w-full">
						<input type="text" name="bookName" placeholder="Search for a book or author..." class="w-1/3 px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-200">
						<button type="submit"class="px-6 py-2 bg-white text-black rounded-lg border border-gray-300 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200">
							Submit
						</button>
					</div>


				</div>
			</form>
			
		</div>
	</nav>

  
 

    <!-- Basic results display -->
   <div class="p-8">
    <h2 class="text-3xl font-bold mb-6">Search Results</h2>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <c:choose>
            <c:when test="${not empty searchResults}">
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
                        	<!-- change the action to whatever your file name is for the reservations -->
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
            </c:when>
            <c:otherwise>
                <div class="col-span-full">
                    <p class="text-gray-600">${errorMessage}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>