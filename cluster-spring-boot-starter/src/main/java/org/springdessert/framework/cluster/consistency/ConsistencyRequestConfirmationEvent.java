package org.springdessert.framework.cluster.consistency;

import org.springdessert.framework.cluster.ApplicationInfo;
import org.springframework.context.ApplicationEvent;

/**
 * 
 * ConsistencyRequestConfirmationEvent
 *
 * @author Jimmy Hoff
 * @since 1.0
 */
public class ConsistencyRequestConfirmationEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4041272418956233610L;

	public ConsistencyRequestConfirmationEvent(ConsistencyRequest request, ApplicationInfo applicationInfo, boolean ok) {
		super(request.getValue());
		this.request = request;
		this.applicationInfo = applicationInfo;
		this.ok = ok;
	}

	private final ConsistencyRequest request;
	private final ApplicationInfo applicationInfo;
	private final boolean ok;

	public ConsistencyRequest getRequest() {
		return request;
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public boolean isOk() {
		return ok;
	}

}
