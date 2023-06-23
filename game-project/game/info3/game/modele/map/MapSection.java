package info3.game.modele.map;

import java.util.Random;

import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.modele.StillEntityClass.RedCross;

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

	private final static int NB_TILE_MIN_PER_ISLAND = 250; // The number of tiles an island must be composed to be valid

	private RedCross redCross;
	private CrabLair crabLair;

	private int sectionNumber;

	/*
	 * @param seaType : The type of sea
	 * 
	 * @param sectionWidth and sectionHeight : The dimension of the section
	 * 
	 * @param rand : the random generator
	 * 
	 */
	public MapSection(EnumSectionType seaType, int sectionWidth, int sectionHeight, Random rand, int sectionNumber)
			throws Exception {

		this.noiseGenerator = new PerlinNoiseGenerator(0.05);
		this.randomGenerator = rand;

		this.seaType = seaType;

		this.sectionNumber = sectionNumber;

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
		case CALM_SEA_TO_STORMY_SEA:
			generateCalmSea();
			addTransitionCalmToStormy();
			break;
		case STORMY_SEA_FROM_CALM_SEA:
			generateStormySea();
			addTransitionStormyFromCalm();
			break;
		case STORMY_SEA_TO_RAGING_SEA:
			generateStormySea();
			addTransitionStormyToRaging();
			break;
		case STORMY_SEA:
			generateStormySea();
			break;
		case RAGING_SEA_FROM_STORMY_SEA:
			generateStormySea();
			addTransitionRagingFromStormy();
			break;
		case RAGING_SEA:
			generateRagingSea();
			break;
		case CRAB_KING_SEA:
			generateKingCrabSea();
			break;
		case KRAKEN_SEA:
			generateKrakenSea();
			break;
		default:
			throw new Exception("Type de section inexistante");
		}

		if (seaType == EnumSectionType.CALM_SEA || seaType == EnumSectionType.CALM_SEA_TO_STORMY_SEA
				|| seaType == EnumSectionType.STORMY_SEA_TO_RAGING_SEA
				|| seaType == EnumSectionType.RAGING_SEA_FROM_STORMY_SEA
				|| seaType == EnumSectionType.STORMY_SEA_FROM_CALM_SEA || seaType == EnumSectionType.STORMY_SEA
				|| seaType == EnumSectionType.RAGING_SEA) {
			generateNormalSectionElement();
		} else if (seaType == EnumSectionType.CRAB_KING_SEA) {
			generateCrabKing();
		} else if (seaType == EnumSectionType.KRAKEN_SEA) {
			generateKraken();
		}
	}

	public MapSection(EnumSectionType seaType, int sectionWidth, int sectionHeight, Random rand, int[] height,
			int sectionNumber) throws Exception {

		this.noiseGenerator = new PerlinNoiseGenerator(0.05);
		this.randomGenerator = rand;

		this.seaType = seaType;

		this.sectionHeight = sectionHeight;
		this.sectionWidth = sectionWidth;

		this.sectionNumber = sectionNumber;

		this.tiles = new Tiles[this.sectionHeight][this.sectionWidth];

		initSection();

		if (this.seaType == EnumSectionType.MOUTAIN) {
			generateMoutainSectionHeight(height);
		}
	}

	private void generateMoutainSectionHeight(int[] height) {
		for (int i = 0; i < this.sectionWidth; i++) {
			this.tiles[this.sectionHeight - 1][i].setHeight(height[i]);
		}

		for (int i = this.sectionHeight - 2; i >= 0; i--) {
			for (int j = 0; j < this.sectionWidth; j++) {
				this.tiles[i][j].setHeight(this.tiles[i + 1][j].getHeight() - 1);
			}
		}
	}

	private void generateNormalSectionElement() {
		addSandToGrassTransition();
		generateCrabsLair();
		generateFlower();
		generateRock();
		generateShellfish();
		generateTree();
		generateRedCross();
		generateSeaEnnemieTile();
		generateSeaChestTile();
	}

	/*
	 * Initialize the section with only water based on the seaType
	 */
	private void initSection() throws Exception {
		EnumTiles tilesType;

		switch (this.seaType) {
		case HARBOR:
		case CALM_SEA:
		case CALM_SEA_TO_STORMY_SEA:
			tilesType = EnumTiles.CALM_WATER;
			break;
		case STORMY_SEA:
		case STORMY_SEA_FROM_CALM_SEA:
		case STORMY_SEA_TO_RAGING_SEA:
			tilesType = EnumTiles.STORMY_WATER;
			break;
		case RAGING_SEA:
		case RAGING_SEA_FROM_STORMY_SEA:
		case CRAB_KING_SEA:
			tilesType = EnumTiles.RAGING_WATER;
			break;
		case KRAKEN_SEA:
			tilesType = EnumTiles.KRAKEN_WATER;
			break;
		case MOUTAIN:
			tilesType = EnumTiles.MOUTAIN;
			break;
		default:
			throw new Exception("Type de section inexistante");
		}

		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				tiles[i][j] = new Tiles(tilesType, j, i);
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

	private void addTransitionRagingFromStormy() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[this.sectionHeight - 1 - i][j].getType() == EnumTiles.RAGING_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[this.sectionHeight - 1 - i][j].setType(EnumTiles.STORMY_WATER);
					}
				}
			}
		}
	}

	private void addTransitionStormyFromCalm() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[this.sectionHeight - 1 - i][j].getType() == EnumTiles.STORMY_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[this.sectionHeight - 1 - i][j].setType(EnumTiles.CALM_WATER);
					}
				}
			}
		}
	}

	private void addTransitionStormyToRaging() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.STORMY_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[i][j].setType(EnumTiles.RAGING_WATER);
					}
				}
			}
		}
	}

	private void addTransitionCalmToStormy() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[i][j].setType(EnumTiles.STORMY_WATER);
					}
				}
			}
		}
	}

	public void addTransitionCrabToKraken() {
		for (int i = 0; i < 4; i++) {
			for (int j = this.sectionWidth / 2 - 10; j < this.sectionWidth / 2 + 10; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.RAGING_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[i][j].setType(EnumTiles.KRAKEN_WATER);
					}
				}
			}
		}
	}

	private void addTransitionKrakenFromCrab() {
		for (int i = 0; i < 4; i++) {
			for (int j = this.sectionWidth / 2 - 10; j < this.sectionWidth / 2 + 10; j++) {
				if (this.tiles[this.sectionHeight - 1 - i][j].getType() == EnumTiles.KRAKEN_WATER) {
					int rand = this.randomGenerator.nextInt((i + 1) * 3);
					if (rand == 2) {
						this.tiles[this.sectionHeight - 1 - i][j].setType(EnumTiles.RAGING_WATER);
					}
				}
			}
		}
	}

	private void generateHarbor() {

		// Generation of perlin noise for the island
		double[][] harborSection = new double[this.sectionHeight][this.sectionWidth];

		harborSection = generateHarborGradient(harborSection);

		insertHarborInSection(harborSection);
	}

	private double[][] generateHarborGradient(double[][] harbor) {
		double value;
		for (int i = 0; i < 8; i++) {
			value = (8 - i) * 0.1;
			for (int j = 0; j < this.sectionWidth; j++) {
				harbor[this.sectionHeight - 1 - i][j] += value;
			}
		}

		return harbor;
	}

	private void insertHarborInSection(double[][] harbor) {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if ((i == this.sectionHeight - 9 || i == this.sectionHeight - 10) && j == this.sectionWidth / 2) {
					this.tiles[i][j].setType(EnumTiles.PONTOON);
				} else if (harbor[i][j] == 0) {
					this.tiles[i][j].setType(EnumTiles.CALM_WATER);
				} else if (harbor[i][j] > 0) {
					this.tiles[i][j].setType(EnumTiles.HARBOR_SAND);
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

	private double[][] generateCrabKingGradient(double[][] island) {
		double maxValue = Math.sqrt(Math.pow(this.sectionHeight / 2, 2) + Math.pow(this.sectionWidth / 2, 2));

		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				double value = Math
						.sqrt(Math.pow(Math.max(i, this.sectionHeight / 2) - Math.min(i, this.sectionHeight / 2), 2)
								+ Math.pow(Math.max(j, this.sectionWidth / 2) - Math.min(j, this.sectionWidth / 2), 2));

				island[i][j] += this.noiseGenerator.smoothing(map(value, 0, maxValue, 0.5, 0)); // The more the value is
																								// far from the center
																								// of the
				// array, the closer it is from 1
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				island[i][j] -= (double) (10 - (double) i) / 10;
				island[this.sectionHeight - i - 1][j] -= (double) (10 - (double) i) / 10;
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				island[j][i + 8] -= (double) (10 - (double) i) / 10;
				island[j][this.sectionWidth - 9 - i] -= (double) (10 - (double) i) / 10;
			}
		}
		return island;
	}

	private void generateKingCrabSea() {

		// Generation of perlin noise for the island
		double[][] tempPerlinNoiseIsland = new double[this.sectionHeight][this.sectionWidth];
		tempPerlinNoiseIsland = this.noiseGenerator.generateNoiseArray(this.sectionHeight, this.sectionWidth,
				this.randomGenerator.nextDouble() * 10000, this.randomGenerator.nextDouble() * 10000);

		// Substract a island gradient to the perlin noise
		tempPerlinNoiseIsland = generateCrabKingGradient(tempPerlinNoiseIsland);

		// If the island is too small we generate a new one
		if (isCrabKingSurfaceBigEnough(tempPerlinNoiseIsland, this.sectionHeight, this.sectionWidth)) {
			generateKingCrabSea();
		} else { // Otherwise, we insert the island in the section
			insertCrabKingInSection(tempPerlinNoiseIsland, this.sectionHeight, this.sectionWidth);
		}

		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER) {
					this.tiles[i][j].setType(EnumTiles.RAGING_WATER);
				}
				if (this.tiles[i][j].getType() == EnumTiles.GRASS) {
					this.tiles[i][j].setType(EnumTiles.CRAB_KING_LAND);
				}
			}
		}
	}

	private double[][] krakenMountainGradien(double[][] noise) {
		double distMax = Math.sqrt(Math.pow(this.sectionWidth / 2, 2) + Math.pow((this.sectionHeight / 2 - 5), 2));

		double value;

		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				value = Math.sqrt(Math.pow(Math.abs(this.sectionWidth / 2 - j), 2)
						+ Math.pow(Math.abs((this.sectionHeight / 2 - 5) - i), 2));
				noise[i][j] = this.noiseGenerator.smoothing(this.map(value, 0, distMax, 0, 1));
			}
		}
		return noise;
	}

	private void generateKrakenSea() {
		double[][] mountainNoise = new double[this.sectionHeight][this.sectionWidth];
		mountainNoise = krakenMountainGradien(mountainNoise);

		insertMountainInSection(mountainNoise);

		int[][] height = new int[this.sectionHeight][this.sectionWidth];
		height = generateHeight(height);
		applyHeight(height);
	}

	private void applyHeight(int[][] height) {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (height[i][j] >= 0) {
					this.tiles[i][j].setHeight(-height[i][j]);
				}
			}
		}
	}

	private int[][] generateHeight(int[][] height) {

		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].isWater()) {
					height[i][j] = -1;
				} else {
					height[i][j] = -2;
				}
			}
		}

		int currentHeight = -1;
		while (!allHeightCalculated(height)) {
			for (int i = 0; i < this.sectionHeight; i++) {
				for (int j = 0; j < this.sectionWidth; j++) {
					if (height[i][j] == currentHeight) {
						if (i > 0) {
							if (height[i - 1][j] == -2) {
								height[i - 1][j] = currentHeight + 1;
							}
						}

						if (i < this.sectionHeight - 1) {
							if (height[i + 1][j] == -2) {
								height[i + 1][j] = currentHeight + 1;
							}
						}

						if (j > 0) {
							if (height[i][j - 1] == -2) {
								height[i][j - 1] = currentHeight + 1;
							}
						}

						if (j < this.sectionWidth - 1) {
							if (height[i][j + 1] == -2) {
								height[i][j + 1] = currentHeight + 1;
							}
						}
					}
				}
			}
			currentHeight++;
		}
		
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].isWater()) {
					height[i][j] = 0;
				}
			}
		}

		return height;
	}

	private boolean allHeightCalculated(int[][] height) {
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (height[i][j] == -2) {
					return false;
				}
			}
		}
		return true;
	}

	private void insertMountainInSection(double[][] moutain) {

		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (moutain[i][j] > 0.1) {
					this.tiles[i][j].setType(EnumTiles.MOUTAIN);
				} else {
					this.tiles[i][j].setType(EnumTiles.KRAKEN_WATER);
				}
			}
		}
		
		for (int i = 0; i < this.sectionWidth; i++) {
			this.tiles[this.sectionHeight - 1][i].setType(EnumTiles.RAGING_WATER);
		}
	}
	
	public void openKraken() {
		for (int i = this.sectionHeight / 2; i < this.sectionHeight; i++) {
			for (int j = 0; j < 10; j++) {
				this.tiles[i][this.sectionWidth / 2 + j].setType(EnumTiles.KRAKEN_WATER);
				this.tiles[i][this.sectionWidth / 2 - j].setType(EnumTiles.KRAKEN_WATER);
			}
		}

		for (int i = this.sectionWidth / 2 - 10; i < this.sectionWidth / 2 + 10; i++) {
			this.tiles[this.sectionHeight - 1][i].setType(EnumTiles.KRAKEN_WATER);
		}
		
		int[][] height = new int[this.sectionHeight][this.sectionWidth];
		
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				height[i][j] = 0;
			}
		}
		height = generateHeight(height);
		applyHeight(height);
		addTransitionKrakenFromCrab();
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

	private boolean isCrabKingSurfaceBigEnough(double[][] island, int height, int width) {
		int nbTileIsland = 0;
		for (int i = 0; i < height && nbTileIsland < NB_TILE_MIN_PER_ISLAND * 3; i++) {
			for (int j = 0; j < width && nbTileIsland < NB_TILE_MIN_PER_ISLAND * 3; j++) {
				if (island[i][j] * 255 >= 50) {
					nbTileIsland++;
				}
			}
		}

		return nbTileIsland < NB_TILE_MIN_PER_ISLAND * 3;
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

				island[i][j] -= this.noiseGenerator.smoothing(map(value, 0, maxValue, 0, 1)); // The more the value is
																								// far from the center
																								// of the
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
			offsetX = this.randomGenerator.nextInt(48);
			offsetX += 16;
		}
		for (int i = 0; i < islandSize; i++) {
			for (int j = 0; j < islandSize; j++) {
				if (island[i][j] * 255 < 0) {
					switch (this.seaType) {
					case HARBOR:
					case CALM_SEA:
					case CALM_SEA_TO_STORMY_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.CALM_WATER);
						break;
					case STORMY_SEA:
					case STORMY_SEA_FROM_CALM_SEA:
					case STORMY_SEA_TO_RAGING_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.STORMY_WATER);
						break;
					case RAGING_SEA:
					case RAGING_SEA_FROM_STORMY_SEA:
					case CRAB_KING_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.RAGING_WATER);
						break;
					default:
						break;
					}
				} else if (island[i][j] * 255 < 30) {
					switch (this.seaType) {
					case HARBOR:
					case CALM_SEA:
					case CALM_SEA_TO_STORMY_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.SAND_WATER);
						break;
					case STORMY_SEA:
					case STORMY_SEA_FROM_CALM_SEA:
					case STORMY_SEA_TO_RAGING_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.STORMY_SAND_WATER);
						break;
					case RAGING_SEA:
					case RAGING_SEA_FROM_STORMY_SEA:
					case CRAB_KING_SEA:
						this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.RAGING_SAND_WATER);
						break;
					default:
						break;
					}
				} else if (island[i][j] * 255 < 50) {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.SAND);
				} else {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.GRASS);
				}
			}
		}
	}

	private void insertCrabKingInSection(double[][] island, int height, int width) {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (island[i][j] * 255 < 0) {
					this.tiles[i][j].setType(EnumTiles.RAGING_WATER);
				} else if (island[i][j] * 255 < 30) {
					this.tiles[i][j].setType(EnumTiles.RAGING_SAND_WATER);
				} else if (island[i][j] * 255 < 50) {
					this.tiles[i][j].setType(EnumTiles.SAND);
				} else {
					this.tiles[i][j].setType(EnumTiles.GRASS);
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
						this.tiles[i + 1][j].setType(EnumTiles.CRAB_SPAWNER_TRANSITION);
						this.tiles[i + 1][j + 1].setType(EnumTiles.CRAB_SPAWNER_TRANSITION);
						this.tiles[i + 1][j + 2].setType(EnumTiles.CRAB_SPAWNER_TRANSITION);
						Tiles tile = this.tiles[i][j];
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

	public void generateSeaChestTile() {
		int rand;
		boolean added = false;
		for (int i = 8; i < this.sectionHeight - 8 && !added; i++) {
			for (int j = 10; j < this.sectionWidth - 11 && !added; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER
						|| this.tiles[i][j].getType() == EnumTiles.RAGING_WATER
						|| this.tiles[i][j].getType() == EnumTiles.STORMY_WATER) {
					rand = this.randomGenerator.nextInt(10000);
					if (rand == 500 && !added) {
						added = true;
						switch (this.tiles[i][j].getType()) {
						case CALM_WATER:
							this.tiles[i][j].setType(EnumTiles.CALM_SEA_CHEST);
							break;
						case STORMY_WATER:
							this.tiles[i][j].setType(EnumTiles.STORMY_SEA_CHEST);
							break;
						default:
							this.tiles[i][j].setType(EnumTiles.RAGING_SEA_CHEST);
							break;
						}

					}
				}
			}
		}
		if (!added) {
			generateSeaChestTile();
		}
	}

	public int[] getMountainHeight() {
		int[] height = new int[this.sectionWidth];

		for (int i = 0; i < this.sectionWidth; i++) {
			height[i] = this.tiles[0][i].getHeight() - 1;
		}

		return height;
	}

	public void generateSeaEnnemieTile() {
		int rand;
		boolean added = false;
		for (int i = 0; i < this.sectionHeight && !added; i++) {
			for (int j = 10; j < this.sectionWidth - 11 && !added; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CALM_WATER
						|| this.tiles[i][j].getType() == EnumTiles.RAGING_WATER
						|| this.tiles[i][j].getType() == EnumTiles.STORMY_WATER) {
					rand = this.randomGenerator.nextInt(10000);
					if (rand == 500 && !added) {
						added = true;
						switch (this.tiles[i][j].getType()) {
						case CALM_WATER:
							this.tiles[i][j].setType(EnumTiles.CALM_SEA_ENNEMIE);
							break;
						case STORMY_WATER:
							this.tiles[i][j].setType(EnumTiles.STORMY_SEA_ENNEMIE);
							break;
						default:
							this.tiles[i][j].setType(EnumTiles.RAGING_SEA_ENNEMIE);
							break;
						}

					}
				}
			}
		}
		if (!added) {
			generateSeaEnnemieTile();
		}
	}

	public void generateCrabKing() {
		int rand;
		boolean added = false;
		for (int i = 0; i < this.sectionHeight && !added; i++) {
			for (int j = 0; j < this.sectionWidth && !added; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.CRAB_KING_LAND) {
					rand = this.randomGenerator.nextInt(2000);
					if (rand == 500 && !added) {
						added = true;
						this.tiles[i][j].setType(EnumTiles.CRAB_KING);
					}
				}
			}
		}
		if (!added) {
			generateCrabKing();
		}
	}

	public void generateKraken() {
		this.tiles[this.sectionHeight / 2 - 5][this.sectionWidth / 2 - 10].setType(EnumTiles.KRAKEN_TENTACLE);
		this.tiles[this.sectionHeight / 2 - 5][this.sectionWidth / 2 + 10].setType(EnumTiles.KRAKEN_TENTACLE);
		this.tiles[this.sectionHeight / 2 - 15][this.sectionWidth / 2 + 5].setType(EnumTiles.KRAKEN_TENTACLE);
		this.tiles[this.sectionHeight / 2 - 15][this.sectionWidth / 2 - 5].setType(EnumTiles.KRAKEN_TENTACLE);
	}

	public void generateTree() {
		int rand;
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {
				if (this.tiles[i][j].getType() == EnumTiles.GRASS) {
					rand = this.randomGenerator.nextInt(12);
					if (rand == 5) {
						this.tiles[i][j].setType(EnumTiles.TREE);
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

	public void setRedCross(RedCross redCross) {
		this.redCross = redCross;
	}

	public void setCrabLair(CrabLair crabLair) {
		this.crabLair = crabLair;
	}

	public int getSectionNumber() {
		return this.sectionNumber;
	}
}
