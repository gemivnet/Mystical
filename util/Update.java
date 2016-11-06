package util;

import game.End;
import game.Map;

import java.util.Timer;
import java.util.TimerTask;

public class Update extends TimerTask {

	private final int FPS = 120;
	
	private static Update instance;
	
	public static Update getInstance() {
		if (instance == null) {
			instance = new Update();
		}
		return instance;
	}
	
	Timer update;
	
	public void initUpdate() {
	
		  update = new Timer();
	      update.schedule(this, 1000 / FPS, 1000 / FPS);
	
	}

	public void run() {
		frame.Main.getInstance().refresh();
		
	}

}
