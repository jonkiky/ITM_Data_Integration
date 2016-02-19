package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class  SettingInfo
{
private String father_id;
private String uuid=UUID.randomUUID().toString();
private String viewId;
private String fontFamily;
private String linkColor;
private String backgroundColor;
private int fontSize;
private String guid;

public  String toString( ){ return "[class name]:'SettingInfo'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'viewId'[class attributes]:[type]'String'[name]'fontFamily'[class attributes]:[type]'String'[name]'linkColor'[class attributes]:[type]'String'[name]'backgroundColor'[class attributes]:[type]'int'[name]'fontSize'[class attributes]:[type]'String'[name]'guid'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getViewId( ){ return viewId;  }
public  void setViewId(  String viewId){ this.viewId=viewId;  }
public  String getFontFamily( ){ return fontFamily;  }
public  void setFontFamily(  String fontFamily){ this.fontFamily=fontFamily;  }
public  String getLinkColor( ){ return linkColor;  }
public  void setLinkColor(  String linkColor){ this.linkColor=linkColor;  }
public  String getBackgroundColor( ){ return backgroundColor;  }
public  void setBackgroundColor(  String backgroundColor){ this.backgroundColor=backgroundColor;  }
public  int getFontSize( ){ return fontSize;  }
public  void setFontSize(  int fontSize){ this.fontSize=fontSize;  }
public  String getGuid( ){ return guid;  }
public  void setGuid(  String guid){ this.guid=guid;  }
public   SettingInfo( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String viewIdinstance=" ";
if(null!=getViewId()){viewIdinstance= getViewId().replace("\"","\\\"");
};
String fontFamilyinstance=" ";
if(null!=getFontFamily()){fontFamilyinstance= getFontFamily().replace("\"","\\\"");
};
String linkColorinstance=" ";
if(null!=getLinkColor()){linkColorinstance= getLinkColor().replace("\"","\\\"");
};
String backgroundColorinstance=" ";
if(null!=getBackgroundColor()){backgroundColorinstance= getBackgroundColor().replace("\"","\\\"");
};
String guidinstance=" ";
if(null!=getGuid()){guidinstance= getGuid().replace("\"","\\\"");
};

String thisClassInsertQuery= "Insert into `SettingInfo` ( father_id, `uuid`, `viewId`, `fontFamily`, `linkColor`, `backgroundColor`, `fontSize`, `guid`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+viewIdinstance +"\",\""+fontFamilyinstance +"\",\""+linkColorinstance +"\",\""+backgroundColorinstance +"\","+getFontSize()+",\""+guidinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}