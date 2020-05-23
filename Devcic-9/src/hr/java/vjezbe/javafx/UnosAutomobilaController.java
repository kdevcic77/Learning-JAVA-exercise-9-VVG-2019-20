package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UnosAutomobilaController {
    @FXML
    private Label naslov;
    @FXML
    private Label opis;
    @FXML
    private Label snagaKs;
    @FXML
    private Label stanje;
    @FXML
    private Label cijena;
    @FXML
    private TextField txtFieldNaslov;
    @FXML
    private TextField txtFieldOpis;
    @FXML
    private TextField txtFieldSnagaKs;
    @FXML
    private ChoiceBox<Stanje> choiceBoxStanje;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonUnos;

    List<Automobil> listaAutomobila = new ArrayList<>();

    public void initialize() {
	choiceBoxStanje.getItems().setAll(Stanje.values());
	choiceBoxStanje.getSelectionModel().selectFirst();
	listaAutomobila = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Automobil)
		.map(sc -> (Automobil) sc).collect(Collectors.toList());
    }

    public void unesiAutomobil() {
	Boolean ispravniPodaci = true;
	String poruka = "";

	String naslov = txtFieldNaslov.getText();
	String opis = txtFieldOpis.getText();
	String snagaKs = txtFieldSnagaKs.getText();
	String cijena = txtFieldCijena.getText();
	if (Main.stringPrazan(naslov)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli naslov automobila!\n";
	}
	if (Main.stringPrazan(opis)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli opis automobila!\n";
	}
	if (Main.stringPrazan(snagaKs)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli snagu automobila!\n";
	}
	if (Main.stringPrazan(cijena)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli cijenu automobila!\n";
	}
	if (ispravniPodaci == true) {
	    List<Artikl> listaArtikala = Datoteke.dohvatiArtikle();
	    OptionalLong maxId = listaArtikala.stream().mapToLong(Entitet::getId).max();
	    Automobil automobil = new Automobil(maxId.getAsLong() + 1, txtFieldNaslov.getText(), txtFieldOpis.getText(),
		    new BigDecimal(txtFieldSnagaKs.getText()), choiceBoxStanje.getValue(),
		    new BigDecimal(txtFieldCijena.getText()));
	    listaArtikala.add(automobil);
	    Datoteke.zapisiUDatotekuArtikl(listaArtikala);
	    Main.alertPopup("Uspjeh", "Uspješno je unesen novi automobil", "Èestitamo!");
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Nepravilan unos!");
	    alert.setHeaderText("Niste unijeli slijedeæe podatke:");
	    alert.setContentText(poruka);
	    alert.showAndWait();
	}

    }

}
