package com.student.queues;

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
		QueueConnection queueConnection = connFactory.createQueueConnection();
		QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueBrowser queueBrowser = queueSession.createBrowser(queue);
		queueConnection.start();
		
		int numMsgs = 0;

		while (queueBrowser.getEnumeration().hasMoreElements()) {
			//Message message = (Message) enumeration.nextElement();
			numMsgs++;
		}

		System.out.println(queue + " has " + numMsgs + " messages");
		
		queueBrowser.close();
		queueSession.close();
		queueConnection.close();
	}

}
