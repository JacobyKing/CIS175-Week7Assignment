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
 * Servlet implementation class EditAuthorServlet
 */
@WebServlet("/editAuthorServlet")
public class EditAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditAuthorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorHelper dao = new AuthorHelper();
		BookHelper daoForBooks = new BookHelper();
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Author authorToUpdate = dao.searchForAuthorById(tempId);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String[] selectedBooks = request.getParameterValues("allBooksToAdd");
		List<Book> selectedBooksInList = new ArrayList<Book>();
		if(selectedBooks != null && selectedBooks.length > 0) {
			for(int i = 0;i < selectedBooks.length; i++) {
				Book bookAuthored = daoForBooks.searchForBookById(Integer.parseInt(selectedBooks[i]));
				selectedBooksInList.add(bookAuthored);
			}
		}
		authorToUpdate.setFirstName(firstName);
		authorToUpdate.setLastName(lastName);
		authorToUpdate.setTitlesAuthored(selectedBooksInList);;
		dao.updateAuthor(authorToUpdate);
		getServletContext().getRequestDispatcher("/viewAllAuthorsServlet").forward(request, response);
	}
}
