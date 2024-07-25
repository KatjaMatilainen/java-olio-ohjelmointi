//VIIKKOTEHTAVA 4
/*
Ohjelman pitaa tehda seuraavat asiat:

Luodaan taulukko(lista) kassajonossa oleville asiakkaille.  jos array tulee 
tayteen, luodaan uusi isompi taulukko ja kopioidaan tiedot sinne.

arvotaan, kuinka monta kertaa heitetaan kolikkoa.

luodaan main ohjelmaan patka joka heittaa kolikkoa (0,1)
    jos tulos on 0, lisataan asiakas jonon peralle
    jos tulos on 1, poistetaan asiakas jonon etupaasta

kun asiakas luodaan, hanelle annetaan asiakasnumero ja arvotaan maksutapa (0,1)
    0 on kateisasiakas
    1 on luottokorttiasiakas

kun asiakas poistuu jonosta, han suorittaa maksun
    jos asiakas on kateisasiakas, han tekee kateismaksun
    jos asiakas on luottokorttiasiakas, han tekee luottokorttimaksun

molemmilla maksutavoilla on tietty kesto, jota simuloidaan This.sleep(aika_ms) 
-metodilla. Ajan voi asettaa tai arpoa.
Samalla arvotaan luottokortin numero (jos korttimaksu) ja maksun suuruus.
 */

package kassajonomain;
import java.util.*;

public class KassaJonoMain {
    //suoritettavien tapahtumien maara voi olla staattinen muuttuja
    private static int maara;
    
    public static void main(String[] args) throws InterruptedException {
        //luodaan random -luokka arpomisia varten
        Random random = new Random();
        
        //luodaan ensin (tyhja) jono
        int pituus = 1+random.nextInt(10);
        System.out.println("Luodaan tyhja jono, jonka pituus = "+pituus);
        KassaJono uusiJono = new KassaJono(pituus);
        
        //lisataanko jonoon heti jonkin verran asiakkaita?
        int jonottajatAlussa = 1+random.nextInt(pituus);
        for(int i=0; i<jonottajatAlussa; i++){
            Asiakas as = new Asiakas();
            uusiJono.lisaaElementti(as);
        }
        
        //haetaan ja tulostetaan jonottajien maara
        int ind=uusiJono.returnInd();
        System.out.println("Lisataan aloitukseen jonottajia: "+ind);
        
        //arvotaan tapahtumien maara
        maara = 10+random.nextInt(10);
        System.out.println("Suoritettavien tapahtumien maara = "+maara+"\r\n");
       
    //tarkistetaan ensin, onko jono olemassa ja onko se tyhja
    boolean olemassa=uusiJono.onkoJonoOlemassa();
    boolean tyhja=uusiJono.onkoJonoTyhja();
    
    //suoritetaan for-silmukkaa niin kauan kuin jono on olemassa ja siina on 
    //asiakkaita (aloituksessa maara saa olla 0, lopetetaan jos tyhjenee sen jalkeen)
    
    int laskuri=0; //pitaa kirjaa, monesko tapahtuma on menossa
    int kuittinro=0; //pitaa kirjaa, monesko tulostettava kuitti on menossa
    while(olemassa){
        
        for(int j=0; j<maara; j++){//suoritetaan arvottu maara tapahtumia
            laskuri++;
            
            //arvotaan, onko tapahtuma lisays vai poisto
            int poista = random.nextInt(2);
            switch(poista){
                case 0:{
                    //haetaan ja tulostetaan jonottajien maara
                    ind=uusiJono.returnInd();
                    
                    //poistutaan silmukasta ennen poistoa jos jono on tyhja
                    tyhja=uusiJono.onkoJonoTyhja();
                    if(tyhja){break;}
                    
                    //poistetaan jonon ensimmainen asiakas
                    Asiakas ensimmainen = (Asiakas) uusiJono.palautaElementti();
                    
                    //simuloidaan kasittelyaikaa (tamankin keston voisi arpoa)
                    Thread.sleep(4000);
                    
                    //lisataan huvin vuoksi laskuri tulostettaville kuiteille
                    kuittinro++;
                    //tulostetaan maksun tiedot
                    ensimmainen.tulostaTiedot(kuittinro);
                    
                    //ilmoitetaan, etta asiakas poistui jonosta
                    System.out.println("Asiakas poistui jonosta.");
                    
                    //haetaan ja tulostetaan uusi jonottajien maara
                    ind=uusiJono.returnInd();
                    System.out.println("Jonottajien maara poiston jalkeen: "+ind);
                    break;
                }
                case 1:{
                    //tarkistetaan jonottajien maara
                    ind = uusiJono.returnInd();
                    
                    //kasvatetaan tarvittaessa jonoa
                    if(ind>=pituus){uusiJono.kasvataJonoa();}
                    
                    //luodaan uusi asiakas
                    Asiakas uusiAsiakas = new Asiakas();
                    //lisataan asiakas jonon hannille
                    uusiJono.lisaaElementti(uusiAsiakas);
                    
                    //tulostetaan ilmoitus uudesta asiakkaasta
                    System.out.println("\r\nUusi asiakas astui jonoon.");
                    
                    //haetaan ja tulostetaan uusi jonottajien maara
                    ind = uusiJono.returnInd();
                    System.out.println("Jonottajien maara asiakkaan lisayksen jalkeen: "+ind);
                    break;
                }
            }
            //tarkistaTiedot(uusiJono); //tarkistus varmuuden vuoksi
            
            //paivitetaan jonon tiedot joka kierroksen lopuksi
            olemassa=uusiJono.onkoJonoOlemassa();
            tyhja=uusiJono.onkoJonoTyhja();
            //jos jono on operaation jalkeen tyhja, poistutaan silmukasta
            if(tyhja){break;}
        }
        //tulostetaan lopuksi, kuinka monta tapahtumaa suoritettiin
        System.out.println("\r\nSuoritettiin "+laskuri+" tapahtumaa "+maara+":sta, jono suljetaan.");
        break;
    }
}

    
    //paaohjelman metodi, jolla tulostetaan tiedot siita, onko jono olemassa
    //ja onko se tyhja (tama on olemassa tarkistusta varten)
    public static void tarkistaTiedot(KassaJono uusiJono){
        boolean olemassa,tyhja;
        
        //onko jono olemassa?
          olemassa = uusiJono.onkoJonoOlemassa();
          if(olemassa){System.out.println("jono on olemassa");
          }else{System.out.println("jonoa ei ole olemassa");}
          
        //onko jono tyhja?
          tyhja = uusiJono.onkoJonoTyhja();
          if(tyhja){System.out.println("jono on tyhja");
        }else{System.out.println("jono ei ole tyhja");}
    }
}
