package com.example;

import org.springframework.data.repository.CrudRepository;

/**
 * Inventory repository, Spring Data creates an implementation automatically
 */
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
	
	Inventory findByBookId(Long bookId);
}