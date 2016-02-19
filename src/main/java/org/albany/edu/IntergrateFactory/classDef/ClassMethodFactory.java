package org.albany.edu.IntergrateFactory.classDef;

import java.util.ArrayList;
import java.util.List;

import org.albany.edu.IntergrateFactory.Interface.IClassMethod;

public class ClassMethodFactory {
	
	
	private List<IClassMethod> regist = new ArrayList<IClassMethod>();

	
	public List<IClassMethod> getRegist() {
		return regist;
	}


	public void setRegist(List<IClassMethod> regist) {
		this.regist = regist;
	}

	public void registMethod(IClassMethod e){
		this.regist.add(e);
	}
	
	
	public List<ClassMethod> getListMethod(ClassDefine classDef){
		List<ClassMethod> classMethods = new ArrayList<ClassMethod>();
		for(IClassMethod methodBuilder : regist){
			for(ClassMethod method: methodBuilder.createMethod(classDef)){
				classMethods.add(method);
			}
			
		}
		return classMethods;
	}
	
	public ClassMethodFactory(){
		IClassMethod toString = new ClassMethodDisplay();
		IClassMethod GetterAndSetter = new ClassMethodGetterAndSetter();
		IClassMethod constructruction= new ClassMethodConstruction();
		IClassMethod InsertSQL=new ClassMethodInsertSQL();
		
		regist.add(toString);
		regist.add(GetterAndSetter);
		regist.add(constructruction);
		regist.add(InsertSQL);

	}
}
