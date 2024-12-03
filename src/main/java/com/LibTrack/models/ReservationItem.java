package com.LibTrack.models;

public class ReservationItem {
	private String bookTitle;
	private String reservationDate;
	private String status;

	// Constructor
	public ReservationItem(String bookTitle, String reservationDate, String status) {
		this.bookTitle = bookTitle;
		this.reservationDate = reservationDate;
		this.status = status;
	}

	// Getters and Setters
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getreservationDate() {
		return reservationDate;
	}

	public void setreservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReservationItem{" + "bookTitle='" + bookTitle + '\'' + ", reservationDate=" + reservationDate
				+ ", status='" + status + '\'' + '}';
	}
}
