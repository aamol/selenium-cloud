/**
 * 
 */
package org.cloud.proxy.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cloud.proxy.CloudBaseRemoteProxy;
import org.openqa.grid.internal.ProxySet;
import org.openqa.grid.internal.RemoteProxy;
import org.openqa.grid.internal.TestSession;

/**
 * @author Kshitiz
 *
 */
public abstract class AbstractCloudProxy implements CloudProxy {
	
	private List<String> removeNodes;
	private Map<String, Integer> activeNodes;

	/**
	 * @return the removeNodes
	 */
	public List<String> getRemoveNodes() {
		return removeNodes;
	}

	/**
	 * @param removeNodes the removeNodes to set
	 */
	public void setRemoveNodes(List<String> removeNodes) {
		this.removeNodes = removeNodes;
	}

	/**
	 * @return the activeNodes
	 */
	public Map<String, Integer> getActiveNodes() {
		return activeNodes;
	}

	/**
	 * @param activeNodes the activeNodes to set
	 */
	public void setActiveNodes(Map<String, Integer> activeNodes) {
		this.activeNodes = activeNodes;
	}

	/**
	 * method will detect busy and idle nodes associated with a specified cloud type
	 */
	public void getAllRegisteredNodes(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {

		if (null != pCloudBaseRemoteProxy) {
			
			ProxySet proxies = pCloudBaseRemoteProxy.getRegistry().getAllProxies();
			
			if (null != proxies) {
			
				Iterator<RemoteProxy> iterator = proxies.iterator();
				
				while (iterator.hasNext()) {
					RemoteProxy eachProxy = iterator.next();
					
					if(null != eachProxy) {
						Set<TestSession> activeSessions = eachProxy.getRegistry().getActiveSessions();
						System.out.println("***** Active Sessions :  *****"+ activeSessions.size());
						
						//to get all used proxies that are in use
						//eachProxy.getRegistry().getUsedProxies()
						
						if(null != null && activeSessions.size() == 0) {
							getRemoveNodes().add(eachProxy.getId());
						} else {
							getActiveNodes().put(eachProxy.getId(), activeSessions.size());
						}
						
						//eachProxy.getMaxNumberOfConcurrentTestSessions()
						}	
					}
				}//end of while
			}//end of if
		
	}// end of method

	/**
	 * method to check if need to add a node to the proxy
	 */
	public boolean requireToAddANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		getAllRegisteredNodes(pCloudBaseRemoteProxy);

		if (null != this.getActiveNodes() && this.getActiveNodes().size() > 0) {
			//TO-DO: Check if configured nodes & max active sessions got exausted
			return true;
		}

		return false;
	}

	/**
	 * method to check if need to remove a node in case if node is ideal and not executing any test cases
	 */
	public boolean requireToRemoveANode(CloudBaseRemoteProxy pCloudBaseRemoteProxy) {
		getAllRegisteredNodes(pCloudBaseRemoteProxy);

		if (null != this.getRemoveNodes() && this.getRemoveNodes().size() > 0) {
			return true;
		}
		
		return false;
	}

}// end of class
