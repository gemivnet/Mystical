package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Music {

	private static Music instance;

	public static Music getInstance() {
		if (instance == null) {
			instance = new Music();
		}
		return instance;
	}

	Clip clip;

	public void initMusic() {
		if (End.getInstance().isDone() == false) {
			AudioInputStream audioIn;
			try {
				int n = (int) (Math.random() * 3) + 1;
				audioIn = AudioSystem.getAudioInputStream(getClass()
						.getResource("/music/" + n + ".wav"));
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				clip.addLineListener(new LineListener() {

					public void update(LineEvent e) {

						if (e.getType() == LineEvent.Type.STOP) {
							e.getLine().close();
							initMusic();
						}

					}

				});

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	public void creditMusic() {
		clip.stop();
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(getClass().getResource(
					"/music/end.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			
			clip.addLineListener(new LineListener() {

				public void update(LineEvent e) {

					if (e.getType() == LineEvent.Type.STOP) {
						e.getLine().close();
						creditMusic();
					}

				}

			});

			
		} catch (Exception e) {

		}

	}

}
