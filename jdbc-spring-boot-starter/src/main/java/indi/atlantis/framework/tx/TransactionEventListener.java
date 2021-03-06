package indi.atlantis.framework.tx;

import java.util.EventListener;

/**
 * 
 * TransactionEventListener
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public interface TransactionEventListener extends EventListener {

	void fireTransactionEvent(TransactionEvent event);

}
