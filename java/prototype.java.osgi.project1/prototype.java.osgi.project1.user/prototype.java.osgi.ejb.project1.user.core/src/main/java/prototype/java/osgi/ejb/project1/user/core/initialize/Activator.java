package prototype.java.osgi.ejb.project1.user.core.initialize;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger("LoggerBundleUser").info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));

        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;

        entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManagerFactory);
        System.out.println(entityManager);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Logger.getLogger("LoggerBundleUser").info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

}
