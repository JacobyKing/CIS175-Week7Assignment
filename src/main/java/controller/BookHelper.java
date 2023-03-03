package controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Book;

/**
 * @author stephaniesink - sisink, jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2022
 * Feb 28, 2023
 */
public class BookHelper {
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CIS175-JPAJoinMiniProject");
	
	public BookHelper() {}
	public void insertBook(Book b) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
		em.close();
	}
	public List<Book> showAllBooks(){
		EntityManager em = emfactory.createEntityManager();
		List<Book> allBooks = em.createQuery("SELECT i FROM Book i").getResultList();
		return allBooks;
	}
	public void deleteBook(Book toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.title = :selectedTitle and b.isbn = :selectedIsbn and b.publishDate = :selectedPublishDate", Book.class);
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedIsbn", toDelete.getIsbn());
		typedQuery.setParameter("selectedPublishDate", toDelete.getPublishDate());
		typedQuery.setMaxResults(1);
		Book result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	public List<Book> searchForBookByTitle(String titleSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.title = :selectedTitle", Book.class);
		typedQuery.setParameter("selectedTitle", titleSelected);
		List<Book> foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
	}
	public List<Book> searchForBookByIsbn(int isbnSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.isbn = :selectedIsbn", Book.class);
		typedQuery.setParameter("selectedIsbn", isbnSelected);
		List<Book> foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
	}
	public List<Book> searchForBookByPublishDate(LocalDate publishDateSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.publishDate = :selectedPublishDate", Book.class);
		typedQuery.setParameter("selectedPublishDate", publishDateSelected);
		List<Book> foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
	}
	public Book searchForBookById(int idSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Book found = em.find(Book.class, idSelected);
		em.close();
		return found;
	}
	public void updateBook(Book toUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toUpdate);
		em.getTransaction().commit();
		em.close();
	}
	public void cleanUp(){
		emfactory.close();
	}
}
