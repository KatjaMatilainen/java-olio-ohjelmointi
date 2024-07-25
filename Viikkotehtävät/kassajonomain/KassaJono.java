/*
JonoInterfacen implementoiva KassaJono
*/

package kassajonomain;

public class KassaJono<Asiakas> implements JonoInterface<Asiakas> {
    private Asiakas[] asiakkaat; //asiakkaista koostuva array
    private int pituus;          //jonolle varatun arrayn pituus
    private int jonottajat = 0;  //jonottajien maara tietylla hetkella
    
    //HUOM! jonon viimeisen alkion indeksi = jonottajat-1, ei jonottajat !!
    
    public KassaJono(int asetaPituus){
        pituus = asetaPituus;
        asiakkaat = (Asiakas[]) new Object[pituus];
    }
    
    //kasvatetaan jonoa
    public void kasvataJonoa(){
        //kopioidaan asiakkaat talteen siirtoa varten
        Asiakas[] tempAsiakkaat = asiakkaat;
        
        //kasvatetaan jonolle varatun arrayn pituutta 
        //(luodaan uusi tyhja yhden alkion pidempi jono)
        pituus++;
        asiakkaat = (Asiakas[]) new Object[pituus];
        
        //siirretaan asiakkaat tempista takaisin pidennettyyn arrayhyn
        for(int i=0; i<(pituus-1); i++){
            asiakkaat[i]=tempAsiakkaat[i];
        }
    }
    
    //palautetaan jonottajien maara tietylla hetkella
    int returnInd(){
        return jonottajat;
    }
    
    //lisataan asiakas jonon "asiakkaat" viimeiseksi
    //samalla jonottajien maaraa kasvatetaan yhdella
    @Override
    public void lisaaElementti(Asiakas uusiAsiakas){
        asiakkaat[jonottajat++] = uusiAsiakas;
    }
    
    //poistetaan jonon ensimmainen
    @Override
    public Asiakas palautaElementti(){
        //otetaan jonon ensimmainen alkio talteen palautettavaksi
        Asiakas ensimmainen = asiakkaat[0];
        
        //kopioidaan asiakkaat talteen valiaikaiseen arrayhyn
        Asiakas[] temp = asiakkaat;
        
        //taytetaan uusi array vanhan alkioilla niin, etta jonon ensimmainen jatetaan lisaamatta
        for(int i=0; i<(pituus-1); i++){
            temp[i] = asiakkaat[i+1];
        }
        
        //poistetaan duplicate jonon lopusta
        asiakkaat[jonottajat-1] = null;

        //siirretaan alkiot takaisin sinne tempista
        for(int i=0; i<pituus; i++){
            asiakkaat[i] = temp[i];
        }
        
        //vahennetaan jonottajien maaraa yhdella (siirretaan jonon paata)
        --jonottajat;
        
        //palautetaan kayttajalle jonosta poistettu asiakas
        return ensimmainen;
    }
    
    @Override
    public boolean onkoJonoOlemassa(){
        boolean olemassa=false;
        /*
        jos jonossa on yhtaan alkiota, jono on olemassa 
        (en keksi kylla tapausta, jossa alkioiden maara menisi nollaan,
        koska ei arrayta ole mitaan jarkea lyhentaa joka kerta kun siita 
        lahtee asiakas)
        */
        if(asiakkaat.length > 0){
            olemassa=true;
        }
        return olemassa;
    }
    
    @Override
    public boolean onkoJonoTyhja(){
        boolean tyhja = true;
        //jos yksikaan jonon alkioista on jotain muuta kuin null, jono ei ole tyhja
        for(int i=0; i<(pituus); i++){
            if (asiakkaat[i] != null) {
                tyhja = false;
            }
        }
        return tyhja;    
    }
}
