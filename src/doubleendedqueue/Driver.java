package doubleendedqueue;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/* Creating object of class Dequeue */
		Deques dq = new Deques();
		/* Perform Dequeue Operations */
		System.out.println("Dequeue Test\n");
		char ch;
		do {
			System.out.println("\nDequeue Operations");
			System.out.println("1. insert at front");
			System.out.println("2. insert at rear");
			System.out.println("3. delete at front");
			System.out.println("4. delete at rear");
			System.out.println("5. peek at front");
			System.out.println("6. peek at rear");
			System.out.println("7. size");
			System.out.println("8. check empty");
			System.out.println("9. clear");
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter integer element to insert");
				dq.addFirst(scan.nextInt());
				break;
			case 2:
				System.out.println("Enter integer element to insert");
				dq.addLast(scan.nextInt());
				break;
			case 3:
				try {
					System.out.println("Removed Element = " + dq.removeFirst());
				} catch (Exception e) {
					System.out.println("Error : " + e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("Removed Element = " + dq.removeLast());
				} catch (Exception e) {
					System.out.println("Error : " + e.getMessage());
				}
				break;
			case 5:
				try {
					System.out.println("Peek Element = " + dq.peekFirst());
				} catch (Exception e) {
					System.out.println("Error : " + e.getMessage());
				}
				break;
			case 6:
				try {
					System.out.println("Peek Element = " + dq.peekLast());
				} catch (Exception e) {
					System.out.println("Error : " + e.getMessage());
				}
				break;
			case 7:
				System.out.println("Size = " + dq.getSize());
				break;
			case 8:
				System.out.println("Empty status = " + dq.isEmpty());
				break;
			case 9:
				System.out.println("\nDequeue Cleared\n");
				dq.clear();
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			/* display dequeue */
			dq.display();

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}
}
