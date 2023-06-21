package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.Entity;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.bonus.HealthBonus;
import info3.game.modele.map.Map;
import info3.game.modele.map.Tiles;

public abstract class Bonus extends StillEntity{
	
	public final static int BonusesNumber = 5;
	protected int _level;
	public final float BONUS_APPLIED = 0.10f;
	protected boolean otherBonusNotYetTaken;
	
	// Quand un bonus est appliqué, le joueur ajoute à la statistique correspondante le level du tronçon plus 10% de sa stat.
	
	public Bonus(int level, int x, int y) {
		_level = level;
		this.x = x;
		this.y = y;
		this.automate = AutomateLoader.findAutomate(GameEntity.Bonus);
		this.current_state = automate.initial_state;
		this.otherBonusNotYetTaken = false;
	}
	
	public Bonus(int level) {
		_level = level;
		this.automate = AutomateLoader.findAutomate(GameEntity.Bonus);
		this.current_state = automate.initial_state;
		this.otherBonusNotYetTaken = false;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	abstract public void power();
	
	public int getLevel() {
		return _level;
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) {
		if(d == EnumDirection.H && c == EnumCategory.T) {
			
			Map map = GameModele.map;
			BoatPlayer boatPlayer = GameModele.pirateBoat;
			Tiles myTile = map.getTileUnderEntity(this.x, this.y);
			
			if(myTile.equals(map.getTileUnderEntity(boatPlayer.x,boatPlayer.y )))
				return true;
			
		}
		return false;
	}
	
	public boolean gotPower() {
		return this.otherBonusNotYetTaken;
	}
	
	public void die() {
		super.die();
		for(Entity e: GameModele.entities) {
			if(e instanceof Bonus) {
				((Bonus) e).otherBonusNotYetTaken = true;
			}
		}
		
	}
}
