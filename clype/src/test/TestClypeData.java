package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import data.ClypeData;
import data.FileClypeData;
import data.MessageClypeData;

public class TestClypeData {
	/**
	 * Assignment 1 Tests
	 */
	private static ClypeData test4 = new FileClypeData("kenny", "testFile", 2);
	private static ClypeData test5 = new FileClypeData();
	
	private static ClypeData test6 = new MessageClypeData("kenny", "test", 2);
	private static ClypeData test7 = new MessageClypeData();
	
	/**
	 * Assignment 2 Tests
	 */
	private static ClypeData test8 = new MessageClypeData("kenny", "All your base are belong to us!", "test", 3);
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		/**
		 * Assignment 1 Tests
		 */
		// Tests for ClypeData class functions
		System.out.println("ClypeData test4's type: " + test4.getType());
		System.out.println("ClypeData test5's username: " + test5.getUserName());
		System.out.println("ClypeData test6's date: " + test6.getDate());
		
		// Tests for FileClypeData class functions
		FileClypeData temp = (FileClypeData)test4;
		System.out.println("FileClypeData test4's filename:"+ temp.getFileName());
		temp.setFileName("t3st");
		System.out.println("FileClypeData test4's new filename: " + temp.getFileName());
		System.out.println("FileClypeData test4's file: " + test4.getData());
		System.out.println("FileClypeData test5's hashcode: " + test5.hashCode());
		System.out.println("Is test4 equal to test5?: " + test4.equals(test5));
		System.out.println("FileClypeData test5's toString: " + test5.toString());
		
		// Tests for MessageClypeData class functions
		System.out.println("MessageClypeData test6's instant message: " + test6.getData());
		System.out.println("MessageClypeData test6's hashcode: " + test6.hashCode());
		System.out.println("Is test6 equal to test7?: " + test6.equals(test7));
		System.out.println("MessageClypeData test7's toString: " + test7.toString());
		
		/**
		 * Assignment 2 Tests
		 */
		// Encryption tests and new getData methods
		System.out.println("Encrypted message from test8: " + test8.getData());
		System.out.println("New getData test for MessageClypeData (decrypting message): " + test8.getData("test"));
		
		// Writing input from a test file to 'fileContents' and writing 'fileContents' to a test file
		((FileClypeData)test4).readFileContents();
		System.out.println("\'fileContents\' after being filled with input from file: " + test4.getData());
		((FileClypeData)test5).setFileName("encryptedTestFile");
		((FileClypeData)test5).writeFileContents("test");
		System.out.println("\'fileContents\' written to test5's fileName");
	}
}