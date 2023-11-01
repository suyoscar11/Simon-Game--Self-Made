import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BST<E> implements Tree<E> {
  protected TreeNode<E> root;
  protected int size = 0;
  protected java.util.Comparator<E> c;

  public BST() {
    this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
  }

  public BST(java.util.Comparator<E> c) {
    this.c = c;
  }

  public BST(E[] objects) {
    this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  // Search for an element in the BST
  public boolean search(E e) {
    TreeNode<E> current = root;

    while (current != null) {
      if (c.compare(e, current.element) < 0) {
        current = current.left;
      } else if (c.compare(e, current.element) > 0) {
        current = current.right;
      } else
        return true;
    }

    return false;
  }

  // Insert an element into the BST
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e);
    else {
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (c.compare(e, current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (c.compare(e, current.element) > 0) {
          parent = current;
          current = current.right;
        } else
          return false;

      if (c.compare(e, parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true;
  }

  // Create a new tree node with the given element
  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<>(e);
  }

  // Perform an inorder traversal of the tree
  public void inorder() {
    inorder(root);
  }

  // Helper method to perform an inorder traversal from a subtree
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  // Perform a postorder traversal of the tree
  public void postorder() {
    postorder(root);
  }

  // Helper method to perform a postorder traversal from a subtree
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  // Perform a preorder traversal of the tree
  public void preorder() {
    preorder(root);
  }

  // Helper method to perform a preorder traversal from a subtree
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  // TreeNode class for representing nodes in the BST
  public static class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  // Get the number of nodes in the tree
  public int getSize() {
    return size;
  }

  // Returns the root of the tree
  public TreeNode<E> getRoot() {
    return root;
  }

  // Find a path from the root to a specific element
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
    TreeNode<E> current = root;

    while (current != null) {
      list.add(current);
      if (c.compare(e, current.element) < 0) {
        current = current.left;
      } else if (c.compare(e, current.element) > 0) {
        current = current.right;
      } else
        break;
    }

    return list;
  }

  // Delete an element from the binary tree
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (c.compare(e, current.element) < 0) {
        parent = current;
        current = current.left;
      } else if (c.compare(e, current.element) > 0) {
        parent = current;
        current = current.right;
      } else
        break; // Element is in the tree

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left child
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      } else {
        if (c.compare(e, parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    } else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        parentOfRightMost.left = rightMost.left;
    }

    size--; // Reduce the size of the tree
    return true; // Element deleted successfully
  }

  // Get the height of the tree
  public int height() {
    return height(root);
  }

  // Helper method to calculate the height from a given node
  private int height(TreeNode<E> root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  // Perform a breadth-first traversal of the tree
  public void breadthFirstTraversal() {
    if (root == null) {
      return;
    }

    Queue<TreeNode<E>> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode<E> current = queue.poll();
      System.out.print(current.element + " ");
      if (current.left != null) {
        queue.offer(current.left);
      }
      if (current.right != null) {
        queue.offer(current.right);
      }
    }
  }

  // Count the number of leaf nodes in the tree
  public int getNumberOfLeaves() {
    return getNumberOfLeaves(root);
  }

  // Helper method to count the number of leaf nodes from a given node
  private int getNumberOfLeaves(TreeNode<E> root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
  }

  // Count the number of non-leaf nodes in the tree
  public int getNumberOfNonLeaves() {
    return getNumberOfNonLeaves(root);
  }

  // Helper method to count the number of non-leaf nodes from a given node
  private int getNumberOfNonLeaves(TreeNode<E> root) {
    if (root == null) {
      return 0;
    }
    if (root.left != null || root.right != null) {
      return 1 + getNumberOfNonLeaves(root.left) + getNumberOfNonLeaves(root.right);
    }
    return 0;
  }

  // Perform a non-recursive inorder traversal of the tree
  public void nonRecursiveInorder() {
    if (root == null) {
      return;
    }

    Stack<TreeNode<E>> stack = new Stack<>();
    TreeNode<E> current = root;

    while (current != null || !stack.isEmpty()) {
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
      current = stack.pop();
      System.out.print(current.element + " ");
      current = current.right;
    }
  }

  // Perform a non-recursive postorder traversal of the tree
  public void nonRecursivePostorder() {
    if (root == null) {
      return;
    }

    Stack<TreeNode<E>> stack = new Stack<>();
    stack.push(root);
    Stack<TreeNode<E>> outputStack = new Stack<>();

    while (!stack.isEmpty()) {
      TreeNode<E> current = stack.pop();
      outputStack.push(current);

      if (current.left != null) {
        stack.push(current.left);
      }
      if (current.right != null) {
        stack.push(current.right);
      }
    }

    while (!outputStack.isEmpty()) {
      System.out.print(outputStack.pop().element + " ");
    }
  }

  // Perform a non-recursive preorder traversal of the tree
  public void nonRecursivePreorder() {
    if (root == null) {
      return;
    }

    Stack<TreeNode<E>> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode<E> current = stack.pop();
      System.out.print(current.element + " ");

      if (current.right != null) {
        stack.push(current.right);
      }
      if (current.left != null) {
        stack.push(current.left);
      }
    }
  }

  // Inner class InorderIterator
  private class InorderIterator implements java.util.Iterator<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    private int current = 0;

    public InorderIterator() {
      inorder();
    }

    private void inorder() {
      inorder(root);
    }

    private void inorder(TreeNode<E> root) {
      if (root == null) return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    public boolean hasNext() {
      return current < list.size();
    }

    public E next() {
      return list.get(current++);
    }

    public void remove() {
      if (current == 0)
        throw new IllegalStateException();

      delete(list.get(--current));
      list.clear();
      inorder();
    }
  }

  public java.util.Iterator<E> iterator() {
    return new InorderIterator();
  }

  public void clear() {
    root = null;
    size = 0;
  }

  // Add the AaryamanPrint method
  public void AaryamanPrint() {
    System.out.println("Number of nodes: " + getSize());
    System.out.print("Inorder: ");
    inorder(root);
    System.out.println();
    System.out.print("Preorder: ");
    preorder(root);
    System.out.println();
    System.out.print("Postorder: ");
    postorder(root);
    System.out.println();
  }
}
