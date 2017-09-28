/**
 * ClypeData is the abstract superclass of FileClypeData and MessageClype data.
 * It contains the mutual functionality for these classes as well as the constants used to represent types of sessions the user can open.
 * 
 * userName - String representing the name of the client user
 * type		- Integer used to determine which service to provide the client (numbered 0-3)
 * date		- Date object representing the data when the ClypeData object was created
 * LISTUSERS, DONE, SENDFILE, SENDMESSAGE are constants that represent the four types of services, respectively
 * 
 * @author Kenneth Galbraith
 */
package data;

import java.util.Date;

public abstract class ClypeData {
	protected String userName;
	protected int type;
	protected Date date;
	public static int LISTUSERS = 0;
	public static int DONE = 1;
	public static int SENDFILE = 2;
	public static int SENDMESSAGE = 3;
	
	/**
	 * Constructor for ClypeData that takes a username and a type of service.
	 * This constructor also initializes the date that the object was created.
	 * @param userName
	 * @param type
	 */
	public ClypeData( String userName, int type ) { 
		this.userName = userName; 
		this.type = type;
		this.date = new Date();
	}
	
	/**
	 * Constructor for ClypeData that calls the first constructor 
	 * but sets the username to 'Anon' by default.
	 * @param type
	 */
	public ClypeData( int type ) { this("Anon", type); }
	
	/**
	 * Constructor for ClypeData that calls the first constructor 
	 * and initializes the name to 'Anon' and the type to 0
	 */
	public ClypeData() { this("Anon", 0); }
	
	/**
	 * Returns the type of the ClypeData object
	 * @return
	 */
	public int getType() { return this.type; }
	
	/**
	 * Returns the username of the ClypeData object
	 * @return
	 */
	public String getUserName() { return this.userName; }
	
	/**
	 * Returns the date that the ClypeData object was created
	 * @return
	 */
	public Date getDate() { return this.date; }
	
	/**
	 * Abstract method which is overriden in each subclass to return the data that the object contains (message or file)
	 * @return
	 */
	public abstract String getData();
	
	/**
	 * Abstract method which decrypts the data that the object contains and returns the result
	 * @param key
	 * @return
	 */
	public abstract String getData(String key);
	
	/**
	 * Using the Vignere cipher, this function encrypts the String taken as an input using the provided key and returns the result.
	 * This is done by shifting the ascii value of the original character according to the corresponding key character and appending
	 * the result of this operation to 'encryptedString', which is created when the function is called. If a character is shifted
	 * out of the range of the letters in ascii, it will be reset to the first letter and adjusted accordingly.
	 * @param inputStringToEncrypt
	 * @param key
	 * @return
	 */
	protected String encrypt( String inputStringToEncrypt, String key ) {
		String encryptedString = "";
		int currentKeyIndex = 0;
		for (int i = 0; i < inputStringToEncrypt.length(); i++) {
			char currentChar = inputStringToEncrypt.charAt(i);
			if (Character.isLetter(currentChar)) {
				int newChar = currentChar + key.charAt(currentKeyIndex);
				if ((int) key.charAt(currentKeyIndex) > 90)
					newChar -= 97;
				else
					newChar -= 65;
				if ((Character.isUpperCase(currentChar) && newChar > 90) 
						|| (Character.isLowerCase(currentChar) && newChar > 122) )
					newChar -= 26;
				currentChar = (char) newChar;
				
				currentKeyIndex++;
				if (currentKeyIndex >= key.length())
					currentKeyIndex = 0;
			}
			encryptedString += currentChar;
		}
		return encryptedString;
	}
	
	/**
	 * This function takes a String that is encrypted with the Vignere cipher, decrypts it,
	 * and returns the result.
	 * The same method of shifting the original character's ascii value and appending it is used here,
	 * only in reverse compared to the original encrypt function.
	 * @param inputStringToDecrypt
	 * @param key
	 * @return
	 */
	public String decrypt( String inputStringToDecrypt, String key ) {
		String decryptedString = "";
		int currentKeyIndex = 0;
		for (int i = 0; i < inputStringToDecrypt.length(); i++) {
			char currentChar = inputStringToDecrypt.charAt(i);
			if (Character.isLetter(currentChar)) {
				int newChar = currentChar - key.charAt(currentKeyIndex);
				if ((int) key.charAt(currentKeyIndex) < 97)
					newChar += 65;
				else
					newChar += 97;
				if((Character.isUpperCase(currentChar) && newChar < 65)
					|| (Character.isLowerCase(currentChar) && newChar < 97))
					newChar += 26;
				currentChar = (char) newChar;
				
				currentKeyIndex++;
				if (currentKeyIndex >= key.length())
					currentKeyIndex = 0;
			}
			decryptedString += currentChar;
		}
		return decryptedString;
	}
}
