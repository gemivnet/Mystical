package game;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class End {

	private static End instance;

	public static End getInstance() {
		if (instance == null) {
			instance = new End();
		}
		return instance;
	}

	boolean done = false;
	boolean credits = false;

	public void initEnd() {

		Main.getInstance().setSuspect(5);
		done = true;

	}

	public void message() {
		Map.getInstance().finish();
		Char.getInstance().home(0, 0);

	}

	public void credits() {
		credits = true;
		Music.getInstance().creditMusic();
	}

	public boolean isDone() {
		return done;
	}

	public boolean isCredits() {
		return credits;
	}

	public String getCredits() {

		String token1 = "";

		Scanner i = new Scanner(getClass().getResourceAsStream("credits.txt"));

		StringBuffer o = new StringBuffer();
		while (i.hasNextLine())
			o.append(i.nextLine() + "\n");

		String c = o.toString();

		return c;

	}

}
