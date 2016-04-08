package prototype.java.osgi.project1;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class ManagerBundleContext {

    private static final ManagerBundleContext MANAGER_BUNDLE_CONTEXT = new ManagerBundleContext();
    private BundleContext bundleContext;

    private ManagerBundleContext() {
    }

    public static ManagerBundleContext getInstance() {
        return MANAGER_BUNDLE_CONTEXT;
    }

    public BundleContext getBundleContext() {
        return bundleContext;
    }

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void listBundles() {
        if (bundleContext != null) {
            Bundle[] bundleList = bundleContext.getBundles();
            for (Bundle bundle : bundleList) {
                System.out.println(bundle.getSymbolicName());
            }
        }
    }

    public void installBundle() {
        if (bundleContext != null) {
            try {
                Bundle bundleInstalled = bundleContext.installBundle("file:/media/data/workspace/repositories/tcc_repository/software/source/smom/main/util/api/v1/br.com.smom.main.util.api/target/br.com.smom.main.util.api-1.0.0.jar");
                System.out.println("Bundle installed: " + bundleInstalled.getSymbolicName());
                bundleInstalled.update();
                bundleInstalled.start();
            } catch (BundleException ex) {
                Logger.getLogger(ManagerBundleContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
