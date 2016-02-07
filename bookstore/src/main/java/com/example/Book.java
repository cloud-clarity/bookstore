package com.example;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Book entity in database, handled by Spring Data JPA
 */
@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String title;
	private String author;
	private BigDecimal price;
	
	protected Book() {}

    public Book(String title, String author, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
	
	
    public Long getId() {
		return id;
	}
    
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', author='%s', price='%s']",
                id, title, author, price);
    }
	
	public boolean equals(Book book) {
		return this.id == book.getId();
	}
}
