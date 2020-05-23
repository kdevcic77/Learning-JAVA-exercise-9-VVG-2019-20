package hr.java.vjezbe.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PocetniEkranController {

    public void prikaziEkranZaPretraguAutomobila() {
	BorderPane root;

	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaPretraguAutomobila = (BorderPane) FXMLLoader
		    .load(getClass().getResource("pretragaAutomobila.fxml"));
	    root.setCenter(ekranZaPretraguAutomobila);
//	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
//	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaPretraguStanova() {
	BorderPane root;
	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaPretraguStanova = (BorderPane) FXMLLoader
		    .load(getClass().getResource("pretragaStanova.fxml"));
	    root.setCenter(ekranZaPretraguStanova);
//	    Scene scenaZaPretraguStanova = new Scene(ekranZaPretraguStanova);
//	    Main.getMainStage().setScene(scenaZaPretraguStanova);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaPretraguUsluga() {
	BorderPane root;
	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaPretraguUsluga = (BorderPane) FXMLLoader
		    .load(getClass().getResource("pretragaUsluga.fxml"));
	    root.setCenter(ekranZaPretraguUsluga);
//	    Scene scenaZaPretraguUsluga = new Scene(ekranZaPretraguUsluga);
//	    Main.getMainStage().setScene(scenaZaPretraguUsluga);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaPretraguPrivatnihKorisnika() {
	BorderPane root;
	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaPretraguPrivatnihKorisnika = (BorderPane) FXMLLoader
		    .load(getClass().getResource("pretragaPrivatnihKorisnika.fxml"));
	    root.setCenter(ekranZaPretraguPrivatnihKorisnika);
//	    Scene scenaZaPretraguPrivatnihKorisnika = new Scene(ekranZaPretraguPrivatnihKorisnika);
//	    Main.getMainStage().setScene(scenaZaPretraguPrivatnihKorisnika);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaPretraguPoslovnihKorisnika() {
	BorderPane root;
	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaPretraguPoslovnihKorisnika = (BorderPane) FXMLLoader
		    .load(getClass().getResource("pretragaPoslovnihKorisnika.fxml"));
	    root.setCenter(ekranZaPretraguPoslovnihKorisnika);
// 	    Scene scenaZaPretraguPoslovnihKorisnika = new Scene(ekranZaPretraguPoslovnihKorisnika);
// 	    Main.getMainStage().setScene(cenaZaPretraguPoslovnihKorisnika);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaUnosAutomobila() {
	BorderPane root;

	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaUnosuAutomobila = (BorderPane) FXMLLoader
		    .load(getClass().getResource("unosAutomobila.fxml"));
	    root.setCenter(ekranZaUnosuAutomobila);
//	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
//	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void prikaziEkranZaUnosStana() {
	BorderPane root;

	try {
	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
	    BorderPane ekranZaUnosStana = (BorderPane) FXMLLoader.load(getClass().getResource("unosStana.fxml"));
	    root.setCenter(ekranZaUnosStana);
// 	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
// 	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
	    Main.setMainStage(root);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void prikaziEkranZaUnosUsluge() {
 	BorderPane root;

 	try {
 	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
 	    BorderPane ekranZaUnosUsluge = (BorderPane) FXMLLoader.load(getClass().getResource("unosUsluge.fxml"));
 	    root.setCenter(ekranZaUnosUsluge);
//  	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
//  	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
 	    Main.setMainStage(root);
 	} catch (Exception e) {
 	    e.printStackTrace();
 	}
     }
    
    public void prikaziEkranZaUnosPrivatnogKorisnika() {
 	BorderPane root;

 	try {
 	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
 	    BorderPane ekranZaUnosPrivatnogKorisnika = (BorderPane) FXMLLoader.load(getClass().getResource("unosPrivatnogKorisnika.fxml"));
 	    root.setCenter(ekranZaUnosPrivatnogKorisnika);
//  	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
//  	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
 	    Main.setMainStage(root);
 	} catch (Exception e) {
 	    e.printStackTrace();
 	}
     }
    
    public void prikaziEkranZaUnosPoslovnogKorisnika() {
 	BorderPane root;

 	try {
 	    root = (BorderPane) FXMLLoader.load(getClass().getResource("pocetniEkran.fxml"));
 	    BorderPane ekranZaUnosPoslovnogKorisnika = (BorderPane) FXMLLoader.load(getClass().getResource("unesiPoslovnogKorisnika.fxml"));
 	    root.setCenter(ekranZaUnosPoslovnogKorisnika);
//  	    Scene scenaZaPretraguAutomobila = new Scene(ekranZaPretraguAutomobila);
//  	    Main.getMainStage().setScene(scenaZaPretraguAutomobila);
 	    Main.setMainStage(root);
 	} catch (Exception e) {
 	    e.printStackTrace();
 	}
     }
}
