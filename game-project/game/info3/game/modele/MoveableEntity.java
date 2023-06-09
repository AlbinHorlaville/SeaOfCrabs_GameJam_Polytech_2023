package info3.game.modele;

import info3.game.vue.view.PlayingView;

public abstract class MoveableEntity extends Entity{
	int lifePoint;
	int attackCoeff;
	int speedCoeff;
	
	public MoveableEntity(int lifePoint, int attackCoeff,int speedCoeff) {
		this.attackCoeff = attackCoeff;
		this.lifePoint = lifePoint;
		this.speedCoeff = speedCoeff;
	}
	
	public abstract void attack();
	
	public abstract void takeDamage();
	
	public void die() {
		PlayingView.deleteAvatar(this.avatar);
	}
}
