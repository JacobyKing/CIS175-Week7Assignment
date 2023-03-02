package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Author;
import model.Book;

/**
 * Servlet implementation class EditBookServlet
 */
@WebServlet("/editBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		
		String title = request.getParameter("title");
		String isbn = request.getParameter("isbn");
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Book bookToUpdate = dao.searchForBookById(tempId);
		bookToUpdate.setTitle(title);
		bookToUpdate.setIsbn(isbn);
		
		dao.updateBook(bookToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllBookServlet").forward(request, response);
	}

}
