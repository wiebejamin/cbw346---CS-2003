import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Name: Connor Wiebe

public class Lab2
{
	
	String[][] studentSeating;
	
	public void initialize(int size)
	{
		int width = (int) Math.sqrt(size) + 1;
		studentSeating = new String[width][width];
	}
	
	public void fill(File file)
	{
		try
		{
			Scanner input = new Scanner(file);
			for(int i = 0; i < studentSeating[0].length - 1; i++)
			{
				for(int j = 0; j < studentSeating[0].length - 1; j++)
				{
					if(input.hasNextLine())
					{
						studentSeating[i][j] = input.nextLine();
					}
					else break;
				}//end j for loop
			}    //end i for loop
		} 
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void readName()
	{
		while(true)
		{
			Scanner console = new Scanner(System.in);
			System.out.println("What seat do you want to know?");
			
			console.useDelimiter("//s*,//s*");
			
			int row = console.nextInt();
			int col = console.nextInt();
			
			System.out.println(row);
			
			System.out.println(studentSeating[row][col]);
		}
	}
	
	public static void main(String[] args)
	{
		Lab2 lab = new Lab2();
		
		Scanner console = new Scanner(System.in);
		System.out.println("What is the file containing the list of students?");
		String filename = console.next();
    	try
    	{
    		File inputFile = new File(filename);
    		
    	    Scanner input = new Scanner(inputFile);
    	    int studentCount = 0;
    	    while(input.hasNextLine()) {studentCount++; input.nextLine();}
    	    
    	    lab.initialize(studentCount);
    	    lab.fill(inputFile);
    	    lab.readName();
    	}
    	catch (IOException e)
    	{
    	    System.err.println("IOException in reading input file!!!");
    	}	

	}//end main
}//end class

/*
 * Create 2D array, size dependent on input
 * Given a combination, say student/empty
 * 
 * 
 */ 