package game;

public class Achievement {

	boolean found = false;
	String n, u;
	
	public Achievement(String name, String url) {
		n = name;
		u = url;
	}
	
	public String getName() {
		return n;
	}
	
	public String getURL() {
		return u;
	}
	
	public boolean isFound() {
		return found;
	}
	
	public void setFound() {
		found = true;
	}
	
}
