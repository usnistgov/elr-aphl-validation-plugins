package gov.nist.hit.elr.plugin.utils;

import java.util.ArrayList;
import java.util.List;

public class MyTreeNode<T> {
  private T data = null;
  private List<MyTreeNode<?>> children = new ArrayList<>();
  private MyTreeNode<?> parent = null;

  public MyTreeNode(T data) {
    this.data = data;
  }

  public void addChild(MyTreeNode<?> child) {
    child.setParent(this);
    this.children.add(child);
  }

  public void addChild(T data) {
    MyTreeNode<T> newChild = new MyTreeNode<>(data);
    this.addChild(newChild);
  }

  public void addChildren(List<MyTreeNode<?>> children) {
    for (MyTreeNode<?> t : children) {
      t.setParent(this);
    }
    this.children.addAll(children);
  }

  public List<MyTreeNode<?>> getChildren() {
    return children;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  private void setParent(MyTreeNode<?> parent) {
    this.parent = parent;
  }

  public MyTreeNode<?> getParent() {
    return parent;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((children == null) ? 0 : children.hashCode());
    result = prime * result + ((data == null) ? 0 : data.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MyTreeNode other = (MyTreeNode) obj;
    if (children == null) {
      if (other.children != null)
        return false;
    } else if (!children.equals(other.children))
      return false;
    if (data == null) {
      if (other.data != null)
        return false;
    } else if (!data.equals(other.data))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MyTreeNode [data=" + data + ", children=" + children + "]";
  }

}
