package com.ibm.springjms;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

/**                                                                                              
 * This is a standalone program, which runs in its own JVM outside of the Websphere Application
 * Server. This class is responsible for delegating message to com.ibm.springjms.SpringMessageProducer
 * class.                                             
 *                                               
 * @see com.ibm.springjms.SpringMessageProducer : This is the Spring aware
 * class and responsible for sending the message to the final destination defined into the
 * context/mdp-client-context.xml.		         					
 *                                                                  	
 * @author Ghulam Rashid, iGate Mastech                   			
 *                                                                                  
 */                                                                       
public final class ElectronicPostMan                                    
{                                                                           
    /**                                                                    
     * This method is used to send the message and called from the main method.
     *                                                                  
     * @param message                                                  
     */                                                              
	private final void deliver(String message)                            
	{                                                           
        try
        {
            System.out.println(this + " : In implementaion class ... ");

            SpringMessageProducer originationMessageProducer= (SpringMessageProducer) getBean("mdp-client-factory","spring-message-producer");

            System.out.println(this + " : Got bean from factory - " + originationMessageProducer);
            originationMessageProducer.sendMessage(message);

            System.out.println(this + " : Message sent using Spring JMS.");
        }
        catch (Throwable throwable)
        {
            System.err.println(this + " : Problem sending message : " + throwable.getMessage());
            throwable.printStackTrace();
        }    
	}	
    
	/**
	 * This is the helper method. This method is called from deliver() method 
	 * for getting the bean SpringMessageProducer, spring container 
	 * intializes the bean and also initializes jmsTemplate through setter injection of
	 * Dependency Injection pattern. This setter method <code>setJmsTemplate</code> is 
	 * defined in the class SpringMessageProducer.
	 * 
	 * @param String : factoryName : the name of the factory which container uses for
	 * the intialization of bean.
	 * @param String : beanName. This is the actual bean name defined int mdp-client-context.xml
	 * 
	 * @return java.lang. Object This is the intialized POJO or bean class 
	 * 
	 */
	private final Object getBean(String factoryName, String beanName)
	{
		try
		{
			BeanFactoryLocator beanFactoryLocator = SingletonBeanFactoryLocator.getInstance();
			BeanFactoryReference beanFactoryReference = beanFactoryLocator.useBeanFactory(factoryName);
			return beanFactoryReference.getFactory().getBean(beanName);			
		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
			return null;
 		}		
	}
	
	/**
	 * This is main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    new ElectronicPostMan().deliver("Test message for Message Driven POJO sent at 	" + new GregorianCalendar().getTime());
	}
}