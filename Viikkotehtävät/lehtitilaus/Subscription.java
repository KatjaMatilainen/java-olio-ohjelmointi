/*
Tilausten kantaluokka
 */
package lehtitilaus;
import java.util.*;

public abstract class Subscription {
    //kaikille yhteiset muuttujat
    //private String tilauksenTyyppi;
    private String lehden_nimi;
    private String tilaajan_nimi;
    private String toimitusosoite;
    private double kuukausihinta;
    
    //konstruktorit yhteisille muuttujille
    public Subscription(){
        tilaajan_nimi = haeTilaajanNimi();
        toimitusosoite = haeOsoite();
        lehden_nimi = haeLehdenNimi();
        kuukausihinta = haeKuukausiHinta();
    }
    
    //metodit muuttujien palautuselle
    public String palautaLehdenNimi(){return lehden_nimi;}
    public String palautaTilaajanNimi(){return tilaajan_nimi;}
    public String palautaToimitusosoite(){return toimitusosoite;}
    public double palautaKuukausihinta(){return kuukausihinta;}
    
    //hakumetodit yhteisille muuttujille
    private String haeLehdenNimi(){
        System.out.println("\r\nSyota haluamasi lehden tiedot. \r\nLehden nimi: ");
        Scanner reader = new Scanner(System.in);
        lehden_nimi = reader.nextLine();
        return lehden_nimi;
    }
    private String haeTilaajanNimi(){
        System.out.println("\r\nSyota tilaajan tiedot. \r\nTilaajan nimi: ");
        Scanner reader = new Scanner(System.in);
        tilaajan_nimi = reader.nextLine();
        return tilaajan_nimi;
    }
    private String haeOsoite(){
        System.out.println("\r\nSyota toimitusosoite. \r\nKatuosoite: ");
        Scanner reader = new Scanner(System.in);
        String katuosoite = reader.nextLine();
        System.out.println("Postinumero: ");
        Scanner reader2 = new Scanner(System.in);
        String postinumero = reader2.nextLine();
        System.out.println("Postitoimipaikka / kaupunki: ");
        Scanner reader3 = new Scanner(System.in);
        String postitoimipaikka = reader3.nextLine();
        
        toimitusosoite = katuosoite+", "+postinumero+" "+postitoimipaikka;
        return toimitusosoite;
    }
    private double haeKuukausiHinta(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            System.out.println("Lehden normaali kuukausihinta: ");
            try{
                Scanner reader = new Scanner(System.in);
                kuukausihinta = reader.nextDouble();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Subscription.errorMessage();
                kysyUudestaan = true;
            }
            if(kuukausihinta < 0){
                Subscription.errorNegative();
                kysyUudestaan = true;
            }
        }
        return kuukausihinta;
    }
    
    //yleinen virheilmoitus tietojen syotosta vaarassa muodossa (nama varmaan saavat olla tassa)
    static void errorMessage(){
        System.out.println("*** Tarkista, etta syotit tiedot oikeassa muodossa. ");
    }
    //virheilmoitus negatiivisesta arvosta
    static void errorNegative(){
        System.out.println("*** Syota positiivinen arvo. ");
    }

}
