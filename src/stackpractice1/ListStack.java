package stackpractice1;
/**
 * = linked list based stacks =
 * 
 * - The stack class can be implemented ad a linked list in which
 *   the top of the stack is represented by the first item in the list.
 * 
 * - To implement a push, we create a new node in the list and attach it as the new first element.
 * - To implement a pop, we merely advance the top of the stack 
 *   to the second item in the list(if there is one).
 * - An empty stack is represented by an empty linked list.
 * - Clearly, each operation is performed in constant time because,
 *   by restricting operations to the first node,
 *   we have made all calculations independent of the size of the list.
 *   
 *   
 *      Linked list implementation of the stack class:
 *   
 *          D -> C -> B -> A
 *   
 *          D: topOfStack
 *    
 * - implementation:
 *   
 *   data member:
 *   
 *   topOfStack
 *   
 *   - The stack itself is represented by a single data member, 
 *     topOfStack, which is a reference to the first ListNode in the linked list.
 *     
 *     
 *   methods:   
 *   
 *   no constructor:
 *   - The constructor is not explicitly written, 
 *   since by default we obtain an empty stack by setting topOfStack to NULL.
 *   - makeEmpty and isEmpty are thus trivial.
 *   
 *   
 *   push
 *   - The push operation is essentially one line of code,
 *     in which we allocate a new ListNode whose data member contains the item x to be pushed.
 *   - The next reference for this new node is the original topOfStack.
 *   - This new inserted node then becomes the new topOfStack.  
 *   
 *   pop
 *   - The pop operation also simple.
 *   - After the obligatory test for emptiness, we reset topOfStack to the second node in the list.
 *   
 * 	 top
 * 
 *   topAndPop
 *
 */

//ListStack class
//
// Construction: with no initializer
//
// Public Operations:
// void push(x)  ----> Insert x
// void pop()    ----> Remove most recently inserted item
// AnyType top()  ----> Return most recently inserted item
// AnyType topAndPop()  ----> Return and remove most recent item
// Boolean isEmpty()   ----> Return true if empty; else false
// void makeEmpty()   ----> Remove all items
// Errors:
// top, pop, or topAndPop on empty stack
public class ListStack<AnyType> implements Stack<AnyType> {
	
	private ListNode<AnyType> topOfStack = null;
	
	/**
	 * Insert a new item into the stack.
	 * @param x the item to insert.
	 */
	@Override
	public void push(AnyType x) {
		topOfStack = new ListNode<AnyType>(x, topOfStack);
		
	}
	
	/**
	 * Remove the most recently inserted item from the stack.
	 * @throws UnderflowException if the stack is empty
	 */
	@Override
	public void pop() {
		if(isEmpty())
			throw new UnderflowException("ListStack pop");
		topOfStack = topOfStack.next;
		
	}
	
	/**
	 * Get the most recently inserted item in the stack.
	 * Does not alter the stack.
	 * @return the most recently inserted item in the stack.
	 * @throws UnderflowException if the stack is empty.
	 */
	@Override
	public AnyType top() {
		if(isEmpty())
			throw new UnderflowException("ListStack top");
		
		return topOfStack.element;
	}
	
	/**
	 * Return and remove the most recently inserted item from the stack.
	 * @return the most recently inserted item in the stack.
	 * @throws UnderflowException if the stack is empty.
	 */
	@Override
	public AnyType topAndPop() {
		if(isEmpty())
			throw new UnderflowException("ListStack topAndPop");
		
		AnyType topItem = topOfStack.element;
		topOfStack = topOfStack.next;
		return topItem;
	}

	@Override
	public boolean isEmpty() {
		
		return topOfStack == null;
	}

	@Override
	public void makeEmpty() {
		topOfStack = null;
		
	}

}
