package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewAllBookServlet
 */
@WebServlet("/viewAllBooksServlet")
public class ViewAllBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewAllBooksServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		request.setAttribute("allBooks", dao.showAllBooks());
		String emptyMessage = "";
		if(dao.showAllBooks().isEmpty()){
			emptyMessage = "There are currently no books in the table.";
		}
		request.setAttribute("emptyMessage", emptyMessage);
		getServletContext().getRequestDispatcher("/view-books.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
