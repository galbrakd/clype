/**
 * ClypeClient is an object that represents the client for a session in the Clype program.
 * It contains the user's information related to the session, as well as the means
 * to begin sessions, hash client objects, and test client objects for equality.
 * 
 * userName                - String representing the name of the client.
 * hostName                - String representing the name of the computer representing the server
 * port                    - integer representing the port number on the server that has been connected to
 * closeConnection         - boolean representing whether connection is closed or not
 * dataToSendToServer      - ClypeData object representing data sent to the server
 * dataToReceiveFromServer - ClypeData object representing data received from the server
 * DEFAULTPORT             - static integer representing the default port
 * inFromStd               - Scanner to take the user's session type selection and possibly a filename
 * 
 * @author Kenneth Galbraith
 */

package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.Scanner;

import data.ClypeData;
import data.FileClypeData;
import data.MessageClypeData;

public class ClypeClient {
	private String userName;
	private String hostName;
	private int port;
	private boolean closeConnection;
	private ClypeData dataToSendToServer;
	private ClypeData dataToReceiveFromServer;
	static int DEFAULTPORT = 7000;
	Scanner inFromStd = null;
	public static final String key = "test";
	
	/**
	 * Constructor that sets the username, host name, and port equal
	 * to user-provided values. The 'closeConnection' value is set to false,
	 * and the data being sent and received from the server are both set to null.
	 * @param userName
	 * @param hostName
	 * @param port
	 */
	public ClypeClient( String userName, String hostName, int port ) throws IllegalArgumentException {
		if (userName == null || hostName == null || port < 1024) {
			throw new IllegalArgumentException("Illegal argument provided. \nUsername or hostname cannot be null, and port number cannot be less than 1024.");
		}
		else {
			this.userName = userName;
			this.hostName = hostName;
			this.port = port;
			this.closeConnection = false;
			dataToSendToServer = null;
			dataToReceiveFromServer = null;
		}
	}
	
	/**
	 * Constructor that calls the original ClypeClient constructor,
	 * the only difference being that the port is set to DEFAULTPORT.
	 * @param userName
	 * @param hostName
	 */
	public ClypeClient( String userName, String hostName ) throws IllegalArgumentException { 
		this(userName, hostName, DEFAULTPORT); 
	}
	
	/**
	 * Constructor that calls the previous constructor, 
	 * the only difference being that 'hostName' is set
	 * to "localhost".
	 * @param userName
	 */
	public ClypeClient( String userName ) throws IllegalArgumentException {
		this(userName, "localhost");
	}
	
	/**
	 * Constructor that calls the previous constructor,
	 * the only difference being that 'userName' is 
	 * set to null.
	 */
	public ClypeClient() {
		this( "Anon" );
	}
	
	/**
	 * This method starts this client's communication with the server
	 * by initializing 'inFromStd', and then reading and printing the client's data.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void start() throws FileNotFoundException, IOException {
		inFromStd = new Scanner(System.in);
		this.readClientData();
		dataToReceiveFromServer = dataToSendToServer;
		this.printData();
	}
	
	/**
	 * This method takes input for what type of session the user wants to open
	 * and possibly a username if it's relevant. It then initializes the 'dataToSendToServer'
	 * object based on the user's session choice.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readClientData() throws FileNotFoundException, IOException {	// reads the data from the client, does not return anything
		System.out.println("Type which kind of session you would like to open and press \'enter\': ");
		System.out.println("0: give a listing of all users connected to this session");
		System.out.println("1: log out (close this client's connection");
		System.out.println("2: send a file");
		System.out.println("3: send a message");
		int sessionType = inFromStd.nextInt();
		System.out.println("Enter the name of the file to be sent. If there is no file to be sent, press \'Space\' then \'enter\'");
		String fileNameToSend = inFromStd.next();
		if (sessionType == ClypeData.DONE) { closeConnection = true; }
		else if (sessionType == ClypeData.SENDFILE) {
			dataToSendToServer = new FileClypeData(this.userName, fileNameToSend, ClypeData.SENDFILE);
			try {
				((FileClypeData) dataToSendToServer).readFileContents();
			} catch (IOException ioe) {
				System.err.print("Could not read file. \'dataToSendToServer\' reset to null.");
				dataToSendToServer = null;
			}
		}
		else if (sessionType == ClypeData.LISTUSERS) {}
		else {
			dataToSendToServer = new MessageClypeData(this.userName, null, 3);
		}
	}	
	
	/**
	 * Sends data to server...
	 */
	public void sendData() {}
	
	/**
	 * Receives data from the server...
	 */
	public void receiveData() {}
	
	/**
	 * Prints the contents of 'dataToReceiveFromSever' to the client.
	 */
	public void printData() {
		if (dataToReceiveFromServer.getType() == ClypeData.SENDFILE) {
			System.out.println(((FileClypeData) dataToReceiveFromServer).toString());
		}
		else if (dataToReceiveFromServer.getType() == ClypeData.SENDMESSAGE) {
			System.out.println(((MessageClypeData) dataToReceiveFromServer).toString());
		}
	}
	
	/**
	 * Returns this object's username.
	 * @return
	 */
	public String getUserName() { return this.userName; }
	
	/**
	 * Returns this object's host name.
	 * @return
	 */
	public String getHostName() { return this.hostName; }
	
	/**
	 * Returns the object's port.
	 * @return
	 */
	public int getPort() { return this.port; }
	
	/**
	 * Generates and returns a hashcode value for the ClypeClient object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + userName.hashCode();
		result = 37 * result + hostName.hashCode();
		result = 37 * result + port;
		if (dataToSendToServer != null)
			result = 37 * result + dataToSendToServer.hashCode();
		if (dataToReceiveFromServer != null)
			result = 37 * result + dataToReceiveFromServer.hashCode();
		return result;
	}
	
	/**
	 * Compares two objects, ensures that they can both be cast to ClypeClient objects,
	 * and returns whether the two objects contain all of the same data with the same values.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ClypeClient))
			return false;
		ClypeClient cc = (ClypeClient) o;
		
		if (cc.userName.equals(this.userName) &&
				cc.hostName.equals(this.hostName) && 
				cc.port == this.port &&
				cc.closeConnection == this.closeConnection &&
				cc.dataToSendToServer.equals(this.dataToSendToServer) &&
				cc.dataToReceiveFromServer.equals(this.dataToReceiveFromServer))
			return true;
		return false;
	}
	
	/**
	 * Returns a String that describes the data that this ClypeClient object contains.
	 */
	@Override
	public String toString() {
		return "A class to represent a client user for Clype.\n" +
				"Username: " + this.userName + "\n" +
				"Hostname: " + this.hostName + "\n" +
				"Port: " + this.port + "\n" +
				"Connection Closed?: " + this.closeConnection + "\n" +
				"Data to send to server: " + this.dataToSendToServer + "\n" +
				"Data to receive from server: " + this.dataToReceiveFromServer + "\n";
	}
}
