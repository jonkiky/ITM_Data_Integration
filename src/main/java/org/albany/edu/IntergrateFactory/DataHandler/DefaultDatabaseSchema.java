package org.albany.edu.IntergrateFactory.DataHandler;

import java.util.ArrayList;
import java.util.List;

public class DefaultDatabaseSchema {

	//public List<String> defaultSchema = new ArrayList<String>();
	
	private List<String> defaultSchema = new ArrayList<String>();
	private String mainhost="data_integration_2015";
	
	
	
	public String getMainhost() {
		return mainhost;
	}

	public void setMainhost(String mainhost) {
		this.mainhost = mainhost;
	}

	public String swichToThisHost(){
		return "use "+mainhost+" ;";
	}
	

	String class_structure_define =
			"CREATE TABLE `class_structure_define` ("
			+"`id` int(11) NOT NULL AUTO_INCREMENT,"
			+"`pid` int(11) DEFAULT NULL,"
			+"`class_name` varchar(100) DEFAULT NULL,"
			+"`attributes` varchar(45) DEFAULT NULL,"
			+"`type` varchar(45) DEFAULT NULL,"
			+"PRIMARY KEY (`id`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	String note_group_table =
			"CREATE TABLE `note_group_table` ("
					+"  `id` int(11) NOT NULL AUTO_INCREMENT,"
					+" `note_group_table_name` varchar(45) DEFAULT NULL,"
					+"  PRIMARY KEY (`id`)"
					+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	
	String user_group_table =
			"CREATE TABLE `user_group_table` ("
			+"  `id` int(11) NOT NULL AUTO_INCREMENT,"
			+"  `user_group_table_name` varchar(45) DEFAULT NULL,"
			+"  PRIMARY KEY (`id`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


	String permission_of_note=
			"CREATE TABLE `permission_of_note` ("
			+"		  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Same with permission_abstract_model''s primary id.',"
			+"		  `note_id` int(11) DEFAULT NULL,"
			+"		  `user_id` int(11) DEFAULT NULL,"
			+"		  `permission_role_id` int(11) DEFAULT NULL COMMENT 'foreign key with permission_role',"
			+"		  PRIMARY KEY (`id`)"
			+"		) ENGINE=InnoDB DEFAULT CHARSET=utf8;";


	
	String permission_number =
	"CREATE TABLE `permission_number` ("
			+"	  `id` int(11) NOT NULL AUTO_INCREMENT,"
			+"  `permission_number` int(11) DEFAULT NULL,"
			+"  `desc` varchar(45) DEFAULT NULL,"
			+"  PRIMARY KEY (`id`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	
	String permission_of_note_group =
	"CREATE TABLE `permission_of_note_group` ("
			+"  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Same with permission_abstract_model''s primary id.',"
			+"  `note_group_id` int(11) DEFAULT NULL,"
			+"  `user_id` int(11) DEFAULT NULL,"
			+"  `permission_role_id` int(11) DEFAULT NULL COMMENT 'foreign key with permission_role',"
			+"  PRIMARY KEY (`id`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	
	
	String database_table =
	"CREATE TABLE `database_table` ("
			+"  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Same with permission_abstract_model''s primary id.',"
			+"  `table_name` varchar(100) DEFAULT NULL,"
			+"  PRIMARY KEY (`id`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


	public DefaultDatabaseSchema(){
		defaultSchema.add(class_structure_define);
		defaultSchema.add(note_group_table);
		defaultSchema.add(user_group_table);
		defaultSchema.add(permission_of_note);
		defaultSchema.add(permission_number);
		defaultSchema.add(permission_of_note_group);
		defaultSchema.add(database_table);
	}


	
	public List<String> getDefaultSchema() {
		return defaultSchema;
	}


	public void setDefaultSchema(List<String> defaultSchema) {
		this.defaultSchema = defaultSchema;
	}

	
}
 