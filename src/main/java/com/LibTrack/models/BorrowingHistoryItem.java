package com.LibTrack.models;

public class BorrowingHistoryItem {
	private int borrowId;
	private String title;
	private String borrowDate;
	private String dueDate;
	private String returnDate;
	private String status;

	public BorrowingHistoryItem(int borrowId, String title, String borrowDate, String dueDate, String returnDate,
			String status) {
		super();
		this.borrowId = borrowId;
		this.title = title;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BorrowingHistoryItem [borrowId=" + borrowId + ", title=" + title + ", borrowDate=" + borrowDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + "]";
	}
}
