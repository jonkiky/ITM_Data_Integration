package org.albany.edu.webservice;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.albany.edu.IntergrateFactory.FactoryConfiguration;
import org.albany.edu.IntergrateFactory.JsonFactory;
import org.albany.edu.IntergrateFactory.Interface.Imp.ReadDataByFileAddress;
import org.albany.edu.model.ModelBuilderByJson;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * 
 * @author Yizhen Chen
 * @version 2015-8-18
 * 
 */
public class ITMService {

	/**
	 * Check User Login
	 * <p>
	 * 
	 * @param FactoryConfiguration
	 *            config config contains project information for connect KF5
	 * 
	 * @param URL
	 *            URL to connect KF5
	 * 
	 * @param pythonFileLocation
	 *            KF5 use python to provide api, this arg indicate location if
	 *            python code
	 * 
	 * @return boolean
	 */

	public boolean login(FactoryConfiguration config, String URL,
			String pythonFileLocation) throws IOException, InterruptedException {

		String output;
		output = login(URL, config.getUserName(), config.getPassword(),
				pythonFileLocation);
		if (output.equals("Error")) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Check User Login
	 * <p>
	 * 
	 * @param userName
	 * @param passWord
	 * @param URL
	 *            URL to connect KF5
	 * 
	 * @param pythonFileLocation
	 *            KF5 use python to provide api, this arg indicate location if
	 *            python code
	 * 
	 * @return String
	 */

	public String login(String URL, String userName, String passWord,
			String pythonFileLocation) throws IOException, InterruptedException {

		try {
			StringBuffer cmdout = new StringBuffer();
			
//			String[] cmd2 = {"/network/rit/lab/zhanglab/custom-python/bin/python", pythonFileLocation
//					, URL, userName, passWord};
			
			String[] cmd2 = {"python", pythonFileLocation
					, URL, userName, passWord};
			
			ProcessBuilder builder = new ProcessBuilder(cmd2);
			Process proc = builder.start();
			proc.waitFor();
			InputStream fis = proc.getInputStream();
			InputStream is2 = proc.getErrorStream();  
			   InputStreamReader isr = new  InputStreamReader(is2,  "GBK" );  
			    BufferedReader br2 = new  BufferedReader(isr);  
			    String line2;  
			    while  ((line2 = br2.readLine()) !=  null ) {  
				      System.out.println(line2);  }
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				cmdout.append(line).append(System.getProperty("line.separator"));
			}
			if (cmdout.toString().isEmpty() || cmdout.toString().trim().equals("Error")) {
				return "Error";
			} else {
				updateUserPermissionTypeInITM(cmdout.toString());
				return cmdout.toString();
			}
			
		} catch (Exception e) {
			return e.toString();
		}
	}

	public void updateUserPermissionTypeInITM(String userInfo) {
		System.out.println("userInfo:" + userInfo);

	}

