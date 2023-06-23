package info3.game.modele.StillEntityClass;

import java.util.ArrayList;
import java.util.Random;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.Crab;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.CrabslairAvatar;

public class CrabLair extends StillEntity {

	public static final int SCRAB_SPANWING_RANGE = 1500;
	public static final int DEFAULT_CRAB_NUMBER = 2;

	private int m_level;
	private int m_nbCrabsToEgg;
	private int m_nbCrabsAlive;
	private ArrayList<Tiles> m_crabLairTiles;
	private boolean m_isDead;
	private ArrayList<Tiles> m_tilesForCrabSpanwing;
	private MapSection m_section;

	public CrabLair(int level, MapSection m_section, int x, int y) {
		super(x, y);
		this.m_level = level;
		m_section.setCrabLair(this);
		this.m_nbCrabsToEgg = DEFAULT_CRAB_NUMBER + Level.getNbCrabsToEgg(level);
		this.m_nbCrabsAlive = this.m_nbCrabsToEgg;
		this.m_section = m_section;
		this.m_section.setCrabLair(this);
		this.automate = AutomateLoader.findAutomate(GameEntity.CrabsLair);
		this.current_state = automate.initial_state;
		this.avatar = new CrabslairAvatar(this);
		this.m_tilesForCrabSpanwing = null;
		this.m_isDead = false;
	}

	@Override
	public void move() {
		// System.out.println("AAAAAAAAAAAAAAAAAAAAAA : " + this.toString());
	}

	@Override
	public void die() {
		m_isDead = true;
		CrabslairAvatar avatar = (CrabslairAvatar) this.avatar;
		avatar.setDead();
	}

	public boolean gotStuff() {
		return this.m_nbCrabsToEgg > 0;
	}

	public boolean gotPower() {
		return this.m_nbCrabsAlive > 0;
	}

	public void aCrabDied() {
		this.m_nbCrabsAlive--;
		if (!this.gotPower())
			this.die();
	}

	public boolean closest() {
		/*
		 * double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) +
		 * Math.pow(this.y - GameModele.player1.y,2)); //double distanceP2 =
		 * Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y -
		 * GameModele.player2.y,2));
		 * 
		 * if(distanceP1 < SCRAB_SPANWING_RANGE ){//&& distanceP2 <
		 * SCRAB_SPANWING_RANGE) { return true; } return false;
		 */
		return !GameModele.onSea && GameModele.currentSection == GameModele.map.getSectionOfEntity(x, y);

	}

	public void egg() {
		if (this.m_crabLairTiles == null) {
			this.defineCrabLairTiles();
		}

		if (this.tick(this.timeElapsed) % 700 == 0) {
			if (this.m_tilesForCrabSpanwing == null) {
				this.m_tilesForCrabSpanwing = this.defineTilesWhreCrabsCouldSpawn();
			}
			int rand = new Random().nextInt(this.m_tilesForCrabSpanwing.size());
			Tiles tile = this.m_tilesForCrabSpanwing.get(rand);

			// Create crab and addint it to the List
			Crab crab = new Crab(this.m_level, this, tile.getX(), tile.getY());
			GameModele.entities.add(crab);
			this.m_nbCrabsToEgg--;
		}

	}

	private ArrayList<Tiles> defineTilesWhreCrabsCouldSpawn() {

		// get coordinates of the crabsLair (Top left hand corner)
		Tiles crabLairTile = GameModele.map.getTileUnderEntity(this.x, this.y);
		int initX = crabLairTile.getTileX();
		int initY = crabLairTile.getTileY();

		// adding all the tiles around the CrabsLair to an arrayList
		ArrayList<Tiles> tilesWhreCrabsCouldSpawn = new ArrayList<Tiles>();

		// All the tiles above the crabs lair
		for (int i = 0; i < 5; i++) {
			Tiles tile = m_section.getTiles()[initY - 1][initX - 1 + i];
			if (tile.isIsland()) {
				tilesWhreCrabsCouldSpawn.add(tile);
			}
		}

		// All the tiles below the crabs lair
		for (int i = 0; i < 5; i++) {
			Tiles tile = m_section.getTiles()[initY + 2][initX - 1 + i];
			if (tile.isIsland()) {
				tilesWhreCrabsCouldSpawn.add(tile);
			}
		}

		// All the tiles on the left the crabs lair
		for (int i = 0; i < 2; i++) {
			Tiles tile = m_section.getTiles()[initY - i][initX - 1];
			if (tile.isIsland()) {
				tilesWhreCrabsCouldSpawn.add(tile);
			}
		}

		// All the tiles on the left the crabs lair
		for (int i = 0; i < 2; i++) {
			Tiles tile = m_section.getTiles()[initY - i][initX + 3];
			if (tile.isIsland()) {
				tilesWhreCrabsCouldSpawn.add(tile);
			}
		}

		return tilesWhreCrabsCouldSpawn;

	}

	private void defineCrabLairTiles() {

		// get coordinates of the crabsLair (Top left hand corner)
		Tiles crabLairTile = GameModele.map.getTileUnderEntity(this.x, this.y);
		int initX = crabLairTile.getTileX();
		int initY = crabLairTile.getTileY();

		// adding all the tiles around the CrabsLair to an arrayList
		this.m_crabLairTiles = new ArrayList<Tiles>();

		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 6; i++) {
				Tiles tile = m_section.getTiles()[initY - 1 + j][initX - 1 + i];
				this.m_crabLairTiles.add(tile);
			}
		}
	}

	public ArrayList<Tiles> getCrabLairTiles() {
		return this.m_crabLairTiles;
	}

	public boolean isDead() {
		return m_isDead;
	}

	public void setDead(boolean m_isDead) {
		this.m_isDead = m_isDead;
	}

	public ArrayList<Tiles> getTilesForCrabSpanwing() {
		return m_tilesForCrabSpanwing;
	}

	public void setTilesForCrabSpanwing(ArrayList<Tiles> m_tilesForCrabSpanwing) {
		this.m_tilesForCrabSpanwing = m_tilesForCrabSpanwing;
	}

	public MapSection getSection() {
		return m_section;
	}

	public void setSection(MapSection m_section) {
		this.m_section = m_section;
	}

	public static int getScrabSpanwingRange() {
		return SCRAB_SPANWING_RANGE;
	}

	public int getLevel() {
		return m_level;
	}

	public void setLevel(int m_level) {
		this.m_level = m_level;
	}

	public int getNbCrabsToEgg() {
		return m_nbCrabsToEgg;
	}

	public void setNbCrabsToEgg(int m_nbCrabsToEgg) {
		this.m_nbCrabsToEgg = m_nbCrabsToEgg;
	}

	public int getNbCrabsAlive() {
		return m_nbCrabsAlive;
	}

	public void setNbCrabsAlive(int m_nbCrabsAlive) {
		this.m_nbCrabsAlive = m_nbCrabsAlive;
	}

}
