package gov.nist.hit.elr.plugin.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for parsing CSV files used in HL7 message validation.
 * Loads and processes data from various CSV files including test data,
 * observations, orders, value sets, specimen types, and message header
 * information for use in validation rules.
 */
public class CSVUtils {

	private static final Logger logger = LogManager.getLogger(CSVUtils.class);

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

	private static final String CLASSPATH_PREFIX = "classpath:";

	// Resolves a column by name with progressive fallback:
	// 1. case-insensitive exact match (already handled by setIgnoreHeaderCase)
	// 2. normalized match: strip all spaces (catches "CodeSystem" vs "Code System")
	// 3. suffix match: header ends with the target name (catches "ARLN Value Set Name" vs "Value Set Name")
	// 4. positional index fallback with a warning
	private String getField(CSVRecord record, Map<String, Integer> headerMap, String columnName, int fallbackIndex) {
		String normalizedTarget = columnName.toLowerCase();

		if (headerMap.containsKey(normalizedTarget)) {
			return record.get(columnName);
		}

		String strippedTarget = normalizedTarget.replace(" ", "");
		for (String header : headerMap.keySet()) {
			if (header.replace(" ", "").equals(strippedTarget)) {
				return record.get(header);
			}
		}

		for (String header : headerMap.keySet()) {
			if (header.endsWith(normalizedTarget)) {
				return record.get(header);
			}
		}

		logger.warn("Column '{}' not found in header {}; falling back to index {}", columnName, headerMap.keySet(), fallbackIndex);
		return record.get(fallbackIndex);
	}

	private BufferedReader openReader(String path) throws IOException {
		if (path.startsWith(CLASSPATH_PREFIX)) {
			String resourcePath = path.substring(CLASSPATH_PREFIX.length());
			InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
			if (is == null) {
				throw new IOException("Classpath resource not found: " + resourcePath);
			}
			return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		}
		return new BufferedReader(new FileReader(path));
	}

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

	public Map<CodedElement, Set<String>> getOBX3_OBX2() {
		return OBX3_OBX2;
	}

	public Map<CodedElement, String> getOBX3_OBX5() {
		return OBX3_OBX5;
	}

	public Map<String, Set<CodedElement>> getValueSets() {
		return valueSets;
	}

	public Set<CodedElement> getSPM4() {
		return SPM4;
	}

	public void parse(String testCsv, String observationsCsv, String orderCsv, String valueSetsCsv) throws IOException {
		parseOrdersCSV(orderCsv);
		parseObservationsCSV(observationsCsv);
		parseTestCSV(testCsv);
		parseValueSetsCSV(valueSetsCsv);
	}

