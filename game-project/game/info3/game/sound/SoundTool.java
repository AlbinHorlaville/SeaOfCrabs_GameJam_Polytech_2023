package info3.game.sound;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import info3.game.graphics.GameCanvas;

public class SoundTool {

	static GameCanvas canvas;

	static BackgroundMusic currenBackgroundSound = BackgroundMusic.MainMenu;
	
	static private boolean cancelSoundEffect = false;

	public final static String path = "assets/audio/";

	private static HashMap<BackgroundMusic, String> backgroundSounds;
	private static HashMap<SoundEffect, String> soundEffects;

	/**
	 * Change la musique actuel du background
	 * 
	 * @param bgs
	 */
	public static void changeBackgroundMusic(BackgroundMusic bgs) {
		stopBackgroundMusic();
		SoundTool.currenBackgroundSound = bgs;
		playBackgroundMusic();
	}

	/**
	 * Joue un sound effect
	 * 
	 * @param se
	 * @param duration
	 * @param volume
	 * @throws FileNotFoundException
	 */
	public static void playSoundEffect(SoundEffect se, long duration){
		String filename = path + "se/" + soundEffects.get(se);
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			
			float volume = (cancelSoundEffect) ? 0:1f;
			canvas.playSound(filename, fis, duration, volume);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	/**
	 * Fonction jouant la musique de background en r�p�tion Use
	 * changeBackgroundMusic() pour changer la musique
	 */
	public static void playBackgroundMusic() {
		String filename = path + "bgm/" + backgroundSounds.get(currenBackgroundSound);
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			canvas.playMusic(fis, 0, 1f);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	public static BackgroundMusic stopBackgroundMusic() {
		String m = null;
		switch (currenBackgroundSound) {
		case MainMenu:
			m = "Town8.ogg";
			break;
		case Game:
			m = "Town1.ogg";
			break;
		default:
		}
		canvas.stopMusic(path + m);
		return currenBackgroundSound;
	}

	/**
	 * Fonction permettant d'initialiser les sons d'avances.
	 * 
	 * @param canvas
	 */
	public static void initSoundTool(GameCanvas canvas) {
		SoundTool.canvas = canvas;
		
		/********
		 * 
		 * HASHMAP DES EFFECTS SONORES
		 * 
		 */
		soundEffects = new HashMap<>();
		
		for (SoundEffect se: SoundEffect.values()) {
			SoundTool.soundEffects.put(se, se.getFileName());
		}

		backgroundSounds = new HashMap<>();

		/********
		 * 
		 * HASHMAP DES SONS DE FONDS
		 * 
		 */
		for (BackgroundMusic bgm: BackgroundMusic.values()) {
			SoundTool.backgroundSounds.put(bgm, bgm.getFileName());
		}
	}

	/**
	 * Permet de savoir si un son est un son de fond
	 * 
	 * @param name
	 * @return
	 */
	public static boolean is_background(String name) {
		// System.out.println(name);
		return backgroundSounds.containsValue(name);
	}

	public static void setCancelSoundEffect(boolean cancelSoundEffect) {
		SoundTool.cancelSoundEffect = cancelSoundEffect;
	}

}
