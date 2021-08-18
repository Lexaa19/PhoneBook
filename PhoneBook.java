import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represents the PhoneBook containing all the contacts.
 * 
 * It also holds the logic for the contact operations(add, update etc)
 * 
 * It doesn't hold any messages that are displayed to the user. That is done in
 * the Main class
 * 
 * @author Caliopi Durdunescu
 * @since 17.08.2021
 */
public class PhoneBook {

	private Map<String, Contact> contactMap = new HashMap<>();

	/**
	 * Uses the Contact class copy constructor to add contacts to the contactMap
	 * 
	 * @param entry an object of type Contact
	 */
	public void add(Contact entry) {
		this.contactMap.put(entry.getPhoneNumber(), entry);
	}

	/**
	 * Gets a copy (using the "new" keyword") of the contactMap containing all the
	 * contacts
	 */
	public Map<String, Contact> getContactMap() {
		return new HashMap<String, Contact>(contactMap);
	}

	/**
	 * Updates an entry in the contactMap
	 * 
	 * @param entry an object of type Contact
	 */
	public void updateContact(Contact entry) {
		this.contactMap.replace(entry.getPhoneNumber(), entry);
	}

	/**
	 * Checks if the phone number is a valid one with the help of regex
	 * 
	 * @param str the string to be checked by the regex
	 * @return true if the string matches the regex pattern
	 */
	public boolean isValidMobileNo(String str) {
		/*
		 * Pattern class contains matcher() method to find matching between given string
		 * and regular expression
		 * 
		 * ^ defines that the pattern must start at the beginning of a new line
		 * 
		 * by using ^ and $ you tell the engine that whatever is in between them must
		 * cover the entire line end-to-end
		 */

		try {
			Pattern p = Pattern.compile("^0[0-9-( )]+$");

			/*
			 * remove the white spaces and dashes from the string so that they are not
			 * counted when computing length.
			 * 
			 * + => One or more
			 * 
			 * \\s => empty space
			 */
			str = str.replaceAll("[()\\s-]+", "");

			/*
			 * returns a Matcher object which contains information about the search that was
			 * performed
			 */

			Matcher m = p.matcher(str);
			/*
			 * groups are a way to treat multiple characters as a single unit.
			 * 
			 * The find() method of Matcher Class attempts to find the next subsequence of
			 * the input sequence that find the pattern. It returns a boolean value
			 */
			if ((m.find() && m.group().equals(str)) && str.length() == 10) {
				return true;
			} else {
				return false;
			}
		} catch (PatternSyntaxException ex) {
			System.out.println("The phone number string format is not correct: " + ex.getPattern());
			System.out.println(ex.getMessage());
		}
		return true;
	}

}
