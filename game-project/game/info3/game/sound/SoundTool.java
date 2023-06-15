package info3.game.sound;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import info3.game.graphics.GameCanvas;

public class SoundTool {
	
	static GameCanvas canvas;
	
	static float backgroundSoundVolume = 0.75f;
	
	public static float getBackgroundSoundVolume() {
		return backgroundSoundVolume;
	}

	public static void setBackgroundSoundVolume(float backgroundSoundVolume) {
		SoundTool.backgroundSoundVolume = backgroundSoundVolume;
	}

	static float effectSoundVolume = 0.75f;
	
	public static float getEffectSoundVolume() {
		return effectSoundVolume;
	}

	public static void setEffectSoundVolume(float effectSoundVolume) {
		SoundTool.effectSoundVolume = effectSoundVolume;
	}

	static BackgroundMusic currenBackgroundSound = BackgroundMusic.MainMenu;
	
	public final static String path = "assets/audio/";
	
	private static HashMap<BackgroundMusic, String> backgroundSounds;
	private static HashMap<SoundEffect, String> soundEffects;
	
	/**
	 * Change la musique actuel du background
	 * @param bgs
	 */
	public static void changeBackgroundMusic(BackgroundMusic bgs) {
		SoundTool.currenBackgroundSound = bgs;
		playBackgroundMusic();
	}
	
	/**
	 * Joue un sound effect
	 * @param se
	 * @param duration
	 * @param volume
	 * @throws FileNotFoundException
	 */
	public static void playSoundEffect(SoundEffect se, long duration) throws FileNotFoundException {
		String filename = path + "se/" + soundEffects.get(se);
		try { 
			RandomAccessFile file = new RandomAccessFile(filename,"r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			canvas.playSound(filename, fis, duration, effectSoundVolume);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	
	
	/**
	 * Fonction jouant la musique de background en r�p�tion
	 * Use changeBackgroundMusic() pour changer la musique
	 */
	public static void playBackgroundMusic() {
		String filename = path + "bgm/" + backgroundSounds.get(currenBackgroundSound);
		try { 
			RandomAccessFile file = new RandomAccessFile(filename,"r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			canvas.playMusic(fis, 0, backgroundSoundVolume);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	
	
	/**
	 * Fonction permettant d'initialiser les sons d'avances.
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
		SoundTool.soundEffects.put(SoundEffect.Test,"Sword4.ogg");

		/********
		 * 
		 * HASHMAP DES SONS DE FONDS
		 * 
		 */
		backgroundSounds = new HashMap<>();
		SoundTool.backgroundSounds.put(BackgroundMusic.Game,"Town1.ogg");
		SoundTool.backgroundSounds.put(BackgroundMusic.MainMenu,"Town8.ogg");
	}
	
	/**
	 * Permet de savoir si un son est un son de fond
	 * @param name
	 * @return
	 */
	public static boolean is_background(String name) {
		//System.out.println(name);
		return backgroundSounds.containsValue(name);
	}

	
	
}
