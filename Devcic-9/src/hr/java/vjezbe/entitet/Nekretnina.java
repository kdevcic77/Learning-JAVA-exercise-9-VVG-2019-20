package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * * Predstavlja su�elje Nekretnina kako bi klase koje se ne naslje�uju
 * implementirale zajedni�ku skupinu metoda
 * 
 * @author deva
 * @version Devcic-6
 */
public interface Nekretnina {

    /**
     * Vra�a izra�unati iznos poreza na temelju une�ene cijene nekretnine, a ukoliko
     * se unese preniska cijena nekretnine baca se iznimka
     * 
     * @param cijenaNekretnine - podatak o cijeni nekretnine
     * @return vra�a izra�unati iznos poreza
     */
    default public BigDecimal izracunajPorez(BigDecimal cijenaNekretnine) {
	BigDecimal iznosPoreza = new BigDecimal(0);
	BigDecimal minimalnaCijenaNekretnine = new BigDecimal(10000);
	if (cijenaNekretnine.compareTo(minimalnaCijenaNekretnine) < 0) {
	    throw new CijenaJePreniskaException();
	}
	iznosPoreza = (cijenaNekretnine.multiply(new BigDecimal(3)).divide(new BigDecimal(100), RoundingMode.HALF_UP));
	return iznosPoreza;

    }
}
