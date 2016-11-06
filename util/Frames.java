package util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Frames {

	private static Frames instance;
	
	public static Frames getInstance() {
		if (instance == null) {
			instance = new Frames();
		}
		return instance;
	}
	
	int f;
	int tF;
	long old;
	
	public int FPS() {
		
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		
		long current = rb.getUptime();
		
		if (current - old >= 1000) {
			old = rb.getUptime();
			f = tF;
			tF = 0;
		} else {
			tF++;
		}

		return f;
		
	}
	
}
