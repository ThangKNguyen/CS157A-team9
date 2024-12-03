<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Book Reviews - LibTrack</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
	<div class="container mx-auto px-4 py-8">
		<header class="mb-8">
			<h1 class="text-3xl font-bold text-gray-800">Book Reviews</h1>
		</header>

		<main>
			<div class="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
				<c:forEach var="review" items="${reviews}">
					<div class="bg-white shadow-md rounded-lg overflow-hidden">
						<div class="p-6">
							<h2 class="text-xl font-semibold text-gray-800 mb-2">${review.bookTitle}</h2>
							<div class="flex items-center mb-3">
								<span class="text-yellow-400 mr-1">★</span> <span
									class="text-gray-700">${review.rating}</span>
							</div>
							<p class="text-sm text-gray-600 mb-4">Reviewed by
								${review.member} on ${review.reviewDate}</p>
							<p class="text-gray-700 mb-4 line-clamp-3">${review.reviewText}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</main>

	</div>

</body>
</html>