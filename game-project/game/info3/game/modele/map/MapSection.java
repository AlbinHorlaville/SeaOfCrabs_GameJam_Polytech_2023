package info3.game.modele.map;

import java.util.Random;

import info3.game.modele.CrabLair;
import info3.game.modele.RedCross;

/*
 * Contain the reprensation of a section of the map
 */
public class MapSection {
	private Tiles[][] tiles; // The section itself composed of tiles

	private int sectionWidth; // The width of the section
	private int sectionHeight; // The height of the section

	private PerlinNoiseGenerator noiseGenerator; // The perlin noise generator to proceduraly generate the island

	private EnumSectionType seaType; // The type of sea

	private Random randomGenerator; // The random generator initialized based on the seed of the map the section is
	// in

	private final static int NB_TILE_MIN_PER_ISLAND = 230; // The number of tiles an island must be composed to be valid

	private RedCross redCross;
	private CrabLair crabLair;

	/*
	 * @param seaType : The type of sea
	 * 
	 * @param sectionWidth and sectionHeight : The dimension of the section
	 * 
	 * @param rand : the random generator
	 * 
	 */
	public MapSection(EnumSectionType seaType, int sectionWidth, int sectionHeight, Random rand) throws Exception {

		this.noiseGenerator = new PerlinNoiseGenerator(0.05);
		this.randomGenerator = rand;

		this.seaType = seaType;

		this.sectionHeight = sectionHeight;
		this.sectionWidth = sectionWidth;

		this.tiles = new Tiles[this.sectionHeight][this.sectionWidth];

		initSection();

		switch (seaType) {
		case HARBOR:
			generateHarbor();
			break;
		case CALM_SEA:
			generateCalmSea();
			break;
		case STORMY_SEA:
			generateStormySea();
			break;
		case RAGING_SEA:
			generateRagingSea();
			break;
		case CRAB_KING_SEA:
			generateKingCrabSea();
			break;
		case KRAKEN_SEA:
			break;
		default:
			throw new Exception("Type de section inexistante");
		}

		if (seaType == EnumSectionType.CALM_SEA || seaType == EnumSectionType.STORMY_SEA
				|| seaType == EnumSectionType.RAGING_SEA || seaType == EnumSectionType.CRAB_KING_SEA) {
			addSandToGrassTransition();

			generateCrabsLair();

			generateFlower();
			generateRock();
			generateShellfish();
		}

	}

