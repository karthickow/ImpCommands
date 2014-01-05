package com.student.bean.stateless;

import java.util.ArrayList;
import java.util.List;

import com.student.bean.stateless.view.LibraryBeanLocal;
import com.student.bean.stateless.view.LibraryBeanRemote;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class LibraryBean
 */
@Stateless
@Local(LibraryBeanLocal.class)
@Remote(LibraryBeanRemote.class)
public class LibraryBean implements LibraryBeanRemote, LibraryBeanLocal {

	List<String> bookShelf;
    public LibraryBean() {
    	bookShelf = new ArrayList<String>();
    }

	@Override
	public void addBook(String bookName) {
		bookShelf.add(bookName);
	}

	@Override
	public List<String> getBooks() {
		return bookShelf;
	}
}
