package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String guid;
private Boolean locked;
private String created;
private String title;
private String modified;
private String sectionGuid;
private List<LinkedViewReferences> linkedViewReferences;
private List<Authors> authors;
private Boolean active;
private SettingInfo settingInfo;
private String primaryAuthorId;
private Boolean published;

public  String toString( ){ return "[class name]:'NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'guid'[class attributes]:[type]'Boolean'[name]'locked'[class attributes]:[type]'String'[name]'created'[class attributes]:[type]'String'[name]'title'[class attributes]:[type]'String'[name]'modified'[class attributes]:[type]'String'[name]'sectionGuid'[class attributes]:[type]'List<LinkedViewReferences>'[name]'linkedViewReferences'[class attributes]:[type]'List<Authors>'[name]'authors'[class attributes]:[type]'Boolean'[name]'active'[class attributes]:[type]'SettingInfo'[name]'settingInfo'[class attributes]:[type]'String'[name]'primaryAuthorId'[class attributes]:[type]'Boolean'[name]'published'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getGuid( ){ return guid;  }
public  void setGuid(  String guid){ this.guid=guid;  }
public  Boolean getLocked( ){ return locked;  }
public  void setLocked(  Boolean locked){ this.locked=locked;  }
public  String getCreated( ){ return created;  }
public  void setCreated(  String created){ this.created=created;  }
public  String getTitle( ){ return title;  }
public  void setTitle(  String title){ this.title=title;  }
public  String getModified( ){ return modified;  }
public  void setModified(  String modified){ this.modified=modified;  }
public  String getSectionGuid( ){ return sectionGuid;  }
public  void setSectionGuid(  String sectionGuid){ this.sectionGuid=sectionGuid;  }
public  List<LinkedViewReferences> getLinkedViewReferences( ){ return linkedViewReferences;  }
public  void setLinkedViewReferences(  List<LinkedViewReferences> linkedViewReferences){ this.linkedViewReferences=linkedViewReferences;  }
public  List<Authors> getAuthors( ){ return authors;  }
public  void setAuthors(  List<Authors> authors){ this.authors=authors;  }
public  Boolean getActive( ){ return active;  }
public  void setActive(  Boolean active){ this.active=active;  }
public  SettingInfo getSettingInfo( ){ return settingInfo;  }
public  void setSettingInfo(  SettingInfo settingInfo){ this.settingInfo=settingInfo;  }
public  String getPrimaryAuthorId( ){ return primaryAuthorId;  }
public  void setPrimaryAuthorId(  String primaryAuthorId){ this.primaryAuthorId=primaryAuthorId;  }
public  Boolean getPublished( ){ return published;  }
public  void setPublished(  Boolean published){ this.published=published;  }
public   NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String guidinstance=" ";
if(null!=getGuid()){guidinstance= getGuid().replace("\"","\\\"");
};
String createdinstance=" ";
if(null!=getCreated()){createdinstance= getCreated().replace("\"","\\\"");
};
String titleinstance=" ";
if(null!=getTitle()){titleinstance= getTitle().replace("\"","\\\"");
};
String modifiedinstance=" ";
if(null!=getModified()){modifiedinstance= getModified().replace("\"","\\\"");
};
String sectionGuidinstance=" ";
if(null!=getSectionGuid()){sectionGuidinstance= getSectionGuid().replace("\"","\\\"");
};
String primaryAuthorIdinstance=" ";
if(null!=getPrimaryAuthorId()){primaryAuthorIdinstance= getPrimaryAuthorId().replace("\"","\\\"");
};

if(null!=getLinkedViewReferences()){for(LinkedViewReferences obj : getLinkedViewReferences()){
List<String> linkedViewReferencesInsertSQL = obj.getInsertSQL(getUuid());
for(String st : linkedViewReferencesInsertSQL){ output.add(st);};
}};
if(null!=getAuthors()){for(Authors obj : getAuthors()){
List<String> authorsInsertSQL = obj.getInsertSQL(getUuid());
for(String st : authorsInsertSQL){ output.add(st);};
}};
List<String> settingInfoInsertSQL =getSettingInfo().getInsertSQL(getUuid());
for(String st : settingInfoInsertSQL){ output.add(st);};
String thisClassInsertQuery= "Insert into `NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025` ( father_id, `uuid`, `guid`, `locked`, `created`, `title`, `modified`, `sectionGuid`, `active`, `primaryAuthorId`, `published`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+guidinstance +"\","+getLocked()+",\""+createdinstance +"\",\""+titleinstance +"\",\""+modifiedinstance +"\",\""+sectionGuidinstance +"\","+getActive()+",\""+primaryAuthorIdinstance +"\","+getPublished()+");";
output.add(thisClassInsertQuery);
return output;  }}