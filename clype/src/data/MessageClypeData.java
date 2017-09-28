/**
 * MessageClypeData is a subclass of ClypeData.
 * This subclass is used to contain a message, which can be returned and encrypted if need be.
 * 
 * message  - A String representing the instant message that this class contains
 * 
 * @author Kenneth Galbraith
 */
package data;

public class MessageClypeData extends ClypeData {
	private String message;
	
	/**
	 * A constructor that calls the original ClypeData constructor
	 * and sets the object's message equal to the 'message' variable
	 * the user provides.
	 * @param userName
	 * @param message
	 * @param type
	 */
	public MessageClypeData( String userName, String message, int type ) { 
		super(userName, type);
		this.message = message;
	}
	
	/**
	 * A constructor that calls the first MessageClypeData constructor,
	 * sets the username to 'Anon', the message to null, and the service type to 0.
	 */
	public MessageClypeData() { 
		this("Anon", null, 0);
	}
	
	/**
	 * A constructor that is the same as the first MessageClypeData constructor,
	 * except it also encrypts the message that the user provides.
	 * @param userName
	 * @param message
	 * @param key
	 * @param type
	 */
	public MessageClypeData( String userName, String message, String key, int type ) {
		super(userName, type);
		this.message = encrypt(message, key);
	}
	
	/**
	 * Returns the data for this object, in this case the message.
	 */
	public String getData() { return message; }
	
	/**
	 * Decrypts the message for this object and returns the result.
	 */
	public String getData(String key) { return this.decrypt(message, key); }
	
	/**
	 * Generates and returns a hashcode value for the MessageClypeData object.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		if (message != null)
			result = 37 * result + message.hashCode();
		result = 37 * result + userName.hashCode();
		result = 37 * result + type;
		return result;
	}
	
	/**
	 * Compares two objects, ensures that they can both be cast to MessageClypeData objects,
	 * and returns whether the two objects contain all of the same data with the same values.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MessageClypeData) || o.equals(null))
			return false;
		MessageClypeData mcd = (MessageClypeData) o;
		
		if (mcd.userName.equals(this.userName) && 
				mcd.message.equals(this.message) && 
				mcd.type == this.type)
			return true;
		return false;
	}
	
	/**
	 * Returns a String that describes the data that this MessageClypeData object contains.
	 */
	@Override
	public String toString() {
		return "A subclass of ClypeData meant to store messages.\nUsername: " + 
				this.userName + "\nMessage: " + this.message;
	}
}
