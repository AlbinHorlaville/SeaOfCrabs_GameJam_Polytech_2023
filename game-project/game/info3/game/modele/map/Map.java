package info3.game.modele.map;

import java.util.Random;

import info3.game.vue.GameView;
import info3.game.vue.avatar.MapRepresentation;
import info3.game.vue.avatar.MiniMap;

/*
 * This class contain a reprensation of the map section by section and the offset of each tiles to create the wave effect
 */
public class Map {
	private double[][] wave; // The offset of each tiles of the map to create a wave effect
	private MapSection[] map; // The map itself composed of section

	private int sectionWidth; // The width of a section
	private int sectionHeight; // The height of a section

	private int nbSection; // The number of section

	private int seed; // The seed of the map (two map generate with the same seed are the same)
	private Random rand; // The random generator created with the seed

	private MapRepresentation mapRepres; // The graphic representation of the map

	private MiniMap miniMap; // The graphic representation of the map

	private int tileWidth;
	private int tileHeight;

	/*
	 * @param seed : the seed of the map
	 * 
	 * @param nbSection : the number of section in the map
	 * 
	 * @param sectionWidth and sectionHeight : the dimension of a section
	 * 
	 */
	public Map(int seed, int nbSection, int sectionWidth, int sectionHeight) throws Exception {
		this.seed = seed;
		this.rand = new Random(this.seed);

		this.sectionHeight = sectionHeight;
		this.sectionWidth = sectionWidth;
		this.nbSection = nbSection;

		this.map = new MapSection[this.nbSection];

		this.wave = new double[this.sectionHeight * this.nbSection][this.sectionWidth];

		generateBaseMap();

		generateWave();

		this.mapRepres = new MapRepresentation(this);

		this.miniMap = new MiniMap(this);
	}

	/*
	 * @param seed : the seed of the map
	 * 
	 * @param nbSection : the number of section in the map
	 * 
	 * @param sectionWidth and sectionHeight : the dimension of a section
	 * 
	 */
	public Map(int seed) throws Exception {
		this.seed = seed;
		this.rand = new Random(this.seed);

		this.sectionHeight = 48;
		this.sectionWidth = 96;
		this.nbSection = 9;

		this.map = new MapSection[this.nbSection];

		this.wave = new double[this.sectionHeight * this.nbSection][this.sectionWidth];

		generateMap();

		generateWave();

		this.mapRepres = new MapRepresentation(this);

		this.miniMap = new MiniMap(this);
	}

	/*
	 * Generate a map based on the seed and the section parameters
	 */
	public void generateBaseMap() throws Exception {
		for (int i = 0; i < this.nbSection; i++) {
			this.map[i] = new MapSection(EnumSectionType.CALM_SEA, this.sectionWidth, this.sectionHeight, this.rand);
		}
	}

	/*
	 * Generate a map based on the seed and the section parameters
	 */
	public void generateMap() throws Exception {
		this.map[0] = new MapSection(EnumSectionType.HARBOR, this.sectionWidth, this.sectionHeight, this.rand);

		this.map[1] = new MapSection(EnumSectionType.CALM_SEA, this.sectionWidth, this.sectionHeight, this.rand);
		this.map[2] = new MapSection(EnumSectionType.CALM_SEA_TO_STORMY_SEA, this.sectionWidth, this.sectionHeight, this.rand);

		this.map[3] = new MapSection(EnumSectionType.STORMY_SEA_FROM_CALM_SEA, this.sectionWidth, this.sectionHeight, this.rand);
		this.map[4] = new MapSection(EnumSectionType.STORMY_SEA_TO_RAGING_SEA, this.sectionWidth, this.sectionHeight, this.rand);

		this.map[5] = new MapSection(EnumSectionType.RAGING_SEA_FROM_STORMY_SEA, this.sectionWidth, this.sectionHeight, this.rand);
		this.map[6] = new MapSection(EnumSectionType.RAGING_SEA, this.sectionWidth, this.sectionHeight, this.rand);

		this.map[7] = new MapSection(EnumSectionType.CRAB_KING_SEA, this.sectionWidth, this.sectionHeight, this.rand);

		this.map[8] = new MapSection(EnumSectionType.KRAKEN_SEA, this.sectionWidth, this.sectionHeight, this.rand);
	}

