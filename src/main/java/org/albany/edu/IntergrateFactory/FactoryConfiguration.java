package org.albany.edu.IntergrateFactory;

import java.sql.SQLException;
import java.util.UUID;

import org.albany.edu.IntergrateFactory.DataHandler.IDatabase;
import org.albany.edu.IntergrateFactory.DataHandler.RationalDatabase;

/**
 * The instant of this class provide personal info to create a factory.
 *
 * 
 * @author Yizhen Chen
 * @version 2015-8-17
 * 
 */
public class FactoryConfiguration {

	private String uuid =UUID.randomUUID().toString();
	private String userName;
	private String password;
	private String email;
	private String Org;
	private String firstName;
	private String lastName;
	private String projectName;
	private String db;

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	private String note_table;
	private String user_table;
	private String user_group_table;
	private String note_group_table;
	private String permission_table;
	private String note_ralation_with_note;
	private String note_ralation_with_note_group;
	private String note_group_relation_with_note_group;
	private String user_relation_with_user;
	private String user_relation_with_user_group;
	private String user_group_relation_with_user_group;

	public String databaseSchema() {

		return "CREATE SCHEMA `" + getDataBaseName() + "` ;";
	}

	public String ifExistsDatabaseSchema(){
		return "DROP DATABASE IF EXISTS `" + getDataBaseName() + "`;";
	}
	public RationalDatabase swichToThisHost(RationalDatabase database) throws SQLException{
		 database.getJdbcTemplate().getDataSource().getConnection().setCatalog(getDataBaseName());
		 return database;
	}

	public String getInSertSQL(){
		return "INSERT INTO `project_info` (`"
				+ "userName`,"
				+ " `password`,"
				+ " `email`, "
				+ "`Org`, "
				+ "`firstName`, "
				+ "`lastName`,"
				+ " `projectName`,"
				+"`posttime`,"
				+"`uuid`"
				+ ") "
				+ "VALUES "
				+ "("
				+ "'"+this.userName+"',"
				+ " '"+this.password+"',"
				+ " '"+this.email+"',"
				+ " '"+this.Org+"',"
				+ " '"+this.firstName+"',"
				+ " '"+this.lastName+"',"
				+ " '"+this.projectName+"',"
				+ " '"+System.currentTimeMillis()+"',"
					+ " '"+this.uuid+"');";

	}
	
	public String getAppendNoteTable(){
		return "update  project_info set note_table=concat(note_table, '"+this.note_table+"') where uuid = '"+uuid+"'";
	}
	
	public String getAppendNoteRalationWithNoteGroup(){
		return "update  project_info set note_ralation_with_note_group=concat(note_ralation_with_note_group, '"+this.note_ralation_with_note_group+"') where uuid = '"+uuid+"'";
	}
	
	public String getAppendNoteGroupRalationWithNoteGroup(){
		return "update  project_info set note_ralation_with_note_group=concat(note_group_ralation_with_note_group, '"+this.note_ralation_with_note_group+"') where uuid = '"+uuid+"'";
	}
	
	
	public String getAppendNoteGroupTable(){
		return "update  project_info set note_group_table=concat(note_group_table, '"+this.note_group_table+"') where uuid = '"+uuid+"'";
	}
	
	public String getAppendNoteRalationWithNote(){
		return "update  project_info set note_ralation_with_note=concat(note_ralation_with_note, '"+this.note_ralation_with_note+"') where uuid = '"+uuid+"'";
	}
	
	public String getAppendUserGroupRelationWithUserGroup(){
		return "update  project_info set user_group_relation_with_user_group=concat(user_group_relation_with_user_group, '"+this.user_group_relation_with_user_group+"') where uuid = '"+uuid+"'";
	}

	public String getAppendUserRelationWithUserGroup(){
		return "update  project_info set user_relation_with_user_group=concat(user_relation_with_user_group, '"+this.user_relation_with_user_group+"') where uuid = '"+uuid+"'";
	}
	
	public String getAppendUserRelationWithUser(){
		return "update  project_info set user_relation_with_user=concat(user_relation_with_user, '"+this.user_relation_with_user+"') where uuid = '"+uuid+"'";
	}
	
	
	public String getAppendUserTable(){
		return "update  project_info set user_table=concat(user_table, '"+this.user_table+"') where uuid = '"+uuid+"'";
	}
	
	
	public String getAppendUserGroupTable(){
		return "update  project_info set user_group_table=concat(user_group_table, '"+this.user_group_table+"') where uuid = '"+uuid+"'";
	}
	
	
	
