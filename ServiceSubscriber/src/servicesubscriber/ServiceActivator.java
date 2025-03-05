package servicesubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import servicepublisher.ServicePublish;

public class ServiceActivator implements BundleActivator {

    private static BundleContext context;
    private ServiceReference<?> serviceReference;

    static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        // Assign the bundleContext to the static field
        ServiceActivator.context = bundleContext;
        System.out.println("Start Subscribe Service!");

        try {
            // Attempt to get the service reference
            serviceReference = bundleContext.getServiceReference(ServicePublish.class.getName());
            if (serviceReference == null) {
                throw new IllegalStateException("ServicePublish service is not available.");
            }

            // Get the service instance
            ServicePublish servicePublish = (ServicePublish) bundleContext.getService(serviceReference);
            if (servicePublish == null) {
                throw new IllegalStateException("Could not retrieve ServicePublish service.");
            }

            // Call the service method
            System.out.println(servicePublish.publishService());
        } catch (Exception e) {
            System.err.println("Error in ServiceActivator.start(): " + e.getMessage());
            throw e; // Re-throw the exception to let OSGi handle it
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Good Bye!");
        if (serviceReference != null) {
            bundleContext.ungetService(serviceReference);
            serviceReference = null;
        }
        ServiceActivator.context = null; // Clean up context
    }
}