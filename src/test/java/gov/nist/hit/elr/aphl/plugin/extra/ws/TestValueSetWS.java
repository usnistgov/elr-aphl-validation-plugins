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
  private static Set<String> Rabies;
  private static Set<String> VPD;


  @BeforeClass
  public static void setUp() {
    ARLN = new HashSet<String>();
    // value sets tab 20210603
    ARLN.add("ARLN Regional Labs");
    ARLN.add("ARLN microorganism");
    ARLN.add("Acinetobacter culture result");
    ARLN.add("Age unit");
    ARLN.add("Beta-lac results for GC");
    ARLN.add("C auris PCR result");
    ARLN.add("C auris culture result");
    ARLN.add("CRE AST method");
    ARLN.add("CRE PCR method");
    ARLN.add("CRE PCR results");
    ARLN.add("CRE Phenotypic method");
    ARLN.add("Carbapenemase phenotypic results");
    ARLN.add("Clinical sample or isolate");
    ARLN.add("Coded specimen type");
    ARLN.add("County_FIPS_6-4");
    ARLN.add("Ethnicity");
    ARLN.add("GC Facility codes");
    ARLN.add("GC specimen quality");
    ARLN.add("Gender");
    ARLN.add("Healthcare facility type");
    ARLN.add("Identifier type");
    ARLN.add("Index case resistance gene");
    ARLN.add("Index case species");
    ARLN.add("Interpretation codes");
    ARLN.add("Jurisdiction PHL codes for GC");
    ARLN.add("Method detail");
    ARLN.add("PHL - CRE CRPA");
    ARLN.add("Race");
    ARLN.add("Reason for screening");
    ARLN.add("Sex");
    ARLN.add("State");
    ARLN.add("Units for test results");
    ARLN.add("Yes No Indicator");
    ARLN.add("Yes No Unknown");

    // tests tab 20210603
    ARLN.add("Acinetobacter culture result");
    ARLN.add("ARLN microorganism");
    ARLN.add("ARLN Regional Labs");
    ARLN.add("Beta-lac results for GC");
    ARLN.add("C auris culture result");
    ARLN.add("C auris PCR result");
    ARLN.add("Carbapenemase phenotypic results");
    ARLN.add("Clinical sample or isolate");
    ARLN.add("CRE AST method");
    ARLN.add("CRE PCR method");
    ARLN.add("CRE PCR results");
    ARLN.add("CRE Phenotypic method");
    ARLN.add("GC Facility codes");
    ARLN.add("GC specimen quality");
    ARLN.add("Gender");
    ARLN.add("Healthcare facility type");
    ARLN.add("Identifier type");
    ARLN.add("Index case resistance gene");
    ARLN.add("Index case species");
    ARLN.add("Jurisdiction PHL codes for GC");
    ARLN.add("PHL - CRE CRPA");
    ARLN.add("Reason for screening");
    ARLN.add("Yes No Indicator");
    ARLN.add("Yes No Unknown");

    // value sets tab 20210607
    PHLIP = new HashSet<String>();
    PHLIP.add("Age value units");
    PHLIP.add("CF IF Neut result");
    PHLIP.add("Conclusion PCR result");
    PHLIP.add("Country");
    PHLIP.add("Drug test result");
    PHLIP.add("Echovirus");
    PHLIP.add("EIA result");
    PHLIP.add("Ethnicity");
    PHLIP.add("EV culture result");
    PHLIP.add("Flu A B virus screen DFA");
    PHLIP.add("Flu A H subtype");
    PHLIP.add("Flu B lineage");
    PHLIP.add("Flu B lineage PC R");
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
    PHLIP.add("Race");
    PHLIP.add("Respiratory pathogen PCR");
    PHLIP.add("Respiratory viruses");
    PHLIP.add("Results PLT103");
    PHLIP.add("Results PLT171");
    PHLIP.add("Results PLT196");
    PHLIP.add("Sample type tested results");
    PHLIP.add("SARS-CoV-2 variant lineage");
    PHLIP.add("Sex");
    PHLIP.add("Symptom");
    PHLIP.add("Target PCR result");
    PHLIP.add("Units of measure");
    PHLIP.add("Yes No");
    PHLIP.add("Yes No Unknown");

    // tests tab 20210607
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
    PHLIP.add("Pregnancy Status");
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
    PHLIP.add("SARS-CoV-2 variant lineage");
    PHLIP.add("Symptom");
    PHLIP.add("Target PCR result");
    PHLIP.add("Target PCR Result");
    PHLIP.add("Yes No Unknown");


    Rabies = new HashSet<String>();
    // value sets tab 20210408
    Rabies.add("AG_Results");
    Rabies.add("Age");
    Rabies.add("DeathType");
    Rabies.add("ExposureType");
    Rabies.add("ManufacturerName");
    Rabies.add("Ownership");
    Rabies.add("PCR_Results");
    Rabies.add("RabiesStrain");
    Rabies.add("Species");
    Rabies.add("Surveillance");
    Rabies.add("VaccinationStatus");
    Rabies.add("VaccineProduct");
    Rabies.add("YesNoUnknown");

    // tests tab 20210408
    Rabies.add("AG_Results");
    Rabies.add("Age");
    Rabies.add("DeathType");
    Rabies.add("ExposureType");
    Rabies.add("ManufacturerName");
    Rabies.add("Ownership");
    Rabies.add("PCR_Results");
    Rabies.add("RabiesStrain");
    Rabies.add("Species");
    Rabies.add("Surveillance");
    Rabies.add("VaccinationStatus");
    Rabies.add("VaccineProduct");
    Rabies.add("YesNoUnknown");

    VPD = new HashSet<String>();
    // tests tab 20210828
    VPD.add("(FASTA file)");
    // VPD.add("(Non-coded date/time value)");
    VPD.add("(Non-coded numeric value)");
    VPD.add("(Non-coded string value)");
    VPD.add("(Non-coded structured numeric value)");
    VPD.add("(Non-coded text)");
    VPD.add("(PDF file result)");
    VPD.add("Antibiotics for pertussis");
    VPD.add("Bacterial culture");
    VPD.add("Bordetella ptxS1 marker Ct interpretation");
    VPD.add("Bordetella sp PCR");
    VPD.add("Clinical specimen or isolate");
    VPD.add("Ct interpretation with Indeterminate");
    VPD.add("Ct value interpretation");
    VPD.add("Culture ordinal");
    VPD.add("Detected, Not detected");
    VPD.add("H. influenzae serotyping PCR");
    VPD.add("Measles genotyping");
    VPD.add("Mumps genotyping");
    VPD.add("N. meningitidis serogrouping PCR");
    VPD.add("PCR ordinal");
    VPD.add("PCR ordinal - bacterial");
    VPD.add("PCR ordinal with Indeterminate");
    VPD.add("Pertussis toxin serology ordinal");
    VPD.add("Positive, Negative");
    VPD.add("RNase P PCR ordinal");
    VPD.add("Rubella genotyping");
    VPD.add("S. pneumoniae serotype");
    VPD.add("S. pneumoniae serotyping PCR conventional");
    VPD.add("S. pneumoniae serotyping PCR real time");
    VPD.add("S. pneumoniae WGS result");
    VPD.add("Serology ordinal");
    VPD.add("Streptococcus culture");
    VPD.add("VPD Specimen Type");
    VPD.add("VZV genotyping");
    VPD.add("VZV strain PCR");
    VPD.add("VZV strain SNP PCR");
    VPD.add("Yes, No, Unknown");

    // tests tab 20210828
    VPD.add("(FASTA file)");
    // VPD.add("(Non-coded date/time value)");
    VPD.add("(Non-coded numeric value)");
    VPD.add("(Non-coded string value)");
    VPD.add("(Non-coded structured numeric value)");
    VPD.add("(Non-coded text)");
    VPD.add("Antibiotics for pertussis");
    VPD.add("Bacterial culture");
    VPD.add("Bordetella sp PCR");
    VPD.add("Clinical specimen or isolate");
    VPD.add("Ct interpretation with Indeterminate");
    VPD.add("Ct value interpretation");
    VPD.add("Culture ordinal");
    VPD.add("H. influenzae serotyping PCR");
    VPD.add("Measles genotyping");
    VPD.add("Mumps genotyping");
    VPD.add("N. meningitidis serogrouping PCR");
    VPD.add("PCR ordinal");
    VPD.add("PCR ordinal with Indeterminate");
    VPD.add("Pertussis toxin serology ordinal");
    VPD.add("RNase P PCR ordinal");
    VPD.add("Rubella genotyping");
    VPD.add("S. pneumoniae serotype");
    VPD.add("S. pneumoniae serotyping PCR conventional");
    VPD.add("S. pneumoniae serotyping PCR real time");
    VPD.add("S. pneumoniae WGS result");
    VPD.add("Serology ordinal");
    VPD.add("Streptococcus culture");
    VPD.add("VZV genotyping");
    VPD.add("VZV strain PCR");
    VPD.add("Yes, No, Unknown");


  }

  @Test
  public void testARLN() throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    for (String valueSetName : ARLN) {
      // System.err.println(valueSetName);
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

  @Test
  public void testRabies() throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    for (String valueSetName : Rabies) {
      List<ValueSet> valueSet = ws.getValueSet(Program.APHL_RABIES, valueSetName);
      assertEquals(1, valueSet.size());
    }
  }

  @Test
  public void testVPD() throws IOException, InterruptedException, URISyntaxException {
    WSUtils ws = new WSUtils();
    for (String valueSetName : VPD) {
      // System.err.println(valueSetName);
      List<ValueSet> valueSet = ws.getValueSet(Program.APHL_VPD, valueSetName);
      if (valueSet.size() == 0) {
        System.err.println(valueSetName);
      }
      assertEquals(1, valueSet.size());
    }
  }

}
