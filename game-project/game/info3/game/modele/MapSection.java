package info3.game.modele;

import java.util.Random;

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
			generateKrakenSea();
			break;
		default:
			throw new Exception("Type de section inexistante");
		}
	}

	/*
	 * Initialize the section with only water based on the seaType
	 */
	private void initSection() {
		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				switch (this.seaType) {
				case CALM_SEA:
					tiles[i][j] = new Tiles(EnumTiles.CALM_WATER);
					break;
				case STORMY_SEA:
					tiles[i][j] = new Tiles(EnumTiles.STORMY_WATER);
					break;
				case RAGING_SEA:
				case KRAKEN_SEA:
				case CRAB_KING_SEA:
					tiles[i][j] = new Tiles(EnumTiles.RAGING_WATER);
					break;
				}
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
				this.randomGenerator.nextFloat() * 10000, this.randomGenerator.nextFloat() * 10000);

		// Substract a island gradient to the perlin noise
		tempPerlinNoiseIsland = generateIslandGradient(tempPerlinNoiseIsland, islandSize);

		// If the island is too small we generate a new one
		if (isIslandSurfaceBigEnough(tempPerlinNoiseIsland, islandSize)) {
			generateCalmSea();
		} else { // Otherwise, we insert the island in the section
			insertIslandInSection(tempPerlinNoiseIsland, islandSize);
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
	}

	/*
	 * Generate a sea of the type krakenSea, no island, only raging water.
	 */
	private void generateKrakenSea() {
		for (int i = 0; i < sectionHeight; i++) {
			for (int j = 0; j < sectionWidth; j++) {
				this.tiles[i][j].setType(EnumTiles.RAGING_WATER);
			}
		}
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

				island[i][j] -= map(value, 0, maxValue, 0, 1); // The more the value is far from the center of the
																// array, the closer it is from 1
			}
		}

		return island;
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
				if (island[i][j] * 255 < 20) {
					this.tiles[i + offsetY][j + offsetX].setType(EnumTiles.CALM_WATER);
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
}
