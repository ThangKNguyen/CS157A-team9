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
<title>Borrowing History - LibTrack</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
	<div class="container mx-auto px-4 py-8">
		<header class="mb-8">
			<h1 class="text-3xl font-bold text-gray-800">
				Welcome,
				<c:out value="${loggedInUser.name}!" />
			</h1>
		</header>

		<main>
    <h2 class="text-2xl font-semibold text-gray-700 mb-4">Your Borrowing History</h2>
    <div class="bg-white shadow-md rounded-lg overflow-hidden">
        <div class="overflow-x-auto">
            <table class="w-full">
                <thead class="bg-gray-50">
                    <tr>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Borrow ID</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Book Title</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Borrow Date</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Due Date</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Return Date</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Review</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Return</th>
                    </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                    <c:forEach var="item" items="${borrowingHistory}">
                        <tr>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <c:out value="${item.borrowId}" />
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                <c:out value="${item.title}" />
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <c:out value="${item.borrowDate}" />
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <c:out value="${item.dueDate}" />
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <c:choose>
                                    <c:when test="${item.returnDate == null}">
                                        <span class="text-yellow-600">Not returned yet</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${item.returnDate}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <span
                                    class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                                        ${item.status == 'Returned' ? 'bg-green-100 text-green-800' : 
                                          item.status == 'Overdue' ? 'bg-red-100 text-red-800' : 
                                          'bg-yellow-100 text-yellow-800'}">
                                    <c:out value="${item.status}" />
                                </span>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <button onclick="openReviewModal('${item.borrowId}')"
                                    class="border border-blue-500 text-blue-500 hover:bg-blue-100 font-semibold py-1 px-3 rounded focus:outline-none focus:shadow-outline transition duration-150 ease-in-out">
                                    Add Review
                                </button>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                <c:if test="${item.status == 'Borrowed'}">
                                    <button onclick="openReturnModal('${item.borrowId}', '${item.bookId}')"
                                        class="border border-green-500 text-green-500 hover:bg-green-100 font-semibold py-1 px-3 rounded focus:outline-none focus:shadow-outline transition duration-150 ease-in-out">
                                        Return
                                    </button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
		
	</div>

	<!-- Review Modal -->
	<div id="reviewModal"
		class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full hidden"
		role="dialog" aria-labelledby="modalTitle" aria-modal="true">
		<div
			class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
			<div class="mt-3 text-center">
				<h3 class="text-lg leading-6 font-medium text-gray-900"
					id="modalTitle">Review</h3>
				<div class="mt-2 px-7 py-3">
					<form action="AddReview" method="post" id="reviewForm">
						<input type="hidden" id="borrowId" name="borrowId">
						<div class="mb-4">
							<label for="rating"
								class="block text-gray-700 text-sm font-bold mb-2">Rating
								(1-5):</label> <input type="number" id="rating" name="rating" min="1"
								max="5"
								class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
								required>
						</div>
						<div class="mb-4">
							<label for="review"
								class="block text-gray-700 text-sm font-bold mb-2">Review:</label>
							<textarea id="review" name="review" rows="3"
								class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"></textarea>
						</div>
						<div class="flex items-center justify-between">
							<button type="submit"
								class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
								Submit Review</button>
							<button type="button" onclick="closeReviewModal()"
								class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
								Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Return Confirmation Modal -->
	<div id="returnModal"
		class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full hidden"
		role="dialog" aria-labelledby="returnModalTitle" aria-modal="true">
		<div
			class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
			<div class="mt-3 text-center">
				<h3 class="text-lg leading-6 font-medium text-gray-900"
					id="returnModalTitle">Confirm Return</h3>
				<div class="mt-2 px-7 py-3">
					<p class="text-sm text-gray-500">Are you sure you want to
						return this book?</p>
					<div class="mt-4 flex justify-center">
						<form action="BookReturn" method="post" id="returnForm">
							<input type="hidden" id="returnBorrowId" name="borrowId">
							<input type="hidden" id="returnBookId" name="bookId">
							<button type="submit"
								class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2">
								Confirm</button>
						</form>
						<button onclick="closeReturnModal()"
							class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
							Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function openReviewModal(borrowId) {
			document.getElementById('borrowId').value = borrowId;
			document.getElementById('reviewModal').classList.remove('hidden');
		}

		function closeReviewModal() {
			document.getElementById('reviewModal').classList.add('hidden');
			resetForm('reviewForm');
		}

		function openReturnModal(borrowId, bookId) {
			document.getElementById('returnBorrowId').value = borrowId;
			document.getElementById('returnBookId').value = bookId;
			document.getElementById('returnModal').classList.remove('hidden');
		}

		function closeReturnModal() {
			document.getElementById('returnModal').classList.add('hidden');
		}

		function resetForm(formId) {
			document.getElementById(formId).reset();
		}
	</script>
</body>
</html>