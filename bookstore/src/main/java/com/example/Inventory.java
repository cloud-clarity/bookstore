package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Inventory entity in database, handled by Spring Data JPA
 */
@Entity
public class Inventory {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private long bookId;
	private Integer quantity;
	
	protected Inventory() {}

    public Inventory(Long bookId, Integer quantity) {
        this.setBookId(bookId);
        this.quantity = quantity;
    }
	
	
    public Long getId() {
		return id;
	}
    
    public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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
                "Book[id=%d, bookId='%s', quantity='%s']",
                id, bookId, quantity);
    }
}
