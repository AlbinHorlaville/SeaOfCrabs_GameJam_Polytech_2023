package info3.game.vue.toolkitUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UIImage extends UIComponent{
	
	float scale;
	
	BufferedImage image;

	public UIImage(int x, int y, String file, float scale) {
		super(x, y, 0, 0);
		this.scale = scale;
		File fileCJ1 = new File(file);
		try {
			image = ImageIO.read(fileCJ1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setImage(BufferedImage i) {
		image = i;
	}
	
	

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, getPositionX(), getPositionY(), (int)( image.getWidth() * scale), (int)(image.getHeight() * scale), null);
	}

}
