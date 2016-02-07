package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot main application
 */
@ComponentScan
@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	/**
	 * Spring Boot starts the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	/**
	 * Automatically run by Spring Boot, used to download the list with books and add books and inventory to database.
	 * As everything else in this test, this is not meant to be production ready!
	 */
	@Bean
	public CommandLineRunner demo(BookListImpl bookList, BookRepository bookRepository, InventoryRepository inventoryRepository) {
		
		return (args) -> {
			// Read the file (requires internet access)
			URL url  = new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt" );
			URLConnection uc = url.openConnection();
			InputStreamReader inStream = new InputStreamReader(uc.getInputStream());
            BufferedReader br= new BufferedReader(inStream);
            
            // Handles the format to be able to handle the price
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            String pattern = "#,##0.0#";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);
			
            // Add books and inventory in database
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                    log.info(inputLine);
                    String[] p = inputLine.split(";");
                    bookList.add(new Book(p[0], p[1], (BigDecimal)decimalFormat.parse(p[2])), Integer.parseInt(p[3]));
            }
		};
	}
}
