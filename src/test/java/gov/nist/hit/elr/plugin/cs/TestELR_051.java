package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestELR_051 {

	private static ELR_051 testObject;

	@BeforeClass
	public static void setUp() {
		testObject = new ELR_051();
	}

	@Test
	public void testCheckSuccess() {
		String OBR7_1 = "20140617";
		Set<String> OBX14_1 = Stream.of("20140617", "20140617", "20140617").collect(Collectors.toSet());
		boolean result = testObject.check(OBR7_1, OBX14_1);
		assertTrue(result);

		OBR7_1 = "";
		OBX14_1 = Stream.of("", "", "").collect(Collectors.toSet());
		result = testObject.check(OBR7_1, OBX14_1);
		assertTrue(result);

		OBR7_1 = "20140617";
		OBX14_1 = Stream.of("", "", "").collect(Collectors.toSet());
		result = testObject.check(OBR7_1, OBX14_1);
		assertTrue(result);

		OBR7_1 = "20140617";
		OBX14_1 = Stream.of("20140617", "", "").collect(Collectors.toSet());
		result = testObject.check(OBR7_1, OBX14_1);
		assertTrue(result);

		OBR7_1 = "20140617";
		OBX14_1 = new HashSet<String>();
		result = testObject.check(OBR7_1, OBX14_1);
		assertTrue(result);
	}

	@Test
	public void testCheckFail() {
		String OBR7_1 = "20140617";
		Set<String> OBX14_1 = Stream.of("20140617", "20140618").collect(Collectors.toSet());
		boolean result = testObject.check(OBR7_1, OBX14_1);
		assertFalse(result);

		OBR7_1 = "";
		OBX14_1 = Stream.of("20140617", "", "").collect(Collectors.toSet());
		result = testObject.check(OBR7_1, OBX14_1);
		assertFalse(result);

	}

	@Test
	@Ignore
	public void testMessage() throws Exception {

		String globalFolder = "/MessageProfile";

		String profiles = StringUtils.join(globalFolder, "/Profile.xml");
		String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
		String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

		String message1FileName = "TestMessages/ELR051/Message1.txt";

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
	}
}
