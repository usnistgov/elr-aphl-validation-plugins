package gov.nist.hit.elr.aphl.plugin.extra.ws;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import gov.nist.hit.elr.aphl.domain.Program;
import gov.nist.hit.elr.aphl.domain.vocab.ValueSet;
import gov.nist.hit.elr.plugin.utils.WSUtils;

public class TestValueSetWS {

  private static Set<String> ARLN;
  private static Set<String> PHLIP;

  @BeforeClass
  public static void setUp() {
    ARLN = new HashSet<String>();
    ARLN.add("CRE AST method");
    ARLN.add("ARLN microorganism");
    ARLN.add("CRE PCR results");
    ARLN.add("Carbapenemase phenotypic results");
    ARLN.add("GC specimen quality");
    ARLN.add("Beta-lac results for GC");
    ARLN.add("Yes No Indicator");
    ARLN.add("C auris PCR result");
    ARLN.add("C auris culture result");
    ARLN.add("Acinetobacter culture result");
    ARLN.add("Clinical sample or isolate");
    ARLN.add("CRE PCR method");
    ARLN.add("CRE Phenotypic method");
    ARLN.add("Gender");
    ARLN.add("ARLN Regional Labs");
    ARLN.add("PHL - CRE CRPA");
    ARLN.add("Jurisdiction PHL codes for GC");
    ARLN.add("Yes No Unknown");
    ARLN.add("GC Facility codes");
    ARLN.add("Index case resistance gene");
    ARLN.add("Index case species");
    ARLN.add("Reason for screening");
    ARLN.add("Identifier type");
    ARLN.add("Healthcare facility type");

    PHLIP = new HashSet<String>();
    // PHLIP.add("(no value set defined)");
    // PHLIP.add("(Non-coded date)");
    // PHLIP.add("(Non-coded ID format)");
    // PHLIP.add("(Non-coded number)");
    // PHLIP.add("(Non-coded text)");
    PHLIP.add("CF IF Neut result");
    PHLIP.add("Conclusion PCR result");
    PHLIP.add("Country");
    PHLIP.add("Drug test result");
    PHLIP.add("EIA result");
    PHLIP.add("Flu A B virus screen DFA");
    PHLIP.add("Flu A H subtype");
    PHLIP.add("Flu B lineage");
    PHLIP.add("Flu B lineage PCR");
    PHLIP.add("Flu viruses");
    PHLIP.add("Flu viruses HAI");
    PHLIP.add("HAI results");
    PHLIP.add("IA result");
    PHLIP.add("Non-respiratory pathogens");
    PHLIP.add("Patient location");
    PHLIP.add("PCR Commercial Conclusion result");
    PHLIP.add("Pregnancy status");
    PHLIP.add("Pre-screen method");
    PHLIP.add("Pre-screen results");
    PHLIP.add("Program list");
    PHLIP.add("Pyro results");
    PHLIP.add("Ql culture result");
    PHLIP.add("Respiratory pathogen PCR");
    PHLIP.add("Respiratory viruses");
    PHLIP.add("Results PLT103");
    PHLIP.add("Results PLT171");
    PHLIP.add("Results PLT196");
    PHLIP.add("Sample type tested results");
    PHLIP.add("Target PCR result");
    PHLIP.add("Yes No");
    PHLIP.add("Yes No Unknown");
  }

  @Test
  public void testARLN() throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    for (String valueSetName : ARLN) {
      System.err.println(valueSetName);
      List<ValueSet> valueSet = ws.getValueSet(Program.APHL_ARLN, valueSetName);
      assertEquals(1, valueSet.size());
    }
  }

  @Test
  public void testPHLIP() throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    for (String valueSetName : PHLIP) {
      List<ValueSet> valueSet = ws.getValueSet(Program.APHL_PHLIP, valueSetName);
      assertEquals(1, valueSet.size());
    }
  }

}
