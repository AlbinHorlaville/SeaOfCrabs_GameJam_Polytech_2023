package info3.game.modele.StillEntityClass;

import info3.game.modele.StillEntity;

public class Treasure extends StillEntity {
	
	//Type Object à modifier lorsque la classe bonus/malus sera crée
	 
	protected Object bonus;
	protected Object malus; 

	public Treasure(Object bonus, Object malus) {
		super();
		this.bonus = bonus;
		this.malus = malus;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	/*
	 * @return bonus ou malus en fonction du choix du joueur
	 */
	public Object open() {
		// TODO
		return bonus;

	}

	public Object getBonus() {
		return this.bonus;
	}

	public Object getMalus() {
		return this.malus;
	}

}
