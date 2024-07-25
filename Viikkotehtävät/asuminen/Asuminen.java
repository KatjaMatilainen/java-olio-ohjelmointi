/**
Viikkotehtavan 5 versio.
Nyt sisaluokkien sijaan kaikki komponentit ovat omissa luokissaan, ja
arrayn sijasta kaytetaan javan omaa listarakennetta objekteille.
 **/

package asuminen;

//Main class
public class Asuminen {
    public static void main(String[] args) {

    //luodaan uusi tontti
    Tontti uusiTontti = new Tontti();
    
    //tulostetaan tontin tiedot
    uusiTontti.tulostaTontinTiedot();
    
    }
}
