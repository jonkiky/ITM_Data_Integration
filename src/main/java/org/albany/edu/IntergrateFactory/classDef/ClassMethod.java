package org.albany.edu.IntergrateFactory.classDef;

import java.util.List;

import org.albany.edu.IntergrateFactory.MethodInput;

public class ClassMethod {

	// define function type { public or private}
	private String functionType;
	
	// define function return type { Object, void }
	private String returnType;
	
	// define function input { type : name}
	private List<MethodInput> opts;
	
	// define function body
	private String functionContent;

	//define as is static or not
	private String isStatic ="";
	
	//define a function name
	private String functionName;
	
	
	
	
	// construct a function
	public String toString(){
		
		String out = this.functionType+" "+this.isStatic+" "+this.returnType+" "+this.functionName+"( ";
		if(null!=opts&&!opts.isEmpty()){
			for(MethodInput attr : this.opts){
				out+=" "+attr.getType()+" "+attr.getName()+",";
			}
			out=out.substring(0, out.length()-1);
		}
		out+="){ " +this.functionContent+"  }";
		return out;
	}
	
	
	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}


	public String getFunctionContent() {
		return functionContent;
	}

	public void setFunctionContent(String functionContent) {
		this.functionContent = functionContent;
	}


	public List<MethodInput> getOpts() {
		return opts;
	}


	public void setOpts(List<MethodInput> opts) {
		this.opts = opts;
	}


	public String getIsStatic() {
		return isStatic;
	}


	public void setIsStatic(String isStatic) {
		this.isStatic = isStatic;
	}


	public String getFunctionName() {
		return functionName;
	}


	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	
	
	
}
