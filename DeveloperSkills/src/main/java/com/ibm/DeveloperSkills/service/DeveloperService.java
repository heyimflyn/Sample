package com.ibm.DeveloperSkills.service;

import java.util.List;

import com.ibm.DeveloperSkills.domain.Developer;

public interface DeveloperService {

	public List<Developer> findAll();
	
	public Developer find(long devID);
	
	public List<Developer> findByName(String firstname, String lastname);
	
	public boolean addDeveloper(Developer dev);
	
	public void delDeveloper(long devID);
}
