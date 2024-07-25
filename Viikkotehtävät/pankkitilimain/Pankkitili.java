package pankkitilimain;
import java.util.*;

public class Pankkitili {
    //luokan muuttujat, private-tyyppia
    private String omistaja;
    private String tilinumero;
    private double saldo;
    private double nostoSumma;
    private double talletusSumma;
    private int kysy;
    
    //palauta private-muuttujien arvot paaohjelmalle
    String returnOmistaja(){return omistaja;}
    String returnTilinumero(){return tilinumero;}
    double returnSaldo(){return saldo;}
    
    //konstruktorit
    public Pankkitili(){
        omistaja = haeOmistaja();
        tilinumero = haeTilinumero();
        saldo = haeSaldo();
    }
    
    //metodit tilitietojen hakemiseksi
    private String haeOmistaja(){
        Scanner reader1 = new Scanner(System.in);
        System.out.println("\r\n------------------------------------"
                + "\r\n  Tervetuloa pankkitiliohjelmaan!"
                + "\r\n------------------------------------"
                + "\r\n Anna tilin omistajan nimi:" 
                + "\r\n (Huomio: Skandinaaviset aakkoset "
                + "tulostuvat virheellisesti. \r\n");
        omistaja = reader1.nextLine();
        return omistaja;
    }
    private String haeTilinumero(){
        Scanner reader2 = new Scanner(System.in);
        System.out.println("\r\n Anna tilinumero: ");
        tilinumero = reader2.nextLine();
        return tilinumero;
    }
    private double haeSaldo(){
        boolean kysySaldoUudestaan = true;
        while(kysySaldoUudestaan){
            Scanner reader3 = new Scanner(System.in);
            System.out.println("\r\n Anna tilin saldo euroina: ");
            try{
                saldo = reader3.nextDouble();
                kysySaldoUudestaan = false;
            }catch(InputMismatchException ime){
                Pankkitili.virheilmoitusYleinen();
                kysySaldoUudestaan = true;
            }
        }
        return saldo;
    }

    //metodit nostoon, talletukseen, saldokyselyyn, ja poistumiseen
    
    //vaihtoehdon valinta
    public int kysyKayttajalta(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
        Scanner reader4 = new Scanner(System.in);
        System.out.println("----------------------------------\r\n| "
                + "Valitse haluamasi toiminto:    |\r\n|   "
                + "1) Nosto                     |\r\n|   "
                + "2) Talletus                  |\r\n|   "
                + "3) Saldon tarkistus          |\r\n|   "
                + "4) Sulje ohjelma             |\r\n"
                + "----------------------------------");
        try{
            kysy = reader4.nextInt();
            kysyUudestaan = false;
        }catch(InputMismatchException ime){
            vaaraValinta();
            kysyUudestaan = true;
        }
        }
        return kysy;
    }
    //nostosumman kysyminen
    double nostoSumma(double saldo){
        if(saldo<0){
            virheilmoitusSaldo();
            lopetaNosto();
        return 0;
        }
        else{
            boolean kysyNostoUudestaan = true;
            while(kysyNostoUudestaan){
                Scanner reader5 = new Scanner(System.in);
                System.out.println(" Anna haluamasi nostosumma (tai 0 poistuaksesi nostosta): ");
                nostoSumma = reader5.nextDouble();
                kysyNostoUudestaan=false;
                if(nostoSumma > saldo){
                    virheilmoitusSaldo();
                    System.out.println("Tilin saldo on "+saldo+" euroa.");
                    kysyNostoUudestaan=true;
                }
                if(nostoSumma<0){
                    virheilmoitusNegatiivinen();
                    kysyNostoUudestaan=true;
                }
            }
        return nostoSumma;
        }
    }
    //noston suorittaminen
    double nosto(double nostoSumma,double saldo){
        System.out.printf("\r\n----------------------------------"
                + "\r\nTilin saldo euroina ennen nostoa on:   %5.2f%n", saldo);
        //System.out.println("\r\n----------------------------------"
        //        + "\r\nTilin saldo ennen nostoa on "+saldo+" euroa.");
        System.out.printf("Noston suuruus euroina on:   %5.2f%n", nostoSumma);
        //System.out.println("Noston suuruus on "+nostoSumma+" euroa.");
        saldo = saldo - nostoSumma;
        System.out.printf("Tilin saldo euroina noston jalkeen on   %5.2f%n",saldo);
        return saldo;
    }
    //talletussumman kysyminen
    double talletusSumma(){
        boolean kysyUudestaan = true;
        while(kysyUudestaan){
            Scanner reader6 = new Scanner(System.in);
            System.out.println(" Anna haluamasi talletussumma (tai 0 poistuaksesi talletuksesta): ");
            talletusSumma = reader6.nextDouble();
            kysyUudestaan = false;
            if(talletusSumma < 0){
                virheilmoitusNegatiivinen();
                kysyUudestaan = true;
            }
        }
        return talletusSumma;
    }
    //talletuksen suorittaminen
    double talletus(double talletusSumma,double saldo){
        boolean kysyTalletusUudestaan = true;
        while(kysyTalletusUudestaan){
            System.out.printf("\r\n----------------------------------"
                    + "\r\nTilin saldo euroina ennen talletusta on %5.2f%n",saldo);
            System.out.printf("Talletuksen suuruus euroina on %5.2f%n",talletusSumma);
            saldo = saldo + talletusSumma;
            System.out.printf("Tilin saldo euroina talletuksen jalkeen on %5.2f%n",saldo);
            kysyTalletusUudestaan=false;
            if(talletusSumma < 0){
                virheilmoitusNegatiivinen();
                kysyTalletusUudestaan=true;
            }
        }
        return saldo;
    }
    
 //ei-staattinen versio
