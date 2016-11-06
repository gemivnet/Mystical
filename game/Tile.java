package game;

public class Tile {

	boolean a = false, messageShown;
	int x, y;
	String[] message;
	boolean found = false;
	int messagesDone = 0;
	boolean hasExc = false;
	String g;

	public Tile(int xx, int yy, String[] m, String goal) {

		x = xx;
		y = yy;

		if (m == null) {
			messageShown = true;
		} else {
			message = m;
		}

		if (goal == null) {
			g = "";
		} else {
			g = goal;

			if (g.contains("l")) {
				switch (g) {
				case "dl":
					messageShown = false;
					message = Map.getInstance().getTerm(0).getDef();
					break;
				case "hl":
					messageShown = false;
					message = Map.getInstance().getTerm(1).getDef();
					break;
				case "ddl":
					messageShown = false;
					message = Map.getInstance().getTerm(2).getDef();
					break;
				case "cl":
					messageShown = false;
					message = Map.getInstance().getTerm(3).getDef();
					break;
				}
			}

		}

	}

	public int getX() {
		return x;
	}

	public void msg() {
		message = new String[] { "There seems to be something~here, but you have not~learned about it yet" };
		messageShown = false;
	}

	public int getY() {
		return y;
	}

	public String getMessage() {
		return message[messagesDone];
	}

	public boolean isMessageShown() {
		return messageShown;
	}
	
	public void achieve(String t) {
		
		messageShown = false;
		messagesDone = 0;
		message = new String[]{"Achievement Unlocked:~" + t};
		a = true;
		
	}

	public void setMessageShown() {
		if (messagesDone == message.length - 1) {
			messageShown = true;

			if (End.getInstance().isDone()) {
				End.getInstance().credits();
			}
			
			if (a == false) {
				switch (g) {
				case "detect":
					Map.getInstance().getAchievement(0).setFound();
					achieve(Map.getInstance().getAchievement(0).getName());
					break;
				case "dl":
					Map.getInstance().getAchievement(1).setFound();
					achieve(Map.getInstance().getAchievement(1).getName());
					break;
				case "hl":
					Map.getInstance().getAchievement(3).setFound();
					achieve(Map.getInstance().getAchievement(3).getName());
					break;
				case "cl":
					Map.getInstance().getAchievement(5).setFound();
					achieve(Map.getInstance().getAchievement(5).getName());
					break;
				case "ddl":
					Map.getInstance().getAchievement(7).setFound();
					achieve(Map.getInstance().getAchievement(7).getName());
					break;
				}
			}

			if (g.contains("l")) {
				switch (g) {
				case "dl":
					Map.getInstance().getTerm(0).setLearned();
					break;
				case "hl":
					Map.getInstance().getTerm(1).setLearned();
					break;
				case "ddl":
					Map.getInstance().getTerm(2).setLearned();
					break;
				case "cl":
					Map.getInstance().getTerm(3).setLearned();
					break;
				}
			}

		} else {
			messagesDone++;
		}
	}

	public boolean isFound() {
		switch (g) {
		case "hs":
			if (!found) {
				msg();
			}
			if (found & Map.getInstance().getTerm(1).getAns() != 0) {
				msg();
				hasExc = true;
			} else {
				hasExc = false;
			}
			break;
		case "ds":
			if (!found) {
				msg();
			}
			if (found & Map.getInstance().getTerm(0).getAns() != 0) {
				msg();
				hasExc = true;
			} else {
				hasExc = false;
			}
			break;
		case "cs":
			if (!found) {
				msg();
			}
			if (found & Map.getInstance().getTerm(3).getAns() != 0) {
				msg();
				hasExc = true;
			} else {
				hasExc = false;
			}
			break;
		case "dds":
			if (!found) {
				msg();
			}
			if (found & Map.getInstance().getTerm(2).getAns() != 0) {
				msg();
				hasExc = true;
			} else {
				hasExc = false;
			}
			break;
		}

		return found;
	}

	public void setFound() {
		found = true;
	}

	public boolean hasMessage() {
		if (message == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean shouldHaveExec() {
		return hasExc;
	}

	public String getG() {
		return g;
	}

}
