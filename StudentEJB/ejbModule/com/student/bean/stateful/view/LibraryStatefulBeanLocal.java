package com.student.bean.stateful.view;

import java.util.List;

public interface LibraryStatefulBeanLocal {
	void addBook(String bookName);
	List<String> getBooks();
}