	public void setImageSize(int width, int height) {
		this.tileWidth = width;
		this.tileHeight = height;
	}

	/*
	 * Set the coordonate of each tiles
	 */
	public void setCoordiate(int tileWidth, int tileHeight) {
		Tiles[][] section;
		for (int i = 0; i < this.nbSection; i++) {
			section = this.map[i].getTiles();
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					section[j][k].setCoordinate(
							transpoXCoordinateToIsometric(k, (this.nbSection - i - 1) * this.sectionHeight + j),
							transpoYCoordinateToIsometric(k, (this.nbSection - i - 1) * this.sectionHeight + j));
				}
			}
		}
	}

	/*
	 * Convert normal (x,y) coordinate to isometric x coordinate
	 */
	public int transpoXCoordinateToIsometric(int tileX, int tileY) {
		return (tileX * (this.tileWidth / 2)) + ((-tileY) * (this.tileWidth / 2));
	}

	/*
	 * Convert normal (x,y) coordinate to isometric y coordinate
	 */
	public int transpoYCoordinateToIsometric(int tileX, int tileY) {
		return (tileX * (this.tileHeight / 4)) + (tileY * (this.tileHeight / 4));
	}

	/*
	 * Convert normal (x,y) coordinate to isometric x coordinate
	 */
	public int transpoYCoordinateToTile(int xPos, int yPos) {
		int yNoIso = 0;

		double det = determinant();

		yNoIso = (int) Math.ceil(((xPos * (det * ((-this.tileHeight) / 4))) + (yPos * (det * (this.tileWidth / 2)))));

		return yNoIso;

	}

	/*
	 * Convert normal (x,y) coordinate to isometric y coordinate
	 */
	public int transpoXCoordinateToTile(int xPos, int yPos) {
		int xNoIso = 0;

		double det = determinant();

		xNoIso = (int) Math.ceil(((xPos * (det * (this.tileHeight / 4))) + (yPos * (det * (this.tileWidth / 2)))));

		return xNoIso;

	}

	public int getSectionOfEntity(int xPos, int yPos) {
		int y = transpoYCoordinateToTile(-(xPos - GameView.screenWidth / 2), -(yPos - GameView.screenHeight / 2));

		int numSection = 9;

		while (y >= 0) {
			y -= this.sectionHeight;
			numSection--;
		}

		return numSection;
	}

	public Tiles getTileUnderEntity(int xPos, int yPos) {
		int x = transpoXCoordinateToTile(-(xPos - GameView.screenWidth / 2), -(yPos - GameView.screenHeight / 2));
		int y = transpoYCoordinateToTile(-(xPos - GameView.screenWidth / 2), -(yPos - GameView.screenHeight / 2)) % 48;

		int numSection = this.getSectionOfEntity(xPos, yPos);

		return this.map[numSection].getTiles()[y][x];
	}

	double determinant() {
		float bottom = ((this.tileWidth * this.tileHeight) / 4);
		return (1 / bottom);
	}

	/*
	 * Print the map, usefull for debbuging
	 */
	public void printMap() {
		for (int i = 0; i < this.nbSection; i++) {
			System.out.print("\n----" + i + "----\n");
			this.map[i].printSection();

		}
	}

	/**
	 * Get the Wave Offset for a pos
	 * 
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	public double getWaveOffset(int xPos, int yPos) {
		if (getTileUnderEntity(xPos, yPos).getType() == EnumTiles.CALM_WATER
				|| getTileUnderEntity(xPos, yPos).getType() == EnumTiles.STORMY_WATER
				|| getTileUnderEntity(xPos, yPos).getType() == EnumTiles.RAGING_WATER
				|| getTileUnderEntity(xPos, yPos).getType() == EnumTiles.KRAKEN_WATER) {
			int x = transpoXCoordinateToTile(-(xPos - GameView.screenWidth / 2), -(yPos - GameView.screenHeight / 2));
			int y = transpoYCoordinateToTile(-(xPos - GameView.screenWidth / 2), -(yPos - GameView.screenHeight / 2))
					% 48;

			int numSection = this.getSectionOfEntity(xPos, yPos);

			return this.wave[numSection * this.sectionHeight + y][x];
		} else {
			return 0;
		}
	}

	/*
	 * Print the waveMap, usefull for debbuging
	 */
	public void printWaveMap() {
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					System.out.print(" " + this.wave[i * this.sectionHeight + j][k] + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n\n-------------\n\n");
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
	 * Generate the wave of the map section by section regarding of the section type
	 * The generation is done with perlin noise mapped to a range depending of the
	 * section type
	 */
	public void generateWave() {
		PerlinNoiseGenerator perlin = new PerlinNoiseGenerator(0.05);
		double[][] waveNoise = new double[this.sectionHeight * this.nbSection][this.sectionWidth];

		waveNoise = perlin.generateNoiseArray(this.sectionHeight * this.nbSection, this.sectionWidth,
				rand.nextDouble() * 10000, rand.nextDouble() * 10000);

		int waveRange;
		for (int i = 0; i < this.nbSection; i++) {
			switch (this.map[i].getSeaType()) {
			case HARBOR:
			case CALM_SEA:
			case CALM_SEA_TO_STORMY_SEA:
				waveRange = 25;
				break;
			case STORMY_SEA:
			case STORMY_SEA_FROM_CALM_SEA:
			case STORMY_SEA_TO_RAGING_SEA:
				waveRange = 35;
				break;
			case RAGING_SEA:
			case RAGING_SEA_FROM_STORMY_SEA:
			case CRAB_KING_SEA:
			case KRAKEN_SEA:
				waveRange = 45;
				break;
			default:
				waveRange = 0;
			}
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					if (k > 15 && k < this.sectionWidth - 16) {
						this.wave[i * this.sectionHeight + j][k] = map(waveNoise[i * this.sectionHeight + j][k], -1, 1,
								-waveRange, waveRange);
					} else {
						this.wave[i * this.sectionHeight + j][k] = 0;
					}
				}
			}
		}

		smoothWaveBorder(); // Smooth the border of the array for no relica when cycling the wave
	}

	/*
	 * Smooth all the border
	 */
	public void smoothWaveBorder() {
		smoothWaveBorderNorthAndSouth();
		smoothWaveBorderEastAndWest();
	}

	/*
	 * Smooth North and South border
	 */
	private void smoothWaveBorderNorthAndSouth() {
		double valueNorth;
		double valueSouth;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < this.sectionWidth; j++) {

				// South value become combinaison of south and north wave
				if (i == 0) {
					valueSouth = 0.5 * this.wave[i][j]
							+ 0.5 * this.wave[(this.nbSection * this.sectionHeight) - i - 1][j];
				} else {
					valueSouth = 0.6 * this.wave[i][j] + 0.4 * this.wave[i - 1][j];
				}

				// North value become combinaison of south and north wave
				if (i == 0) {
					valueNorth = 0.5 * this.wave[i][j]
							+ 0.5 * this.wave[(this.nbSection * this.sectionHeight) - i - 1][j];
				} else {
					valueNorth = 0.4 * this.wave[(this.nbSection * this.sectionHeight) - i][j]
							+ 0.6 * this.wave[(this.nbSection * this.sectionHeight) - i - 1][j];
				}

				this.wave[i][j] = valueSouth;
				this.wave[(this.nbSection * this.sectionHeight) - i - 1][j] = valueNorth;
			}
		}
	}

	/*
	 * Smooth West and East border
	 */
	private void smoothWaveBorderEastAndWest() {
		double valueEast;
		double valueWest;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < this.sectionHeight * this.nbSection; j++) {
				// West value become combinaison of west and east wave
				valueWest = 0.3 * this.wave[j][i + 16] + i * 0.25 * this.wave[j][i + 16];

				// East value become combinaison of west and east wave
				valueEast = 0.3 * this.wave[j][this.sectionWidth - i - 17] + i * 0.25 * this.wave[j][this.sectionWidth - i - 17];

				this.wave[j][i + 16] = valueWest;
				this.wave[j][this.sectionWidth - i - 17] = valueEast;
			}
		}
	}

	/*
	 * The wave cicle torward the north
	 */
	public void cicleWaveNorth() {
		double temp[] = new double[this.sectionWidth];

		int maxX = this.nbSection * this.sectionHeight - 1;

		for (int i = 0; i < this.sectionWidth; i++) {
			temp[i] = this.wave[maxX][i];
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = this.sectionHeight - 1; j >= (i == 0 ? 1 : 0); j--) {
				for (int k = 0; k < this.sectionWidth; k++) {
					this.wave[i * this.sectionHeight + j][k] = this.wave[i * this.sectionHeight + (j - 1)][k];
				}
			}
		}

		for (int i = 0; i < this.sectionWidth; i++) {
			this.wave[0][i] = temp[i];
		}
	}

	/*
	 * The wave cicle torward the South
	 */
	public void cicleWaveSouth() {
		double temp[] = new double[this.sectionWidth];
		for (int i = 0; i < this.sectionWidth; i++) {
			temp[i] = this.wave[0][i];
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < (i == this.nbSection - 1 ? this.sectionHeight - 1 : this.sectionHeight); j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					this.wave[i * this.sectionHeight + j][k] = this.wave[i * this.sectionHeight + (j + 1)][k];
				}
			}
		}

		int maxX = this.nbSection * this.sectionHeight - 1;

		for (int i = 0; i < this.sectionWidth; i++) {
			this.wave[maxX][i] = temp[i];
		}
	}

	/*
	 * The wave cicle torward the west
	 */
	public void cicleWaveWest() {
		double temp[] = new double[this.sectionHeight * this.nbSection];
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				temp[i * this.sectionHeight + j] = this.wave[i * this.sectionHeight + j][0];
			}
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth - 1; k++) {
					this.wave[i * this.sectionHeight + j][k] = this.wave[i * this.sectionHeight + j][k + 1];
				}
			}
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				this.wave[i * this.sectionHeight + j][this.sectionWidth - 1] = temp[i * this.sectionHeight + j];
			}
		}
	}

	/*
	 * The wave cicle torward the east
	 */
	public void cicleWaveEast() {
		double temp[] = new double[this.sectionHeight * this.nbSection];
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				temp[i * this.sectionHeight + j] = this.wave[i * this.sectionHeight + j][this.sectionWidth - 1];
			}
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = this.sectionWidth - 1; k > 0; k--) {
					this.wave[i * this.sectionHeight + j][k] = this.wave[i * this.sectionHeight + j][k - 1];
				}
			}
		}

		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				this.wave[i * this.sectionHeight + j][0] = temp[i * this.sectionHeight + j];
			}
		}
	}

	public double[][] getWave() {
		return wave;
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

	public int getNbSection() {
		return nbSection;
	}

	public void setNbSection(int nbSection) {
		this.nbSection = nbSection;
	}

	public int getSeed() {
		return seed;
	}

	public MapSection[] getMap() {
		return this.map;
	}

	public MapRepresentation getRepresentation() {
		return this.mapRepres;
	}

	public MiniMap getMiniMap() {
		return this.miniMap;
	}

	public Random getRand() {
		return this.rand;
	}
}
