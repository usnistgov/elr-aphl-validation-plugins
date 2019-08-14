package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestELR_019 {

	private static Logger logger = Logger.getLogger(TestELR_019.class.getName());

	private static String globalFolder = "/MessageProfile";

	private static String profiles;
	private static String constraints;
	private static String valueSets;

	private static String message1FileName = "TestMessages/ELR019/Message1.txt";
	private static String message2FileName = "TestMessages/ELR019/Message2.txt";
	private static String message3FileName = "TestMessages/ELR019/Message3.txt";
	private static String message4FileName = "TestMessages/ELR019/Message4.txt";
	private static String message5FileName = "TestMessages/ELR019/Message5.txt";
	private static String message6FileName = "TestMessages/ELR019/Message6.txt";
	private static String message7FileName = "TestMessages/ELR019/Message7.txt";
	private static String message8FileName = "TestMessages/ELR019/Message8.txt";
	private static String message9FileName = "TestMessages/ELR019/Message9.txt";
	private static String message10FileName = "TestMessages/ELR019/Message10.txt";
	private static String message11FileName = "TestMessages/ELR019/Message11.txt";
	private static String message12FileName = "TestMessages/ELR019/Message12.txt";

	@BeforeClass
	public static void setUp() {
		profiles = StringUtils.join(globalFolder, "/Profile.xml");
		constraints = StringUtils.join(globalFolder, "/Constraints.xml");
		valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");
	}

	/**
	 * MSH-21.1 = PHLabReport-Ack, MSH-15 is present and valued 'AL'. No error/alert
	 * expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message1 = new File(classLoader.getResource(message1FileName).getFile());
		String messageString = FileUtils.readFileToString(message1);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(0, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 = PHLabReport-Ack, MSH-15 is absent. Two errors, no alert expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message2 = new File(classLoader.getResource(message2FileName).getFile());
		String messageString = FileUtils.readFileToString(message2);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(2, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 = PHLabReport-Ack, MSH-15 is present and valued 'NE'. One error, no
	 * alert expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message3 = new File(classLoader.getResource(message3FileName).getFile());
		String messageString = FileUtils.readFileToString(message3);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(1, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21 Not present, MSH-15 not present. Two errors, no alert expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message4 = new File(classLoader.getResource(message4FileName).getFile());
		String messageString = FileUtils.readFileToString(message4);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(2, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21 Not present, MSH-15 present and valued NE. Two errors, no alert
	 * expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message5 = new File(classLoader.getResource(message5FileName).getFile());
		String messageString = FileUtils.readFileToString(message5);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(2, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21 Not present, MSH-15 present and valued AL. Three errors, no alert
	 * expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message6 = new File(classLoader.getResource(message6FileName).getFile());
		String messageString = FileUtils.readFileToString(message6);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(3, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Not present, MSH-15 not present. Two errors, no alert expected.
	 * expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message7 = new File(classLoader.getResource(message7FileName).getFile());
		String messageString = FileUtils.readFileToString(message7);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(2, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Not present, MSH-15 present and valued NE. Two errors, no alert
	 * expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test8() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message8 = new File(classLoader.getResource(message8FileName).getFile());
		String messageString = FileUtils.readFileToString(message8);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(2, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Not present, MSH-15 present and valued AL. Three errors, no alert
	 * expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test9() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message9 = new File(classLoader.getResource(message9FileName).getFile());
		String messageString = FileUtils.readFileToString(message9);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(3, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Present, but different from PHLabReport-Ack, MSH-15 not valued. No
	 * error, no alert expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message10 = new File(classLoader.getResource(message10FileName).getFile());
		String messageString = FileUtils.readFileToString(message10);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(0, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Present, but different from PHLabReport-Ack, MSH-15 valued 'AL'. One
	 * error, no alert expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message11 = new File(classLoader.getResource(message11FileName).getFile());
		String messageString = FileUtils.readFileToString(message11);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(1, errors);
		assertEquals(0, alerts);
	}

	/**
	 * MSH-21.1 Present, but different from PHLabReport-Ack, MSH-15 valued 'NE'. No
	 * error, no alert expected.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12() throws Exception {
		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message12 = new File(classLoader.getResource(message12FileName).getFile());
		String messageString = FileUtils.readFileToString(message12);
		Report report = validator.check(messageString, "ORU_R01");

		Set<String> keys = report.getEntries().keySet();
		int errors = 0;
		int alerts = 0;
		for (String key : keys) {
			List<Entry> entries = report.getEntries().get(key);
			if (entries != null && entries.size() > 0) {
				System.out.println("*** " + key + " ***");
				for (Entry entry : entries) {
					switch (entry.getClassification()) {
					case "Error":
						Util.printEntry(entry);
						errors++;
						break;
					case "Alert":
						Util.printEntry(entry);
						alerts++;
						break;
					}
				}
			}
		}
		assertEquals(0, errors);
		assertEquals(0, alerts);
	}
}
