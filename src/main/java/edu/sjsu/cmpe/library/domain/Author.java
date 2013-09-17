package edu.sjsu.cmpe.library.domain;

import java.util.List;

public class Author {
	
	private static int authorKey;
	private int id;
	private String name;
	private List<Book> booksByAuthors;
	
	public Author() {
		this.id = ++authorKey;
	}
	
	
	public int getAuthorId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooksByAuthors() {
		return booksByAuthors;
	}
	public void setBooksByAuthors(List<Book> booksByAuthors) {
		this.booksByAuthors = booksByAuthors;
	}
}
