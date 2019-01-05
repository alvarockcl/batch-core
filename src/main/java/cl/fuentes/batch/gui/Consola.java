package cl.fuentes.batch.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Consola extends JPanel {

    private int maxX, maxY;
    private Graphics graConsola = null;
    private Image imgConsola = null;
    
    private int ejecuciones = 0;
    
    BufferedImage img = null;

    public Consola(){
        try {
            img = ImageIO.read(new File("resources/fondo.jpg"));
        } catch (IOException e) {
            System.out.println(e);
        }        
    }

    public void actualizaConsola(int ejecuciones) {
        renderConsola();
        pintaConsola();
        this.ejecuciones = ejecuciones;
    }

    public void renderConsola(){
        if(graConsola==null){ 
            if(imgConsola==null){ 
                imgConsola = createImage(maxX, maxY);
            }
            if(imgConsola!=null){ 
                graConsola = imgConsola.getGraphics();
            }      
        }else{
            // pinta en secuencia
            graConsola.drawImage(img,0,0,maxX,maxX, this);

            graConsola.setFont(new Font("Arial", Font.BOLD, 19));
            graConsola.setColor(Color.green);
            graConsola.drawString("Resolución : " + maxX + " X " + maxY + " " + ejecuciones, 20, 60);                            
        }
    }

    public void pintaConsola(){
        try {
            Graphics g = this.getGraphics();
            if(g != null){ 
                if(getGraphics()!=null){ 
                    g.drawImage(imgConsola, 0, 0, maxX, maxY, Color.blue, this); 
                }
            }
        } catch (Exception e) {
        }    
    }    

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}