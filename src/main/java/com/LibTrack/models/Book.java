package com.LibTrack.models;

public class Book {
	private int bookId, authorId, categoryId, genreId;
	private String title, ISBN, availability;

	public Book(int bookId, String title, int authorId, int categoryId, int genreId, String iSBN, String availability) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.genreId = genreId;
		ISBN = iSBN;
		this.availability = availability;
	}

	public Book() {
		super();
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
