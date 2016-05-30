package prototype.java.osgi.util.services;

import java.util.logging.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Class responsible for getting services of modules.
 */
public class ServiceProvider {

    public static Object getBundleService(Class<?> classFromBundleService) {

        BundleContext bundleContext;
        ServiceReference serviceReference;
        Object bundleService;

        try {
            bundleContext = BundleProvider.getBundle(classFromBundleService).getBundleContext();
            serviceReference = bundleContext.getServiceReference(classFromBundleService.getName());
            bundleService = bundleContext.getService(serviceReference);
            Logger.getLogger("LoggerBundleUtil").info(String.format("Bundle service found: %s.", classFromBundleService.getName()));
            return bundleService;
        } catch (Exception e) {
            Logger.getLogger("LoggerBundleUtil").warning(String.format("Bundle service not found. Class not found with service: %s. %s.", classFromBundleService.getName(), e.getMessage()));
            return null;
        }

    }

}
