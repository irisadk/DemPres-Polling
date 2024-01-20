import java.util.ArrayList;

/**
 * Implements array heap - MAX.
 * Author: Iris Kim
 * @param <P> - enables generic types
 * @param <V> - enables generic types
 */
public class ArrayHeap<P extends Comparable<P>, V extends Comparable<V>>
    extends ArrayBinaryTree<Entry<P, V>> implements PriorityQueue<Entry<P, V>> {
   
    /**
     * Empty constructor.
     */
    public ArrayHeap() {
        data = new Entry[CAPACITY];
    }

    /**
     * Constructs a duplicate heap.
     * @param heapToCopy - the heap to be copied
     */
    public ArrayHeap(ArrayHeap<P, V> heapToCopy) {
        data = heapToCopy.data.clone();
        size = heapToCopy.size;
    }

    /**
     * Inserts new element into the first empty spot in the array,
     * indicated by priority and value.
     * @param priority - the priority to be added
     * @param value - the value to be added
     */
    public void insert(P priority, V value) {
        insert(new Entry(priority, value));
    }
    
    /**
     * Inserts new element into the first empty spot in the array.
     * @param element - the element to be added
     */
    public void insert(Entry element) {
        int index;
        if (contains(element)) {
            index = find(element);
        } else {
            index = findEmptySlot();
            size++;
        }
        data[index] = element;
        bubbleUp(index);
        bubbleDown(index);
    }

    /**
     * Remove element with selected value and priority.
     * @param priority - the priority
     * @param value - the value
     * @return true if removed, false if not found
     */
    public boolean remove(P priority, V value) {
        return remove(new Entry(0, value));
    }

    /**
     * Remove selected element from the heap.
     * @param element - the element to be removed
     * @return true if removed, false if not found
     */
    public boolean remove(Entry element) {
        int index = find(element);
        if (index == -1) {
            return false;
        }
        data[index] = null;
        bubbleDown(index);
        size--;
        return true;
    }

    /** 
     * Moves one element up in the heap to maintain heap order.
     * @param index - the index of the element to be moved
     */
    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }
        while (data[index].compareTo(data[parent(index)]) > 0) {
            swap(index, parent(index));
            index = parent(index);
            if (index == 0) {
                return;
            }
        }
    }

    /** 
     * Moves one element down in the heap to maintain heap order.
     * @param index - the index of the element to be moved
     */

    private void bubbleDown(int index) {
        int indexOfSmallerChild;
        while (hasLeft(index) || hasRight(index)) {
            if (!hasLeft(index)) {
                indexOfSmallerChild = right(index);
            } else if (!hasRight(index)) {
                indexOfSmallerChild = left(index);
            } else {
                if (data[left(index)].compareTo(data[right(index)]) > 0) {
                    indexOfSmallerChild = left(index);
                } else {
                    indexOfSmallerChild = right(index);
                }
            }
            if (data[index] == null ||
                data[index].compareTo(data[indexOfSmallerChild]) < 0) {
                swap(index, indexOfSmallerChild);
                index = indexOfSmallerChild;
            } else {
                return;
            }
        }
    }

    /**
     * Finds the index of the given element.
     * @param element - the element to be found
     * @return the index of the element if found,
     * -1 if not found.
     */
    protected int find(Entry element) {
        int index = 0;
        int count = 0;
        while (count < size) {
            if (data[index] != null) {
                if (data[index].compareValue(element) == 0) {
                    return index;
                }
                count++;
            }
            index++;
        }
        return -1;
    }


    /** 
     * Returns the element with the biggest value.
     * @return the element, or null if the heap is empty
     */
    public Entry peek() {
        if (size == 0) {
            return null;
        }
        return getRootElement();
    }
   
    /** 
     * Removes and returns the element with the biggest value.
     * @return the element, or null if the heap is empty
     */
    public Entry poll() {
        if (size == 0) {
            return null;
        }
        Entry tmp = (getRootElement());
        remove(tmp);
        return tmp;
    }

    /** 
     * Returns an ArrayList of the top few values in the heap.
     * @param n - the number of top values to return
     * @return the top values
     */
    public ArrayList<Entry> peekTopN(int n) {
        ArrayHeap temp = new ArrayHeap(this);
        ArrayList<Entry> topN = new ArrayList();
        for (int i = 0; i < Math.min(n, size); i++) {
            topN.add(temp.poll());
        }
        return topN;
    }

    /*
    //old version
    public ArrayList<Entry> peekTopN(int n) {
        ArrayList<Entry> topN = new ArrayList();
        int i = 0;
        while (topN.size() < n) {
            if (data[i] != null) {
                topN.add(data[i]);
            }
            i++;
        }
        return topN;
    }
    */

    /** 
     * Returns a string representation of the heap in breadth first order.
     * @return the string
     */
    public String toString() {
        return toStringBreadthFirst();
    }
}