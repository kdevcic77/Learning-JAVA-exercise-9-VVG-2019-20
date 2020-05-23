package hr.java.vjezbe.entitet;

/**
 * @author deva
 * @version Devcic-6
 */
public enum Stanje {
    novo, izvrsno, rabljeno, neispravno;
    
    public static Stanje fromInteger(int broj) {
        switch(broj) {
        case 1:
            return novo;
        case 2:
           return izvrsno;
        case 3:
            return rabljeno;
        case 4:
           return neispravno;
        }
        return null;
    }
    
    public static String fromStanje (Stanje stanje) {
        switch(stanje) {
        case novo:
            return "1";
        case izvrsno:
           return "2";
        case rabljeno:
            return "3";
        case neispravno:
           return "4";
        }
	return null;
	
    }
    
}
