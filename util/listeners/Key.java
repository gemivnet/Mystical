package util.listeners;

import game.Char;
import game.End;
import game.Main;
import game.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

	private static Key instance;

	public static Key getInstance() {
		if (instance == null) {
			instance = new Key();
		}
		return instance;
	}

	boolean showBG = true;
	String additional = "";

	public void keyPressed(KeyEvent e) {
		if (game.Main.getInstance().isSuspect() == 4) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				Char.getInstance().up();
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				Char.getInstance().down();
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				Char.getInstance().left();
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				Char.getInstance().right();
				break;
			case KeyEvent.VK_B:
				if (showBG) {
					showBG = false;
					System.out.println(showBG);
				} else {
					showBG = true;
				}
				break;

			}
			if (Char.getInstance().ifMessage()
					&& Char.getInstance().isMessageDone()) {
				Char.getInstance().noMessage();
				
				if (Map.getInstance().getTerm(5).getAns() != 0 && Map.getInstance().getTerm(0).getAns() == 0 && Map.getInstance().getTerm(1).getAns() == 0 && Map.getInstance().getTerm(2).getAns() == 0 && Map.getInstance().getTerm(3).getAns() == 0) {
					
					End.getInstance().initEnd();	
						
				}
				
			}
		} else {
			
			int code = 5;
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_1:
				code = 1;
				break;
			case KeyEvent.VK_2:
				code = 2;
				break;
			case KeyEvent.VK_3:
				code = 3;
				break;
			}
			
			if (code != 5) {
				int answer = Map.getInstance().getTerm(game.Main.getInstance().isSuspect()).getAns();
				
				if (answer == code) {
					game.Main.getInstance().finishSuspect();
					additional = "";
				} else {
					additional = "~~That answer is incorrect";
				}
			}
			
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public boolean ifShowBG() {
		return true;
	}
	
	public String getAdditional() {
		return additional;
	}

}
