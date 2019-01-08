package cl.fuentes.batch.tool;

public class Tiempo {

    static long t1, t2;
    static long tres;
    
    
    public static void main(String[] args) {
    
        t1 = System.nanoTime();
        System.out.println("Hola Mundo");
        t2 = System.nanoTime();
        tres = t2-t1;
        System.out.println("tdif : " + tres);
        
        
    }

    
}
