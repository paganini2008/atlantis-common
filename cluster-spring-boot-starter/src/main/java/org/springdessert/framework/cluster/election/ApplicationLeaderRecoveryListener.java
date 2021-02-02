package org.springdessert.framework.cluster.election;

import org.springdessert.framework.cluster.ApplicationInfo;
import org.springdessert.framework.cluster.HealthState;
import org.springdessert.framework.cluster.InstanceId;
import org.springdessert.framework.cluster.multicast.ApplicationMulticastEvent;
import org.springdessert.framework.cluster.multicast.ApplicationMulticastEvent.MulticastEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * ApplicationLeaderRecoveryListener
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
@Slf4j
public class ApplicationLeaderRecoveryListener implements ApplicationListener<ApplicationMulticastEvent> {

	@Value("${spring.application.cluster.name}")
	private String clusterName;

	@Autowired
	private InstanceId instanceId;

	@Autowired
	private LeaderRecovery leaderRecovery;

	@Override
	public void onApplicationEvent(ApplicationMulticastEvent applicationEvent) {
		if (applicationEvent.getMulticastEventType() == MulticastEventType.ON_INACTIVE
				&& applicationEvent.getApplicationInfo().isLeader()) {
			log.info("Leader of application cluster '{}' is expired.", clusterName);
			ApplicationInfo formerLeader = instanceId.getLeaderInfo();
			instanceId.setLeaderInfo(null);

			leaderRecovery.recover(formerLeader);

			ApplicationContext applicationContext = applicationEvent.getApplicationContext();
			applicationContext.publishEvent(new ApplicationClusterLeaderEvent(applicationContext, HealthState.UNLEADABLE));
		}
	}

}
