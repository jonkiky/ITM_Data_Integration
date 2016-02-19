package org.albany.edu.IntergrateFactory.Interface;

import java.util.List;
import java.util.Map;

import org.albany.edu.IntergrateFactory.Message;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;

public interface IBuilder {

	List<ClassDefine> ObjectToJavaBean(String JsonString, String Javabean,
			String className, String packageName);

	void CheckModel(boolean flag);

	Message LoadJavaBeanToJVM();

	Map<String, ClassDefine> getCheckModel();
	 
	Map<String, ClassDefine> getUncheckModel() ;
	
	Object getSamples(String jsonSource, Object t);
}
