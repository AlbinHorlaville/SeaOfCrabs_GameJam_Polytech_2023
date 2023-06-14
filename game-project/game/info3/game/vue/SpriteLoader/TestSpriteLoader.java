package info3.game.vue.SpriteLoader;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class TestSpriteLoader extends Canvas {
	private BufferedImage image;
	
	public TestSpriteLoader(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }


    public static void main(String[] args) {
        try {
            SpriteLoader spriteLoader = new SpriteLoader();
			spriteLoader.initAllSprites();
        	BufferedImage [] playerSprites = SpriteLoader.TypeSpritesMap.get(SpriteType.Player);
        	BufferedImage image = playerSprites[1];
        	
        	JFrame frame = new JFrame("Ma fenêtre");
            TestSpriteLoader canvas = new TestSpriteLoader(image);
            Graphics g = canvas.getGraphics();
            frame.setVisible(true);
            canvas.paint(g);
            // Ajoutez le canvas à votre fenêtre ou à un autre conteneur approprié

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
