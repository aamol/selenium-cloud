/**
 * 
 */
package org.cloud.proxy;

import java.util.Map;

import org.cloud.capability.CloudType;
import org.cloud.utils.CloudUtils;
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
		// TODO Auto-generated method stub
		super.beforeRelease(session);
	}

	@Override
	public void beforeSession(TestSession session) {
		// TODO Auto-generated method stub
		super.beforeSession(session);
		Map capabilities = session.getRequestedCapabilities();
		CloudType cloudType = CloudType.valueOf((String) capabilities.get("CloudType"));
		CloudUtils cloudUtil = cloudType.getUtil();
		if(null!=cloudType) {
			cloudUtil.checkandCreateIfNewInstanceToBeCreated(this);

		}
		
	}
	
	

}
