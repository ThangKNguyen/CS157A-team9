<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Search</title>
</head>
<body>
	<form action= "BookConnection" method="GET">
		<div class = "searchBar">
			<input type="text" id = "bookName" name = "bookName" placeholder="Search for a book.." required>
		</div>
		
		<br>
		
		<div class = "dropdown">
			<div>
				<label for= "category">category</label>
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
		
			<br>
			
			<div>
				<label for= "genre">genre</label>
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
		
		<p>${name}</p>
			
	
	</form>
</body>
</html>