package queuepractice;

import stackpractice1.UnderflowException;

/**
 * = queues =
 * 
 * - The easiest way to implement the queue is to store the items in an array
 *   with the front item in the front position(i.e., array index 0).
 * - If back represents the position of the last item in the queue,
 *   then to enqueue we merely increment back and place the item there.
 * 
 * - The problem is that the dequeue operation is very expensive.
 * - The reason is that, by requiring that the items be placed at the start of the array,
 *   we force the dequeue to shift all the items one position after we remoce the front item.
 * 
 *   Basic array implementation of the queue:
 *   
 *   1.
 *   makeEmpty()
 *   size = 0
 *   
 *   back
 *       |    |    |    |    |    |
 *        front
 *        

 *
 *   2.
 *   enqueue(a)
 *   size = 1
 *   
 *       back
 *       | a  |    |    |    |    | 
 *        front
 *   3.
 *   enqueue(b)
 *   size = 2
 *   
 *              back
 *       | a  |  b  |    |    |    | 
 *        front
 *        
  *   4.
 *   dequeue()
 *   size = 0
 *   
 *             back
 *       |    |  b  |    |    |    | 
 *             front
 *   5.
 *   enqueue()
 *   size = 0
 *   
 *             back
 *       |    |    |    |    |    | 
 *                  front    
 *                  
 *                            
 *       - This figure shows that we can overcome this problem 
 *         when performing a dequeue by incrementing front rather than shifting all the elements.
 *       - When the queue has one element, both front and back represent the array index of that element.
 *       - Thus, for an empty queue, back must be initialized to front-1.
 *       
 *       - This implementation ensures that both enqueue and dequeue can be performed in constant time.
 *       - The fundamental problem with this approach is in this figure:
 *  
 *  Array implementation of the queue with wraparound:
 *       
 *       
 *   1.
 *   After 3 enqueues
 *   size = 3
 *   
 *                              back
 *       |    |    |  c  |  d  |  e  |
 *                  front
 *        

 *
 *   2.
 *   enqueue(f)
 *   size = 4
 *   
 *       back
 *       | f  |    |  c  |  d  |  e  | 
 *                  front
 *                  
 *   3.
 *   dequeue()
 *   size = 3
 *   
 *       back
 *      | f  |    |   |  d  |  e  | 
 *                      front
 *        
  *   4.
 *   dequeue()
 *   size = 2
 *   
*       back
 *      | f  |    |    |    |  e  | 
 *                            front
 *   5.
 *   dequeue()
 *   size = 1
 *   
 *        back
 *       | f  |    |    |    |    | 
 *        front                  
 *        
 *                      
 *      - After 3 more enqueue operations, we cannot add any more items,
 *        even though the queue is not really full.
 *      - Array doubling does not solve the problem because,
 *        even if the size of the array is 1000, after 1000 enqueue operations
 *        there is no room in the queue,
 *        regardless of its actual size.
 *      - Even if 1000 dequeue operations have been performed,
 *        thus abstractly making queue empty, we cannot add to it.
 *        
 *  - As this figure shows, however, there is plenty of extra space:
 *    All the positions before front are unused and can thus be recycled.
 *    -> Hence, we use wraparound;
 *       that is, when either back or front reaches the end of the array,
 *       we reset it to the beginning.
 *       
 *       circular array implementation: (leetcode 622 design circular queue)
 *       
 *       - This operation implementing a queue is called a circular array implementation.
 *       - We need to double the array only when the number of elements in the queue
 *         equals the number of array positions.
 *         - To enqueue(f),
 *           we therefore reset back to the start of the array and place f there.
 *         - After 3 dequeue operations, front is also reset to the start of the array.
 *         
 *  
 * - The skeleton for the ArrayQueue class:
 *   
 *   - The ArrayQueue class has 4 data members:
 *   
 *      1. a dynamically expanding array,
 *      2. the number of items currently in the queue,
 *      3. the array index of the front item,
 *      4. the array index of the back item.
 *     
 * - Internal(private) method:
 * 
 *   1. increment()
 *   
 *     - We declare 2 methods in the private section.
 *     - These methods are used internally by the ArrayQueue methods but not made available
 *       to the user of the class.
 *     - Oner of these method is the increment routine, which adds 2 to its parameter and
 *       returns the new value.
 *     - Because this method implements wraparound,
 *       if the result would equal the array size it is wrapped around to zero.
 *       
 *   2. doubleQueue()
 *   
 *     - doubleQueue routine is called if an enqueue requires a doubling of the array.
 *     - It is slightly more complex than the usual expansion because
 *       the queue items are not necessarily stored in an array starting at location 0.
 *     - Thus items must be copied carefully.
 *
 * public methods:
 * 
 * - Many of the public methods resemble their stack counterparts,
 *   including the constructor and isEmpty.
 * 
 *   - constructor:
 *    - This constructor is not particularly special, 
 *      except that we must be sure that we have the correct initial values for 
 *      both front and back.
 *    - This is done by calling makeEmpty.
 *    
 *   - enqueue()
 *    
 *   - doubleQueue()
 *    - begin by resizing the array.
 *    - We must move items starting at front, rather than 0.
 *    - The doubleQueue steps through the old array and copies each item to the new part of the array.
 *    - Then we reset back.
 *    
 *    
 * - The queue routines clearly are constant-time operations, so the cost of array doubling
 *   can be amortized over the sequence of enqueue operations, as for the stack.
 *   
 * - The circular array implementation of the queue can easily be done incorrectly
 *   when attempts to shorten the code are made.
 *   - For instance, 
 *     if you attempt to avoid using the size member by using front and back to infer the size,
 *     the array must be resized when the number of items in the queue is 1 less than the array's size.      
 *                      
 *        
 */
