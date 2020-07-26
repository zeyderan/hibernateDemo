package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		
//		hibernate ile veritaban�na session g�nderilir.
//		session i�erisinde sorgular� bar�nd�r�r.
//		session transaction i�lemleri i�in gereklidir
//		bu nedenle �nce session factory ile session olu�turacak fatory elde edilir
		SessionFactory factory = new Configuration()
//				factory hangi ayarlar� kullaacak 
				.configure("hibernate.cfg.xml")
//				hangi pojo kulan�lacak
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		
//		session nesnesini al�n�r
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
//			burada sorgu g�nderilip sonu� list i�erisine al�n�r
//			city de�il City yaz�lmal� class ismi
//			from City c where c.countryCode='TUR' AND c.district='Istanbul b�lgesi istanbul olan t�rkiyedeli �ehirleri getir
			/*
			 * List<String> countryCodes =
			 * session.createQuery("select c.countryCode from City c GROUP BY c.countryCode"
			 * ).getResultList();
			 * 
			 * // list elemanlar�n� ekrana yazd�r�r for(String countryCode:countryCodes) {
			 * // tablodaki name s�tunu i�erisindekiler okur
			 * System.out.println(countryCode); }
			 */
			
			//insert
//			City city = new City();
//			city.setName("D�zce 18");
//			city.setCountryCode("TUR");
//			city.setDistrict("Istanbul");
//			city.setPopulation(10000);
//			session.save(city);
			
			//update
//			City city = session.get(City.class,4080);
//			
//			city.setPopulation(20000);
//			
//			session.save(city);
//			
//			session.getTransaction().commit();
			
			//delete
			
			City city = session.get(City.class, 4080);
			session.delete(city);
			//save e burada gerek yoktur.

		}finally {
//			session factroy i kapat�r
			factory.close();
		}
	}

}
