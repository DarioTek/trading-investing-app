package com.dariotek.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.dariotek.entity.HistoricalStockPrice;

@Component
public class HistoricalStockPricessDAOImpl extends DAOAbstractClass implements HistoricalStockPricesDAO {

	@Override
	public void addHistoricalStockPrices(HistoricalStockPrice historicalStockPrices) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.save(historicalStockPrices);
		session.getTransaction().commit();
	}

	@Override
	public void updateHistoricalStockPrices(HistoricalStockPrice historicalStockPrices) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(historicalStockPrices);
		session.getTransaction().commit();
	}

	@Override
	public void saveOrUpdateHistoricalStockPrices(HistoricalStockPrice historicalStockPrices) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(historicalStockPrices);
		session.getTransaction().commit();
	}

	@Override
	public void deleteHistoricalStockPricess(String symbol, Date date) {
		Session session = getCurrentSession();
		session.beginTransaction();

		HistoricalStockPrice.Key key = new HistoricalStockPrice.Key();
		key.setSymbol(symbol);
		key.setDate(date);
		
		HistoricalStockPrice historicalStockPrice = session.get(HistoricalStockPrice.class, key);

		session.delete(historicalStockPrice);
		
		session.getTransaction().commit();
		session.flush();
	}

	@Override
	public HistoricalStockPrice getHistoricalStockPrice(String symbol, Date date) {
		Session session = getCurrentSession();
		session.beginTransaction();
		
		HistoricalStockPrice.Key key = new HistoricalStockPrice.Key();
		key.setSymbol(symbol);
		key.setDate(date);
		
		HistoricalStockPrice historicalStockPrice = session.get(HistoricalStockPrice.class, key);
		
		session.getTransaction().commit();

		return historicalStockPrice;
	}

	@Override
	public List<HistoricalStockPrice> getHistoricalStockPrices(String symbol, Date startDate, Date endDate) {
		Session session = getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(HistoricalStockPrice.class);
		criteria.add(Restrictions.eq("key.symbol", symbol));
		criteria.add(Restrictions.ge("key.date", startDate));
		criteria.add(Restrictions.le("key.date", endDate));
		
		List<HistoricalStockPrice> result = criteria.list();
		session.getTransaction().commit();

		return result;
	}
	
	@Override
	public List<HistoricalStockPrice> getAllHistoricalStockPrices(String symbol) {
		Session session = getCurrentSession();
		session.beginTransaction();		
		List<HistoricalStockPrice> historicalStockPrices = session.createQuery("from HistoricalStockPrice where symbol = '" +  symbol + "' order by txn_date desc").getResultList();
		session.getTransaction().commit();
		
		return historicalStockPrices;
	}

	@Override
	public List<String> getListOfStockSymbols() {
		Session session = getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(HistoricalStockPrice.class);
		criteria.setProjection(Projections.distinct(Projections.property("key.symbol")));
		
		List<String> result = criteria.list();
		session.getTransaction().commit();

		return result;
	}

}
