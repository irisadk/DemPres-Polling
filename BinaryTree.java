/**
 * Interface for binary tree.
 * @param <E> - allows for generic types
 */
public interface BinaryTree<E extends Comparable<E>> {
    /**
     * Returns the root element.
     * @return the root element
     */
    E getRootElement();

    /**
     * Gets the size of the tree.
     * @return size
     */
    int size();

    /**
     * Determines whether tree is empty.
     * @return true if empty, false if not
     */
    boolean isEmpty();

    /**
     * Determines whether the tree contains a certain element.
     * @param element - the element to be found
     * @return true if found, false if not
     */
    boolean contains(E element);

    /**
     * Insert new element into the tree.
     * @param element - the added element
     */
    void insert(E element);

    /**
     * Remove selected element from the tree.
     * @param element - the element being removed
     * @return true if removed, false if not found
     */
    boolean remove(E element);
    
    /** 
     * Returns a string representation of the tree
     * based on an inorder traversal.
     * @return the String representation.
     */
    String toStringInOrder();

    /** 
     * Returns a string representation of the tree
     * based on a preorder traversal.
     * @return the String representation.
     */
    String toStringPreOrder();

    /** 
     * Returns a string representation of the tree
     * based on a postorder traversal.
     * @return the String representation.
     */
    String toStringPostOrder();
}