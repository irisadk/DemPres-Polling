/**
* Entry for a priority queue. Stores each element's priority and value.
* @author Iris
* @param <P> - generic priority data type
* @param <V> - generic value data type
*/
public class Entry<P extends Comparable<P>, V extends Comparable<V>>
                                            implements Comparable<Entry<P, V>> {
    private P priority;
    private V value;

    /** 
    * Constructor for Entry.
    * @param priority - the element's priority in the queue
    * @param value - the data
    */
    public Entry(P priority, V value) {
        this.priority = priority;
        this.value = value;
    }

    /** 
    * Compares the entries by priority.
    * @param other - the entry to compare to
    * @return zero if the entries have the same priority,
    * a negative number if the first has a lesser priority,
    * a positive number otherwise
    */
    public int compareTo(Entry<P, V> other) {
        return this.priority.compareTo(other.priority);
    }

    /** 
    * Compares the entries by value.
    * @param other - the entry to compare to
    * @return zero, negative number, or positive number
    * based on compareTo method of the value object
    */
    public int compareValue(Entry<P, V> other) {
        return this.value.compareTo(other.value);
    }

    /** 
    * Creates a string representation of the object.
    * @return the string representation
    */
    public String toString() {
        return value.toString();
    }
}