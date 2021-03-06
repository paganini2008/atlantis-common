package indi.atlantis.framework.tx;

import com.github.paganini2008.devtools.cache.Cache;
import com.github.paganini2008.devtools.db4j.JdbcOperations;

/**
 * 
 * TransactionalSession
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public class TransactionalSession extends AbstractSession {

	private final JdbcTransaction jdbcTransaction;
	private final long timeout;

	public TransactionalSession(JdbcTransaction jdbcTransaction, Cache cache, long timeout) {
		super(jdbcTransaction.getJdbcOperations(), cache);
		this.jdbcTransaction = jdbcTransaction;
		this.timeout = timeout;
	}

	@Override
	public JdbcOperations getJdbcOperations() {
		if (timeout < 0 || System.currentTimeMillis() - jdbcTransaction.getStartTime() < timeout) {
			return jdbcTransaction.getJdbcOperations();
		}
		throw new TransactionTimeoutException(String.valueOf(timeout));
	}

	@Override
	public long getTimeout() {
		return timeout;
	}

}
