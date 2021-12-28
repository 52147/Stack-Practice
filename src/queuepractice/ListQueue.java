package queuepractice;

import stackpractice1.ListNode; // use "import" can use class of different package
import stackpractice1.UnderflowException;

/**
 * = linked list based queues =
 * 
 * - The queue can be implemented by a linked list, provided we keep references
 *   to both the front and back of the list.
 *   
 *   Linked list implementation of the queue class:
 *   
 *   A -> B -> C -> D
 *   
 *   A: front
 *   B: back
 *   
 *   
 * - The ListQueue class is similar to the ListStack class,
 * - The only new thing here is that we maintain 2 references(front and back) instead of 1.
 * 
 * 
 * dequeue:
 * 
 * - The dequeue routine is logically identical to a stack pop (actually popAndPop).
 * 
 * enqueue
 * 
 * - The enqueue routine has 2 cases.
 * - If queue is empty, we create a one-element queue by calling new and having
 *   both front and back reference the single node.
 *   
 * - Otherwise, we create a new node with data value x,
 *   attach it at the end of the list, and them reset the end of the list to this new node.
 * - Note that enqueueing the first element is a special case because there is no next reference
 *   to which a new node can be attached.
 *   
 *
 * -comparison of the 2 versions(array and linked list based):
 *   
 *   - Both the array and linked list versions run in constant time per operations.
 *   - Thus they are so fast that they unlikely to be the bottleneck of any algorithms and,
 *     in that regard, which version is used rarely matters.
 *     
 *  - array version faster than linked list version:
 *    
 *    - The array version of these data structures are likely to be faster than their linked list counterparts,
 *      especially if an accurate estimation of capacity is available.
 *    - If an additional constructor is provided to specify the initial capacity
 *      and the estimate is correct, no doubling is performed.
 *    - Also, the sequential access provided by an array is typically faster than 
 *      the potential nonsequential access offered by dynamic memory allocation.
 *      
 *  - 2 drawbacks:
 *    
 *    1.
 *    
 *    - The array implementation does have 2 drawbacks, however.
 *    - First, for queues, the array implementation is arguably more complex than the linked list implementation,
 *      owing to the combined code for wraparound and array doubling,
 *    - Our implementation of array doubling was not as efficient as possible,
 *      thus a faster implementation of the queue would require a few additional lines of code.
 *    - Even the array implementation of the stack uses a few more lines of code than its linked list counterpart.
 *    
 *    2.
 *    
 *    - The second drawback affects other languages, but not Java.
 *    - When doubling, we temporarily require 3 times as much space as the number of data items suggests.
 *    - The reason is that, when the array is doubled, we need to have memory to store both and the new(double-sized) array.
 *    - Further, at the queue's peak size, the array is between 50 percent and 100 percent full;
 *      on average it is 75 percent full, so for every three items in the array, one spot is empty.
 *    - The wasted space is thus 33 percent on average and 100 percent when the table is only half full.
 *   
 *    - As discussed earlier, in Java, each element in the array is simply a reference.
 *    - In other languages, such as C++, objects are stored directly, rather than referenced.
 *    - In these languages, the wasted space could be significant when compared to the linked list-based version
 *      than uses only an extra reference per item.
 */

// ListQueue class
// 
// Construction: with no initializer
//
// Public Operations:
// void enqueue(x)    ----> Insert x
// void dequeue()     ----> Return and remove least recently inserted item
// AnyType getFront() ----> return least recently inserted item
// boolean isEmpty()  ----> Return true if empty; else false
// void makeEmpty()   ----> Remove all items
// Errors:
// getFront or dequeue on empty queue

public class ListQueue<AnyType> {
	
	private ListNode<AnyType> front;
	private ListNode<AnyType> back;
	
	/**
	 * Construct the queue.
	 */
	public ListQueue() {
		front = back = null;
	}
	/**
	 * Insert a new item into the queue.
	 * @param x the item to insert
	 */
	public void enqueue(AnyType x) {
		
		if(isEmpty())  // Make a queue of one element 
			back = front = new ListNode<AnyType>(x);
		else  // Regular case
			back = back.next = new ListNode<AnyType>(x);
		
	}
	/**
	 * Return and remove the least recently inserted item from the queue.
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is empty.
	 */
	public AnyType dequeue() {
		if(isEmpty())
			throw new UnderflowException("ListQueue dequeue");
		AnyType returnValue = front.element;
		front = front.next;
		return returnValue;
	}
	/**
	 * Get the least recently inserted item in the queue.
	 * Does not alter the queue
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is empty.
	 */
	public AnyType getFront() {
		if(isEmpty())
			throw new UnderflowException("ListQueue getFront");
		return front.element;
	}
	
	/**
	 * Test if the queue is logically empty.
	 */
	public boolean isEmpty() {
		return front == null;
	}
	
	/**
	 * Make the queue logically empty.	
	 */
	public void makeEmpty() {
		front = null;
        back =null;
	}
	
	
	

}
