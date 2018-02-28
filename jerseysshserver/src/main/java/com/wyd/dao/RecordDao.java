package com.wyd.dao;

import java.util.List;

import com.wyd.domain.Record;

public interface RecordDao extends GenericDao<Record>{
	
	Record getByRecordId(String r_id, int e_id);
	List<Record> findAll();
	String save(Record entity);
}
