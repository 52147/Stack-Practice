package stackpractice1;
// Stack interface
//
// Public operations:
// void push(x)        ----> Insert x
// void pop ()         ----> Remove most recently inserted item
// AnyType top()       ----> Return most recently inserted item
// AnyType topAndPop() ----> Return and remove most recent item
// boolean isEmpty()   ----> Return true if empty; else false
// void makeEmpty()    ----> Remove all items
// Errors:
// top, pop, or topAndPop on empty stack
public interface Stack<AnyType> {
	
	/**
	 * Insert a new item into the stack.
	 * @param x the item to insert.
	 */
	void push(AnyType x);
	
	/**
	 * Remove the most recently inserted item from the stack.
	 * @exception UnderflowException if the stack is empty/
	 */
	void pop();
	
	/**
	 * Get the most recently item in the stack.
	 * Does not alter the stack.
	 * @return the most recently inserted item in the stack.
	 * @exception UnderflowException if the stack is empty.
	 */
	AnyType top();
	
	/**
	 * Return and remove the most recently inserted item from the stack.
	 * @return the most recently inserted item in the stack.
	 * @exception UnderflowException if the stack is empty.
	 */
	AnyType topAndPop();
	
	/**
	 * Test if the stack is logically empty.
	 * @return true if empty, false otherwise.
	 */	
	boolean isEmpty();
	
	/**
	 * Make the stack logically empty.
	 */
	void makeEmpty();

}
