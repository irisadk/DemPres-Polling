/**
 * Implements array binary tree.
 * Author: Iris Kim
 * @param <E> - enables generic types
 */
public class ArrayBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {
    /** Stores the elements in an array. */
    public E[] data;
    /** The number of elements in the tree. */
    public int size;
    /** The capacity of the array. */
    public final int CAPACITY = 1000;

    /**
     * Empty constructor.
     */
    public ArrayBinaryTree() {
        data = (E[]) new Comparable[CAPACITY];
    }
    
    /**
     * Gets the size of the tree.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Determines whether tree is empty.
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Get the index of an element's parent.
     * @param i - the element's index
     * @return parent index
     */
    public int parent(int i) {
        if (i == 0) {
            return -1;
        }
        if (i % 2 == 0) {
            return (i - 2) / 2;
        }
        return (i - 1) / 2;
    }

    /**
     * Get the index of an element's left leaf.
     * @param i - the element's index
     * @return index of left
     */
    public int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Get the index of an element's right leaf.
     * @param i - the element's index
     * @return index of right
     */
    public int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Determines whether an element has a left leaf.
     * @param i - the element's index
     * @return true if it does, false if not
     */
    public boolean hasLeft(int i) {
        return (data[left(i)] != null);
    }
    
    /**
     * Determines whether an element has a right leaf.
     * @param i - the element
     * @return true if it does, false if not
     */
    public boolean hasRight(int i) {
        return (data[right(i)] != null);
    }

    /**
     * Swap two elements in the tree.
     * @param i - index of first element
     * @param j - index of second element
     */
    public void swap(int i, int j) {
        E save = data[i];
        data[i] = data[j];
        data[j] = save;
    }

    /**
     * Find the index given the element.
     * @param element - the element to be found
     * @return the index of the element if found,
     * -1 if not found.
     */
    protected int find(E element) {
        for (int i = 0; i < CAPACITY; i++) {
            if (data[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Determines whether the tree contains a certain element.
     * @param element - the element to be found
     * @return true if found, false if not
     */
    public boolean contains(E element) {
        if (find(element) == -1) {
            return false;
        }
        return true;
    }

    /**
     * Gets the height of the tree.
     * @return height
     */
    public int height() {
        return height(0);
    }

    /**
     * Gets the height of a specific element in the tree.
     * @param index - the index of the element
     * @return the height of the element
     */
    private int height(int index) {
        if (data[index] == null) {
            return 0;
        }
        int lHeight = height(left(index));
        int rHeight = height(right(index));
        return Math.max(lHeight, rHeight) + 1;
    }

    /**
     * Finds the first empty slot in the tree.
     * @return the index of the slot
     */
    protected int findEmptySlot() {
        for (int i = 0; i < CAPACITY; i++) {
            if (data[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Insert new element into the first empty spot in the array.
     * @param element - the added element
     */
    public void insert(E element) {
        int emptySlot = findEmptySlot();
        data[emptySlot] = element;
        size++;
    }

    /**
     * Remove selected element from the tree.
     * @param element - the element being removed
     * @return true if removed, false if not found
     */
    public boolean remove(E element) {
        if (!contains(element)) {
            return false;
        }
        int index = find(element);
        data[index] = null;
        size--;
        return true;
    }

    /**
     * Returns the root element.
     * @return the root element
     */
    public E getRootElement() {
        return data[0];
    }

    /** 
     * Returns a string representation of the tree
     * based on an breadth first traversal.
     * @return the String representation.
     */
    public String toStringBreadthFirst() {
        String thisString = "";
        int count = 0;
        for (int layer = 0; layer < height(); layer++) {
            for (int i = 0; i < Math.pow(2, layer); i++) {
                if (data[count] != null) {
                    thisString += data[count].toString() + " ";
                } else {
                    thisString += "  ";
                }
                count++;
            }
            if (layer + 1 < height()) {
                thisString += "\n";
            }
        }
        return thisString;
    }

    /** 
     * Recursively creates a string representation of the elements
     * in the tree, based on an inorder traversal.
     * @return A string representation of the elements in the tree,
     * starting from index
     * @param index the index to start the string from
     */
    private String buildInOrder(int index) {
        String thisLeft = "";
        String thisRight = "";
        if (hasLeft(index)) {
            thisLeft = buildInOrder(left(index)) + " ";

        }
        if (hasRight(index)) {
            thisRight = " " + buildInOrder(right(index));
        }
        return thisLeft + data[index] + thisRight;
    }

    /** 
     * Recursively creates a string representation of the elements
     * in the tree, based on an preorder traversal.
     * @return A string representation of the elements in the tree,
     * starting from index
     * @param index the index to start the string from
     */
    private String buildPreOrder(int index) {
        String thisLeft = "";
        String thisRight = "";
        if (hasLeft(index)) {
            thisLeft = " " + buildPreOrder(left(index));
        }
        if (hasRight(index)) {
            thisRight = " " + buildPreOrder(right(index));
        }
        return data[index] + thisLeft + thisRight;
    }

    /** 
     * Recursively creates a string representation of the elements
     * in the tree, based on an postorder traversal.
     * @return A string representation of the elements in the tree,
     * starting from index
     * @param index the index to start the string from
     */
    private String buildPostOrder(int index) {
        String thisLeft = "";
        String thisRight = "";
        if (hasLeft(index)) {
            thisLeft = buildPostOrder(left(index)) + " ";
        }
        if (hasRight(index)) {
            thisRight = buildPostOrder(right(index)) + " ";
        }
        return thisLeft + thisRight + data[index];
    }

    /** 
     * Returns a string representation of the tree
     * based on an inorder traversal.
     * @return the String representation.
     */
    public String toStringInOrder() {
        return buildInOrder(0);
    }

    /** 
     * Returns a string representation of the tree
     * based on an preorder traversal.
     * @return the String representation.
     */
    public String toStringPreOrder() {
        return buildPreOrder(0);
    }

    /** 
     * Returns a string representation of the tree
     * based on an postorder traversal.
     * @return the String representation.
     */
    public String toStringPostOrder() {
        return buildPostOrder(0);
    }

    /** 
     * Returns a string representation of the tree containing
     * preorder, inorder, and postorder traversal.
     * @return the String representation.
     */
    public String toString() {
        return "Tree:\nPre:\t" + toStringPreOrder() + "\nIn:\t"
            + toStringInOrder() + "\nPost:\t" + toStringPostOrder();
    }
}
