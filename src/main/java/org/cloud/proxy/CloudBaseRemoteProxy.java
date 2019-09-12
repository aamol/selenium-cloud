/**
 * 
 */
package org.cloud.proxy;

import java.util.Map;

import org.cloud.capability.CloudType;
import org.cloud.proxy.utils.CloudProxy;
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
		CloudProxy cloudProxy = getProxy(session);
		
		if(null != cloudProxy) {
			cloudProxy.checkIfNodeToBeRemoved(this);
		}
		
		super.beforeRelease(session);
	}

	@Override
	public void beforeSession(TestSession session) {
		CloudProxy cloudProxy = getProxy(session);
		
		if(null != cloudProxy) {
			cloudProxy.checkIfNodeToBeAdded(this);
		}
		
		super.beforeSession(session);
	}

	/**
	 * get the object of Cloud proxy class
	 * @param session
	 * @return
	 */
	private CloudProxy getProxy(TestSession session) {
		if(null != session) {
			
			Map<String, Object> capabilities = session.getRequestedCapabilities();

			if (null != capabilities && capabilities.size() > 0 && null != capabilities.get("CloudType")) {
				CloudType cloudType = CloudType.valueOf((String) capabilities.get("CloudType"));

				if (null != cloudType && null != cloudType.getProxy()) {
					return cloudType.getProxy();
				}
			}
		}// end of if
		
		return null;
	}//end of getProxy
}
