package org.cloud.capability;

import org.cloud.proxy.utils.AWSProxy;
import org.cloud.proxy.utils.CloudProxy;
import org.cloud.proxy.utils.DefaultProxy;
import org.cloud.proxy.utils.GCPProxy;
import org.cloud.proxy.utils.KubernetesProxy;

public enum CloudType {

	KUBERNETES, GCP, AWS;

	public CloudProxy getProxy() {
		CloudProxy cloudProxy ;
		
		switch (this) {
		case KUBERNETES:
			cloudProxy = new KubernetesProxy();
			break;
		case AWS:
			cloudProxy = new AWSProxy();
			break;
		case GCP:
			cloudProxy = new GCPProxy();
			break;
		default:
			cloudProxy = new DefaultProxy();
		}

		return cloudProxy;
	}

}
