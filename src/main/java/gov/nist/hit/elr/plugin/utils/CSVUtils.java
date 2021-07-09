package gov.nist.hit.elr.plugin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

public class CSVUtils {

  private static Logger logger = Logger.getLogger(CSVUtils.class.getName());

  private Set<CodedElement> OBR4;
  private Set<CodedElement> OBX3;
  private Map<CodedElement, Set<CodedElement>> OBR4_OBX3;
  private Map<CodedElement, Set<CodedElement>> OBX3_OBR4;
  private Map<CodedElement, Set<String>> OBX3_OBX2;
  private Map<CodedElement, String> OBX3_OBX5;
  private Map<String, Set<CodedElement>> valueSets;

  private Set<HierarchicDesignator> MSH3;
  private Map<HierarchicDesignator, Set<String>> MSH3_MSH11;
  private Set<HierarchicDesignator> MSH4;

  private Set<CodedElement> SPM4;

  public CSVUtils() throws IOException {
    OBR4 = new HashSet<CodedElement>();
    OBX3 = new HashSet<CodedElement>();
    OBR4_OBX3 = new HashMap<CodedElement, Set<CodedElement>>();
    OBX3_OBR4 = new HashMap<CodedElement, Set<CodedElement>>();
    OBX3_OBX2 = new HashMap<CodedElement, Set<String>>();
    OBX3_OBX5 = new HashMap<CodedElement, String>();
    valueSets = new HashMap<String, Set<CodedElement>>();

    MSH3 = new HashSet<HierarchicDesignator>();
    MSH3_MSH11 = new HashMap<HierarchicDesignator, Set<String>>();
    MSH4 = new HashSet<HierarchicDesignator>();

    SPM4 = new HashSet<CodedElement>();

  }

  public Set<HierarchicDesignator> getMSH3() {
    return MSH3;
  }

  public Map<HierarchicDesignator, Set<String>> getMSH3_MSH11() {
    return MSH3_MSH11;
  }

  public Set<HierarchicDesignator> getMSH4() {
    return MSH4;
  }

  @Deprecated
  public Set<CodedElement> getOBR4() {
    return OBR4;
  }

  @Deprecated
  public Set<CodedElement> getOBX3() {
    return OBX3;
  }

  @Deprecated
  public Map<CodedElement, Set<CodedElement>> getOBR4_OBX3() {
    return OBR4_OBX3;
  }

  @Deprecated
  public Map<CodedElement, Set<CodedElement>> getOBX3_OBR4() {
    return OBX3_OBR4;
  }

  @Deprecated
  public Map<CodedElement, Set<String>> getOBX3_OBX2() {
    return OBX3_OBX2;
  }

  @Deprecated
  public Map<CodedElement, String> getOBX3_OBX5() {
    return OBX3_OBX5;
  }

  @Deprecated
  public Map<String, Set<CodedElement>> getValueSets() {
    return valueSets;
  }

  public Set<CodedElement> getSPM4() {
    return SPM4;
  }

  public void parse(String folder, String testCsv, String observationsCsv, String orderCsv,
      String valueSetsCsv) throws IOException {
    parseOrdersCSV(folder, orderCsv);
    parseObservationsCSV(folder, observationsCsv);
    parseTestCSV(folder, testCsv);
    parseValueSetsCSV(folder, valueSetsCsv);
  }

