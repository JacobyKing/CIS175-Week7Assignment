package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Author;

/**
 * Servlet implementation class AuthorNavigationServlet
 */
@WebServlet("/authorNavigationServlet")
public class AuthorNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AuthorNavigationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorHelper dao = new AuthorHelper();
		String action = request.getParameter("doThisToAuthor");
		String path = "/viewAllAuthorsServlet";
		if(action.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Author authorToDelete = dao.searchForAuthorById(tempId);
				dao.deleteAuthor(authorToDelete);
			}
			catch(NumberFormatException e) {
				System.out.println("Forgot to select an author");
			}
		}
		else if(action.equals("edit")) {
			try{
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Author authorToEdit = dao.searchForAuthorById(tempId);
				request.setAttribute("authorToEdit", authorToEdit);
				BookHelper daoForBooks = new BookHelper();
				request.setAttribute("allBooks", daoForBooks.showAllBooks());
				path = "/edit-author.jsp";
			}
			catch(NumberFormatException e) {
				System.out.println("Forgot to select an author");
				path = "/viewAllAuthorsServlet";
			}
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}
