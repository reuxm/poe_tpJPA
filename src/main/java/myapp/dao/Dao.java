package myapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import myapp.model.Person;

public class Dao {

	private EntityManagerFactory factory = null;

	public void init() {
		factory = Persistence.createEntityManagerFactory("myBase");
	}

	public void close() {
		if (factory != null) {
			factory.close();
		}
	}

	public Person findPerson(long id) {
		EntityManager em = null;
		try {
			em = factory.createEntityManager();
			em.getTransaction().begin();
			// utilisation de l’EntityManager
			Person p = em.find(Person.class, id);
			return p;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	// Cr ́eer un EM et ouvrir une transaction
	private EntityManager newEntityManager() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		return (em);
	}
	
	// Fermer un EM et d ́efaire la transaction si n ́ecessaire
	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				EntityTransaction t = em.getTransaction();
				if (t.isActive()) {
					try {
						t.rollback();
					} catch (PersistenceException e) {
					}
				}
				em.close();
			}
		}
	}
	
	// Nouvelle version simplifi ́ee
	public Person addPerson(Person p) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			// utilisation de l’EntityManager
			em.persist(p);
			em.getTransaction().commit();
			System.err.println("addPerson witdh id=" + p.getId());
			return (p);
		} finally {
			closeEntityManager(em);
		}
	}
	
	public void updatePerson(Person p) {
	}
	
	public void removePerson(Long id) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			Person p = em.find( Person.class, id );
			em.remove( p );
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em);
		}
		
	}
}