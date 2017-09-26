
/**
 * CS 2003 - Lab 1. Code to compute the average of doubles from an input
 * file.  NOTE: add data members and methods at your convenience. Use
 * the most accurate data types. You can reuse bits of code from
 * the class Lab1a.
 *
 * @ConnorWiebe
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.IOException;

public class Lab1b
{
    
    // data members

    /** Constructor: calls method readFile that reads the doubles
     * contained in the file "<code>filename</code>" and computes the
     * average of the elements.
     @param filename name of the file containing doubles.
    */
    public Lab1b(String filename)
    {
    	readFile(filename);
    }

    /** Reads double from a file named <code>filename</code> and
     * computes the average of the elements contained in the file 
     * @param filename name of the file containing the doubles 
     */
    public void readFile(String filename)
    {
    	//  -- TO COMPLETE -- Method description here
    	try
    	{
    		//reads filename
    	    File inputFile = new File(filename);
    	    Scanner input = new Scanner(inputFile);
    	    
    	    //calculates average and prints it
    	    double sum = 0;
    	    int count = 0;
    	    while(input.hasNextDouble())
    	    {
    	    	sum = sum + input.nextDouble();
    	    	count++;
    	    }
    	    double ave = sum / count;
    	    System.out.println("The average of the values is " + ave);
    	}
    	catch (IOException e)
    	{
    	    System.err.println("IOException in reading input file!!!");
    	}	
    } //end readFile()
    
    

    /** main : creates a Lab1b Object with the filename passed in
     * argument*/
    public static void main(String args[])
    {
	    new Lab1b("Lab1b.dat");
    } //end main
    
} //end class Lab1b
