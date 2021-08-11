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
	    // 1) Begins with 0 or 07
	    // 2) The rest 7 digits can contain any number between 0 to 9
	    Pattern p = Pattern.compile("(0|07)?[7][0-9]{8}");
	 
	    // Pattern class contains matcher() method
	    // to find matching between given number
	    // and regular expression
	    Matcher m = p.matcher(str);
	    return (m.find() && m.group().equals(str));
	}

}
