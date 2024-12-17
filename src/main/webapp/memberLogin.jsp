<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>LibTrack Login</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
	<div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
		<h2 class="text-2xl font-bold mb-6 text-center text-gray-800">Login
			to LibTrack</h2>
		<%
		String error = request.getParameter("error");
		if (error != null && !error.isEmpty()) {
		%>
		<div class="mb-4 p-4 rounded-md bg-red-50 border border-red-300">
			<div class="flex items-center">
				<i class="lucide-alert-circle text-red-400 mr-2"></i>
				<p class="text-sm font-medium text-red-800">
					Error: Invalid Credentials
				</p>
			</div>
		</div>
		<%
		}
		%>
		<form action="Login" method="post" class="space-y-4">
			<div>
				<label for="email" class="block text-sm font-medium text-gray-700">Email</label>
				<input type="email" id="email" name="email" required
					class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
			</div>
			<div>
				<label for="password"
					class="block text-sm font-medium text-gray-700">Password</label> <input
					type="password" id="password" name="password" required
					class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-gray-400
                              focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500">
			</div>
			<div>
				<button type="submit"
					class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
					Login</button>
			</div>
		</form>

		<p class="mt-4 text-center text-sm text-gray-600">
			Don't have an account? <a href="memberRegister.jsp"
				class="font-medium text-blue-600 hover:text-blue-500"> Register
				here </a>
		</p>
		<p class="mt-4 text-center text-sm text-gray-600">
			Staff Member? <a href="staffLogin.jsp"
				class="font-medium text-blue-600 hover:text-blue-500"> Log in here </a>
		</p>
	</div>
</body>
</html>