package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import org.albany.edu.model.IModel;
import org.albany.edu.model.INoteAndNoteGroupRelationShip;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
public class  NoteAndNoteGroupRelationShip implements IModel ,INoteAndNoteGroupRelationShip{
private String father_id;
private String uuid=UUID.randomUUID().toString();
@JsonProperty("018e9938-f197-405b-802c-265337786025")
private NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025 NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025;
private String renamekey="&018e9938-f197-405b-802c-265337786025";

public  String toString( ){ return "[class name]:'NoteAndNoteGroupRelationShip'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025'[name]'NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025'[class attributes]:[type]'String'[name]'renamekey'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025 getNoteAndNoteGroupRelationShip018e9938f197405b802c265337786025( ){ return NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025;  }
public  void setNoteAndNoteGroupRelationShip018e9938f197405b802c265337786025(  NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025 NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025){ this.NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025=NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025;  }
public  String getRenamekey( ){ return renamekey;  }
public  void setRenamekey(  String renamekey){ this.renamekey=renamekey;  }
public   NoteAndNoteGroupRelationShip( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String renamekeyinstance=" ";
if(null!=getRenamekey()){renamekeyinstance= getRenamekey().replace("\"","\\\"");
};

List<String> NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025InsertSQL =getNoteAndNoteGroupRelationShip018e9938f197405b802c265337786025().getInsertSQL(getUuid());
for(String st : NoteAndNoteGroupRelationShip018e9938f197405b802c265337786025InsertSQL){ output.add(st);};
String thisClassInsertQuery= "Insert into `NoteAndNoteGroupRelationShip` ( father_id, `uuid`, `renamekey`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+renamekeyinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}