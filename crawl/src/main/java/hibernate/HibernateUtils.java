package hibernate;

import model.Format;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import Entity.News;

import java.util.List;

public class HibernateUtils {

    private static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Build Session Factory
     * @return session factory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry registry;
            SessionFactory sessionFactory;
            registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            MetadataSources sources = new MetadataSources(registry);

            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
            System.out.println("Build session Factory sucesss!...");
            return sessionFactory;
        } catch(Throwable e) {
            System.out.println("Initial SessionFactory created Fail:" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * get Session Factory
     * @return session factory
     */
    private static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }


    /**
     * Insert Data
     * @param object object
     */
    public static void insertData(Object object) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try{
            session.getTransaction().begin();
            session.save(object);
            session.getTransaction().commit();
        } catch(Exception e ) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /**
     * Find column of the table
     * @param table table name
     * @param column column name
     * @return list
     */
    public static List findColumnOfTable(String table, String column) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String sql = "";
        try {
            session.getTransaction().begin();
            if(column.equals("all")) {
                sql = "from " + table ;
            } else {
                sql = "select " + column + " from " + table;
            }

            // Tạo đối tượng query
            Query query = session.createQuery(sql);

            List<Object> itemList = query.getResultList();
            session.getTransaction().commit();
            return itemList;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * find Format By Url
     * @param url String
     * @return list
     */
    public static List<String> findFormatByUrl(String Column, String url) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String sql = "";

        try {
            session.getTransaction().begin();
            sql = new StringBuilder().append("select fm.")
                    .append(Column)
                    .append(" from WebFormat fm , Websource ws")
                    .append(" WHERE ws.id = fm.")
                    .append("websource.id")
                    .append(" and ws.url like '%")
                    .append(url).append("'").toString();
            // Tạo đối tượng query
            Query<String>query = session.createQuery(sql);

            List<String>itemList = query.getResultList();
            session.getTransaction().commit();
            return itemList;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * find Content Format By Id
     * @param id id
     * @return list Content Format
     */
    public static List<String> findContentFormatById(long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String sql = "";

        try {
            session.getTransaction().begin();
            sql = new StringBuilder().append("SELECT  wf.formatContentDetail ")
                    .append("from News n, Websource ws, WebFormat wf")
                    .append(" where n.websource.id = ws.id and wf.websource.id = ws.id and n.id = '")
                    .append(id).append("'").toString();
            // Tạo đối tượng query
            Query<String>query = session.createQuery(sql);

            List<String>itemList = query.getResultList();
            session.getTransaction().commit();
            return itemList;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * find Column of Multi Table By String
     * @param table table
     * @param column column
     * @param id id
     * @return list
     */
    public static List findColumnOfMultiTableByString(String table,String column, Long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        String sql = "";

        try {
            session.getTransaction().begin();
            if(column.equals("all")) {
                sql = "from " + table + " a where a.id = " +id ;
            } else {
                sql = "select " + column + " from " + table + " a where a.id = " +id ;
            }
            // Tạo đối tượng query
            Query<News>query = session.createQuery(sql);

            List<News> itemList = query.getResultList();
            session.getTransaction().commit();
            return itemList;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Shutdown the session
     */
    public static void shutDown(){
        getSessionFactory().close();
    }
}
