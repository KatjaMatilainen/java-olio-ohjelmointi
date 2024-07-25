/*
VIIKKOTEHTAVA 3
 */
package lehtitilaus;
import java.util.*;

public class Lehtitilaus {
    //lehtitilauksen muuttuja (maaraaikainen vai kestotilaus)
    int kysy;
    //lehtitilauksen konstruktori (dummy)
    public Lehtitilaus(){}
    
    //paaohjelma
    public static void main(String[] args) {
    //luodaan uusi lehtitilaus
    Lehtitilaus lehtitilaus = new Lehtitilaus();
    lehtitilaus.luoTilaus();
    }
    
   //metodi vaihtoehdon hakemiseksi (kestotilaus vai maaraaikainen tilaus)
   public int haeVaihtoehto(){
       boolean jatka = true;
       while(jatka){
        System.out.println("\r\n--------------------------------------------\r\n"
                + "Valitse haluamasi vaihtoehto:\r\n"
                + "  1) uuden kestotilauksen luominen\r\n"
                + "  2) uuden maaraaikaisen tilauksen luominen\r\n"
                + "  3) ohjelmasta poistuminen\r\n"
                + "--------------------------------------------");
        Scanner reader = new Scanner(System.in);
        try{
            kysy = reader.nextInt();
            jatka = false;
        }catch(InputMismatchException ime){
            System.out.println("Valitse jokin vaihtoehdoista 1-3.");
            jatka = true;
        }
        }
       return kysy;
   }
   
   //metodi usean perakkaisen tilauksen luomiselle
   /*Tahan voisi lisata listan luomisen olioista, jotta kaikki luodut tilaukset 
   tallentuvat muistiin, mutta oikeastaan sita ei mitenkaan vaadittu.*/
   
   public void luoTilaus(){
       boolean jatka = true;
       int vaihtoehto;
       while(jatka){
            vaihtoehto = haeVaihtoehto();
            switch(vaihtoehto){
                case 1:{
                    StandingSubscription kestotilaus = new StandingSubscription();
                    printSubscriptionInvoice(kestotilaus);
                    break;}
                case 2:{
                    RegularSubscription normaalitilaus = new RegularSubscription();
                    printSubscriptionInvoice(normaalitilaus);
                    break;}
                case 3:{
                    System.out.println("Ohjelma lopetetaan.");
                    jatka=false;
                    break;}
                default:{
                    System.out.println("Valitse jokin vaihtoehdoista 1-3.");
                    break;}
            }
       }
   }
    
    //kaikille tilauksille yhteinen laskun tulostava metodi
    static void printSubscriptionInvoice(Subscription subs){
        int kuukaudet;
        int alennus = 0; //default
        kuukaudet = 12; //default
        
        //jos tilaus on maaraaikainen, haetaan kesto ja asetetaan tilaustyyppi
        String tyyppi = "Ei maaritelty";
        if(subs instanceof RegularSubscription){
            kuukaudet = ((RegularSubscription) subs).palautaKkMaara();
            tyyppi = ((RegularSubscription) subs).palautaTilauksenTyyppi();
        }
        
        //jos tilaus on kestotilaus, haetaan alennusprosentti ja asetetaan tilaustyyppi
        if(subs instanceof StandingSubscription){
            alennus = ((StandingSubscription) subs).palautaAlennusProsentti();
            tyyppi = ((StandingSubscription) subs).palautaTilauksenTyyppi();
        }
        
        //lasketaan tilaukselle lopullinen hinta
        double hinta = (double)kuukaudet * subs.palautaKuukausihinta() * ((double)100-(double)alennus)/(double)100;
        
        System.out.println("\r\n------------------------\r\n"
                + "Tilauksen tiedot: \r\n------------------------"
                + "\r\nTilaajan nimi: "+subs.palautaTilaajanNimi()
                + "\r\nToimitusosoite: "+subs.palautaToimitusosoite()
                + "\r\nTilauksen tyyppi: "+tyyppi
                + "\r\nLehden nimi: "+subs.palautaLehdenNimi());
        //muotoitu tulostus hinnalle
        System.out.printf("Kuukausihinta euroina: %5.2f%n", subs.palautaKuukausihinta());
        System.out.println("Laskutuskauden kesto: "+kuukaudet+" kk"
                + "\r\nKestotilauksen alennusprosentti: "+alennus+" %");
        //muotoiltu tulostus hinnalle
        System.out.printf("Tilauksen kokonaishinta euroina: %5.2f%n",hinta);
    }
}