<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Search</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<nav class ="bg-gray-300">
		<div class = "py-3 max-h-7xl mx-auto w-full bg-gray/80 border-b border-slate-200">
			<form action= "BookConnection" method="GET">
			<div class = "flex ">
				<div id = "logo">
				<h1 class="text-black-500 text-4xl font-bold">LibTrack</h1>
				</div>
				
				<div class = "flex space-x-4 items-center justify-center" id = "middleSection">
					<div id = "searchBar">
						<input type="text" id = "bookName" name = "bookName" placeholder="Search for a book.." required>
					</div>
					
					<br>
					
					<div class = "flex space-x-4" id = "dropdown">
						<div>
							<label for= "category">Category:</label>
							<select id = "category" name= "category">
								<option value = "fiction">Fiction</option>
								<option value = "non-Fiction">Non-Fiction</option>
								<option value = "scienceFiction">Science Fiction</option>
								<option value = "fantasy">Fantasy</option>
								<option value = "mystery">Mystery</option>
								<option value = "biography">Biography</option>
								<option value = "romance">Romance</option>
								<option value = "historical">Historical</option>
								<option value = "thriller">Thriller</option>
								<option value = "selfHelp">Self-Help</option>
							</select>
						</div>
						<div>
							<label for= "genre">Genre:</label>
							<select id = "genre" name= "genre">
								<option value = "dystopian">Dystopian</option>
								<option value = "adventure">Adventure</option>
								<option value = "classic">Classic</option>
								<option value = "horror">Horror</option>
								<option value = "youngAdult">Young Adult</option>
								<option value = "literaryFiction">Literary Fiction</option>
								<option value = "contemporary">Contemporary</option>
								<option value = "scifiFantasy">Sci-Fi Fantasy</option>
								<option value = "detective">Detective</option>
								<option value = "memoir">Memoir</option>
							</select>
						</div>
					</div>
						
					<br>
					
					<div>
						<input type = "submit">
					</div>
				</div>
				
					
			</div>
			</form>
			
		</div>
	</nav>
	<div class = "py-32 text-center">
		<p class = "font-extrabold text-4xl">${name}</p>
	</div>
</body>
</html>