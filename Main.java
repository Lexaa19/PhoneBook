package com.caliopi.mapphonebookchallenge;

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
					"1. Add or edit a contact.\n2. View all contacts.\n3. Find a contact by phone number\n4. Find contacts by name.\n0. Exit");
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
				System.out.println("Enter the phone number: ");
				number = scanner.nextLine();
				
				while (number.isEmpty()) {
					System.out.println("Phone number cannot be empty.\nEnter the phone number: ");
					number = scanner.nextLine();
				}
				
				while(!phoneBook.isValidMobileNo(number)) {
					System.out.println("Number is not ok. Please reenter the phone number");
					number = scanner.nextLine();
				}
				
				
				// Edit existing contact
				if (phoneBook.getContactMap().containsKey(number)) {
					System.out.println("This phone number already exists. Editing an existing entry");
					System.out.println("Enter the first name: ");
					firstName = scanner.nextLine();

					while (firstName.isEmpty()) {
						System.out.println("First name cannot be empty.\nEnter the first name: ");
						firstName = scanner.nextLine();
					}

					System.out.println("Enter the last name: ");
					lastName = scanner.nextLine();

					while (lastName.isEmpty()) {
						System.out.println("Last name cannot be empty.\nEnter the first name: ");
						lastName = scanner.nextLine();
					}

					System.out.println("Enter the email: ");
					email = scanner.next();

					System.out.println("Enter the address: ");
					// accept user input with whitespaces
					address = scanner.nextLine();
					address += scanner.nextLine();

					Contact contact = new Contact(number, firstName, lastName, email, address);
					phoneBook.updateContact(contact);
					System.out.println(phoneBook.getContactMap().toString());
				} else {
					System.out.println("This phone number is new. Adding a new entry to the phone book");
					System.out.println("Enter the first name: ");
					firstName = scanner.nextLine();

					while (firstName.isEmpty()) {
						System.out.println("First name cannot be empty.\nEnter the first name: ");
						firstName = scanner.nextLine();
					}

					System.out.println("Enter the last name: ");
					lastName = scanner.nextLine();

					while (lastName.isEmpty()) {
						System.out.println("Last name cannot be empty.\nEnter the first name: ");
						lastName = scanner.nextLine();
					}

					System.out.println("Enter the email: ");
					email = scanner.nextLine();

					System.out.println("Enter the address: ");
					address = scanner.next();
					address += scanner.nextLine();

					Contact contact = new Contact(number, firstName, lastName, email, address);
					if (contact.getPhoneNumber() != null) {
						phoneBook.add(contact);
					} else {
						System.out.println("Nulls not allowed ");
					}

					System.out.println(phoneBook.getContactMap().toString());
				}
				break;
			case 2:
				break;

			case 3:
				System.out.println("Find a contact by phone number : ");
				number = scanner.next();
				break;
			case 4:
				System.out.println("Find a contact by name : ");
				number = scanner.next();
				break;

			}
		} while (userOption != 5);

	}

}
