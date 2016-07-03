package util.servicelocator.providers;

import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class BundleProvider {

    public static Object getBundleService(Class<?> classFromBundleService) {

        BundleContext bundleContext;
        ServiceReference serviceReference;
        Object bundleService;

        try {
            bundleContext = getBundle(classFromBundleService).getBundleContext();
            serviceReference = bundleContext.getServiceReference(classFromBundleService.getName());
            bundleService = bundleContext.getService(serviceReference);
            Logger.getGlobal().info(String.format("Bundle service found: %s", classFromBundleService.getName()));
            return bundleService;
        } catch (Exception e) {
            Logger.getGlobal().warning(String.format("Bundle service not found. Class not found with service: %s. Cause: %s",
                    classFromBundleService.getName(), e.getMessage()));
            return null;
        }

    }

    private static Bundle getBundle(Class<?> classFromBundle) {
        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {
            Logger.getGlobal().info(String.format("Bundle found: %s", bundle.getSymbolicName()));
            return bundle;
        } else {
            Logger.getGlobal().warning(String.format("Bundle not found. Class %s was not found as a bundle.",
                    classFromBundle.getName()));
            return null;
        }
    }

    private static Bundle[] getBundleList() {

        BundleContext bundleContext;

        try {
            bundleContext = getBundle(BundleProvider.class).getBundleContext();
            return bundleContext.getBundles();
        } catch (Exception e) {
            Logger.getGlobal().warning(String.format("Error get bundle list. Cause: %s.", e.getMessage()));
            return null;
        }
    }

}
