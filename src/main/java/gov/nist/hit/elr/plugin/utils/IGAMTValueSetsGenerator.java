package gov.nist.hit.elr.plugin.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import gov.nist.hit.elr.aphl.plugin.extra.context.ARLN;
import gov.nist.hit.elr.aphl.plugin.extra.context.LOI;

public class IGAMTValueSetsGenerator {

  public static void main(String[] args) throws IOException {

    CSVUtils util = new CSVUtils();

    // obx5 value sets
    util.parseValueSetsCSV(LOI.getFolder(), LOI.getValueSetsCsv());
    Map<String, Set<CodedElement>> map = util.getValueSets();
    Set<String> vsNames = map.keySet();
    try {
      for (String vsName : vsNames) {
        String formattedName = vsName.toLowerCase();

        if (formattedName.contains("(")) {
          continue;
        }

        CSVPrinter printer = new CSVPrinter(new FileWriter("output/obx5/" + formattedName + ".csv"),
            CSVFormat.EXCEL);
        // metadata
        printer.printRecord("Value Set Metadata", "");
        printer.printRecord("Mapping Identifier", vsName);
        printer.printRecord("Name", vsName);
        printer.printRecord("Description", "");
        printer.printRecord("OID", "");
        printer.printRecord("Version", "");
        printer.printRecord("Extensibility", "Undefined");
        printer.printRecord("Stability", "Undefined");
        printer.printRecord("Content Definition", "Undefined");
        printer.printRecord("Comment", StringUtils.substringAfterLast(ARLN.getFolder(), "/"));
        printer.printRecord("", "");
        printer.printRecord("Value Set Definition", "");
        printer.printRecord("Value", "Description", "CodeSystem", "Usage", "Comments");

        Set<CodedElement> values = map.get(vsName);
        // codes
        for (CodedElement value : values) {
          printer.printRecord(value.getIdentifier(), value.getText(), value.getCodeSystem(), "R",
              "");
        }

        printer.close();
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // obx5 value sets
    util.parseTestCSV(LOI.getFolder(), LOI.getTestCsv());

    Map<CodedElement, String> map2 = util.getOBX3_OBX5();


  }
}
