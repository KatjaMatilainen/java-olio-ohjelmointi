/*
Hirsipuu -luokka itse pelin pelaamiseen
 */
package hirsipuumain;
import java.util.*;
/*
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;*/

public class Hirsipuu {
    //kaytettava sanalista
        Sanalista sanalista;
    //oikea vastaus (arvotaan listasta)
        String vastaus;
    //vastauksen pituus
        int vastauksenPituus;
    //arvausten maksimimaara
        int arvauksiaJaljella;
    //arvatut merkit
        List<Character> arvatutMerkit;
    //paivitettava vihje
        String vihje;
        
    //konstruktorit
    public Hirsipuu(Sanalista lista, int arvausmaara){
        sanalista = lista;
        vastaus = arvoVastaus();
        vastauksenPituus = vastaus.length();
        arvauksiaJaljella = arvausmaara;
        arvatutMerkit = new ArrayList<>();
        vihje = teeVihje();
    }
    
    //metodit
    
    //arvo, mika sanoista valitaan
    private String arvoVastaus(){
        //lista vaihtoehdoista
        List<String> vaihtoehdot = sanalista.annaSanat();
        //listan pituus
        int listanpituus = vaihtoehdot.size();
        //arvotaan kokonaisluku valilta 0-listanpituus
        int arvottuluku = new Random().nextInt(listanpituus);
        
        //valitaan listasta alkio, jonka indeksi on arvottu luku
        vastaus = vaihtoehdot.get(arvottuluku);
        //palautetaan valittu vastaus
        return vastaus;
    }
    
    //verrataan annettua merkkia arvattavaan sanaan ja paivitetaan tiedot
    public boolean arvaa(Character merkki){
        //lisataan alkio arvatut merkit -listaan
        arvatutMerkit.add(merkki);
        
        //muutetaan character string-muotoiseksi pieneksi kirjaimeksi
        String m = merkki.toString().toLowerCase();
        
        //array jarjestysluvuista, joilla tietty merkki loytyy vastauksesta
        List<Integer> indeksilista = new ArrayList<>();
        
        //etsitaan, loytyyko kirjainta vastauksesta
        boolean vertaa = false;
        for(int i=0; i<vastauksenPituus; i++){
            if(m.equals(vastaus.substring(i,i+1))){
                //jos kirjain loydetaan, ilmoitetaan sen loytyminen
                vertaa = true;
                //laitetaan muistiin, monesko merkki oli kyseessa
                indeksilista.add(i);
            }
        }
        //jos kirjain loydettiin, tulostetaan mista kohdista se loytyi
        if(vertaa){
                //sanity check, vihjetta on vaikea lukea
                //System.out.println("\r\nVastauksen kirjaimet nro "+indeksilista+" ovat merkkia "+m);
            for(Integer indeksi: indeksilista){
                vihje = vihje.substring(0,indeksi)+m+vihje.substring(indeksi+1,vastauksenPituus);
            }
            System.out.println("\r\nArvauksia on jaljella "+arvauksiaJaljella+" kpl."
                    + "\r\nTahan mennessa tehdyt arvaukset ovat: "+arvatutMerkit);
            
        }else{
            //vahennetaan jaljella olevien arvausten maaraa yhdella
            arvauksiaJaljella--;
            //tulostetaan tehdyt arvaukset
            System.out.println("\r\nArvauksia on jaljella "+arvauksiaJaljella+" kpl."
                    + "\r\nTahan mennessa tehdyt arvaukset ovat: "+arvatutMerkit);
        }
        
        return vertaa;
    }
    
//arvausten metodit
    //palauta tehdyt kirjain-arvaukset listana
    public List<Character> arvaukset(){
        return arvatutMerkit;
    }
    //palauta jaljella olevien arvausten maara
    public int arvauksiaOnJaljella(){
        return arvauksiaJaljella;
    }
    
//vihjeen metodit
    //muodosta alussa vihje
    private String teeVihje(){
        vihje = "";
        for(int i=0; i<vastauksenPituus; i++){
            vihje=vihje+"_";
        }
        return vihje;
    }
    //palauta vihje
    public String palautaVihje(){
        return vihje;
    }
    
//vertaa vihjetta oikeaan vastaukseen
    //tarkista, voitettiinko
    public boolean onLoppu(){
        boolean loppu = false;
        //peli loppuu, jos arvataan sana
        if(vihje.equals(vastaus)){
            loppu = true;
        }
        return loppu;
    }
    //tarkista,havittiinko (onko arvauksia jaljella)
    public boolean onHavio(){
        boolean havio = false;
        if(arvauksiaJaljella == 0){
            havio = true;
        }
        return havio;
    }
    
    //palauta oikea vastaus
    public String sana(){
        return vastaus;
    }
}
