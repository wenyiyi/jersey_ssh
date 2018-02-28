package com.wyd.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wyd.dao.RecordDao;
import com.wyd.domain.Record;

@Repository("recordDao")
@Transactional 
public class RecordDaoImpl implements RecordDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Record load(int id) {
		return (Record) this.getCurrentSession().load(Record.class, id);
	}

	public Record get(int id) {
		return (Record) this.getCurrentSession().get(Record.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Record> findAll() {
		return (List<Record>)sessionFactory.openSession()
				.createQuery("SELECT r from Record r inner join r.employee e where r.employee.e_id = e.e_id")
				.setCacheable(true).list();
	}

	public void persist(Record entity) {
		this.getCurrentSession().persist(entity);
	}

	public String save(Record entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Record entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(int id) {
		this.getCurrentSession().delete(this.load(id));
	}

	public void flush() {
		this.getCurrentSession().flush();
	}
	
	public Record getByRecordId(String r_id, int e_id) {
		return (Record)this.getCurrentSession()
				.createQuery("SELECT r from Record r inner join r.employee e where r.r_id=? and r.employee.e_id=?")
				.setParameter(0, r_id)
				.setParameter(1, e_id)
				.setCacheable(true).list().get(0);
	}
	
}
