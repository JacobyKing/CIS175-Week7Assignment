package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Author;
import model.Book;

/**
 * Servlet implementation class AddAuthorServlet
 */
@WebServlet("/addAuthorServlet")
public class AddAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddAuthorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper lih = new BookHelper();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String[] selectedBooks = request.getParameterValues("allBooksToAdd");
		List<Book> selectedBooksInList = new ArrayList<Book>();
		if(selectedBooks != null && selectedBooks.length > 0) {
			for(int i = 0;i < selectedBooks.length; i++) {
				Book bookAuthored = lih.searchForBookById(Integer.parseInt(selectedBooks[i]));
				selectedBooksInList.add(bookAuthored);
			}
		}
		Author authorToAdd = new Author(firstName, lastName);
		authorToAdd.setTitlesAuthored(selectedBooksInList);
		AuthorHelper dao = new AuthorHelper();
		dao.insertAuthor(authorToAdd);
		getServletContext().getRequestDispatcher("/viewAllAuthorsServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
