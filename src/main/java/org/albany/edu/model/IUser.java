package org.albany.edu.model;

import java.util.List;

public interface IUser {
	public String getUuid() ;
	public List<String> getInsertSQL(String fatherId);
}
