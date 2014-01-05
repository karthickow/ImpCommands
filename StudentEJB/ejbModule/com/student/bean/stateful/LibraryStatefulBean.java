package com.student.bean.stateful;

import java.util.ArrayList;
import java.util.List;

import com.student.bean.stateful.view.LibraryStatefulBeanLocal;
import com.student.bean.stateful.view.LibraryStatefulBeanRemote;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class LibraryStatefulBean
 */
@Stateful
@Local(LibraryStatefulBeanLocal.class)
@Remote(LibraryStatefulBeanRemote.class)
public class LibraryStatefulBean implements LibraryStatefulBeanRemote, LibraryStatefulBeanLocal {
	
	List<String> bookShelf;
    public LibraryStatefulBean() {
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
