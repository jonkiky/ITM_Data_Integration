package org.albany.edu.IntergrateFactory.classDef;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.albany.edu.IntergrateFactory.MethodInput;
import org.albany.edu.IntergrateFactory.Interface.IClassMethod;

public class ClassMethodInsertSQL implements IClassMethod {

	public List<ClassMethod> createMethod(ClassDefine classDef) {
		ClassMethod method = new ClassMethod();

		// new line
		String rt = "\r\n";
		// define total output;
		String output = "List<String> output = new ArrayList<String>();";

		String retrunValue = "return output;";

		String thisClassInsertQuery = "String thisClassInsertQuery= \"Insert into `"
				+ classDef.getClassName().toLowerCase() + "` ( father_id,";
		String thisClassInsertValue = "values ('\"+fatherId+\"',";
		List<ClassAttributes> attrs = classDef.getAttrs();
		String attDataValue ="";

		/*
		 * ChildInSertQuery deal with Class combination issue.If a calss defined
		 * as Class A{ B b }
		 * 
		 * A has a relationship with class B In order to maintain this
		 * relationship we change class A into Class A { Uuid FatherId B b }
		 * Class B{ FatherId } which use father id to maintain their
		 * relationship in database.
		 * 
		 * Besides, Class A has it own Insert SQL , Class B has as well. When
		 * store Class A's instant into database,it will store B's instant into
		 * database as well.The childInsertQuery will stores Insert Query of
		 * Class B , in this cases.
		 */
		String childInSertQuery = "";

		for (ClassAttributes att : attrs) {
			if(att.getName()!="father_id"){

			

				if (!att.getType().startsWith("List")) {
					// If att type is string
				
					
					if (att.getType().equals("String")) {
						attDataValue+="String "+att.getName()+"instance=\" \";"+rt;
						attDataValue+="if(null!=get"
									+ att.getName().substring(0, 1).toUpperCase()
									+ att.getName().substring(1) + "()){"
											+ att.getName()+"instance= get"
											+ att.getName().substring(0, 1).toUpperCase()
											+ att.getName().substring(1) + "().replace(\"\\\"\",\"\\\\\\\"\");"+rt
											+ "};"+rt;
						thisClassInsertValue += "\\\"\"+"+att.getName()+"instance +\"\\\",";
						thisClassInsertQuery += " `"+att.getName().toLowerCase() + "`,";
						continue;
					}else if (att.getType().equals("Date")) {
						attDataValue+=" SimpleDateFormat spdtformat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");String"+att.getName()+"instance=\" \";"+rt;
						attDataValue+="if(null!=get"
									+ att.getName().substring(0, 1).toUpperCase()
									+ att.getName().substring(1) + "()){"
											+ att.getName()+"instance= spdtformat.format(get"
											+ att.getName().substring(0, 1).toUpperCase()
											+ att.getName().substring(1) + "());"+rt
											+ "};"+rt;
						thisClassInsertValue += "\\\"\"+"+att.getName()+"instance +\"\\\",";
						thisClassInsertQuery += " `"+att.getName().toLowerCase() + "`,";
						continue;
					} else if (att.getType().equals("int")
							|| att.getType().equals("Double")
							|| att.getType().equals("Boolean")) {
						// if att type among int double boolean
						thisClassInsertValue += "\"+get"
								+ att.getName().substring(0, 1).toUpperCase()
								+ att.getName().substring(1) + "()+\",";
						thisClassInsertQuery += " `"+att.getName().toLowerCase() + "`,";
						continue;
					} else {
						/*
						 * if the att type is object will call this object's
						 * InsertSQL function
						 */

						String getObject = att.getName().substring(0, 1)
								.toUpperCase()
								+ att.getName().substring(1) + "()";
						// Get List of InsertQuery from object
						String getInsertSQLOfObject = "List<String> "
								+ att.getName() + "InsertSQL =get" + getObject
								+ ".getInsertSQL(getUuid());";
						// Store Query in to output;
						String iteratorGetInsertSQLOfObject = "for(String st : "
								+ att.getName() + "InsertSQL)" + "{"
								+ " output.add(st);" + "};";
						childInSertQuery += rt + getInsertSQLOfObject + rt
								+ iteratorGetInsertSQLOfObject;
						
						
						
					}
					

				} else {
					if(att.getType().equals("List")){
						// If att is list of unknown value 
						// Do nothing
						
					}else if (att.getType().equals("List<Boolean>")
							|| att.getType().equals("List<int>")
							|| att.getType().equals("List<Double>")
							|| att.getType().equals("List<Boolean>")) {
						// If att is list of basic type

						thisClassInsertValue += "\"+get"
								+ att.getName().substring(0, 1).toUpperCase()
								+ att.getName().substring(1) + "().toString()+\",";
						thisClassInsertQuery += " `"+att.getName().toLowerCase() + "`,";
						continue;
					} else if (att.getType().equals("List<String>")) {

						thisClassInsertValue += "\\\"\"+get"
								+ att.getName().substring(0, 1).toUpperCase()
								+ att.getName().substring(1)
								+ "().toString()+\"\\\",";
						thisClassInsertQuery += " `"+att.getName().toLowerCase() + "`,";
						
						continue;
					} else {
						// If att is list of object
						String getListOfObject = "get"+att.getName().substring(0, 1)
								.toUpperCase()
								+ att.getName().substring(1) + "()";
						
						String iteratorListofObjectStart = "if(null!="+getListOfObject+"){"
								+ "for(" + att.getType().substring(5, att.getType().length()-1)
								+ " obj : " + getListOfObject + "){";

						String getInsertSQLOfObject = "List<String> "
								+ att.getName()
								+ "InsertSQL = obj.getInsertSQL(getUuid());";
						String iteratorGetInsertSQLOfObject = "for(String st : "
								+ att.getName() + "InsertSQL)" + "{"
								+ " output.add(st);" + "};";

						String iteratorListofObjectEnd = "}"
								+ "};";

						childInSertQuery += rt + iteratorListofObjectStart + rt
								+ getInsertSQLOfObject + rt
								+ iteratorGetInsertSQLOfObject + rt
								+ iteratorListofObjectEnd;
						
					

						continue;
					}

				}
			}

		}
		thisClassInsertQuery = thisClassInsertQuery.substring(0,
				thisClassInsertQuery.length() - 1) + ")";
		thisClassInsertValue = thisClassInsertValue.substring(0,
				thisClassInsertValue.length() - 1) + ");\";";
		thisClassInsertQuery += thisClassInsertValue;
		
		//Finial content
		String content =output+rt+attDataValue
				+childInSertQuery+rt
				+thisClassInsertQuery+rt
				+"output.add(thisClassInsertQuery);"+rt
				+retrunValue;
		method.setFunctionContent(content);
		method.setFunctionName("getInsertSQL");
		method.setFunctionType("public");
		method.setIsStatic("");
		List<MethodInput> inputs = new ArrayList<MethodInput>();
		MethodInput input = new MethodInput();
		input.setName("fatherId");
		input.setType("String");
		inputs.add(input);
		method.setOpts(inputs);
		method.setReturnType("List<String>");
		List<ClassMethod> methods = new ArrayList<ClassMethod>();
		methods.add(method);
		return methods;
	}

	public List<String> getImportPackages() {
		// TODO Auto-generated method stub
		return null;
	}

}
