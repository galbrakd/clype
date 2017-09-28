package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.ClypeClient;

public class TestClypeClient {
	/**
	 * Assignment 1 Tests
	 */
	// Testing constructors for ClypeClient
	private static ClypeClient test1 = new ClypeClient("kenny", "host1", 3000);
	private static ClypeClient test2 = new ClypeClient("michael", "host2");
	private static ClypeClient test3 = new ClypeClient("ryan");
	private static ClypeClient test4 = new ClypeClient();
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		/**
		 * Assignment 1 Tests
		 */
		// Tests for ClypeClient functions
		System.out.println("test1's username: " + test1.getUserName());
		System.out.println("test2's hostname: " + test2.getHostName());
		System.out.println("test3's port: " + test3.getPort());
		System.out.println("test1's hashcode: " + test1.hashCode());
		System.out.println("Is test3 equal to test4?: " + test3.equals(test4));
		System.out.println("test2's toString: " + test2.toString());
		
		/**
		 * Assignment 2 Tests
		 */
		test1.start();
		test2.start();
		test1.readClientData();
		test2.printData();
	}
}
