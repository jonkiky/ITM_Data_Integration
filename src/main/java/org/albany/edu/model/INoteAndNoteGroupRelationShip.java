package org.albany.edu.model;

import java.util.List;

public interface INoteAndNoteGroupRelationShip {
	public String getUuid() ;
	public List<String> getInsertSQL(String fatherId);
}
