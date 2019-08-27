package gov.nist.hit.elr.plugin.utils;

import java.io.BufferedReader;
import java.io.IOException;
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
	private Map<CodedElement, String> OBX3_OBX2;
	private Map<CodedElement, String> OBX3_OBX5;
	private Map<String, Set<CodedElement>> valueSets;

	public CSVUtils() throws IOException {
		OBR4 = new HashSet<CodedElement>();
		OBX3 = new HashSet<CodedElement>();
		OBR4_OBX3 = new HashMap<CodedElement, Set<CodedElement>>();
		OBX3_OBR4 = new HashMap<CodedElement, Set<CodedElement>>();
		OBX3_OBX2 = new HashMap<CodedElement, String>();
		OBX3_OBX5 = new HashMap<CodedElement, String>();
		valueSets = new HashMap<String, Set<CodedElement>>();
	}

	public Set<CodedElement> getOBR4() {
		return OBR4;
	}

	public Set<CodedElement> getOBX3() {
		return OBX3;
	}

	public Map<CodedElement, Set<CodedElement>> getOBR4_OBX3() {
		return OBR4_OBX3;
	}

	public Map<CodedElement, Set<CodedElement>> getOBX3_OBR4() {
		return OBX3_OBR4;
	}

	public Map<CodedElement, String> getOBX3_OBX2() {
		return OBX3_OBX2;
	}

	public Map<CodedElement, String> getOBX3_OBX5() {
		return OBX3_OBX5;
	}

	public Map<String, Set<CodedElement>> getValueSets() {
		return valueSets;
	}

	public void parse(String folder, String testCsv, String observationsCsv, String orderCsv, String valueSetsCsv)
			throws IOException {
		parseOrdersCSV(folder, orderCsv);
		parseObservationsCSV(folder, observationsCsv);
		parseTestCSV(folder, testCsv);
		parseValueSetsCSV(folder, valueSetsCsv);
	}

	private void parseValueSetsCSV(String folder, String valueSetsCsv) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(CSVUtils.class.getResourceAsStream("/" + folder + "/" + valueSetsCsv)));

		CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
		CSVParser csvParser = new CSVParser(reader, format);

		for (CSVRecord csvRecord : csvParser) {
			String valueSetName = csvRecord.get("Value Set Name");
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

	private void parseTestCSV(String folder, String testCsv) throws IOException {

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
			OBX3_OBX2.put(OBX3, OBX2);

			String OBX5 = csvRecord.get("Value Set Name");
			OBX3_OBX5.put(OBX3, OBX5);
		}
		csvParser.close();
		reader.close();
	}

	private void parseObservationsCSV(String folder, String observationsCsv) throws IOException {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(CSVUtils.class.getResourceAsStream("/" + folder + "/" + observationsCsv)));

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
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(CSVUtils.class.getResourceAsStream("/" + folder + "/" + orderCsv)));
		CSVFormat format = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
		CSVParser csvParser = new CSVParser(reader, format);

		for (CSVRecord csvRecord : csvParser) {
			String OBR4Identifier = csvRecord.get("OBR4 Code");
			String OBR4CodeSystem = csvRecord.get("OBR4 Code System");

			CodedElement OBR4 = new CodedElement(OBR4Identifier, OBR4CodeSystem);

			this.OBR4.add(OBR4);

		}
		csvParser.close();
		reader.close();
	}

}
