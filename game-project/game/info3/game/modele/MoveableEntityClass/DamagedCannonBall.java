package info3.game.modele.MoveableEntityClass;

import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.GameView;
import info3.game.vue.avatar.DamagedCannonBallAvatar;

public class DamagedCannonBall extends CannonBall {
	
	static final int DAMAGED_DAMAGE = 50; // A modifier
	
	public DamagedCannonBall() {
		super(DAMAGED_DAMAGE, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new DamagedCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
	}

	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).takeDamage(damage);
		}
		
		if (ennemyAimed instanceof Tentacle) {
			((Tentacle) ennemyAimed).takeDamage(damage);
		}
		
		if (ennemyAimed instanceof SeaTreasure ) {
			((SeaTreasure) ennemyAimed).takeDamage(damage);
		}
	}
	
	public void tripleShot(int mouseX, int mouseY,BoatPlayer boat) {
		DamagedCannonBall a = new DamagedCannonBall();
		DamagedCannonBall c = new DamagedCannonBall();
		double theta = Math.atan2((mouseY-GameView.screenHeight/2),((mouseX-GameView.screenWidth / 2)));
		double r = Math.sqrt(Math.pow((mouseX-GameView.screenWidth/2), 2)+Math.pow((mouseY-GameView.screenHeight/2),2));
		//System.out.println(""+theta);
		//System.out.println(""+r);
		
		System.out.println(""+(mouseX-GameView.screenWidth/2));
		System.out.println(""+mouseX);
		//System.out.println(""+(int)(r*Math.cos(theta+Math.PI/8)));
		//System.out.println(""+(mouseY-GameView.screenHeight/2));
		//System.out.println(""+(x-GameView.screenWidth/2+r*Math.cos(theta-Math.PI/4)));
		
		
		a.setPositions(boat.x, boat.y, (int)(r*Math.cos(theta+Math.PI/8)),(int)(r*Math.sin(theta+Math.PI/8)));
		
		this.setPositions(boat.x, boat.y, mouseX-GameView.screenWidth/2, mouseY -GameView.screenHeight/2);
		
		c.setPositions(boat.x, boat.y, (int)(r*Math.cos(theta-Math.PI/8)),(int)(r*Math.sin(theta-Math.PI/8)));
		
		a.fire();
		this.fire();
		c.fire();
		
		return;
	}
}