package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja su�elje vozila kako bi klase koje se ne naslje�uju implementirale
 * zajedni�ku skupinu metoda
 * 
 * @author deva
 * @version Devcic-6
 */
public interface Vozilo {

    /**
     * @param snagaKs - predstavlja unos snage vozila u konjskim satima
     * @return - vra�a izra�unatu snagu vozila u kilovatima (kW), jer su konjske
     *         snage u biti nezakonita jedinica
     */
    default public BigDecimal izracunajKilovate(BigDecimal snagaKs) {
	BigDecimal snagaKw = new BigDecimal(0);
	snagaKw = snagaKs.multiply(new BigDecimal(0.735499));
	return snagaKw;
    }

    /**
     * @return svi entiteti koji koriste su�elje Vozilo moraju implementirati
     *         izra�unavanje grupe osiguranja putem vra�anja cjelobrojne vrijednosti
     *         grupe osiguranja u koje vozilo spada
     * @throws NemoguceOdreditiGrupuOsiguranjaException - u slu�aju prevelikog broja
     *                                                  kilivata, baca se gre�ka da
     *                                                  nije mogu�e odrediti grupu
     *                                                  osiguranja
     */
    public Integer izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;

    /**
     * @return vra�a odre�eni iznos osiguranja za vozilo koje je potrebno platiti na
     *         temelju toga u koju grupu osiguranja vozilo pripada
     * @throws NemoguceOdreditiGrupuOsiguranjaException - u slu�aju prevelikog broja
     *                                                  kilivata, baca se gre�ka da
     *                                                  nije mogu�e odrediti grupu
     *                                                  osiguranja
     */
    default public BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {

	BigDecimal iznosOsiguranja = new BigDecimal(0);

	switch (izracunajGrupuOsiguranja()) {
	// 5 grupa osiguranja
	case 0:
	    iznosOsiguranja = new BigDecimal(300);
	    break;
	case 1:
	    iznosOsiguranja = new BigDecimal(600);
	    break;
	case 2:
	    iznosOsiguranja = new BigDecimal(900);
	    break;
	case 3:
	    iznosOsiguranja = new BigDecimal(1200);
	    break;
	case 4:
	    iznosOsiguranja = new BigDecimal(1500);
	    break;
	}
	return iznosOsiguranja;

    }

}
