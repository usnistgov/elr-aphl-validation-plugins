package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.plugin.util.Util;
import gov.nist.hit.elr.plugin.utils.MyTreeNode;
import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;

public class TestELR_037 {

	private static ELR_037 testObject;
	private static MyTreeNode<String> provider1;
	private static MyTreeNode<String> provider2;
	private static MyTreeNode<String> provider3;
	private static MyTreeNode<String> provider4;

	@BeforeClass
	public static void setUp() {
		testObject = new ELR_037();

		provider1 = new MyTreeNode<String>("");
		provider1.addChild(new MyTreeNode<String>("111111111"));
		provider1.addChild(new MyTreeNode<String>("Bloodraw"));
		provider1.addChild(new MyTreeNode<String>("Leonard"));
		provider1.addChild(new MyTreeNode<String>("T"));
		provider1.addChild(new MyTreeNode<String>("JR"));
		provider1.addChild(new MyTreeNode<String>("DR"));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		MyTreeNode<String> npi = new MyTreeNode<String>("");
		npi.addChild("NPI");
		npi.addChild("2.16.840.1.113883.4.6");
		npi.addChild("ISO");
		provider1.addChild(npi);
		provider1.addChild(new MyTreeNode<String>("L"));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		MyTreeNode<String> npi_fac = new MyTreeNode<String>("");
		npi_fac.addChild("NPI");
		npi_fac.addChild("NPI_Facility");
		npi_fac.addChild("2.16.840.1.113883.3.72.5.26");
		npi_fac.addChild("ISO");
		provider1.addChild(npi_fac);
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>(""));
		provider1.addChild(new MyTreeNode<String>("MD"));

		provider2 = new MyTreeNode<String>("");
		provider2.addChild(new MyTreeNode<String>("111111111"));
		provider2.addChild(new MyTreeNode<String>("Bloodraw"));
		provider2.addChild(new MyTreeNode<String>("Leonard"));
		provider2.addChild(new MyTreeNode<String>("T"));
		provider2.addChild(new MyTreeNode<String>("JR"));
		provider2.addChild(new MyTreeNode<String>("DR"));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		npi = new MyTreeNode<String>("");
		npi.addChild("NPI");
		npi.addChild("2.16.840.1.113883.4.6");
		npi.addChild("ISO");
		provider2.addChild(npi);
		provider2.addChild(new MyTreeNode<String>("L"));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		npi_fac = new MyTreeNode<String>("");
		npi_fac.addChild("NPI");
		npi_fac.addChild("NPI_Facility");
		npi_fac.addChild("2.16.840.1.113883.3.72.5.26");
		npi_fac.addChild("ISO");
		provider2.addChild(npi_fac);
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>(""));
		provider2.addChild(new MyTreeNode<String>("MD"));

		provider3 = new MyTreeNode<String>("");
		provider3.addChild(new MyTreeNode<String>("111111113"));
		provider3.addChild(new MyTreeNode<String>("Bloodraw"));
		provider3.addChild(new MyTreeNode<String>("Leonard"));
		provider3.addChild(new MyTreeNode<String>("T"));
		provider3.addChild(new MyTreeNode<String>("JR"));
		provider3.addChild(new MyTreeNode<String>("DR"));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		npi = new MyTreeNode<String>("");
		npi.addChild("NPI");
		npi.addChild("2.16.840.1.113883.4.6");
		npi.addChild("ISO");
		provider3.addChild(npi);
		provider3.addChild(new MyTreeNode<String>("L"));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		npi_fac = new MyTreeNode<String>("");
		npi_fac.addChild("NPI");
		npi_fac.addChild("NPI_Facility");
		npi_fac.addChild("2.16.840.1.113883.3.72.5.26");
		npi_fac.addChild("ISO");
		provider3.addChild(npi_fac);
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>(""));
		provider3.addChild(new MyTreeNode<String>("MD"));

		provider4 = new MyTreeNode<String>("");
		provider4.addChild(new MyTreeNode<String>("111111111"));
		provider4.addChild(new MyTreeNode<String>("Bloodraw"));
		provider4.addChild(new MyTreeNode<String>("Leonard"));
		provider4.addChild(new MyTreeNode<String>("T"));
		provider4.addChild(new MyTreeNode<String>("JR"));
		provider4.addChild(new MyTreeNode<String>("DR"));
		provider4.addChild(new MyTreeNode<String>(""));
		provider4.addChild(new MyTreeNode<String>(""));
		npi = new MyTreeNode<String>("");
		npi.addChild("NPI");
		npi.addChild("2.16.840.1.113883.4.6");
		npi.addChild("ISO");
		provider4.addChild(npi);
		provider4.addChild(new MyTreeNode<String>("L"));
		provider4.addChild(new MyTreeNode<String>(""));
		provider4.addChild(new MyTreeNode<String>(""));
		npi_fac = new MyTreeNode<String>("");
		npi_fac.addChild("NPI");
		npi_fac.addChild("NPI_Facility");
		npi_fac.addChild("2.16.840.1.113883.3.72.5.26");
		npi_fac.addChild("ISO");
		provider4.addChild(npi_fac);
		provider4.addChild(new MyTreeNode<String>("MD"));

	}

	@Test
	public void testCheckSucess() throws Exception {

		HashSet<MyTreeNode<String>> orc12 = new HashSet<MyTreeNode<String>>();
		HashSet<MyTreeNode<String>> obr16 = new HashSet<MyTreeNode<String>>();

		orc12.add(provider1);
		obr16.add(provider2);

		boolean result = testObject.check(orc12, obr16);
		assertTrue(result);

		orc12.add(provider2);
		result = testObject.check(orc12, obr16);
		assertTrue(result);

		orc12.add(provider3);
		obr16.add(provider3);
		result = testObject.check(orc12, obr16);
		assertTrue(result);
	}

	@Test
	public void testCheckFail() throws Exception {

		HashSet<MyTreeNode<String>> orc12 = new HashSet<MyTreeNode<String>>();
		HashSet<MyTreeNode<String>> obr16 = new HashSet<MyTreeNode<String>>();

		orc12.add(provider1);
		obr16.add(provider3);

		boolean result = testObject.check(orc12, obr16);
		assertFalse(result);

		orc12.removeAll(orc12);
		obr16.removeAll(obr16);

		orc12.add(provider1);
		obr16.add(provider4);

		result = testObject.check(orc12, obr16);
		assertFalse(result);

	}

	@Ignore
	@Test
	public void testMessage() throws Exception {

		String globalFolder = "/MessageProfile";

		String profiles = StringUtils.join(globalFolder, "/Profile.xml");
		String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
		String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

		String message1FileName = "TestMessages/ELR037/Message1.txt";

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
