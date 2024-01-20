/**
 * Interface for priority queue.
 * @param <E> - allows for generic types
 */
public interface PriorityQueue<E extends Comparable<E>> extends BinaryTree<E> {
    /**
     * Returns the element with the minkey, or null when PQ is empty.
     * @return the element.
     */
    E peek();
    
    /**
     * Removes and returns the element with the minkey,
     * or null when PQ is empty.
     * @return the element.
     */
    E poll();
}