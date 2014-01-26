package com.student.queues;

import java.util.Enumeration;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

public class Browse {
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("jms/my_qcf");
		Queue queue = (Queue) ctx.lookup("jms/my_queue");
		QueueConnection queueConn = connFactory.createQueueConnection();
		QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueBrowser queueBrowser = queueSession.createBrowser(queue);
		queueConn.start();
		
		Enumeration e = queueBrowser.getEnumeration();
		int numMsgs = 0;

		while (e.hasMoreElements()) {
			Message message = (Message) e.nextElement();
			numMsgs++;
		}

		System.out.println(queue + " has " + numMsgs + " messages");

		queueConn.close();
	}

}
