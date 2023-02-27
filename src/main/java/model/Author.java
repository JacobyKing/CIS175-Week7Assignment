/**
 * @author jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2023
 * Feb 26, 2023
 */
package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue
	@Column(name = "AUTHOR_ID")
	private int authorId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@Column(name = "TITLES_AUTHORED")
	private List<Book> titlesAuthored;
	
	public Author(String firstName, String lastName, List<Book> titlesAuthored) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.titlesAuthored = titlesAuthored;
	}
	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Author() {
		super();
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Book> getTitlesAuthored() {
		return titlesAuthored;
	}
	public void setTitlesAuthored(List<Book> titlesAuthored) {
		this.titlesAuthored = titlesAuthored;
	}
	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", titlesAuthored=" + titlesAuthored + "]";
	}
}
