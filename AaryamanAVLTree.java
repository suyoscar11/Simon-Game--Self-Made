import java.util.Comparator;

public class AVLTree<E> extends AaryamanBinarySearchTree<E> {
  /** Create an empty AVL tree using a natural comparator */
  public AVLTree() {
    super();
  }

  /** Create an AVL tree with a specified comparator */
  public AVLTree(Comparator<E> c) {
    super(c);
  }

  /** Create an AVL tree from an array of objects */
  public AVLTree(E[] objects) {
    super(objects);
  }

  @Override
  protected AaryamanAVLTreeNode<E> createNewNode(E e) {
    return new AaryamanAVLTreeNode<>(e);
  }

  @Override
  public boolean insert(E e) {
    boolean successful = super.insert(e);
    if (!successful)
      return false; // e is already in the tree
    else {
      balancePath(e); // Balance from e to the root if necessary
    }
    return true; // e is inserted
  }

  /** Update the height of a specified node */
  private void updateHeight(AaryamanAVLTreeNode<E> node) {
    if (node.left == null && node.right == null) // node is a leaf
      node.height = 0;
    else if (node.left == null) // node has no left subtree
      node.height = 1 + ((AaryamanAVLTreeNode<E>) (node.right)).height;
    else if (node.right == null) // node has no right subtree
      node.height = 1 + ((AaryamanAVLTreeNode<E>) (node.left)).height;
    else
      node.height = 1 + Math.max(((AaryamanAVLTreeNode<E>) (node.right)).height,
          ((AaryamanAVLTreeNode<E>) (node.left)).height);
  }

  /** Balance the nodes in the path from the specified node to the root if necessary */
  private void balancePath(E e) {
    java.util.ArrayList<AaryamanBinarySearchTree.TreeNode<E>> path = path(e);
    for (int i = path.size() - 1; i >= 0; i--) {
      AaryamanAVLTreeNode<E> A = (AaryamanAVLTreeNode<E>) (path.get(i));
      updateHeight(A);
      AaryamanAVLTreeNode<E> parentOfA = (A == root) ? null : (AaryamanAVLTreeNode<E>) (path.get(i - 1));
      switch (balanceFactor(A)) {
        case -2:
          if (balanceFactor((AaryamanAVLTreeNode<E>) A.left) <= 0) {
            balanceLL(A, parentOfA); // Perform LL rotation
          } else {
            balanceLR(A, parentOfA); // Perform LR rotation
          }
          break;
        case 2:
          if (balanceFactor((AaryamanAVLTreeNode<E>) A.right) >= 0) {
            balanceRR(A, parentOfA); // Perform RR rotation
          } else {
            balanceRL(A, parentOfA); // Perform RL rotation
          }
      }
    }
  }

  /** Return the balance factor of the node */
  private int balanceFactor(AaryamanAVLTreeNode<E> node) {
    if (node.right == null) // node has no right subtree
      return -node.height;
    else if (node.left == null) // node has no left subtree
      return node.height;
    else
      return ((AaryamanAVLTreeNode<E>) node.right).height - ((AaryamanAVLTreeNode<E>) node.left).height;
  }

  /** Balance LL (see Figure 26.3) */
  private void balanceLL(AaryamanBinarySearchTree.TreeNode<E> A, AaryamanBinarySearchTree.TreeNode<E> parentOfA) {
    AaryamanBinarySearchTree.TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

    if (A == root) {
      root = B;
    } else {
      if (parentOfA.left == A) {
        parentOfA.left = B;
      } else {
        parentOfA.right = B;
      }
    }

    A.left = B.right; // Make T2 the left subtree of A
    B.right = A; // Make A the left child of B
    updateHeight((AaryamanAVLTreeNode<E>) A);
    updateHeight((AaryamanAVLTreeNode<E>) B);
  }

