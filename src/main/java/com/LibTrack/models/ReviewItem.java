package com.LibTrack.models;

public class ReviewItem {
	private int reviewId;
	private String bookTitle, member, reviewDate, reviewText;

	public ReviewItem() {
		super();
	}

	public ReviewItem(int reviewId, String bookTitle, String member, String reviewDate, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.bookTitle = bookTitle;
		this.member = member;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
