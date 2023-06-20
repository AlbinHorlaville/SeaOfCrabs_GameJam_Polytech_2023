package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.map.MapSection;

public class RedCross extends StillEntity {
	
	private MapSection m_section;
	
	public RedCross(MapSection section) {
		super();
		this.m_section = section;
		this.automate = AutomateLoader.findAutomate(GameEntity.RedCross);
		this.current_state = automate.initial_state;
	}

	@Override
	public void die() {
		super.die();

	}
	
	public void egg() {
		System.out.println("Je egg petit batard");
		GameModele.entities.add(new Treasure(null, null, this.x, this.y));
	}
	
	/*
	 */
	public boolean gotPower() {
		System.out.println(this.m_section.getCrabLair().isDead());
		return this.m_section.getCrabLair().isDead();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
