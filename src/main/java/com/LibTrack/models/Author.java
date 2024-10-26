package com.LibTrack.models;

public class Author {
	private int id;
	private String name;

	public Author(int authorId, String name) {
		super();
		this.id = authorId;
		this.name = name;
	}

	public Author() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}