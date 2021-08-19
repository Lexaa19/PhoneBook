/**
 * Represents the Contact as POJO.
 * 
 * It also holds the information about the contact.
 * 
 * It doesn't hold any logic regarding the contact.
 * 
 * 
 * @author Caliopi Durdunescu
 * @version 1
 * @since 18.08.2021
 */
class Contact {

	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String address;

	/**
	 * Creates a contact with the specified phone number, first name, last name,
	 * email and address.
	 * 
	 * @param phoneNumber The contact's phone number.
	 * @param firstName   The contact's first name.
	 * @param lastName    The contact's last name.
	 * @param email       The contact's email.
	 * @param address     The contact's address.
	 */
	public Contact(String phoneNumber, String firstName, String lastName, String email, String address) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	/**
	 * Creates a copy constructor.
	 * 
	 * @param contact An object of type contact
	 */
	public Contact(Contact contact) {
		this.phoneNumber = contact.phoneNumber;
		this.firstName = contact.firstName;
		this.lastName = contact.lastName;
		this.email = contact.email;
		this.address = contact.address;
	}

	/**
	 * Gets the contact's phone number.
	 * 
	 * @return A string representing the employee’s phone number.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Gets the contact's first name.
	 * 
	 * @return A string representing the employee’s first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the contact's last name.
	 * 
	 * @return A string representing the employee’s last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the contact's email.
	 * 
	 * @return A string representing the employee’s email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the contact's address.
	 * 
	 * @return A string representing the employee’s address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the contact’s phone number.
	 * 
	 * @param phoneNumber A String containing the contact’s phone number.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Sets the contact’s first name.
	 * 
	 * @param firstName A String containing the contact’s first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the contact’s last name.
	 * 
	 * @param lastName A String containing the contact’s last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the contact’s email.
	 * 
	 * @param email A String containing the contact’s email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the contact’s address.
	 * 
	 * @param address A String containing the contact’s address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the string representation of a contact in the phonebook.
	 * 
	 * @return A string containing all the details about a contact.
	 */
	@Override
	public String toString() {
		return "Contact [phoneNumber=" + phoneNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", address=" + address + "]";
	}

}
