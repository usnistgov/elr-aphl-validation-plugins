package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class Test_TS {

	private static TS testObject;

	@BeforeClass
	public static void setUp() {
		testObject = new TS();
	}

	@Before
	public void resetData() {

	}

	@Test
	public void testCheckSuccess() {
		String dtm = "2019";
		boolean result = testObject.check(dtm);
		assertTrue(result);

		dtm = "201909";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "2019091210";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "201909121046";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.1";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.12";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.123";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.1234";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "2019+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "201909+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "2019091210+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "201909121046+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.1+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.12+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.123+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20190912104633.1234+0500";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "20200229";
		result = testObject.check(dtm);
		assertTrue(result);
	}

	@Test
	public void testCheckFail() {
		String dtm = "xxxx";
		boolean result = testObject.check(dtm);
		assertFalse(result);

		dtm = "2019-0555";
		result = testObject.check(dtm);
		assertTrue(result);

		dtm = "2";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "201";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20191";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "201913";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20191232";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20191131";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20190230";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20190229";
		result = testObject.check(dtm);
		assertFalse(result);

		dtm = "20180229";
		result = testObject.check(dtm);
		assertFalse(result);
	}

	@Test
	public void testMessage() throws Exception {

		String globalFolder = "/TS";

		String profiles = StringUtils.join(globalFolder, "/Profile.xml");
		String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
		String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

		String message1FileName = "TS/Message.txt";

		SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
		ClassLoader classLoader = getClass().getClassLoader();
		File message1 = new File(classLoader.getResource(message1FileName).getFile());
		String messageString = FileUtils.readFileToString(message1);
		Report report = validator.check(messageString, "5d557ed577493e608b838fa8");

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
