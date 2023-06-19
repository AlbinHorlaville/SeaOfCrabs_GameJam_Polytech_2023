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
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.modele.StillEntityClass.RedCross;
import info3.game.modele.StillEntityClass.Tree;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.Tiles;
import info3.game.sound.BackgroundMusic;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.avatar.BoatPlayerAvatar;
import info3.game.vue.avatar.Player1;
import info3.game.vue.avatar.Player2;
import info3.game.vue.avatar.TreasureAvatar;
import info3.game.vue.avatar.TreeAvatar;

public class GameModele {

	GameView gameview;

	public static ArrayList<Entity> entities = new ArrayList<>();

	public static PiratePlayer player1;

	public static PiratePlayer player2;

	public static BoatPlayer pirateBoat;

	public static boolean onSea = true;

	public static boolean solo = true;

	public static Map map;

	int waveTick = 0;

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
		if (currentState == GameState.Jeu) {
			if (waveTick++ == 10) {
				map.cicleWaveNorth();
				waveTick = 0;
			}
		}
		for (Entity entity : entities) {
			entity.step();
			entity.tick(elapsed);
		}
		ArrayList<Entity> newEntities = new ArrayList<>();
		for (Entity entity : entities) {
			if(!entity.current_state.isDead())
				newEntities.add(entity);
		}
		entities = newEntities;
		// System.out.print("\n\n x : " +
		// -map.getMap()[0].getTiles()[26][map.getSectionWidth() / 2].getX() + "\n\n");
		/*System.out.print("\n\n x : "
				+ this.map.transpoXCoordinateToTile(this.pirateBoat.getX(), this.pirateBoat.getY())
				+ "\n\n");
		System.out.print("tt:" + this.map.getSectionWidth()/2);
		System.out.print("\n\n YYYY : "
				+ this.map.transpoYCoordinateToTile(this.pirateBoat.getX(), this.pirateBoat.getY())
				+ "\n\n");*/
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState state) {
		this.currentState = state;
		this.gameview.update_view(state);
	}

	public void start(int s) throws Exception {
		if (currentState == GameState.AvantJeu) {
			SoundTool.changeBackgroundMusic(BackgroundMusic.Game);
			setCurrentState(GameState.Jeu);

			map = new Map(s);

			player1 = new PiratePlayer("Player1");
			player1.setAvatar(new Player1(player1));
			// GameModele.entities.add(player1);
			if (!solo) {
				player2 = new PiratePlayer("Player2");
				player2.setAvatar(new Player2(player2));
				// GameModele.entities.add(player2);
			}

			pirateBoat = new BoatPlayer(
					map.getMap()[0].getTiles()[this.map.getSectionHeight() - 10][map.getSectionWidth() / 2].getX(),
					map.getMap()[0].getTiles()[this.map.getSectionHeight() - 10][map.getSectionWidth() / 2].getY());
			pirateBoat.setAvatar(new BoatPlayerAvatar(pirateBoat));
			GameModele.entities.add(pirateBoat);
			map = new Map(s);
			
			genereEntity(map);
		}
	}

	public void param() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Parametre);
		}
	}

	public void credits() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Credits);
		}
	}

	public void score() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Score);
		}
	}

	public void commandes() throws IOException {
		if (currentState == GameState.AvantJeu) {
			setCurrentState(GameState.Commandes);
		}
	}

	public void beforePlaying() throws IOException {
		if (currentState == GameState.ChoixGameplay) {
			setCurrentState(GameState.AvantJeu);
		}
	}

	public void choiceGameplay() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.ChoixGameplay);
		}
	}
	
	public static int getCurrentPlayerX() {
		if (onSea) {
			return pirateBoat.getX();
		} else {
			if (solo) {
				return player1.getX();
			} else {
				return (player1.getX()+player2.getX())/2;
			}
		}
	}
	
	public static int getCurrentPlayerY() {
		if (onSea) {
			return pirateBoat.getY();
		} else {
			if (solo) {
				return player1.getY();
			} else {
				return (player1.getY()+player2.getY())/2;
			}
		}
	}

	void genereEntity(Map map) {

		int nbSection = map.getNbSection();
		int mapWidth = map.getSectionWidth();
		int mapHeight = map.getSectionHeight();
		for (int k = 0; k < nbSection; k++) {
			boolean crab = false; // Boolean to spawn only once
			boolean treasure = false; // Boolean to spawn only once
			
			for (int i = 0; i < mapHeight ; i++) {
				for (int j = 0; j < mapWidth ; j++) {
					Tiles Current = map.getMap()[k].getTiles()[i][j];
					Entity newEntity;
					if (Current.getType() == EnumTiles.TREASUR && treasure == false) {
						treasure = true;
						newEntity = new RedCross(map.getMap()[k]);
						newEntity.setLocation(Current.getX(),Current.getY());
						newEntity.setAvatar(new TreasureAvatar(newEntity)); // TODO Mettre dans le constructeur
						entities.add(newEntity);
					} 
					else if (Current.getType() == EnumTiles.CRAB_SPAWNER && crab == false) {
						crab = true;
						newEntity = new CrabLair(k, map.getMap()[k], Current.getX(), Current.getY()); // Créer 10 crabes de niveau k (le numéro																				// de section) avec 20 points de vie
						//newEntity.setLocation(Current.getX(),Current.getY());
						entities.add(newEntity);
					}
					else if (Current.getType() == EnumTiles.TREE) {
						newEntity = new Tree();
						newEntity.setAvatar(new TreeAvatar(newEntity));
						newEntity.setLocation(Current.getX(),Current.getY());
						entities.add(newEntity);
					}
//					} else if (Current.getType() == EnumTiles.SEA_CHEST) {
//						newEntity = new Tree();
//						newEntity.setAvatar(new TreeAvatar(newEntity));
//						newEntity.setX(i);
//						newEntity.setY(j);
//						entities.add(newEntity);
//					}
				}
			}
		}
	}

}
