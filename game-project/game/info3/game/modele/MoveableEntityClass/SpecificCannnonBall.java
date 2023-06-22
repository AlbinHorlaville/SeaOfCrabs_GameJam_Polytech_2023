package info3.game.modele.MoveableEntityClass;

import automate.EnumCategory;
import automate.EnumDirection;

public class SpecificCannnonBall extends CannonBall {
	int amount;
	public SpecificCannnonBall(int damage,int range, int rateOfFire) {
		super(damage,range,rateOfFire);
		this.amount = 1;
	}
	
	public void addBullet() {
		this.amount++;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		
	}
}
