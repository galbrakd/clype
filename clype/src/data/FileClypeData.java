/**
 * FileClypeData is a subclass of ClypeData.
 * This subclass contains the name of a file and the file's contents,
 * which can be recalled, encrypted, read, and overwritten.
 * 
 * fileName     - String representing the name of the file the class is based around.
 * fileContents - String representing the contents of the file.
 * 
 * @author Kenneth Galbraith
 */
package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileClypeData extends ClypeData{
	private String fileName;
	private String fileContents;
	
	/**
	 * A constructor that calls the original ClypeData constructor,
	 * sets the filename equal to the 'fileName' variable,
	 * and sets the fileContents to null.
	 * @param userName
	 * @param fileName
	 * @param type
	 */
	public FileClypeData( String userName, String fileName, int type) {
		super(userName, type);
		this.fileName = fileName;
		this.fileContents = null;
	}
	
	/**
	 * A constructor that calls the first FileClypeData constructor,
	 * setting the userName to 'Anon' and the service type to 0.
	 * This constructor also sets the 'fileName' and 'fileContents' variables
	 * equal to null.
	 */
	public FileClypeData() {
		super("Anon", 0);
		this.fileName = null;
		this.fileContents = null;
	}
	
	/**
	 * Sets the filename to the provided argument value.
	 * @param fileName
	 */
	public void setFileName( String fileName ) { this.fileName = fileName; }
	
	/**
	 * Returns this object's filename.
	 * @return
	 */
	public String getFileName() { return this.fileName; }
	
	/**
	 * Returns the data for this object, in this case the file contents.
	 */
	public String getData() { return fileContents; }
	
	/**
	 * Decrypts the file contents for this object and returns the result.
	 */
	public String getData(String key) { return this.decrypt(fileContents, key); }
	/**
	 * Block of code used for the common functionality for all functions
	 * that read a file.
	 * This function takes the provided filename and reads it one character at a time
	 * to the 'fileContents' variable.
	 * @param fileName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void readFunctionality(String fileName) 
			throws IOException, FileNotFoundException {
		try {
			FileReader reader = new FileReader(this.fileName);
			boolean doneReadingFile = false;
			
			while (!doneReadingFile) {
				int nextCharacterAsInteger = reader.read();
				doneReadingFile = nextCharacterAsInteger == -1;
				if (!doneReadingFile) {
					char nextCharacter = (char) nextCharacterAsInteger;
					fileContents += Character.toString(nextCharacter);
				}
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("This file cannot be found. File will not be read.");
		} catch (IOException ioe) {
			throw new IOException("Error encountered while reading from or closing file.");
		}
	}
	
	/**
	 * Reads the contents from the file with name stored in this object's 'fileName'
	 * to this object's 'fileContents'.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void readFileContents() 
			throws IOException, FileNotFoundException {
		readFunctionality(this.fileName);
	}
	
	/**
	 * Reads the contents from the file with name stored in this object's 'fileName'
	 * to this object's 'fileContents'. The 'fileContents' string is then encrypted
	 * using the provided key.
	 * @param key
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void readFileContents(String key) 
			throws IOException, FileNotFoundException {
		readFunctionality(this.fileName);
		this.encrypt(fileContents, key);
	}
	
	/**
	 * Block of code used for the common functionality for all functions
	 * that write from 'fileContents' to a file.
	 * This function 
	 * @param fileName
	 * @param fileContents
	 * @throws IOException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	private void writeFunctionality(String fileName, String fileContents) 
			throws IOException, IndexOutOfBoundsException, FileNotFoundException {
		try {
			FileWriter writer = new FileWriter(fileName);
			for (int i = 0; i < this.fileContents.length(); ++i) {
				writer.write(fileContents.charAt(i));
			}
			writer.close();
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("This file cannot be found. File will not be read.");
		} catch (IndexOutOfBoundsException iobe) {
			throw new IndexOutOfBoundsException("Went out of bounds while moving through \"fileContents\"");
		} catch (IOException ioe) {
			throw new IOException("Error encountered while reading from or closing file.");
		}
	}
	
	/**
	 * Writes the contents from this object's 'fileContents' variable to the file specified
	 * by this object's 'fileName' variable
	 * @throws IOException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	public void writeFileContents() 
			throws IOException, IndexOutOfBoundsException, FileNotFoundException {
		writeFunctionality(this.fileName, this.fileContents);
	}
	
	/**
	 * Decrypts the 'fileContents' variable and writes it to the file specified
	 * by this object's 'fileName' variable
	 * @param key
	 * @throws IOException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	public void writeFileContents(String key) 
			throws IOException, IndexOutOfBoundsException, FileNotFoundException  {
		String decryption = this.decrypt(fileContents, key);
		writeFunctionality(this.fileName, decryption);
	}
	
	/**
	 * Generates and returns a hashcode value for the FileClypeData object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		if (fileName != null)
			result = 37 * result + fileName.hashCode();
		if (fileContents != null)
			result = 37 * result + fileContents.hashCode();
		result = 37 * result + userName.hashCode();
		result = 37 * result + type;
		return result;
	}
	
	/**
	 * Compares two objects, ensures that they can both be cast to FileClypeData objects,
	 * and returns whether the two objects contain all of the same data with the same values.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FileClypeData) || o.equals(null))
			return false;
		
		FileClypeData fcd = (FileClypeData) o;
		
		if (fcd.userName.equals(this.userName) && 
				fcd.fileName.equals(this.fileName) && 
				fcd.fileContents.equals(this.fileContents) &&
				fcd.type == this.type)
			return true;
		return false;
	}
	
	/**
	 * Returns a String that describes the data that this FileClypeData object contains.
	 */
	@Override
	public String toString() {
		return "A subclass of ClypeData meant to store files.\nUsername: " + 
				this.userName + "\nFilename: " + this.fileName + 
				"\nFile Contents: " + this.fileContents;
	}
}
