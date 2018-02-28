package com.wyd.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer e_id;
	private String name;
	private Set<Record> records = new HashSet<Record>();
	
	public Integer getE_id() {
		return e_id;
	}
	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Record> getRecords() {
		return records;
	}
	public void setRecords(Set<Record> records) {
		this.records = records;
	}
}
