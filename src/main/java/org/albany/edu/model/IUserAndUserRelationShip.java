package org.albany.edu.model;

import java.util.List;

public interface IUserAndUserRelationShip {
	public String getUuid() ;
	public List<String> getInsertSQL(String fatherId);
}
