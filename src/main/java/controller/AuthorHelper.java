package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Author;

/**
 * @author stephaniesink - sisink, jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2022
 * Feb 28, 2023
 */
public class AuthorHelper {
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CIS175-JPAJoinMiniProject");
	
	public void insertAuthor(Author a) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
	}
	public List<Author> showAllAuthors(){
		EntityManager em = emfactory.createEntityManager();
		List<Author> allAuthors = em.createQuery("SELECT a FROM Author a").getResultList();
		return allAuthors;
	}
	public void deleteAuthor(Author toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Author> typedQuery = em.createQuery("select a from Author a where a.authorId = :selectedAuthorId", Author.class);
		typedQuery.setParameter("selectedAuthorId", toDelete.getAuthorId());
		typedQuery.setMaxResults(1);
		Author result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	public Author searchForAuthorById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Author found = em.find(Author.class, tempId);
		em.close();
		return found;
	}
	public void updateAuthor(Author toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
