package com.caliopi.mapphonebookchallenge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String firstName, lastName, number;

		PhoneBook phoneBook = new PhoneBook();

		int ch = 0;
		do {
			System.out.println("Phone Book");
			System.out.println("There are currently " + phoneBook.getContactFullNameMap().size()
					+ " contacts in the phone book.\n");
			System.out.println(
					"1. Add or edit a contact.\n2. View all contacts.\n3. Find a contact by phone number\n4. Find contacts by name.\n0. Exit");
			try {
				System.out.print("Select an option: ");
				ch = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Wrong output. Exiting the program");
				break;
			}
			switch (ch) {
			case 1:
				System.out.println("Enter the first name: ");
				firstName = scanner.next();
				System.out.println("Enter the last name: ");
				lastName = scanner.next();
				System.out.println("Enter the phone number: ");
				number = scanner.next();
				Contact contact = new Contact(firstName, lastName, number);
				phoneBook.add(contact);
				System.out.println(phoneBook.getContactFullNameMap());
				System.out.println(phoneBook.getPhoneNumberMap());
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
		} while (ch != 5);

	}

}
