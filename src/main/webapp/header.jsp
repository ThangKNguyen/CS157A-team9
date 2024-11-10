<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibTrack</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<%
	if (session == null || session.getAttribute("loggedInUser") == null) {
		response.sendRedirect("memberLogin.jsp");
	}
%>
<body>
    <header class="sticky top-0 z-50 w-full border-b bg-white/95 backdrop-blur supports-[backdrop-filter]:bg-white/60">
        <div class="container mx-auto px-4 lg:px-8 flex h-14 items-center">
            <div class="flex items-center">
                <a href="Home" class="mr-6 flex items-center space-x-2">
                    <span class="font-bold">LibTrack</span>
                </a>
                <nav class="flex items-center space-x-5 text-sm font-medium">
                    <a href="Categories" class="hover:text-gray-700">Categories</a>
                    <a href="Genres" class="hover:text-gray-700">Genres</a>
                    <a href="Authors" class="hover:text-gray-700">Authors</a>
                </nav>
            </div>
            <div class="flex flex-1 items-center justify-end space-x-4">
                <form action="/search" method="get" class="w-64">
                    <div class="relative">
                        <input type="search" name="q" placeholder="Search books..." 
                               class="w-full rounded-md border border-gray-300 bg-transparent py-2 pl-3 pr-3 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-offset-2" />
                    </div>
                </form>
				<div class="flex font-semibold h-9 items-center space-x-2 bg-gray-900 text-white px-4 py-2 rounded-md hover:bg-gray-500 transition duration-200">
    				<a href="profile.jsp">My Account</a>
				</div>
				
				<div class="flex font-semibold h-9 items-center space-x-2 bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700 transition duration-200">
    				<a href="landingPage.jsp">Log Out</a>
				</div>
				
			</div>
		</div>
    </header>
    <!-- Rest of your page content goes here -->
</body>
</html>