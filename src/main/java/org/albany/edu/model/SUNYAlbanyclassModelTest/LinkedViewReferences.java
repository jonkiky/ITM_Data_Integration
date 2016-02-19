package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  LinkedViewReferences
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String guid;
private Location location;
private String viewReferenceId;

public  String toString( ){ return "[class name]:'LinkedViewReferences'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'guid'[class attributes]:[type]'Location'[name]'location'[class attributes]:[type]'String'[name]'viewReferenceId'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getGuid( ){ return guid;  }
public  void setGuid(  String guid){ this.guid=guid;  }
public  Location getLocation( ){ return location;  }
public  void setLocation(  Location location){ this.location=location;  }
public  String getViewReferenceId( ){ return viewReferenceId;  }
public  void setViewReferenceId(  String viewReferenceId){ this.viewReferenceId=viewReferenceId;  }
public   LinkedViewReferences( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String guidinstance=" ";
if(null!=getGuid()){guidinstance= getGuid().replace("\"","\\\"");
};
String viewReferenceIdinstance=" ";
if(null!=getViewReferenceId()){viewReferenceIdinstance= getViewReferenceId().replace("\"","\\\"");
};

List<String> locationInsertSQL =getLocation().getInsertSQL(getUuid());
for(String st : locationInsertSQL){ output.add(st);};
String thisClassInsertQuery= "Insert into `LinkedViewReferences` ( father_id, `uuid`, `guid`, `viewReferenceId`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+guidinstance +"\",\""+viewReferenceIdinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}