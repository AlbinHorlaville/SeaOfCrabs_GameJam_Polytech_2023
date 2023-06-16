package info3.game.modele.StillEntityClass;

import info3.game.modele.StillEntity;

public class Rhum extends StillEntity {

	private int m_healthPoints;
	
	public Rhum(int healthPoints) {
		this.m_healthPoints = healthPoints;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}
	
	public void power() {
		//give heath to players
	}

}
