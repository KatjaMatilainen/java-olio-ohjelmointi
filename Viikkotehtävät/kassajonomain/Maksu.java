/*
Paaluokka maksuille
 */
package kassajonomain;
import java.util.*;

public abstract class Maksu {
    private double summa;
    
    //konstruktori
    public Maksu(){
        summa = arvoSumma();
    }
    
    //metodi summan arpomiseksi
    private double arvoSumma(){
        Random rand = new Random();
        //summa on jotain valilta 0-500 euroa
        summa =rand.nextInt(500) + rand.nextDouble();
        return summa;
    }
    //metodi summan palauttamiseksi
    double palautaSumma(){
        return summa;
    }
}
