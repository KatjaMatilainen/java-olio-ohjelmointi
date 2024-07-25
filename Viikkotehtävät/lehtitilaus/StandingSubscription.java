/*
Kestotilaus -luokka
 */
package lehtitilaus;
import java.util.*;

public class StandingSubscription extends Subscription {       
    //luokkakohtainen muuttuja
    private int alennusProsentti;
    private final String tilauksenTyyppi;
    
    //konstruktorit
    public StandingSubscription(){
        super();
        alennusProsentti = haeAlennusProsentti();
        tilauksenTyyppi = "Kestotilaus";
    }
    
    //palauta alennusprosentti paaohjelmalle
    public int palautaAlennusProsentti(){return alennusProsentti;}
    //palauta tilauksen tyyppi paaohjelmalle
    public String palautaTilauksenTyyppi(){return tilauksenTyyppi;}
    
    //hae kayttajalta alennusprosentti
    private int haeAlennusProsentti(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            System.out.println("Kestotilauksen alennusprosentti: \r\n"
                    + "(Kokonaisluku valilta 0-100.)");
            Scanner reader = new Scanner(System.in);
            try{
                alennusProsentti = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Subscription.errorMessage();
                kysyUudestaan = true;
            }
            if(alennusProsentti > 100){
                errorOver100();
                kysyUudestaan = true;
            }
            if(alennusProsentti < 0){
                Subscription.errorNegative();
                kysyUudestaan = true;
            }
        }
        return alennusProsentti;
    }
    
    //virheilmoitus jos alennusprosentti on yli 100 (tarvitaan vain kestotilauksessa)
    static void errorOver100(){
        System.out.println("\r\nAlennus ei voi olla yli 100%.");
    }
}
