package com.caliopi.mapphonebookchallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
	// key: last name, value: first name
	private Map<String, String> contactFullNameMap = new HashMap<String, String>();
	// phoneNumber must be unique
	private Map<String, String> phoneNumberMap = new HashMap<String, String>();

	public void add(Contact entry) {
		this.contactFullNameMap.put(entry.getLastName(), entry.getFirstName());
		this.phoneNumberMap.put(entry.getPhoneNumber(), entry.getLastName());
	}

	public Map<String, String> getPhoneNumberMap() {
		return new HashMap<String, String>(phoneNumberMap);
	}

	public void setPhoneNumberMap(Map<String, String> phoneNumberMap) {
		this.phoneNumberMap = phoneNumberMap;
	}

	public Map<String, String> getContactFullNameMap() {
		return new HashMap<String, String>(contactFullNameMap);
	}

	public void setContactFullNameMap(Map<String, String> contactFullNameMap) {
		this.contactFullNameMap = contactFullNameMap;
	}

}
