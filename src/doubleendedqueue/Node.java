package doubleendedqueue;

public class Node {

	private int element;
	private Node next;

	public Node() {
		next = null;
		element = 0;

	}

	public Node(int element, Node next) {
		this.element = element;
		this.next = next;
	}

	public void setNext(Node next) {
		this.next = next;

	}

	public Node getNext() {

		return next;
	}

	public void setData(int element) {
		this.element = element;
	}

	public int getData() {

		return element;
	}

}
