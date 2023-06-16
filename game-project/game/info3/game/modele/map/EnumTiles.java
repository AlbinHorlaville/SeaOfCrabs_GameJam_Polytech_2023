package info3.game.modele.map;

/*
 * Enumeration of every different type of tiles
 */
public enum EnumTiles {
	CALM_WATER, STORMY_WATER, RAGING_WATER, KRAKEN_WATER, SAND_WATER, SAND, GRASS, TRANSITION_GRASS_UNDER_SAND,
	TRANSITION_GRASS_ON_TOP_OF_SAND, TRANSITION_GRASS_ON_LEFT_OF_SAND, TRANSITION_GRASS_ON_RIGHT_OF_SAND,
	TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND, TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND,
	TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND, TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND,
	TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND, TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND,
	TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_TOP_OF_SAND, TRANSITION_GRASS_UNDER_AND_ON_RIGHT_AND_ON_TOP_OF_SAND,
	TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_RIGHT_OF_SAND, TRANSITION_GRASS_ON_RIGHT_AND_ON_LEFT_AND_ON_TOP_OF_SAND,
	TRANSITION_GRASS_ANGLE_SAND_TOP_LEFT, TRANSITION_GRASS_ANGLE_SAND_TOP_RIGHT,
	TRANSITION_GRASS_ANGLE_SAND_BOTTOM_LEFT, TRANSITION_GRASS_ANGLE_SAND_BOTTOM_RIGHT, TREASUR, CRAB_SPAWNER, TREE,
	SEA_CHEST, RED_FLOWER, YELLOW_FLOWER, BLUE_FLOWER, SHELLFISH_1, SHELLFISH_2, SHELLFISH_3, PONTOON, GRASS_WITH_ROCK_1, GRASS_WITH_ROCK_2;
	
	//water tile : CALM_SATER, STORMY_WATER, RARGING_WATER
	//sand tile : SAND_WATER, SAND, SHELLFISH_1, SHELLFISH_2, SHELLFISH_3
	// other : grass / transition / special tiles
}
