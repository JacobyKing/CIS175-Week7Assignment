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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorHelper dao = new AuthorHelper();
		String act = request.getParameter("doThisToAuthor");
		String path = "/viewAllAuthorServlet";

		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Author authorToDelete = dao.searchForAuthorById(tempId);
				dao.deleteAuthor(authorToDelete);

			} 
			catch (NumberFormatException e) {
				System.out.println("Forgot to select an author");
			}

		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Author authorToEdit = dao.searchForAuthorById(tempId);
				request.setAttribute("authorToEdit", authorToEdit);
				path = "/ "; //need to add jsp file name
			} 
			catch (NumberFormatException e) {
				System.out.println("Forgot to select an author");
			}

		} else if (act.equals("add")) {
			path = "/index.html";

		}

		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
