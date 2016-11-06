package frame;

import game.Char;
import game.End;
import game.Map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import terms.Term;
import util.Frames;

public class Paint extends JPanel {
	long old, other;
	boolean sec = true;
	boolean ms = true;
	int height = Main.getInstance().getHeight();
	int width = Main.getInstance().getWidth();
	boolean repaint = true;
	ImageIcon msg, letter, img, map;
	String message;
	String cm = "";
	int pos, lPos;
	int ocx, ocy, Cx = 1, Cy = 1;
	ArrayList<ImageIcon> aws;

	String lttr, ll = "";
	String c, cc = "";

	int eX = 10, eY = 40;
	int times = 0;
	int delay = 50;
	int speed = 15;

	public void paintComponent(Graphics g) {

		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();

		ocx = Cx;
		ocy = Cy;
		Cx = Char.getInstance().getX();
		Cy = Char.getInstance().getY();

		// Timed Updates

		long current = rb.getUptime();

		// Every Second

		if (current - old >= 1000) {
			old = rb.getUptime();
			old = Math.abs(old);
			if (sec) {
				sec = false;
			} else {
				sec = true;
			}
		}

		// Text Update

		if (current - other >= speed) {
			other = rb.getUptime();
			if (ms) {
				ms = false;
			} else {
				ms = true;
				times += 2;
			}
		}

		if (!End.getInstance().isCredits()) {

			// Check if repaint is needed

			if ((height != Main.getInstance().getHeight())
					|| (width != Main.getInstance().getWidth())) {
				height = Main.getInstance().getHeight();
				width = Main.getInstance().getWidth();
				repaint = true;
			}

			// Draw Current Tile

			if (repaint || (Cx != ocx || Cy != ocy)) {
				map = new ImageIcon(getClass().getResource(
						"/map/" + Cx + "x" + Cy + ".png"));
				Image mapi = map.getImage();
				mapi = mapi.getScaledInstance(width * 196 / 320 + 1,
						height * 158 / 180 + 1, 4);
				map = new ImageIcon(mapi);
			}

			g.drawImage(map.getImage(), width * 12 / 320, height * 12 / 180,
					null);

			// Draw Background

			if (repaint) {
				img = new ImageIcon(getClass().getResource("/img/bg.png"));
				Image i = img.getImage();
				i = i.getScaledInstance(width, height, 4);
				img = new ImageIcon(i);
			}
			g.drawImage(img.getImage(), 0, 0, null);

			// Set Font

			g.setFont(game.Main.getInstance().getF().deriveFont(Font.PLAIN, 15));
			g.setColor(Color.WHITE);
			g.drawString("FPS: " + Frames.getInstance().FPS(), 5, 15);

			// Draw Arrows

			if ((Char.getInstance().canUp()) && (sec)) {
				ImageIcon up = new ImageIcon(getClass().getResource(
						"/img/up.png"));
				g.drawImage(up.getImage(), width * 250 / 800,
						45 * height / 500, null);
			}

			if ((Char.getInstance().canDown()) && (sec)) {
				ImageIcon up = new ImageIcon(getClass().getResource(
						"/img/down.png"));
				g.drawImage(up.getImage(), width * 250 / 800,
						405 * height / 500, null);
			}

			if (Char.getInstance().canLeft() && sec) {
				ImageIcon up = new ImageIcon(getClass().getResource(
						"/img/left.png"));
				g.drawImage(up.getImage(), width * 40 / 800,
						230 * height / 500, null);
			}

			if (Char.getInstance().canRight() && sec) {
				ImageIcon up = new ImageIcon(getClass().getResource(
						"/img/right.png"));
				g.drawImage(up.getImage(), width * 460 / 800,
						230 * height / 500, null);
			}

			// Draw Map Preview

			int sizeX = width * 76 / 320 / 5 + 2;
			int sizeY = height * 76 / 180 / 5 + 3;
			for (int x = 0; x < Map.getInstance().getXSize() + 1; x++) {
				for (int y = 0; y < Map.getInstance().getYSize() + 1; y++) {
					if (Map.getInstance().getTile(x, y).isFound()) {
						g.setColor(new Color(38, 127, 0));
					} else {
						g.setColor(new Color(18, 61, 0));
					}
					g.fillRect(x * sizeX + width * 232 / 320 - 2, y * sizeY
							+ height * 12 / 180, sizeX, sizeY);
					if (((x == Cx ? 1 : 0) & (y == Char.getInstance().getY() ? 1
							: 0)) != 0) {
						g.setColor(Color.WHITE);
						g.drawRect(x * sizeX + width * 232 / 320 - 2, y * sizeY
								+ height * 12 / 180, sizeX - 1, sizeY - 1);
					}
					if (Map.getInstance().getTile(x, y).shouldHaveExec()) {
						g.setColor(Color.BLACK);
						g.setFont(game.Main.getInstance().getF()
								.deriveFont(Font.PLAIN, height / 20));
						g.drawString("?", x * sizeX + width * 237 / 320 - 2, y
								* sizeY + height * 23 / 180);
					}
				}
			}

			// Draw Message

			if (Char.getInstance().ifMessage()) {
				if (message != Map.getInstance().getTile(Cx, Cy).getMessage()) {
					message = Map.getInstance().getTile(Cx, Cy).getMessage();
					Char.getInstance().setMsg(1);
					cm = "";
					pos = 0;
				}
				if (Char.getInstance().getMsg() == 0) {
					message = Map.getInstance().getTile(Cx, Cy).getMessage();
					Char.getInstance().setMsg(1);
				} else if (Char.getInstance().getMsg() == 1) {
					if (ms) {
						if (pos < message.length()) {
							cm += message.substring(pos, pos + 1);
							pos += 1;
						} else {
							Char.getInstance().setMsg(2);
						}
					}
				} else if (sec) {
					if (cm.contains("...")) {
						cm = cm.replace("...", "");
					}
				} else if (!cm.contains("...")) {
					cm += "...";
				}
				if (repaint) {
					msg = new ImageIcon(getClass().getResource(
							"/img/message.png"));
					Image m = msg.getImage();
					m = m.getScaledInstance(width * 194 / 320,
							height * 63 / 180, 4);
					msg = new ImageIcon(m);
				}
				g.drawImage(msg.getImage(), width * 13 / 320,
						height * 105 / 180, null);

				g.setFont(game.Main.getInstance().getF()
						.deriveFont(Font.PLAIN, height / 20));
				g.setColor(Color.WHITE);

				int y = height * 340 / 500;
				int x = width * 65 / 800;

				for (int i = 0; i < cm.length(); i++) {

					if (cm.substring(i, i + 1).equals("~")) {

						y += height / 16;
						x = width * 65 / 800;

					} else {

						g.drawString(cm.substring(i, i + 1), x, y);
						x += height / 30;

					}

				}

			} else {
				Char.getInstance().setMsg(0);
				cm = "";
				pos = 0;
			}

			// Draw Achievements

			int px = width * 243 / 320, py = height * 110 / 180;

			aws = new ArrayList<ImageIcon>();
			for (int i = 0; i < 9; i++) {
				if (Map.getInstance().getAchievement(i).isFound()) {

					ImageIcon z = new ImageIcon(getClass().getResource(
							"/achievements/"
									+ Map.getInstance().getAchievement(i)
											.getURL()));
					Image zz = z.getImage();
					zz = zz.getScaledInstance(width * 15 / 320,
							height * 16 / 180, 4);
					z = new ImageIcon(zz);
					aws.add(z);
				}
			}

			if (aws != null) {

				for (int i = 0; i < aws.size(); i++) {
					g.drawImage(aws.get(i).getImage(), px, py, null);
					px += (width * 4 / 320) + (width * 15 / 320);

					if ((i + 1) % 3 == 0) {
						px = width * 243 / 320;
						py += (height * 2 / 180) + (height * 16 / 180);
					}
				}
			}

			// Draw Learning

			if (repaint) {

				letter = new ImageIcon(getClass()
						.getResource("/img/letter.png"));
				Image l = letter.getImage();
				l = l.getScaledInstance(width * 200 / 320, height * 160 / 180,
						4);
				letter = new ImageIcon(l);

			}

			if (game.Main.getInstance().isSuspect() != 4) {

				g.drawImage(letter.getImage(), width * 10 / 320,
						height * 10 / 180, null);

				Term t = Map.getInstance().getTerm(
						game.Main.getInstance().isSuspect());

				g.setFont(game.Main.getInstance().getF()
						.deriveFont(Font.PLAIN, height / 30));
				g.setColor(Color.WHITE);

				if (game.Main.getInstance().isSuspect() == 5) {
					g.setFont(game.Main.getInstance().getF()
							.deriveFont(Font.PLAIN, height / 40));
				}

				lttr = t.getQuestion() + "~~" + t.getSus(0) + "~~"
						+ t.getSus(1) + "~~" + t.getSus(2)
						+ util.listeners.Key.getInstance().getAdditional();

				if (ms) {
					if (lPos < lttr.length()) {
						ll += lttr.substring(lPos, lPos + 1);
						lPos += 1;
					}
				}

				int y = height * 18 / 180;
				int x = width * 18 / 320;

				for (int i = 0; i < ll.length(); i++) {

					if (ll.substring(i, i + 1).equals("~")) {

						x = width * 18 / 320;
						
						if (game.Main.getInstance().isSuspect() == 5) {
							y += height / 36;
						} else {
							y += height / 26;
						}

					} else {

						g.drawString(ll.substring(i, i + 1), x, y);

						if (game.Main.getInstance().isSuspect() == 5) {
							x += height / 50;
						} else {
							x += height / 40;
						}

					}

				}

				ImageIcon one = new ImageIcon(getClass().getResource(
						"/img/one.png"));
				Image oone = one.getImage();
				oone = oone.getScaledInstance(width * 15 / 320,
						height * 25 / 320, 4);
				one = new ImageIcon(oone);

				g.drawImage(one.getImage(), width * 80 / 320,
						height * 153 / 180, null);

				ImageIcon two = new ImageIcon(getClass().getResource(
						"/img/two.png"));
				Image ttwo = two.getImage();
				ttwo = ttwo.getScaledInstance(width * 15 / 320,
						height * 25 / 320, 4);
				two = new ImageIcon(ttwo);

				g.drawImage(two.getImage(), width * 100 / 320,
						height * 153 / 180, null);

				ImageIcon three = new ImageIcon(getClass().getResource(
						"/img/three.png"));
				Image tthree = three.getImage();
				tthree = tthree.getScaledInstance(width * 15 / 320,
						height * 25 / 320, 4);
				three = new ImageIcon(tthree);

				g.drawImage(three.getImage(), width * 120 / 320,
						height * 153 / 180, null);

			} else {
				lPos = 0;
				ll = "";
			}

		} else {

			speed = 60;

			if (c == null) {
				times = 0;
			}

			g.setFont(game.Main.getInstance().getF()
					.deriveFont(Font.PLAIN, height / 25));
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Main.getInstance().getWidth(), Main.getInstance()
					.getHeight());
			c = End.getInstance().getCredits();

			g.setColor(Color.WHITE);

			if (ms) {
				if (lPos < c.length()) {
					cc += c.substring(lPos, lPos + 1);
					lPos += 1;
				}
			}

			int y;

			int x = eX;
			if (times > 320) {
				y = eY - times + 320;
			} else {
				y = eY;
			}

			for (int i = 0; i < cc.length(); i++) {

				if (cc.substring(i, i + 1).equals("~")) {

					y += height / 21;
					x = 10;

				} else {

					g.drawString(cc.substring(i, i + 1), x, y);
					x += height / 40;

				}

			}

		}

		ms = false;
		repaint = false;
	}
}