  /** Balance LR (see Figure 26.5) */
  private void balanceLR(AaryamanBinarySearchTree.TreeNode<E> A, AaryamanBinarySearchTree.TreeNode<E> parentOfA) {
    AaryamanBinarySearchTree.TreeNode<E> B = A.left; // A is left-heavy
    AaryamanBinarySearchTree.TreeNode<E> C = B.right; // B is right-heavy

    if (A == root) {
      root = C;
    } else {
      if (parentOfA.left == A) {
        parentOfA.left = C;
      } else {
        parentOfA.right = C;
      }
    }

    A.left = C.right; // Make T3 the left subtree of A
    B.right = C.left; // Make T2 the right subtree of B
    C.left = B;
    C.right = A;

    // Adjust heights
    updateHeight((AaryamanAVLTreeNode<E>) A);
    updateHeight((AaryamanAVLTreeNode<E>) B);
    updateHeight((AaryamanAVLTreeNode<E>) C);
  }

  /** Balance RR (see Figure 26.4) */
  private void balanceRR(AaryamanBinarySearchTree.TreeNode<E> A, AaryamanBinarySearchTree.TreeNode<E> parentOfA) {
    AaryamanBinarySearchTree.TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

    if (A == root) {
      root = B;
    } else {
      if (parentOfA.left == A) {
        parentOfA.left = B;
      } else {
        parentOfA.right = B;
      }
    }

    A.right = B.left; // Make T2 the right subtree of A
    B.left = A;
    updateHeight((AaryamanAVLTreeNode<E>) A);
    updateHeight((AaryamanAVLTreeNode<E>) B);
  }

  /** Balance RL (see Figure 26.6) */
  private void balanceRL(AaryamanBinarySearchTree.TreeNode<E> A, AaryamanBinarySearchTree.TreeNode<E> parentOfA) {
    AaryamanBinarySearchTree.TreeNode<E> B = A.right; // A is right-heavy
    AaryamanBinarySearchTree.TreeNode<E> C = B.left; // B is left-heavy

    if (A == root) {
      root = C;
    } else {
      if (parentOfA.left == A) {
        parentOfA.left = C;
      } else {
        parentOfA.right = C;
      }
    }

    A.right = C.left; // Make T2 the right subtree of A
    B.left = C.right; // Make T3 the left subtree of B
    C.left = A;
    C.right = B;

    // Adjust heights
    updateHeight((AaryamanAVLTreeNode<E>) A);
    updateHeight((AaryamanAVLTreeNode<E>) B);
    updateHeight((AaryamanAVLTreeNode<E>) C);
  }

  @Override
  public boolean delete(E element) {
    if (root == null)
      return false; // Element is not in the tree

    // Locate the node to be deleted and also locate its parent node
    AaryamanBinarySearchTree.TreeNode<E> parent = null;
    AaryamanBinarySearchTree.TreeNode<E> current = root;
    while (current != null) {
      if (c.compare(element, current.element) < 0) {
        parent = current;
        current = current.left;
      } else if (c.compare(element, current.element) > 0) {
        parent = current;
        current = current.right;
      } else
        break; // Element is in the tree pointed by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left children (see Figure 23.6)
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      } else {
        if (element.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;

        // Balance the tree if necessary
        balancePath(parent.element);
      }
    } else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      AaryamanBinarySearchTree.TreeNode<E> parentOfRightMost = current;
      AaryamanBinarySearchTree.TreeNode<E> rightMost = current.left;

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
        // Special case: parentOfRightMost is current
        parentOfRightMost.left = rightMost.left;

      // Balance the tree if necessary
      balancePath(parentOfRightMost.element);
    }

    size--;
    return true; // Element inserted
  }

  /** AVLTreeNode is TreeNode plus height */
  protected static class AaryamanAVLTreeNode<E> extends AaryamanBinarySearchTree.TreeNode<E> {
    protected int height = 0; // New data field

    public AaryamanAVLTreeNode(E o) {
      super(o);
    }
  }
}
