package com.fbianco.shortener;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbianco.shortener.hibernate.HibernateUtil;

import jakarta.annotation.PostConstruct;

@Service
public class ShortUrlService{
    
    @Autowired
    public ShortUrlRepository shortUrlRepository;

    @PostConstruct
    public void onInit(){
		HibernateUtil.getSessionFactory().getCurrentSession();
    }

	public ShortUrl createShortUrl(String url) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
            try {
            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setUrl(url);
            shortUrl.setInsertTime(new Date());
            session.persist(shortUrl);
            session.getTransaction().commit();
            return shortUrl;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
	}

	public String getLongUrl(String url) {
        try {
            int urlId = Integer.valueOf(Base62.decode(url).toString(10));
            ShortUrl shortUrl = shortUrlRepository.getReferenceById(urlId);
            return shortUrl.getUrl();
        } catch (Exception e) {
            throw e;
        }
	}

    public void deleteByUrl(String url) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
        try {
            int urlId = Integer.valueOf(Base62.decode(url).toString(10));
            shortUrlRepository.deleteById(urlId);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
        try {
            shortUrlRepository.deleteById(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
