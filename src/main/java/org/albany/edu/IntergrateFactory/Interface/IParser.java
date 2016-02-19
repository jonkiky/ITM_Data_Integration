package org.albany.edu.IntergrateFactory.Interface;

import java.util.List;
import java.util.Map;

import org.albany.edu.IntergrateFactory.classDef.ClassDefine;

/**
 * Interface define base method deal with source data.
 * 
 * @author Yizhen Chen
 * @version 2015-8-15
 * 
 */

public interface IParser {

	/**
	 * Convert source text into JavaBean , return list of class definition.
	 * <p>
	 * 
	 * @param source ,JsonString is source text
	 * @param className , Javabean is  class name.
	 * @param javaBean , Which class 
	 * @return The list ClassDefine, or null if empty.
	 */
	public List<ClassDefine> ObjectToJavaBean(String source, String javaBean,String className,String packageName);

	/**
	 * Find data by key.
	 * <p>
	 * 
	 * @param source
	 *            source text
	 * @param key
	 *            search keyword
	 * @param condition
	 *            such equal to , contain ,not equal or not contain.
	 * 
	 * @return The value string, or null if empty.
	 */
	public String findDataByKey(String source, String key, String condition);

	/**
	 * Convert String into Map tree structure.
	 * <p>
	 * 
	 * @param source
	 *            source text
	 * @return Map<key,value>, or null if empty.
	 */
	public Map<String, String> ObjectToMap(String source);
	
	
	public  Object getSamples(String jsonSource,Object t );
	
	public void display();

}
