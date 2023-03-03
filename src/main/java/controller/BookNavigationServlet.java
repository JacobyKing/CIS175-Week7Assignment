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
    
    public BookNavigationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookHelper dao = new BookHelper();
		String action = request.getParameter("doThisToBook");
		String path = "/viewAllBooksServlet";
		if(action.equals("delete")){
			try{
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Book bookToDelete = dao.searchForBookById(tempId);
				dao.deleteBook(bookToDelete);
			}
			catch(NumberFormatException e){
				System.out.println("Forgot to select a book");
			}
		}
		else if(action.equals("edit")){
			try{
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Book bookToEdit = dao.searchForBookById(tempId);
				request.setAttribute("bookToEdit", bookToEdit);
				path = "/edit-book.jsp";
			}
			catch(NumberFormatException e) {
				System.out.println("Forgot to select a book");
			}
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}
