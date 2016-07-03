package util.servicelocator.initialize;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getGlobal().info(String.format("Start bundle %s", context.getBundle().getSymbolicName()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Logger.getGlobal().info(String.format("Stop bundle %s", context.getBundle().getSymbolicName()));
    }

}
