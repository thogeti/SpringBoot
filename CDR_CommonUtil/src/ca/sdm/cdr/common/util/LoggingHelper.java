package ca.sdm.cdr.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingHelper {

	
	private long lastLogTime = System.currentTimeMillis();

	private synchronized long getLastLogTime() {
		return lastLogTime;
	}
	
	private synchronized void setLastLogTime(long logTime) {
		lastLogTime = logTime;
	}

	public void log(Logger log, String logContent) {
		try {
			long current = System.currentTimeMillis();
			long diff = current - getLastLogTime();
			setLastLogTime(current);
			logContent = String.valueOf(diff) + " ms: " + logContent;
			log.info(logContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
