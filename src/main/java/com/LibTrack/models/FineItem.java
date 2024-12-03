package com.LibTrack.models;

import java.math.BigDecimal;

public class FineItem {
	private String bookTitle;
	private BigDecimal fineAmount;
	private String paidStatus;
	private String issuedDate;
	private String finesDueDate;

	public FineItem(String bookTitle, BigDecimal fineAmount, String paidStatus, String issuedDate,
			String finesDueDate) {
		this.bookTitle = bookTitle;
		this.fineAmount = fineAmount;
		this.paidStatus = paidStatus;
		this.issuedDate = issuedDate;
		this.finesDueDate = finesDueDate;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public BigDecimal getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public String getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getFinesDueDate() {
		return finesDueDate;
	}

	public void setFinesDueDate(String finesDueDate) {
		this.finesDueDate = finesDueDate;
	}

}