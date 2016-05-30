package prototype.java.osgi.util.services;

import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * Class responsible for getting bundles.
 */
public class BundleProvider {

    protected static Bundle getBundle(Class<?> classFromBundle) {
        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {
            Logger.getLogger("LoggerBundleUtil").info(String.format("Bundle found: %s %s.", bundle.getSymbolicName(), bundle.getVersion()));
            return bundle;
        } else {
            Logger.getLogger("LoggerBundleUtil").warning(String.format("Bundle not found: Class %s was not found as a bundle.", classFromBundle.getName()));
            return null;
        }
    }

    protected static Bundle[] getBundleList() {

        BundleContext bundleContext;

        try {
            bundleContext = getBundle(BundleProvider.class).getBundleContext();
            return bundleContext.getBundles();
        } catch (Exception e) {
            Logger.getLogger("LoggerBundleUtil").warning("Error get bundle list.");
            return null;
        }
    }

}