	protected void parseValueSetsCSV(String valueSetsCsv) throws IOException {
		logger.info("Opening value sets CSV file: " + valueSetsCsv);
		BufferedReader reader = openReader(valueSetsCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		Map<String, Integer> headerMap = csvParser.getHeaderMap();
		logger.debug("Header columns: " + headerMap.keySet());

		for (CSVRecord csvRecord : csvParser) {
			String valueSetName = getField(csvRecord, headerMap, "Value Set Name", 0).toLowerCase();
			String code = getField(csvRecord, headerMap, "Code", 1);
			String codeSystem = getField(csvRecord, headerMap, "CodeSystem", 2);
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

	protected void parseTestCSV(String testCsv) throws IOException {
		logger.info("Opening test CSV file: " + testCsv);
		BufferedReader reader = openReader(testCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		Map<String, Integer> headerMap = csvParser.getHeaderMap();
		logger.debug("Header columns: " + headerMap.keySet());

		for (CSVRecord csvRecord : csvParser) {
			String OBR4Identifier = getField(csvRecord, headerMap, "OBR4 Code", 0);
			String OBR4CodeSystem = getField(csvRecord, headerMap, "OBR4 Code System", 1);
			CodedElement OBR4 = new CodedElement(OBR4Identifier, OBR4CodeSystem);

			String OBX3Identifier = getField(csvRecord, headerMap, "OBX3 Code", 2);
			String OBX3CodeSystem = getField(csvRecord, headerMap, "OBX3 Code System", 3);
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

			String OBX2 = getField(csvRecord, headerMap, "OBX2", 4);
			if (!OBX3_OBX2.containsKey(OBX3)) {
				OBX3_OBX2.put(OBX3, new HashSet<String>());
			}
			OBX3_OBX2.get(OBX3).add(OBX2);

			String OBX5 = getField(csvRecord, headerMap, "Value Set Name", 5);
			if (OBX3_OBX5.containsKey(OBX3) && !OBX3_OBX5.get(OBX3).equalsIgnoreCase(OBX5)) {
				logger.warn(
						"OBX-3 inconsistency detected: Code={}, Previous Value Set Name={}, Current Value Set Name={}",
						new Object[] { OBX3, OBX3_OBX5.get(OBX3), OBX5 });
			}
			OBX3_OBX5.put(OBX3, OBX5.toLowerCase());
		}
		csvParser.close();
		reader.close();
	}

	private void parseObservationsCSV(String observationsCsv) throws IOException {
		logger.info("Opening observations CSV file: " + observationsCsv);
		BufferedReader reader = openReader(observationsCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		Map<String, Integer> headerMap = csvParser.getHeaderMap();
		logger.debug("Header columns: " + headerMap.keySet());

		for (CSVRecord csvRecord : csvParser) {
			String OBX3Identifier = getField(csvRecord, headerMap, "OBX3 Code", 0);
			String OBX3CodeSystem = getField(csvRecord, headerMap, "OBX3 Code System", 1);
			CodedElement OBX3 = new CodedElement(OBX3Identifier, OBX3CodeSystem);

			this.OBX3.add(OBX3);
		}
		csvParser.close();
		reader.close();
	}

	private void parseOrdersCSV(String orderCsv) throws IOException {
		logger.info("Opening orders CSV file: " + orderCsv);
		BufferedReader reader = openReader(orderCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		Map<String, Integer> headerMap = csvParser.getHeaderMap();
		logger.debug("Header columns: " + headerMap.keySet());

		for (CSVRecord csvRecord : csvParser) {
			String OBR4Identifier = getField(csvRecord, headerMap, "OBR4 Code", 0);
			String OBR4CodeSystem = getField(csvRecord, headerMap, "OBR4 Code System", 1);
			CodedElement OBR4 = new CodedElement(OBR4Identifier, OBR4CodeSystem);

			this.OBR4.add(OBR4);
		}
		csvParser.close();
		reader.close();
	}

	public void parseSpecimenTypeCSV(String specimentTypeCsv) throws IOException {
		logger.info("Opening specimen type CSV file: " + specimentTypeCsv);
		BufferedReader reader = openReader(specimentTypeCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		Map<String, Integer> headerMap = csvParser.getHeaderMap();
		logger.debug("Header columns: " + headerMap.keySet());

		for (CSVRecord csvRecord : csvParser) {
			String SPM4Identifier = getField(csvRecord, headerMap, "Concept Code", 1);
			String SPM4Text = getField(csvRecord, headerMap, "Preferred Concept Name", 3);
			String SPM4CodeSystem = getField(csvRecord, headerMap, "Code System", 4);
			CodedElement SPM4 = new CodedElement(SPM4Identifier, SPM4Text, SPM4CodeSystem);

			this.SPM4.add(SPM4);
		}
		csvParser.close();
		reader.close();
	}

	public void parseSendingApplication(String messageHeaderCsv) throws IOException {
		logger.info("Opening sending application CSV file: " + messageHeaderCsv);
		BufferedReader reader = openReader(messageHeaderCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		logger.debug("Header columns: " + csvParser.getHeaderMap().keySet());

		for (CSVRecord csvRecord : csvParser) {
			String MSH3nsId = csvRecord.get(1);
			String MSH3uId = csvRecord.get(2);
			String MSH3uIdType = csvRecord.get(3);
			String MSH11 = csvRecord.get(4);
			HierarchicDesignator MSH3 = new HierarchicDesignator(MSH3nsId, MSH3uId, MSH3uIdType);

			this.MSH3.add(MSH3.normalize());
			if (!this.MSH3_MSH11.containsKey(MSH3.normalize())) {
				this.MSH3_MSH11.put(MSH3.normalize(), new HashSet<String>());
			}
			this.MSH3_MSH11.get(MSH3.normalize()).add(MSH11);
		}
		csvParser.close();
		reader.close();
	}

	public void parseSendingFacility(String messageHeaderCsv) throws IOException {
		logger.info("Opening sending facility CSV file: " + messageHeaderCsv);
		BufferedReader reader = openReader(messageHeaderCsv);
		CSVFormat format = CSVFormat.EXCEL.builder().setHeader() // Replaces withFirstRecordAsHeader()
				.setSkipHeaderRecord(true) // Required with setHeader() to skip first row
				.setIgnoreHeaderCase(true) // Replaces withIgnoreHeaderCase()
				.setTrim(true) // Replaces withTrim()
				.get();
		CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(format).get();
		logger.debug("Header columns: " + csvParser.getHeaderMap().keySet());

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