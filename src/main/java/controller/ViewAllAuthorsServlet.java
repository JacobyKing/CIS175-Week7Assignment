package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAllAuthorServlet
 */
@WebServlet("/viewAllAuthorsServlet")
public class ViewAllAuthorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewAllAuthorsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorHelper dao = new AuthorHelper();
		request.setAttribute("allAuthors", dao.showAllAuthors());
		String emptyMessage = "";
		if(dao.showAllAuthors().isEmpty()){
			emptyMessage = "There are currently no authors in the table.";
		}
		request.setAttribute("emptyMessage", emptyMessage);
		BookHelper daoForBooks = new BookHelper();
		request.setAttribute("allBooks", daoForBooks.showAllBooks());
		getServletContext().getRequestDispatcher("/view-authors.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