	public String getAddNoteTable(){
		return "update  project_info set note_table= '"+this.note_table+"' where uuid = '"+uuid+"'";
	}
	
	public String getAddNoteRalationWithNoteGroup(){
		return "update  project_info set note_ralation_with_note_group='"+this.note_ralation_with_note_group+"' where uuid = '"+uuid+"'";
	}
	
	public String getAddNoteGroupRalationWithNoteGroup(){
		return "update  project_info set note_ralation_with_note_group= '"+this.note_ralation_with_note_group+"' where uuid = '"+uuid+"'";
	}
	
	
	public String getAddNoteGroupTable(){
		return "update  project_info set note_group_table='"+this.note_group_table+"' where uuid = '"+uuid+"'";
	}
	
	public String getAddNoteRalationWithNote(){
		return "update  project_info set note_ralation_with_note= '"+this.note_ralation_with_note+"' where uuid = '"+uuid+"'";
	}
	
	public String getAddUserGroupRelationWithUserGroup(){
		return "update  project_info set user_group_relation_with_user_group= '"+this.user_group_relation_with_user_group+"' where uuid = '"+uuid+"'";
	}

	public String getAddUserRelationWithUserGroup(){
		return "update  project_info set user_relation_with_user_group='"+this.user_relation_with_user_group+"' where uuid = '"+uuid+"'";
	}
	
	public String getAddUserRelationWithUser(){
		return "update  project_info set user_relation_with_user='"+this.user_relation_with_user+"' where uuid = '"+uuid+"'";
	}
	
	
	public String getAddUserTable(){
		return "update  project_info set user_table= '"+this.user_table+"' where uuid = '"+uuid+"'";
	}
	
	
	public String getAddUserGroupTable(){
		return "update  project_info set user_group_table='"+this.user_group_table+"' where uuid = '"+uuid+"'";
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrg() {
		return Org;
	}

	public void setOrg(String org) {
		Org = org;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public void display() {
		System.out.println("[Factory Configuration info]:");
		System.out.println("userName :" + userName);
		System.out.println("email:" + email);
		System.out.println("Org:" + Org);
		System.out.println("firstName:" + firstName);
		System.out.println("lastName:" + lastName);
		System.out.println("projectName:" + projectName);
	}

	public String getNote_table() {
		return note_table;
	}

	public void setNote_table(String note_table) {
		this.note_table = note_table;
	}

	public String getUser_table() {
		return user_table;
	}

	public void setUser_table(String user_table) {
		this.user_table = user_table;
	}

	public String getUser_group_table() {
		return user_group_table;
	}

	public void setUser_group_table(String user_group_table) {
		this.user_group_table = user_group_table;
	}

	public String getNote_group_table() {
		return note_group_table;
	}

	public void setNote_group_table(String note_group_table) {
		this.note_group_table = note_group_table;
	}

	public String getPermission_table() {
		return permission_table;
	}

	public void setPermission_table(String permission_table) {
		this.permission_table = permission_table;
	}

	public String getNote_ralation_with_note() {
		return note_ralation_with_note;
	}

	public void setNote_ralation_with_note(String note_ralation_with_note) {
		this.note_ralation_with_note = note_ralation_with_note;
	}

	public String getNote_ralation_with_note_group() {
		return note_ralation_with_note_group;
	}

	public void setNote_ralation_with_note_group(
			String note_ralation_with_note_group) {
		this.note_ralation_with_note_group = note_ralation_with_note_group;
	}

	public String getNote_group_relation_with_note_group() {
		return note_group_relation_with_note_group;
	}

	public void setNote_group_relation_with_note_group(
			String note_group_relation_with_note_group) {
		this.note_group_relation_with_note_group = note_group_relation_with_note_group;
	}

	public String getUser_relation_with_user() {
		return user_relation_with_user;
	}

	public void setUser_relation_with_user(String user_relation_with_user) {
		this.user_relation_with_user = user_relation_with_user;
	}

	public String getUser_relation_with_user_group() {
		return user_relation_with_user_group;
	}

	public void setUser_relation_with_user_group(
			String user_relation_with_user_group) {
		this.user_relation_with_user_group = user_relation_with_user_group;
	}

	public String getUser_group_relation_with_user_group() {
		return user_group_relation_with_user_group;
	}

	public void setUser_group_relation_with_user_group(
			String user_group_relation_with_user_group) {
		this.user_group_relation_with_user_group = user_group_relation_with_user_group;
	}

	public String getDataBaseName() {
		return Org + "_" + db.trim().replace(" ","_").replace("-","_") ;
	}
	
}
