package info3.game.sound;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;

import info3.game.graphics.GameCanvas;

public class SoundTool {

	static GameCanvas canvas;

	static BackgroundMusic currenBackgroundSound = BackgroundMusic.MainMenu;

	public final static String pathBGM = "assets/audio/bgm/";
	public final static String pathSE = "assets/audio/se/";

	private static HashMap<BackgroundMusic, InputStream> backgroundSounds;
	private static HashMap<SoundEffect, InputStream> soundEffects;

	/**
	 * Change la musique actuel du background
	 * 
	 * @param bgs
	 */
	public static void changeBackgroundMusic(BackgroundMusic bgs) {
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
	public static void playSoundEffect(SoundEffect se, long duration) throws FileNotFoundException {
		try {
			canvas.playSound(se.getFileName(), soundEffects.get(se), duration, 1f);
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
		try {
			canvas.playMusic(backgroundSounds.get(currenBackgroundSound), 0, 1f);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	public static BackgroundMusic stopBackgroundMusic() {
		canvas.stopMusic(pathBGM + currenBackgroundSound.getFileName());
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
			loadSoundEffect(se, se.getFileName());
		}

		/********
		 * 
		 * HASHMAP DES SONS DE FONDS
		 * 
		 */
		backgroundSounds = new HashMap<>();

		for (BackgroundMusic se: BackgroundMusic.values()) {
			loadBackground(se, se.getFileName());
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
		for (BackgroundMusic music : BackgroundMusic.values()) {
			if (name.equals(music.getFileName()));
		}
		return false;
	}
	
	/**
	 * Load Background Music
	 * @param bgm
	 * @param filename
	 */
	private static void loadBackground(BackgroundMusic bgm, String filename) {
		try {
			RandomAccessFile file = new RandomAccessFile( pathBGM + filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			backgroundSounds.put(bgm, fis);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	
	/**
	 * Load Sound Effect
	 * @param se
	 * @param filename
	 */
	private static void loadSoundEffect(SoundEffect se, String filename) {
		try {
			RandomAccessFile file = new RandomAccessFile( pathSE + filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			soundEffects.put(se, fis);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

}
