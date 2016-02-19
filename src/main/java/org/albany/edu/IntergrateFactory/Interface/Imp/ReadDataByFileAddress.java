package org.albany.edu.IntergrateFactory.Interface.Imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.Interface.IReader;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataByFileAddress implements IReader {
	
	private String address ;
	
	
	public Message getData() {
		String jsonString ="";
		Resource res1 = new FileSystemResource(this.address);
		ObjectMapper mapper = new ObjectMapper();
		InputStream ins1;
		try {
			ins1 = res1.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(ins1, writer, "UTF-8");
			jsonString = writer.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Message msg = new Message();
		msg.setSTATUS(200);
		msg.setMESSAGE(jsonString);
		return msg;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
