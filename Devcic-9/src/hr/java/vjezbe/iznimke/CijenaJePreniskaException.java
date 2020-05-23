package hr.java.vjezbe.iznimke;

/**
 * Predstavlja entitet CijenaJePreniskaException koji ozna�ava neozna�enu
 * iznimku u slu�aju kada je une�ena cijena stana preniska
 * 
 * @author deva
 * @version Devcic-6
 */
public class CijenaJePreniskaException extends RuntimeException {

    private static final long serialVersionUID = 2555341954833609776L;

    /**
     * Kreira objekt znakovnog niza sa porukom korisniku da se izvo�enje programa ne
     * odvija normalno
     */
    public CijenaJePreniskaException() {
	super("Pogre�ka prilikom odre�ivanja iznosa poreza!");
    }

    /**
     * Kreira objekt sa porukom o pogre�ci
     * 
     * @param poruka - podaci poruke o pogre�ci
     */
    public CijenaJePreniskaException(String poruka) {
	super(poruka);
    }

    /**
     * Kreira objekt sa porukum o pogre�ci kao i uzrocima pogre�ke
     * 
     * @param poruka - podaci poruke o pogre�ci
     * @param uzrok  - opisuje uzrok pogre�ke
     */
    public CijenaJePreniskaException(String poruka, Throwable uzrok) {
	super(poruka, uzrok);
    }

    /**
     * Kreira objekt sa uzrocima pogre�ke
     * 
     * @param uzrok - podaci o uzrocima pogre�ke
     */
    public CijenaJePreniskaException(Throwable uzrok) {
	super(uzrok);
    }
}
