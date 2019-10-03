package gov.nist.hit.elr.plugin.cs;

import java.util.HashSet;
import java.util.Set;

import gov.nist.hit.elr.plugin.utils.MyTreeNode;
import hl7.v2.instance.Complex;
import hl7.v2.instance.Element;
import hl7.v2.instance.Query;
import hl7.v2.instance.Simple;
import scala.collection.Iterator;
import scala.collection.immutable.List;

public class ELR_038 {

  /**
   * ELR-038: ORC-14 (Call Back Phone Number) SHALL be the same value as OBR-17 within same
   * Order_Observation Group.
   */

  /**
   * 
   * @param e the ORDER_OBSERVATION group
   * @return
   */
  public boolean assertion(Element e) {

    List<Element> ORCList = Query.query(e, "1[*]").get();

    if (ORCList.size() == 0) {
      // no ORC segment
      return true;
    }

    List<Element> ORC14List = Query.query(e, "1[*].14[*]").get();
    List<Element> OBR17List = Query.query(e, "2[*].17[*]").get();

    if (ORC14List.size() == 0 && OBR17List.size() == 0) {
      return true;
    }
    // parse ORC-14
    Set<MyTreeNode<String>> ORC14 = new HashSet<MyTreeNode<String>>();
    Iterator<Element> it = ORC14List.iterator();
    while (it.hasNext()) {
      Element next = it.next();
      if (next instanceof Complex) {
        MyTreeNode<String> node = toTreeNode((Complex) next);
        ORC14.add(node);
      }
    }
    // parse OBR-17
    Set<MyTreeNode<String>> OBR17 = new HashSet<MyTreeNode<String>>();
    Iterator<Element> it2 = OBR17List.iterator();
    while (it2.hasNext()) {
      Element next = it2.next();
      if (next instanceof Complex) {
        MyTreeNode<String> node = toTreeNode((Complex) next);
        OBR17.add(node);
      }
    }
    boolean result = check(ORC14, OBR17);
    return result;
  }

  public boolean check(Set<MyTreeNode<String>> orc14, Set<MyTreeNode<String>> obr17) {
    return orc14.equals(obr17);
  }

  private MyTreeNode<String> toTreeNode(Simple s) {
    String data = s.value().raw();
    MyTreeNode<String> node = new MyTreeNode<String>(data);
    return node;
  }

  private MyTreeNode<String> toTreeNode(Complex c) {
    Iterator<Element> children = c.children().iterator();
    MyTreeNode<String> node = new MyTreeNode<String>("");
    while (children.hasNext()) {
      Element child = children.next();
      if (child instanceof Simple) {
        MyTreeNode<String> childNode = toTreeNode((Simple) child);
        node.addChild(childNode);
      }
      if (child instanceof Complex) {
        MyTreeNode<String> childNode = toTreeNode((Complex) child);
        node.addChild(childNode);
      }
    }
    return node;
  }

}
