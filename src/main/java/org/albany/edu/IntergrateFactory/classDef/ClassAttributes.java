package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

public class ClassAttributes {

	// Scope { private , public , protect}
	private String scope = "private";
	//  String , int 
	private String type;
	
	private String name;
	
	private String defaultValue=null;
	
	private List<String> annotation=new ArrayList<String>();
	
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAnnotation() {
		return annotation;
	}
	public void setAnnotation(List<String> annotation) {
		this.annotation = annotation;
	}
	
	public void addAnnotation(String str){
		this.annotation.add(str);
	}
}
