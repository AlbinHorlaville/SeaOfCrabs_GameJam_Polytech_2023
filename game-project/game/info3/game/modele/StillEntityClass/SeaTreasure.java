package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.bonus.AttackSpeedbonus;
import info3.game.modele.bonus.DamageBonus;
import info3.game.modele.bonus.HealthBonus;
import info3.game.modele.bonus.RangeBonus;
import info3.game.modele.bonus.SpeedBonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.avatar.SeaTreasureAvatar;

public class SeaTreasure extends StillEntity{
	
	private Bonus m_bonusLeft;
	private Bonus m_bonusRight;
	private MapSection m_mapSection;
	private boolean m_opened;

	
	public SeaTreasure(MapSection mapSection, int x, int y) {
		super(x, y);
		this.m_mapSection = mapSection;
		this.automate = AutomateLoader.findAutomate(GameEntity.SeaTreasure);
		this.current_state = automate.initial_state;
		this.avatar = new SeaTreasureAvatar(this);
		this.m_opened = false;
	}
	
	@Override
	public void die() {
		super.die();

	}
	
	private void setBonuses() {
		
		int rand = GameModele.map.getRand().nextInt(Bonus.BonusesNumber);
		this.m_bonusLeft = this.getBonusBasedOnNumber(rand);
		rand = GameModele.map.getRand().nextInt(Bonus.BonusesNumber);
		this.m_bonusRight = this.getBonusBasedOnNumber(rand);
		
		while(this.m_bonusLeft.equals(this.m_bonusRight)) {
			rand = GameModele.map.getRand().nextInt(Bonus.BonusesNumber);
			this.m_bonusRight = this.getBonusBasedOnNumber(rand);
		}
		
		this.m_bonusLeft.setLocation(this.x - 200, this.y);
		this.m_bonusLeft.setOtherBonus(m_bonusRight);
		this.m_bonusRight.setLocation(this.x + 200, this.y);
		this.m_bonusRight.setOtherBonus(m_bonusLeft);
	}
	
//	private boolean areThesameBonuses() {
//		if(this.m_bonusRight instanceof AttackSpeedbonus && this.m_bonusLeft instanceof AttackSpeedbonus ||
//				this.m_bonusRight instanceof DamageBonus && this.m_bonusLeft instanceof DamageBonus ||
//					this.m_bonusRight instanceof HealthBonus && this.m_bonusLeft instanceof HealthBonus ||
//						this.m_bonusRight instanceof RangeBonus && this.m_bonusLeft instanceof RangeBonus ||
//							this.m_bonusRight instanceof SpeedBonus && this.m_bonusLeft instanceof SpeedBonus) {
//			
//		}
//	}
	
	public Bonus getBonusBasedOnNumber(int rand) {
		switch (rand) {
		case 0:
			return new HealthBonus(this.m_mapSection);
		case 1:
			return new AttackSpeedbonus(this.m_mapSection);
		case 2:
			return new DamageBonus(this.m_mapSection);
		case 3:
			return new RangeBonus(this.m_mapSection);
		case 4:
			return new SpeedBonus(this.m_mapSection);
		default:
			return null;
		}
	}
	
//	public boolean cell(EnumDirection d, EnumCategory c) {
//		
//
//		if(d == EnumDirection.H && c == EnumCategory.T) {
//			Map map = GameModele.map;
//			BoatPlayer pirateBoat = GameModele.pirateBoat;
//			Tiles myTile = map.getTileUnderEntity(this.x, this.y);
//			
////			System.out.println("Coord Coeffre : "+this.x + "    " +this.y);
////			System.out.println("Coord Player : "+pirateBoat.x + "    " +pirateBoat.y);
//			
//			if(myTile.equals(map.getTileUnderEntity(pirateBoat.x,pirateBoat.y ))) {
//				System.out.println("Je suis dessus");
//				return true;
//			}
//			
//		}
//		return false;
//	}
	

	
	public void egg() {
		
		this.setBonuses();
		GameModele.entities.add(m_bonusLeft);
		GameModele.entities.add(m_bonusRight);
	}
	
	public boolean gotPower() {
		return !this.m_opened;
	}
	
	public void takeDamage(int damage) {
		this.m_opened = true;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	

}
