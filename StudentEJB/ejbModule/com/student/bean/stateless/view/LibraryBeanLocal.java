package com.student.bean.stateless.view;

import java.util.List;

public interface LibraryBeanLocal {
	void addBook(String bookName);
	List<String> getBooks();
}