	/*
	 * Initialize the section with only water based on the seaType
	 */
	private void initSection() throws Exception {
		EnumTiles tilesType;

		switch (this.seaType) {
		case HARBOR:
		case CALM_SEA:
			tilesType = EnumTiles.CALM_WATER;
			break;
		case STORMY_SEA:
			tilesType = EnumTiles.STORMY_WATER;
			break;
		case RAGING_SEA:
			tilesType = EnumTiles.RAGING_WATER;
			break;
		case KRAKEN_SEA:
			tilesType = EnumTiles.KRAKEN_WATER;
			break;
		case CRAB_KING_SEA:
			tilesType = EnumTiles.RAGING_WATER;
			break;
		default:
			throw new Exception("Type de section inexistante");
		}

		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				tiles[i][j] = new Tiles(tilesType);
			}
		}
	}

	/*
	 * Generate a sea of the type calmSea, one island, only calm water.
	 */
	private void generateCalmSea() {
		int islandSize = Math.min(sectionWidth, sectionHeight);

		// Generation of perlin noise for the island
		double[][] tempPerlinNoiseIsland = new double[islandSize][islandSize];
		tempPerlinNoiseIsland = this.noiseGenerator.generateNoiseArray(islandSize, islandSize,
				this.randomGenerator.nextDouble() * 10000, this.randomGenerator.nextDouble() * 10000);

		// Substract a island gradient to the perlin noise
		tempPerlinNoiseIsland = generateIslandGradient(tempPerlinNoiseIsland, islandSize);

		// If the island is too small we generate a new one
		if (isIslandSurfaceBigEnough(tempPerlinNoiseIsland, islandSize)) {
			generateCalmSea();
		} else { // Otherwise, we insert the island in the section
			insertIslandInSection(tempPerlinNoiseIsland, islandSize);
		}
	}

	private void generateHarbor() {

		// Generation of perlin noise for the island
		double[][] harborSection = new double[this.sectionHeight][this.sectionWidth];

		harborSection = this.noiseGenerator.generateNoiseArray(this.sectionHeight, this.sectionWidth,
				this.randomGenerator.nextDouble() * 10000, this.randomGenerator.nextDouble() * 10000);

		harborSection = generateHarborGradient(harborSection);

		insertHarborInSection(harborSection);
	}

	private double[][] generateHarborGradient(double[][] harbor) {
		double value;
		for (int i = 0; i < 5; i++) {
			value = (5 - i) * 0.1;
			for (int j = 0; j < this.sectionWidth; j++) {
				harbor[this.sectionHeight - 1 - i][j] += value;
			}
		}

		for (int i = this.sectionHeight - 6; i >= 0; i--) {
			for (int j = 0; j < this.sectionWidth; j++) {
				harbor[i][j] -= 1;
			}
		}

		return harbor;
	}

	private void insertHarborInSection(double[][] harbor) {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if ((i == this.sectionHeight - 6 || i == this.sectionHeight - 7) && j == this.sectionWidth / 2) {
					this.tiles[i][j].setType(EnumTiles.PONTOON);
				} else if ((j == this.sectionWidth / 2 && i > this.sectionHeight - 6)
						|| (j == this.sectionWidth / 2 + 1 && i > this.sectionHeight - 5)
						|| (j == this.sectionWidth / 2 - 1 && i > this.sectionHeight - 5)) {
					this.tiles[i][j].setType(EnumTiles.SAND);
				} else if (harbor[i][j] == 0) {
					this.tiles[i][j].setType(EnumTiles.CALM_WATER);
				} else if (harbor[i][j] > 0) {
					this.tiles[i][j].setType(EnumTiles.SAND);
				}
			}
		}
	}

	/*
	 * Generate a sea of the type stormySea, one island, only stormy water.
	 */
	private void generateStormySea() {
		generateCalmSea();
		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER) {
					this.tiles[i][j].setType(EnumTiles.STORMY_WATER);
				}
			}
		}
	}

	/*
	 * Generate a sea of the type ragingSea, one island, only raging water.
	 */
	private void generateRagingSea() {
		generateCalmSea();
		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER) {
					this.tiles[i][j].setType(EnumTiles.RAGING_WATER);
				}
			}
		}
	}

	/*
	 * TODO
	 */
	private void generateKingCrabSea() {
		generateRagingSea();
	}

	/*
	 * Check if an island have enough surface to be in a section
	 * 
	 * @param island : An array representing an island (perlin noise -
	 * islandGradient)
	 * 
	 * @param islandSize : the size of the island
	 */
	private boolean isIslandSurfaceBigEnough(double[][] island, int islandSize) {
		int nbTileIsland = 0;
		for (int i = 0; i < islandSize && nbTileIsland < NB_TILE_MIN_PER_ISLAND; i++) {
			for (int j = 0; j < islandSize && nbTileIsland < NB_TILE_MIN_PER_ISLAND; j++) {
				if (island[i][j] * 255 >= 50) {
					nbTileIsland++;
				}
			}
		}

		return nbTileIsland < NB_TILE_MIN_PER_ISLAND;
	}

	/*
	 * Generate an island gradient and substract this gradient to a 2d perlin noise
	 * to create an island
	 * 
	 * @param island : An array of 2d perlin noise
	 * 
	 * @param islandSize : the size of the island
	 */
	private double[][] generateIslandGradient(double[][] island, int islandSize) {
		double maxValue = Math.sqrt(Math.pow(islandSize / 2, 2) + Math.pow(islandSize / 2, 2));

		for (int i = 0; i < islandSize; i++) {
			for (int j = 0; j < islandSize; j++) {
				double value = Math.sqrt(Math.pow(Math.max(i, islandSize / 2) - Math.min(i, islandSize / 2), 2)
						+ Math.pow(Math.max(j, islandSize / 2) - Math.min(j, islandSize / 2), 2));

				island[i][j] -= map(value, 0, maxValue, -1, 1); // The more the value is far from the center of the
																// array, the closer it is from 1
			}
		}

		return island;
	}

	private boolean oneSideGrass(Tiles tile) {
		return tile.getType() == EnumTiles.TRANSITION_GRASS_ON_LEFT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_ON_RIGHT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_UNDER_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_ON_TOP_OF_SAND;
	}

	private boolean twoSideGrass(Tiles tile) {
		return tile.getType() == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND
				|| tile.getType() == EnumTiles.TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND;
	}

	private void addSandToGrassTransition() {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (i > 0) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& this.tiles[i][j].getType() == EnumTiles.GRASS) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_SAND);
					}
				}
				if (i < this.sectionHeight - 1) {
					if ((this.tiles[i + 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& this.tiles[i][j].getType() == EnumTiles.GRASS) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_TOP_OF_SAND);
					}
				}
				if (j > 0) {
					if ((this.tiles[i][j - 1].getType() == EnumTiles.SAND
							|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& this.tiles[i][j].getType() == EnumTiles.GRASS) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_RIGHT_OF_SAND);
					}
				}

				if (j < this.sectionWidth - 1) {
					if ((this.tiles[i][j + 1].getType() == EnumTiles.SAND
							|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& this.tiles[i][j].getType() == EnumTiles.GRASS) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_LEFT_OF_SAND);
					}
				}

				if (i > 0 && j > 0) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j - 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND);
					}
				}

				if (i > 0 && j < this.sectionWidth - 1) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND);
					}
				}

				if (i < this.sectionHeight - 1 && j > 0) {
					if ((this.tiles[i + 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j - 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND);
					}
				}

				if (i < this.sectionHeight - 1 && j < this.sectionWidth - 1) {
					if ((this.tiles[i + 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND);
					}
				}

				if (i > 0 && i < this.sectionHeight - 1) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i + 1][j].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND);
					}
				}

				if (j > 0 && j < this.sectionWidth - 1) {
					if ((this.tiles[i][j - 1].getType() == EnumTiles.SAND
							|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND);
					}
				}

				if (j > 0 && j < this.sectionWidth - 1 && i > 0) {
					if ((this.tiles[i][j - 1].getType() == EnumTiles.SAND
							|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i - 1][j].getType() == EnumTiles.SAND
									|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j])
									|| twoSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_RIGHT_OF_SAND);
					}
				}

				if (j > 0 && j < this.sectionWidth - 1 && i < this.sectionHeight - 1) {
					if ((this.tiles[i][j - 1].getType() == EnumTiles.SAND
							|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i + 1][j].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j])
									|| twoSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ON_RIGHT_AND_ON_LEFT_AND_ON_TOP_OF_SAND);
					}
				}

				if (i > 0 && j < this.sectionWidth - 1 && i < this.sectionHeight - 1) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j + 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i + 1][j].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j])
									|| twoSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_AND_ON_TOP_OF_SAND);
					}
				}

				if (i > 0 && j > 0 && i < this.sectionHeight - 1) {
					if ((this.tiles[i - 1][j].getType() == EnumTiles.SAND
							|| this.tiles[i - 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j - 1].getType() == EnumTiles.SAND
									|| this.tiles[i][j - 1].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i + 1][j].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j].getType() == EnumTiles.SAND_WATER)
							&& (this.tiles[i][j].getType() == EnumTiles.GRASS || oneSideGrass(this.tiles[i][j])
									|| twoSideGrass(this.tiles[i][j]))) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_TOP_OF_SAND);
					}
				}

				if (i > 0 && j > 0 && i < this.sectionHeight - 1 && j < this.sectionWidth - 1) {
					if (this.tiles[i][j].getType() == EnumTiles.GRASS
							&& (this.tiles[i - 1][j - 1].getType() == EnumTiles.SAND
									|| this.tiles[i - 1][j - 1].getType() == EnumTiles.SAND_WATER)) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_LEFT);
					} else if (this.tiles[i][j].getType() == EnumTiles.GRASS
							&& (this.tiles[i + 1][j - 1].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j - 1].getType() == EnumTiles.SAND_WATER)) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_RIGHT);
					} else if (this.tiles[i][j].getType() == EnumTiles.GRASS
							&& (this.tiles[i + 1][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i + 1][j + 1].getType() == EnumTiles.SAND_WATER)) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_RIGHT);
					} else if (this.tiles[i][j].getType() == EnumTiles.GRASS
							&& (this.tiles[i - 1][j + 1].getType() == EnumTiles.SAND
									|| this.tiles[i - 1][j + 1].getType() == EnumTiles.SAND_WATER)) {
						this.tiles[i][j].setType(EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_LEFT);
					}
				}
			}
		}
	}

	/*
	 * Insert an island in the section map
	 * 
	 * @param island : the island, a perlin noise tab with the island gradient
	 * 
	 * @param islandSize : the side dimension of the island
	 */
	private void insertIslandInSection(double[][] island, int islandSize) {
		int offsetX = 0;
		int offsetY = 0;
		if (this.sectionWidth == islandSize) {
			offsetY = this.randomGenerator.nextInt((int) Math.floor((this.sectionHeight - islandSize) / 2));
		} else {
			offsetX = this.randomGenerator.nextInt((int) Math.floor((this.sectionWidth - islandSize) / 2));
		}
		for (int i = 0; i < islandSize; i++) {
			for (int j = 0; j < islandSize; j++) {
				if (island[i][j] * 255 < 0) {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.CALM_WATER);
				} else if (island[i][j] * 255 < 30) {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.SAND_WATER);
				} else if (island[i][j] * 255 < 50) {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.SAND);
				} else {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.GRASS);
				}
			}
		}
	}

	/*
	 * Map a value in a range to another range
	 * 
	 * @param value : The value in the initialRange to transform into a value of the
	 * transformedRange
	 * 
	 * @param initialRangeMin and initialRangeMax : the range of the initial value
	 * 
	 * @param transformedRangeMin and transformedRangeMax : the range of the
	 * transformed value
	 */
	private double map(double value, double initialRangeMin, double initialRangeMax, double transformedRangeMin,
			double transformedRangeMax) {
		return transformedRangeMin + ((transformedRangeMax - transformedRangeMin) / (initialRangeMax - initialRangeMin))
				* (value - initialRangeMin);
	}

	private void generateCrabsLair() {
		int rand;
		boolean added = false;
		for (int i = 0; i < this.sectionHeight - 1 && !added; i++) {
			for (int j = 0; j < this.sectionWidth - 2 && !added; j++) {
				if (!added && this.tiles[i][j].getType() == EnumTiles.GRASS
						&& this.tiles[i][j + 1].getType() == EnumTiles.GRASS
						&& this.tiles[i][j + 2].getType() == EnumTiles.GRASS
						&& this.tiles[i + 1][j].getType() == EnumTiles.GRASS
						&& this.tiles[i + 1][j + 1].getType() == EnumTiles.GRASS
						&& this.tiles[i + 1][j + 2].getType() == EnumTiles.GRASS) {
					rand = this.randomGenerator.nextInt(100);
					if (rand == 50) {
						this.tiles[i][j].setType(EnumTiles.CRAB_SPAWNER);
						this.tiles[i][j + 1].setType(EnumTiles.CRAB_SPAWNER);
						this.tiles[i][j + 2].setType(EnumTiles.CRAB_SPAWNER);
						this.tiles[i + 1][j].setType(EnumTiles.CRAB_SPAWNER);
						this.tiles[i + 1][j + 1].setType(EnumTiles.CRAB_SPAWNER);
						this.tiles[i + 1][j + 2].setType(EnumTiles.CRAB_SPAWNER);
						this.crabLair = new CrabLair(10, 1, 10, this);
						added = true;
					}
				}
			}
		}

		if (!added) {
			generateCrabsLair();
		}
	}

	public void generateRedCross() {
		int rand;
		boolean added = false;
		for (int i = 0; i < this.sectionHeight && !added; i++) {
			for (int j = 0; j < this.sectionWidth && !added; j++) {
				if (!added && this.tiles[i][j].getType() == EnumTiles.SAND) {
					rand = this.randomGenerator.nextInt(300);
					if (rand == 150) {
						this.tiles[i][j].setType(EnumTiles.TREASUR);
						this.redCross = new RedCross(this);
						added = true;
					}
				}
			}
		}
		if (!added) {
			generateRedCross();
		}
	}

	public void generateFlower() {
		int rand;
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.GRASS) {
					rand = this.randomGenerator.nextInt(12);
					if (rand == 5) {
						rand = this.randomGenerator.nextInt(3);
						switch (rand) {
						case 1:
							this.tiles[i][j].setType(EnumTiles.YELLOW_FLOWER);
							break;
						case 2:
							this.tiles[i][j].setType(EnumTiles.BLUE_FLOWER);
							break;
						default:
							this.tiles[i][j].setType(EnumTiles.RED_FLOWER);
							break;
						}

					}
				}
			}
		}
	}
	
	public void generateRock() {
		int rand;
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.GRASS) {
					rand = this.randomGenerator.nextInt(12);
					if (rand == 5) {
						rand = this.randomGenerator.nextInt(2);
						switch (rand) {
						case 1:
							this.tiles[i][j].setType(EnumTiles.GRASS_WITH_ROCK_1);
							break;
						default:
							this.tiles[i][j].setType(EnumTiles.GRASS_WITH_ROCK_2);
							break;
						}

					}
				}
			}
		}
	}
	
	public void generateShellfish() {
		int rand;
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.SAND) {
					rand = this.randomGenerator.nextInt(5);
					if (rand == 3) {
						rand = this.randomGenerator.nextInt(3);
						switch (rand) {
						case 1:
							this.tiles[i][j].setType(EnumTiles.SHELLFISH_1);
							break;
						case 2:
							this.tiles[i][j].setType(EnumTiles.SHELLFISH_2);
							break;
						default:
							this.tiles[i][j].setType(EnumTiles.SHELLFISH_3);
							break;
						}

					}
				}
			}
		}
	}

	/*
	 * Print the section, usefull for debugging
	 */
	public void printSection() {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				this.tiles[i][j].printTile();
			}
			System.out.print("\n");
		}
	}

	public int getSectionWidth() {
		return sectionWidth;
	}

	public void setSectionWidth(int sectionWidth) {
		this.sectionWidth = sectionWidth;
	}

	public int getSectionHeight() {
		return sectionHeight;
	}

	public void setSectionHeight(int sectionHeight) {
		this.sectionHeight = sectionHeight;
	}

	public EnumSectionType getSeaType() {
		return seaType;
	}

	public void setSeaType(EnumSectionType seaType) {
		this.seaType = seaType;
	}

	public Tiles[][] getTiles() {
		return this.tiles;
	}

	public RedCross getRedCross() {
		return this.redCross;
	}

	public CrabLair getCrabLair() {
		return this.crabLair;
	}
}
