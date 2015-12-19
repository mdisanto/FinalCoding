package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;

public class RateDAL {

	public static ArrayList<RateDomainModel> getRates() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel RateGet = null;		
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List students = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				RateDomainModel rate = (RateDomainModel) iterator.next();
				rates.add(rate);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rates;
	}		

	public static double getRate(int GivenCreditScore) {
		double InterestRate = 0;
		ArrayList<RateDomainModel> Rates = getRates();
		int Remainder = GivenCreditScore % 50;
		int CreditRange = GivenCreditScore - Remainder;
		
		if(CreditRange > 800){
			CreditRange=800;
		}			
		
		for(RateDomainModel i: Rates){
			if(GivenCreditScore==i.getMinCreditScore()){
				InterestRate = i.getInterestRate();
			}
		}
		return InterestRate;	
	}

}