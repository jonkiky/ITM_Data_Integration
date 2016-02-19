package org.albany.edu.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.albany.edu.IntergrateFactory.JsonFactory;
import org.albany.edu.IntergrateFactory.DataHandler.RationalDatabase;
import org.albany.edu.IntergrateFactory.Interface.IReader;
import org.albany.edu.IntergrateFactory.Interface.Imp.JsonParser;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;

public class ModelBuilderByJson implements IModelBuilder {

	private JsonFactory factory = new JsonFactory();

	private Map<String, ClassDefine> notes;
	private Map<String, ClassDefine> noteGroup;
	private Map<String, ClassDefine> users;
	private Map<String, ClassDefine> userGroup;
	private Map<String, ClassDefine> noteWithNote;
	private Map<String, ClassDefine> noteWithNoteGroup;
	private Map<String, ClassDefine> noteGroupWithNoteGroup;
	private Map<String, ClassDefine> userWithUser;
	private Map<String, ClassDefine> userWithUserGroup;
	private Map<String, ClassDefine> userGroupWithuserGroup;

	public void build(IReader reader, String className, String interfaceName) {
		factory.setReader(reader);
		String source = factory.collectionDate().getMESSAGE();
		factory.MapToModel(source, interfaceName, className);
		switch (interfaceName) {
		case "INote":
			this.notes = factory.getBuilder().getUncheckModel();
			break;
		case "INoteAndNoteGroupRelationShip":
			this.noteWithNoteGroup = factory.getBuilder().getUncheckModel();
			break;
		case "INoteAndNoteRelationShip":
			this.noteWithNote = factory.getBuilder().getUncheckModel();
			break;
		case "INoteGroup":
			this.noteGroup = factory.getBuilder().getUncheckModel();
			break;
		case "INoteGroupAndNoteGroupRelationShip":
			this.noteGroupWithNoteGroup = factory.getBuilder()
					.getUncheckModel();
			break;
		case "IUser":
			this.users = factory.getBuilder().getUncheckModel();
			break;
		case "IUserAndUserGroupRelationShip":
			this.userWithUserGroup = factory.getBuilder().getUncheckModel();
			break;
		case "IUserAndUserRelationShip":
			this.userWithUser = factory.getBuilder().getUncheckModel();
			break;
		case "IUserGroup":
			this.userGroup = factory.getBuilder().getUncheckModel();
			break;
		case "IUserGroupAndUserGroupRelationShip":
			this.userGroupWithuserGroup = factory.getBuilder()
					.getUncheckModel();
			break;
		}

	}

	public void check(boolean bool) {
		this.factory.CheckModel(bool);
	}

	public void check(boolean bool, boolean isAppend) {
		this.factory.CheckModel(bool, isAppend);
	}

//	public void StoreData(Object o) {
//		String source = factory.collectionDate().getMESSAGE();
//		IModel note = (IModel) factory.getSamples(source, o);
//		factory.storeInstanceIntoDatabase(note);
//		factory.empty();
//	}
	private RationalDatabase dataOperator = new RationalDatabase();
	public void StoreData(String tableName) {
		String source = factory.collectionDate().getMESSAGE();
		JsonParser p=(JsonParser)this.factory.getBuilder().getParser();
		try {
			Map<String, Object> objs=p.ObjectToMap(source, tableName);
			List<String> SQLs=p.ObjectMapToInsertString(objs, "", tableName,this.factory.getConfig().getDataBaseName());
			for(String sql : SQLs){
				System.out.println(sql);
				dataOperator.execute(sql);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.empty();
	}

	// Setter and Getter

	public Map<String, ClassDefine> getNotes() {
		return this.notes;
	}

	public Map<String, ClassDefine> getNoteGroup() {
		return noteGroup;
	}

	public void setNoteGroup(Map<String, ClassDefine> noteGroup) {
		this.noteGroup = noteGroup;
	}

	public Map<String, ClassDefine> getUsers() {
		return users;
	}

	public void setUsers(Map<String, ClassDefine> users) {
		this.users = users;
	}

	public Map<String, ClassDefine> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(Map<String, ClassDefine> userGroup) {
		this.userGroup = userGroup;
	}

	public Map<String, ClassDefine> getNoteWithNote() {
		return noteWithNote;
	}

	public void setNoteWithNote(Map<String, ClassDefine> noteWithNote) {
		this.noteWithNote = noteWithNote;
	}

	public Map<String, ClassDefine> getNoteWithNoteGroup() {
		return noteWithNoteGroup;
	}

	public void setNoteWithNoteGroup(Map<String, ClassDefine> noteWithNoteGroup) {
		this.noteWithNoteGroup = noteWithNoteGroup;
	}

	public Map<String, ClassDefine> getNoteGroupWithNoteGroup() {
		return noteGroupWithNoteGroup;
	}

	public void setNoteGroupWithNoteGroup(
			Map<String, ClassDefine> noteGroupWithNoteGroup) {
		this.noteGroupWithNoteGroup = noteGroupWithNoteGroup;
	}

	public Map<String, ClassDefine> getUserWithUser() {
		return userWithUser;
	}

	public void setUserWithUser(Map<String, ClassDefine> userWithUser) {
		this.userWithUser = userWithUser;
	}

	public Map<String, ClassDefine> getUserWithUserGroup() {
		return userWithUserGroup;
	}

	public void setUserWithUserGroup(Map<String, ClassDefine> userWithUserGroup) {
		this.userWithUserGroup = userWithUserGroup;
	}

	public Map<String, ClassDefine> getUserGroupWithuserGroup() {
		return userGroupWithuserGroup;
	}

	public void setUserGroupWithuserGroup(
			Map<String, ClassDefine> userGroupWithuserGroup) {
		this.userGroupWithuserGroup = userGroupWithuserGroup;
	}

	public void setNotes(Map<String, ClassDefine> notes) {
		this.notes = notes;
	}

	public JsonFactory getFactory() {
		return factory;
	}

	public void setFactory(JsonFactory factory) {
		this.factory = factory;
	}

}
