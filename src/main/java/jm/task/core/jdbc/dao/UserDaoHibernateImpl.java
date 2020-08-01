package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {

        Session session = sessionFactory
                .openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)")
                   .executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе createUsersTable: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {

        Session session = sessionFactory
                .openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("DROP TABLE users")
                   .executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе dropUsersTable: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory
                .openSession();//создается объект типа Session
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction
                    .commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе saveUser: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }  /* entityManager.persist(new User(name, lastName, age));*/
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory
                .openSession();//создается объект типа Session
        Transaction transaction = session.beginTransaction();//запускается транзакция
        try {
            session.delete(id);//делается нужное действие (удаление данных)
            session.flush();
            transaction
                    .commit();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе removeUserByID: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory
                .openSession();//создается объект типа Session
        List<User> list = new ArrayList<>();
        try {
            list = session.createQuery("from User")
                          .list();//делается нужное действие (получение данных)
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе getAllUsers: " + e.getMessage());
        } finally {
            session.close();
        }
        return list; /* return entityManager.createQuery("Select e from " + User  e")
                         .getResultList();*/
    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory
                .openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("DELETE from User")
                   .executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Выброшено SQL исключение в методе cleanUsersTable: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
