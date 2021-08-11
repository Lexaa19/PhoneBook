package com.caliopi.mapphonebookchallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public boolean isValidMobileNo(String str) {
		// check if number starts with 0
		Pattern p = Pattern.compile("^0[0-9-( )]+$");

		// Pattern class contains matcher() method
		// to find matching between given string
		// and regular expression
		// remove the white spaces and dashes from the string so that they are not
		// counted when computing length
		str = str.replaceAll("[()\\s-]+", "");
		Matcher m = p.matcher(str);

		if ((m.find() && m.group().equals(str)) && str.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

}
