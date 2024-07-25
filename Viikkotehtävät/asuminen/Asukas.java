package asuminen;
import java.util.*;

public class Asukas{
    //muuttuja
    private String nimi;
    
    //konstruktori
    public Asukas(int nro){
        nimi = getName(nro);
    }
    
    //metodit
//nimen metodit
    //palauta nimi paaohjelmalle
    public String palautaNimi(){return nimi;}
    //hae nimi kayttajalta
    private String getName(int nro){
        boolean kysy = true;
        String saatunimi = "Ei maaritelty";
        while(kysy){
            //uusi scanner
            Scanner reader = new Scanner(System.in);
            //kysy kayttajalta
            System.out.println("Syota asukkaan "+nro+" nimi: ");
            saatunimi = reader.nextLine();
                kysy = false;
            if(saatunimi.length()==0){
                Tontti.printInputTypeError();
                kysy = true;
            }
        }
        
        //muokataan etunimiin ja sukunimeen isot alkukirjaimet
        String[] nimilista= saatunimi.split(" ");
        String kokonimi="";
        for(String nimenosa: nimilista){
            String osa="";
            //jos alkio on tyhja, sita ei yriteta muokata
            if(nimenosa.equals("")){}
            //muussa tapauksessa muutetaan iso alkukirjain
            else{
                osa = nimenosa.substring(0,1).toUpperCase()+nimenosa.substring(1).toLowerCase()+" ";
            }
            kokonimi=kokonimi+osa;
        }
        
        //palautetaan nimi
        return kokonimi;
    }
    
}