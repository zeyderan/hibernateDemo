package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		
//		hibernate ile veritabanýna session gönderilir.
//		session içerisinde sorgularý barýndýrýr.
//		session transaction iþlemleri için gereklidir
//		bu nedenle önce session factory ile session oluþturacak fatory elde edilir
		SessionFactory factory = new Configuration()
//				factory hangi ayarlarý kullaacak 
				.configure("hibernate.cfg.xml")
//				hangi pojo kulanýlacak
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		
//		session nesnesini alýnýr
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
//			burada sorgu gönderilip sonuç list içerisine alýnýr
			List<City> cities = session.createQuery("from City").getResultList();
			
//			list elemanlarýný ekrana yazdýrýr
			for(City city:cities) {
//				tablodaki name sütunu içerisindekiler okur
				System.out.println(city.getName());
			}
			session.getTransaction().commit();
		}finally {
//			session factroy i kapatýr
			factory.close();
		}
	}

}
