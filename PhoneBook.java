import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PhoneBook {
	// key: phone number
	private Map<String, Contact> contactMap = new HashMap<String, Contact>();

	// Getting a Set of Key-value pairs
	private Set<Entry<String, Contact>> entrySet = contactMap.entrySet();

	// Obtaining an iterator for the entry set
	private Iterator it = entrySet.iterator();

	public void add(Contact entry) {
		this.contactMap.put(entry.getPhoneNumber(), new Contact(entry.getPhoneNumber(), entry.getFirstName(),
				entry.getLastName(), entry.getEmail(), entry.getAddress()));
	}

	public Map<String, Contact> getContactMap() {
		return new HashMap<String, Contact>(contactMap);
	}

	public void setContactMap(Map<String, Contact> contactMap) {
		this.contactMap = contactMap;
	}

	public void updateContact(Contact entry) {
		this.contactMap.replace(entry.getPhoneNumber(), new Contact(entry.getPhoneNumber(), entry.getFirstName(),
				entry.getLastName(), entry.getEmail(), entry.getAddress()));
	}

	/**
	 * 
	 * @param str -> the string to be check by the regex
	 * @return true if the string matches the regex pattern and false otherwise
	 */
	public boolean isValidMobileNo(String str) {
		/*
		 * Pattern class contains matcher() method to find matching between given string
		 * and regular expression
		 * 
		 * ^ defines that the pattern must start at beginning of a new line
		 * 
		 * by using ^ and $ you tell the engine that whatever is in between them must
		 * cover the entire line end-to-end
		 */

		try {
			Pattern p = Pattern.compile("^0[0-9-( )]+$");

			/*
			 * remove the white spaces and dashes from the string so that they are not
			 * counted when computing length. + => One or more ; \\s => empty space
			 */
			str = str.replaceAll("[()\\s-]+", "");

			/*
			 * returns a Matcher object which contains information about the search that was
			 * performed
			 */

			Matcher m = p.matcher(str);
			/*
			 * groups are a way to treat multiple characters as a single unit The find()
			 * method of Matcher Class attempts to find the next subsequence of the input
			 * sequence that find the pattern. It returns a boolean value
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

	// method for listing the contacts in the phone book (case 2 in Main)
	public void getContactMapEntries() {
		/*
		 * Map.Entry interface in Java provides certain methods to access the entry in
		 * the Map. Map.Entry interface in Java provides certain methods to access the
		 * entry in the Map.
		 */
		for (Map.Entry<String, Contact> contact : contactMap.entrySet()) {
			Contact contactMapValue = contact.getValue();
			// %s is a 'format character', indicating "insert a string here"
			System.out.println(String.format("%s %s %s %s ", contactMapValue.getPhoneNumber(),
					contactMapValue.getFirstName(), contactMapValue.getLastName(), contactMapValue.getEmail()));
		}
	}

}
