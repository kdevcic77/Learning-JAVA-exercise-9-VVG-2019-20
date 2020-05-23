package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosPoslovnogKorisnikaController {
    @FXML
    private Label naziv;
    @FXML
    private Label web;
    @FXML
    private Label email;
    @FXML
    private Label telefon;
    @FXML
    private TextField txtFieldNaziv;
    @FXML
    private TextField txtFieldWeb;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldTelefon;
    @FXML
    private Button buttonUnos;

    List<Korisnik> listaKorisnika = new ArrayList<>();

    public void initialize() {
	listaKorisnika = Datoteke.dohvatiKorisnike().stream().filter(p -> p instanceof PoslovniKorisnik)
		.map(sc -> (PoslovniKorisnik) sc).collect(Collectors.toList());
    }
    public void unesiPoslovnogKorisnika() {
	Boolean ispravniPodaci = true;
	String poruka = "";

	String naziv = txtFieldNaziv.getText();
	String web = txtFieldWeb.getText();
	String email = txtFieldEmail.getText();
	String telefon = txtFieldTelefon.getText();
	if (Main.stringPrazan(naziv)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli naziv poslovnog korisnika!\n";
	}
	if (Main.stringPrazan(web)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli web poslovnog korisnika!\n";
	}
	if (Main.stringPrazan(email)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli email poslovnog korisnika!\n";
	}
	if (Main.stringPrazan(telefon)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli telefon poslovnog korisnika!\n";
	}
	if (ispravniPodaci == true) {
	    List<Korisnik> listaKorisnika = Datoteke.dohvatiKorisnike();
	    OptionalLong maxId = listaKorisnika.stream().mapToLong(Entitet::getId).max();
	    PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(maxId.getAsLong() + 1, txtFieldNaziv.getText(),
		    txtFieldWeb.getText(), txtFieldEmail.getText(), txtFieldTelefon.getText());
	    listaKorisnika.add(poslovniKorisnik);
	    Datoteke.zapisiUDatotekuKorisnika(listaKorisnika);
	    Main.alertPopup("Uspjeh", "Uspješno je unesen novi poslovni korisnik", "Èestitamo!");
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Nepravilan unos!");
	    alert.setHeaderText("Niste unijeli slijedeæe podatke:");
	    alert.setContentText(poruka);
	    alert.showAndWait();
	}
    }
}
