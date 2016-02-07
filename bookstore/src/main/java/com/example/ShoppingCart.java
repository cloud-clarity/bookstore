package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Shopping cart
 */
public class ShoppingCart {

	private List<Book> books;
	
    public ShoppingCart(){
        this.books = new ArrayList<Book>();
    }

    public void add(Book book){
        this.books.add(book);
    }
    
    public void remove(int index){
        this.books.remove(index);
    }
    
    public List<Book> getBooks() {
    	return books;
    }
    
    public BigDecimal getTotalPrice(){
        BigDecimal sum = new BigDecimal(0.0);
        for(Book book : books){
            sum = sum.add(book.getPrice());
        }
        return sum;
    }
    
    public int getQuantity () {
    	return this.books.size();
    }
	
}
