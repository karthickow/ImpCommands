package com.student.bean.stateless.view;

import java.util.List;
import com.student.book.model.Book;

public interface BookPersistentBeanLocal {
	void addBook(Book book);
	List<Book> getBooks();
}
