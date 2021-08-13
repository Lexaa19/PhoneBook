import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String firstName, lastName, number, email, address;
		int userOption = 0;
		PhoneBook phoneBook = new PhoneBook();

		do {
			System.out.println("\nPhone Book");
			System.out.println(
					"There are currently " + phoneBook.getContactMap().size() + " contact(s) in the phone book.\n");
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

			case 1:
				System.out.print("Enter the phone number: ");
				number = scanner.nextLine();

				number = validatePhoneNumber(number, phoneBook);

				// Edit existing contact
				if (phoneBook.getContactMap().containsKey(number)) {
					System.out.println("This phone number already exists. Editing an existing entry");

					System.out.print("Enter the first name: ");
					firstName = scanner.nextLine();
					firstName = validateFirstName(firstName);

					System.out.print("Enter the last name: ");
					lastName = scanner.nextLine();
					validateLastName(lastName);

					System.out.print("Enter the email: ");
					email = scanner.next();

					System.out.print("Enter the address: ");
					// accept user input with white spaces
					address = scanner.nextLine();
					address += scanner.nextLine();

					Contact contact = new Contact(number, firstName, lastName, email, address);

					phoneBook.updateContact(contact);

					System.out.println("\nPhone book was updated successfully.\nPress ENTER to continue.");
					scanner.nextLine();

				} else {
					System.out.println("This phone number is new. Adding a new entry to the phone book");

					System.out.print("Enter the first name: ");
					firstName = scanner.nextLine();
					validateFirstName(firstName);

					System.out.print("Enter the last name: ");
					lastName = scanner.nextLine();
					lastName = validateLastName(lastName);

					System.out.print("Enter the email: ");
					email = scanner.next();

					System.out.print("Enter the address: ");
					// accept user input with white spaces
					address = scanner.nextLine();
					address += scanner.nextLine();

					Contact contact = new Contact(number, firstName, lastName, email, address);
					if (contact.getPhoneNumber() != null) {
						phoneBook.add(contact);
					} else {
						System.out.println("Nulls not allowed ");
					}
				}

				break;

			case 2:
				System.out.println("\nContact list\n");
				phoneBook.getContactMapEntries();
				System.out.println("\nPress ENTER to continue.");
				scanner.nextLine();
				break;

			case 3:
				System.out.print("Search by phone number: ");
				number = scanner.nextLine();
				while (number.isEmpty()) {
					System.out.print("The phone number cannot be empty ");
					number = scanner.nextLine();

				}

				if (!number.isEmpty()) {
					phoneBook.listContactByPhoneNumber(number);
				}
				break;

			case 4:
				System.out.println("Find a contact by name: ");
				number = scanner.next();
				break;
			case 5:
				userOption = scanner.nextInt();
				if (userOption == 0) {
					System.exit(0);
				} else {
					break;
				}

			}
		} while (userOption != 5);

	}

	public static String validatePhoneNumber(String number, PhoneBook phoneBook) {
		Scanner scanner = new Scanner(System.in);

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

	public static String validateFirstName(String firstName) {
		Scanner scanner = new Scanner(System.in);

		while (firstName.isEmpty()) {
			System.out.print("First name cannot be empty.\nEnter the first name: ");
			firstName = scanner.nextLine();
		}
		return firstName;
	}

	public static String validateLastName(String lastName) {
		Scanner scanner = new Scanner(System.in);

		while (lastName.isEmpty()) {
			System.out.print("Last name cannot be empty.\nEnter the first name: ");
			lastName = scanner.nextLine();
		}
		return lastName;
	}

}
