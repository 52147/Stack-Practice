package doubleendedqueue;

import fundamentalinheritance1.NoSuchElementException;

/**
 * = double-ended queues(deque) = 
 * 
 *   (leetcode 622 design circular queue)
 * 
 * - The double-ended queues is like a queue, except that access is allowed at both ends.
 * - The key property of a deque is that entries can be quickly inserted and removed at both ends.
 * - This differs from a stack(which uses only one end)
 *   and an ordinary queue(in which things enter at one end and leave at the other)
 *   
 * - The end of the dequeue are called "front" and "rear", but these designations are arbitrary
 *   since the same operation can occur at both ends.
 *   
 *   
 * - In Java, deques are an interface(java.util.Deque)
 *   that includes all of the queue interface plus additional methods
 *   such as addFirst(to add an element to the front end) and
 *   removeLast(to remove an element from the back end).
 *   
 * - The most straightforward implementations of a deque are similar to the queue implementations
 *   that we have seen using a circular array or linked list,
 *   but a special technique must be used for a linked list.
 *   
 *   key methods of the java Deque interface:
 *   
 *   Operation               At the front end use         At the back end use
 *   
 *   
 *   add an element          void addFirst(E element)      void addLast(E element)
 *   
 *   remove an element       E removeFirst()               E removeLast()
 *   and return a reference
 *   to the removed element
 *   
 *   
 *   retrieve an element     E peekFirst()                 E peekLast()
 *   without removing it
 * 
 *
 */
public class Deques {
	
	private Node front, rear;
	private int size;
	
	/*Constructor*/
	public Deques() {
		front = null;
		rear = null;
		size = 0;
		
	}
	
	/*check if queue is empty*/
	public boolean isEmpty() {
		return front == null;
	}
	
	/*get the size of the queue*/
	public int getSize() {
		return size;
	}
	
	/*clear deque*/
	public void clear() {
		front  = null;
		rear = null;
		size = 0;
	}
	
	/*insert an element at the beginning*/
	public void addFirst(int element) {
		Node ptr = new Node (element, null);
		size ++;
		if(front == null) {
			front = ptr;
			rear = front;
		}else {
			ptr.setNext(front);
			front = ptr;
		}
			
	}
	
	/*insert an element at the end*/
	public void addLast(int element) {
		Node ptr = new Node(element, null);
		size++;
		if(rear == null) {
			rear = ptr;
			front = rear;
		}else {
			rear.setNext(ptr);
			rear = ptr;
		}
		
	}
	
	/*remove the front element from the queue*/
	public int removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		Node ptr = front;
		front = ptr.getNext();
		
		if(front == null)
			rear = null;
		size--;
		
		return ptr.getData();
	}
	
	/*remove rear element from the queue*/
	public int removeLast() {
		
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		
		int data = rear.getData();
		Node ptr1 = front;
		Node ptr2 = front;
		
		while(ptr1 != rear) {
			ptr2 = ptr1;
			ptr1 = ptr1.getNext();
		}
		
		rear = ptr2;
		rear.setNext(null);
		
		return data;
	}
	/*check the front elemet*/
	public int peekFirst() {
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return front.getData();
	}
	
	/*check the last element*/
	public int peekLast() {
		if(isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return rear.getData();
	}
	
	public void display() {
		
		System.out.println("Deque : ");
		if(size == 0) {
			System.out.println("Empty");
			return;
		}
		
		Node ptr = front;
		
		while(ptr != rear.getNext()) {
			System.out.println(ptr.getData() + " ");
			ptr = ptr.getNext();
		}
		System.out.println();
	}

}
