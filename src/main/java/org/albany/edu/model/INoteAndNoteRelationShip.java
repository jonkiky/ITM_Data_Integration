package org.albany.edu.model;

import java.util.List;

public interface INoteAndNoteRelationShip {
	public String getUuid() ;
	public List<String> getInsertSQL(String fatherId);
}
