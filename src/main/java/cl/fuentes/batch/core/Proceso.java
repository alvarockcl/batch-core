package cl.fuentes.batch.core;


public class Proceso implements Runnable
{

    private Thread proceso = null;
    private boolean activo = false;
    private int ejecuciones = 1;

    public Proceso(){
        start();
    }

    @Override
    public void run() {
        while(activo){
            renderProceso();
            ejecuciones++;
            if(ejecuciones == 1000000000){
                stop();
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

    public static void main(String[] args){
        new Proceso().start();
    }

    public void renderProceso(){
        System.out.println("Ejecución : " + ejecuciones);
    }
}
