package org.albany.edu.IntergrateFactory.Interface.Imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.albany.edu.IntergrateFactory.Interface.IParser;
import org.albany.edu.IntergrateFactory.classDef.ClassAttributes;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;
import org.albany.edu.IntergrateFactory.classDef.ClassMethod;
import org.albany.edu.IntergrateFactory.classDef.ClassMethodFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This is concrete class implements from IParser{@link IParser} . Provides base
 * implementations of all convenience methods to deal with with JSON data
 * source.
 * <p>
 * 
 * @author Yizhen Chen
 * @version 2015-8-15
 * 
 */

public class JsonParser implements IParser {

	private ClassMethodFactory factory = new ClassMethodFactory();

	
	public Map<String, Object>  ObjectToMap(String JsonString,String className) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(JsonString,
				Map.class);
		
	}
	
	public List<String>  ObjectMapToInsertString(Object sourceMap,String fatherId, String TableName, String db) {
		
		List<String> output = new ArrayList<String>();
		String keys="";
		String values="";
		String rename="";
		if (sourceMap instanceof Map) {
			String uuid =UUID.randomUUID().toString();
			
			String fId=fatherId;
			keys+="`uuid`,`father_id`";
			values+="'"+uuid+"','"+fId+"'";
			Iterator<String> keySet = ((Map<String, Object>) sourceMap)
					.keySet().iterator();
			while (keySet.hasNext()) {
				String key = keySet.next().trim();
				Object value = ((Map<String, Object>) sourceMap).get(key);
				boolean nameChanged = false;
				if (Character.isDigit(key.charAt(0))||key.contains("_") || key.contains("-") || key.contains("/")||key.contains(" ")) {
					nameChanged = true;
				}
				if (nameChanged) {
					rename+="&"+key;
				 	key=TableName+key.replace("_", "").replace("-", "").replace("/", "").replace(" ", "");
				}
				
				
				
				// System.out.print("key is:" + key);
				// System.out.println("value is:" + value);
				if (value instanceof String) {
					// classDef+="private String "+key+";"+rt;
					keys+=",`"+key.replace("\'","\"")+"`";
					values+=",'"+value.toString().replace("\'","\"")+"'";
				} else if (value instanceof Integer) {
					keys+=",`"+key.replace("\'","\"")+"`";
					values+=","+(Integer)value;
				} else if (value instanceof Double) {
					keys+=",`"+key.replace("\'","\"")+"`";
					values+=",'"+value.toString()+"'";
				} else if (value instanceof Date) {
					keys+=",`"+key.replace("\'","\"")+"`";
					values+=",'"+value.toString().replace("\'","\"")+"'";
				}else if (value instanceof Boolean) {
					keys+=",`"+key.replace("\'","\"")+"`";
					values+=","+(Boolean)value;
				} else if (value instanceof Map) {
					
					for (String st : ObjectMapToInsertString(value, uuid, key
							,db)) {
						output.add(st);
					}

				} else if (value instanceof List) {
					if (((List) value).size() > 0) {

						if (((List) value).get(0) instanceof String) {

							keys+=",`"+key.replace("\'","\"")+"`";
							values+=",'"+value.toString().replace("\'","\"")+"'";

						} else if (((List) value).get(0) instanceof Integer) {

							keys+=",`"+key.replace("\'","\"")+"`";
							values+=",'"+value.toString().replace("\'","\"")+"'";
						} else if (((List) value).get(0) instanceof Double) {

							keys+=",`"+key.replace("\'","\"")+"`";
							values+=",'"+value.toString().replace("\'","\"")+"'";

						} else if (((List) value).get(0) instanceof Boolean) {

							keys+=",`"+key.replace("\'","\"")+"`";
							values+=",'"+value.toString().replace("\'","\"")+"'";

						} else {
						
							/////////
							// (List) value).get(0), will be the problem 
							///////
							for(int valueSize = ((List) value).size()-1;valueSize>=0; valueSize--){
								for (String st : ObjectMapToInsertString(
										((List) value).get(valueSize),
										uuid,
										key.substring(0, 1).toUpperCase()
												+ key.substring(1),db)) {
									output.add(st);
								}
							}
							
						}

					} else {

						// do nothing
					}

				} else {
					for (String st : ObjectMapToInsertString(
							value,
							uuid,
							key,db)){
						output.add(st);
					}
					
				}

			}
			if(rename!=""){
				output.add(
						"INSERT INTO `"+db+"`.`"+TableName.toLowerCase()+"`("+keys+",`renamekey`) values ("+values+",'"+rename+"');"
						);
				
				
			}else{
				output.add(
						"INSERT INTO `"+db+"`.`"+TableName.toLowerCase()+"`("+keys+") values ("+values+");"
						);
				
				}
			
		}
		return output;
		
	}
	/**
	 * Convert Json text into JavaBean , return class definition.
	 * <p>
	 * 
	 * @param JsonString
	 *            and JavaBean JsonString is source text
	 * @param Javabean
	 *            is class name.
	 * @return The value string, or null if empty.
	 */

	public List<ClassDefine> ObjectToJavaBean(String JsonString,
			String Javabean, String className, String packageName) {

		List<ClassDefine> classDefList = new ArrayList<ClassDefine>();

		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert Json source into Map<key,value>
			Map<String, Object> sourceMap = mapper.readValue(JsonString,
					Map.class);

			// iterator map
			for (ClassDefine clazzDef : MapToJavaBean(sourceMap, Javabean,
					className, packageName)) {
				classDefList.add(clazzDef);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classDefList;
	}

	public List<ClassDefine> MapToJavaBean(Object sourceMap, String Javabean,
			String className, String packageName) {
		className = className.substring(0, 1).toUpperCase()
				+ className.substring(1);
		List<ClassDefine> classDefList = new ArrayList<ClassDefine>();

		if (sourceMap instanceof Map) {

			// Create Instant of ClassDefine
			ClassDefine classDef = new ClassDefine();
			// define class attributes
			List<ClassAttributes> atts = new ArrayList<ClassAttributes>();
			// define class methods
			List<ClassMethod> method = new ArrayList<ClassMethod>();
			// define libraries need to import
			List<String> importPackages = new ArrayList<String>();

			// Every Class will use List and ArrayList set default packages
			importPackages.add("import java.util.ArrayList");
			importPackages.add("import java.util.List");

			// define class name
			classDef.setClassName(className);
			// define interface to implements
			if(null!=Javabean && Javabean!=""){
				List<String> implementsClasses = new ArrayList<String>();
				implementsClasses.add(Javabean);
				classDef.setImplementsClass(implementsClasses);
				importPackages.add("import org.albany.edu.model.IModel");
			}
			// define package info
			classDef.setPackageinfo(packageName);
			if (null != Javabean && "" != Javabean) {
				importPackages.add("import org.albany.edu.model." + Javabean);
			}

			Iterator<String> keySet = ((Map<String, Object>) sourceMap)
					.keySet().iterator();

			// define relationship
			ClassAttributes father_id = new ClassAttributes();
			father_id.setName("father_id");
			father_id.setScope("private");
			father_id.setType("String");
			atts.add(father_id);

			// define identity
			ClassAttributes att_id = new ClassAttributes();
			att_id.setName("uuid");
			att_id.setScope("private");
			att_id.setType("String");
			att_id.setDefaultValue("UUID.randomUUID().toString()");
			importPackages.add("import java.util.UUID");
			atts.add(att_id);
			ClassAttributes attForRename = new ClassAttributes();
			attForRename.setName("renamekey");
			attForRename.setScope("private");
			attForRename.setType("String");
			attForRename.setDefaultValue("");
			
			while (keySet.hasNext()) {
				String key = keySet.next().trim();
				Object value = ((Map<String, Object>) sourceMap).get(key);
				ClassAttributes att = new ClassAttributes();
				String scope = "private";

				boolean nameChanged = false;
				
				// check the Class name is good with java
				// Check key's first char is digit
				if (Character.isDigit(key.charAt(0))||key.contains("_") || key.contains("-") || key.contains("/")||key.contains(" ")) {
					nameChanged = true;
				}

				if (nameChanged) {
					att.addAnnotation("@JsonProperty(\"" + key + "\")");
					importPackages
							.add("import com.fasterxml.jackson.annotation.JsonProperty");
					// to keep this key as value of attribute renamekey
					
					attForRename.setDefaultValue(attForRename.getDefaultValue()+"&"+key);
					
					key=className+key.replace("_", "").replace("-", "").replace("/", "").replace(" ", "");
					
				}
				
				
				// System.out.print("key is:" + key);
				// System.out.println("value is:" + value);
				if (value instanceof String) {
					att.setScope(scope);
					att.setType("String");
					att.setName(key);
					atts.add(att);
					// classDef+="private String "+key+";"+rt;
				} else if (value instanceof Integer) {
					att.setScope(scope);
					att.setType("int");
					att.setName(key);
					atts.add(att);
					// classDef+="private int "+key+";"+rt;
				} else if (value instanceof Double) {
					att.setScope(scope);
					att.setType("Double");
					att.setName(key);
					atts.add(att);
					// classDef+="private Double "+key+";"+rt;
				} else if (value instanceof Date) {
					att.setScope(scope);
					att.setType("Date");
					att.setName(key);
					atts.add(att);
					// classDef+="private Boolean "+key+";"+rt;
				}else if (value instanceof Boolean) {
					att.setScope(scope);
					att.setType("Boolean");
					att.setName(key);
					atts.add(att);
					// classDef+="private Boolean "+key+";"+rt;
				} else if (value instanceof Map) {
					att.setScope(scope);
					att.setName(key);
					atts.add(att);
					importPackages.add("import java.util.List");
					key = key.substring(0, 1).toUpperCase()
							+ key.substring(1);

					att.setType(key);
					for (ClassDefine clazzDef : MapToJavaBean(value, null, key,
							packageName)) {
						classDefList.add(clazzDef);
					}

				} else if (value instanceof List) {
					att.setScope(scope);
					att.setName(key);
					atts.add(att);
					importPackages.add("import java.util.List");
					if (((List) value).size() > 0) {

						if (((List) value).get(0) instanceof String) {

							att.setType("List<String>");

						} else if (((List) value).get(0) instanceof Integer) {

							att.setType("List<int>");

						} else if (((List) value).get(0) instanceof Double) {

							att.setType("List<Double>");

						} else if (((List) value).get(0) instanceof Boolean) {

							att.setType("List<Boolean>");

						} else {
							att.setType("List<"
									+ key.substring(0, 1).toUpperCase()
									+ key.substring(1) + ">");
							/////////
							// (List) value).get(0), will be the problem 
							///////
							for(int valueSize = ((List) value).size()-1;valueSize>=0; valueSize--){
								for (ClassDefine clazzDef : MapToJavaBean(
										((List) value).get(valueSize),
										null,
										key.substring(0, 1).toUpperCase()
												+ key.substring(1), packageName)) {
									classDefList.add(clazzDef);
								}
							}
							
						}

					} else {
						att.setType("List");
					}

				} else {
					att.setScope(scope);
					att.setType( key.substring(0, 1).toUpperCase()
							+ key.substring(1) );
					att.setName(key);
					atts.add(att);
				}

			}
			if(attForRename.getDefaultValue()!=""){
				attForRename.setDefaultValue("\""+attForRename.getDefaultValue().replace("null", "")+"\"");
				atts.add(attForRename);
			}
			classDef.setAttrs(atts);
			classDef.setMethods(factory.getListMethod(classDef));
			classDef.setImportPackage(importPackages);
			classDefList.add(classDef);
		}
		return classDefList;

	}

	public Object getSamples(String jsonSource, Object t) {
		// Map<String,T> output = new LinkedHashMap<String,T>();
		ObjectMapper mapper = new ObjectMapper();
		// DeserializationFeature for changing how JSON is read as POJOs:

		// to prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		// TypeReference ref = new TypeReference<T>(){};
		// JavaType type = mapper.getTypeFactory().
		// constructCollectionType(List.class, t.getClass()) ;
		//

		// Type listType = new TypeToken<ConcreteNote>() {
		// }.getType();
		// //Gson gson = new GsonBuilder().registerTypeAdapter(RoleInfo.class,
		// new DatasetTypeAdapter()).create();
		// Gson gson = new Gson();
		// ConcreteNote list = gson.fromJson(jsonSource, listType);

		try {
			// Resource res1 = new
			// FileSystemResource("D://04 ITM//kf5dataFromPythonApi//kf5posts.json");
			// InputStream ins1;
			// ins1 = res1.getInputStream();
			// StringWriter writer = new StringWriter();
			// IOUtils.copy(ins1, writer, "UTF-8");
			// String jsonString2 = writer.toString();
			return mapper.readValue(jsonSource, t.getClass());
			// output.put("0", t);
			// output= mapper.readValue(jsonSource,ref);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		// return output;

	}

	public String findDataByKey(String source, String key, String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> ObjectToMap(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	public void display() {
		System.out.println("init JsonParser");
	}

}
