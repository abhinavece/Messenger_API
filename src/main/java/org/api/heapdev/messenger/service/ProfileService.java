package org.api.heapdev.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.heapdev.messenger.database.DatabaseClass;
import org.api.heapdev.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("abhinavece", new Profile(1L, "abhinavece", "Abhinav", "Singh"));
		profiles.put("sanchayspidy", new Profile(2L, "sanchayspidy", "Sanchay", "Kumar"));
		profiles.put("vivek720", new Profile(3L, "vivek720", "Vivek", "Singh"));
		profiles.put("anand.kaushal", new Profile(4L, "anand.kaushal", "Anand", "Kaushal"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String name) {
		return profiles.get(name);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile deleteProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
