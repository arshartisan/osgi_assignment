package servicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration<?> publishServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Publisher Start!");
		ServicePublish publisherService = new ServicePublishImpl();
		publishServiceRegistration = bundleContext.registerService(ServicePublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Publisher Stop");
	}

}
