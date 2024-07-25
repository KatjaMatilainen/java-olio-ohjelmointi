/*
Tavallinen maaraaikainen tilaus.
 */
package lehtitilaus;
import java.util.*;

public class RegularSubscription extends Subscription {
    //luokkakohtainen muuttuja
    private int kkMaara;
    private final String tilauksenTyyppi;
    
    //konstruktorit
    public RegularSubscription(){
        super();
        kkMaara = haeKkMaara();
        tilauksenTyyppi = "Maaraaikainen tilaus";
    }
    
    //palauta kuukausimaara paaohjelmalle
    public int palautaKkMaara(){return kkMaara;}
    //palauta tilauksen tyyppi paaohjelmalle
    public String palautaTilauksenTyyppi(){return tilauksenTyyppi;}
    
    //aliluokan omat metodit
    private int haeKkMaara(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            System.out.println("Tilauksen kesto kuukausina: \r\n"
                    + "(Syota tieto positiivisena kokonaislukuna.)");
            Scanner reader = new Scanner(System.in);
            try{
                kkMaara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Subscription.errorMessage();
                kysyUudestaan = true;
            }
            if(kkMaara < 0){
                Subscription.errorNegative();
                kysyUudestaan = true;
            }
        }
        return kkMaara;
    }
    
}
