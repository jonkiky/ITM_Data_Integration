package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

import org.albany.edu.IntergrateFactory.Interface.IClassMethod;

public class ClassMethodConstruction implements IClassMethod {

	public List<ClassMethod> createMethod(ClassDefine classDef) {
		List<ClassMethod> constractors = new ArrayList<ClassMethod>();
		ClassMethod method = new ClassMethod();
		method.setFunctionContent("");
		method.setFunctionName(classDef.getClassName());
		method.setFunctionType("public");
		method.setIsStatic("");
		method.setOpts(null);
		method.setReturnType("");
		constractors.add(method);
		return constractors;
	}
	public List<String> getImportPackages() {
		// TODO Auto-generated method stub
		return null;
	}

}
