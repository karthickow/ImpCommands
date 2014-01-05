package com.student.ejb.tester;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.student.bean.stateless.view.BookPersistentBeanRemote;
import com.student.book.model.Book;

public class EJBTesterEntity {

	BufferedReader brConsoleReader = null;
	Properties props;

	InitialContext ctx;
	{
		props = new Properties();
		try {
			props.load(new FileInputStream("jndi.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			ctx = new InitialContext(props);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {
		EJBTesterEntity entity = new EJBTesterEntity();
		entity.testEntityEjb();
	}

	private void showGUI() {
		System.out.println("**********************");
		System.out.println("Welcome to Book Store");
		System.out.println("**********************");
		System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	}

	private void testEntityEjb(){
		try {
			int choice = 1;
			BookPersistentBeanRemote libraryBean = (BookPersistentBeanRemote) PortableRemoteObject.narrow(ctx.lookup("ejb/BookPersistentBean"), BookPersistentBeanRemote.class);
			
			while (choice != 2) {
				String bookName;
				showGUI();
				String strChoice = brConsoleReader.readLine();
				choice = Integer.parseInt(strChoice);

				if (choice == 1) {
					System.out.print("Enter book name: ");
					bookName = brConsoleReader.readLine();
					Book book = new Book();
					book.setBookName(bookName);
					libraryBean.addBook(book);
				} else if (choice == 2) {
					break;
				}
			}

			List<Book> booksList = libraryBean.getBooks();
			System.out.println("Book(s) entered so far: " + booksList.size());

			int i = 0;
	        for (Book book:booksList) {
	            System.out.println((i+1)+". " + book.getBookName());
	            i++;
	        }     
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (brConsoleReader != null) {
					brConsoleReader.close();
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
