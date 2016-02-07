package com.example;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller for the bookstore
 */
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	BookListImpl bookList;

	/**
	 * Admin page with form to add more books
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
		Book[] books = bookList.list(null);
		model.addAttribute("books", books);
		model.addAttribute("bookCommandObject", new BookCommandObject());
		return "admin";
	}
	
	/**
	 * Adds a new book to the database (book and repository)
	 */
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid  BookCommandObject book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			Book[] books = bookList.list(null);
			model.addAttribute("books", books);
			model.addAttribute("bookCommandObject", book);
			return "admin";
        }
		bookList.add(new Book(book.getTitle(), book.getAuthor(), book.getPrice()), book.getQuantity());
        return "redirect:/admin";
    }
	
}
