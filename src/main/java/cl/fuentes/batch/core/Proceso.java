package cl.fuentes.batch.core;

import cl.fuentes.batch.render.*;

import java.util.concurrent.TimeUnit;

import cl.fuentes.batch.gui.*;

public class Proceso implements Runnable
{

    private Thread proceso = null;
    private boolean activo = false;
    private int ejecuciones = 1;

    private int contador_tiempo=0;
    private int fsuma=0;
    private int nframe = 0;
    private float tinicio;
    private float ttermino;
    private long tres;
    private long tdelay;
    private int tdif =0;

    Actividad actividad = null;
    Pantalla pantalla = null;

    public Proceso(){
        actividad = new Actividad();
        pantalla = new Pantalla(ejecuciones, nframe);
        pantalla.setVisible(true);
        this.start();
    }

    @Override
    public void run() {
        while(activo){
            tinicio = System.nanoTime();

            this.renderProceso();
            ejecuciones++;
            if(pantalla.isCerrado()){
                this.stop();
                System.exit(0);
            }

            ttermino = System.nanoTime();
            tres = (long)(ttermino-tinicio);
            tdelay = 33333333 - tres; // a 30 fps
            try { 
                TimeUnit.NANOSECONDS.sleep(tdelay);
            } catch (InterruptedException ex) {
            }
            if(contador_tiempo <= 50){
                tdif = (int)(1/((ttermino - tinicio + tdelay)/1000000000));
                fsuma = fsuma + tdif;
                contador_tiempo++;
            }else{
                nframe = (int)(fsuma/50);
                fsuma = 0;
                contador_tiempo = 0;
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
        pantalla.setNframe(nframe);
        pantalla.actualizaPantalla();
    }

    public static void main(String[] args){
        new Proceso().start();
    }
}