/*    public void tulostaTiedot(){
        String tulostaTilitiedot = "-------------------------------\r\n"
                + "| Tilin tiedot: \r\n|   Tilin omistaja: "+omistaja
                +"\r\n|   Tilinumero:     "+tilinumero;
        System.out.println(tulostaTilitiedot);
        System.out.printf("|   Tilin saldo euroina:   %5.2f%n", saldo);
        System.out.println("-------------------------------\r\n");
    }*/
    
//tilitietojen tulostaminen (staattinen versio, jos parametrit maaritellaan paaohjelmassa)
    public static void tulostaTiedot(String omistaja, String tilinumero, double saldo){
        String tulostaTilitiedot = "-------------------------------\r\n"
                + "| Tilin tiedot: \r\n|   Tilin omistaja: "+omistaja
                +"\r\n|   Tilinumero:     "+tilinumero;
        System.out.println(tulostaTilitiedot);
        System.out.printf("|   Tilin saldo euroina:   %5.2f%n", saldo);
        System.out.println("-------------------------------\r\n");
    }
    
//metodit virheilmoituksille (nama voivat olla staattisia)
    //ohjelman lopettaminen
    public static void lopetaOhjelma(){
        String lopetusviesti = " Ohjelma suljetaan.";
        System.out.println(lopetusviesti);
    }
    //estetaan nosto jos tilille on alunperin syotetty negatiivinen arvo
    public static void lopetaNosto(){
        String lopetusviesti = " Tilin saldo on negatiivinen. Nosto suljetaan.";
        System.out.println(lopetusviesti);
    }
    //virheilmoitus, jos syotetaan vaarassa muodossa
    public static void virheilmoitusYleinen(){
        String errorMessageGen = ""
                + "**************************************************\r\n"
                + " Tarkista, etta syotit tiedot oikeassa muodossa. \r\n"
                + "**************************************************\r\n";
        System.out.println(errorMessageGen);
    }
    //virheilmoitus, jos syotetaan negatiivinen luku
    public static void virheilmoitusNegatiivinen(){
        String errorMessageNeg = "\r\n Anna positiivinen luku! \r\n";
        System.out.println(errorMessageNeg);
    }
    //virheilmoitus, jos tilin saldo menisi noston jalkeen negatiiviseksi
    public static void virheilmoitusSaldo(){
        String errorMessageSaldo = "\r\n Tililla ei ole tarpeeksi katetta.";
        System.out.println(errorMessageSaldo);
    }
    //virheilmoitus, jos syotetaan vaara kokonaisluku
    public static void vaaraValinta(){
        String errorMessageInt = "**************************************************"
                + "\r\n Virhe: valitse joku vaihtoehdoista 1-4. \r\n"
                + "**************************************************";
        System.out.println(errorMessageInt);
    }
}