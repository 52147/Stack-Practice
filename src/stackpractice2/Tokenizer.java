package stackpractice2;
/**
 * = Stack =
 * 
 *  - Stacks are used extensively in complier.
 *    - complier:
 *      - is a computer program that translates computer code written 
 *        in one program language(the source language) into another language(the target language).
 *      - primarily used for programs that translate the source code from a high-level programming language to a lower level language.
 *        (e.g. assembly language, object code, or machine code) to create an executable program.
 *        
 *      A diagrm of the operation of a typical multi-language, multi-target compiler:
 *        
 *        -Language 1 source code-> Complier front-end for language 1 (Lexical Analyzer(Scanner)-> Syntax/Semantic Analyzer (Parser)-> Intermediate-code Generator) 
 *        
 *        Non-optimized intermediate code -> Intermediate code optimizer-optimized intermediate code-> Target 1 Code Generator -(target 1 machine code )-> computer
 *  
 *  
 *  - In this chapter we present two simple components of a complier:
 *    
 *      1. a balanced symbol checker
 *      2. simple calculator
 *      
 * - balanced-symbol checker:
 * 
 *    - complier check your programs for syntax errors.
 *    - However, a lack of one symbol can cause the compiler to produce numerous lines of diagnostics without identifying the real error.
 *    - A useful tool to help debug compiler error messages is a program that checks whether symbols are balanced.
 *    - In other words, every { must correspond to a }, every [to a], and so on.
 *    
 *    - However, simply counting the numbers of each symbol is insufficient.
 *    - For example, the sequence [()] is legal, but the sequence [(]) is wrong.
 *    
 * - Algorithm:
 * 
 *   - a stack is useful here because we know that when a closing symbol such as ) is seen, it matches the most recently seen unclosed(.
 *   - therefore, by placing an opening symbol on a stack, we can easily determine whether a closing symbol makes sense.
 *   
 *     1. make an empty stack
 *     2. read symbols until the end of the file.
 *        a. if the symbols until the end of the file.
 *        b. if it is a closing symbol, do the following.
 *           i. if the stack is empty, report an error.
 *           ii. Otherwise, pop the stack. 
 *               If the symbol popped is not the corresponding opening symbol, report an error.
 *     3. At the end of the file, if the stack is not empty, report an error.
 *     
 *    - Stack operations in a balanced-symbol:
 *     
 *            symbol: ([}])[
 *            
 *            stack:
 *            
 *            1. empty 
 *            
 *          ( 2. put(
 *          
 *               (      
 *            
 *          [ 3. put  [
 *            
 *               [
 *               (
 *               
 *          ] 4. pop  [
 *          
 *               (
 *                
 *         }* 5.  empty    => error (because no matching symbol)
 *          
 *         )* 6.  empty    => error (because no matching symbol)
 *          
 *         [  7. put [
 *          
 *       eof* 8.  empty    
 *        
 *        
 *             Error (  -> indicated by *)
 *             { when expecting )
 *             ( with no matching opening symbol
 *             [ unmatched at end of input
 *        
 *        - In this algorithm, the fourth, fifth, and sixth symbols all generate errors.
 *        - The } is an error because the symbol popped from the top of the stack is a error because the stack is empty
 *        - so there is no corresponding (.
 *        - The [ is an error detected when the end of input is encounters and the stack is not empty.
 *        
 *        
 *   - To make this technique work for java programs, we need to consider all the contexts in which parentheses, braces, and brackets need not match.
 *     - For example,
 *       - we should not consider a parenthesis as a symbol if it occurs inside a comment, string constant, or character constant.
 *       - we thus need routines to skip comments, string constants, and character constant.
 *       - A character constant in Java can be difficult to recognize because of the many escape sequences possible, 
 *         so we need to simplify things.
 *       - We want to design a program that works for the bulk of inputs likely to occur. 
 *       
 *   - For the program to be useful, we must not only report mismatches but also attempt to identify where the mismatches occur. 
 *   - Consequently, we keep track of the line numbers where the symbols are seen.
 *   - When an error is encountered, obtaining an accurate message is always difficult.
 *   - If there is an extra }, does that mean that the } is extraneous.  
 *     Or was a { missing earlier?
 *   - We keep the error handling as simple as possible, 
 *     but once error has been reported, the program may get confused and start flagging many errors.
 *   - Thus, only the first error can be considered meaningful.
 *   - Even so, the program developed here is very useful.
 * 
 *   
 * - Implementation:
 * 
 *   - The program has 2 basic components.
 *   - 1. One part, called tokenization, is the process of scanning an input stream for opening and closing symbols(the tokens) and 
 *     generating the sequence of tokens that need to be recognized.
 *   
 *   - 2. The second part is running the balanced symbol algorithm, based on the tokens.
 *   
 *   - The 2 component are represented as seperate classes.
 *   
 *The Tokenizer class:
 *   
 *   - The Tokenizer class provides a constructor that requires a Reader and then provides a set of accessors that can be used to 
 *     
 *     1. The next token(either an opening/closing symbol)
 *     2. The current line number
 *     3. The number of errors(mismatched quotes and comments)
 *     
 *   - The Tokenizer class maintains most of this information in private data members.
 *   
 *   - Tokenizer class is a reference to a PushbackReader object and is initialized at construction.
 *   - Because of the I/O hierarchy, it may be constructed with any Reader object.
 *   - The current character being scanned is stored in ch, and the current line number is stored in currentLine.
 *   - Finally, an integer that counts the number of errors.
 *   - The constructor initializes the error count to 0 and the current line number to 1 and sets the PushbackReader reference.
 *  
 *   The Tokenizer class methods:
 *   
 *   - are concerned with keeping track of the current line and
 *     attempting to differentiate symbols that represent opening and closing tokens
 *     from those that are inside comments, character constants, and string constants.
 *     -> this process of recognizing tokens in a stream of symbols is called lexical analysis
 *   
 *   
 *   - nextChar and putBackChar
 *     - The nextChar method reads the next character from in, assigns it to ch,
 *       and updates currentLine if a newline is encountered.
 *     - it returns false only if the end of the file has been reached,
 *     
 *     - The complementary procedure putBackChar puts the current character, ch, back onto the input stream, 
 *       and decrements currentLine if the character is a newline.
 *   
 *     
 */
