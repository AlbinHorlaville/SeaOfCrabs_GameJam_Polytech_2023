package info3.game.modele.map;

import java.util.Random;

import info3.game.vue.avatar.MapRepresentation;

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

		generateMap();

		generateWave();

		this.mapRepres = new MapRepresentation(this, this.wave);
	}

	/*
	 * Generate a map based on the seed and the section parameters
	 */
	public void generateMap() throws Exception {
		for (int i = 0; i < this.nbSection; i++) {
			this.map[i] = new MapSection(EnumSectionType.CALM_SEA, this.sectionWidth, this.sectionHeight, this.rand);
		}
	}

	/*
	 * Set the coordonate of each tiles (with tiles[this.height * this.nbSection][0] in (0,0))
	 */
	public void setCoordiate(int tileWidth, int tileHeight) {
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					if (this.map[i].getTiles()[j][k].getType() == EnumTiles.CALM_WATER) {
						this.map[i].getTiles()[j][k].setCoordinate(
								transpoXCoordinateToIsometric(tileWidth, tileHeight, k, i * this.sectionHeight + j),
								(int) (this.wave[i * this.sectionHeight + j][k]) + transpoYCoordinateToIsometric(
										tileWidth, tileHeight, k, i * this.sectionHeight + j));
					} else {
						this.map[i].getTiles()[j][k].setCoordinate(
								transpoXCoordinateToIsometric(tileWidth, tileHeight, k, i * this.sectionHeight + j),
								transpoYCoordinateToIsometric(tileWidth, tileHeight, k, i * this.sectionHeight + j));
					}
				}
			}
		}
	}
	
	/*
	 * Remove the offset of the wave on the coordinate of the tiles
	 */
	public void removeWaveOffset() {
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					if (this.map[i].getTiles()[j][k].getType() == EnumTiles.CALM_WATER) {
						this.map[i].getTiles()[j][k].setCoordinate(this.map[i].getTiles()[j][k].getX(),
								this.map[i].getTiles()[j][k].getY() - (int) (this.wave[i * this.sectionHeight + j][k]));
					} else if (this.map[i].getTiles()[j][k].getType() == EnumTiles.SAND_WATER) {
						this.map[i].getTiles()[j][k].setCoordinate(this.map[i].getTiles()[j][k].getX(),
								this.map[i].getTiles()[j][k].getY() - (int) ((this.wave[i * this.sectionHeight + j][k]) / 3));
					}
				}
			}
		}
	}

	/*
	 * Apply the offset of the wave to the coordinate of the tiles
	 */
	public void applyWaveOffset() {
		for (int i = 0; i < this.nbSection; i++) {
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					if (this.map[i].getTiles()[j][k].getType() == EnumTiles.CALM_WATER) {
						this.map[i].getTiles()[j][k].setCoordinate(this.map[i].getTiles()[j][k].getX(),
								this.map[i].getTiles()[j][k].getY() + (int) (this.wave[i * this.sectionHeight + j][k]));
					} else if (this.map[i].getTiles()[j][k].getType() == EnumTiles.SAND_WATER) {
						this.map[i].getTiles()[j][k].setCoordinate(this.map[i].getTiles()[j][k].getX(),
								this.map[i].getTiles()[j][k].getY() + (int) ((this.wave[i * this.sectionHeight + j][k]) / 3));
					}
				}
			}
		}
	}

	/*
	 * Convert normal (x,y) coordinate to isometric x coordinate
	 */
	public int transpoXCoordinateToIsometric(int tileWidth, int tileHeight, int tileX, int tileY) {
		return (tileX * (tileWidth / 2)) + ((-tileY) * (tileWidth / 2));
	}

	/*
	 * Convert normal (x,y) coordinate to isometric y coordinate
	 */
	public int transpoYCoordinateToIsometric(int tileWidth, int tileHeight, int tileX, int tileY) {
		return (tileX * (tileHeight / 4)) + (tileY * (tileHeight / 4));
	}

	/*
	 * Print the map, usefull for debbuging
	 */
	public void printMap() {
		for (int i = 0; i < this.nbSection; i++) {
			this.map[i].printSection();
			System.out.print("\n--------\n");
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

		for (int i = 0; i < this.nbSection; i++) {
			int waveRange;
			switch (this.map[i].getSeaType()) {
			case CALM_SEA:
				waveRange = 25;
				break;
			case STORMY_SEA:
				waveRange = 35;
				break;
			case RAGING_SEA:
			case CRAB_KING_SEA:
			case KRAKEN_SEA:
				waveRange = 45;
				break;
			default:
				waveRange = 0;
			}
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
					this.wave[i * this.sectionHeight + j][k] = map(waveNoise[i * this.sectionHeight + j][k], -1, 1,
							-waveRange, waveRange);
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
				if (i == 0) {
					valueWest = 0.5 * this.wave[j][i] + 0.5 * this.wave[j][this.sectionWidth - i - 1];
				} else {
					valueWest = 0.6 * this.wave[j][i] + 0.4 * this.wave[j][i - 1];
				}

				// East value become combinaison of west and east wave
				if (i == 0) {
					valueEast = 0.5 * this.wave[j][i] + 0.5 * this.wave[j][this.sectionWidth - i - 1];
				} else {
					valueEast = 0.4 * this.wave[j][this.sectionWidth - i]
							+ 0.6 * this.wave[j][this.sectionWidth - i - 1];
				}

				this.wave[j][i] = valueWest;
				this.wave[j][this.sectionWidth - i - 1] = valueEast;
			}
		}
	}

	/*
	 * The wave cicle torward the north
	 */
	public void cicleWaveNorth() {
		removeWaveOffset();
		double temp[] = new double[this.sectionWidth];
		for (int i = 0; i < this.sectionWidth; i++) {
			temp[i] = this.wave[this.nbSection * this.sectionHeight - 1][i];
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
		applyWaveOffset();
	}

	/*
	 * The wave cicle torward the South
	 */
	public void cicleWaveSouth() {
		removeWaveOffset();
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

		for (int i = 0; i < this.sectionWidth; i++) {
			this.wave[this.nbSection * this.sectionHeight - 1][i] = temp[i];
		}
		applyWaveOffset();
	}

	/*
	 * The wave cicle torward the west
	 */
	public void cicleWaveWest() {
		removeWaveOffset();
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
		applyWaveOffset();
	}

	/*
	 * The wave cicle torward the east
	 */
	public void cicleWaveEast() {
		removeWaveOffset();
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
		applyWaveOffset();
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
}
