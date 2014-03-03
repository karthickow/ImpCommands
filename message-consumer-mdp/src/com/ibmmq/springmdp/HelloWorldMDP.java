package com.ibmmq.springmdp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 
 * This is the MDP class, which listens for incoming message. This 
 * implements javax.jms.MessageListener. MessageListener is used to receive 
 * messages asynchronously. 
 * 
 * @author Ghulam Rashid, iGate Mastech
 *
 */
public class HelloWorldMDP implements MessageListener
{
    /**
     * onMessage() method is triggered automatically once message arrives 
     * in the queue.
     * 
     * @param message : message containing application data.
     */
    public void onMessage(Message message)
    {
        try
        {
            System.out.println(this + " : Message Driven POJO received message");
            System.out.println(message);
            
            if(message instanceof TextMessage)
            {
                System.out.println(this + " : " + ((TextMessage) message).getText());    
            }
            else
            {
                System.out.println(this + " : " + message.toString());
            }            
        }
        catch (JMSException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}