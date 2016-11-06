package game;

import java.util.ArrayList;

public class Char {

	private static Char instance;

	public static Char getInstance() {
		if (instance == null) {
			instance = new Char();
		}
		return instance;
	}

	int x, y;
	boolean message;
	int msgDone = 0;

	public void initChar() {

		y = 0;
		x = 0;
		updateDirections();

	}

	boolean cUp, cDown, cLeft, cRight;

	public void home(int xx, int yy) {
		x = xx;
		y = yy;
	}
	
	public void updateDirections() {

		Map.getInstance().getTile(x, y).setFound();

		if (Map.getInstance().getTile(x, y).hasMessage()
				& !Map.getInstance().getTile(x, y).isMessageShown()) {
			message = true;
		}

		if (y > 0) {
			cUp = true;
		} else {
			cUp = false;
		}

		if (y < Map.getInstance().getYSize()) {
			cDown = true;
		} else {
			cDown = false;
		}

		if (x > 0) {
			cLeft = true;
		} else {
			cLeft = false;
		}

		if (x < Map.getInstance().getXSize()) {
			cRight = true;
		} else {
			cRight = false;
		}

		if (message == true) {
			cUp = false;
			cDown = false;
			cRight = false;
			cLeft = false;
		}

		switch (Map.getInstance().getTile(x, y).getG()) {
		case "ds":
			if (Map.getInstance().getTerm(0).hasLearned() && Map.getInstance().getTerm(0).getAns() != 0) {
				game.Main.getInstance().setSuspect(0);
			}
			break;
		case "hs":
			if (Map.getInstance().getTerm(1).hasLearned() && Map.getInstance().getTerm(1).getAns() != 0) {
				game.Main.getInstance().setSuspect(1);
			}
			break;
		case "dds":
			if (Map.getInstance().getTerm(2).hasLearned() && Map.getInstance().getTerm(2).getAns() != 0) {
				game.Main.getInstance().setSuspect(2);
			}
			break;
		case "cs":
			if (Map.getInstance().getTerm(3).hasLearned() && Map.getInstance().getTerm(3).getAns() != 0) {
				game.Main.getInstance().setSuspect(3);
			}
			break;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void up() {
		if (cUp) {
			y--;
		}
		updateDirections();
	}

	public void down() {
		if (cDown) {
			y++;
		}
		updateDirections();
	}

	public void left() {
		if (cLeft) {
			x--;
		}
		updateDirections();
	}

	public void right() {
		if (cRight) {
			x++;
		}
		updateDirections();
	}

	public boolean canUp() {
		return cUp;
	}

	public boolean canDown() {
		return cDown;
	}

	public boolean canLeft() {
		return cLeft;
	}

	public boolean canRight() {
		return cRight;
	}

	public boolean ifMessage() {
		return message;
	}

	public void noMessage() {
		message = false;
		Map.getInstance().getTile(x, y).setMessageShown();
		updateDirections();
	}

	public boolean isMessageDone() {
		if (msgDone == 2) {
			return true;
		}
		return false;
	}

	public int getMsg() {
		return msgDone;
	}

	public void setMsg(int m) {
		msgDone = m;
	}
	
	public void trueMessage() {
		message = true;
	}
	
}