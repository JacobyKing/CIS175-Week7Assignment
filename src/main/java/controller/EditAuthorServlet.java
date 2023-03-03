package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Author;

/**
 * Servlet implementation class EditAuthorServlet
 */
@WebServlet("/editAuthorServlet")
public class EditAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorHelper dao = new AuthorHelper();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Author authorToUpdate = dao.searchForAuthorById(tempId);
		authorToUpdate.setFirstName(firstName);
		authorToUpdate.setLastName(lastName);
		
		dao.updateAuthor(authorToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllAuthorServlet").forward(request, response);
	}

}
