package gov.nist.hit.elr.plugin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import gov.nist.validation.report.Entry;
import hl7.v2.profile.XMLDeserializer;
import hl7.v2.validation.SyncHL7Validator;
import hl7.v2.validation.content.ConformanceContext;
import hl7.v2.validation.content.DefaultConformanceContext;
import hl7.v2.validation.vs.ValueSetLibrary;
import hl7.v2.validation.vs.ValueSetLibraryImpl;

public class Util {

  public static SyncHL7Validator createValidator(String globalProfileFileName,
      String globalConstraintsFileName, File specificConstraintsFile,
      String globalValueSetLibFileName) throws Exception {

    // The profile in XML
    InputStream profileXML = Util.class.getResourceAsStream(globalProfileFileName);

    // The default conformance context file XML
    InputStream contextXML = Util.class.getResourceAsStream(globalConstraintsFileName);

    // The test case specific conformance context file XML
    List<InputStream> confContexts = null;
    if (specificConstraintsFile != null && specificConstraintsFile.exists()) {
      InputStream specificContextXML = new FileInputStream(specificConstraintsFile);
      confContexts = Arrays.asList(contextXML, specificContextXML);
    } else {

      confContexts = Arrays.asList(contextXML);
    }

    ConformanceContext context = DefaultConformanceContext.apply(confContexts).get();

    // The value set library file XML
    InputStream vsLibXML = Util.class.getResourceAsStream(globalValueSetLibFileName);

    // The get() at the end will throw an exception if something goes wrong

    hl7.v2.profile.Profile profile = XMLDeserializer.deserialize(profileXML).get();
    ValueSetLibrary valueSetLibrary = ValueSetLibraryImpl.apply(vsLibXML).get();
    // A java friendly version of an HL7 validator
    // This should be a singleton for a specific tool. We create it once
    // and
    // reuse it across validations
    return new SyncHL7Validator(profile, valueSetLibrary, context);
  }

  public static void printEntry(Entry entry) {
    if (entry instanceof gov.nist.validation.report.impl.EntryImpl) {
      System.out.println(entry);
    } else if (entry instanceof hl7.v2.validation.vs.EnhancedEntry) {
      System.out.println(entry.toText());
    }
  }
}
