package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    protected final SessionFactory sessionFactory = Util.setUpSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.createNativeQuery("CREATE TABLE IF NOT EXISTS UsersTable (ID bigint NOT NULL AUTO_INCREMENT,"
                        + "Name varchar(255), LastName varchar(255), Age tinyint, PRIMARY KEY (ID))").executeUpdate();
                session.getTransaction().commit();

            } catch (Exception e) {
                System.out.println("Table already exist");
                session.getTransaction().rollback();
            } finally {
                session.close();
            }

        }

    }

    @Override
    public void dropUsersTable() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS UsersTable").executeUpdate();
                session.getTransaction().commit();

            } catch (Exception e) {
                System.out.println("Table dont exist");
                session.getTransaction().rollback();
            } finally {
                session.close();
            }

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.save(new User(name,lastName,age));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {

        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.createQuery("delete User where ID = :id").setParameter("id", id).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                List result = session.createQuery("from User").list();
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                session.createQuery("delete from User").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }

    }
}
