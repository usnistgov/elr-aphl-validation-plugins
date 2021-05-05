package gov.nist.hit.elr.aphl.plugin.extra;

import java.util.HashSet;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.MyTreeNode;
import hl7.v2.instance.Complex;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class OBR_26 {

  /*
   * Context PATIENT_RESULT
   * 
   * If OBR-26 (Parent Result) is valued, then there SHALL be at least one OBX in the message where
   * OBX-3 (Observation Identifier) = OBR-26.1 (Parent Observation Identifier) AND OBX-4
   * (Observation Sub-ID) = OBR-26.2 (Parent Observation Sub-identifier)
   */

  /**
   * 
   * @param e the PATIENT_RESULT group
   * @return
   */
  public java.util.List<String> assertionWithCustomMessages(hl7.v2.instance.Element e) {
    java.util.List<String> messages = new java.util.ArrayList<String>();
    List<Element> OBRList = Query.query(e, "2[*].2[*]").get();

    if (OBRList.size() == 0) {
      // no OBR segment
      return messages;
    }
    List<Element> OBR26List = Query.query(e, "2[*].2[*].26[*]").get();

    // no OBR-26
    if (OBR26List.size() == 0) {
      return messages;
    }

    List<Element> OBXList = Query.query(e, "2[*].6[*].1[1]").get();
    Set<MyTreeNode<String>> OBXs = new HashSet<MyTreeNode<String>>();
    Iterator<Element> it2 = OBXList.iterator();
    while (it2.hasNext()) {
      Element next = it2.next();
      MyTreeNode<String> node = new MyTreeNode<String>("");

      // OBX-3
      Element OBX3 = Query.query(next, "3[*]").get().apply(0);
      if (OBX3 instanceof Complex) {
        MyTreeNode<String> node1 = toTreeNode((Complex) OBX3);
        node.addChild(node1);
      }

      // OBX-4
      List<Element> OBX4List = Query.query(next, "4[*]").get();
      if (OBX4List.size() == 0) {
        node.addChild("");
      } else {
        Element OBX4 = OBX4List.apply(0);
        if (OBX4 instanceof Simple) {
          MyTreeNode<String> node2 = toTreeNode((Simple) OBX4);
          node.addChild(node2);
        }
      }
      OBXs.add(node);
    }

    // parse each OBR-26 & check against list of OBX-3/OBX-4
    Iterator<Element> it = OBR26List.iterator();
    while (it.hasNext()) {
      MyTreeNode<String> OBR26 = new MyTreeNode<String>("");
      Element next = it.next();
      if (next instanceof Complex) {
        List<Element> components = ((Complex) next).children();
        MyTreeNode<String> OBR26_1 = new MyTreeNode<String>("");
        MyTreeNode<String> OBR26_2 = new MyTreeNode<String>("");
        if (components != null && components.size() > 0 && components.apply(0).position() == 1
            && components.apply(0) instanceof Complex) {
          // get OBR-26.1
          OBR26_1 = toTreeNode((Complex) components.apply(0));
        }
        if (components != null && components.size() > 0 && components.apply(0).position() == 1
            && components.apply(0) instanceof Simple) {
          // get OBR-26.1
          OBR26_1 = toTreeNode((Simple) components.apply(0));
        }
        if (components != null && components.size() > 1 && components.apply(1).position() == 2
            && components.apply(1) instanceof Simple) {
          // get OBR-26.2
          OBR26_2 = toTreeNode((Simple) components.apply(1));
        }
        OBR26.addChild(OBR26_1);
        OBR26.addChild(OBR26_2);
      }
      messages.addAll(check(OBR26, OBXs));
    }
    return messages;
  }

  public java.util.List<String> check(MyTreeNode<String> OBR26, Set<MyTreeNode<String>> OBXs) {
    java.util.List<String> messages = new java.util.ArrayList<String>();
    if (!OBXs.contains(OBR26)) {
      String obr26_1_1 = "";
      String obr26_1_3 = "";
      String obr26_2 = "";
      if (OBR26.hasChild(0)) {
        MyTreeNode<?> obr26_1 = OBR26.getChild(0);
        obr26_1_1 = !obr26_1.hasChild(0) ? "" : (String) obr26_1.getChild(0).getData();
        obr26_1_3 = !obr26_1.hasChild(2) ? "" : (String) obr26_1.getChild(2).getData();
        obr26_2 = !OBR26.hasChild(1) ? "" : (String) OBR26.getChild(1).getData();
      }
      messages.add(
          "No matching OBX-3/OBX-4 values found in the message for child the OBR [OBR-26.1.1 = '"
              + obr26_1_1 + "', OBR-26.1.3 = '" + obr26_1_3 + "', OBR-26.2 = '" + obr26_2 + "']");
    }
    return messages;
  }


  private MyTreeNode<String> toTreeNode(Complex c) {
    Iterator<Element> children = c.children().iterator();
    MyTreeNode<String> node = new MyTreeNode<String>("");
    while (children.hasNext()) {
      Element child = children.next();
      if (child instanceof Simple) {
        MyTreeNode<String> childNode = toTreeNode((Simple) child);
        node.addChild(childNode);
      } else if (child instanceof Complex) {
        MyTreeNode<String> childNode = toTreeNode((Complex) child);
        node.addChild(childNode);
      }
    }
    return node;
  }

  private MyTreeNode<String> toTreeNode(Simple s) {
    String data = s.value().raw();
    MyTreeNode<String> node = new MyTreeNode<String>(data);
    return node;
  }
}
