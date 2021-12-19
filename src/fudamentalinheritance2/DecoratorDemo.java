package fudamentalinheritance2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * OutputStream hierarchy:
 * 
 * - The OutputStream hierarchy includes several wrappers, such as DataOutput-Stream, ObjectOutputStream, and GZIPOutputStream.
 * 
 *   - DataOutputStream allows us to write primitives in binary form(rather than human-readable text form);
 *     
 *      - for instance, a call to writeInt writes the 4 bytes that represent a 32-bit integer.
 *      - Writing data that way avoids conversions to text form, resulting in time and (sometimes) space savings.
 *   
 *   - ObjectOutputStream allows us to write an entire object including all its components,
 *     its component's components, etc., to a stream.
 *   - The object and all its components must implement the Serializable interface.
 *   - There are no methods in the interface; 
 *     one must simply declare that a class is serializable.
 *     
 * GZIOutputStream: 
 *    
 *   - The GZIOutputStream wraps an OutputStream and compresses the writes prior to sending it to the OutputStream.
 * 
 * BufferedOutputStream:  
 * 
 *   - In addition, there is a BufferedOutputStream class.
 * 
 * InputStream:
 * 
 *   - Similar wrappers are found on the InputStream side.
 *   
 * an array of serializable:
 *   
 *   - As an example, suppose we have an array of serializable Person object.
 *   
 *   
 *   - We can write the objects, as a unit, compressed as follow:
 *   
 *     Person [] p = getPersons(); // populate the array
 *     FileOutputStream fout = new FileOutputStream("people.gzip");
 *     BufferedOutputStream bout = new BufferedOutputStream(fout);
 *     GZIPOutputStream gout = new GZIPOutputStream(bout);
 *     ObjectOutputStream oout = new ObjectOutputStream(gout);
 *     oout.writeObject(p);
 *     oout.close;
 *     
 *   - We could read everything back:
 *   
 *     FileInputStream fin = new FileInputStream("people.gzip");
 *     BufferedInputStream bin = new BufferedInputStream(fin);
 *     GZIPInputStream gin = new GZIPInputStream(bin);
 *     ObjectInputStream oin = new ObjectInputStream(gin);
 *     Person [] p =(Person[]) oin.readObject();
 *     oin.close();
 *     
 *     
 *  - The code below expand this example by having each Person store a name, a birth data
 *    and the two Person objects that represent the parents.
 * 
 * decorator pattern:
 *      
 *  - The idea of nesting wrappers in order to add functionality is known as the decorator pattern.
 *  - By doing this, we have numerous small classes that are combined to provide a powerful interface.
 *  - Without this pattern, each different I/O source would have to have functionality 
 *    for compression, serialization, character, and byte I/O, and so on.
 *  - With this pattern, each source is only responsible for minimal, basic I/O, 
 *    and then the extra features are added on by the decorators.
 *    
 *    
 * wrapper class: 
 * 
 *  - Bytes, Short, Integer,Long, Float, Double,Boolean,Character
 *  - provide a way to use primitive data type as objects.
 *    
 *    - ex: 
 *         toString()
 *         which is used to convert wrapper object to strings.
 *         
 *         Integer myInt = 100;
 *         String myString = myInt.toString(); // we convert an integer to string
 *         
 * Gzip: 
 * 
 *  - Gzip is a file format and software application used on Unix and Unix-like systems 
 *  - to compress HTTP content before it's served to a client.       
 *      
 */
/**
 * - The demo creates an array of Person, serializes it out to a compressed file, closed the file,
 *   and then read it back in to prove that everything works.
 *   
 * - Date and Person are static nested classes to avoid any conflicts with other sample code. 
 * - This does not otherwise affect the code, so it was not shown this way in the text.
 *
 *
 */

public class DecoratorDemo {

	/**
	 * A simple Date class.
	 *
	 */
	private static class Date implements Serializable {

		private int month;
		private int date;
		private int year;

		public Date() {
			this(1, 1, 2000);
		}

		public Date(int m, int d, int y) {
			month = m;
			date = d;
			year = y;
		}

		public String toString() {
			return month + "/" + date + "/" + year;
		}

	}

	/**
	 * A simple Person class.
	 *
	 */
	private static class Person implements Serializable {

		private String name;
		private Date birthDate;
		private Person[] parents;

		public Person(String n, Date b, Person mother, Person father) {
			name = n;
			birthDate = b;
			parents = new Person[] { mother, father };

		}

		public Person getFather() {
			return parents[1];
		}

		public Person getMother() {
			return parents[0];
		}

		public String getName() {
			return name;
		}

		public Date getBirthDate() {
			return birthDate;
		}

		public String toString() {
			return "[" + name + " born on =" + birthDate + "(mother=" + getMother().getName() + ")(father="
					+ getFather().getName() + ")" + "]";
		}

		public static final Person UNKNOWN = new Person("????", new Date(), null, null);
	}

	public static Person[] createArray() {
		Person[] p = new Person[5];

		Date twinBday = new Date(10, 2, 1950);

		p[0] = new Person("Joe", new Date(3, 4, 1930), Person.UNKNOWN, Person.UNKNOWN);
		p[1] = new Person("Sue", new Date(5, 5, 1932), Person.UNKNOWN, Person.UNKNOWN);
		p[2] = new Person("Bobby", twinBday, p[1], p[0]);
		p[3] = new Person("Sue", twinBday, p[1], p[0]);
		p[4] = p[0];

		return p;

	}

	public static void printPerson(Person[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		// Write out some stuff
		ObjectOutputStream os = null;
		try {
			FileOutputStream fout = new FileOutputStream("people.gzip");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			GZIPOutputStream gout = new GZIPOutputStream(bout);
			os = new ObjectOutputStream(gout);
			Person[] people1 = createArray();
			os.writeObject(people1);
		} catch (IOException e) {
			System.err.println("I/O problem: " + e);
			return;
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				System.err.println("Can't close: " + e);
			}
		}

		// Try to read it back in
		ObjectInputStream is = null;
		try {
			FileInputStream fin = new FileInputStream("people.gzip"); // obtains input bytes from a file in a file
																		// system.
			BufferedInputStream bin = new BufferedInputStream(fin); // add functionality to another input stream, which
																	// is the ability to buffer the input and to support
																	// the mark and rest methods.
			GZIPInputStream gin = new GZIPInputStream(bin); // this class implements a stream filter for reading
															// compressed data in the GZIP file format
			is = new ObjectInputStream(gin);

			Person[] people2 = (Person[]) is.readObject();
			printPerson(people2);
			System.out.println("Persons 0 and 4 same? " + (people2[0] == people2[4]));
			System.out.println("Birthday 2 and 3 same? " + (people2[2].getBirthDate() == people2[3].getBirthDate()));
		} catch (ClassNotFoundException e) {
			System.err.println("What's a Person? " + e);
		} catch (IOException e) {
			System.err.println("I/O problem: " + e);
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				System.err.println("Can't close: " + e);
			}
		}
	}
}
