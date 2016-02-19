package org.albany.edu.IntergrateFactory.Interface.Imp;

import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.Interface.IValidate;



/**
 * This is a class implements from Ivalidate{@link IValidate} . 
 * Provides method to validate data. 
 * <p>
 *  
 * @author Yizhen Chen
 * @version 2015-8-15
 * 
 */


public class JsonValidate implements IValidate{

	public Message validate(String source) {
		
		Message msg = new Message();
		msg.setSTATUS(200);
		return msg;
	}
	
	
	public void display(){
		System.out.println("init Json Validate");
	}

}
