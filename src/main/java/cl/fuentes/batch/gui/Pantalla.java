package cl.fuentes.batch.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Pantalla extends JFrame{

    private int ejecuciones;
    private JTextField texto;
    private boolean cerrado = false;

    public Pantalla(int ejecuciones){
        initComponents();        
        this.ejecuciones = ejecuciones;
    }

    private void initComponents() {

        this.setTitle("Consola Procesos");
        this.setSize(400, 200);
        this.setLayout(null);
        
        texto = new JTextField();
        texto.setLocation(20, 20);
        texto.setSize(200, 20);
        texto.setText("" + ejecuciones);
        texto.setVisible(true);
        
        this.add(texto);

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
        texto.setText(String.valueOf(ejecuciones));
    }

}