package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for the bookstore
 */
@Controller
public class BookstoreController {
	@Autowired
	BookListImpl bookList;
	
	@Autowired
	BookRepository repository;

	/**
	 * Home page with books and shopping cart
	 */
	@RequestMapping("/")
    public String home(Model model, HttpSession session) {
		Book[] books = bookList.list((String)session.getAttribute("searchString"));
		model.addAttribute("books", books);
		return "home";
	}
	
	/**
	 * Search - redirects to home page
	 */
	@RequestMapping("/search")
    public String search(@RequestParam("searchString") String searchString, HttpSession session, Model model) {
		session.setAttribute("searchString", searchString);
		return "redirect:/";
	}
	
	/**
	 * Add to shopping cart - redirects to home page
	 */
	@RequestMapping("/add")
    public String add(@RequestParam("id") Long id, HttpServletRequest req, HttpSession session, Model model) {
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
		}
		Book book = repository.findOne(id);
		cart.add(book);
		session.setAttribute("cart", cart);
		return "redirect:/";
	}
	
	/**
	 * Remove from shopping cart - redirects to home page
	 */
	@RequestMapping("/remove")
    public String remove(@RequestParam("index") int index, HttpServletRequest req, HttpSession session, Model model) {
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/";
	}
	
	/**
	 * Buy everything in shopping cart
	 */
	@RequestMapping("/buy")
    public String buy(HttpSession session, Model model) {
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		if (cart != null) {
			int[] status = bookList.buy(cart.getBooks().toArray(new Book[0]));
			model.addAttribute("confirmation", cart);
			model.addAttribute("status", status);
		}
		session.removeAttribute("cart");
		Book[] books = bookList.list((String)session.getAttribute("searchString"));
		model.addAttribute("books", books);
		return "home";
	}
	
}
