package org.albany.edu.IntergrateFactory.Interface.Imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.Interface.IBuilder;
import org.albany.edu.IntergrateFactory.Interface.IParser;
import org.albany.edu.IntergrateFactory.Interface.IValidate;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;

/**
 * This is main class provide base methods to map data source to generic model
 * and Store into Database. <br>
 * The assemble line has two part. First one is to construct model (model of
 * data). Second is to create sample according to model. The model is set of
 * classes , sample is instance of these classes.
 *
 * 
 * @author Yizhen Chen
 * @version 2015-8-15
 * 
 */

abstract class ClassBuilder implements IBuilder {

	/**
	 * Parser helps to analysis data source . They provides methods to deal with
	 * data.
	 */
	public IParser parser;

	/**
	 * Data could be any formate, IValidate could validate data if well defined.
	 */
	public IValidate validate;

	/**
	 * Model holder Just like factory , Models have to pass quality testing.
	 * UnCheckModele is product waiting for checking. Checked Model is product
	 * ready for using. Map<className,class define>
	 */
	private Map<String, ClassDefine> uncheckModel = new LinkedHashMap <String, ClassDefine>();
	private Map<String, ClassDefine> checkModel = new LinkedHashMap <String, ClassDefine>();

	
	
	public static void makeDir(File dir) {  
        if(! dir.getParentFile().exists()) {  
            makeDir(dir.getParentFile());  
        }  
        dir.mkdir();  
    } 
	
	/**
	 * 
	 * Load Java Bean into JVM, So we could use it.
	 * 
	 * 
	 */
	public void CreateDataBaseSchema() {
		
		
	}

	/**
	 * 
	 * Load Java Bean into JVM, So we could use it.
	 * 
	 * 
	 */
	public Message LoadJavaBeanToJVM( Map<String, ClassDefine> checkModel ) {
	
		Message msg = new Message();
		
		// if checkModel is empty then nothing load into JVM
		if (checkModel.isEmpty()) {
			msg.setSTATUS(400);
			msg.setMESSAGE("No Model is found");
		} else {
			// itereate  checkmodel map
			Iterator<String> keySet = checkModel.keySet().iterator();
			
			while (keySet.hasNext()) {
				String key = keySet.next();
				ClassDefine classDef = checkModel.get(key);
				// Get current project location
				String fileName =//System.getProperty("user.dir")
						  "D:\\workspace\\DIpure\\org.albany.edu\\src\\main\\java\\org\\albany\\edu\\model\\"
						+ classDef.getPackageinfo()+"\\" +classDef.getClassName()+ ".java";
				// create file
				File f = new File(fileName);
				try {
					if (!f.exists()) {
						 if(! f.exists()) {  
					            makeDir(f.getParentFile());  
					        }  
						f.createNewFile();
					}
					FileWriter fw;
					fw = new FileWriter(f);
					fw.write(classDef.toString());
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				Iterator<String> keySet2 = checkModel.keySet().iterator();
				List<ClassDefine> temp = new ArrayList<ClassDefine>();
				while (keySet2.hasNext()) {
					
					String key = keySet2.next();
					ClassDefine classDef = checkModel.get(key);
					temp.add(classDef);
					}
				for(ClassDefine classDef:temp){
				try {
					String fileName =//System.getProperty("user.dir")
							 "D:\\workspace\\DIpure\\org.albany.edu\\src\\main\\java\\org\\albany\\edu\\model\\"
							+ classDef.getPackageinfo()+"\\" +classDef.getClassName()+ ".java";
					File f = new File(fileName);
					JavaCompiler compiler = ToolProvider
							.getSystemJavaCompiler();
					StandardJavaFileManager fileMgr = compiler
							.getStandardFileManager(null, null, null);
					Iterable units = fileMgr.getJavaFileObjects(fileName);
					CompilationTask t = compiler.getTask(
							null,
							fileMgr,
							null,
							Arrays.asList("-d","D:\\workspace\\DIpure\\org.albany.edu\\target\\classes"), null, units);
					t.call();
					fileMgr.close();
//					if (f.exists()) {
//						URL[] urls = new URL[] { new URL("file:/"
//								+ System.getProperty("user.dir") + "/src") };
//						URLClassLoader ul = new URLClassLoader(urls);
//						Class c = ul.loadClass("org.albany.edu.model."+classDef.getPackageinfo()
//								+"."+ classDef.getClassName());
//						System.out.println(c);
//						if(classDef.getImplementsClass()=="INote"){
//							INote note = (INote) c.newInstance();
//							System.out.println("***************"+note.toString());
//						}
//						
//					} else {
//						System.out.println("file does not exit.");
//					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					temp.add(classDef);
				}

			}
		}
		return msg;
	}

	
	
	
	public void display() {
		System.out.println("Init assemble line....");
		parser.display();
		validate.display();
		System.out.println("Assemble Line is ready....");
	}

	public IParser getParser() {
		return parser;
	}

	public void setParser(IParser parser) {
		this.parser = parser;
	}

	public IValidate getValidate() {
		return validate;
	}

	public void setValidate(IValidate validate) {
		this.validate = validate;
	}

	public Map<String, ClassDefine> getUncheckModel() {
		return uncheckModel;
	}

	public void setUncheckModel(Map<String, ClassDefine> uncheckModel) {
		this.uncheckModel = uncheckModel;
	}

	public Map<String, ClassDefine> getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(Map<String, ClassDefine> checkModel) {
		this.checkModel = checkModel;
	}

}
