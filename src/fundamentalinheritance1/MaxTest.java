package fundamentalinheritance1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


// Wrapping an InputStream inside an InputStreamReader inside a BufferedReader
// works for any InputStream, including System.in or sockets.
// This program illustrated the use of this pattern to read 2 numbers from the standard input.
public class MaxTest {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		// Use BufferedReader to read text from a character input stream, use read(), readLine()
		// wrap the BufferedReader around the Reader
		// such as FileReaders or InputStreamReaders
		// will buffering the input from the specific file
		System.out.println("Enter 2 ints on one line: ");
		
		try {
			String oneLine = in.readLine();
			if(oneLine == null)
				return;
			Scanner str = new Scanner(oneLine);
			
			int x = str.nextInt();
			int y = str.nextInt();
			
			System.out.println("Max: " + Math.max(x, y));
		}catch(IOException e) {
			System.err.println("Unexcepted I/O error");
		}
		catch(NoSuchElementException e) {
			System.err.println("Error: need 2 ints");
		}
		

	}

}
