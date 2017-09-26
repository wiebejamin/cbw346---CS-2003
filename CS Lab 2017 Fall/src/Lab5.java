import java.util.Scanner;

//Name: Connor Wiebe
//Lab 5: Recursion - Palindrome

public class Lab5
{
	
	public static void main(String[] args)
	{
		while(true)
		{
			System.out.println("What word would you like to test?");
			Scanner console = new Scanner(System.in);
			String test = console.nextLine();
			if(isPalindrome(test)) System.out.println('"' + test + '"' + " is a palindrome.");
			else System.out.println(test + " is not a palindrome.");
			System.out.println("Would you like to test another?");
			String answer = console.nextLine();
			if(answer.toUpperCase().equals("NO") || answer.toUpperCase().equals("N")) break;
			console.close();
		}

		System.out.println("Goodbye!");
		
	}
	
	public static boolean isPalindrome(String word)
	{
		//turns the String upper case to remove case sensitivity
		word = word.toUpperCase();
		
		//reaplces punctuation with spaces to be ignored by the Scanner
		word.replace(',', ' ');
		word.replace('.', ' ');
		word.replace('!', ' ');
		word.replace('?', ' ');
		
		//removes spaces using a scanner
		Scanner test = new Scanner(word);
		String tmp = "";
		while(test.hasNext()) tmp = tmp + test.next();
		word = tmp;
		test.close();
		
		//tests the palindrome
		//if the string has length 0 or 1, returns true base case
		if(word.length() == 1 || word.length() == 0) return true;
		
		//if the first and last character are the same,
		//tests the palindrome without first and last character
		if(word.charAt(0) == word.charAt(word.length()-1))
			return isPalindrome(word.substring(1, word.length()-1));
		//otherwise, returns false base case
		return false;
	}
}






