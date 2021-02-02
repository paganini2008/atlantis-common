package org.springdessert.framework.cluster.multicast;

import java.util.List;

import org.springdessert.framework.cluster.ApplicationInfo;

/**
 * 
 * RegistryCenter
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public interface RegistryCenter {

	void registerApplication(ApplicationInfo applicationInfo);

	void removeApplication(ApplicationInfo applicationInfo);

	List<ApplicationInfo> getApplications();

	List<ApplicationInfo> getApplications(String applicationName);

	int countOfApplication();

}