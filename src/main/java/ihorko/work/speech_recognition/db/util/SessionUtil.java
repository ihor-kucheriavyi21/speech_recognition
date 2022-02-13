package ihorko.work.speech_recognition.db.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return sessionFactory.openSession();
    }
}
