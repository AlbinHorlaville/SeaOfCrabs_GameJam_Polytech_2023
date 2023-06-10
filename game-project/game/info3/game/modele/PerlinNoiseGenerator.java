package info3.game.modele;

public class PerlinNoiseGenerator {

	/*
	 * The perlin noise table every number from 0 to 255 repeated twice and without
	 * consecutive number
	 */
	private final static int perlinTable[] = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69,
			142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35,
			11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48,
			27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244,
			102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116,
			188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126,
			255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2,
			44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224,
			232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81,
			51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45,
			127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156,
			180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8,
			99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32,
			57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
			77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143,
			54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159,
			86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82,
			85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154,
			163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178,
			185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145,
			235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4,
			150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 };

	private double offset; // The gap between each value generated

	public PerlinNoiseGenerator(double offset) {
		this.offset = offset;
	}

	/*
	 * Generate an array of 2d perlin noise
	 * 
	 * @param width and height : the dimension of the array to generate
	 * 
	 * @param noiseStartX and noiseStartY : the position to start the perlin noise
	 * (case[0][0] of the array)
	 */
	public double[][] generateNoiseArray(int width, int height, double noiseStartX, double noiseStartY) {
		double[][] noiseArray = new double[width][height];

		double startX = noiseStartX;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				noiseArray[i][j] = perlinNoise2d(noiseStartX, noiseStartY);
				noiseStartX += this.offset;
			}
			noiseStartY += this.offset;
			noiseStartX = startX;
		}

		return noiseArray;
	}

	/*
	 * Generate the 2d perlin noise corresponding to the x and y value
	 */
	public double perlinNoise2d(double x, double y) {
		int xi = (int) Math.floor(x) & 255;
		int yi = (int) Math.floor(y) & 255;

		int g1 = perlinTable[perlinTable[xi] + yi];
		int g2 = perlinTable[perlinTable[xi + 1] + yi];
		int g3 = perlinTable[perlinTable[xi] + yi + 1];
		int g4 = perlinTable[perlinTable[xi + 1] + yi + 1];

		double xf = x - Math.floor(x);
		double yf = y - Math.floor(y);

		double d1 = gradientVector(g1, xf, yf);
		double d2 = gradientVector(g2, xf - 1, yf);
		double d3 = gradientVector(g3, xf, yf - 1);
		double d4 = gradientVector(g4, xf - 1, yf - 1);

		// Make the value between 0 and 1 smoother for a better linearInterpolation
		double u = smoothing(xf);
		double v = smoothing(yf);

		double x1Inter = linearInterpolation(u, d1, d2);
		double x2Inter = linearInterpolation(u, d3, d4);
		double yInter = linearInterpolation(v, x1Inter, x2Inter);

		return yInter;
	}

	/*
	 * Smooth the value between 0 and 1
	 */
	private double smoothing(double t) {
		return t * t * t * (t * (t * 6 - 15) + 10);
	}

	/*
	 * The gradient of the 2d perlin noise 0: x + y 1: -x + y 2: x - y 3: -x - y
	 */
	private double gradientVector(int hash, double x, double y) {
		switch (hash & 3) { // Choose the gradient vector, {(1, 1), (-1, 1), (1, -1), (-1, -1)}
		case 0:
			return x + y;
		case 1:
			return -x + y;
		case 2:
			return x - y;
		case 3:
			return -x - y;
		default:
			return 0;
		}
	}

	/*
	 * Classic linear interpolation
	 */
	private double linearInterpolation(double amount, double left, double right) {
		return ((1 - amount) * left + amount * right);
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}
}
