/*
Viikkotehtava 6: hirsipuu
 */
package hirsipuumain;
import java.util.*;
//import java.io.*;

public class HirsipuuMain {
    //int arvaustenMaara;
    
    public static void main(String[] args) {
        boolean jatka = true;
        while(jatka){
            
        //tulostetaan tervetuloa -viesti
            tulostaAloitusViesti();
            
        //sanalistan luominen annetusta tiedostonimesta
            //haetaan tiedoston nimi kayttajalta
            String tiedostonnimi = Sanalista.haetiedostonnimi();
            //luodaan uusi Sanalista-objekti
            Sanalista uusiSanalista = new Sanalista(tiedostonnimi);
            
            //haetaan luodusta objektista lista hirsipuun sanoista
            List<String> lista = uusiSanalista.annaSanat();
            //jos tiedostosta lukeminen epaonnistui, keskeytetaan ohjelma
            if(lista==null){break;}
        
        //pelin vaikeustason kysyminen, sanalistan muokkaaminen sen mukaan
            //kysy kayttajalta, minka pituisia sanoja han haluaa arvata
            List<Integer> pituudet = haePituusVali();
                //sanity check
                //System.out.println("Valitsit sanojen pituudet "+pituudet);
            //kysy kuinka monta arvausta annetaan
            int arvaustenMaara = haeArvaustenMaara();
            //muodosta sanalista halutusta pituusvalista
            Sanalista yhdistetty = uusiSanalista.yhdistaListat(pituudet);
        
        
        //hirsipuun pelaaminen valitulla sanalistalla
        Hirsipuu uusipeli = new Hirsipuu(yhdistetty,arvaustenMaara);
        String vihje=uusipeli.palautaVihje();
        System.out.println("Sanan pituus on: "+vihje.length()+" kirjainta."
                + "\r\nVihje: "+vihje);
        
            //toistetaan kunnes oikea vastaus tai kuolema
            boolean lopeta = false;
            boolean voitto = false;
            boolean havio = false;
            while(!lopeta){
                //hae kirjain
                char arvaus = haeKirjain();
                //jos arvaukseksi syotetaan 0, annetaan kayttajan arvata koko sana
                if(arvaus=='0'){
                    voitto = arvausOikein(uusipeli.sana());
                    if(voitto){
                        tulostaVoittoTiedot();
                        lopeta = voitto;
                        break;
                    }
                }
                
                //suorita arvaus
                uusipeli.arvaa(arvaus);
                //tulosta paivitetty vihje
                vihje = uusipeli.palautaVihje();
                System.out.println("Vihje: "+vihje);
                //tarkasta,voitettiinko
                voitto = uusipeli.onLoppu();
                if(voitto){
                    tulostaVoittoTiedot();
                    lopeta = voitto;
                    break;
                }
                
                //tarkasta, havittiinko
                havio = uusipeli.onHavio();
                if(havio){
                    tulostaHavioTiedot();
                    lopeta = havio;
                    break;
                }
            }
            
        //tulostetaan lopuksi oikea vastaus
        System.out.println("Oikea vastaus on: "+uusipeli.sana());
        
        //kysytaan, haluaako pelaaja poistua vai arvata uuden sanan
            jatka = jatketaanko();
        }
    }

    
//PAAOHJELMAN HirsipuuMain metodit
    
//vaikeustason valinta
    //hae haluttu sanojen pituusvali
    static List<Integer> haePituusVali(){
        int vaihtoehto = 1;
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            //kysytaan vaihtoehto
            System.out.println(""
                + "Valitse haluamasi vaikeustaso: \r\n"
                + "  1 - HELPPO, sanojen pituus alle 5 kirjainta,\r\n"
                + "  2 - KESKIVERTO, sanojen pituus 5-10 kirjainta,\r\n"
                + "  3 - VAIKEA, sanojen pituus 10-20 kirjainta,\r\n");
                //+ "  4 - HARDCORE, sanojen pituus yli 20 kirjainta.");
            Scanner reader = new Scanner(System.in);
            try{
                vaihtoehto = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                inputTypeError();
                kysyUudestaan = true;
            }
            if(vaihtoehto<1 || vaihtoehto>3){
                inputTypeError();
                kysyUudestaan = true;
            }
        }
        //lista pituuksia varten
        List<Integer> indeksilista;// = new ArrayList<>();
        switch(vaihtoehto){
            case 1:{
                indeksilista = new ArrayList<>(Arrays.asList(0,1,2,3));
                break;
            }
            case 2:{
                indeksilista = new ArrayList<>(Arrays.asList(4,5,6,7,8,9));
                break;
            }
            case 3:{
                indeksilista = new ArrayList<>(Arrays.asList(10,12,13,14,15,16,17,18,19));
                break;
            }
            default:{
                indeksilista = new ArrayList<>(Arrays.asList(4,5,6,7,8,9));
                break;
            }
        }
        
