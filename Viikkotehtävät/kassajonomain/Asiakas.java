/*
Jonottava asiakas
 */
package kassajonomain;
import java.util.*;

public class Asiakas {
    private final String asiakasnumero;
    private final String maksutapa;
    private final double maksuSumma;
    private final boolean kortti;
    private String kortinnumero;
    
    public Asiakas(){
        //arvotaan asiakasnumero
        asiakasnumero =arvoAsiakasnumero();
        
        //arvotaan, onko kyseessa kortti- vai kateisasiakas
        Random rand = new Random();
        kortti = rand.nextBoolean();
        if(kortti){
            maksutapa = "Luottokorttiasiakas";
            LuottokorttiMaksu uusiMaksu = new LuottokorttiMaksu();
            maksuSumma = uusiMaksu.palautaSumma();
            kortinnumero = uusiMaksu.palautaKortinNumero();
            
        }else{
            maksutapa = "Kateisasiakas";
            KateisMaksu uusiMaksu = new KateisMaksu();
            maksuSumma = uusiMaksu.palautaSumma();
        }
    }
    
    //arvotaan asiakasnumero ja tehdaan siita padded string
    private String arvoAsiakasnumero(){
        Random rand = new Random();
        int arvottu = rand.nextInt(100000);
        String numero = String.format("%05d", arvottu);
        return numero;
    }
    
    //metodi tietojen tulostamiseksi
    public void tulostaTiedot(int kuittinro){
        System.out.println("-----------------------------------\r\n"
                + "| #"+String.format("%03d",kuittinro)+"      KUITTI                |\r\n"
                + "-----------------------------------"
                + "\r\n| Asiakasnumero: "+asiakasnumero+"\r\n"
                + "| Maksutapa: "+maksutapa);
        
        //jos kyseessa on korttimaksu, tulostetaan myos kortin numero
        if(kortti){System.out.println("| Luottokortin numero: "+kortinnumero);}
        
        System.out.printf("| Summa euroina: %5.2f%n",maksuSumma);
        System.out.println("-----------------------------------");
        
    }
}