package io.mboettger.bachelorthesis.persistence.memory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

class ThreadLocalSessionFactory {
    private static final ThreadLocal<Session> currentSession = new ThreadLocal<>();

    public static Session getOrCreate(SessionFactory sessionFactory) {
        var localSession = currentSession.get();

        if (localSession == null || !localSession.isOpen()) {
            localSession = sessionFactory.openSession();
            currentSession.set(localSession);
        }

        return localSession;
    }
}