// Skeleton for the array-based queue class
//
// ArrayQueue class
//
// Construction: with no initializer
// Public Operations
// void enqueue(x)    ----> Insert x
// AnyType getFront() ----> Return least recently inserted item
// AnyType dequeue()  ----> Return and remove least recent item
// boolean isEmpty()  ----> Return true if empty; else false
// void makeEmpty()   ----> Remove all items
// Errors
// getFront or dequeue on empty queue
public class ArrayQueue<AnyType> {
	
	private AnyType[] theArray;
	private int currentSize;
	private int front;
	private int back;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Construct the queue.
	 */
	public ArrayQueue() {
		theArray = (AnyType[]) new Object [DEFAULT_CAPACITY];
		makeEmpty();
	}
	
	/**
	 * Test if the queue is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	/**
	 * Make the queue logically empty
	 */
	public void makeEmpty() {
		currentSize = 0;
		front = 0;
		back = -1;
	}
	
	/**
	 * Return and remove the least recently inserted item from the queue.
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is Empty.
	 */
	public AnyType dequeue() {
		if(isEmpty())
			throw new UnderflowException("ArrayQueue dequeue");
		currentSize--;
		
		AnyType returnValue = theArray[front];
		front = increment(front);
		return returnValue;
	}
	
	/**
	 * Get the least recently inserted item in the queue.
	 * Does not alter the queue.
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is empty.
	 */
	public AnyType getFront() {
		if(isEmpty())
			throw new UnderflowException("ArrayQueue getFront");
		return theArray[front];
	}
	
	/**
	 * Insert a new item into the queue.
	 * @param x the item to insert.
	 */
	public void enqueue(AnyType x) {
		if(currentSize == theArray.length)
			doubleQueue();
		back = increment(back);
		theArray[back] = x;
		currentSize++;
	}
	
	/**
	 * Internal method to increment with wraparound.
	 * @param x any index in theArray's range.
	 * @return x+1, or 0 if x is at the end of theArray.
	 */
	private int increment (int x) {
		if(++x == theArray.length) // if x+1 is equal to array's length, return 0, else returns x+1
			x = 0;
		return x;
		
	}
	
	/**
	 * Internal method to expand theArray.
	 */
	private void doubleQueue() {
		
		AnyType [] newArray;
		newArray = (AnyType []) new Object[theArray.length * 2];
		
		// Copy elements that are logically in the queue.
		for(int i = 0; i < currentSize; i ++, front = increment(front))
			newArray[i] = theArray[front];
		
		theArray = newArray;
		front = 0;
		back = currentSize - 1;
	
	}

}
