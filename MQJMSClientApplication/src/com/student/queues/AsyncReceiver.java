package com.student.queues;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class AsyncReceiver implements MessageListener, ExceptionListener {

	public static void main(String[] args) {
		try{
	       InitialContext ctx = new InitialContext();
	       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("jms/my_qcf");
	       Queue queue = (Queue) ctx.lookup("jms/my_queue");	       
	       QueueConnection queueConn = connFactory.createQueueConnection();
	       QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	       QueueReceiver queueReceiver = queueSession.createReceiver(queue);
	       AsyncReceiver asyncReceiver = new AsyncReceiver();
	       queueReceiver.setMessageListener(asyncReceiver);
	       queueConn.setExceptionListener(asyncReceiver);
	       queueConn.start();

	       System.out.println("waiting for messages");
	       for (int i = 0; i < 10; i++) {
	          Thread.sleep(1000);
	          System.out.print(".");
	       }
	       System.out.println();
	       queueConn.close();
		}catch(Exception exception){
			System.out.println("Exception  = "+exception);
		}
	}

	@Override
	public void onException(JMSException arg0) {
		System.err.println("an error occurred: " + arg0);
	}

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("received: " + msg.getText());
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
}
