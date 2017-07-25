package services;

import models.PhoneBuzzRound;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.util.List;
import java.util.Collections;

import java.lang.NullPointerException;

public class PhoneBuzzRoundService {

    private SessionFactory sessionFactory;

    public void save(PhoneBuzzRound round) {
      this.sessionFactory.getCurrentSession().save(round)
    // Session session = sessionFactory.getCurrentSession();
  //   session.save(round);
  //   session.flush();
  //   session.close();
      
    // System.out.println(round.getPhone());
    // System.out.println(round.getDelayedSeconds());
    // System.out.println(round.getInput());;
    }

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