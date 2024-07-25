package asuminen;
import java.util.*;

public class Tontti {
    //tontti -luokan muuttujat
    private String tontinNimi;
    private double tontinAla;
    private final String tontinOsoite;
    Rakennus tontinRakennus;

    //palauta private-muuttujien arvot paaohjelmalle
    String returnName(){return tontinNimi;}
    double returnAla(){return tontinAla;};
    
    //tontin konstruktorit
    public Tontti(){
        tontinNimi = haeTontinNimi();
        tontinAla = haeTontinAla();
        tontinOsoite = haeTontinOsoite();
        
        //luo tontille rakennus
        tontinRakennus = haeTontinRakennus();
    }
    
    //metodit 
    
//tontin muuttujen hakeminen kayttajalta
    //haetaan tontin nimi
    private String haeTontinNimi(){
        String haettuNimi="Ei maaritelty";
        boolean kysy = true;
        while(kysy){
            //luodaan uusi scanneri
            Scanner reader = new Scanner(System.in);
            //kysytaan nimi
            System.out.println("Syota tontin nimi: ");
            //tallennetaan nimi-muuttujaan
            haettuNimi = reader.nextLine();
                kysy = false;
            //kysytaan uudestaan jos syotetaan tyhjaa
            if(haettuNimi.length()==0){
                Tontti.printInputTypeError();
                kysy = true;
            }
        }
        //capitalize
        tontinNimi = haettuNimi.substring(0,1).toUpperCase()+haettuNimi.substring(1).toLowerCase();
        //palautetaan arvo
        return tontinNimi;
    }
    //haetaan osoite vaihe kerrallaan ja muotoillaan lopputulos
    private String haeTontinOsoite(){
        String luettukatuosoite="Ei maaritelty";
        boolean kysy = true;
        while(kysy){
            //uusi scanneri
            Scanner reader = new Scanner(System.in);
            //kysytaan katuosoita
            System.out.println("Syota tontin osoite:\r\nKatuosoite: ");
            //luetaan nappaimistolta input
            luettukatuosoite = reader.nextLine();
                kysy = false;
            //jos syotetaan tyhja string, kysytaan uudestaan
            if(luettukatuosoite.length()==0){
                Tontti.printInputTypeError();
                kysy = true;
            }
        }
        
        //muutetaan ensimmainen kirjain isoksi alkukirjaimeksi
        String katuosoite = luettukatuosoite.substring(0,1).toUpperCase()+luettukatuosoite.substring(1).toLowerCase();
        
        
        //kysytaan postinumero
        int kysypostinumero=0;
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            try{
                Scanner reader2 = new Scanner(System.in);
                //kysytaan kayttajalta arvo
                System.out.println("Postinumero: ");
                //tallennetaan muuttujaan
                kysypostinumero = reader2.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                //kysytaan uudestaan, jos input ei ole muotoa int
                printInputTypeError();
                kysyUudestaan = true;
            }
        }
        //muutetaan padded stringiksi
        String postinumero = String.format("%05d", kysypostinumero);
        
        boolean kysykaupunki = true;
        String kaupunki="Ei maaritelty";
        while(kysykaupunki){
            //tehdaan uusi reader
            Scanner reader3 = new Scanner(System.in);
            //kysytaan postitoimipaikka
            System.out.println("Kaupunki / postitoimipaikka: ");
            //tallennetaan muuttujaan
            kaupunki = reader3.nextLine();
                kysykaupunki = false;
            if(kaupunki.length()==0){
                Tontti.printInputTypeError();
                kysykaupunki = true;
            }
        }
        
        //yhdistetaan palat yhdeksi osoitteeksi
        String osoite=katuosoite+", "+postinumero+" "+kaupunki.toUpperCase();
        //palautetaan lopullinen arvo
        return osoite;
    }
    private double haeTontinAla(){
        //kysytaan uudestaan jos syotetaan jotain muuta kuin luku
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            //uusi scanneri
            Scanner reader = new Scanner(System.in);
            //kysytaan pinta-ala
            System.out.println("Syota tontin pinta-ala hehtaareina: ");
            try{//tarkistetaan, onko oikeaa muotoa
                tontinAla = reader.nextDouble();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
        return tontinAla;
    }
    
    //tehdaan tontille rakennus
    private Rakennus haeTontinRakennus(){
        //vaihtoehdon default arvo = kerrostalo
        int vaihtoehto=1;
        boolean kysyUudestaan = true;
        
        while(kysyUudestaan){
            //uusi scanneri
            Scanner reader = new Scanner(System.in);
            //haetaan kayttajalta rakennuksen tyyppi
            System.out.println("Valitse tontilla olevan rakennuksen tyyppi: \r\n"
                    + "  1 - Kerrostalo,"
                    + "  2 - Rivitalo,"
                    + "  3 - Omakotitalo");
            //kysytaan vaihtoehto uudestaan jos annetaan vaara arvo
            try{
                vaihtoehto = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                //kysytaan uudestaan, jos input ei ole muotoa int
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
            if(vaihtoehto<0 || vaihtoehto>3){
                //kysytaan uudestaan, jos annettu luku ei ole 1-3
                kysyUudestaan = true;
            }
        }
        
        //tehdaan annetun vaihtoehdon mukaisesti rivi-/kerros-/tai omakotitalo
        switch(vaihtoehto){
            case 1:{
                tontinRakennus = new KerrosTalo();
                break;
            }
            case 2:{
                tontinRakennus = new RiviTalo();
                break;
            }
            case 3:{
                tontinRakennus = new OmakotiTalo();
                break;
            }
            default:{
                tontinRakennus = new KerrosTalo();
                break;
            }
        }
    return tontinRakennus;
    }
    
    //metodi tontin tietojen tulostamiseksi
    public void tulostaTontinTiedot(){
        System.out.println("\r\n-----------------------------------------\r\n"
                + "      Tontin tiedot ovat: \r\n"
                + "-----------------------------------------"
                + "\r\nNimi:            "+tontinNimi
                + "\r\nOsoite:          "+tontinOsoite);
        System.out.printf("Pinta-ala (hA): %5.2f%n",tontinAla);
        tontinRakennus.tulostaRakennuksenTiedot(tontinRakennus);
    }
    
    //virheilmoitukset
    public static void printInputTypeError(){
        System.out.println("*** Tarkasta, etta syotit tiedot oikeassa muodossa! ***");
    }
    
}