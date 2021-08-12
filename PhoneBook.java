import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PhoneBook {
	// key: phone number
	private Map<String, Contact> contactMap = new HashMap<String, Contact>();

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

			// remove the white spaces and dashes from the string so that they are not
			// counted when computing length
			str = str.replaceAll("[()\\s-]+", "");

			// returns a Matcher object which contains information about the search that was
			// performed
			Matcher m = p.matcher(str);
			// groups are a way to treat multiple characters as a single unit
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

	public void getContactMapEntries(Contact entry) {
		for (Map.Entry<String, Contact> contact : contactMap.entrySet()) {
			Contact contactMapValue = contact.getValue();
			System.out.println(String.format("%s %s %s %s ", contactMapValue.getPhoneNumber(), contactMapValue.getFirstName(),
					contactMapValue.getLastName(), contactMapValue.getEmail()));
		}
	}

}