import java.io.Reader;
import java.io.PushbackReader;  // A character-stream reader that allows characters to be pushed back into the stream.
import java.io.IOException;

// The Tokenizer class used to retrieve tokens from an input stream.

// Tokenizer class.
//
// Construction: with a Reader object
// Public Operation:
// char getNextOpenClose()   --> Get next opening/closing symbol
// int getLineNumber()       --> Return current line number
// int getErrorCount()       --> Return number of parsing errors
// String getNextID()        --> Get next Java identifier
//
// Errors:
// Error checking on comments and quotes is performed
public class Tokenizer{
	
	private PushbackReader in; // the input stream
	private char ch; // current character
	private int currentLine; // current line
	private int errors; // number of errors seen
	
	
	public Tokenizer(Reader inStream) {
		errors = 0;
		ch = '0';
		currentLine = 1;
		in = new PushbackReader(inStream);
	}
	
	public static final int SLASH_SLASH = 0;
	public static final int SLASH_STAR = 1;
	
	public int getLineNumber() {
		return currentLine;
	}
	public int getErroCount() {
		return errors;
	}
	public char getNextOpenClose() {}
	
	public char getNextID() {}
	
	private boolean nextChar() {}
	
	private void putBackChar() {}
	
	private void skipComment(int start) {}
	
	private void skipQuote(char quoteType) {}
	
	private void processSlash() {}
	
	private static final boolean isIdChar(char ch) {}
	
	private String getRemainingString() {}
	
	
}
