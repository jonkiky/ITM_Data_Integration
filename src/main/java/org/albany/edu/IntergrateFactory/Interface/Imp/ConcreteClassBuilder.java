package org.albany.edu.IntergrateFactory.Interface.Imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.Interface.IBuilder;
import org.albany.edu.IntergrateFactory.Interface.IParser;
import org.albany.edu.IntergrateFactory.Interface.IValidate;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;
import org.albany.edu.model.INote;
import org.albany.edu.model.INoteGroup;
import org.albany.edu.model.IUser;
import org.albany.edu.model.IUserGroup;

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

public class ConcreteClassBuilder extends ClassBuilder implements IBuilder {

	/**
	 * Parser helps to analysis data source . They provides methods to deal with
	 * data.
	 */
	private IParser parser;

	/**
	 * Data could be any formate, IValidate could validate data if well defined.
	 */
	private IValidate validate;

	/**
	 * Model holder Just like factory , Models have to pass quality testing.
	 * UnCheckModele is product waiting for checking. Checked Model is product
	 * ready for using. Map<className,class define>
	 */
	private Map<String, ClassDefine> uncheckModel = new LinkedHashMap <String, ClassDefine>();
	private Map<String, ClassDefine> checkModel = new LinkedHashMap <String, ClassDefine>();

	
	/**
	 * Convert source text into JavaBean , return class definition.
	 * <p>
	 * 
	 * @param source
	 * @param className
	 * @return List<ClassDefine>
	 */
	public List<ClassDefine> ObjectToJavaBean(String JsonString,
			String Javabean, String className, String packageName) {
		if (!JsonString.isEmpty() && (Validate(JsonString).getSTATUS() == 200)) {
			List<ClassDefine> classDefs = parser.ObjectToJavaBean(JsonString,
					Javabean, className, packageName);
			for (ClassDefine classDef : classDefs) {
				if(uncheckModel.containsKey(classDef.getClassName())){
					if(uncheckModel.get(classDef.getClassName()).toString().length()<classDef.toString().length()){
						uncheckModel.put(classDef.getClassName(), classDef);
					}
				}else{
					uncheckModel.put(classDef.getClassName(), classDef);
					
				}
				
			}
			return classDefs;
		} else {
			return null;
		}

	}
	
	
	private void emptyUnCheckModel() {
		this.uncheckModel.clear();
	}

	private void emptyCheckedModel() {
		this.checkModel.clear();
	}





	/**
	 * Validate Data is well defined or not.If not well defined may cause parser
	 * data source failure
	 * <p>
	 * 
	 * @param source
	 * @return Message
	 */
	public Message Validate(String source) {
		return this.validate.validate(source);
	}

	/**
	 * Change the status of Model is under check or pass the check. If check
	 * pass, means model is good and ready for creating instants. If check
	 * failed , means model needs refine, current uncheck model will be
	 * destroyed.
	 * <p>
	 * 
	 * @param flag
	 *            true -> pass test, false -> test failed
	 */
	public void CheckModel(boolean flag) {
		// if flag true, model is ok
		if (flag) {
			Iterator<String> keySet = uncheckModel.keySet().iterator();
			while (keySet.hasNext()) {
				String key = keySet.next();
				checkModel.put(key, uncheckModel.get(key));
			}
			//emptyUnCheckModel();
		} else {
			emptyUnCheckModel();
		}

	}
	
	


	/**
	 * Create Class File into project
	 * 
	 * @param ClassDef
	 * @param filePath
	 * 
	 */
	public Message createClassFile(String classDef, String filePath) {
		return null;
	}

	public Object getSamples(String jsonSource,Object t) {
		return this.parser.getSamples(jsonSource,t);
	}


	public Message LoadJavaBeanToJVM() {
		return super.LoadJavaBeanToJVM(this.checkModel) ;  
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


	public void emptyBuilder() {
		// TODO Auto-generated method stub
		emptyUnCheckModel();
		emptyCheckedModel();
	}

	
}
