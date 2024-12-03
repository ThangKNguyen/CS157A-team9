<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibTrack</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="min-h-screen bg-background flex flex-col">
    <!-- Header -->
    <header class="bg-black text-primary-foreground py-4">
	    <div class="mx-auto flex justify-around items-center">
	        <h1 class="text-3xl font-bold mr-[300px] text-white">LibTrack</h1>
	        <nav>
	            <ul class="flex space-x-2">
	                <li>
	                	<div class="flex font-semibold h-9 items-center space-x-2 bg-white text-black border-2 border-white px-4 py-2 rounded-md hover:bg-black hover:text-white transition duration-200">
	                		 <a href="memberLogin.jsp">
	                       			 Login
	                    		</a>
	                	</div>
	                </li>
	                <li>
	                	<div class="flex font-semibold h-9 items-center space-x-2 bg-black text-white border-2 border-black hover:bg-white hover:text-black hover:border-white px-4 py-2 rounded-md transition duration-200">
	                		<a href="memberRegister.jsp">
		                        Register
		                    </a>
	                	</div>
	                </li>
	            </ul>
	        </nav>
	    </div>
	</header>

    <!-- Hero Section -->
   <section class="bg-secondary py-5 mt-5">
    <div class="container mx-auto text-center">
        <h2 class="text-5xl font-bold mb-4">Welcome to LibTrack</h2>
        <p class="text-2xl mb-2">Discover, Learn, and Grow with Us</p>
    </div>
	</section>

<!-- Join Now Button -->
<div class="py-2 text-center mb-8">
    <div class="flex justify-center">
        <a href="memberRegister.jsp" 
           class="flex justify-center font-semibold items-center space-x-2 bg-black text-white border-2 border-black hover:bg-white hover:text-black px-8 py-3 rounded-lg transition duration-200 text-lg">
            Join Now!
        </a>
    </div>
</div>

<!-- About Us Section -->
<section class="bg-muted py-13 mt-2">
    <div class="container mx-auto flex flex-col md:flex-row items-center">
        <!-- About Us Text -->
        <div class="w-full md:w-1/2 text-left px-6">
            <h3 class="text-3xl font-bold mb-4">About Us</h3>
            <p class="text-lg text-gray-700 leading-relaxed mb-6">
                At LibTrack, we believe in empowering individuals through access to knowledge. 
                With thousands of books, e-books, and resources, our library serves as a hub for 
                discovery and personal growth. Whether you're here to research, read, or relax, 
                LibTrack is your partner in lifelong learning.
            </p>
            <p class="text-lg text-gray-700 leading-relaxed">
                Join us today and explore the countless opportunities that await you. We are here to inspire 
                and support your journey towards knowledge and growth.
            </p>
        </div>

        <!-- Image Placeholder -->
        <div class="w-full md:w-1/2 flex justify-center items-center px-6">
            <div class="w-full h-64 md:h-80 bg-gray-300 rounded-lg flex items-center justify-center">
                <span class="text-gray-500 text-xl">Image Placeholder</span>
            </div>
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
    <footer class="bg-black text-white py-8 mt-auto">
        <div class="container mx-auto grid grid-cols-1 md:grid-cols-2 gap-8 text-center">
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
</body>
</html>
