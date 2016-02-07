package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Book repository, Spring Data creates an implementation automatically
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    
	List<Book> findByOrderByTitleAsc();
	
	@Query("select b from Book b where lower(b.title) like lower(concat('%',:searchString,'%')) or lower(b.author) like lower(concat('%',:searchString,'%')) order by title")
    List<Book> findByTitleContainsOrAuthorContains(@Param("searchString") String searchString);
}