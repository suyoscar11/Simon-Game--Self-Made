import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AaryamanBinarySearchTree<Integer> bst = new AaryamanBinarySearchTree<>();
        AVLTree<Integer> avlTree = new AVLTree<>();

        System.out.println("Enter 10 integral values:");

        for (int i = 0; i < 10; i++) {
            int value = input.nextInt();
            bst.insert(value);
            avlTree.insert(value);
        }

        System.out.println("Binary Search Tree (BST) Inorder Traversal:");
        bst.inorder();
        System.out.println("\n");

        System.out.println("AVL Tree Inorder Traversal:");
        avlTree.inorder();
        System.out.println("\n");

        System.out.println("Enter a value to search for:");
        int searchValue = input.nextInt();
        boolean foundInBST = bst.search(searchValue);
        boolean foundInAVLTree = avlTree.search(searchValue);

        System.out.println("Value " + searchValue + " found in BST: " + foundInBST);
        System.out.println("Value " + searchValue + " found in AVL Tree: " + foundInAVLTree);

        System.out.println("Enter a value to delete:");
        int deleteValue = input.nextInt();
        boolean deletedFromBST = bst.delete(deleteValue);
        boolean deletedFromAVLTree = avlTree.delete(deleteValue);

        System.out.println("Binary Search Tree (BST) Inorder Traversal after delete:");
        bst.inorder();
        System.out.println("\n");

        System.out.println("AVL Tree Inorder Traversal after delete:");
        avlTree.inorder();
        System.out.println("\n");

        input.close();
    }
}

class AaryamanBinarySearchTree<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected Comparator<E> c;

    public AaryamanBinarySearchTree() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    public AaryamanBinarySearchTree(Comparator<E> c) {
        this.c = c;
    }

    public AaryamanBinarySearchTree(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (E object : objects) {
            add(object);
        }
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }

            if (c.compare(e, parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }

        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean delete(E e) {
        // Implementation of delete method
        return false;
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    // Other methods and inner classes

    static class TreeNode<E> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }
}

class AVLTree<E> extends AaryamanBinarySearchTree<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<E> c) {
        super(c);
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            balancePath(e);
        }

        return true;
    }

    private void updateHeight(AVLTreeNode<E> node) {
        // Implementation of updateHeight method
    }

    private void balancePath(E e) {
        // Implementation of balancePath method
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        // Implementation of balanceFactor method
        return 0;
    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        // Implementation of LL rotation
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        // Implementation of LR rotation
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        // Implementation of RR rotation
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        // Implementation of RL rotation
    }

    @Override
    public boolean delete(E element) {
        // Implementation of delete method
        return false;
    }

    protected static class AVLTreeNode<E> extends TreeNode<E> {
        int height = 0;

        public AVLTreeNode(E o) {
            super(o);
        }
    }
}

interface Tree<E> {
    boolean search(E e);

    boolean insert(E e);

    boolean delete(E e);

    void inorder();
}
