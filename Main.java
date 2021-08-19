import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		final int OPTION_ADD_EDIT = 1;
		final int OPTION_VIEW_CONTACTS = 2;
		final int OPTION_FIND_NUMBER = 3;
		final int OPTION_FIND_NAME = 4;
		final int OPTION_EXIT = 5;

		String firstName = null;
		String lastName = null;
		String number = null;
		String address = null;
		String email = null;
		int userOption = 0;

		PhoneBook phoneBook = new PhoneBook();

		do {
			System.out.println("\nPhone Book");
			System.out.println("There is/(are) currently " + phoneBook.getContactMap().size()
					+ " contact(s) in the phone book.\n");
			System.out.println(
					"1. Add or edit a contact.\n2. View all contacts.\n3. Find a contact by phone number\n4. Find contacts by name.\n5. Exit");
			try {
				System.out.print("Select an option: ");
				userOption = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Wrong output. Exiting the program");
				break;
			}
			switch (userOption) {

			case OPTION_ADD_EDIT:
				caseAddEdit(number, firstName, lastName, email, address, phoneBook);
				break;

			case OPTION_VIEW_CONTACTS:
				caseViewContacts(phoneBook);
				break;

			case OPTION_FIND_NUMBER:
				caseFindNumber(phoneBook);
				break;

			case OPTION_FIND_NAME:
				System.out.println("Find a contact by name: ");
				firstName = scanner.nextLine();
				searchContactByName(phoneBook, firstName);
				break;

			case OPTION_EXIT:
				userOption = scanner.nextInt();
				if (userOption == 0) {
					System.exit(0);
				} else {
					break;
				}
			}
		} while (userOption != 5);

	}

	// SWITCH CASES SPLIT INTO METHODS and their auxiliary methods
	/**
	 * Used in switch menu (case 1) to add a new contact or edit an existing one.
	 * 
	 * @param phoneNumber User's phone number.
	 * @param firstName   User's first name.
	 * @param lastName    User's last name.
	 * @param email       User's email.
	 * @param address     User's address.
	 * @param phoneBook   the Phone Book containing all the contacts.
	 */
	public static void caseAddEdit(String phoneNumber, String firstName, String lastName, String email, String address,
			PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		phoneNumber = readPhoneNumber(phoneBook);

		// edit an existing contact
		if (phoneBook.getContactMap().containsKey(phoneNumber)) {
			System.out.println("This phone number already exists. Editing an existing entry");

			firstName = readFirstName();
			lastName = readLastName();
			email = readEmail();
			address = readAddress();

			Contact contact = new Contact(phoneNumber, firstName, lastName, email, address);
			Contact contactFinalContact = new Contact(contact);
			phoneBook.updateContact(contactFinalContact);

			System.out.println("\nPhone book was updated successfully.\nPress ENTER to continue.");
			scanner.nextLine();

		} else {
			System.out.println("This phone number is new. Adding a new entry to the phone book");

			firstName = readFirstName();
			lastName = readLastName();
			email = readEmail();
			address = readAddress();

			Contact contact = new Contact(phoneNumber, firstName, lastName, email, address);
			//using the copy constructor
			Contact contactFinalContact = new Contact(contact);

			if (contact.getPhoneNumber() != null) {
				phoneBook.add(contactFinalContact);
			} else {
				System.out.println("Nulls not allowed ");
			}
		}
	}

	/**
	 * Used in the switch menu (case 2) to view the list with all the contacts.
	 * 
	 * @param phoneBook The phonebook containing all the contacts.
	 */
	public static void caseViewContacts(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nContact list\n");
		getContactMapEntries(phoneBook);
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

	/**
	 * Auxiliary method used in caseViewContacts(PhoneBook phoneBook) to list all
	 * the contacts.
	 *
	 * @param phoneBook The phonebook containing all the contacts.
	 */
	public static void getContactMapEntries(PhoneBook phoneBook) {
		for (Map.Entry<String, Contact> contact : phoneBook.getContactMap().entrySet()) {
			Contact contactMapValue = contact.getValue();
			// %s is a 'format character', indicating "insert a string here"
			System.out.println(String.format("%s %s %s %s ", contactMapValue.getPhoneNumber(),
					contactMapValue.getFirstName(), contactMapValue.getLastName(), contactMapValue.getEmail()));
		}
	}

	/**
	 * Used in switch menu (case 3) to find a contact by phone number.
	 * 
	 * @param phoneBook The phonebook containing all the contacts.
	 */
	public static void caseFindNumber(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Search by phone number: ");
		String phoneNumber = scanner.nextLine();

		// normalizing user input in order to check with the phonebook existing entries
		String normalizedPhoneNumber= phoneBook.isValidMobileNo(phoneNumber);

		while (phoneNumber.isEmpty()) {
			System.out.print("The phone number cannot be empty: ");
			normalizedPhoneNumber = scanner.nextLine();
		}

		if (!phoneNumber.isEmpty()) {
			// when calling this method, use the normalized form of the phone number
			searchContactByPhoneNumber(phoneBook, normalizedPhoneNumber);
		}
	}

	/*
	 * Auxiliary method used in case 3 to search by phone number.
	 * 
	 * Map.Entry interface in Java provides certain methods to access the entry in
	 * the Map. It should be used if you need both map keys and values in the loop.
	 * 
	 * entrySet() method in Java is used to create a set out of the same elements
	 * contained in the hash map. It basically returns a set view of the hash map or
	 * we can create a new set and store the map elements into them.
	 * 
	 * entrySet() is doing a lookup only once, so it is faster.
	 * 
	 * The value found is used for outputting the right message if the phone number
	 * was not found. Initially set to false, when the number is found its value is
	 * set to true.
	 * 
	 * @param phoneBook The phonebook containing all the contacts.
	 * 
	 * @param phoneNumber The phone number from the user input to be searched for.
	 *
	 */

	public static void searchContactByPhoneNumber(PhoneBook phoneBook, String phoneNumber) {
		Scanner scanner = new Scanner(System.in);
		boolean found = false;
		Iterator<Map.Entry<String, Contact>> contactMapIterator = phoneBook.getContactMap().entrySet().iterator();
		while (contactMapIterator.hasNext()) {
			Entry<String, Contact> contact = contactMapIterator.next();
			if (phoneNumber.equals(contact.getKey())) {
				outputSearchByPhone(phoneBook, contact);
				found = true;
			}
		}
		if (!found) {
			System.out.println("The phone number could not be found in the address book.");
		}
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

	/**
	 * Used in searchContactByPhoneNumber(PhoneBook phonebook, String phoneNumber)
	 * to display the contact personal information if the search by the phone number
	 * was successful.
	 * 
	 * @param phoneBook    The phonebook containing all the contacts.
	 * @param contactEntry Contains the next element in the contactMapIterator.
	 */
	public static void outputSearchByPhone(PhoneBook phoneBook, Entry<String, Contact> contactEntry) {
		System.out.println("Phone number: " + contactEntry.getValue().getPhoneNumber());
		System.out.println("First name: " + contactEntry.getValue().getFirstName());
		System.out.println("Last name: " + contactEntry.getValue().getLastName());
		System.out.println("Email: " + contactEntry.getValue().getEmail());
		System.out.println("Address: " + contactEntry.getValue().getAddress());
	}

	// METHODS FOR READING USER INPUT
	/**
	 * Used in the switch case 1 to read the phone number the user enters and to
	 * check if it is valid. The method also normalizes the phone number so that it
	 * is added in a standard form in the phone book.
	 *
	 * @param Phonebook The phonebook containing all the contacts.
	 * @return the phone number's normalized form.
	 */
	public static String readPhoneNumber(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the phone number: ");
		String number = scanner.nextLine();
		// normalizing user input in order to check with the phonebook existing entries
		String normalizedPhoneNumber = phoneBook.isValidMobileNo(number);
		// if the user doesn't add any phone number
		while (number.isEmpty()) {
			System.out.print("Phone number cannot be empty.\nEnter the phone number: ");
			normalizedPhoneNumber = scanner.nextLine();
		}
		// check if the phone number is valid
		while (phoneBook.isValidMobileNo(normalizedPhoneNumber) == null) {
			System.out.print("Phone number is not in the correct format. Please reenter the phone number: ");
			normalizedPhoneNumber = scanner.nextLine();
		}
		return normalizedPhoneNumber;
	}

	/**
	 * Used in the switch case 1 to read the first name the user enters and to check
	 * if it is not empty.
	 *
	 * @return the user's first name.
	 */
	public static String readFirstName() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the first name: ");
		String firstName = scanner.nextLine();
		while (firstName.isEmpty()) {
			System.out.print("First name cannot be empty.\nEnter the first name: ");
			firstName = scanner.nextLine();
		}
		return firstName;
	}

	/**
	 * Used in the switch case 1 to read the last name the user enters and to check
	 * if it is not empty.
	 *
	 * @return the user's last name.
	 */
	public static String readLastName() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the last name: ");
		String lastName = scanner.nextLine();

		while (lastName.isEmpty()) {
			System.out.print("Last name cannot be empty.\nEnter the first name: ");
			lastName = scanner.nextLine();
		}
		return lastName;
	}

	/**
	 * Used in the switch case 1 to read the email the user enters.
	 *
	 * @return the user's email.
	 */
	public static String readEmail() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the email: ");
		String email = scanner.nextLine();
		return email;
	}

	/**
	 * Used in the switch case 1 to read the address the user enters.
	 *
	 * @return the user's address.
	 */
	public static String readAddress() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the address: ");
		// accept user input with white spaces
		String address = scanner.nextLine();
		return address;
	}

	/**
	 * Method used in case 4 to search by first name or last name.
	 * 
	 * -----Theory Introduction-----
	 * 
	 * Since the map doesn’t extend the Collection interface, it doesn’t have its
	 * own iterator. But Map.entrySet() returns a set of key-value mappings.
	 * 
	 * Map.Entry interface in Java provides certain methods to access the entry in
	 * the Map (for key-value pair).
	 * 
	 * entrySet() method in Java is used to create a set out of the same elements
	 * contained in the hash map. It basically returns a set view of the hash map or
	 * we can create a new set and store the map elements into them.
	 * 
	 * entrySet() is faster
	 * 
	 * -----Method explanation-----
	 * 
	 * The method searches for the name entered by the user in the following
	 * scenarios: 1. The user enters the first name or the last name or
	 * both 2. The user enters the first name or the last name partially ("Jo"
	 * instead of "John").
	 * 
	 * In order to check for the first name or last name we have to split the user
	 * input (using the isSpaceInContactName(String name) method) taking into
	 * account the white spaces. We split the String by space and access the first
	 * name as the element at index 0 and the last name as the element at index 1.
	 * 
	 * 
	 * @param phonebook The Phone book containing all the contacts.
	 * @param name      The name (first name / last name or both) to search for.
	 */
	public static void searchContactByName(PhoneBook phoneBook, String name) {
		Scanner scanner = new Scanner(System.in);
		boolean foundContact = false;

		// phonebook.getContactMap() returns all the contacts in the phone book
		Iterator<Map.Entry<String, Contact>> contactMapIterator = phoneBook.getContactMap().entrySet().iterator();
		while (contactMapIterator.hasNext()) {
			Entry<String, Contact> contact = contactMapIterator.next();
			String contactFirstNameToString = contact.getValue().getFirstName();
			String contactLastNameToString = contact.getValue().getLastName();

			// search by either first name or last name
			if (contactFirstNameToString.contains(name) || contactLastNameToString.contains(name)) {
				// output contact details when found in the phone book
				outputSearchByName(phoneBook, contact);
				foundContact = true;
			}
			if (isSpaceInContactName(name)) {
				// split the user input name in first name and last name to perform the search
				String[] entries = name.split(" ");
				// check for both first name and last name in the phone book
				if (contactFirstNameToString.contains(entries[0]) && contactLastNameToString.contains(entries[1])) {
					foundContact = true;
					// output contact details when found in the phone book
					outputSearchByName(phoneBook, contact);
				}
			}
		}
		// if the first name / last name / both is / are not found, return this message
		if (!foundContact) {
			System.out.println("No results.");
		}
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

	/**
	 * Auxiliary method used in searchContactByName(PhoneBook phonebook, String
	 * name) to detect the space in the name and to split it in first name and last
	 * name.
	 *
	 * @param name the user's input name.
	 */
	public static boolean isSpaceInContactName(String name) {
		// split the String when white space is found
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(name);
		boolean found = matcher.find();
		return found;
	}

	/**
	 * Auxiliary method used in searchContactByName(PhoneBook phonebook, String
	 * name) to list all the found contacts in the required format.
	 *
	 * @param phoneBook    The phonebook containing all the contacts.
	 * @param contactEntry Contains the next element in the contactMapIterator.
	 */
	public static void outputSearchByName(PhoneBook phoneBook, Entry<String, Contact> contactEntry) {
		System.out.println(contactEntry.getValue().getPhoneNumber() + " " + contactEntry.getValue().getFirstName() + " "
				+ contactEntry.getValue().getLastName() + " " + contactEntry.getValue().getEmail());
	}

}
