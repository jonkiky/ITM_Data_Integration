package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  Point
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private int y;
private int x;

public  String toString( ){ return "[class name]:'Point'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'int'[name]'y'[class attributes]:[type]'int'[name]'x'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  int getY( ){ return y;  }
public  void setY(  int y){ this.y=y;  }
public  int getX( ){ return x;  }
public  void setX(  int x){ this.x=x;  }
public   Point( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};

String thisClassInsertQuery= "Insert into `Point` ( father_id, `uuid`, `y`, `x`)values ('"+fatherId+"',\""+uuidinstance +"\","+getY()+","+getX()+");";
output.add(thisClassInsertQuery);
return output;  }}