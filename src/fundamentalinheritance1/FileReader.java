package fundamentalinheritance1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * i/o: the decorator pattern
 * 
 *  - The idea of nesting wrappers in order to add functionality is known as the decorator pattern.
 *
 * 
 *  - I/O in Java looks fairly complex to use but works nicely for doing I/O
 *    with different sources, such as the terminal, files, and internet sockets.
 *    - socket: A socket is one endpoint of a two-way communication link between two programs running on the network.
 *  
 *  - Because it is designed to be extensible, there are lots of classes-over 50 in all.
 *  - It is cumbersome to use for trivial tasks;
 *    for example,
 *    reading a number from the terminal requires substantial work.
 *    
 *  Stream classes:
 *    
 *  - Input is done through the use of stream classes.
 *  - Because Java was designed for Internet programming, most of the I/O centers around byte oriented reading and writing.
 *  
 *  Byte-oriented I/O:
 *  
 *  - Byte-oriented I/O is done with stream classes that extend InputStream or OutputStream.
 *  - InputStream and OutputStream are abstract classes and not interfaces, so there is no such thing as a stream open for both input and output.
 *  - These classes declare an abstract read and write method for single-byte I/O, respectively.
 *  - Also a small set of concrete methods such as close and block I/O (which can be implemented in terms of calls to single-bytes I/O).
 *    
 *    - Example of these classes include FileInputStream and FileOutputStream, as well as
 *      the hidden SocketInputStream and SocketOutputStream.
 *      (The socket streams are produced by methods that return an object statically typed as InputStream or OutputStream)
 *  
 *  Character-oriented I/O:
 *  
 *  - Character-oriented I/O is done with classes that extend the abstract classes Reader and Writer.
 *  - These also contain read and write methods.
 *  - There are not as many Reader and Writer classes as InputStream and OutputStream classes.
 *  
 *  - However, this is not a problem, because of the InputStreamReader and OutputStreamWriter classes.
 *  - These are called bridges because they cross over from Stream to Reader and Writer hierarchies.
 *  - An InputStreamReader is constructed with any InputStream and creates an object that IS-A Reader.
 *    - For example,
 *      we can create a Reader for files using.
 *    
 *      InputStream fis = new FileInputStream("foo.txt");
 *      Reader fin = new InputStreamReader(fis);
 *      
 *    - there is a FileReader convenience class that does this already; program below provides a plausible implementation.
 *    
 *    
 *    - From a Reader, we can do limited I/O; the read method returns one character.
 *    - If we want to read one line instead, we need a class called BufferedReader.
 *    - Like other Reader objects, a BufferedReader is constructed from any other Reader,
 *      but it provides both buffering and readLine method.
 *    - Thus, continuing the previous example,
 *      
 *      BufferedReader bin = new BufferedReader(fin);
 *      
 *  - Wrapping an InputStream inside an InputStreamReader inside a BufferedReader works for any InputStream, including System.in or sockets.
 *     
 *  
 *  - The wrapping idea is an example of a commonly used Java design pattern.
 *  - Similar to the BufferedReader is the PrintWriter, which allows us to do println operation.
 *  
 *  
 *  
 *  convenience class or convenience class:
 *  - generally means a class that does not do anything itself, but provides simplified access to other classes or group of classes.
 *    
 *    - For example,
 *      the random() method
 *      in java.lang.Math is a convenience method that provides a simple way to use the more powerful java.util.Random class.
 *      
 *  
 *
 */

// The FileReader convenience class
public class FileReader extends InputStreamReader{
	
	public FileReader(String name) throws FileNotFoundException{
		super(new FileInputStream(name));
	}

}
