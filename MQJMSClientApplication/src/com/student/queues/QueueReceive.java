package com.student.queues;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueReceive {

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
	}
	
	protected void receive_queueMessage() {
		try {
			QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("jms/my_qcf");			
			Queue queue = (Queue) ctx.lookup("jms/my_queue");
			QueueConnection queueConnection = qcf.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = queueSession.createReceiver(queue);
			QueueBrowser queueBrowser = queueSession.createBrowser(queue);
			queueConnection.start();
			
			while (queueBrowser.getEnumeration().hasMoreElements()){
				TextMessage msg = (TextMessage) receiver.receive();
				System.out.println("msg:" + " id=" + msg.getJMSMessageID());
				System.out.println("sent: " + msg.getText());
			}
			
			queueBrowser.close();
			receiver.close();
			queueSession.close();
			queueConnection.close();
		} catch (Exception jmse) {
			jmse.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		QueueReceive send = new QueueReceive();
		send.receive_queueMessage();
	}

}
