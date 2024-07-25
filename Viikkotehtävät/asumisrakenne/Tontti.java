/*
Sisaluokkaversio Tontti(Rakennus(Asukkaat)))
    Sisaluokka "rakennus" ja sen sisaluokka "asukas" ovat tiedoston lopussa.
 */
package asumisrakenne;
import java.util.*;

public class Tontti {
    //tontti -luokan muuttujat
    private String tontinNimi;
    private double tontinAla;
    private String tontinSijainti;
        private String leveysAste;
        private String pituusPiiri;
    
    //tontin konstruktorit
    public Tontti(){
        tontinNimi = haeTontinNimi();
        tontinSijainti = haeSijainti();
        tontinAla = haeTontinAla();
    }
    
//Tontin sisaluokat
/************************************************************
/**  tontin sisaluokka "rakennus" alkaa tasta 
/**  rakennuksen sisalla on viela toinen sisaluokka "asukas" 
/**  koko luokkarakenne: Tontti(Rakennus(Asukkaat()));
/************************************************************/
    public class Rakennus{
            //rakennus-sisaluokan muuttujat
            public double rakennuksenAla;
            public int huoneidenMaara;
            protected int asukkaidenMaara;
            
            //palauta rakennuksen tietojen arvot paaohjelmalle
            double returnRakennuksenAla(){return rakennuksenAla;};
            int returnHuoneidenMaara(){return huoneidenMaara;};
            int returnAsukkaidenMaara(){return asukkaidenMaara;};
    
            //rakennuksen konstruktorit
            public Rakennus(){
                huoneidenMaara = haeHuoneidenMaara();
                rakennuksenAla = haeRakennuksenAla();
                asukkaidenMaara = haeAsukkaidenMaara();
            }
            //rakennus-sisaluokan metodit tulevat tahan
            private double haeRakennuksenAla(){
                boolean kysyUudestaan = true;
                while(kysyUudestaan){
                    try{
                        System.out.println("Syota rakennuksen pinta-ala neliometreina: ");
                        Scanner reader = new Scanner(System.in);
                        rakennuksenAla = reader.nextDouble();
                        kysyUudestaan = false;
                    }catch(InputMismatchException ime){
                        Tontti.tulostaInputError();
                        kysyUudestaan = true;
                    }
                }
                return rakennuksenAla;
            }
            private int haeHuoneidenMaara(){
                boolean kysyUudestaan = true;
                while(kysyUudestaan){
                    try{
                        System.out.println("Syota huoneiden lukumaara: ");
                        Scanner reader = new Scanner(System.in);
                        huoneidenMaara = reader.nextInt();
                        kysyUudestaan = false;
                    }catch(InputMismatchException ime){
                        Tontti.tulostaInputError();
                        kysyUudestaan = true;
                    }
                }
                return huoneidenMaara;
            }
            private int haeAsukkaidenMaara(){
                boolean kysyUudestaan = true;
                while(kysyUudestaan){
                    try{
                        System.out.println("Syota asukkaiden lukumaara: ");
                        Scanner reader = new Scanner(System.in);
                        asukkaidenMaara = reader.nextInt();
                        kysyUudestaan = false;
                    }catch(InputMismatchException ime){
                        Tontti.tulostaInputError();
                        kysyUudestaan = true;
                    }
                }
                return asukkaidenMaara;
            }
            //metodit rakennuksen tietojen tulostamiseksi
            public void tulostaRakennuksenTiedot(){
                System.out.println("\r\n-----------------------------\r\n"
                        + "Rakennuksen tiedot ovat: "
                        + "\r\n-----------------------------\r\n"
                        + "Huoneiden lukumaara:  "+huoneidenMaara
                        + "\r\nAsukkaiden lukumaara: "+asukkaidenMaara);
                System.out.printf("Rakennuksen pinta-ala (m^2): %5.2f%n",rakennuksenAla);
            }
                /*************************************************/
                //  rakennuksen sisaluokka "asukas" alkaa tasta  //
                /*************************************************/
                public class Asukas{
                    //asukas-sisaluokan muuttujat
                   public String asukkaanNimi;
                   public String syntymaAika;
                   
                   //palauta rakennuksen tietojen arvot paaohjelmalle
                    String returnAsukkaanNimi(){return asukkaanNimi;};
                    String returnSyntymaAika(){return syntymaAika;};
                   
                   //asukkaiden konstruktorit
                   public Asukas(){
                       asukkaanNimi = haeAsukkaanNimi();
                       syntymaAika = haeSyntymaAika();
                   }
                   //asukas-sisaluokan metodit tulevat tahan
                   private String haeAsukkaanNimi(){
                        System.out.println("Nimi: ");
                        Scanner reader = new Scanner(System.in);
                        asukkaanNimi = reader.nextLine();
                        return asukkaanNimi;
                   }
                   private String haeSyntymaAika(){
                       System.out.println("Syntymaaika: ");
                        Scanner reader = new Scanner(System.in);
                        syntymaAika = reader.nextLine();
                        return syntymaAika;
                   }
                   //metodit asukkaiden tietojen tulostamiseksi
                    public void tulostaAsukkaanTiedot(String asukkaanNimi, String syntymaAika){
                        System.out.println("Asukkaan nimi: "+asukkaanNimi
                                + "\r\nSyntymaaika: "+syntymaAika);
                    }
                }
        }
    
//tontti-luokan metodit tulevat tahan
    
    //virheilmoitus: syotto vaarassa muodossa
    static void tulostaInputError(){
        System.out.println("***Tarkista, etta syotit tiedot oikeassa muodossa.***");
    }
    
    //palauta private-muuttujien arvot paaohjelmalle
    String returnName(){return tontinNimi;}
    String returnSijainti(){return tontinSijainti;};
    double returnAla(){return tontinAla;};
    
    //metodit tontin muuttujen hakemiseksi kayttajalta
    private String haeTontinNimi(){
        System.out.println("Syota tontin nimi: ");
        Scanner reader = new Scanner(System.in);
        tontinNimi = reader.nextLine();
        return tontinNimi;
    }
    private String haeSijainti(){
        tontinSijainti = "("+haeLeveysAste()+"N, "+haePituusPiiri()+"E)";
        return tontinSijainti;
    }
    public String haeLeveysAste(){
        System.out.println("Leveysaste: ");
        Scanner reader = new Scanner(System.in);
        leveysAste = reader.nextLine();
        return leveysAste;
    }
    public String haePituusPiiri(){
        System.out.println("Pituuspiiri: ");
        Scanner reader = new Scanner(System.in);
        pituusPiiri = reader.nextLine();
        return pituusPiiri;
    }
    private double haeTontinAla(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            try{
                System.out.println("Syota tontin pinta-ala hehtaareina: ");
                Scanner reader = new Scanner(System.in);
                tontinAla = reader.nextDouble();
                kysyUudestaan = false;
            }catch(InputMismatchException ime){
                Tontti.tulostaInputError();
                kysyUudestaan = true;
            }
        }
        return tontinAla;
    }
    
    //metodit tontin tietojen tulostamiseksi
    public void tulostaTontinTiedot(){
        System.out.println("\r\n-----------------------------\r\n"
                + "Tontin tiedot ovat: "
                + "\r\n-----------------------------\r\n"
                + "Nimi:             "+tontinNimi
                + "\r\nSijainti:        "+tontinSijainti);
        System.out.printf("Tontin pinta-ala (hA): %5.2f%n",tontinAla);
    }
    
}
