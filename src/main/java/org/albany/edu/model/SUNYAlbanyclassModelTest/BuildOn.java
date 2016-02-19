package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import org.albany.edu.model.IModel;
import org.albany.edu.model.INoteAndNoteRelationShip;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
public class  BuildOn implements IModel ,INoteAndNoteRelationShip{
private String father_id;
private String uuid=UUID.randomUUID().toString();
@JsonProperty("b47a8a6b-9298-4b1f-b520-65891e9dcdec")
private List<BuildOnb47a8a6b92984b1fb52065891e9dcdec> BuildOnb47a8a6b92984b1fb52065891e9dcdec;
private String renamekey="&b47a8a6b-9298-4b1f-b520-65891e9dcdec";

public  String toString( ){ return "[class name]:'BuildOn'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'List<BuildOnb47a8a6b92984b1fb52065891e9dcdec>'[name]'BuildOnb47a8a6b92984b1fb52065891e9dcdec'[class attributes]:[type]'String'[name]'renamekey'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  List<BuildOnb47a8a6b92984b1fb52065891e9dcdec> getBuildOnb47a8a6b92984b1fb52065891e9dcdec( ){ return BuildOnb47a8a6b92984b1fb52065891e9dcdec;  }
public  void setBuildOnb47a8a6b92984b1fb52065891e9dcdec(  List<BuildOnb47a8a6b92984b1fb52065891e9dcdec> BuildOnb47a8a6b92984b1fb52065891e9dcdec){ this.BuildOnb47a8a6b92984b1fb52065891e9dcdec=BuildOnb47a8a6b92984b1fb52065891e9dcdec;  }
public  String getRenamekey( ){ return renamekey;  }
public  void setRenamekey(  String renamekey){ this.renamekey=renamekey;  }
public   BuildOn( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String renamekeyinstance=" ";
if(null!=getRenamekey()){renamekeyinstance= getRenamekey().replace("\"","\\\"");
};

if(null!=getBuildOnb47a8a6b92984b1fb52065891e9dcdec()){for(BuildOnb47a8a6b92984b1fb52065891e9dcdec obj : getBuildOnb47a8a6b92984b1fb52065891e9dcdec()){
List<String> BuildOnb47a8a6b92984b1fb52065891e9dcdecInsertSQL = obj.getInsertSQL(getUuid());
for(String st : BuildOnb47a8a6b92984b1fb52065891e9dcdecInsertSQL){ output.add(st);};
}};
String thisClassInsertQuery= "Insert into `BuildOn` ( father_id, `uuid`, `renamekey`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+renamekeyinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}