package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class TDenormalizeOutMainJava
{
  protected static String nl;
  public static synchronized TDenormalizeOutMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDenormalizeOutMainJava result = new TDenormalizeOutMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "if(hash_";
  protected final String TEXT_3 = "_";
  protected final String TEXT_4 = ".containsKey(";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = ")){" + NL + "\thash_";
  protected final String TEXT_7 = " = hash_";
  protected final String TEXT_8 = ".get(";
  protected final String TEXT_9 = ");                    " + NL + "}else{" + NL + "\thash_";
  protected final String TEXT_10 = " = new ";
  protected final String TEXT_11 = "java.util.";
  protected final String TEXT_12 = "Hash";
  protected final String TEXT_13 = "Map<";
  protected final String TEXT_14 = ", DenormalizeStruct";
  protected final String TEXT_15 = ",";
  protected final String TEXT_16 = ">";
  protected final String TEXT_17 = "();" + NL + "\thash_";
  protected final String TEXT_18 = ".put(";
  protected final String TEXT_19 = ",hash_";
  protected final String TEXT_20 = ");" + NL + "}";
  protected final String TEXT_21 = ")){" + NL + "\tdenormalize_result_";
  protected final String TEXT_22 = ");";
  protected final String TEXT_23 = NL + "\tif(!denormalize_result_";
  protected final String TEXT_24 = ".contains(";
  protected final String TEXT_25 = ")){" + NL + "\t\tdenormalize_result_";
  protected final String TEXT_26 = ".add(";
  protected final String TEXT_27 = ");" + NL + "\t}";
  protected final String TEXT_28 = NL + "\tdenormalize_result_";
  protected final String TEXT_29 = "\t\t" + NL + "\tdenormalize_result_";
  protected final String TEXT_30 = ".append(";
  protected final String TEXT_31 = ").append(FormatterUtils.format_Date(";
  protected final String TEXT_32 = ", ";
  protected final String TEXT_33 = "));" + NL + "\t\t\t";
  protected final String TEXT_34 = ").append(";
  protected final String TEXT_35 = ");" + NL + "\t\t\t";
  protected final String TEXT_36 = "                  " + NL + "}else{" + NL + "\tdenormalize_result_";
  protected final String TEXT_37 = " = new DenormalizeStruct";
  protected final String TEXT_38 = "();";
  protected final String TEXT_39 = ".append(FormatterUtils.format_Date(";
  protected final String TEXT_40 = NL + "\thash_";
  protected final String TEXT_41 = ", denormalize_result_";
  protected final String TEXT_42 = NL + "if(denormalize_result_";
  protected final String TEXT_43 = " == null){" + NL + "\tdenormalize_result_";
  protected final String TEXT_44 = NL + "}else{";
  protected final String TEXT_45 = NL + "}";
  protected final String TEXT_46 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {
    IMetadataTable metadata = metadatas.get(0);
    List< ? extends IConnection> conns = node.getIncomingConnections();
    IMetadataTable inMetadata = null;
    String connName = "";
    if(conns != null){
    	for (IConnection conn : conns) { 
			if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { 
				connName = conn.getName();
				inMetadata = conn.getMetadataTable();
    			break;
			}
		}
    	if (metadata != null && inMetadata != null) { 
			List<IMetadataColumn> columns = inMetadata.getListColumns();
    		Map<String, String> typesMap = new HashMap<String, String>();
    		Map<String, String> patternsMap = new HashMap<String, String>();
    		for(IMetadataColumn column : columns){
    			String type = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);
    			typesMap.put(column.getLabel(), type);
    			String pattern = ((column.getPattern() == null) || (column.getPattern().trim().length() == 0)) ? "" : column.getPattern();
    			patternsMap.put(column.getLabel(), pattern);
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
        
        	for(IMetadataColumn column : columns){
        		String columnName = column.getLabel();
        		if(denormalizeColumns.contains(columnName)){
        			continue;
        		}
        		groupColumns.add(column.getLabel());
        	}
        	
        	String tInputColumn =null;
        	String lastInputColumn = null;
			if(groupColumns.size() > 0){
				lastInputColumn = groupColumns.get(groupColumns.size() - 1);
			}
			for (int i=0; i < groupColumns.size(); i++) {
				String inputColumn = groupColumns.get(i);
				String nextInputColumn = null;
				if(i != groupColumns.size() - 1){
					nextInputColumn = groupColumns.get(i+1);
				}
				if(i < groupColumns.size()-1){

    stringBuffer.append(TEXT_2);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(nextInputColumn  );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(nextInputColumn  );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    
//start
//
					for(int j = i+1; j < groupColumns.size(); j++){
						if(j == groupColumns.size() - 1){
//
//end

    stringBuffer.append(TEXT_11);
    if(j == i+1){
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(typesMap.get(groupColumns.get(j)) );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid );
    
//start
//
						}else{
//
//end

    stringBuffer.append(TEXT_11);
    if(j == i+1){
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(typesMap.get(groupColumns.get(j)) );
    stringBuffer.append(TEXT_15);
    
//start
//
						}
					}
					for(int j = i+1; j < groupColumns.size(); j++){
//
//end

    stringBuffer.append(TEXT_16);
    
//start
//
					}
//
//end

    stringBuffer.append(TEXT_17);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(nextInputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_20);
    //start
//
				}else{
					tInputColumn = inputColumn;
//
//end
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputColumn );
    stringBuffer.append(TEXT_22);
    
	//denormalize
	for(int k = 0; k < denormalizeColumns.size(); k++){
		String denormalizeColumn = denormalizeColumns.get(k);
		if(denormalizeMergeFlags.get(k)){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_27);
    
		}else{
			if("List".equals(outTypesMap.get(denormalizeColumn))){

    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_22);
    
				continue;
    		}
    		
    		if(typesMap.get(denormalizeColumn) !=null && patternsMap.get(denormalizeColumn) != null){
				if((("java.util.Date").equals(typesMap.get(denormalizeColumn))) && (patternsMap.get(denormalizeColumn).length() != 0)){

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(denormalizeDelimiters.get(k) );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(patternsMap.get(denormalizeColumn) );
    stringBuffer.append(TEXT_33);
    
				}else{

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(denormalizeDelimiters.get(k) );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_35);
    
				}
			}
		}
	}

    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_38);
    
	//denormalize
	for(int k = 0; k < denormalizeColumns.size(); k++){
		String denormalizeColumn = denormalizeColumns.get(k);
		if(denormalizeMergeFlags.get(k)){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_27);
    
		}else{
			if("List".equals(outTypesMap.get(denormalizeColumn))){

    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_22);
    
				continue;
    		}
    		
			if(typesMap.get(denormalizeColumn) !=null && patternsMap.get(denormalizeColumn) != null){
				if((("java.util.Date").equals(typesMap.get(denormalizeColumn))) && (patternsMap.get(denormalizeColumn).length() != 0)){

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(patternsMap.get(denormalizeColumn) );
    stringBuffer.append(TEXT_33);
    
				}else{

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_35);
    
				}
			}
		}
	}

    stringBuffer.append(TEXT_40);
    stringBuffer.append(tInputColumn );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(lastInputColumn );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_20);
    //start
