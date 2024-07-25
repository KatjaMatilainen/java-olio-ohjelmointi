/*
Luottokorttimaksu -aliluokka.
Luottokorttimaksuun lisataan muuttujaksi kortin numero ja metodi sen 
arpomiseksi ja palauttamiseksi.
 */
package kassajonomain;
import java.util.*;

public class LuottokorttiMaksu extends Maksu {
    //luokkakohtainen muuttuja
    private String kortinNumero;
    
    //konstruktori
    public LuottokorttiMaksu(){
        super();
        kortinNumero=arvoKortinNumero();
    }
    
    //metodi kortin numeron arpomiseksi
    private String arvoKortinNumero(){
        Random rand = new Random();
        //luottokortin numero on 9-numeroinen yhdistelma
        int arvottu = rand.nextInt(100000000);
        kortinNumero = String.format("%09d", arvottu);
        return kortinNumero;
    }
    
    //palauta kortin numero
    String palautaKortinNumero(){
        return kortinNumero;
    }
}
