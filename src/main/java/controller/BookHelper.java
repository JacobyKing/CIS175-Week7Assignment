package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Book;

/**
 * @author stephaniesink - sisink
 * CIS175 - Spring 2022
 * Feb 28, 2023
 */
public class BookHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebBookList");
	
	public void insertBook(Book li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Book> showAllBooks() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Book> allBooks = em.createQuery("SELECT i from Book i").getResultList();
		return allBooks;

	}
	
	public void deleteBook(Book toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select li from Author li where li.bookId = :selectedBookId and li.title = :selectedTitle and li.isbn = :selectedIsbn", Book.class);

		typedQuery.setParameter("selectedBookId", toDelete.getBookId());
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedIsbn", toDelete.getIsbn());

		typedQuery.setMaxResults(1);

		Book result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateBook(Book toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public Book searchForBookById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Book found = em.find(Book.class, idToEdit);
		em.close();
		return found;
	}
	
	public List<Book> searchForBookByTitle(String title) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select li from Book li where li.title = :selectedTitle", Book.class);
		
		typedQuery.setParameter("selectedTitle", title);
		
		List<Book> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<Book> searchForAuthorByLastName(String isbn) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select li from Book li where li.isbn = :selectedIsbn", Book.class);
		
		typedQuery.setParameter("selectedIsbn", isbn);
		
		List<Book> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
