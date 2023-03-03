package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;


/**
 * Servlet implementation class BookNavigationServlet
 */
@WebServlet("/bookNavigationServlet")
public class BookNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookNavigationServlet() {
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
		BookHelper dao = new BookHelper();
		String act = request.getParameter("doThisToBook");
		String path = "/viewAllBookServlet";

		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Book bookToDelete = dao.searchForBookById(tempId);
				dao.deleteBook(bookToDelete);

			} 
			catch (NumberFormatException e) {
				System.out.println("Forgot to select a book");
			}

		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Book bookToEdit = dao.searchForBookById(tempId);
				request.setAttribute("bookToEdit", bookToEdit);
				path = "/ "; //need to add jsp file name
			} 
			catch (NumberFormatException e) {
				System.out.println("Forgot to select a book");
			}

		} else if (act.equals("add")) {
			path = "/index.html";

		}

		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
