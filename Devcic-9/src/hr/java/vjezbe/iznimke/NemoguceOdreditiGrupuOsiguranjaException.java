package hr.java.vjezbe.iznimke;

/**
 * Predstavlja entitet NemoguceOdreditiGrupuOsiguranjaException koji ozna�ava
 * ozna�enu iznimku u slu�aju kada se ne mo�e odraditi cijena osiguranja
 * 
 * @author deva
 * @version Devcic-6
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

    private static final long serialVersionUID = -2291535264076831445L;

    /**
     * Kreira objekt znakovnog niza sa porukom korisniku da se izvo�enje programa ne
     * odvija normalno
     */
    public NemoguceOdreditiGrupuOsiguranjaException() {
	super("Pogre�ka prilikom odre�ivanja cijene osiguranja!");
    }

    /**
     * Kreira objekt sa porukom o pogre�ci
     * 
     * @param poruka - podaci poruke o pogre�ci
     */
    public NemoguceOdreditiGrupuOsiguranjaException(String poruka) {
	super(poruka);
    }

    /**
     * Kreira objekt sa porukum o pogre�ci kao i uzrocima pogre�ke
     * 
     * @param poruka - podaci poruke o pogre�ci
     * @param uzrok  - opisuje uzrok pogre�ke
     */
    public NemoguceOdreditiGrupuOsiguranjaException(String poruka, Throwable uzrok) {
	super(poruka, uzrok);
    }

    /**
     * Kreira objekt sa uzrocima pogre�ke
     * 
     * @param uzrok - podaci o uzrocima pogre�ke
     */
    public NemoguceOdreditiGrupuOsiguranjaException(Throwable uzrok) {
	super(uzrok);
    }
}
