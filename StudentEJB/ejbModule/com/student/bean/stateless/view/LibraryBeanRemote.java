package com.student.bean.stateless.view;

import java.util.List;

public interface LibraryBeanRemote {
	void addBook(String bookName);
	List<String> getBooks();
}
