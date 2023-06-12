/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game.modele;

import java.io.IOException;
import java.util.ArrayList;

import info3.game.GameState;
import info3.game.Sound;
import info3.game.vue.GameView;
import info3.game.vue.view.PlayingView;

public class GameModele {


	GameView gameview;
	Cowboy cowboy;
	Sound music;
	
	public static ArrayList<Entity> entities = new ArrayList<>();

	GameState currentState;

	public GameModele() throws Exception {
		// creating a cowboy, that would be a model
		// in an Model-View-Controller pattern (MVC)
		currentState = GameState.Menu;
	}	
	
	public void setGameview(GameView gameview) {
		this.gameview = gameview;
	}


	public void tick(long elapsed) {
	}


	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState state) {
		this.currentState = state;
		this.gameview.update_view(state);
	}

	public void start() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Jeu);
			Cowboy cowboy = new Cowboy();
			GameModele.entities.add(cowboy);
		}
	}



}
