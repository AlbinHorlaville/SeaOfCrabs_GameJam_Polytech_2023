package info3.game.modele;

import java.util.Random;

/*
 * This class contain a reprensation of the map section by section and the offset of each tiles to create the wave effect
 */
public class Map {
	private int[][] wave; // The offset of each tiles of the map to create a wave effect
	private MapSection[] map; // The map itself composed of section

	private int sectionWidth; // The width of a section
	private int sectionHeight; // The height of a section

	private int nbSection; // The number of section

	private int seed; // The seed of the map (two map generate with the same seed are the same)
	private Random rand; // The random generator created with the seed

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

		generateMap();
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
	 * Print the map, usefull for debbuging
	 */
	public void printMap() {
		for (int i = 0; i < this.nbSection; i++) {
			this.map[i].printSection();
			System.out.print("\n--------\n");
		}
	}

	public int[][] getWave() {
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
}
