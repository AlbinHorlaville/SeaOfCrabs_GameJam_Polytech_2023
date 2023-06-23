package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Player;

public abstract class UIBarrePointDeVie extends UIComponent {

	private boolean state;

	public UIBarrePointDeVie(int x, int y) {
		super(x, y, 10, 150);
		state = true;
	}

	public void changeShowing() {
		state = !state;
	}
}
