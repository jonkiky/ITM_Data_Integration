package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  BuildOnb47a8a6b92984b1fb52065891e9dcdec
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String from;
private String type;
private String to;

public  String toString( ){ return "[class name]:'BuildOnb47a8a6b92984b1fb52065891e9dcdec'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'from'[class attributes]:[type]'String'[name]'type'[class attributes]:[type]'String'[name]'to'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getFrom( ){ return from;  }
public  void setFrom(  String from){ this.from=from;  }
public  String getType( ){ return type;  }
public  void setType(  String type){ this.type=type;  }
public  String getTo( ){ return to;  }
public  void setTo(  String to){ this.to=to;  }
public   BuildOnb47a8a6b92984b1fb52065891e9dcdec( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String frominstance=" ";
if(null!=getFrom()){frominstance= getFrom().replace("\"","\\\"");
};
String typeinstance=" ";
if(null!=getType()){typeinstance= getType().replace("\"","\\\"");
};
String toinstance=" ";
if(null!=getTo()){toinstance= getTo().replace("\"","\\\"");
};

String thisClassInsertQuery= "Insert into `BuildOnb47a8a6b92984b1fb52065891e9dcdec` ( father_id, `uuid`, `from`, `type`, `to`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+frominstance +"\",\""+typeinstance +"\",\""+toinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}