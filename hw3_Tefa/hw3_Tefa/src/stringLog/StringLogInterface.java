// from Text book (Chapter 2, p.74)

package stringLog;

public interface StringLogInterface {

    void insert(String e);	// this is different from text book:
    				// you always call resize() before insertion.
    boolean isFull();
    int size();
    boolean contains(String e);
    void clear();
    String toString(int m);	// this is different from text book:
    				// you only show the first m and
    				// the last m strings in the Log.

    // This is new:
    void resize();		// if isFull() is false, do nothing.
    				// Otherwise, double the capacity
    				// of the current array, and copy
    				// the old array into the new array.
}

