package prototype.java.osgi.project1.user.api.initialize;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Logger.getLogger("LoggerBundleUser").info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

    public void stop(BundleContext context) throws Exception {
        Logger.getLogger("LoggerBundleUser").info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

}
