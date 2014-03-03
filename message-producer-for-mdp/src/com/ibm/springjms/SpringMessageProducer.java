package com.ibm.springjms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * This class is actual responsible for sending message to the destination with
 * the help of jmsTemplate. This class defines sendMessage() user defined method 
 * which is called from ElectronicPostMan.deliver() to send the message.
 * 
 * Spring container does magic on this class when initializes this class. The
 * magic is setting the jmsTemplate throgh dependency injection pattern. 
 * 
 * @author Ghulam Rashid, iGate Mastech
 * 
 */
public final class SpringMessageProducer
{
    private JmsTemplate jmsTemplate;

    /**
     * Called by spring container while intializing the bean.
     * 
     * @param jmsTemplate
     *            The jmsTemplate to set.
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * This method responsible for sending the message to MOM server.
     * 
     * @param message
     * 			Application data to be transported to the destination.
     */
    protected final void sendMessage(final String message) 
	{
	    System.out.println(this + " : In sendMessage() of SpringMessageProducer");
	    System.out.println(this + " : Calling send() of jmsTemplate... ");	     
	    
		jmsTemplate.send(new MessageCreator() 
		{
			public Message createMessage(Session session) throws JMSException 
			{
			    System.out.println(this + " : Creating message ......");    				
				TextMessage textMessage = session.createTextMessage();
				System.out.println(this + " : Inside send() sending message :   " + message);
				textMessage.setText(message);
				return textMessage;
			}
		});
	}
}