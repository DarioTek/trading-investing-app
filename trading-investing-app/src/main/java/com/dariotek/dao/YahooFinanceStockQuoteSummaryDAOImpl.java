package com.dariotek.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

@Component
public class YahooFinanceStockQuoteSummaryDAOImpl extends DAOAbstractClass implements YahooFinanceStockQuoteSummaryDAO {

	@Override
	public void addYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		session.beginTransaction();
		session.save(yahooFinanceStockQuoteSummary);
		session.getTransaction().commit();
	}

	@Override
	public void updateYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(yahooFinanceStockQuoteSummary);
		session.getTransaction().commit();
	}

	@Override
	public void deleteYahooFinanceStockQuoteSummary(String symbol, Date date) {
		Session session = getCurrentSession();
		session.beginTransaction();
		YahooFinanceStockQuoteSummary.Key key = new YahooFinanceStockQuoteSummary.Key();
		key.setTickerSymbol(symbol);
		key.setDateTimeScraped(date);		
		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = session.get(YahooFinanceStockQuoteSummary.class, key);
		session.delete(yahooFinanceStockQuoteSummary);		
		session.getTransaction().commit();
		session.flush();
	}

	@Override
	public void saveOrUpdateYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary) {
		Session session = getCurrentSession();
		session.beginTransaction();
				
		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummaryTemp = session.get(YahooFinanceStockQuoteSummary.class, yahooFinanceStockQuoteSummary.getKey());		
		if (yahooFinanceStockQuoteSummaryTemp != null) {
			session.saveOrUpdate(yahooFinanceStockQuoteSummaryTemp);
		}else {
			session.saveOrUpdate(yahooFinanceStockQuoteSummary);
		}
		
		session.getTransaction().commit();
	}

	@Override
	public YahooFinanceStockQuoteSummary getYahooFinanceStockQuoteSummary(String symbol, Date date) {
		Session session = getCurrentSession();
		session.beginTransaction();
		
		YahooFinanceStockQuoteSummary.Key key = new YahooFinanceStockQuoteSummary.Key();
		key.setTickerSymbol(symbol);
		key.setDateTimeScraped(date);		
		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = session.get(YahooFinanceStockQuoteSummary.class, key);		
		session.getTransaction().commit();

		return yahooFinanceStockQuoteSummary;
}
	
	@Override
	public List<YahooFinanceStockQuoteSummary> getYahooFinanceStockQuoteSummary(String symbol, Date startDate, Date endDate) {
		Session session = getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(YahooFinanceStockQuoteSummary.class);
		criteria.add(Restrictions.eq("key.tickerSymbol", symbol));
		criteria.add(Restrictions.ge("key.dateTimeScraped", startDate));
		criteria.add(Restrictions.le("key.dateTimeScraped", endDate));
		
		List<YahooFinanceStockQuoteSummary> result = criteria.list();
		session.getTransaction().commit();

		return result;
	}

	@Override
	public List<YahooFinanceStockQuoteSummary> getYahooFinanceStockQuoteSummary(String symbol) {
		Session session = getCurrentSession();
		session.beginTransaction();		
		List<YahooFinanceStockQuoteSummary> yahooFinanceStockQuoteSummary = session.createQuery("from YahooFinanceStockQuoteSummary where tickerSymbol = '" +  symbol + "' order by dateTimeScraped desc").getResultList();
		session.getTransaction().commit();
		
		return yahooFinanceStockQuoteSummary;
	}

}
