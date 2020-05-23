package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosUslugeController {
    @FXML
    private Label naslov;
    @FXML
    private Label opis;
    @FXML
    private Label stanje;
    @FXML
    private Label cijena;
    @FXML
    private TextField txtFieldNaslov;
    @FXML
    private TextField txtFieldOpis;
    @FXML
    private ChoiceBox<Stanje> choiceBoxStanje;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonUnos;

    List<Usluga> listaUsluga = new ArrayList<>();

    public void initialize() {
	choiceBoxStanje.getItems().setAll(Stanje.values());
	choiceBoxStanje.getSelectionModel().selectFirst();
	listaUsluga = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Usluga).map(sc -> (Usluga) sc)
		.collect(Collectors.toList());
    }

    public void unesiUslugu() {
	Boolean ispravniPodaci = true;
	String poruka = "";

	String naslov = txtFieldNaslov.getText();
	String opis = txtFieldOpis.getText();
	String cijena = txtFieldCijena.getText();

	if (Main.stringPrazan(naslov)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli naslov usluge!\n";
	}
	if (Main.stringPrazan(opis)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli opis usluge!\n";
	}
	if (Main.stringPrazan(cijena)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli cijenu usluge!\n";
	}
	if (ispravniPodaci == true) {
	    List<Artikl> listaArtikala = Datoteke.dohvatiArtikle();
	    OptionalLong maxId = listaArtikala.stream().mapToLong(Entitet::getId).max();
	    Usluga usluga = new Usluga(maxId.getAsLong() + 1, txtFieldNaslov.getText(), txtFieldOpis.getText(),
		    choiceBoxStanje.getValue(), new BigDecimal(txtFieldCijena.getText()));
	    listaArtikala.add(usluga);
	    Datoteke.zapisiUDatotekuArtikl(listaArtikala);
	    Main.alertPopup("Uspjeh", "Uspješno je unesen nova usluga", "Èestitamo!");
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Nepravilan unos!");
	    alert.setHeaderText("Niste unijeli slijedeæe podatke:");
	    alert.setContentText(poruka);
	    alert.showAndWait();
	}
    }
}
