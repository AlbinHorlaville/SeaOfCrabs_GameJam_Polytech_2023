package info3.game.modele.MoveableEntityClass;

import info3.game.modele.MoveableEntity;

public abstract class Ennemy extends MoveableEntity {
//	
//	protected int level;		//To delete
//	
//	public Ennemy(int healthPoint, int damage, int level) { // constrctor to delete
//		super(healthPoint, damage);
//		this.level = level;
//	}

	public Ennemy(int lifePoint, int damage, int hitbox) { // good constructor
		super(lifePoint, damage, hitbox);
	}

	public Ennemy(int lifePoint, int damage) { // good constructor
		super(lifePoint, damage);
	}

	public Ennemy(int lifePoint, int damage, int x, int y) { // good constructor
		super(lifePoint, damage, x, y);
	}

	public Ennemy(int lifePoint, int damage, int hitbox, int x, int y) { // good constructor
		super(lifePoint, damage, x, y, hitbox);
	}

}
