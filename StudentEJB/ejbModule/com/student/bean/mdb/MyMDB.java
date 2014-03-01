package com.student.bean.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "jms/messageQueue")
public class MyMDB implements MessageListener {

	public MyMDB() {
	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage msg = (TextMessage) message;
			try {
				System.out.println("Message received using MDB = "+msg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
