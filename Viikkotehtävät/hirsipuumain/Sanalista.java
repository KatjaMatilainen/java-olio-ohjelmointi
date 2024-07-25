/*
Sanalista -luokka.
Saa parametrina tekstitiedoston nimen.
Lukee sanat tekstitiedostosta listaan.
 */
package hirsipuumain;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Sanalista {
    //muuttujat
    private List<String> sanalista;
    private final String tiedosto;
    
    //konstruktori, saa parametrina tiedostonnimen
    public Sanalista(String tiedostonnimi){
        tiedosto = tiedostonnimi;
        sanalista = lueSanatTiedostosta(tiedosto);
    }
    

//Sanalista -luokan metodit alkavat tasta
    
//paaohjelmalle palauttavat metodit
    //palauttaa sanalistan paaohjelmalle, ei muita funktioita
    public List<String> annaSanat (){
        return sanalista;
    }
    
//sanalistan luomisessa avustavat metodit
    //hae kayttajalta tekstitiedoston nimi
    static String haetiedostonnimi(){
        System.out.println("Anna arvattavat sanat sisaltavan tekstitiedoston nimi: ");
        Scanner reader = new Scanner(System.in);
        String nimi = reader.nextLine();
        return nimi;
    }
    //lukee sanat tiedostosta
    private List<String> lueSanatTiedostosta(String tiedosto){
        //luodaan uusi tyhja lista johon luetaan sanat
        List<String> lueSanalista = new LinkedList<>();
        //tehdaan uusi sanalista, jonne tallennetaan muotoillut sanat
        List<String> muokattuLista = new LinkedList<>();
        
        //ladataan utf-8 character set
        Charset utf8 = Charset.forName("UTF-8");
        //lisataan annettuun tiedostonnimeen peraan tiedostopaate
        String tiedostonnimi = tiedosto+".txt";
        
        //avataan haluttu tekstitiedosto
        try(BufferedReader file = new BufferedReader(new InputStreamReader(
            new FileInputStream(tiedostonnimi),utf8))){
            //luetaan rivi ja tallennetaan se muuttujaan
            String rivi;
            //toistetaan kunnes kohdataan tiedoston loppu
            while( (rivi = file.readLine()) != null ){
                //kaydaan rivilta lapi sanat ja tallennetaan ne arrayhyn arr
                String[] arr = rivi.split(" ");
                //siirretaan rivin sanat arraysta listaan
                lueSanalista.addAll(Arrays.asList(arr));
            }
            //muutetaan kaikki kirjaimet pieniksi kirjaimiksi
            for(String sana: lueSanalista){
                muokattuLista.add(sana.toLowerCase());
            }
                
        //tulostetaan virheilmoitus, jos tiedostoa ei loydy
        }catch(IOException ioe){
            //current directory
            Path currentRelativePath = Paths.get("");
            String path = currentRelativePath.toAbsolutePath().toString();
            //error message
            System.out.println("\r\n*** Virhe arvattavien sanojen lukemisessa! ***\r\n"
                    + "Tarkista seuraavat asiat ja kaynnista peli uudestaan:"
                    + "\r\n  - Onko tiedosto muotoa 'tiedostonnimi.txt'?"
                    + "\r\n  - Syotitko tiedoston nimen oikein? Jata tiedoston paate '.txt' "
                    + "\r\n    kirjoittamatta ja anna pelkka tiedoston nimi 'tiedostonnimi'."
                    + "\r\n  - Sijaitseeko tiedosto samassa kansiossa ohjelman kanssa?"
                    + "\r\n    ("+path+")\r\n");
            
            //jos tiedostoa ei saada luettua oikein, palauta tyhja lista
            muokattuLista=null;
        }
        
    //palautetaan lista
    return muokattuLista;
    }

//sanalistaa muokkaavat metodit
    //palauttaa sanat joiden pituus on int pituus
    public Sanalista sanatJoidenPituusOn(int pituus){
        //luodaan uusi objekti
        Sanalista uusiSanalista = new Sanalista(tiedosto);
        
        //tehdaan uusi sanalista johon lisataan vain tietyn pituiset sanat
        List<String> muokattuSanalista = new LinkedList<>();
        for(String sana: uusiSanalista.sanalista){
            if(sana.length() == pituus){muokattuSanalista.add(sana);}
        }
        
        //tallennetaan uusi lista objektiin alkuperaisen listan tilalle
        uusiSanalista.sanalista = muokattuSanalista;
        //palautetaan muokattu objekti
        return uusiSanalista;
    }
    //palauttaa sanat, joissa on tietyt merkit parametrin maaraamissa kohdissa
    public Sanalista sanaJoissaMerkit(String mjono){
        //luodaan uusi Sanalista -objekti merkkijonon mjono pituisista sanoista
        int pituus = mjono.length();
        Sanalista lista = new Sanalista(tiedosto);
        Sanalista uusiSanalista = lista.sanatJoidenPituusOn(pituus);
        
        //tehdaan uusi string-lista
        List<String> muokattuSanalista = uusiSanalista.annaSanat();
        
        //boolean match
        boolean remove = false;
        //poistetaan iteraattorilla listasta sanat, jotka eivat tayta ehtoa
        Iterator<String> iter = muokattuSanalista.iterator();
        while(iter.hasNext()){
            String sana = iter.next();
            //jatkamisen ehto: merkki nro i merkkijonossa mjono on '_'
            //tai sama kirjain kuin vertailtavassa sanassa siina kohti
            for(int i=0; i<pituus; i++){
                if(mjono.substring(i,i+1).equals("_") || mjono.substring(i,i+1).equals(sana.substring(i,i+1))){
                    remove = false;
                }else{
                    remove = true;
                    //siirrytaan seuraavaan sanaan heti jos yksikin kirjain on eri
                    break;
                }
            }
            //jos joku paljastetuista kirjaimista ei ollut sama, poistetaan sana
            if(remove){iter.remove();}
        }
        
        //tallennetaan uusi lista objektiin alkuperaisen listan tilalle
        uusiSanalista.sanalista = muokattuSanalista;
        //palautetaan muokattu objekti
        return uusiSanalista;
    }
    //muodosta sanalista halutusta pituusvalista
    public Sanalista yhdistaListat(List<Integer> pituudet){
        Sanalista uusiSanalista = new Sanalista(tiedosto);
        List<String> yhdistettyLista = new LinkedList<>();
        for(int pituus: pituudet){
            Sanalista uusiOsaLista = uusiSanalista.sanatJoidenPituusOn(pituus);
            yhdistettyLista.addAll(uusiOsaLista.annaSanat());
        }
        //tunge muokattu lista takaisin objektiin
        uusiSanalista.sanalista = yhdistettyLista;
            
        //palauta sanalista
        return uusiSanalista;
    }
}
