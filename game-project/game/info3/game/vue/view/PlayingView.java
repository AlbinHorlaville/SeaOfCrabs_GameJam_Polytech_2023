package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.Controller;
import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Scythe;
import info3.game.modele.MoveableEntityClass.Sword;
import info3.game.modele.StillEntityClass.CloudCluster;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIBarrePointDeVie;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIImage;

public class PlayingView extends View {

	UIBarrePointDeVie life;
	UIBoxes weapons;
	UIBox boxSword, boxScythe;

	int k;

	public PlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		life = new UIBarrePointDeVie();

		weapons = new UIBoxes((windowWidth - 100) / 2, windowHeight - 114);
		boxSword = new UIBox(64, Sword.getInstance(), new UIImage(0, 0, "resources/img/Sword.png", 1F));
		boxScythe = new UIBox(64, Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 1F));
		weapons.addBox(boxSword);
		weapons.addBox(boxScythe);

		k = 0;

		addComponent(weapons);
	}

	@Override
	public void tick(long elapsed) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().tick(elapsed);
		}
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
						(GameModele.player1.getX() + width + GameModele.player2.getX()) / 2,
						(GameModele.player1.getY() + height + GameModele.player2.getY()) / 2);
			}
		}
		
		if (GameModele.solo) {
			for (Entity entity : GameModele.entities) {
				if (entity.getY()>=GameModele.player1.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster)entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					}
					else{
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			
			if (!GameModele.onSea)
				GameModele.player1.getAvatar().paint(g, width, height);
			
			for (Entity entity : GameModele.entities) {
				if (entity.getY()<GameModele.player1.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster)entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					}
					else{
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
		}else { // Mode 2 joueurs
			PiratePlayer higher, lower;
			if (GameModele.player1.getY() >= GameModele.player2.getY()) {
				higher = GameModele.player2;
				lower = GameModele.player1;
			}else {
				higher = GameModele.player1;
				lower = GameModele.player2;
			}
			for (Entity entity : GameModele.entities) {
				if (entity.getY()>higher.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster)entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					}
					else{
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!GameModele.onSea)
				higher.getAvatar().paint(g, width, height);
			
			for (Entity entity : GameModele.entities) {
				if (entity.getY()>= lower.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster)entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					}
					else{
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			if (!GameModele.onSea)
				lower.getAvatar().paint(g, width, height);
			
			for (Entity entity : GameModele.entities) {
				if (entity.getY()<lower.getY()) {
					if (entity instanceof CloudCluster) {
						for (Entity cloud : ((CloudCluster)entity).getClouds()) {
							cloud.getAvatar().paint(g, width, height);
						}
					}
					else{
						entity.getAvatar().paint(g, width, height);
					}
				}
			}
			
			
		}
		
		life.paint(g);
		weapons.paint(g);

		if (Controller.getBuffer()[77]) { // Quand M push
			GameModele.map.getMiniMap().paint(g, width, height);
		}
	}

}
