package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

public class ClassDefine {
	private List<String> importPackage;
	private String packageinfo;
	private String className;
	private List<String> implementsClass;
	private String entendsClass;
	private List<ClassAttributes> attrs;
	private List<ClassMethod> methods;

	public List<String> getImportPackage() {
		return importPackage;
	}

	public void setImportPackage(List<String> importPackage) {
		this.importPackage = importPackage;
	}

	public String toString() {
		String classDef = null;
		if (null == className || null == packageinfo) {
			// needs i18n
			return null;
		} else {
			String rt = "\r\n";
			classDef = "package org.albany.edu.model." + packageinfo + ";" + ""
					+ rt;
			List<String> cleanData = new ArrayList<String>();
			// import package
			if (null != importPackage && !importPackage.isEmpty()) {
				for (String pk : importPackage) {
					if (!cleanData.contains(pk)) {
						classDef += pk + ";" + rt;
						cleanData.add(pk);
					}
				}
			}

			if ((null == implementsClass||implementsClass.isEmpty()) && null == entendsClass) {
				classDef += "public class  " + className + rt + "{" + rt;
			} else if ((null == implementsClass ||implementsClass.isEmpty())&& null != entendsClass) {
				classDef += "public class  " + className + " extends "
						+ entendsClass + rt + "{" + rt;
			} else if (null == entendsClass && null != implementsClass&&!implementsClass.isEmpty()) {
			
				classDef += "public class  " + className + " implements IModel ";
				for(String st : implementsClass){
					classDef+= ","+st;
				}
				classDef+="{" + rt;
					
			} else if (!(null == implementsClass) && !(null == entendsClass)) {
				classDef += "public class  " + className + " extends "
						+ entendsClass + " implements IModel" ;
						for(String st : implementsClass){
							classDef+= ","+st;
						}
						classDef += "{" + rt;
			}

			for (ClassAttributes att : attrs) {
				
				if (att.getType() != "list") {
					if (!att.getAnnotation().isEmpty()) {
						for (String anno : att.getAnnotation()) {
							classDef += anno + rt;
						}

					}
					if (null == att.getDefaultValue()) {
						classDef += att.getScope() + " " + att.getType() + " "
								+ att.getName() + ";" + rt;
					} else {
						classDef += att.getScope() + " " + att.getType() + " "
								+ att.getName() + "=" + att.getDefaultValue()
								+ ";" + rt;
					}

				} else {
					if (null == att.getDefaultValue()) {
						classDef += att.getScope() + " " + att.getType() + "<"
								+ att.getName().substring(0, 1).toUpperCase()
								+ att.getName().substring(1) + "> "
								+ att.getName().toLowerCase() + ";" + rt;
					} else {
						classDef += att.getScope() + " " + att.getType() + "<"
								+ att.getName().substring(0, 1).toUpperCase()
								+ att.getName().substring(1) + "> "
								+ att.getName().toLowerCase() + "="
								+ att.getDefaultValue() + ";" + rt;
					}

				}
			}
			if (null != methods && !methods.isEmpty()) {
				for (ClassMethod method : methods) {
					classDef += rt + method.toString();
				}
			}

			classDef += "}";
		}
		return classDef;
	}

	public String getPackageinfo() {
		return packageinfo;
	}

	public void setPackageinfo(String packageinfo) {
		this.packageinfo = packageinfo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getImplementsClass() {
		return implementsClass;
	}

	public void setImplementsClass(List<String> implementsClass) {
		this.implementsClass = implementsClass;
	}

	public String getEntendsClass() {
		return entendsClass;
	}

	public void setEntendsClass(String entendsClass) {
		this.entendsClass = entendsClass;
	}

	public List<ClassAttributes> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<ClassAttributes> attrs) {
		this.attrs = attrs;
	}

	public List<ClassMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<ClassMethod> methods) {
		this.methods = methods;
	}

}
