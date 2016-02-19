package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  Location
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String refType;
private String referenceId;
private Point point;

public  String toString( ){ return "[class name]:'Location'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'refType'[class attributes]:[type]'String'[name]'referenceId'[class attributes]:[type]'Point'[name]'point'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getRefType( ){ return refType;  }
public  void setRefType(  String refType){ this.refType=refType;  }
public  String getReferenceId( ){ return referenceId;  }
public  void setReferenceId(  String referenceId){ this.referenceId=referenceId;  }
public  Point getPoint( ){ return point;  }
public  void setPoint(  Point point){ this.point=point;  }
public   Location( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String refTypeinstance=" ";
if(null!=getRefType()){refTypeinstance= getRefType().replace("\"","\\\"");
};
String referenceIdinstance=" ";
if(null!=getReferenceId()){referenceIdinstance= getReferenceId().replace("\"","\\\"");
};

List<String> pointInsertSQL =getPoint().getInsertSQL(getUuid());
for(String st : pointInsertSQL){ output.add(st);};
String thisClassInsertQuery= "Insert into `Location` ( father_id, `uuid`, `refType`, `referenceId`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+refTypeinstance +"\",\""+referenceIdinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}