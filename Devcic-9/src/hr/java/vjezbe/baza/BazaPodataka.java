package hr.java.vjezbe.baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BazaPodataka {

    public static void main(String[] args) {
	try (Connection konekcija = DriverManager.getConnection(
		"jdbc:h2:file:C:\\Users\\deva\\git\\Learning-JAVA-exercise-9-VVG-2019-20\\Devcic-9\\H2 baza\\Labos9_baza",
		"student", "student")) {
	    System.out.println("Spojili smo se");
	} catch (SQLException e) {
	    System.out.println("Nismo spojeni");
	    e.printStackTrace();
	}

    }

}
