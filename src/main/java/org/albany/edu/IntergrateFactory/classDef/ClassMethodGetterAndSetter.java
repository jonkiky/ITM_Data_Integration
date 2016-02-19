package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

import org.albany.edu.IntergrateFactory.MethodInput;
import org.albany.edu.IntergrateFactory.Interface.IClassMethod;

public class ClassMethodGetterAndSetter implements IClassMethod{

	public List<ClassMethod> createMethod(ClassDefine classDef) {
		// TODO Auto-generated method stub
		List<ClassMethod> methods = new ArrayList<ClassMethod>();
		List<ClassAttributes> atts = classDef.getAttrs();
		if(null!=atts&&!atts.isEmpty()){
			for(ClassAttributes att :atts){
				ClassMethod getMethod= new ClassMethod();
				getMethod.setFunctionContent("return "+att.getName()+";");
				getMethod.setFunctionName("get"+att.getName().substring(0,1).toUpperCase()+ att.getName().substring(1));
				getMethod.setFunctionType("public");
				getMethod.setOpts(null);
				getMethod.setIsStatic("");
				getMethod.setReturnType(att.getType());
				methods.add(getMethod);
				
				ClassMethod setMethod= new ClassMethod();
				setMethod.setFunctionContent("this."+att.getName()+"="+att.getName()+";");
				setMethod.setFunctionName("set"+att.getName().substring(0,1).toUpperCase()+ att.getName().substring(1));
				setMethod.setFunctionType("public");
				setMethod.setIsStatic("");
				List<MethodInput> inputs =new ArrayList<MethodInput>();
				MethodInput input= new MethodInput();
				input.setType(att.getType());
				input.setName(att.getName());
				inputs.add(input);
				setMethod.setOpts(inputs);
				setMethod.setReturnType("void");
			
				methods.add(setMethod);
			}
		}
		
		
		return methods;
	}

	public List<String> getImportPackages() {
		// TODO Auto-generated method stub
		return null;
	}

}
