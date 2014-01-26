package com.student.queues;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueSend {

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
	
	protected void send_queuenameMessage() {
		try {
			QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("jms/my_qcf");			
			Queue q = (Queue) ctx.lookup("jms/my_queue");
			QueueConnection qc = qcf.createQueueConnection();
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender snd = qs.createSender(q);
			snd.setDeliveryMode(DeliveryMode.PERSISTENT);
			qc.start();
			TextMessage msg = qs.createTextMessage("Hello");
			snd.send(msg);
			System.out.println("msg:" + " id=" + msg.getJMSMessageID());
			System.out.println("sent: " + msg.getText());
			snd.close();
			qs.close();
			qc.close();
		} catch (Exception jmse) {
			jmse.printStackTrace();
		}
	}
	
	/*private void ibmJms(){
		try
		{
			MQQueueConnectionFactory cf = new MQQueueConnectionFactory();
			// Config
			cf.setHostName("localhost");
			cf.setPort(1414);
			cf.setTransportType(JMSC.MQJMS_TP_BINDINGS_THEN_CLIENT);
			cf.setQueueManager("HOMEQ1");
			cf.setChannel("HOMEQ1.APP.SVRCONN");
			
			MQQueueConnection connection = (MQQueueConnection) cf.createQueueConnection();
			MQQueueSession session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			MQQueue queue = (MQQueue) session.createQueue("HOMEQ1.REQUEST");
			
			MQQueueSender sender = (MQQueueSender) session.createSender(queue);
			MQQueueReceiver receiver = (MQQueueReceiver) session.createReceiver(queue);

			long uniqueNumber = System.currentTimeMillis() % 1000;
			JMSTextMessage message = (JMSTextMessage) session.createTextMessage("SimplePTP " + uniqueNumber);

			connection.start();

			sender.send(message);
			System.out.println("Sent message:\n" + message.getText());

			JMSMessage receivedMessage = (JMSMessage) receiver.receive(10000);
			System.out.println("\\nReceived message:\\n" + receivedMessage.toString());

			sender.close();
			receiver.close();
			session.close();
			connection.close();

		} catch (JMSException jmsex) {
			System.out.println(jmsex);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}*/

	public static void main(String[] args) {

		QueueSend send = new QueueSend();
		send.send_queuenameMessage();
		//send.ibmJms();
	}
}
