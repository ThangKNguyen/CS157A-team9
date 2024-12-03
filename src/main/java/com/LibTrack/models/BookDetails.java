package com.LibTrack.models;

public class BookDetails {
	private int bookId;
	private String title, ISBN, author, category, genre, availability;

	public BookDetails() {
		super();
	}

	public BookDetails(int bookId, String title, String iSBN, String author, String category, String genre,
			String availability) {
		super();
		this.bookId = bookId;
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.category = category;
		this.genre = genre;
		this.availability = availability;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

}
