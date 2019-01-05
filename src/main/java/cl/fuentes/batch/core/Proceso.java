package cl.fuentes.batch.core;

import cl.fuentes.batch.render.*;
import cl.fuentes.batch.gui.*;

public class Proceso implements Runnable
{

    private Thread proceso = null;
    private boolean activo = false;
    private int ejecuciones = 1;

    Actividad actividad = null;
    Pantalla pantalla = null;

    public Proceso(){
        actividad = new Actividad();
        pantalla = new Pantalla(ejecuciones);
        pantalla.setVisible(true);
        this.start();
    }

    @Override
    public void run() {
        while(activo){
            this.renderProceso();
            ejecuciones++;
            if(pantalla.isCerrado()){
                this.stop();
                System.exit(0);
            }
        }
    }

    public void start(){
        if(!activo){
            proceso = new Thread(this);
            activo = true;
            proceso.start();
        }
    }

    public void stop(){
        activo = false;
    }

    public void renderProceso(){
        pantalla.setEjecuciones(ejecuciones);
    }

    public static void main(String[] args){
        new Proceso().start();
    }
}
