package com.wyd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyd.dao.RecordDao;
import com.wyd.domain.Record;
import com.wyd.service.RecordService;
import com.wyd.util.DateUtil;

@Service("recordService")
public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordDao recordDao;
	private DateUtil dateUtil = new DateUtil();

	public List<Record> findAll() {
		return recordDao.findAll();
	}

	public String save(Record record) {
		return recordDao.save(record);
	}

	public Record getByRecordId(String r_id,String e_id) {
		return recordDao.getByRecordId(r_id, Integer.parseInt(e_id));
	}

}
