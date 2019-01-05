package cl.fuentes.batch.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Consola extends JPanel {

    private int maxX, maxY;
    private Graphics graPantalla = null;
    private Image imgPantalla = null;

    public Consola(){
        //this.setVisible(true);
    }

    public void actualizaConsola() {
        renderConsola();
        pintaConsola();
    }

    public void renderConsola(){
        if(graPantalla==null){ 
            if(imgPantalla==null){ 
                imgPantalla = createImage(maxX, maxY);
            }
            if(imgPantalla!=null){ 
                graPantalla = imgPantalla.getGraphics();
            }      
        }else{
            //System.out.println("Renderiza");
            //graPantalla.drawImage(imgPantalla,0,0,maxX,maxY, this);
            graPantalla.setColor(Color.black);
            graPantalla.fillRect ( 0, 0, 100, 100 );

            graPantalla.setFont(new Font("Arial", Font.BOLD, 20));
            graPantalla.setColor(Color.black);
            graPantalla.drawString("Resolución : " + maxX + " X " + maxY, 20, 60);                            
        }
    }

    public void pintaConsola(){
        try {
            Graphics g = this.getGraphics();
            if(g!=null){ 
                if(getGraphics()!=null){ 
                    //System.out.println("Pinta");
                    g.drawImage(imgPantalla, 0, 0, maxX, maxY, Color.blue, this); 
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