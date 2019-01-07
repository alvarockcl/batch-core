package cl.fuentes.batch.gui;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Pantalla extends JFrame{

    private int ejecuciones;
    private int nframe;
    private boolean cerrado = false;
    private Consola consola;


    public Pantalla(int ejecuciones, int nframe){
        initComponents();        
        this.ejecuciones = ejecuciones;
        this.nframe = nframe;
    }

    private void initComponents() {

        this.setTitle("Consola Procesos");
        setUndecorated(true); // remueve la barra de titulos
        setAlwaysOnTop(false); // esta interface esta siempre sobre
        setResizable(false); // se deshabilita cambiar tamaño 

        Toolkit tk = Toolkit.getDefaultToolkit(); // hará mas comrpensible
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        setSize(xsize, ysize);

        ImageIcon img = new ImageIcon("resources/icon.png");
        setIconImage(img.getImage());

        consola = new Consola();
        consola.setMaxX(xsize);
        consola.setMaxY(ysize);
        
        this.add(consola);  
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                setCerrado(true);
            }
            public void windowClosing(WindowEvent e) {
                setCerrado(true);
            }
        });
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public int getEjecuciones() {
        return ejecuciones;
    }

    public void setEjecuciones(int ejecuciones) {
        this.ejecuciones = ejecuciones;
    }

    public int getNframe() {
        return nframe;
    }

    public void setNframe(int nframe) {
        this.nframe = nframe;
    }
    public void actualizaPantalla(){
        consola.actualizaConsola(ejecuciones, nframe);
    }

}