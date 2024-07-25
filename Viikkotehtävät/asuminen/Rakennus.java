/*
Rakennusten paaluokka
 */

package asuminen;
import java.util.*;

public abstract class Rakennus {
    //rakennus -luokkien yhteiset muuttujat
    private int asuntojenMaara; //asuntojen maara
    List<Asunto> asuntolista; //lista asunnoista
    
    //rakennuksen konstruktorit
    public Rakennus(){
        asuntojenMaara = haeAsuntojenMaara();
        //varmistaako tama, etta asuntojen maaraa 
        //ei haeta uudestaan kun asuntolista luodaan?
        int maara = asuntojenMaara;
        asuntolista = muodostaAsuntoLista(maara);
    }

    //metodit rakennuksen tietojen hakemiseksi
    private int haeAsuntojenMaara(){
        //kysytaan uudestaan, jos input ei ole muotoa int
        boolean kysyUudestaan = true;
        
        while(kysyUudestaan){
            //uusi scanner
            Scanner reader = new Scanner(System.in);
            //kysytaan asuntojen maara
            System.out.println("Syota asuntojen maara: ");
            
            try{
                asuntojenMaara = reader.nextInt();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Tontti.printInputTypeError();
                kysyUudestaan = true;
            }
        }
        return asuntojenMaara;
    }
    //asuntolistan luominen
    private List<Asunto> muodostaAsuntoLista(int asuntojenMaara){
        //tyhja asuntolista
        List<Asunto> lista = new LinkedList<>();
        
        //luodaan asuntojen maaran verran asuntoja listaan
        for(int i=0; i<asuntojenMaara; i++){
            Asunto uusiAsunto = new Asunto(i+1);
            lista.add(uusiAsunto);
        }
    return lista;
    }
    //asuntolistan tulostus
    private void tulostaAsuntoLista(Rakennus rakennus){
        //laskuri sille, monennessako asunnossa tulostus on menossa
        int index=0;
        //kaytetaan listan omia operaatioita for-silmukassa
        for(Asunto a: rakennus.asuntolista){
            index++;
            System.out.print("\r\n-----------------------------------------"
                    + "\r\n      Asunnon "+index+" tiedot:"
                    + "\r\n-----------------------------------------"
                    + "\r\nAsunnon "+index+" pinta-ala:       "+a.asunnonAla+" neliometria\r\n"
                    + "Asunnon "+index+" huoneiden maara: "+a.huoneidenMaara+"\r\n");
            a.tulostaAsukasLista(a);
            System.out.println("");
        }
    }
    
    //metodit rakennuksen tietojen tulostamiseksi
    public void tulostaRakennuksenTiedot(Rakennus rakennus){
        String tyyppi="Ei maaritelty";
        if(rakennus instanceof KerrosTalo){tyyppi="Kerrostalo";}
        if(rakennus instanceof RiviTalo){tyyppi="Rivitalo";}
        if(rakennus instanceof OmakotiTalo){tyyppi="Omakotitalo";}
        
        System.out.printf("\r\nRakennuksen tiedot: \r\n"
                + "   Rakennuksen tyyppi:  "+tyyppi+"\r\n");
        if(rakennus instanceof KerrosTalo){
            System.out.println("   Kerrosten maara:     "+((KerrosTalo) rakennus).palautaKerrosMaara());
        }
        System.out.println("   Asuntojen lukumaara: "+rakennus.asuntojenMaara);
        rakennus.tulostaAsuntoLista(rakennus);
    }
}
