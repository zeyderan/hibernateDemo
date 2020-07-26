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
			List<City> cities = session.createQuery("from City c ORDER BY c.name ASC").getResultList();
			
//			list elemanlar�n� ekrana yazd�r�r
			for(City city:cities) {
//				tablodaki name s�tunu i�erisindekiler okur
				System.out.println(city.getName());
			}
			session.getTransaction().commit();
		}finally {
//			session factroy i kapat�r
			factory.close();
		}
	}

}
