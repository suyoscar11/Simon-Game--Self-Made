import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BST<E extends Comparable<E>> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;

  // Other methods and constructors

  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object element : c) {
      if (!contains(element)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    for (E element : c) {
      if (insert(element)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Object element : c) {
      if (delete(element)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    boolean modified = false;
    Iterator<E> iterator = iterator();
    while (iterator.hasNext()) {
      E element = iterator.next();
      if (!c.contains(element)) {
        iterator.remove();
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];
    int index = 0;
    for (E element : this) {
      array[index++] = element;
    }
    return array;
  }

  @Override
  public <T> T[] toArray(T[] array) {
    if (array.length < size) {
      array = (T[]) java.lang.reflect.Array.newInstance(
        array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }

    int index = 0;
    for (E element : this) {
      array[index++] = (T) element;
    }

    if (index < array.length) {
      array[index] = null;
    }

    return array;
  }

  // Other methods and classes

  // TreeNode class
  protected static class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }
}
