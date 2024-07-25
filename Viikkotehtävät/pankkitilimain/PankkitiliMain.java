/*
 VIIKKOTEHTAVA 1
Jalkikateen katsottuna tasta voisi suurimman osan operaatioista siirtaa
Pankkitili -luokalle ja kutsua sen metodeita, mutta ensimmaista tehtavaa 
tehdessa en viela oikein ymmartanyt luokkien toimintaa kunnolla.
 */

package pankkitilimain;
import java.util.*;

public class PankkitiliMain {
    public static void main (String args[]){
    //pankkitilin muuttujat(ei staattisia)
        String omistaja;
        String tilinumero;
        double saldo;
        double nostoSumma;
        double talletusSumma;

    //tilitietojen hakeminen kayttajalta, toistetaan kunnes oikeaa muotoa
        Pankkitili uusiTili = new Pankkitili();
        omistaja = uusiTili.returnOmistaja();
        tilinumero = uusiTili.returnTilinumero();
        saldo = uusiTili.returnSaldo();

    //kysy kayttajalta mita tilille tehdaan, toistetaan kunnes haluaa lopettaa
        int vaihtoehto;
        boolean repeat = true;
        while(repeat){
            vaihtoehto = uusiTili.kysyKayttajalta();
        //toimi annetun vaihtoehdon mukaisesti
            switch(vaihtoehto){
                case 1:{ //1) tililta nosto
                    nostoSumma = uusiTili.nostoSumma(saldo);
                    saldo = uusiTili.nosto(nostoSumma,saldo);
                    break;
                }//end of case 1
                
                case 2:{ //2) tilille talletus
                    boolean kysyTalletusUudestaan=true;
                    while(kysyTalletusUudestaan){
                        try{ //kysy summa uudestaan jos input on vaaraa muotoa
                            talletusSumma = uusiTili.talletusSumma();
                            saldo=uusiTili.talletus(talletusSumma,saldo);
                            kysyTalletusUudestaan=false;
                        }
                        catch(InputMismatchException ime){
                            kysyTalletusUudestaan=true;
                        }finally{}//end of try
                    }//end of while
                    break;
                }// end of case 2
                
                case 3:{ //3) tulosta tilitiedot
                    uusiTili.tulostaTiedot(omistaja,tilinumero,saldo);
                    break;
                }
                
                case 4:{ //4) poistu
                    Pankkitili.lopetaOhjelma();
                    repeat=false;
                    break;
                }
                
                default:{ //muu kokonaisluku: virheilmoitus
                    Pankkitili.vaaraValinta();
                    break;
                }
            }
        }
    }
}