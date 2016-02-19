package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

import org.albany.edu.IntergrateFactory.Interface.IClassMethod;

public class ClassMethodDisplay implements IClassMethod{

	public List<ClassMethod> createMethod(ClassDefine classDef) {
		ClassMethod method = new ClassMethod();
		String functionContent = "return \""; 
		functionContent+="[class name]:'"+classDef.getClassName()+"'";	
		for(ClassAttributes attr:classDef.getAttrs()){
			functionContent+="[class attributes]:[type]'"+attr.getType()+"'[name]'"+attr.getName()+"'";
		}
				
		functionContent+="\";";
		method.setFunctionContent(functionContent);
		method.setFunctionName("toString");
		method.setFunctionType("public");
		method.setIsStatic("");
		method.setOpts(null);
		method.setReturnType("String");
		List<ClassMethod> methods =new ArrayList<ClassMethod>();
		methods.add(method);
		return methods;
	}

	public List<String> getImportPackages() {
		// TODO Auto-generated method stub
		return null;
	}

}
