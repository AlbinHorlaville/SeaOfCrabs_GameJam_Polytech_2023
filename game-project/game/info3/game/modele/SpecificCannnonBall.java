package info3.game.modele;

public class SpecificCannnonBall extends CannonBall {
	int amount;
	public SpecificCannnonBall(int damage,int range, int rateOfFire) {
		this.damage = damage;
		this.range = range;
		this.rateOfFire = rateOfFire;
		this.amount = 1;
	}
	
	public void addBullet() {
		this.amount++;
	}
}
