package org.albany.edu.model.SUNYAlbanyclassModelTest;
import java.util.ArrayList;
import java.util.List;
import org.albany.edu.model.IModel;
import org.albany.edu.model.INoteGroup;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
public class  Section implements IModel ,INoteGroup{
private String father_id;
private String uuid=UUID.randomUUID().toString();
@JsonProperty("Knowledge Society Network")
private String SectionKnowledgeSocietyNetwork;
@JsonProperty("TaCCL-UAlbany")
private String SectionTaCCLUAlbany;
@JsonProperty("GES 2015-2016")
private String SectionGES20152016;
@JsonProperty("JICS 2015-2016")
private String SectionJICS20152016;
@JsonProperty("LA-WEEK")
private String SectionLAWEEK;
private String renamekey="&Knowledge Society Network&TaCCL-UAlbany&GES 2015-2016&JICS 2015-2016&LA-WEEK";

public  String toString( ){ return "[class name]:'Section'[class attributes]:[type]'String'[name]'father_id'[class attributes]:[type]'String'[name]'uuid'[class attributes]:[type]'String'[name]'SectionKnowledgeSocietyNetwork'[class attributes]:[type]'String'[name]'SectionTaCCLUAlbany'[class attributes]:[type]'String'[name]'SectionGES20152016'[class attributes]:[type]'String'[name]'SectionJICS20152016'[class attributes]:[type]'String'[name]'SectionLAWEEK'[class attributes]:[type]'String'[name]'renamekey'";  }
public  String getFather_id( ){ return father_id;  }
public  void setFather_id(  String father_id){ this.father_id=father_id;  }
public  String getUuid( ){ return uuid;  }
public  void setUuid(  String uuid){ this.uuid=uuid;  }
public  String getSectionKnowledgeSocietyNetwork( ){ return SectionKnowledgeSocietyNetwork;  }
public  void setSectionKnowledgeSocietyNetwork(  String SectionKnowledgeSocietyNetwork){ this.SectionKnowledgeSocietyNetwork=SectionKnowledgeSocietyNetwork;  }
public  String getSectionTaCCLUAlbany( ){ return SectionTaCCLUAlbany;  }
public  void setSectionTaCCLUAlbany(  String SectionTaCCLUAlbany){ this.SectionTaCCLUAlbany=SectionTaCCLUAlbany;  }
public  String getSectionGES20152016( ){ return SectionGES20152016;  }
public  void setSectionGES20152016(  String SectionGES20152016){ this.SectionGES20152016=SectionGES20152016;  }
public  String getSectionJICS20152016( ){ return SectionJICS20152016;  }
public  void setSectionJICS20152016(  String SectionJICS20152016){ this.SectionJICS20152016=SectionJICS20152016;  }
public  String getSectionLAWEEK( ){ return SectionLAWEEK;  }
public  void setSectionLAWEEK(  String SectionLAWEEK){ this.SectionLAWEEK=SectionLAWEEK;  }
public  String getRenamekey( ){ return renamekey;  }
public  void setRenamekey(  String renamekey){ this.renamekey=renamekey;  }
public   Section( ){   }
public  List<String> getInsertSQL(  String fatherId){ List<String> output = new ArrayList<String>();
String uuidinstance=" ";
if(null!=getUuid()){uuidinstance= getUuid().replace("\"","\\\"");
};
String SectionKnowledgeSocietyNetworkinstance=" ";
if(null!=getSectionKnowledgeSocietyNetwork()){SectionKnowledgeSocietyNetworkinstance= getSectionKnowledgeSocietyNetwork().replace("\"","\\\"");
};
String SectionTaCCLUAlbanyinstance=" ";
if(null!=getSectionTaCCLUAlbany()){SectionTaCCLUAlbanyinstance= getSectionTaCCLUAlbany().replace("\"","\\\"");
};
String SectionGES20152016instance=" ";
if(null!=getSectionGES20152016()){SectionGES20152016instance= getSectionGES20152016().replace("\"","\\\"");
};
String SectionJICS20152016instance=" ";
if(null!=getSectionJICS20152016()){SectionJICS20152016instance= getSectionJICS20152016().replace("\"","\\\"");
};
String SectionLAWEEKinstance=" ";
if(null!=getSectionLAWEEK()){SectionLAWEEKinstance= getSectionLAWEEK().replace("\"","\\\"");
};
String renamekeyinstance=" ";
if(null!=getRenamekey()){renamekeyinstance= getRenamekey().replace("\"","\\\"");
};

String thisClassInsertQuery= "Insert into `Section` ( father_id, `uuid`, `SectionKnowledgeSocietyNetwork`, `SectionTaCCLUAlbany`, `SectionGES20152016`, `SectionJICS20152016`, `SectionLAWEEK`, `renamekey`)values ('"+fatherId+"',\""+uuidinstance +"\",\""+SectionKnowledgeSocietyNetworkinstance +"\",\""+SectionTaCCLUAlbanyinstance +"\",\""+SectionGES20152016instance +"\",\""+SectionJICS20152016instance +"\",\""+SectionLAWEEKinstance +"\",\""+renamekeyinstance +"\");";
output.add(thisClassInsertQuery);
return output;  }}