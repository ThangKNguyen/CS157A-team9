<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibTrack Registration</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Register for LibTrack</h2>
        
        <form action="Register" method="post" class="space-y-4">
            <div>
                <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
                <input type="text" id="name" name="name" required 
                       class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
            </div>
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" id="email" name="email" required 
                       class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
            </div>
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required 
                       class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
            </div>
            <div>
                <label for="phone" class="block text-sm font-medium text-gray-700">Phone</label>
                <input type="tel" id="phone" name="phone" required 
                       class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
            </div>
            <div>
                <label for="address" class="block text-sm font-medium text-gray-700">Address</label>
                <textarea id="address" name="address" rows="3" required 
                          class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                                 focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"></textarea>
            </div>
            <div>
                <button type="submit" 
                        class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    Register
                </button>
            </div>
        </form>
        
        <p class="mt-4 text-center text-sm text-gray-600">
            Already have an account? 
            <a href="memberLogin.jsp" class="font-medium text-blue-600 hover:text-blue-500">
                Login here
            </a>
        </p>
    </div>
</body>
</html>