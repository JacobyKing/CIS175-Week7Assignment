package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Author;


/**
 * @author stephaniesink - sisink
 * CIS175 - Spring 2022
 * Feb 28, 2023
 */
public class AuthorHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebBookList");
	
	public void insertAuthor(Author li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Author> showAllAuthors() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Author> allAuthors = em.createQuery("SELECT i from Author i").getResultList();
		return allAuthors;

	}
	
	public void deleteAuthor(Author toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Author> typedQuery = em.createQuery("select li from Author li where li.authorId = :selectedAuthorId and li.firstName = :selectedFirstName and li.lastName = :selectedLastName", Author.class);

		typedQuery.setParameter("selectedAuthorId", toDelete.getAuthorId());
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedLastName", toDelete.getLastName());

		typedQuery.setMaxResults(1);

		Author result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateAuthor(Author toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public Author searchForAuthorById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Author found = em.find(Author.class, idToEdit);
		em.close();
		return found;
	}
	
	public List<Author> searchForAuthorByFirstName(String first) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Author> typedQuery = em.createQuery("select li from Author li where li.firstName = :selectedFirstName", Author.class);
		
		typedQuery.setParameter("selectedFirstName", first);
		
		List<Author> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<Author> searchForAuthorByLastName(String last) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Author> typedQuery = em.createQuery("select li from Author li where li.LastName = :selectedLastName", Author.class);
		
		typedQuery.setParameter("selectedLastName", last);
		
		List<Author> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
