package indi.atlantis.framework.tx;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * TransactionEvent
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public class TransactionEvent extends ApplicationEvent {

	public TransactionEvent(Object source, String id, TransactionPhase transactionPhase, Throwable cause) {
		super(source);
		this.id = id;
		this.transactionPhase = transactionPhase;
		this.cause = cause;
	}

	private final String id;
	private final TransactionPhase transactionPhase;
	private final Throwable cause;

	public String getId() {
		return id;
	}

	public TransactionPhase getTransactionPhase() {
		return transactionPhase;
	}

	public Throwable getCause() {
		return cause;
	}

	private static final long serialVersionUID = 3314753368163679618L;

}
