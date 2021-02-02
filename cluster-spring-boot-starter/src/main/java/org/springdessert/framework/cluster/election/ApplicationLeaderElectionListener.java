package org.springdessert.framework.cluster.election;

import org.springdessert.framework.cluster.multicast.ApplicationMulticastEvent;
import org.springdessert.framework.cluster.multicast.ApplicationMulticastEvent.MulticastEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

/**
 * 
 * ApplicationLeaderElectionListener
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public class ApplicationLeaderElectionListener implements LeaderElectionListener, ApplicationListener<ApplicationMulticastEvent> {

	@Autowired
	private LeaderElection leaderElection;

	@Override
	public void onApplicationEvent(ApplicationMulticastEvent applicationEvent) {
		if (applicationEvent.getMulticastEventType() == MulticastEventType.ON_ACTIVE) {
			leaderElection.onTriggered(applicationEvent);
		}
	}

}
