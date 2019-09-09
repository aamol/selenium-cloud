package org.cloud.proxy.utils;

import org.cloud.proxy.CloudBaseRemoteProxy;
import org.json.JSONObject;

public interface CloudProxy {

	public JSONObject getAllRegisteredNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public boolean requireToAddANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public boolean requireToRemoveANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

	public void addANode();

	public void removeANode();

	public void updateProxyNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy);

}
