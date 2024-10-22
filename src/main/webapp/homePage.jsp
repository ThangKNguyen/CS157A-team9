<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="min-h-screen bg-background">

    <header class="bg-black text-primary-foreground py-4">
	    <div class="mx-auto flex justify-around items-center">
	        <h1 class="text-3xl font-bold mr-[300px] text-white">LibTrack</h1>
	        <nav>
	            <ul class="flex space-x-2">
	                <li>
	                    <a href="memberLogin.jsp" class="text-white hover:bg-primary-foreground hover:text-primary px-4 py-2">
	                        Login
	                    </a>
	                </li>
	                <li>
	                    <a href="memberRegister.jsp" class="text-white bg-primary-foreground text-primary hover:bg-primary hover:text-primary-foreground px-4 py-2">
	                        Register
	                    </a>
	                </li>
	            </ul>
	        </nav>
	    </div>
	</header>

    <!-- Hero Section -->
    <section class="bg-secondary py-20">
        <div class="mx-auto text-center">
            <h2 class="text-4xl font-bold mb-4">Welcome to LibTrack</h2>
            <p class="text-xl mb-8">Discover, Learn, and Grow with Us</p>
            <div class="max-w-md mx-auto flex">
                <input type="text" placeholder="Search books, authors, or topics" class="flex-grow p-2 border rounded" />
                <button type="submit" class="ml-2 bg-black flex items-center px-4 py-2 rounded text-white">
                <i class="fa-brands fa-searchengin mr-3"></i>
                    Search
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

    <script src="path/to/icon-library.js"></script> <!-- Assuming a JS file for icons -->
</body>
</html>