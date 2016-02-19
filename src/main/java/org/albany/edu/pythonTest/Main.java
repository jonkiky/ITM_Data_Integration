package org.albany.edu.pythonTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
	Log log = LogFactory.getLog(Main.class);
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat fromUser = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a",Locale.ENGLISH);
		SimpleDateFormat spdtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String xxx = spdtformat.format(fromUser.parse("Dec 12, 2014 10:30:48 AM"));
				 Date curDate = new Date();

		System.out.print(xxx);
		
	}
	
	
	public String connectToKF5Api(String URL,String userName, String passWord,String pythonFileLocation) throws IOException, InterruptedException{
			StringBuffer cmdout = new StringBuffer();
			String cmd = "python "+pythonFileLocation+" \""+URL+"\" \""+userName+"\" \""+passWord+"\"";
			Process proc = Runtime
					.getRuntime()
					.exec(cmd);
			InputStream fis = proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			proc.waitFor();
			String line = null;
			while ((line = br.readLine()) != null) {
				cmdout.append(line)
						.append(System.getProperty("line.separator"));
			}
			if(cmdout.toString().isEmpty()||cmdout.toString().equals("Error")){
				
				return "Error";
			}else{
				return cmdout.toString();
			}
		
			
	}
}