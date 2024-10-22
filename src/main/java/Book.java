
public class Book {
	private int bookID, authorID, categoryID, genreID;
	private String ISBN, title, availability;
	
	public Book() {
		super();
	}
	
	public Book(int bookID, int authorID, int categoryID, int genreID, String ISBN, String title, String availability ) {
		this.bookID = bookID;
		this.authorID = authorID;
		this.categoryID = categoryID;
		this.genreID = genreID;
		this.ISBN = ISBN;
		this.title = title;
		this.availability = availability;
	}
	
	public int getBookID() {
		return bookID;
	}
	
	public void setBookID(int ID) {
		bookID = ID;
	}
	
	public int getAuthorID() {
		return authorID;
	}
	
	public void setAuthorID(int ID) {
		authorID  = ID;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(int ID) {
		categoryID = ID;
	}
	
	
	public int getGenreID() {
		return genreID;
	}
	
	public void setGenreID(int ID) {
		bookID = ID;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String availability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}


}
