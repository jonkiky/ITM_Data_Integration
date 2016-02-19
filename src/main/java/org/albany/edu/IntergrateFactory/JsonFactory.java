package org.albany.edu.IntergrateFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.albany.edu.IntergrateFactory.DataHandler.DefaultDatabaseSchema;
import org.albany.edu.IntergrateFactory.DataHandler.IDatabase;
import org.albany.edu.IntergrateFactory.DataHandler.RationalDatabase;
import org.albany.edu.IntergrateFactory.Interface.IFactory;
import org.albany.edu.IntergrateFactory.Interface.IReader;
import org.albany.edu.IntergrateFactory.Interface.Imp.ConcreteClassBuilder;
import org.albany.edu.IntergrateFactory.Interface.Imp.JsonParser;
import org.albany.edu.IntergrateFactory.Interface.Imp.JsonValidate;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;
import org.albany.edu.model.IModel;
import org.python.icu.util.BytesTrie.Iterator;

/**
 * This interface is for validate data source is under well define or not.
 * 
 * @author Yizhen Chen
 * @version 2015-8-18
 * 
 */
public class JsonFactory implements IFactory {

	private RationalDatabase dataOperator = new RationalDatabase();
	private IReader reader;
	private String dataSource;

	private FactoryConfiguration config;
	private ConcreteClassBuilder builder;

	private String model;

	public void empty() {
		builder.emptyBuilder();
	}

	public String initFactoryInDB() {
		if (null != this.config) {

			DefaultDatabaseSchema dds = new DefaultDatabaseSchema();
			// dataOperator.execute(dds.swichToThisHost());

			dataOperator.execute(config.getInSertSQL());

			// If database already exits replace the new one.
			dataOperator.execute(config.ifExistsDatabaseSchema());

			// Create Database;
			dataOperator.execute(config.databaseSchema());

			// Create Default tables;
			for (String query : dds.getDefaultSchema()) {
				query = query.substring(0, 12) + " `"
						+ config.getDataBaseName() + "`."
						+ query.substring(12, query.length() - 1);
				dataOperator.execute(query);
			}

		}
		return null;
	}

	public void storeInstanceIntoDatabase(IModel model) {
		List<String> insertQuery = model.getInsertSQL(" ");
		for (String query : insertQuery) {
			query = query.substring(0, 12) + " `" + config.getDataBaseName()
					+ "`." + query.substring(12, query.length());
			System.out.println(query);
			dataOperator.execute(query);
		}
	}

	/**
	 * Initialize factory by initialize ClassBuilderByJson.
	 * <p>
	 * 
	 */
	public JsonFactory() {
		this.builder = new ConcreteClassBuilder();
		this.builder.setParser(new JsonParser());
		this.builder.setValidate(new JsonValidate());

	}

	/**
	 * Retrieve data from data source
	 * <p>
	 * 
	 * @param IReader
	 *            reader is Interface which define how to get Data
	 * @return Message
	 */
	public Message collectionDate() {
		if (reader.getData().getSTATUS() == 200) {
			this.dataSource = reader.getData().getMESSAGE();
		}
		return reader.getData();
	}

	/**
	 * Convert source text into JavaBean , return class definition.
	 * <p>
	 * 
	 * @param source
	 *            source text
	 * @param model
	 *            which class it map to
	 * @param className
	 * @return Message
	 */
	public Message MapToModel(String source, String model, String className) {
		// Use project name and org name as package name
		String packageInfo =config.getOrg() + config.getProjectName();

		// assembleline to convert object into javabeans
		List<ClassDefine> classDefList = this.builder.ObjectToJavaBean(source,
				model, className, packageInfo);

		this.model = model;
		// return a message
		Message msg = new Message();
		if (classDefList.isEmpty()) {
			msg.setSTATUS(200);
			msg.setMESSAGE("No javabean is built.");
		} else {
			msg.setSTATUS(200);
			msg.setMESSAGE(classDefList.toString());
		}

		return msg;

	}

	/**
	 * Change the status of Model is under check or pass the check. If check
	 * pass, means model is good and ready for creating instants. If check
	 * failed , means model needs refine, current uncheck model will be
	 * destroyed.
	 * <p>
	 * 
	 * @param flag
	 *            true -> pass test, false -> test failed
	 */

	public void CheckModel(boolean flag) {
		this.builder.CheckModel(flag);
		//this.builder.LoadJavaBeanToJVM();
		createDatabaseTables();
		createNewClassIntoModel();
				
	}

