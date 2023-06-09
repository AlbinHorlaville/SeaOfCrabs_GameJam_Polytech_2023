package info3.game;

import info3.game.modele.GameModele;
import info3.game.vue.GameView;

public class SeaOfCrabes {

	static GameModele game;
	static GameView view;
	static Controller controller;

	public static void main(String[] args) {
		try {
			System.out.println("Game starting...");
			controller = new Controller();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

}
