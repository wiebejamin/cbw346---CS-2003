
/**
 * CS 2003 - Lab 1
 * Code to find the maximum integer in a given file
 * NOTE:
 * there are bugs including those causing compilation errors and
 * resulting in runtime logical problems
 *
 * @ConnorWiebe
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Lab1a
{
    /**stores the data retrieved from the file */
    Vector <Integer> intVector; 
    /** variable used to compute the run-time */
    long startTime, endTime, totalTime;
    /** Constructor: computes the running time and call readFile
     * method
     */
    public void lab() {
	startTime = System.currentTimeMillis();
	readFile();
	endTime = System.currentTimeMillis();    
	totalTime = endTime - startTime;
	System.out.println("total time taken: " + totalTime + " milliseconds");
    }

    /* Method description here */
    public void readFile()
    {
	try
	{
	    File inputFile = new File("Lab1a.dat");
	    Scanner input = new Scanner(inputFile);
	    intVector = new Vector<Integer>();
	    int max, elt, count=0;
	    
	    // store all elements in a vector
	    while (input.hasNextInt())
	    {
	    	elt=input.nextInt();
	    	count++;
	    	intVector.addElement(elt);
	    }
	    
	    // print on the terminal each elements of intVector
	    System.out.printf("There are %d integers in the input file:\n",
			       count);
	    for (int value: intVector)
	    	System.out.printf("%d ",value);
	    System.out.println();
	    
	    // Find the max
	    max = intVector.get(1);
	    for(int i=1;i<intVector.size();i++)
	    {
	    	int value=intVector.get(i);
	    	if(max < value)
	    		max = value;
	    }

	    //output results
	    System.out.printf("The maximum integer in the input file is %d\n",max);
	    input.close();
	}
	catch (IOException e)
	{
	    System.err.println("IOException in reading input file!!!");
	}	
    } //end readFile()
    
    /** main : creates an Object Lab1a */
    
    
    public static void main(String args[])
    {
    	Lab1a lab = new Lab1a();
    	lab.readFile();
    } //end main 
} //end class Lab1a






















