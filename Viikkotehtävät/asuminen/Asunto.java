/*
Asunto -luokka
 */
package asuminen;
import java.util.*;

public class Asunto {
    //muuttujat
    public int huoneidenMaara;
    public double asunnonAla;
    int asukasMaara;
    List<Asukas> asukaslista;
    
    //konstruktorit
    public Asunto(int nro){
        huoneidenMaara = haeHuoneidenMaara(nro);
        asunnonAla = haeAsunnonAla(nro);
        asukasMaara = haeAsukasMaara(nro);
        
        int maara = asukasMaara;
        asukaslista = luoAsukasLista(maara);
    }
        
    //metodit asunnon tietojen hakemiseksi ja tulostamiseksi
//asukkaat
    //hae asukkaiden maara
    private int haeAsukasMaara(int nro){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            //uusi scanner
            Scanner reader = new Scanner(System.in);
            //kysy kayttajalta
            System.out.println("Syota asunnon "+nro+" asukasmaara: ");
            try{
                asukasMaara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
        
        return asukasMaara;
    }
    //luo asukaslista
    private List<Asukas> luoAsukasLista(int maara){
        //tyhja lista
        List<Asukas> lista = new LinkedList<>();
        //luodaan siihen asukkaat
        for(int i=0; i<maara; i++){
           Asukas uusiAsukas = new Asukas(i+1);
           lista.add(uusiAsukas);
        }
        return lista;
    }
    //tulosta asukaslista
    protected void tulostaAsukasLista(Asunto asunto){
        //indeksi sille, monennessako asukkaassa ollaan menossa
        int index = 0;
        System.out.println("\r\nAsukkaiden tiedot: ");
        //kaytetaan listan omia komentoja
        for(Asukas a: asunto.asukaslista){
            index++;
            System.out.print("   Asukkaan "+index+" nimi on: "+a.palautaNimi()+"\r\n");
        }
    }
//muut asunnon tiedot
    //hae asunnon pinta-ala
    private double haeAsunnonAla(int nro){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            //uusi scanner
            Scanner reader = new Scanner(System.in);
            //haetaan arvo kayttajalta
            System.out.println("Syota asunnon "+nro+" pinta-ala neliometreina: ");
            try{
                asunnonAla = reader.nextDouble();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                //jos input ei ole muotoa double, kysytaan uudestaan
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
    return asunnonAla;
    }
    //hae asunnon huoneiden maara
    private int haeHuoneidenMaara(int nro){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            //uusi scanneri
            Scanner reader = new Scanner(System.in);
            //kysytaan huoneiden maara
            System.out.println("---------------------------------------\r\n"
                    + "      ASUNTO "+nro+"\r\n"
                    + "---------------------------------------\r\n"
                    + "Syota asunnon "+nro+" huoneiden maara: ");
            try{
                huoneidenMaara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
    return huoneidenMaara;
    }
}