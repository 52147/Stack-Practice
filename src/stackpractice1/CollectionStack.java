package stackpractice1;
/**
 * = the java.util.Stack class =
 * 
 * - The Collections API provides a Stack class.
 * 
 *
 *
 */

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Stack class. Unlike java.util.Stack, this is not extended from Vector.
 * This is the minimum respectable set of operations.
 * 
 */

// A simplified Collection-style Stack class, based on the ArrayList class.
public class CollectionStack<AnyType> implements java.io.Serializable {
	
	private ArrayList<AnyType> items;
	
	public CollectionStack() {
		items = new ArrayList<AnyType>();
	}
	/**
	 * Adds an item to the top of the stack.
	 * @param x the item to add.
	 * @return the item added.
	 */	
	public AnyType push(AnyType x) {
		items.add(x);
		return x;
	}
	/**
	 * Removes and returns item from the top of the stack.
	 * @return the former top item.
	 */
	public AnyType pop() {
		if(isEmpty())
			throw new EmptyStackException();
		return items.remove(items.size() - 1);
	}
	
	/**
	 * Return item from the top of the stack.
	 * @return the top item.
	 * @throws EmptyStackException if stack is empty.
	 */
	public AnyType peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return items.get(items.size() - 1);
		
	}
	
	/**
	 * Tests if stack is empty.
	 * @return true if the stack is empty; false otherwise.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the size of the stack.
	 * @return the size of the stack.
	 */
	public int size() {
		return items.size();
	}
	/**
	 * Remove all of the elements in the list.
	 */
	public void clear() {
		items.clear();
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(int i = size()-1;i >= 0;i--)
			result.append(items.get(i));
		return result.toString();
	}

}
