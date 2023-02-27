/**
 * @author jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2023
 * Feb 26, 2023
 */
package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue
	@Column(name = "BOOK_ID")
	private int bookId;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "ISBN")
	private int isbn;
	@Column(name = "PUBLISH_DATE")
	private LocalDate publishDate;
	
	public Book(String title, int isbn, LocalDate publishDate) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.publishDate = publishDate;
	}
	public Book(String title, int isbn) {
		super();
		this.title = title;
		this.isbn = isbn;
	}
	public Book(String title) {
		super();
		this.title = title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", isbn=" + isbn + ", publishDate=" + publishDate + "]";
	}
}
