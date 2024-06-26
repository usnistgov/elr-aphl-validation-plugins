package gov.nist.hit.elr.plugin.util;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import gov.nist.validation.report.Entry;
import gov.nist.validation.report.Report;
import hl7.v2.validation.SyncHL7Validator;
import hl7.v2.validation.ValidationContext;
import hl7.v2.validation.ValidationContextBuilder;

public class TestValidation {

  @Test
  public void testValidation() throws Exception {

    String globalFolder = "/radx";
    String profile = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints.xml");
    String valueSetLibrary = StringUtils.join(globalFolder, "/ValueSets.xml");
    String valueSetBindings = StringUtils.join(globalFolder, "/ValueSetBindings.xml");

    InputStream profileXML = TestValidation.class.getResourceAsStream(profile);
    InputStream constraintsXML = TestValidation.class.getResourceAsStream(constraints);
    InputStream valueSetLibraryXML = TestValidation.class.getResourceAsStream(valueSetLibrary);
    InputStream valueSetBindingsXML = TestValidation.class.getResourceAsStream(valueSetBindings);


    String message1FileName = "radx/Message.txt";


    ValidationContext context = new ValidationContextBuilder(profileXML)
        .useConformanceContext(Arrays.asList(constraintsXML)) // Optional
        .useValueSetLibrary(valueSetLibraryXML) // Optional
        .useVsBindings(valueSetBindingsXML) // Optional
        // .useSlicingContext(slicingsXML) // Optional
        // .useCoConstraintsContext(coConstraintsXML) // Optional
        .getValidationContext();

    SyncHL7Validator validator = new SyncHL7Validator(context);

    ClassLoader classLoader = getClass().getClassLoader();
    File message1 = new File(classLoader.getResource(message1FileName).getFile());
    String messageString = FileUtils.readFileToString(message1);
    Report report = validator.check(messageString, "62878a0d8b87bc0007558731");
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
            case "Spec Error":
              Util.printEntry(entry);
              alerts++;
              break;
            // case "Alert":
            // Util.printEntry(entry);
            // alerts++;
            // break;
          }
        }
      }
    }
  }

  @Test
  @Ignore
  public void testValidationWS() throws Exception {

    String globalFolder = "/WS";

    String profiles = StringUtils.join(globalFolder, "/Profile.xml");
    String constraints = StringUtils.join(globalFolder, "/Constraints_ws.xml");
    String valueSets = StringUtils.join(globalFolder, "/ValueSets.xml");

    String message1FileName = "WS/Message.txt";

    SyncHL7Validator validator = Util.createValidator(profiles, constraints, null, valueSets);
    ClassLoader classLoader = getClass().getClassLoader();
    File message1 = new File(classLoader.getResource(message1FileName).getFile());
    String messageString = FileUtils.readFileToString(message1);
    Report report = validator.check(messageString, "5d5d68ce6dc12f5d54495e15");

    Set<String> keys = report.getEntries().keySet();
    int errors = 0;
    int alerts = 0;
    for (String key : keys) {
      List<Entry> entries = report.getEntries().get(key);
      if (entries != null && entries.size() > 0) {
        // System.out.println("*** " + key + " ***");
        for (Entry entry : entries) {
          switch (entry.getClassification()) {
            case "Error":
              // Util.printEntry(entry);
              errors++;
              break;
            case "Alert":
              // Util.printEntry(entry);
              alerts++;
              break;
          }
        }
      }
    }
  }

}