	public void CheckModel(boolean flag, boolean isAppend) {
		this.builder.CheckModel(flag);
		this.builder.LoadJavaBeanToJVM();
		createDatabaseTables();
		if (isAppend) {
			appendNewClassIntoModel();
		} else {
			createNewClassIntoModel();
		}
	}



	public void createNewClassIntoModel() {
		if (this.model.equals("INote")) {
			dataOperator.execute(config.getAddNoteTable());
		} else if (this.model.equals("INoteGroup")) {
			dataOperator.execute(config.getAddNoteGroupTable());
		} else if (this.model.equals("IUser")) {
			dataOperator.execute(config.getAddUserTable());
		} else if (this.model.equals("IUserGroup")) {
			dataOperator.execute(config.getAddUserGroupTable());
		} else if (this.model.equals("IUserAndUserGrupRelationShip")) {
			dataOperator.execute(config.getAddUserRelationWithUserGroup());
		} else if (this.model.equals("IUserAndUserRelationShip")) {
			dataOperator.execute(config.getAddUserRelationWithUser());
		} else if (this.model.equals("IUserGroupAndUserGroupRelationShip")) {
			dataOperator.execute(config.getAddUserGroupRelationWithUserGroup());
		} else if (this.model.equals("INoteAndNoteGroupRelationShip")) {
			dataOperator.execute(config.getAddNoteRalationWithNoteGroup());
		} else if (this.model.equals("INoteAndNoteRelationShip")) {
			dataOperator.execute(config.getAddNoteRalationWithNote());
		} else if (this.model.equals("INoteGroupAndNoteGroupRelationShip")) {
			dataOperator.execute(config.getAddNoteRalationWithNoteGroup());
		}

	}

	public void appendNewClassIntoModel() {
		if (this.model.equals("INote")) {
			dataOperator.execute(config.getAppendNoteTable());
		} else if (this.model.equals("INoteGroup")) {
			dataOperator.execute(config.getAppendNoteGroupTable());
		} else if (this.model.equals("IUser")) {
			dataOperator.execute(config.getAppendUserGroupTable());
		} else if (this.model.equals("IUserGroup")) {
			dataOperator.execute(config.getAppendUserGroupTable());
		} else if (this.model.equals("IUserAndUserGrupRelationShip")) {
			dataOperator.execute(config.getAppendUserRelationWithUserGroup());
		} else if (this.model.equals("IUserAndUserRelationShip")) {
			dataOperator.execute(config.getAppendUserRelationWithUser());
		} else if (this.model.equals("IUserGroupAndUserGroupRelationShip")) {
			dataOperator.execute(config
					.getAppendUserGroupRelationWithUserGroup());
		} else if (this.model.equals("INoteAndNoteGroupRelationShip")) {
			dataOperator.execute(config.getAppendNoteRalationWithNoteGroup());
		} else if (this.model.equals("INoteAndNoteRelationShip")) {
			dataOperator.execute(config.getAppendNoteRalationWithNote());
		} else if (this.model.equals("INoteGroupAndNoteGroupRelationShip")) {
			dataOperator.execute(config
					.getAppendNoteGroupRalationWithNoteGroup());
		}

	}

	public void createDatabaseTables() {
		Map<String, ClassDefine> clazzDefs = this.builder.getCheckModel();
		ClassDefine clazz = new ClassDefine();
		String query = "";
		for (String key : clazzDefs.keySet()) {
			clazz = clazzDefs.get(key);
			query = dataOperator.ClassDefineToSchema(clazz);
			query = query.substring(0, 26) + " `" + config.getDataBaseName()
					+ "`." + query.substring(26, query.length() - 1);

			System.out.println(query);
			dataOperator.execute(query);
			
			String sql ="insert into "+" `" + config.getDataBaseName()
					+ "`.database_table (table_name) values('"+clazz.getClassName()+"')"; 
			
			dataOperator.execute(sql);
		}
	}

	public Object getSamples(String jsonSource, Object t) {
		return this.builder.getSamples(jsonSource, t);
	}

	public Message getDataByModel() {
		return null;
	}

	public IReader getReader() {
		return reader;
	}

	public void setReader(IReader reader) {
		this.reader = reader;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public FactoryConfiguration getConfig() {
		return config;
	}

	public void setConfig(FactoryConfiguration config) {
		this.config = config;
	}

	public ConcreteClassBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(ConcreteClassBuilder builder) {
		this.builder = builder;
	}

	public void display() {
		System.out.println("Init Json Factory");
		config.display();
		builder.display();
		System.out.println("JsonFactory is ready");

	}

	public JsonParser getParser() {
		return (JsonParser) this.builder.getParser();
	}

}