//
				}
			}
			if(groupColumns.size() == 0 && denormalizeColumns.size() > 0){

    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_38);
    
				//denormalize
				for(int k = 0; k < denormalizeColumns.size(); k++){
					String denormalizeColumn = denormalizeColumns.get(k);
					if(denormalizeMergeFlags.get(k)){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_27);
    
					}else{
						if("List".equals(outTypesMap.get(denormalizeColumn))){

    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_22);
    
            				continue;
                		}
						if(typesMap.get(denormalizeColumn) !=null && patternsMap.get(denormalizeColumn) != null){
							if((("java.util.Date").equals(typesMap.get(denormalizeColumn))) && (patternsMap.get(denormalizeColumn).length() != 0)){

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(patternsMap.get(denormalizeColumn) );
    stringBuffer.append(TEXT_33);
    
							}else{

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_35);
    
							}
						}
					}
				}

    stringBuffer.append(TEXT_44);
    
				//denormalize
				for(int k = 0; k < denormalizeColumns.size(); k++){
					String denormalizeColumn = denormalizeColumns.get(k);
					if(denormalizeMergeFlags.get(k)){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_27);
    
					}else{
						if("List".equals(outTypesMap.get(denormalizeColumn))){

    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_22);
    
							continue;
    					}
						if(typesMap.get(denormalizeColumn) !=null && patternsMap.get(denormalizeColumn) != null){
							if((("java.util.Date").equals(typesMap.get(denormalizeColumn))) && (patternsMap.get(denormalizeColumn).length() != 0)){

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(denormalizeDelimiters.get(k) );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(patternsMap.get(denormalizeColumn) );
    stringBuffer.append(TEXT_33);
    
							}else{

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(denormalizeDelimiters.get(k) );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(denormalizeColumn );
    stringBuffer.append(TEXT_35);
    
							}
						}
					}
				}

    stringBuffer.append(TEXT_45);
    
			}
		}
	}
}
//
//end
    stringBuffer.append(TEXT_46);
    return stringBuffer.toString();
  }
}
