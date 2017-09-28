/**
 * ClypeServer is an object that represents a server used in a clype session.
 * It contains information related to the server, as well as means to start a session
 * with a client, send and receive data, hash server objects, and test server objects
 * for equality.
 * 
 * port                    - integer representing port number on the server the object is connected to.
 * closeConnection         - boolean representing whether the connection is closed or not.
 * dataToReceiveFromClient - ClypeData object representing data received from the client.
 * dataToSendToClient      - ClypeData object representing data sent to the client.
 * DEFAULTPORT             - static integer representing the default port.
 * 
 * @author Kenneth Galbraith
 */

package main;

import data.ClypeData;

public class ClypeServer {
	private int port;
	private boolean closeConnection;
	private ClypeData dataToReceiveFromClient;
	private ClypeData dataToSendToClient;
	static int DEFAULTPORT = 7000;
	
	/**
	 * Constructor that sets the port of the server based on user input.
	 * 'closeConnection' is set to false, and the data received from
	 * and sent to the client is set to null.
	 * @param port
	 */
	public ClypeServer( int port ) {
		this.closeConnection = false;
		this.port = port;
		this.dataToReceiveFromClient = null;
		this.dataToSendToClient = null;
	}
	
	/**
	 * Constructor that calls the first constructor, the only difference
	 * is that the port is set to DEFAULTPORT.
	 */
	public ClypeServer() {
		this(DEFAULTPORT);
	}
	
	/**
	 * Helps to start the clype session...
	 */
	public void start() {}
	
	/**
	 * Receives data from the client...
	 */
	public void receiveData() {}
	
	/**
	 * Sends data to the client...
	 */
	public void sendData() {}
	
	/**
	 * Returns this object's port number.
	 * @return
	 */
	public int getPort() { return this.port; }
	
	/**
	 * Generates and returns a hashcode value for the ClypeServer object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + port;
		if (dataToSendToClient != null)
			result = 37 * result + dataToSendToClient.hashCode();
		if (dataToReceiveFromClient != null)
			result = 37 * result + dataToReceiveFromClient.hashCode();
		return result;
	}
	
	/**
	 * Compares two objects, ensures that they can both be cast to ClypeServer objects,
	 * and returns whether the two objects contain all of the same data with the same values.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ClypeServer))
			return false;
		ClypeServer cs = (ClypeServer) o;
		
		if (cs.port == this.port &&
				cs.closeConnection == this.closeConnection &&
				cs.dataToReceiveFromClient.equals(this.dataToReceiveFromClient) &&
				cs.dataToSendToClient.equals(this.dataToSendToClient))
			return true;
		return false;
	}
	
	/**
	 * Returns a String that describes the data that this ClypeClient object contains.
	 */
	@Override
	public String toString() {
		return "A class to represent a server for Clype.\n" +
				"Port: " + this.port + "\n" +
				"Connection Closed?: " + this.closeConnection + "\n" +
				"Data to send to client: " + dataToSendToClient.toString() + "\n" +
				"Data to receive from client: " + dataToReceiveFromClient.toString() + "\n";
	}
}
