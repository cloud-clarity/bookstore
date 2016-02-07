package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service handling books
 */
@Service
public class BookListImpl implements BookList {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	public static int OK = 0;
	public static int NOT_IN_STOCK = 1;
	public static int DOES_NOT_EXIST = 2;

	/**
	 * Lists books. All books or according to search string if present
	 */
	@Override
	public Book[] list(String searchString) {
		if (searchString == null || searchString.length() == 0) {
			List<Book> books = (List<Book>) bookRepository.findByOrderByTitleAsc();
			return books.toArray(new Book[0]);
		}
		else {
			List<Book> books = (List<Book>) bookRepository.findByTitleContainsOrAuthorContains(searchString);
			return books.toArray(new Book[0]);
		}
	}

	/**
	 * Adds a book to books and inventory
	 */
	@Override
	public boolean add(Book book, int amount) {		
		book = bookRepository.save(book);
		inventoryRepository.save(new Inventory(book.getId(), amount));
		return false;
	}

	/**
	 * Buys books if the book exists and if it is in stock
	 */
	@Override
	public int[] buy(Book... books) {
		int[] status = new int[books.length];
		int index = 0;
		Inventory inv;
		for(Book book : books){
            inv = inventoryRepository.findByBookId(book.getId());
            if (inv == null) {
            	status[index] = DOES_NOT_EXIST;
            }
            else if (inv.getQuantity() == 0) {
            	status[index] = NOT_IN_STOCK;
            }
            else {
            	inv.setQuantity(inv.getQuantity() - 1);
            	inventoryRepository.save(inv);
            	status[index] = OK;
            }
            index++;
        }
		return status;
	}

}
