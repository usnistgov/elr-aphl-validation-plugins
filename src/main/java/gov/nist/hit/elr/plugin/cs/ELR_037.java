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

public class ELR_037 {

	/**
	 * ELR-037: ORC-12 (Ordering Provider) SHALL be the same value as OBR-16 within
	 * same Order_Observation Group.
	 */

	/**
	 * 
	 * @param e the ORDER_OBSERVATION group
	 * @return
	 * @throws InterruptedException
	 */
	public boolean assertion(Element e) {

		List<Element> ORC12List = Query.query(e, "1[*].12[*]").get();
		List<Element> OBR16List = Query.query(e, "2[*].16[*]").get();

		if (ORC12List.size() == 0 && OBR16List.size() == 0) {
			return true;
		}
		// parse ORC-12
		Set<MyTreeNode<String>> ORC12 = new HashSet<MyTreeNode<String>>();
		Iterator<Element> it = ORC12List.iterator();
		while (it.hasNext()) {
			Element next = it.next();
			if (next instanceof Complex) {
				MyTreeNode<String> node = toTreeNode((Complex) next);
				ORC12.add(node);
			}
		}
		// parse OBR-16
		Set<MyTreeNode<String>> OBR16 = new HashSet<MyTreeNode<String>>();
		Iterator<Element> it2 = OBR16List.iterator();
		while (it2.hasNext()) {
			Element next = it2.next();
			if (next instanceof Complex) {
				MyTreeNode<String> node = toTreeNode((Complex) next);
				OBR16.add(node);
			}
		}
		boolean result = check(ORC12, OBR16);
		return result;
	}

	public boolean check(Set<MyTreeNode<String>> orc12, Set<MyTreeNode<String>> obr16) {
//		System.err.println(orc12.toString());
//		System.err.println(obr16.toString());
		return orc12.equals(obr16);
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
