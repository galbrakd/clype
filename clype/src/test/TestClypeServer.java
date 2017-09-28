package test;

import main.ClypeServer;

public class TestClypeServer {
	// Testing constructors for ClypeServer
	private static ClypeServer test1 = new ClypeServer(3000);
	private static ClypeServer test2 = new ClypeServer();
	
	public static void main(String args[]) {
		// Tests for ClypeServer functions
		System.out.println("test1's port: " + test1.getPort());
		System.out.println("test2's port: " + test2.hashCode());
		System.out.println("Is test1 equal to test2?: " + test1.equals(test2));
		System.out.println("test1's toString: " + test1.toString());
	}
}
