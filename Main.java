import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String firstName = null;
		String lastName = null;
		String number = null;
		String address = null;
		String email = null;
		int userOption = 0;

		final int OPTION_ADD_EDIT = 1;
		final int OPTION_VIEW_CONTACTS = 2;
		final int OPTION_FIND_NUMBER = 3;
		final int OPTION_FIND_NAME = 4;
		final int OPTION_EXIT = 5;

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
				// String[] a = scanner.nextLine().split(" ");

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

	// method used in case 4 to search by first name or last name
	public static void searchContactByName(PhoneBook phonebook, String name) {
		Scanner scanner = new Scanner(System.in);

		Iterator<Map.Entry<String, Contact>> contactMapIterator = phonebook.getContactMap().entrySet().iterator();
		while (contactMapIterator.hasNext()) {
			Entry<String, Contact> contact = contactMapIterator.next();
			if (name.equals(contact.getValue().getFirstName()) || name.equals(contact.getValue().getLastName())) {
				System.out.println("Phone number: " + contact.getValue().getPhoneNumber());
				System.out.println("First name: " + contact.getValue().getFirstName());
				System.out.println("Last name: " + contact.getValue().getLastName());
				System.out.println("Email: " + contact.getValue().getEmail());
				System.out.println("Address: " + contact.getValue().getAddress());
			} else if (name.equals(contact.getValue().getLastName())) {
				System.out.println("Phone number: " + contact.getValue().getPhoneNumber());
				System.out.println("First name: " + contact.getValue().getFirstName());
				System.out.println("Last name: " + contact.getValue().getLastName());
				System.out.println("Email: " + contact.getValue().getEmail());
				System.out.println("Address: " + contact.getValue().getAddress());
			}
		}
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

	// SWITCH CASES SPLIT INTO METHODS
	public static void caseAddEdit(String phoneNumber, String firstName, String lastName, String email, String address,
			PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		phoneNumber = readPhoneNumber(phoneBook);

		// Edit existing contact
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
			Contact contactFinalContact = new Contact(contact);
			if (contact.getPhoneNumber() != null) {
				phoneBook.add(contactFinalContact);
			} else {
				System.out.println("Nulls not allowed ");
			}
		}
	}

	public static void caseViewContacts(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nContact list\n");
		getContactMapEntries(phoneBook);
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

	public static void caseFindNumber(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Search by phone number: ");
		String phoneNumber = scanner.nextLine();
		while (phoneNumber.isEmpty()) {
			System.out.print("The phone number cannot be empty: ");
			phoneNumber = scanner.nextLine();

		}

		if (!phoneNumber.isEmpty()) {
			searchContactByPhoneNumber(phoneBook, phoneNumber);
		}
	}

	// METHODS FOR READING USER INPUT
	public static String readPhoneNumber(PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the phone number: ");
		String number = scanner.nextLine();
		while (number.isEmpty()) {
			System.out.print("Phone number cannot be empty.\nEnter the phone number: ");
			number = scanner.nextLine();
		}

		while (!phoneBook.isValidMobileNo(number)) {
			System.out.print("Phone number is not in the correct format. Please reenter the phone number: ");
			number = scanner.nextLine();
		}
		return number;
	}

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

	public static String readEmail() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the email: ");
		String email = scanner.nextLine();
		return email;
	}

	public static String readAddress() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the address: ");
		// accept user input with white spaces
		String address = scanner.nextLine();
		return address;
	}

	// method used in case 2 to view all the contacts
	public static void getContactMapEntries(PhoneBook phonebook) {
		for (Map.Entry<String, Contact> contact : phonebook.getContactMap().entrySet()) {
			Contact contactMapValue = contact.getValue();
			// %s is a 'format character', indicating "insert a string here"
			System.out.println(String.format("%s %s %s %s ", contactMapValue.getPhoneNumber(),
					contactMapValue.getFirstName(), contactMapValue.getLastName(), contactMapValue.getEmail()));
		}
	}

	// method used in case 3 to search by phone number
	public static void searchContactByPhoneNumber(PhoneBook phonebook, String phoneNumber) {
		Scanner scanner = new Scanner(System.in);
		/*
		 * Map.Entry interface in Java provides certain methods to access the entry in
		 * the Map. Map.Entry interface in Java provides certain methods to access the
		 * entry in the Map. It should be used if you need both map keys and values in
		 * the loop
		 * 
		 * entrySet() method in Java is used to create a set out of the same elements
		 * contained in the hash map. It basically returns a set view of the hash map or
		 * we can create a new set and store the map elements into them.
		 * 
		 * entrySet() is doing a lookup only once, so it is faster
		 */
		Iterator<Map.Entry<String, Contact>> contactMapIterator = phonebook.getContactMap().entrySet().iterator();
		while (contactMapIterator.hasNext()) {
			Entry<String, Contact> contact = contactMapIterator.next();
			if (phoneNumber.equals(contact.getKey())) {
				System.out.println("Phone number: " + contact.getValue().getPhoneNumber());
				System.out.println("First name: " + contact.getValue().getFirstName());
				System.out.println("Last name: " + contact.getValue().getLastName());
				System.out.println("Email: " + contact.getValue().getEmail());
				System.out.println("Address: " + contact.getValue().getAddress());
			}

		}
		System.out.println("\nPress ENTER to continue.");
		scanner.nextLine();
	}

}
