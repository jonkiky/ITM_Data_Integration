package org.albany.edu.IntergrateFactory.Interface;

import org.albany.edu.IntergrateFactory.Message;

/**
 * This interface is for validate data source is under well define or not.
 *  
 * @author Yizhen Chen
 * @version 2015-8-17
 * 
 */
public interface IValidate {

	public Message validate(String source);
	
	public void display();
}
