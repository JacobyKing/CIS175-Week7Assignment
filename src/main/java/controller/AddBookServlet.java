package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/addBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String isbnString = request.getParameter("isbn");
		String pubDateString = request.getParameter("publishDate");
		int isbn;
		try {
			isbn = Integer.parseInt(isbnString);
		}
		catch(NumberFormatException e){
			isbn = 12345;
		}
		LocalDate publishDate;
		try {
			publishDate = LocalDate.parse(pubDateString);
		}
		catch(NumberFormatException e){
			publishDate = LocalDate.now();
		}
		Book bookToAdd = new Book(title, isbn, publishDate);
		BookHelper dao = new BookHelper();
		dao.insertBook(bookToAdd);
		getServletContext().getRequestDispatcher("/viewAllBooksServlet").forward(request, response);
	}
}
