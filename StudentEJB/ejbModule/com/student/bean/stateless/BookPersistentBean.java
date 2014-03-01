package com.student.bean.stateless;

import java.util.List;

import com.student.bean.stateless.view.BookPersistentBeanLocal;
import com.student.bean.stateless.view.BookPersistentBeanRemote;
import com.student.book.model.Book;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(BookPersistentBeanLocal.class)
@Remote(BookPersistentBeanRemote.class)
public class BookPersistentBean implements BookPersistentBeanRemote, BookPersistentBeanLocal {
	
	@PersistenceContext
	private EntityManager em;
    public BookPersistentBean() {
    }

	@Override
	public void addBook(Book book) {
		em.persist(book);
	}

	@Override
	public List<Book> getBooks() {
		return em.createQuery("SELECT c FROM Book c").getResultList();
	}
}
