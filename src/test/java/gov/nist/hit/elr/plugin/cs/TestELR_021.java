package gov.nist.hit.elr.plugin.cs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.apache.commons.collections4.SetUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.plugin.cs.ELR_021;

public class TestELR_021 {

	/**
	 * ELR-021: MSH.21.1 (Entity Identifier) of an occurrence of MSH.21 (Message
	 * Profile Identifier) SHALL be valued with 'PHLabReport-Ack' OR
	 * 'PHLabReport-NoAck' OR 'PHLabReport-Batch'
	 */

	private static ELR_021 testObject;

	@BeforeClass
	public static void setUp() {
		testObject = new ELR_021();
	}

	@Test
	public void testCheckSuccess() {
		HashSet<String> actual = SetUtils.hashSet("PHLabReport-Ack");
		boolean result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("PHLabReport-NoAck");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("PHLabReport-Batch");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("ABC", "PHLabReport-Ack");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("ABC", "PHLabReport-NoAck");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("ABC", "PHLabReport-Batch");
		result = testObject.check(actual);
		assertTrue(result);

		/*
		 * With current implementation, this is valid. May change in the future.
		 */
		actual = SetUtils.hashSet("PHLabReport-Ack", "PHLabReport-NoAck", "PHLabReport-Batch");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("PHLabReport-Ack", "PHLabReport-Ack");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("PHLabReport-NoAck", "PHLabReport-NoAck");
		result = testObject.check(actual);
		assertTrue(result);

		actual = SetUtils.hashSet("PHLabReport-Batch", "PHLabReport-Batch");
		result = testObject.check(actual);
		assertTrue(result);
	}

	@Test
	public void testCheckFail() {
		HashSet<String> actual = SetUtils.hashSet("");
		boolean result = testObject.check(actual);
		assertFalse(result);

		actual = SetUtils.hashSet("ABC");
		result = testObject.check(actual);
		assertFalse(result);

		actual = SetUtils.hashSet("ABC", "DEF");
		result = testObject.check(actual);
		assertFalse(result);

		result = testObject.check(null);
		assertFalse(result);
	}
}
