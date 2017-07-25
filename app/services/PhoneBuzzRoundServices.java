package services;

import models.PhoneBuzzRound;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Collections;

import java.lang.NullPointerException;

public class PhoneBuzzRoundService {

    private SessionFactory sessionFactory;

    public void save(PhoneBuzzRound round) {this.sessionFactory.getCurrentSession().save(round);}

    public void delete(PhoneBuzzRound round) {this.sessionFactory.getCurrentSession().delete(round);}

    public void update(PhoneBuzzRound round) {this.sessionFactory.getCurrentSession().update(round);}

    public List<PhoneBuzzRound> findAll() {
    	try {
        return this.sessionFactory.getCurrentSession().createQuery("FROM PhoneBuzzRound").list();
      } catch (NullPointerException exception) {
      	return Collections.emptyList();
      }
    }

}