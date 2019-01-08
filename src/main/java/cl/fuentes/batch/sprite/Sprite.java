package cl.fuentes.batch.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage bigImg;
    final int width = 64;
    final int height = 64;
    final int rows = 5;
    final int cols = 5;
    private int posX=0;
    private int posY=0;
    private BufferedImage[] sprites;
    
    public Sprite(){
        try {
            bigImg = ImageIO.read(new File("resources/explosion1.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
        sprites = new BufferedImage[rows * cols];
        generaSpriteExplosion();
    }
    
    public void generaSpriteExplosion(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                getSprites()[(i * cols) + j] = getBigImg().getSubimage(j * width,i * height, width, height);
            }
        }
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public BufferedImage getBigImg() {
        return bigImg;
    }

    public void setBigImg(BufferedImage bigImg) {
        this.bigImg = bigImg;
    }
}
