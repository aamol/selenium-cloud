package org.cloud.proxy.utils;

import org.cloud.proxy.CloudBaseRemoteProxy;

public interface CloudProxy {

	public void getAllRegisteredNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public boolean requireToAddANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public boolean requireToRemoveANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public void addANode();

	public void removeANode();

	public void updateProxyNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

}
