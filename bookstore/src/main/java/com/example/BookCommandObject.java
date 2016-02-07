package com.example;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Book Command Object - used in form to add new book and inventory information
 */
public class BookCommandObject {
	
	@NotNull
	private String title;
	@NotNull
	private String author;
	@Min(1)
	private BigDecimal price;
	@Min(1)
	private Integer quantity;
	
	protected BookCommandObject() {}

    public BookCommandObject(String title, String author, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.price = price;
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
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Book[title='%s', author='%s', price='%s', quantity='%s']",
                title, author, price, quantity);
    }
}
