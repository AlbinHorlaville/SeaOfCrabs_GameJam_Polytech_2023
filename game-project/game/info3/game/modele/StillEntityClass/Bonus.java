package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;

public abstract class Bonus extends StillEntity{
	
	public final static int BonusesNumber = 5;
	public final float BONUS_APPLIED = 0.10f;
	protected boolean alive;
	protected MapSection m_mapSection;
	protected Bonus otherBonus;
	
	// Quand un bonus est appliqué, le joueur ajoute à la statistique correspondante le level du tronçon plus 10% de sa stat.
	
//	public Bonus(MapSection mapSection, int x, int y) {
//		this.m_mapSection = mapSection;
//		this.x = x;
//		this.y = y;
//		this.automate = AutomateLoader.findAutomate(GameEntity.Bonus);
//		this.current_state = automate.initial_state;
//		this.alive = true;
//	}
	
	public Bonus(MapSection mapSection) {
		this.m_mapSection = mapSection;
		this.automate = AutomateLoader.findAutomate(GameEntity.Bonus);
		this.current_state = automate.initial_state;
		this.alive = true;
	}

	public void setOtherBonus(Bonus bonus) {
		this.otherBonus = bonus;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	abstract public void power();
	
	public int getLevel() {
		return this.m_mapSection.getSectionNumber();
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) {
		if(d == EnumDirection.H && c == EnumCategory.T) {
			
			Map map = GameModele.map;
			BoatPlayer boatPlayer = GameModele.pirateBoat;
			Tiles bonusTile = map.getTileUnderEntity(this.x, this.y);
			Tiles boatTile = map.getTileUnderEntity(boatPlayer.x,boatPlayer.y );
			int X = bonusTile.getTileX();
			int Y = bonusTile.getTileY();
			Tiles[][] tiles = this.m_mapSection.getTiles();			
//			
//			if(myTile.equals(tempTile))
//				return true;
//			
//			if(myTile.equals(tiles[Y][X-1]))
//				return true;
//			
//			if(myTile.equals(tiles[Y][X+1]))
//				return true;
//			
//			if(myTile.equals(tiles[Y-1][X-1]))
//				return true;
//			
//			if(myTile.equals(tiles[Y][X+1]))
//				return true;
//			
//			
//			for(int i = 0; i < 3; i++)
//				if(myTile.equals(tiles[Y-1][X-1 + i]))
//					return true;
//			
//			for(int i = -1; i < 2; i++)
//				if(myTile.equals(tiles[Y][X + i]))
//					return true;
//			
//			for(int i = 0; i < 3; i++)
//				if(myTile.equals(tiles[Y+1][X-1 + i]))
//					return true;
			
			for(int i = -1; i < 2; i++)
				for(int j = -1; j < 2; j++)
					if(X > 0 && Y > 0)
						if(boatTile.equals(tiles[Y+j][X+i])) {
							return true;
						}
							
			
			
		}
		return false;
	}
	
	public boolean gotPower() {
		return this.alive;
	}
	
	public void die() {
		if(alive) {
			this.alive = false;
			otherBonus.alive = false;
			this.otherBonus.die();
		}
		super.die();
		
		
		
		
//		if(alive) {
//			for(Entity e: GameModele.entities) {
//				if(e instanceof Bonus) {
//					((Bonus) e).alive = false;
//				}
//			}
//		}
	}
}
