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
 * Servlet implementation class EditBookServlet
 */
@WebServlet("/editBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		String title = request.getParameter("title");
		String isbnString = request.getParameter("isbn");
		String pubDateString = request.getParameter("publishDate");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
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
		Book bookToUpdate = dao.searchForBookById(tempId);
		bookToUpdate.setTitle(title);
		bookToUpdate.setIsbn(isbn);
		bookToUpdate.setPublishDate(publishDate);
		dao.updateBook(bookToUpdate);
		getServletContext().getRequestDispatcher("/viewAllBooksServlet").forward(request, response);
	}
}
