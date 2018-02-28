package com.wyd.service;

import java.util.List;

import com.wyd.domain.Record;

public interface RecordService {
	List<Record> findAll();
	
	String save(Record record);
	
	Record getByRecordId(String r_id,String e_id);
}
