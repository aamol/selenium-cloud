/**
 * 
 */
package org.cloud.proxy;

import java.util.Map;

import org.cloud.capability.CloudType;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.GridRegistry;
import org.openqa.grid.internal.TestSession;
import org.openqa.grid.selenium.proxy.DefaultRemoteProxy;

/**
 * @author amoamol
 *
 */
public class CloudBaseRemoteProxy extends DefaultRemoteProxy {

	public CloudBaseRemoteProxy(RegistrationRequest request, GridRegistry registry) {
		super(request, registry);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeRelease(TestSession session) {
		System.out.println("****************** Delete Session ****************** ");
		// getTestSlots().get(0).getProxy().getRemoteHost();
		// getRegistry().getActiveSessions();

		super.beforeRelease(session);
	}

	@Override
	public void beforeSession(TestSession session) {
		System.out.println("****************** Create Session ******************");
		super.beforeSession(session);

		Map<String, Object> capabilities = session.getRequestedCapabilities();

		if (null != capabilities && capabilities.size() > 0 && null != capabilities.get("CloudType")) {
			CloudType cloudType = CloudType.valueOf((String) capabilities.get("CloudType"));
			System.out.println("****************** CloudType : " + cloudType + " ******************");

			if (null != cloudType && null != cloudType.getProxy()) {
				System.out.println("****************** Cloud Proxy : " + cloudType.getProxy() + " ******************");
				cloudType.getProxy().updateProxyNodes(this);
			}
		}
	}
}