  protected void parseValueSetsCSV(String folder, String valueSetsCsv) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        CSVUtils.class.getResourceAsStream("/" + folder + "/" + valueSetsCsv)));

    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);

    for (CSVRecord csvRecord : csvParser) {
      String valueSetName = csvRecord.get("Value Set Name").toLowerCase();
      String code = csvRecord.get("Code");
      String codeSystem = csvRecord.get("CodeSystem");

      CodedElement e = new CodedElement(code, codeSystem);

      if (valueSets.containsKey(valueSetName)) {
        valueSets.get(valueSetName).add(e);
      } else {
        Set<CodedElement> set = new HashSet<>(Arrays.asList(e));
        valueSets.put(valueSetName, set);
      }

    }
    csvParser.close();
    reader.close();
  }

  protected void parseTestCSV(String folder, String testCsv) throws IOException {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(CSVUtils.class.getResourceAsStream("/" + folder + "/" + testCsv)));

    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);

    for (CSVRecord csvRecord : csvParser) {
      String OBR4Identifier = csvRecord.get("OBR4 Code");
      String OBR4CodeSystem = csvRecord.get("OBR4 Code System");

      CodedElement OBR4 = new CodedElement(OBR4Identifier, OBR4CodeSystem);

      String OBX3Identifier = csvRecord.get("OBX3 Code");
      String OBX3CodeSystem = csvRecord.get("OBX3 Code System");

      CodedElement OBX3 = new CodedElement(OBX3Identifier, OBX3CodeSystem);

      if (OBX3Identifier == null || "".equals(OBX3Identifier) || OBX3CodeSystem == null
          || "".equals(OBX3CodeSystem)) {
        logger.debug("Error in CSV file. OBX-3 cannot be null");
        continue;
      }

      // OBR-4 to OBX-3 relationship
      if (OBR4_OBX3.containsKey(OBR4)) {
        OBR4_OBX3.get(OBR4).add(OBX3);
      } else {
        Set<CodedElement> set = new HashSet<>(Arrays.asList(OBX3));
        OBR4_OBX3.put(OBR4, set);
      }

      // OBX-3 to OBR-4 relationship
      if (OBX3_OBR4.containsKey(OBX3)) {
        OBX3_OBR4.get(OBX3).add(OBR4);
      } else {
        Set<CodedElement> set = new HashSet<>(Arrays.asList(OBR4));
        OBX3_OBR4.put(OBX3, set);
      }

      String OBX2 = csvRecord.get("OBX2");
      if (!OBX3_OBX2.containsKey(OBX3)) {
        OBX3_OBX2.put(OBX3, new HashSet<String>());
      }
      OBX3_OBX2.get(OBX3).add(OBX2);

      String OBX5 = csvRecord.get("Value Set Name");
      if (OBX3_OBX5.containsKey(OBX3) && !OBX3_OBX5.get(OBX3).equalsIgnoreCase(OBX5)) {
        System.err.println(OBX3);
        System.err.println(OBX3_OBX5.get(OBX3));
        System.err.println(OBX5);
        System.err.println();
      }
      OBX3_OBX5.put(OBX3, OBX5.toLowerCase());
    }
    csvParser.close();
    reader.close();
  }

  private void parseObservationsCSV(String folder, String observationsCsv) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(
        CSVUtils.class.getResourceAsStream("/" + folder + "/" + observationsCsv)));

    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);

    for (CSVRecord csvRecord : csvParser) {

      String OBX3Identifier = csvRecord.get("OBX3 Code");
      String OBX3CodeSystem = csvRecord.get("OBX3 Code System");

      CodedElement OBX3 = new CodedElement(OBX3Identifier, OBX3CodeSystem);

      this.OBX3.add(OBX3);

    }
    csvParser.close();
    reader.close();
  }

  private void parseOrdersCSV(String folder, String orderCsv) throws IOException {

    InputStream in = CSVUtils.class.getResourceAsStream("/" + folder + "/" + orderCsv);
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

    CSVFormat format = null;
    CSVParser csvParser = null;
    try {
      format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
      csvParser = new CSVParser(reader, format);
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (CSVRecord csvRecord : csvParser) {
      String OBR4Identifier = csvRecord.get("OBR4 Code");
      String OBR4CodeSystem = csvRecord.get("OBR4 Code System");

      CodedElement OBR4 = new CodedElement(OBR4Identifier, OBR4CodeSystem);

      this.OBR4.add(OBR4);

    }
    csvParser.close();
    reader.close();
  }

  public void parseSpecimenTypeCSV(String folder, String specimentTypeCsv) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        CSVUtils.class.getResourceAsStream("/" + folder + "/" + specimentTypeCsv)));
    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);

    for (CSVRecord csvRecord : csvParser) {
      String SPM4Identifier = csvRecord.get("Concept Code");
      String SPM4Text = csvRecord.get("Preferred Concept Name");
      String SPM4CodeSystem = csvRecord.get("Code System");

      CodedElement SPM4 = new CodedElement(SPM4Identifier, SPM4Text, SPM4CodeSystem);

      this.SPM4.add(SPM4);

    }
    csvParser.close();
    reader.close();
  }

  public void parseSendingApplication(String folder, String messageHeaderCsv) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        CSVUtils.class.getResourceAsStream("/" + folder + "/" + messageHeaderCsv)));
    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);
    for (CSVRecord csvRecord : csvParser) {
      String MSH3nsId = csvRecord.get(1);
      String MSH3uId = csvRecord.get(2);
      String MSH3uIdType = csvRecord.get(3);

      String MSH11 = csvRecord.get(4);

      HierarchicDesignator MSH3 = new HierarchicDesignator(MSH3nsId, MSH3uId, MSH3uIdType);
      // logger.debug("Adding " + MSH3.normalize().prettyPrint() + " to MSH-3");
      this.MSH3.add(MSH3.normalize());

      if (!this.MSH3_MSH11.containsKey(MSH3.normalize())) {
        this.MSH3_MSH11.put(MSH3.normalize(), new HashSet<String>());
      }
      this.MSH3_MSH11.get(MSH3.normalize()).add(MSH11);

    }
    csvParser.close();
    reader.close();
  }

  public void parseSendingFacility(String folder, String messageHeaderCsv) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        CSVUtils.class.getResourceAsStream("/" + folder + "/" + messageHeaderCsv)));
    CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
    CSVParser csvParser = new CSVParser(reader, format);

    for (CSVRecord csvRecord : csvParser) {

      String MSH4nsId = csvRecord.get(1);
      String MSH4uId = csvRecord.get(2);
      String MSH4uIdType = csvRecord.get(3);

      HierarchicDesignator MSH4 = new HierarchicDesignator(MSH4nsId, MSH4uId, MSH4uIdType);
      this.MSH4.add(MSH4.normalize());

    }
    csvParser.close();
    reader.close();
  }
}
