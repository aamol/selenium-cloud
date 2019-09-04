package org.cloud.capability;

import org.cloud.utils.AWSSeleniumUtils;
import org.cloud.utils.CloudUtils;
import org.cloud.utils.DefaultSeleniumUtils;
import org.cloud.utils.GCPSeleniumUtils;
import org.cloud.utils.KubernetesSeleniumUtils;

public enum CloudType {

	KUBERNETES, GCP, AWS;

	public CloudUtils getUtil() {
		CloudUtils cloudUtils ;
		switch (this) {
		case KUBERNETES:
			cloudUtils = new KubernetesSeleniumUtils();
			break;
		case AWS:
			cloudUtils = new AWSSeleniumUtils();
			break;
		case GCP:
			cloudUtils = new GCPSeleniumUtils();
			break;
		default:
			cloudUtils = new DefaultSeleniumUtils();
		}

		return cloudUtils;
	}

}
