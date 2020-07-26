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
//			city deðil City yazýlmalý class ismi
//			from City c where c.countryCode='TUR' AND c.district='Istanbul bölgesi istanbul olan türkiyedeli þehirleri getir
			List<String> countryCodes = session.createQuery("select c.countryCode from City c GROUP BY c.countryCode").getResultList();
			
//			list elemanlarýný ekrana yazdýrýr
			for(String countryCode:countryCodes) {
//				tablodaki name sütunu içerisindekiler okur
				System.out.println(countryCode);
			}
			session.getTransaction().commit();
		}finally {
//			session factroy i kapatýr
			factory.close();
		}
	}

}
