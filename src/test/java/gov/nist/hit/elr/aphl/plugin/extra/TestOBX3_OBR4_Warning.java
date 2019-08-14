package gov.nist.hit.elr.aphl.plugin.extra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.hit.elr.aphl.plugin.extra.OBX3_OBR4_Warning;
import gov.nist.hit.elr.plugin.utils.ComplexCodedElement;

public class TestOBX3_OBR4_Warning {

	// SUCCESS
	// use case : OBX-3/OBR-4 is in "Tests"

	// FAIL
	// use case : OBX-3 not in "Tests" and not in "Observations"

	// WARNING - not caught by OBX3_OBR4
	// use case : OBX-3 in in "Tests", but OBR-4 is associated to another OBX-3
	// use case : OBX-3 in in "Tests", OBR-4 is not present in "Tests" but it is
	// present in "Orders"
	// use case : OBX-3 is in "Tests", OBR-4 is not present in "Tests" and not
	// present in "Orders"

	// use case : OBX-3 is not present in "Tests", but is present in "Observations",
	// OBR-4 is present in "Tests"
	// use case : OBX-3 is not present in "Tests", but is present in "Observations",
	// OBR-4 is not present in "Tests", but it is present in "Orders"
	// use case : OBX-3 is not present in "Tests", but is present in "Observations",
	// OBR-4 is not present in "Tests", and not present in "Orders"

	private static OBX3_OBR4_Warning testObject;

	private static ComplexCodedElement OBR4;
	private static List<ComplexCodedElement> OBX3s;

	@BeforeClass
	public static void setUp() {
		testObject = new OBX3_OBR4_Warning();
		OBX3s = new ArrayList<ComplexCodedElement>();
	}

	@Before
	public void resetData() {
		OBR4 = null;
		OBX3s.removeAll(OBX3s);
	}

	@Test
	@Ignore
	public void testCheckSuccess() throws IOException {
		// OBX-3 = 11368-8 LN & OBR-4 = 68991-9 LN

		ComplexCodedElement OBX3_identifier = new ComplexCodedElement("11368-8", "LN", "", "");
		OBX3s.add(OBX3_identifier);
		ComplexCodedElement OBX3_alternate = new ComplexCodedElement("", "", "11368-8", "LN");
		OBX3s.add(OBX3_alternate);
		ComplexCodedElement OBX3_identifier_2 = new ComplexCodedElement("11368-8", "LN", "ABC", "L");
		OBX3s.add(OBX3_identifier_2);
		ComplexCodedElement OBX3_alternate_2 = new ComplexCodedElement("ABC", "L", "11368-8", "LN");
		OBX3s.add(OBX3_alternate_2);

		OBR4 = new ComplexCodedElement("68991-9", "LN", "", "");
		List<String> result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("", "", "68991-9", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("68991-9", "LN", "ABC", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("ABC", "L", "68991-9", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

	}

	@Test
	@Ignore
	public void testCheckFail() throws IOException {
		// OBX-3 = 00000-0 LN & OBR-4 = 68991-9 LN

		ComplexCodedElement OBX3_identifier = new ComplexCodedElement("00000-0", "LN", "", "");
		OBX3s.add(OBX3_identifier);
		ComplexCodedElement OBX3_alternate = new ComplexCodedElement("", "", "00000-0", "LN");
		OBX3s.add(OBX3_alternate);
		ComplexCodedElement OBX3_identifier_2 = new ComplexCodedElement("00000-0", "LN", "ABC", "L");
		OBX3s.add(OBX3_identifier_2);
		ComplexCodedElement OBX3_alternate_2 = new ComplexCodedElement("ABC", "L", "00000-0", "LN");
		OBX3s.add(OBX3_alternate_2);

		OBR4 = new ComplexCodedElement("68991-9", "LN", "", "");
		List<String> result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("", "", "68991-9", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("68991-9", "LN", "ABC", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("ABC", "L", "68991-9", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());

		OBR4 = new ComplexCodedElement("ABC", "L", "XYZ", "M");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(0, result.size());
	}

	@Test
	public void testCheckWarning() throws IOException {
		// use case : OBX-3 in in "Tests", but OBR-4 is associated to another OBX-3
		// OBX-3 = 11368-8 LN & OBR-4 = 12237-4 LN

		ComplexCodedElement OBX3_identifier = new ComplexCodedElement("11368-8", "LN", "", "");
		OBX3s.add(OBX3_identifier);
		ComplexCodedElement OBX3_alternate = new ComplexCodedElement("", "", "11368-8", "LN");
		OBX3s.add(OBX3_alternate);
		ComplexCodedElement OBX3_identifier_2 = new ComplexCodedElement("11368-8", "LN", "ABC", "L");
		OBX3s.add(OBX3_identifier_2);
		ComplexCodedElement OBX3_alternate_2 = new ComplexCodedElement("ABC", "L", "11368-8", "LN");
		OBX3s.add(OBX3_alternate_2);

		OBR4 = new ComplexCodedElement("12237-4", "LN", "", "");
		List<String> result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("", "", "12237-4", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("12237-4", "LN", "ABC", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("ABC", "L", "12237-4", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		// use case : OBX-3 is in "Tests", OBR-4 is not present in "Tests" but it is
		// present in "Orders"
		// no code in spreadsheet to test that use case

		// use case : OBX-3 is in "Tests", OBR-4 is not present in "Tests" and not
		// present in "Orders"
		OBR4 = new ComplexCodedElement("0000-0", "LN", "", "");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("", "", "0000-0", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("0000-0", "LN", "11111-1", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		// use case : OBX-3 is not present in "Tests", but is present in "Observations",
		// OBR-4 is present in "Tests"

		OBX3s.removeAll(OBX3s);

		OBX3_identifier = new ComplexCodedElement("86317-5", "LN", "", "");
		OBX3s.add(OBX3_identifier);
		OBX3_alternate = new ComplexCodedElement("", "", "86317-5", "LN");
		OBX3s.add(OBX3_alternate);
		OBX3_identifier_2 = new ComplexCodedElement("86317-5", "LN", "ABC", "L");
		OBX3s.add(OBX3_identifier_2);
		OBX3_alternate_2 = new ComplexCodedElement("ABC", "L", "86317-5", "LN");
		OBX3s.add(OBX3_alternate_2);

		OBR4 = new ComplexCodedElement("12237-4", "LN", "", "");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("", "", "12237-4", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("12237-4", "LN", "ABC", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("ABC", "L", "12237-4", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		// use case : OBX-3 is not present in "Tests", but is present in "Observations",
		// OBR-4 is not present in "Tests", but it is present in "Orders"
		// no code in spreadsheet to test that use case

		// use case : OBX-3 is not present in "Tests", but is present in "Observations",
		// OBR-4 is not present in "Tests", and not present in "Orders"
		OBR4 = new ComplexCodedElement("0000-0", "LN", "", "");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("", "", "0000-0", "LN");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

		OBR4 = new ComplexCodedElement("0000-0", "LN", "11111-1", "L");
		result = testObject.check(OBR4, OBX3s);
		assertEquals(4, result.size());

	}

}
