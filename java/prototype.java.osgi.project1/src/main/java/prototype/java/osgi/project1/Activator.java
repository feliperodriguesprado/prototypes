package prototype.java.osgi.project1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Start bundle prototype.java.osgi.project1-v1-PROTOTYPE");
        ManagerBundleContext.getInstance().setBundleContext(context);
        ManagerBundleContext.getInstance().listBundles();
        ManagerBundleContext.getInstance().installBundle();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop bundle prototype.java.osgi.project1-v1-PROTOTYPE");
    }

}
