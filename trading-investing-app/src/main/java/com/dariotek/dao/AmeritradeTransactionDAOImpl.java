package com.dariotek.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dariotek.entity.AmeritradeTransaction;

@Component
public class AmeritradeTransactionDAOImpl extends DAOAbstractClass implements AmeritradeTransactionDAO {


	
	public AmeritradeTransactionDAOImpl() {		
	}

	@Override
	public List<AmeritradeTransaction> getAmeritradeTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmeritradeTransaction getAmeritradeTransaction(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.dariotek.dao.AmeritradeTransactionDAO#addAmeritradeTransaction(com.dariotek.entity.AmeritradeTransaction)
	 * 
	 * @Transactional on this method will not require the sessio.getTransaction().commit()
	 */
	@Override	
	public void addAmeritradeTransaction(AmeritradeTransaction ameritradeTransaction) {		
		Session session = getCurrentSession();
		session.beginTransaction();
		session.save(ameritradeTransaction);
		session.getTransaction().commit();
	}

	@Override
	public void deleteAmeritradeTransaction(String id) {
		// TODO Auto-generated method stub
		
	}

}
