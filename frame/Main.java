package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

	private static Main instance;
	
	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}
	
	JFrame f = new JFrame("Mystical");
	
	public void initFrame() {

		f.setSize(1600, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		f.add(new Paint());
		
		f.addKeyListener(util.listeners.Key.getInstance());
		f.addMouseListener(util.listeners.Mouse.getInstance());
		
	}
	
	public void refresh() {
		f.repaint();
	}
	
	public int getWidth() {
		return f.getContentPane().getWidth();
	}
	
	public int getHeight() {
		return f.getContentPane().getHeight();
	}

}
