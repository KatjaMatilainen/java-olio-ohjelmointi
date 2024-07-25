/*
VIIKKOTEHTAVA 2

PAAOHJELMA luokkkarakenteen Tontti(Rakennus(Asukkaat))) testaamiselle.

Sisaluokka-versio. En saanut tehtavanannosta ihan selvaa, mita vaadittiin, mutta
minusta sisaluokkien kayttaminen oli jarkevampi rakenne tassa tehtavassa.
Aliluokkien kayttamisessa ei tuntunut olevan jarkea, silla ei "Rakennus" ole tontin 
erityistapaus, eika "Asukas" ole rakennuksen erityistapaus, vaan pikemminkin 
tontti sisaltaa rakennuksia, ja rakennus sisaltaa asukkaita.

Myohemmin asumisesta tehtiin toisenlainen, selkeampi versio viikkotehtavassa 5.
 */
package asumisrakenne;

public class Asumisrakenne {
    public static void main(String[] args) {
        
    //luodaan uusi tontti ja haetaan tontin tiedot kayttajalta
        Tontti uusiTontti = new Tontti();
        
    //luodaan tontille uusi rakennus (tamakin voisi tarvittaessa olla lista)
        Tontti.Rakennus uusiRakennus = uusiTontti.new Rakennus();
    
    //tehdaan halutun asukasmaaran pituinen asukas-array
        Tontti.Rakennus.Asukas[] asukas = teeAsukasLista(uusiRakennus);

    //tulostetaan tontin tiedot
        uusiTontti.tulostaTontinTiedot();
    //tulostetaan tontin rakennuksen tiedot
        uusiRakennus.tulostaRakennuksenTiedot();
    //tulostetaan rakennuksen asukkaiden tiedot
        tulostaAsukasLista(asukas,uusiRakennus.asukkaidenMaara);

}

//paaohjelman metodit (kokeiltiin luoda asukaslista paaohjelmassa sisaluokan sijaan)
    //asukaslistan luominen
    static Tontti.Rakennus.Asukas[] teeAsukasLista(Tontti.Rakennus uusiRakennus){
        //halutun asukasmaaran pituinen tyhja objekti-array
        Tontti.Rakennus.Asukas[] asukas = new Tontti.Rakennus.Asukas[uusiRakennus.returnAsukkaidenMaara()];
        
        //kaydaan lapi asukaslista ja luodaan sinne yksitellen asukkaat
        for(int i=0; i<uusiRakennus.returnAsukkaidenMaara(); i++){
            System.out.println("\r\n-----------------------------\r\n"
                    + "  Syota "+(i+1)+". asukkaan tiedot:"
                    + "\r\n-----------------------------");
            //luodaan uusi asukas
            Tontti.Rakennus.Asukas uusiAsukas = uusiRakennus.new Asukas();
            //tallennetaan asukas asukaslistaan
            asukas[i] = uusiAsukas;
        }
        return asukas;
    }
    //asukaslistan tulostaminen
    static void tulostaAsukasLista(Tontti.Rakennus.Asukas[] asukas, int asukasmaara){
    //tulostetaan asukaslista (taman voisi myos liittaa rakennuksen tulostukseen)
        for(int i=0; i<asukasmaara; i++){
            System.out.println("\r\n-----------------------------\r\n"
                    + ""+(i+1)+". asukkaan tiedot: "
                    + "\r\n-----------------------------");
            asukas[i].tulostaAsukkaanTiedot(asukas[i].asukkaanNimi, asukas[i].syntymaAika);
        }
    }
}
