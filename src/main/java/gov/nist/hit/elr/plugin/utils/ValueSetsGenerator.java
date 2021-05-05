package gov.nist.hit.elr.plugin.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import gov.nist.hit.elr.aphl.plugin.extra.context.ARLN;

public class ValueSetsGenerator {

  public static void main(String[] args) throws IOException {


    CSVUtils util = new CSVUtils();
    
    //value sets 
    util.parseValueSetsCSV(ARLN.getFolder(), ARLN.getValueSetsCsv());
    Map<String, Set<CodedElement>> map = util.getValueSets();
    List<String> vsNames = Arrays.asList("Acinetobacter culture result", "ARLN microorganism",
        "ARLN Regional Labs", "Beta-lac results for GC", "C auris culture result",
        "C auris PCR result", "Carbapenemase phenotypic results", "Clinical sample or isolate",
        "CRE AST method", "CRE PCR method", "CRE PCR results", "CRE Phenotypic method",
        "GC Facility codes", "GC specimen quality", "Gender", "Healthcare facility type",
        "Identifier type", "Index case resistance gene","Index case species", "Jurisdiction PHL codes for GC",
        "PHL - CRE CRPA", "Reason for screening", "Yes No Indicator", "Yes No Unknown");
    
    try {
      FileWriter myWriter = new FileWriter("ARLN_vs.txt");
      myWriter.write("<ValueSetDefinitions Group=\"ARLN\" Order=\"3\">\n");   
      for (String vsName : vsNames) {      
        String formattedName = vsName.toLowerCase();
        myWriter.write("<ValueSetDefinition BindingIdentifier=\"" + formattedName + "\" Name=\""
            + vsName + "\" Description=\"" + vsName
            + "\" Stability=\"Static\" Extensibility=\"Closed\" ContentDefinition=\"Extensional\">\n");
        Set<CodedElement> values = map.get(vsName.toLowerCase());
        for (CodedElement value : values) {
          myWriter.write("<ValueElement Value=\"" + value.getIdentifier()
              + "\" DisplayName=\"\" CodeSystem=\"" + value.getCodeSystem() + "\" Usage=\"P\" />\n");
        }
        myWriter.write("</ValueSetDefinition>\n");
      }   
      myWriter.write("</ValueSetDefinitions>");
      
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    
    //constraints
    util.parseTestCSV(ARLN.getFolder(), ARLN.getTestCsv());
    Map<CodedElement, Set<String>> obx2Map = util.getOBX3_OBX2();
    Map<CodedElement, String> obx5Map = util.getOBX3_OBX5();
    
    
    
    try {
      FileWriter myWriter = new FileWriter("ARLN_constraints.txt");
          
      for ( CodedElement obx3 : obx2Map.keySet()) {
        
        Set<String> obx2 = obx2Map.get(obx3);
        myWriter.write("<Constraint ID=\"OBX_"+obx3.getIdentifier()+"\">\n");
        if(obx2.size() == 1){
          myWriter.write("<Description>If the value of OBX-3[1] (Observation Identifier) is '"+obx3.getIdentifier()+"', then the value of OBX-2[1] (Value Type) SHALL be '"+StringUtils.join(obx2,"")+"'.</Description>\n");
        }
        else {
          myWriter.write("<Description>If the value of OBX-3[1] (Observation Identifier) is '"+obx3.getIdentifier()+"', then the value of OBX-2[1] (Value Type) SHALL be one of ["+StringUtils.join(obx2,",")+"].</Description>\n");
        }
        myWriter.write("<Assertion>\n");
        myWriter.write("<IMPLY>\n");
        myWriter.write("<FORALL>\n");
        myWriter.write("<Presence Path=\"3[1].1[1]\" />\n");
        myWriter.write("<PlainText Path=\"3[1].1[1]\" Text=\""+obx3.getIdentifier()+"\" IgnoreCase=\"false\" />\n");
        myWriter.write("<Presence Path=\"3[1].3[1]\" />\n");
        myWriter.write("<PlainText Path=\"3[1].3[1]\" Text=\""+obx3.getCodeSystem()+"\" IgnoreCase=\"false\" />\n");
        myWriter.write("</FORALL>\n");
        myWriter.write("<AND>\n");
        myWriter.write("<Presence Path=\"2[1]\" />\n");
        myWriter.write("<StringList Path=\"2[1]\" CSV=\""+StringUtils.join(obx2,",")+"\" IgnoreCase=\"false\" />\n");
        myWriter.write("</AND>\n");
        myWriter.write("</IMPLY>\n");
        myWriter.write("</Assertion>\n");
        myWriter.write("</Constraint>\n");        
        
        
        if(obx5Map.containsKey(obx3) && !"".equals(obx5Map.get(obx3))) {
          
          myWriter.write("<Constraint ID=\"OBX_"+obx3.getIdentifier()+"_vs \">\n");
          myWriter.write("<Description>If the value of OBX-3[1] (Observation Identifier) is '"+obx3.getIdentifier()+"', then the value of OBX-5[1] () SHALL come from the '"+obx5Map.get(obx3)+"' value set.</Description>\n");
          myWriter.write("<Assertion>\n");
          myWriter.write("<IMPLY>\n");
          myWriter.write("<FORALL>\n");
          myWriter.write("<Presence Path=\"3[1].1[1]\" />\n");
          myWriter.write("<PlainText Path=\"3[1].1[1]\" Text=\""+obx3.getIdentifier()+"\" IgnoreCase=\"false\" />\n");
          myWriter.write("<Presence Path=\"3[1].3[1]\" />\n");
          myWriter.write("<PlainText Path=\"3[1].3[1]\" Text=\""+obx3.getCodeSystem()+"\" IgnoreCase=\"false\" />\n");
          myWriter.write("</FORALL>\n");
          myWriter.write("<AND>\n");
          myWriter.write("<Presence Path=\"5[1].1[1]\" />\n");
          myWriter.write("<ValueSet ValueSetID=\""+obx5Map.get(obx3)+"\" Path=\"5[1]\" BindingLocation=\"1\" BindingStrength=\"R\"/>\n");
          myWriter.write("</AND>\n");
          myWriter.write("</IMPLY>\n");
          myWriter.write("</Assertion>\n");
          myWriter.write("</Constraint>\n");        

          
         }
        
      }
      
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    


  }
}
