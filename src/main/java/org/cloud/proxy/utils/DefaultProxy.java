package org.cloud.proxy.utils;

import org.cloud.proxy.CloudBaseRemoteProxy;

public class DefaultProxy extends AbstractCloudProxy {

	public void checkIfNodeToBeAdded(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		if (super.requireToAddANode(pCloudBaseRemoteProxy)) {
			// TODO Auto-generated method stub
		}
	}

	public void checkIfNodeToBeRemoved(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		if (super.requireToRemoveANode(pCloudBaseRemoteProxy)) {
			// TODO Auto-generated method stub
		}
	}

}
