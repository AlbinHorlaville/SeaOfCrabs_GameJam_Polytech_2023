package info3.game;

import automate.AutomateLoader;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;

public class SeaOfCrabes {

	static GameModele game;
	static GameView view;
	static Controller controller;

	public static void main(String[] args) {
		try {
			System.out.println("Game starting...");
			AutomateLoader.initAutomateLoader(); // Initialisation des automates
			SpriteLoader.initAllSprites();
			controller = new Controller();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

}
