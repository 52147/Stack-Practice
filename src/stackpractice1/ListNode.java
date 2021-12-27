package stackpractice1;
/**
 * = List Node class =
 * 
 * - give the type declaration for the nodes in the list.
 * - A ListNode consists of 2 data members:
 *   
 *   1. element
 *      - stores the item 
 *   2. nest
 *      - stores a reference to the next ListNode in the linked list.
 *      
 *   3. 2 constructors
 *      - We provide constructors for ListNode that can be used to execute both:
 *      
 *      a.
 *      ListNode<AnyType> p1 = new ListNode<AnyType>(x);
 *      
 *      b.
 *      ListNode<AnyType> p2 = new ListNode<AnyType>(x, ptr);
 * 
 */

// Basic node stored in a linked list.
// Note that this class is not accessible outside of this package.
public class ListNode<AnyType> {
	
	public AnyType element;
	public ListNode next;
	
	public ListNode(AnyType theElement) {
		this(theElement, null);
	}
	
	public ListNode(AnyType theElement, ListNode<AnyType> n) {
		element = theElement;
		next = n;
	}
	

}
