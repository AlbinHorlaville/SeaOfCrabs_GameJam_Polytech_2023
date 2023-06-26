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
import info3.game.modele.MoveableEntityClass.EnumCannonBall;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.BasicCannonBall;
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
	UIBox boxBoulet1, boxBoulet2, boxBoulet3, boxBoulet4;

	UIBox weaponPlayer1, weaponPlayer2;

	private boolean playerOnSea;

	public UILabel attackSpeedBonusLabel, damageBonusLabel, healthBonusLabel, rangeBonusLabel, speedBonusLabel;
	UIImage attackSpeedBonusImage, damageBonusImage, healthBonusImage, rangeBonusImage, speedBonusImage;

	public PlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		GameModele.timer = new GameTimer();

		barreVieTerre = new UIBarreVieTerre(0, 0);
		barreVieMer = new UIBarreVieMer(0, 0);
		labelTimer = new UILabel(windowWidth / 2, 35, "0'", FONT3, Color.black);

		cannonBallBox = new UIBoxes((windowWidth) / 2 - 130, windowHeight - 114);
		/*
		 * boxSword = new UIBox(64, new Sword(), new UIImage(0, 0,
		 * "resources/img/Epee.png", 1F)); boxScythe = new UIBox(64,
		 * Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 1F));
		 * cannonBallBox.addBox(boxSword); cannonBallBox.addBox(boxScythe);
		 */

		boxBoulet1 = new UIBox(64, EnumCannonBall.Basic,
				new UIImage(0, 0, SpriteLoader.get(SpriteType.CannonBall)[0], 2F));
		boxBoulet2 = new UIBox(64, EnumCannonBall.Stunt,
				new UIImage(0, 0, SpriteLoader.get(SpriteType.CannonBall)[2], 2F));
		boxBoulet3 = new UIBox(64, EnumCannonBall.KrakenSlayer,
				new UIImage(0, 0, SpriteLoader.get(SpriteType.CannonBall)[4], 2F));
		boxBoulet4 = new UIBox(64, EnumCannonBall.Damaged,
				new UIImage(0, 0, SpriteLoader.get(SpriteType.CannonBall)[1], 2F));

		cannonBallBox.addBox(boxBoulet1);
		cannonBallBox.addBox(boxBoulet2);
		cannonBallBox.addBox(boxBoulet3);
		cannonBallBox.addBox(boxBoulet4);

		attackSpeedBonusLabel = new UILabel(windowWidth - 375 + 30, 20, "0'", FONT4, Color.black);
		damageBonusLabel = new UILabel(windowWidth - 300 + 30, 20, "0'", FONT4, Color.black);
		healthBonusLabel = new UILabel(windowWidth - 225 + 30, 20, "0'", FONT4, Color.black);
		rangeBonusLabel = new UILabel(windowWidth - 150 + 30, 20, "0'", FONT4, Color.black);
		speedBonusLabel = new UILabel(windowWidth - 75 + 30, 20, "0'", FONT4, Color.black);

		attackSpeedBonusImage = new UIImage(windowWidth - 375, 15, SpriteLoader.get(SpriteType.Bonus)[2], 2F);
		damageBonusImage = new UIImage(windowWidth - 300, 15, SpriteLoader.get(SpriteType.Bonus)[0], 2F);
		healthBonusImage = new UIImage(windowWidth - 225, 15, SpriteLoader.get(SpriteType.Bonus)[3], 2F);
		rangeBonusImage = new UIImage(windowWidth - 150, 15, SpriteLoader.get(SpriteType.Bonus)[5], 2F);
		speedBonusImage = new UIImage(windowWidth - 75, 15, SpriteLoader.get(SpriteType.Bonus)[4], 2F);

		addComponent(cannonBallBox);

		attackSpeedBonusLabel.setText(Float.toString(1f));
		damageBonusLabel.setText(Float.toString(1f));
		healthBonusLabel.setText(Float.toString(1f));
		rangeBonusLabel.setText(Float.toString(1f));
		speedBonusLabel.setText(Float.toString(1f));
		
		barreVieMer.setPositionX(20);
		barreVieMer.setPositionY(15);
		barreVieMer.setWidth(150);

		barreVieTerre.setPositionX(20);
		barreVieTerre.setPositionY(50);
		barreVieTerre.setWidth(75);

		playerOnSea = true;
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
		// g.setColor(Color.gray);
		// g.fillRect(0, 0, width, height);
		
		boolean onSea = GameModele.onSea;
		boolean solo = GameModele.solo;
		
		int player1Y = GameModele.player1.getY();
		int player2Y = 0;
		if (!solo) {
			player2Y = GameModele.player2.getY();
		}

		if (onSea || solo) {
			GameModele.map.getRepresentation().paint(g, width, height, GameModele.getCurrentPlayerX(),
					GameModele.getCurrentPlayerY());
		} else {
			GameModele.map.getRepresentation().paint(g, width, height,
					(GameModele.player1.getX() + GameModele.player2.getX()) / 2,
					(player1Y + player2Y) / 2);
		}

		if (solo) {
			// Paint all entity behind player
			for (Entity entity : GameModele.entities) {
				if (entity.getY() >= player1Y) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}

			// Paint player
			if (!onSea)
				GameModele.player1.getAvatar().paint(g, width, height);

			// Paint all entity in front of the player
			for (Entity entity : GameModele.entities) {
				if (entity.getY() < player1Y) {
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
			int higherY;
			int lowerY;
			
			if (player1Y < player2Y) {
				higher = GameModele.player2;
				lower = GameModele.player1;
				higherY = player2Y;
				lowerY = player1Y;
			} else {
				higher = GameModele.player1;
				lower = GameModele.player2;
				higherY = player1Y;
				lowerY = player2Y;
			}

			for (Entity entity : GameModele.entities) {
				if (entity.getY() > higherY && entity != higher && entity != lower) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!onSea)
				lower.getAvatar().paint(g, width, height);
			
			for (Entity entity : GameModele.entities) {
				if (entity.getY() >= lowerY && entity.getY() <= higherY && entity != higher && entity != lower) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster) entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					} else {
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!onSea)
				higher.getAvatar().paint(g, width, height);

			for (Entity entity : GameModele.entities) {
				if (entity.getY() < lowerY && entity != higher && entity != lower) {
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

		if (playerOnSea != onSea) {
			playerOnSea = onSea;
			if (playerOnSea) {
				barreVieMer.setPositionX(20);
				barreVieMer.setPositionY(15);
				barreVieMer.setWidth(150);

				barreVieTerre.setPositionX(20);
				barreVieTerre.setPositionY(50);
				barreVieTerre.setWidth(75);
			} else {
				barreVieTerre.setPositionX(20);
				barreVieTerre.setPositionY(15);
				barreVieTerre.setWidth(150);

				barreVieMer.setPositionX(20);
				barreVieMer.setPositionY(50);
				barreVieMer.setWidth(75);
			}

		}
		barreVieMer.paint(g);
		barreVieTerre.paint(g);

		switch (GameModele.pirateBoat.getBall()) {
		case Basic:
			cannonBallBox.setSelectedBox(boxBoulet1);
			break;
		case Stunt:
			cannonBallBox.setSelectedBox(boxBoulet2);
			break;
		case KrakenSlayer:
			cannonBallBox.setSelectedBox(boxBoulet3);
			break;
		default:
			cannonBallBox.setSelectedBox(boxBoulet4);
			break;
		}

		cannonBallBox.paint(g);

		boxPlayer1 = new UIBoxes(50, 602);
		weaponPlayer1 = new UIBox(50, 602, 64, new UIImage(0, 0, "resources/img/Epee.png", 1F),
				new UIImage(0, 0, "resources/img/logo-pirate-1.png", 1F));
		boxPlayer1.addBox(weaponPlayer1);
		boxPlayer1.paint(g);

		if (!GameModele.solo) {
			boxPlayer2 = new UIBoxes(910, 602);
			weaponPlayer2 = new UIBox(910, 602, 64, new UIImage(0, 0, "resources/img/Epee.png", 1F),
					new UIImage(0, 0, "resources/img/logo-pirate-2.png", 1F));
			boxPlayer2.addBox(weaponPlayer2);
			boxPlayer2.paint(g);
		}

		labelTimer.setText(GameModele.timer.toString());
		labelTimer.paint(g);

		if (Controller.getBuffer().isBuffered("m")) { // Quand M push
			GameModele.map.getMiniMap().paint(g, width, height);
		}
		GameModele.map.getSectionTitle().paint(g, width, height);

		// *****************************************//
		// Bonus
		// ****************************************//

		attackSpeedBonusImage.paint(g);
		damageBonusImage.paint(g);
		healthBonusImage.paint(g);
		rangeBonusImage.paint(g);
		speedBonusImage.paint(g);

		attackSpeedBonusLabel.paint(g);
		damageBonusLabel.paint(g);
		healthBonusLabel.paint(g);
		rangeBonusLabel.paint(g);
		speedBonusLabel.paint(g);
	}
}
