import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represents the PhoneBook containing all the contacts.
 * 
 * It also holds the logic for the contact operations (add, update etc.).
 * 
 * It doesn't hold any messages that are displayed to the user. That is done in
 * the Main class.
 * 
 * @author Caliopi Durdunescu
 * @version 1
 * @since 17.08.2021
 */
public class PhoneBook {

	private Map<String, Contact> contactMap = new HashMap<>();

	/**
	 * Uses the Contact class copy constructor to add contacts to the contactMap.
	 * 
	 * @param entry An object of type Contact.
	 */
	public void add(Contact entry) {
		this.contactMap.put(entry.getPhoneNumber(), entry);
	}

	/**
	 * Gets the contactMap containing all the contacts.
	 * 
	 * @return A copy of the contactMap.
	 */
	public Map<String, Contact> getContactMap() {
		return new HashMap<String, Contact>(contactMap);
	}

	/**
	 * Updates an entry in the contactMap.
	 * 
	 * @param entry An object of type Contact.
	 */
	public void updateContact(Contact entry) {
		this.contactMap.replace(entry.getPhoneNumber(), entry);
	}

	/**
	 * Checks if the phone number is valid with the help of Regex. It also returns
	 * the normalized form of the phone number.
	 * 
	 * 07 (70) 19 -6645 becomes 0770196645.This will help with the search by phone
	 * number.
	 * 
	 * @param phoneNumber The phone number from user input to be checked by the
	 *                    Regex.
	 * @return The phone number in a normalized form.
	 */
	public String isValidMobileNo(String phoneNumber) {
		/*
		 * Pattern class contains the matcher() method to find the matching between the
		 * given string and the regular expression.
		 * 
		 * ^ Defines that the pattern must start at the beginning of a new line.
		 * 
		 * By using ^ and $ you tell the engine that whatever is in between these signs
		 * must be covered.
		 */

		try {
			Pattern phonePattern = Pattern.compile("^0[0-9-( )]+$");

			/*
			 * Remove the white spaces and dashes from the string so that they are not
			 * counted when computing the phone number length.
			 * 
			 * + => One or more.
			 * 
			 * \\s => Empty space.
			 */
			phoneNumber = phoneNumber.replaceAll("[()\\s-]+", "");

			/*
			 * Returns a Matcher object which contains information about the search that was
			 * performed.
			 */

			Matcher phoneMatcher = phonePattern.matcher(phoneNumber);

			/*
			 * Groups are a way to treat multiple characters as a single unit.
			 * 
			 * The find() method of Matcher Class. It returns a boolean value.
			 */
			if ((phoneMatcher.find() && phoneMatcher.group().equals(phoneNumber)) && phoneNumber.length() == 10) {
				return phoneNumber;
			}
		} catch (PatternSyntaxException ex) {
			System.out.println("The phone number string format is not correct: " + ex.getPattern());
			System.out.println(ex.getMessage());
		}
		return phoneNumber;
	}

}
