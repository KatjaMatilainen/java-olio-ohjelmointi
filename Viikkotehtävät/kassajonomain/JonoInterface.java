/*
Rajapinta jonojen luomiselle
 */
package kassajonomain;

public interface JonoInterface<Asiakas> {
    boolean onkoJonoTyhja();
    boolean onkoJonoOlemassa();
    
    public void lisaaElementti(Asiakas asiakas);
    public Asiakas palautaElementti();
}