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

public class ReadDataByRestApi implements IReader {

	private String url;
	private String userName;
	private String password;

	public Message getData() {
		
		return null;
	}


	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
