package org.albany.edu.IntergrateFactory.Interface;

import java.util.List;

import org.albany.edu.IntergrateFactory.classDef.ClassDefine;
import org.albany.edu.IntergrateFactory.classDef.ClassMethod;

public interface IClassMethod {

	List<ClassMethod> createMethod(ClassDefine classDef);
	List<String> getImportPackages();
}
