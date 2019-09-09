/**
 * 
 */
package org.cloud.proxy.utils;

import java.util.Iterator;
import java.util.Set;

import org.cloud.proxy.CloudBaseRemoteProxy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.grid.internal.ProxySet;
import org.openqa.grid.internal.RemoteProxy;
import org.openqa.grid.internal.TestSession;

/**
 * @author Kshitiz
 *
 */
public abstract class AbstractCloudProxy implements CloudProxy {

	/**
	 * method will detect busy and idle nodes associated with a specified cloud type
	 */
	public JSONObject getAllRegisteredNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		JSONObject allNodes = new JSONObject();

		if (null != pCloudBaseRemoteProxy) {
			ProxySet proxies = pCloudBaseRemoteProxy.getRegistry().getAllProxies();
			if (null != proxies) {
				Iterator<RemoteProxy> iterator = proxies.iterator();
				JSONArray busyProxies = new JSONArray();
				JSONArray freeProxies = new JSONArray();
				while (iterator.hasNext()) {
					RemoteProxy eachProxy = iterator.next();
					
					Set<TestSession> activeSessions = eachProxy.getRegistry().getActiveSessions();
					System.out.println("***** Active Sessions :  *****"+ activeSessions.size());
					
					if (eachProxy.isBusy()) {
						busyProxies.put(eachProxy.getOriginalRegistrationRequest().toJson());
					} else {
						freeProxies.put(eachProxy.getOriginalRegistrationRequest().toJson());
					}
				}

				allNodes.put("BusyProxies", busyProxies);
				allNodes.put("FreeProxies", freeProxies);
			}
		}
		
		return allNodes;
	}// end of method

	/**
	 * method to check if need to add a node to the proxy
	 */
	public boolean requireToAddANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		JSONObject allNodes = getAllRegisteredNodes(pCloudBaseRemoteProxy);

		if (null != allNodes) {
			JSONArray busyProxies = allNodes.getJSONArray("BusyProxies");

			if (null != busyProxies && busyProxies.length() == 5) {
				return true;
			}
		}

		return false;
	}

	/**
	 * method to check if need to remove a node in case if node is ideal and not executing any test cases
	 */
	public boolean requireToRemoveANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		JSONObject allNodes = getAllRegisteredNodes(pCloudBaseRemoteProxy);

		if (null != allNodes) {
			JSONArray freeProxies = allNodes.getJSONArray("FreeProxies");

			if (null != freeProxies && freeProxies.length() == 0) {
				return true;
			}
		}
		return false;
	}

}// end of class
