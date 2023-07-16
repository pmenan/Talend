package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class TDenormalizeOutBeginJava
{
  protected static String nl;
  public static synchronized TDenormalizeOutBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDenormalizeOutBeginJava result = new TDenormalizeOutBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "class DenormalizeStruct";
  protected final String TEXT_3 = " {";
  protected final String TEXT_4 = NL + "java.util.List<";
  protected final String TEXT_5 = "> ";
  protected final String TEXT_6 = " = new java.util.ArrayList<";
  protected final String TEXT_7 = ">();";
  protected final String TEXT_8 = NL + "StringBuilder ";
  protected final String TEXT_9 = " = new StringBuilder();";
  protected final String TEXT_10 = NL + "}" + NL + "DenormalizeStruct";
  protected final String TEXT_11 = " denormalize_result_";
  protected final String TEXT_12 = " = null;";
  protected final String TEXT_13 = NL + "java.util.Map<";
  protected final String TEXT_14 = ", DenormalizeStruct";
  protected final String TEXT_15 = "java.util.Map<";
  protected final String TEXT_16 = ",";
  protected final String TEXT_17 = ">";
  protected final String TEXT_18 = " hash_";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = " = ";
  protected final String TEXT_21 = "null;";
  protected final String TEXT_22 = "new ";
  protected final String TEXT_23 = "java.util.";
  protected final String TEXT_24 = "Hash";
  protected final String TEXT_25 = "Map<";
  protected final String TEXT_26 = "();";
  protected final String TEXT_27 = NL + "\t\tlog.info(\"";
  protected final String TEXT_28 = " - Start to denormalize the data from datasource.\");" + NL + "\t";
  protected final String TEXT_29 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
String destination = ElementParameterParser.getValue(node, "__DESTINATION__");
List<IMetadataTable> metadatas = node.getMetadataList();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
if ((metadatas!=null)&&(metadatas.size()>0)) {

    IMetadataTable metadata = metadatas.get(0);
    List< ? extends IConnection> inConns = node.getIncomingConnections();
    IMetadataTable inMetadata = null;
    if(inConns != null){ 
    	for (IConnection conn : inConns) { 
			if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { 
				inMetadata = conn.getMetadataTable();
    			break;
			}
		}
	}
	
    if (metadata != null && inMetadata != null) { 
    	List<IMetadataColumn> inColumns = inMetadata.getListColumns();
    	Map<String, String> typesMap = new HashMap<String, String>();
    	for(IMetadataColumn column : inColumns){
    		String type = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);
    		typesMap.put(column.getLabel(), type);
    	}
    	Map<String, String> outTypesMap = new HashMap<String, String>();
    	for(IMetadataColumn outColumn : metadata.getListColumns()){
    		String type = JavaTypesManager.getTypeToGenerate(outColumn.getTalendType(), true);
    		outTypesMap.put(outColumn.getLabel(), type);
    	}
        List<Map<String, String>> denormalizes = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__DENORMALIZE_COLUMNS__");
        List<String> denormalizeColumns = new ArrayList<String>();
        List<String> denormalizeDelimiters = new ArrayList<String>();
        List<Boolean> denormalizeMergeFlags = new ArrayList<Boolean>();
        List<String> groupColumns = new ArrayList<String>();
        for(Map<String, String> denormalize : denormalizes){
        	String columnName = denormalize.get("INPUT_COLUMN");
        	if(denormalizeColumns.contains(columnName)){
        		continue;
        	}
        	denormalizeColumns.add(columnName);
        	denormalizeDelimiters.add(denormalize.get("DELIMITER"));
        	denormalizeMergeFlags.add(("true").equals(denormalize.get("MERGE")));
        }
        for(IMetadataColumn column : inColumns){
        	String columnName = column.getLabel();
        	if(denormalizeColumns.contains(columnName)){
        		continue;
        	}
        	groupColumns.add(column.getLabel());
        }

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_3);
    
	for(int i = 0; i < denormalizeColumns.size(); i++){
		String column = denormalizeColumns.get(i);
		if("List".equals(outTypesMap.get(column))){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(typesMap.get(column) );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(column );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(typesMap.get(column) );
    stringBuffer.append(TEXT_7);
    
		}else{
			if(denormalizeMergeFlags.get(i)){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(typesMap.get(column) );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(column );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(typesMap.get(column) );
    stringBuffer.append(TEXT_7);
     
			}else{

    stringBuffer.append(TEXT_8);
    stringBuffer.append(column );
    stringBuffer.append(TEXT_9);
    
			}
		}
	}

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_12);
    
for(int ii = 0; ii < groupColumns.size(); ii++){
	String input = groupColumns.get(ii);
	for(int i = ii; i < groupColumns.size(); i++){
		if(i == groupColumns.size() - 1){
//
//end

    stringBuffer.append(TEXT_13);
    stringBuffer.append(typesMap.get(groupColumns.get(i)) );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid );
    
//start
//
		}else{
//
//end

    stringBuffer.append(TEXT_15);
    stringBuffer.append(typesMap.get(groupColumns.get(i)) );
    stringBuffer.append(TEXT_16);
    
//start
//
		}
	}
	for(int i = ii; i < groupColumns.size(); i++){
//
//end

    stringBuffer.append(TEXT_17);
    
//start
//
	}
//
//end

    stringBuffer.append(TEXT_18);
    stringBuffer.append(input );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_20);
    
	if(ii != 0){

    stringBuffer.append(TEXT_21);
    	}else{

    stringBuffer.append(TEXT_22);
    
//start
//
		for(int i = 0; i < groupColumns.size(); i++){
			if(i == groupColumns.size() - 1){
//
//end

    stringBuffer.append(TEXT_23);
    
					if(i == 0){

    stringBuffer.append(TEXT_24);
    
					}

    stringBuffer.append(TEXT_25);
    stringBuffer.append(typesMap.get(groupColumns.get(i)) );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid );
    
//start
//
			}else{
//
//end

    stringBuffer.append(TEXT_23);
    
				if(i == 0){

    stringBuffer.append(TEXT_24);
    
				}

    stringBuffer.append(TEXT_25);
    stringBuffer.append(typesMap.get(groupColumns.get(i)) );
    stringBuffer.append(TEXT_16);
    
//start
//
			}
		}
		for(int i = 0; i < groupColumns.size(); i++){
//
//end

    stringBuffer.append(TEXT_17);
    
//start
//
		}
//
//end

    stringBuffer.append(TEXT_26);
    
	}
	if (isLog4jEnabled) {
	
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
	}
}

}
}

    stringBuffer.append(TEXT_29);
    return stringBuffer.toString();
  }
}
