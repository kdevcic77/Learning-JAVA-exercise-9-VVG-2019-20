package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosPrivatnogKorisnikaController {
    @FXML
    private Label ime;
    @FXML
    private Label prezime;
    @FXML
    private Label email;
    @FXML
    private Label telefon;
    @FXML
    private TextField txtFieldIme;
    @FXML
    private TextField txtFieldPrezime;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldTelefon;
    @FXML
    private Button buttonUnos;

    List<Korisnik> listaKorisnika = new ArrayList<>();

    public void initialize() {
	listaKorisnika = Datoteke.dohvatiKorisnike().stream().filter(p -> p instanceof PrivatniKorisnik)
		.map(sc -> (PrivatniKorisnik) sc).collect(Collectors.toList());
    }

    public void unesiPrivatnogKorisnika() {
	Boolean ispravniPodaci = true;
	String poruka = "";

	String ime = txtFieldIme.getText();
	String prezime = txtFieldPrezime.getText();
	String email = txtFieldEmail.getText();
	String telefon = txtFieldTelefon.getText();
	if (Main.stringPrazan(ime)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli ime privatnog korisnika!\n";
	}
	if (Main.stringPrazan(prezime)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli prezime privatnog korisnika!\n";
	}
	if (Main.stringPrazan(email)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli email privatnog korisnika!\n";
	}
	if (Main.stringPrazan(telefon)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli telefon privatnog korisnika!\n";
	}
	if (ispravniPodaci == true) {
	    List<Korisnik> listaKorisnika = Datoteke.dohvatiKorisnike();
	    OptionalLong maxId = listaKorisnika.stream().mapToLong(Entitet::getId).max();
	    PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(maxId.getAsLong() + 1, txtFieldIme.getText(),
		    txtFieldPrezime.getText(), txtFieldEmail.getText(), txtFieldTelefon.getText());
	    listaKorisnika.add(privatniKorisnik);
	    Datoteke.zapisiUDatotekuKorisnika(listaKorisnika);
	    Main.alertPopup("Uspjeh", "Uspješno je unesen novi privatni korisnik", "Èestitamo!");
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Nepravilan unos!");
	    alert.setHeaderText("Niste unijeli slijedeæe podatke:");
	    alert.setContentText(poruka);
	    alert.showAndWait();
	}
    }

}
