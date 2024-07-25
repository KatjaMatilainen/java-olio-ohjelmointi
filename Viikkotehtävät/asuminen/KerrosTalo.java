/*
Rakennuksen aliluokka, omana muuttujana kerrosmaara
 */
package asuminen;
import java.util.*;

public class KerrosTalo extends Rakennus {
//kerrostalon omat muuttujat
    private int kerrosMaara;
    
//konstruktori
    public KerrosTalo(){
        super();
        kerrosMaara = haeKerrosMaara();
    }
    
//metodit
    //palauta kerrosten maara
    public int palautaKerrosMaara(){
        return kerrosMaara;
    }
    //kysytaan kerrostalon kerrosten maara
    private int haeKerrosMaara(){
        boolean kysyUudestaan=true;
        while(kysyUudestaan){
            //uusi scanner
            Scanner reader = new Scanner(System.in);
            //kysytaan kayttajalta
            System.out.println("Syota kerrostalon kerrosten maara: ");
            try{
                kerrosMaara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                //kysytaan uudestaan jos ei ole muotoa int
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
        return kerrosMaara;
    }
    
}
