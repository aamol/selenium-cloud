package org.cloud.proxy.utils;

import org.cloud.proxy.CloudBaseRemoteProxy;

public class AWSProxy extends AbstractCloudProxy {

	public void addANode() {
		// TODO Auto-generated method stub		
	}

	public void removeANode() {
		// TODO Auto-generated method stub
		
	}

	public void updateProxyNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		if(super.requireToAddANode(pCloudBaseRemoteProxy)) {
			addANode();
		} else if(super.requireToRemoveANode(pCloudBaseRemoteProxy)) {
			removeANode();
		}
		
	}

}
