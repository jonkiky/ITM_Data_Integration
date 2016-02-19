package org.albany.edu.IntergrateFactory.DataHandler;

import java.util.List;

import org.albany.edu.IntergrateFactory.classDef.ClassAttributes;
import org.albany.edu.IntergrateFactory.classDef.ClassDefine;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class RationalDatabase implements IDatabase {

	private JdbcTemplate jdbcTemplate;

	// without Autowired
	public RationalDatabase() {
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/data_integration_2015");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		JdbcTemplate jb = new JdbcTemplate();
		jb.setDataSource(dataSource);
		this.jdbcTemplate=jb;
	}

	public String ClassDefineToSchema(ClassDefine classDef){
		String content = "CREATE TABLE if not exists`"+ classDef.getClassName().toLowerCase()+"` (";
		content+="`id` INT NOT NULL AUTO_INCREMENT,  ";
		List<ClassAttributes> attrs = classDef.getAttrs();
		for (ClassAttributes att : attrs) {
			if(att.getType().startsWith("List")){
				if(!att.getType().equals("List<String>")
						&&!att.getType().equals("List<int>")
						&&!att.getType().equals("List<Double>")
						&&!att.getType().equals("List<Boolean>")
				  ){
					continue;
				 }
			}else{
				if(!att.getType().equals("String")
						&&!att.getType().equals("int")
						&&!att.getType().equals("Double")
						&&!att.getType().equals("Boolean")
				  ){
					continue;
				 }
			 }
			if (att.getType().equals("String")) {
				if(att.getName()=="body"){
					content+="`"+att.getName().toLowerCase()+"`  LONGBLOB NULL,  ";
				}else if(att.getName()=="text"){
					content+="`"+att.getName().toLowerCase()+"`  LONGBLOB NULL,  ";
				}else if(att.getName()=="renamekey"){
					content+="`"+att.getName().toLowerCase()+"`  Text NULL,  ";
				}else{
					content+="`"+att.getName().toLowerCase()+"`  VARCHAR(800) NULL,  ";
				}
				continue;
			}
			
			if (att.getType().equals("int")) {
				content+="`"+att.getName().toLowerCase()+"`  INT(16) NULL,  ";
				continue;
			}
			if (att.getType().equals("Double")) {
				content+="`"+att.getName().toLowerCase()+"`  Double NULL,  ";
				continue;
			}
			if (att.getType().equals("Boolean")) {
				content+="`"+att.getName().toLowerCase()+"`  VARCHAR(5) NULL,  ";
				continue;
			}
			content+="`"+att.getName().toLowerCase()+"`  VARCHAR(800) NULL,  ";
		}
		content+="  PRIMARY KEY (`id`));";
		return content;
	}

	public void execute(String action) {
		jdbcTemplate.execute(action);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
