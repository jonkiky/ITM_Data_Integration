package org.albany.edu.IntergrateFactory;


/**
 * Internal Message Carrier. Be used when a function need to return String .
 * Status {200,300,404,500} could define operation result, such as failure or have error.
 * Desc define what is the meaning of this status.
 * Message return value if operation success or return null otherwise. 
 * 
 * <p>
 * @author Yizhen Chen
 * @version 2015-8-15
 * 
 */
public class Message {

	private int STATUS;
	private String DESC;
	private String MESSAGE;
	
	
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	public String getDESC() {
		return DESC;
	}
	public void setDESC(String dESC) {
		DESC = dESC;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	
	
	
}