	/**
	 * Get data through kF5 python api then construct the javabeans base on
	 * genomic model. The data will store into Mysql .
	 * <p>
	 * 
	 * @param FactoryConfiguration
	 *            config config contains project information for connect KF5
	 * 
	 * @param URL
	 *            URL to connect KF5
	 * 
	 * @param pythonFileLocation
	 *            KF5 use python to provide api, this arg indicate location if
	 *            python code
	 * 
	 */
	public void getDataFromKf5Api(FactoryConfiguration config, String URL,
			String pythonFileLocation) throws InstantiationException,
			IllegalAccessException {
		StringBuffer cmdout = new StringBuffer();
		long startTime = System.currentTimeMillis();
		// String startTime="1444059309877";
//		String cmd = "/network/rit/lab/zhanglab/Python-2.7.10/python "
//				+ pythonFileLocation + " \"" + URL + "\" \""
//				+ config.getUserName() + "\" \"" + config.getPassword()
//				+ "\" \"" + startTime + "\"";
//		String[] cmdA = { "bash", "-c", cmd };
//		System.out.println("bash :" + startTime);
		
//		String[] cmd2 = {"/network/rit/lab/zhanglab/custom-python/bin/python", pythonFileLocation
//				, URL,  config.getUserName(), config.getPassword(),String.valueOf(startTime)};
		String[] cmd2 = {"python", pythonFileLocation
				, URL,  config.getUserName(), config.getPassword(),String.valueOf(startTime)};
		
		System.out.println("cmd2 :" + cmd2[0]+cmd2[1]+cmd2[2]+cmd2[3]+cmd2[4]);
		// Run python api to retrieve data from kf5
		try {

			ProcessBuilder pb = new ProcessBuilder(cmd2);
			pb.redirectErrorStream(true); // equivalent of 2>&1
			Process proc = pb.start();
			proc.waitFor();
			
			InputStream fis = proc.getInputStream();
			InputStream is2 = proc.getErrorStream();  
			   InputStreamReader isr = new  InputStreamReader(is2,  "GBK" );  
			    BufferedReader br2 = new  BufferedReader(isr);  
			    String line2;  
			    while  ((line2 = br2.readLine()) !=  null ) {  
				      System.out.println(line2);  }
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				cmdout.append(line).append(System.getProperty("line.separator"));
			}
			if (cmdout.toString().isEmpty() || cmdout.toString().trim().equals("Error")) {
				System.out.println( "Error");
			} else {
				updateUserPermissionTypeInITM(cmdout.toString());
				System.out.println( cmdout.toString());
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();

		// System.out.println(endTime);
		// System.out.println((endTime - startTime) / (1000 * 60 * 60));

		ModelBuilderByJson model = new ModelBuilderByJson();
		// init factory
		JsonFactory factory = model.getFactory();

		factory.setConfig(config);
		factory.initFactoryInDB();
		// Retrieve note
		ReadDataByFileAddress reader = new ReadDataByFileAddress();

		String loc = "D://kf5data";
		// URL[] urls = new URL[] { new URL("file:/"
		// + System.getProperty("user.dir") + "/src") };
		// URLClassLoader ul = new URLClassLoader(urls);

		// Retrieve view info
		// reader.setAddress("D://kf5data//kf5view" + startTime + ".json");
		//
		// String noteGroup = "ConcreteNoteGroup";
		// config.setUser_table(noteGroup);
		// model.build(reader, noteGroup, "INoteGroup");
		// model.check(true);
		// Class c5 = ul.loadClass("org.albany.edu.model."
		// + model.getNoteGroup().get(noteGroup).getPackageinfo()
		// + "." + model.getNoteGroup().get(noteGroup).getClassName());
		//
		// model.StoreData(c5.newInstance());
		//
		// System.out.print("Get NoteGroup End");

		// Retrieve User
		// reader.setAddress("D://kf5data//kf5Author" + startTime + ".json");
		//
		// String user = "ConcreteUser";
		// config.setUser_table(user);
		// model.build(reader, user, "IUser");
		// model.check(true);
		// Class c3 = ul.loadClass("org.albany.edu.model."
		// + model.getUsers().get(user).getPackageinfo() + "."
		// + model.getUsers().get(user).getClassName());
		//
		// model.StoreData(c3.newInstance());
		//
		// System.out.print("Get User End");

		// Retrieve buildon by view id
		reader.setAddress(loc + "/kf5BuildOn" + startTime + ".json");

		String noteRelation = "BuildOn";
		config.setUser_table(noteRelation);
		model.build(reader, noteRelation, "INoteAndNoteRelationShip");
		model.check(true);
		model.StoreData(noteRelation);
		// Class c4 = ul.loadClass("org.albany.edu.model."
		// + model.getNoteWithNote().get(noteRelation)
		// .getPackageinfo() + "."
		// + model.getNoteWithNote().get(noteRelation).getClassName());

		// Class c4 = Class.forName("org.albany.edu.model."
		// + model.getNoteWithNote().get(noteRelation)
		// .getPackageinfo() + "."
		// + model.getNoteWithNote().get(noteRelation).getClassName());
		//
		// model.StoreData(c4.newInstance());

		System.out.print("Get buildon End");

		// Retrieve Section
		reader.setAddress(loc + "/kf5Section" + startTime + ".json");

		String noteGroup2 = "Section";
		config.setUser_table(noteGroup2);
		model.build(reader, noteGroup2, "INoteGroup");
		model.check(true);
		model.StoreData(noteGroup2);
		// Class c6 = Class.forName("org.albany.edu.model."
		// + model.getNoteGroup().get(noteGroup2)
		// .getPackageinfo()
		// + "."
		// + model.getNoteGroup().get(noteGroup2)
		// .getClassName());
		//
		// model.StoreData(c6.newInstance());

		System.out.print("Get Section End");

		// Retrieve post by view id
		reader.setAddress(loc + "/kf5viewpost" + startTime + ".json");

		String noteRelationToView = "NoteAndNoteGroupRelationShip";
		config.setUser_table(noteRelationToView);
		model.build(reader, noteRelationToView, "INoteAndNoteGroupRelationShip");
		model.check(true);
		// Class c7 = Class.forName("org.albany.edu.model."
		// + model.getNoteWithNoteGroup().get(noteRelationToView)
		// .getPackageinfo()
		// + "."
		// + model.getNoteWithNoteGroup().get(noteRelationToView)
		// .getClassName());
		//
		// model.StoreData(c7.newInstance());
		model.StoreData(noteRelationToView);

		System.out.print("Get post by view id End");
	}

	public static void main(String arg[]) throws IOException,
			InterruptedException, InstantiationException,
			IllegalAccessException {
		FactoryConfiguration config = new FactoryConfiguration();
		config.setEmail("jonkiky@gmail.com");
		config.setFirstName("yizhen");
		config.setLastName("chen");
		config.setOrg("SUNYAlbany");
		config.setPassword("jianweizh");
		config.setProjectName("classModelTest");
		config.setUserName("Jianwei Zhang");
		String URL = "https://kf.utoronto.ca:443/kforum/";
		config.setDb("TeckWhye_Sec2");
		ITMService service = new ITMService();
		boolean flag = service.login(config, URL,
				"D://kf5py-master//login.py");

		if (flag) {
			System.out.print("Login Success");
			service.getDataFromKf5Api(config,URL,"D://kf5py-master//kf5Api.py");
		} else {
			System.out.print("Login failed");
		}

		// service.login(URL,"Jianwei Zhang","jianweizh","//network//rit//lab//zhanglab//kf5py-master//login.py");
	}
}
