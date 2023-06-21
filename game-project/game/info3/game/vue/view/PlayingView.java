package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.Controller;
import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.GameTimer;
import info3.game.modele.Weapon;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Player;
import info3.game.modele.MoveableEntityClass.Scythe;
import info3.game.modele.MoveableEntityClass.Sword;
import info3.game.modele.StillEntityClass.CloudCluster;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.vue.toolkitUI.UIBarrePointDeVie;
import info3.game.vue.toolkitUI.UIBarreVieMer;
import info3.game.vue.toolkitUI.UIBarreVieTerre;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;

public class PlayingView extends View {

	UIBarrePointDeVie barreVieMer, barreVieTerre;
	UIBoxes cannonBallBox, boxPlayer1, boxPlayer2;
	UIBox boxSword, boxScythe;
	UILabel labelTimer;

	UIBox weaponPlayer1, weaponPlayer2;

	public PlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		GameModele.timer = new GameTimer();

		barreVieTerre = new UIBarreVieTerre(0, 0);
		barreVieMer = new UIBarreVieMer(0, 0);
		labelTimer = new UILabel(windowWidth / 2, 25, "0'", FONT4, Color.black);

		cannonBallBox = new UIBoxes((windowWidth - 100) / 2, windowHeight - 114);
		boxSword = new UIBox(64, new Sword(), new UIImage(0, 0, "resources/img/Epee.png", 1F));
		boxScythe = new UIBox(64, Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 1F));
		cannonBallBox.addBox(boxSword);
		cannonBallBox.addBox(boxScythe);

		addComponent(cannonBallBox);
		
	}

	@Override
	public void tick(long elapsed) {

		for (Entity entity : GameModele.entities) {
			if (!(entity instanceof CloudCluster))
				entity.getAvatar().tick(elapsed);
		}

		GameModele.timer.updateTimer(elapsed);
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		if (GameModele.onSea) {
			GameModele.map.getRepresentation().paint(g, width, height, GameModele.pirateBoat.getX(),
					GameModele.pirateBoat.getY());
		} else {
			if (GameModele.solo) {
				GameModele.map.getRepresentation().paint(g, width, height, GameModele.player1.getX(),
						GameModele.player1.getY());
			} else {
				GameModele.map.getRepresentation().paint(g, width, height,
						(GameModele.player1.getX() + GameModele.player2.getX()) / 2,
						(GameModele.player1.getY() + GameModele.player2.getY()) / 2);
			}
		}

		if (GameModele.solo) {
			for (Entity entity : GameModele.entities) {
				if (entity.getY() >= GameModele.player1.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}

			if (!GameModele.onSea)
				GameModele.player1.getAvatar().paint(g, width, height);

			for (Entity entity : GameModele.entities) {
				if (entity.getY() < GameModele.player1.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
		} else { // Mode 2 joueurs
			PiratePlayer higher, lower;
			if (GameModele.player1.getY() < GameModele.player2.getY()) {
				higher = GameModele.player2;
				lower = GameModele.player1;
			} else {
				higher = GameModele.player1;
				lower = GameModele.player2;
			}
			for (Entity entity : GameModele.entities) {
				if (entity.getY() > higher.getY() && entity != higher && entity != lower) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!GameModele.onSea)
				lower.getAvatar().paint(g, width, height);

			for (Entity entity : GameModele.entities) {
				if (entity.getY() >= lower.getY() && entity.getY() <= higher.getY() && entity != higher
						&& entity != lower) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!GameModele.onSea)
				higher.getAvatar().paint(g, width, height);

			for (Entity entity : GameModele.entities) {
				if (entity.getY() < lower.getY() && entity != higher && entity != lower) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}

		}

		// ****************************************//
		// Components
		// ***************************************//

		if (GameModele.onSea) {
			barreVieMer.setPositionX(20);
			barreVieMer.setPositionY(15);
			barreVieMer.setWidth(150);
			barreVieMer.paint(g);

			barreVieTerre.setPositionX(20);
			barreVieTerre.setPositionY(50);
			barreVieTerre.setWidth(75);
			barreVieTerre.paint(g);
		} else {
			barreVieTerre.setPositionX(20);
			barreVieTerre.setPositionY(15);
			barreVieTerre.setWidth(150);
			barreVieTerre.paint(g);

			barreVieMer.setPositionX(20);
			barreVieMer.setPositionY(50);
			barreVieMer.setWidth(75);
			barreVieMer.paint(g);
		}

		cannonBallBox.paint(g);
		

		if (!GameModele.solo) {
			boxPlayer1 = new UIBoxes(50, 602);
			if (GameModele.player1.weapon.getName() == "Sword") {
				weaponPlayer1 = new UIBox(50, 602, 64, new UIImage(0, 0, "resources/img/Epee.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-1.png",1F));
			} else {
				weaponPlayer1 = new UIBox(50, 602, 64, new UIImage(0, 0, "resources/img/Scythe.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-1.png",1F));
			}
			boxPlayer1.addBox(weaponPlayer1);
			boxPlayer1.paint(g);
			boxPlayer2 = new UIBoxes(910, 602);
			if (GameModele.player2.weapon.getName() == "Sword") {
				weaponPlayer2 = new UIBox(910, 602, 64, new UIImage(0, 0, "resources/img/Epee.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-2.png",1F));
			} else {
				weaponPlayer2 = new UIBox(910, 602, 64, new UIImage(0, 0, "resources/img/Scythe.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-2.png",1F));
			}
			boxPlayer2.addBox(weaponPlayer2);
			boxPlayer2.paint(g);
		} else {
			boxPlayer1 = new UIBoxes(910, 602);
			if (GameModele.player1.weapon.getName() == "Sword") {
				weaponPlayer1 = new UIBox(910, 602, 64, new UIImage(0, 0, "resources/img/Epee.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-1.png",1F));
			} else {
				weaponPlayer1 = new UIBox(910, 602, 64, new UIImage(0, 0, "resources/img/Scythe.png", 1F), new UIImage(0,0,"resources/img/logo-pirate-1.png",1F));
			}
			boxPlayer1.addBox(weaponPlayer1);
			boxPlayer1.paint(g);
		}

		labelTimer.setText(GameModele.timer.toString());
		labelTimer.paint(g);

		if (Controller.getBuffer()[77]) { // Quand M push
			GameModele.map.getMiniMap().paint(g, width, height);
		}
		GameModele.map.getSectionTitle().paint(g, width, height);
	}

}
