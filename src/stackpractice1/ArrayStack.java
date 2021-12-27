package stackpractice1;
/**
 * = Stack =
 * 
 *  - a stack can be implemented with an array and an integer.
 *  - The integer tos (top of stack) provides the array index of the top element of the stack.
 *  - Thus, when tos is -1, the stack is empty.
 *  - To push, we increment tos and place the new element in the array position tos.
 *  - we can perform the pop by decrementing tos.
 *  
 *  1.
 *  empty stack
 *  
 *  tos(-1)
 *   
 *  | | | | |
 *   
 *  2.
 *  push (a)
 *  
 *  tos(0)
 *  
 *  |a| | | |
 *  
 *  3.
 *  push (b)
 *  
 *  tos(1)
 *  
 *  |a|b| | |
 *  
 *  4.
 *  pop()
 *  
 *  tos(0)
 *  
 *  |a| | | | 
 *
 */

/**
 *  = Skeleton for the array-based stack class =
 *  
 *  This array-based Stack class, specifies two data members:
 *  
 *  - theArray : which is expanded as needed, stores the items in the stack
 *  - topOfStack : 
 *     - gives the index of the current top of the stack.
 *     - for an empty stack, this index is -1.
 * 
 *  Time complexity:
 *  
 * - if there is no array doubling , every operation takes constant time.
 * - A push that involves array doubling will take O(N) time.
 * - If this were a frequent occurrence, we would need to worry.
 * 
 * - However, it is infrequent because an array doubling that involves N elements must be preceded by at least
 *   N/2 pushes that do not involve an array doubling.
 * - Consequently, we can charge the O(N) cost of the doubling over these N/2 easy pushes,
 *   thereby effectively raising the cost of each push by only a small constant.
 *   -> this technique is known as amortization.
 *   
 * = amortization =
 * 
 * - A real-life example of amortization is payment of income taxes.
 * - Rather than pay your entire bill on April 15, the government requires that you pay most of your taxes through withholding.
 * - The total tax bill is always the same, it is when the tax is paid that varies.
 * - The same is true for the time spent in the push operations.
 * - we can charge for the array doubling at the time it occurs, or we can bill each push operation equally.
 * - An amortized bound requires that we bill each operation in a sequence for its fair share of the total cost.
 * - In our example, the cost of array doubling therefore is not excessive. 
 *    
 *  
 *  ArrayStack class
 *  
 *  construction : with no initializer
 *  
 *  public operations :
 *  
 *  void push(x)        -> insert x
 *  void pop()          -> remove most recently inserted item
 *  AnyType pop()       -> return most recently inserted item
 *  AnyType topAndPop() -> return and remove most recent item
 *  boolean isEmpty()   -> return true if empty; else false
 *  void makeEmpty()    -> remove all items
 *  
 *  Error:
 *  top, pop or topAndPop on empty stack 
 *
 */

/**
 * 
 * Array-based implementation of the stack.
 *
 * @param <AnyType>
 */

public class ArrayStack<AnyType> implements Stack<AnyType> {
	
	private AnyType[] theArray;
	private int topOfStack;
	private static final int DEFAULT_CAPACITY = 10;

	
	/**
	 * Construct the stack.
	 */
	public ArrayStack() {
		theArray = (AnyType[]) new Object[DEFAULT_CAPACITY];
		topOfStack = -1;
	}
	
	/**
	 * Test if the stack is logically empty.
	 * @return true if empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return topOfStack == -1;
		
	}
	
	/**
	 *  Make the stack logically empty.
	 * 
	 */
	public void makeEmpty() {
		 topOfStack = -1;
	}
	
	/**
	 * Get the most recently inserted item in the stack.
	 * Does not alter the stack.
	 * @return the most recently inserted item in the stack.
	 * @throws UnderflowException if the stack is empty.
	 */
	public AnyType top() {
		if (isEmpty())
			throw new UnderflowException("ArrayStack top");
		return theArray[topOfStack];
		
	}
	
	/**
	 * Remove the most recently inserted item from the stack.
	 * @throws UnderflowException if the stack is empty.
	 */
	public void pop() {
		if (isEmpty()) 
			throw new UnderflowException("ArrayStack pop.");
		topOfStack--;	
		
	}
	
	/**
	 * Return and remove the most recently inserted item from the stack.
	 * @return the most recently inserted item in the stack.
	 * @throws UnderflowException if the stack is empty.
	 */
	public AnyType topAndPop() {
		if(isEmpty()) 
			throw new UnderflowException("ArryStack topAnPop");
		return theArray[topOfStack--];
		
		
	}
	
	/**
	 * Insert the new item into the stack.
	 * @param x the item to insert.
	 */
	public void push(AnyType x) {
		if(topOfStack +1 == theArray.length)
			doubleArray();
		theArray[++topOfStack] = x;
	}
	
	/**
	 * Internal method to extend theArray.
	 */	
	private void doubleArray() {
		AnyType [] newArray;
		newArray = (AnyType[]) new Object[theArray.length*2];
		for (int i = 0; i < theArray.length; i++) {
			newArray[i] = theArray[i];
			theArray = newArray;
		}
	}

}
