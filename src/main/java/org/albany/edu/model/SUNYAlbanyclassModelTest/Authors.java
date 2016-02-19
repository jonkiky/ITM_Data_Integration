package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  Authors
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String userName;
private String lastName;
private String guid;
private String email;
private String firstName;

public  String toString( ){ return "[class name]:'Authors'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'userName'[class attributes]:[type]'String'[name]'lastName'[class attributes]:[type]'String'[name]'guid'[class attributes]:[type]'String'[name]'email'[class attributes]:[type]'String'[name]'firstName'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getUserName( ){ return userName;  }
public  void setUserName(  String userName){ this.userName=userName;  }
public  String getLastName( ){ return lastName;  }
public  void setLastName(  String lastName){ this.lastName=lastName;  }
public  String getGuid( ){ return guid;  }
public  void setGuid(  String guid){ this.guid=guid;  }
public  String getEmail( ){ return email;  }
public  void setEmail(  String email){ this.email=email;  }
public  String getFirstName( ){ return firstName;  }
public  void setFirstName(  String firstName){ this.firstName=firstName;  }
public   Authors( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String userNameinstance=" ";
if(null!=getUserName()){userNameinstance= getUserName().replace("\"","\\\"");
};
String lastNameinstance=" ";
if(null!=getLastName()){lastNameinstance= getLastName().replace("\"","\\\"");
};
String guidinstance=" ";
if(null!=getGuid()){guidinstance= getGuid().replace("\"","\\\"");
};
String emailinstance=" ";
if(null!=getEmail()){emailinstance= getEmail().replace("\"","\\\"");
};
String firstNameinstance=" ";
if(null!=getFirstName()){firstNameinstance= getFirstName().replace("\"","\\\"");
};

String thisClassInsertQuery= "Insert into `Authors` ( father_id, `uuid`, `userName`, `lastName`, `guid`, `email`, `firstName`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+userNameinstance +"\",\""+lastNameinstance +"\",\""+guidinstance +"\",\""+emailinstance +"\",\""+firstNameinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}