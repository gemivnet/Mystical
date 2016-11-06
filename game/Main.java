package game;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Main {

	private static Main instance;
	
	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;

	}
	
	int suspect = 4;
	
	Font f;
	
	public void init() {
		
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/img/font.ttf").openStream());
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map.getInstance().initMap();
		frame.Main.getInstance().initFrame();
		util.Update.getInstance().initUpdate();
		Char.getInstance().initChar();
		Music.getInstance().initMusic();
	}
	
	public Font getF() {
		return f;
	}
	
	public void setSuspect(int l) {
		suspect = l;
	}

	public int isSuspect() {
		return suspect;
	}
	
	public void finishSuspect() {
		
		Map.getInstance().getTerm(suspect).setCompleted();
		
		switch (suspect) {
		case 0:
			suspect = 4;
			Map.getInstance().getAchievement(2).setFound();
			Map.getInstance().getTile(Char.getInstance().getX(), Char.getInstance().getY()).achieve(Map.getInstance().getAchievement(2).getName());
			break;
		case 1:
			suspect = 4;
			Map.getInstance().getAchievement(4).setFound();
			Map.getInstance().getTile(Char.getInstance().getX(), Char.getInstance().getY()).achieve(Map.getInstance().getAchievement(4).getName());
			break;
		case 2:
			suspect = 4;
			Map.getInstance().getAchievement(8).setFound();
			Map.getInstance().getTile(Char.getInstance().getX(), Char.getInstance().getY()).achieve(Map.getInstance().getAchievement(8).getName());
			break;
		case 3:
			suspect = 4;
			Map.getInstance().getAchievement(6).setFound();
			Map.getInstance().getTile(Char.getInstance().getX(), Char.getInstance().getY()).achieve(Map.getInstance().getAchievement(6).getName());
			break;
		case 5:
			Map.getInstance().getTerm(5).setCompleted();
			suspect = 4;
			End.getInstance().message();
		}
	}
	
}
