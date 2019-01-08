package cl.fuentes.batch.core;

import cl.fuentes.batch.render.*;

import java.util.concurrent.TimeUnit;

import cl.fuentes.batch.gui.*;

public class Proceso implements Runnable
{

    private Thread proceso = null;
    private boolean activo = false;
    private int ejecuciones = 1;

    private int contador_tiempo=1;
    private int fsuma=0;
    private int nframe = 0;
    private double tinicio;
    private double ttermino;
    private long tres;
    private long tdelay;
    private int tdif =0;
    private double r1, r2, r3, r4, r5;

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

            this.ajusteFrame(true, 20);

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

    public void ajusteFrame(boolean frameControl, int fps) {
    

          if(frameControl){  
                tres = (long)(ttermino-tinicio);
                double frac = (1 / (double) fps ) * 1000000000;
                
                tdelay = (long) (frac - tres); 
                
                try { 
                    TimeUnit.NANOSECONDS.sleep(tdelay); 
                } catch (InterruptedException ex) {
                }
            }

            if(!frameControl){
                tdelay = 0;
            }
            
            if(contador_tiempo <= 50){
                r1 = ttermino - tinicio + tdelay;
                r2 = r1 / 1000000000;
                r3 =  (1 / r2);
                fsuma =  fsuma + (int) r3;
                contador_tiempo++;
            }else{
                nframe = (int)(fsuma/50);
                r1 = 0;
                r2 = 0;
                r3 = 0;
                fsuma = 0;
                contador_tiempo = 1;
            }
            

    }

    public static void main(String[] args){
        new Proceso().start();
    }

    
}
