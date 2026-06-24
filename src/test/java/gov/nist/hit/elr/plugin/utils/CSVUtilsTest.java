package gov.nist.hit.elr.plugin.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests classpath-based CSV loading and column-name mismatch resolution in CSVUtils.
 *
 * Fixture files live in src/test/resources/csv/ and are loaded via the "classpath:" prefix.
 * Standard-header files exercise the normal path; mismatch files exercise getField() fallback.
 */
public class CSVUtilsTest {

    private static final String CLASSPATH = "classpath:csv/";

    // -------------------------------------------------------------------------
    // classpath: prefix — file-not-found case
    // -------------------------------------------------------------------------

    @Test
    public void testClasspathResourceNotFound_throwsIOException() throws IOException {
        CSVUtils util = new CSVUtils();
        try {
            util.parseValueSetsCSV("classpath:csv/does_not_exist.csv");
            fail("Expected IOException for missing classpath resource");
        } catch (IOException e) {
            assertTrue("Message should mention the resource path",
                    e.getMessage().contains("does_not_exist.csv"));
        }
    }

    // -------------------------------------------------------------------------
    // parseValueSetsCSV — standard column names via classpath
    // -------------------------------------------------------------------------

    @Test
    public void testParseValueSetsCSV_classpathStandardHeaders() throws IOException {
        CSVUtils util = new CSVUtils();
        util.parseValueSetsCSV(CLASSPATH + "test_value_sets_standard.csv");

        Map<String, Set<CodedElement>> valueSets = util.getValueSets();
        assertNotNull(valueSets);
        assertTrue("Value set 'resistance genes' must be present",
                valueSets.containsKey("resistance genes"));

        Set<CodedElement> codes = valueSets.get("resistance genes");
        assertEquals(3, codes.size());
        assertTrue(codes.contains(new CodedElement("blaKPC", "L")));
        assertTrue(codes.contains(new CodedElement("blaNDM", "L")));
        assertTrue(codes.contains(new CodedElement("blaOXA", "L")));
    }

    // -------------------------------------------------------------------------
    // parseValueSetsCSV — mismatched column names via classpath
    // Headers: "ARLN Value Set Name", "Code", "Code System"
    //   "ARLN Value Set Name" must match "Value Set Name" via suffix fallback
    //   "Code System" must match "CodeSystem" via stripped-space fallback
    // -------------------------------------------------------------------------

    @Test
    public void testParseValueSetsCSV_classpathMismatchedHeaders() throws IOException {
        CSVUtils util = new CSVUtils();
        util.parseValueSetsCSV(CLASSPATH + "test_value_sets_mismatch.csv");

        Map<String, Set<CodedElement>> valueSets = util.getValueSets();
        assertTrue("Value set 'resistance genes' must be resolved despite column name mismatch",
                valueSets.containsKey("resistance genes"));

        Set<CodedElement> codes = valueSets.get("resistance genes");
        assertEquals(3, codes.size());
        assertTrue(codes.contains(new CodedElement("blaKPC", "L")));
        assertTrue(codes.contains(new CodedElement("blaNDM", "L")));
        assertTrue(codes.contains(new CodedElement("blaOXA", "L")));
    }

    // -------------------------------------------------------------------------
    // parse() — full pipeline via classpath (tests, observations, orders, value sets)
    // -------------------------------------------------------------------------

    @Test
    public void testParse_classpathAllFiles() throws IOException {
        CSVUtils util = new CSVUtils();
        util.parse(
                CLASSPATH + "test_tests_standard.csv",
                CLASSPATH + "test_observations_standard.csv",
                CLASSPATH + "test_orders_standard.csv",
                CLASSPATH + "test_value_sets_standard.csv");

        // OBR4
        Set<CodedElement> obr4 = util.getOBR4();
        assertTrue("OBR4 must contain 50545-3/LN", obr4.contains(new CodedElement("50545-3", "LN")));

        // OBX3
        Set<CodedElement> obx3 = util.getOBX3();
        assertTrue("OBX3 must contain 6463-4/LN", obx3.contains(new CodedElement("6463-4", "LN")));
        assertTrue("OBX3 must contain blaKPC/L", obx3.contains(new CodedElement("blaKPC", "L")));

        // OBR4->OBX3 relationship
        Map<CodedElement, Set<CodedElement>> obr4_obx3 = util.getOBR4_OBX3();
        Set<CodedElement> obx3ForObr4 = obr4_obx3.get(new CodedElement("50545-3", "LN"));
        assertNotNull(obx3ForObr4);
        assertTrue(obx3ForObr4.contains(new CodedElement("6463-4", "LN")));
        assertTrue(obx3ForObr4.contains(new CodedElement("blaKPC", "L")));

        // OBX3->OBX5 (value set mapping)
        Map<CodedElement, String> obx3_obx5 = util.getOBX3_OBX5();
        assertEquals("resistance genes", obx3_obx5.get(new CodedElement("6463-4", "LN")));

        // Value sets populated
        Map<String, Set<CodedElement>> valueSets = util.getValueSets();
        assertTrue(valueSets.containsKey("resistance genes"));
    }
}