        return indeksilista;
    }
    //hae haluttu arvausten maara
    static int haeArvaustenMaara(){
        boolean kysyUudestaan = true;
        //default arvo on 5
        int maara=5;
        //kysytaan uudestaan jos syotetaan vaarassa muodossa
        while(kysyUudestaan){
            System.out.println("VALITSE VIELA ARVAUSTEN MAKSIMIMAARA "
                    + "\r\n(kokonaisluku valilta 1-25).");
            Scanner reader = new Scanner(System.in);
            try{
                maara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                inputTypeError();
                printInstructions();
                kysyUudestaan = true;
            }
        
            if(maara<1 || maara>25){
                kysyUudestaan=true;
                printInstructions();
            }
        }
        return maara;
    }
    
//pelaamisen metodit
    //hae kayttajalta arvattava kirjain
    static char haeKirjain(){
        boolean kysyUudestaan = true;
        //default arvo
        char kirjain=' ';
        while(kysyUudestaan){
            System.out.println("\r\nSyota arvaus (yksittainen kirjain) tai valitse 0 arvataksesi koko sanan: ");
            try{ //kysytaan uudestaan kunnes input on oikeaa muotoa
                Scanner reader = new Scanner(System.in);
                //input ei voi olla character, joten luetaaan string-muodossa
                String input = reader.next();
                //arvattava kirjain on input-stringin ensimmainen alkio
                kirjain = input.charAt(0);
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                //koska kyseessa on string, tata ei periaatteessa tarvittaisi
                inputTypeError();
                kysyUudestaan = true;
            }
            if(kirjain==' '){
                inputTypeError();
                kysyUudestaan = true;
            }
        }
        
        return kirjain;
    }
    //arvaa koko sana kerralla
    static boolean arvausOikein(String oikeavastaus){
        //oletusarvo = vaara vastaus
        boolean oikein = false;
        //uusi scanneri
        Scanner reader = new Scanner(System.in);
        //kysytaan kayttajalta arvaus
        System.out.println("Valitsit koko sanan arvauksen! Syota vastauksesi!");
        String arvaus = reader.nextLine().toLowerCase();
        if(arvaus.equals(oikeavastaus)){
            oikein = true;
            System.out.println("Arvasit oikein!");
        }else{
            System.out.println("Vastasit vaarin. Vastaamiseen kului yksi yrityskerroista.");
        }
        return oikein;
    }
    
    //kysy, arvataanko toinen sana
    static boolean jatketaanko(){
        //jatketaanko pelia
        boolean jatka = true;
        //kysytaanko tieto uudestaan
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            System.out.println(""
                    + "\r\n---------------------------------------------------------"
                    + "\r\n Haluatko jatkaa pelaamista? "
                    + "\r\n Syota 'kylla' jatkaaksesi tai 'ei' lopettaaksesi pelin."
                    + "\r\n---------------------------------------------------------");

            //uusi scanneri
            Scanner reader = new Scanner(System.in);
            String input = reader.nextLine().toLowerCase();
            switch (input) {
                case "ei":
                    jatka = false;
                    kysyUudestaan = false;
                    break;
                case "kylla":
                    jatka = true;
                    kysyUudestaan = false;
                    break;
                default:
                    kysyUudestaan = true;
                    inputTypeError();
                    break;
            }
        }
        
        return jatka;
    }
    
//viestit
    //tervetuloviesti
    static void tulostaAloitusViesti(){
        System.out.println("\r\n"
                + "----------------------------------------\r\n"
                + "   HIRSIPUU Java deluxe 2016 beta v1.0  \r\n"
                + "----------------------------------------\r\n"
                + "     Game of the year -edition\r\n");
    }
    
    //voittoilmoitus
    static void tulostaVoittoTiedot(){
        //lisaa tahan, milla vaikeustasolla voitettiin
        System.out.println("Voitit pelin.");
    }
    //havioilmoitus
    static void tulostaHavioTiedot(){
        //lisaa tahan, milla vaikeustasolla havittiin
        System.out.println("Havisit pelin.");
    }
    
    //virheilmoitukset
    static void inputTypeError(){
        System.out.println("**** Tarkista, etta syotit tiedot oikeassa muodossa. ****");
    }
    static void printInstructions(){
        System.out.println("Anna kokonaisluku valilta 0-25");
    }
}
