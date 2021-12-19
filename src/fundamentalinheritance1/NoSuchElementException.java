package fundamentalinheritance1;
/**
 * = fundamental inheritance in java =
 * 
 * - Two important places where inheritance is used in Java are the
 *  
 *   1. Object class
 *   2. and the hierarchy of exceptions.
 *   
 *   - the Object class
 *   
 *     - Java specifies that if a class does not extend another class, then it implicitly
 *       extends the class Object(defined in java.lang).
 *     - As a result, every class is either a direct or indirect subclass of Object.
 *     
 *     - The Object class contains several methods, and since it is not abstract, all have implementations.
 *     - The most commonly used method is toString, which we have already seen.
 *     
 *     - If toString is not written for a class, an implementation is provided that concatenates the name of the class,
 *       and @, and the class's "hash code."
 *       
 *     - Other methods are equals and the hashCode.
 *     
 *  - the hierarchy of exceptions:
 *  
 *                         Throwable
 *                        /         \
 *                    Error          Exception
 *                    /           /             \
 *     OutOfMemoryError     RuntimeException    java.io.IOException
 *     InternalError             /                            \
 *     UnknownError         NullPointerException               java.io.FileNotFoundException
 *                          ArrayIndexOutOfBoundsException
 *                          ArithmeticException
 *                          UnsupportedOperationException
 *                          NoSuchMethodException
 *                          InvalidArgumentException
 *                          java.util.NoSuchElementException
 *                          java.util.ConcurrentModificationException
 *                          java.util.EmptyStackException
 *                          ClassCastException
 *                     
 *  
 *    - there are several types of exceptions.
 *    - The root of the hierarchy is Throwable, which defines a set of printStackTrace methods, provides a toString implementation,
 *      a pair of constructors, and little else.
 *    - The hierarchy is split off into Error, RuntimeException, and checked exceptions.
 *      - A checked exception is any Exception that is not a RuntimeException.
 *      - For the most part, each new class extends another exception class, providing only a pair of constructors.
 *      - It is possible to provide more, but none of the standard exceptions bother to do so.
 *      
 *      - In this package, we implement 3 of the standard java.util exceptions.
 *      - One such implementation, which shows that new exception classes typically provide 
 *        little more than constructors.
 * 
 *
 */

public class NoSuchElementException extends RuntimeException{
	
	/**
	 * Constructs a NoSuchElementException with no detail message.
	 */
	public NoSuchElementException(){
		
	}
	
	/**
	 * Constructs a NoSuchElementException with a detail message.
	 * @param msg the detail message
	 */
	public NoSuchElementException(String msg) {
		super(msg);
	}
	
	

}
