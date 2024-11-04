<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<header class="bg-black text-primary-foreground py-4">
		<div class="mx-auto flex justify-around items-center">
			<h1 class="text-3xl font-bold mr-[300px] text-white">LibTrack</h1>

			<nav>
				<%
				if (session == null || session.getAttribute("loggedInUser") == null) {
				%>
				<!-- Render Login and Register buttons if user is not logged in -->
				<ul class="flex space-x-2">
					<li><a href="memberLogin.jsp"
						class="text-white hover:bg-primary-foreground hover:text-primary px-4 py-2">
							Login </a></li>
					<li><a href="memberRegister.jsp"
						class="text-white bg-primary-foreground text-primary hover:bg-primary hover:text-primary-foreground px-4 py-2">
							Register </a></li>
				</ul>
				<%
				} else {
				%>
				<!-- Render My Account button if user is logged in -->
				<ul class="flex space-x-2">
					<li><a href="profile.jsp"
						class="text-white bg-primary-foreground text-primary hover:bg-primary hover:text-primary-foreground px-4 py-2">
							My Account </a></li>
				</ul>
				<%
				}
				%>
			</nav>

		</div>
	</header>

	<!-- Hero Section -->
	<section class="bg-secondary py-20">
		<div class="mx-auto text-center">
			<h2 class="text-4xl font-bold mb-4">Welcome to LibTrack</h2>
			<p class="text-xl mb-8">Discover, Learn, and Grow with Us</p>
			<div class="max-w-md mx-auto flex">
				<input type="text" placeholder="Search books, authors, or topics"
					class="flex-grow p-2 border rounded" />
				<button type="submit"
					class="ml-2 bg-black flex items-center px-4 py-2 rounded text-white">
					<i class="fa-brands fa-searchengin mr-3"></i> Search
				</button>

			</div>
		</div>
	</section>

	<!-- Library Services -->
	<section class="bg-muted py-16">
		<div class="mx-auto">
			<h3 class="text-2xl font-bold mb-8 text-center">Our Services</h3>
			<div class="grid grid-cols-1 md:grid-cols-4 gap-8 mx-8">

				<div class="card bg-white p-6 rounded shadow">
					<div class="card-header flex">
						<i class="fa-solid fa-book text-5xl"></i>
					</div>
					<div class="card-content mt-2">
						<h4 class="text-2xl font-semibold">Vast Collection</h4>
						<p class="mt-3">Access to thousands of books and e-books</p>
					</div>
				</div>

				<div class="card bg-white p-6 rounded shadow">
					<div class="card-header flex">
						<i class="fa-solid fa-wifi text-5xl"></i>
					</div>
					<div class="card-content mt-2">
						<h4 class="text-2xl font-semibold">Free Wi-Fi</h4>
						<p class="mt-3">Stay connected with our high-speed internet</p>
					</div>
				</div>

				<div class="card bg-white p-6 rounded shadow">
					<div class="card-header flex">
						<i class="fa-solid fa-mug-saucer text-5xl"></i>
					</div>
					<div class="card-content mt-2">
						<h4 class="text-2xl font-semibold">Café</h4>
						<p class="mt-3">Enjoy a cup of coffee while you read</p>
					</div>
				</div>

				<div class="card bg-white p-6 rounded shadow">
					<div class="card-header flex">
						<i class="fa-solid fa-clock text-5xl"></i>
					</div>
					<div class="card-content mt-2">
						<h4 class="text-2xl font-semibold">Extended Hours</h4>
						<p class="mt-3">Open late to accommodate your schedule</p>
					</div>
				</div>

			</div>

		</div>
	</section>

	<!-- Footer -->
	<footer class="bg-black text-white py-8">
		<div
			class="container mx-auto grid grid-cols-1 md:grid-cols-2 gap-8 text-center">
			<div>
				<h4 class="font-bold mb-4">LibTrack Library</h4>
				<p>1 Washington Sq, San Jose, CA 95192</p>
				<p>Phone: (123) 456-7890</p>
				<p>Email: info@libtrack.com</p>
			</div>
			<div>
				<h4 class="font-bold mb-4">Hours</h4>
				<p>Monday - Friday: 9am - 9pm</p>
				<p>Saturday: 10am - 6pm</p>
				<p>Sunday: 12pm - 5pm</p>
			</div>

		</div>
	</footer>

	<script src="path/to/icon-library.js"></script>
	<!-- Assuming a JS file for icons -->
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

