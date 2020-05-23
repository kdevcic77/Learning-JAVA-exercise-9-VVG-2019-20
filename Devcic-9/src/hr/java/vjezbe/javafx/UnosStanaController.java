package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosStanaController {
    @FXML
    private Label naslov;
    @FXML
    private Label opis;
    @FXML
    private Label kvadratura;
    @FXML
    private Label stanje;
    @FXML
    private Label cijena;
    @FXML
    private TextField txtFieldNaslov;
    @FXML
    private TextField txtFieldOpis;
    @FXML
    private TextField txtFieldKvadratura;
    @FXML
    private ChoiceBox<Stanje> choiceBoxStanje;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonUnos;
    List<Stan> listaStanova = new ArrayList<>();

    public void initialize() {
	choiceBoxStanje.getItems().setAll(Stanje.values());
	choiceBoxStanje.getSelectionModel().selectFirst();
	listaStanova = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Stan).map(sc -> (Stan) sc)
		.collect(Collectors.toList());
    }

    public void unesiStan() {
	Boolean ispravniPodaci = true;
	String poruka = "";

	String naslov = txtFieldNaslov.getText();
	String opis = txtFieldOpis.getText();
	String kvadratura = txtFieldKvadratura.getText();
	String cijena = txtFieldCijena.getText();
	
	if (Main.stringPrazan(naslov)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli naslov stana!\n";
	}
	if (Main.stringPrazan(opis)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli opis stana!\n";
	}
	if (Main.stringPrazan(kvadratura)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli kvadraturu stana!\n";
	}
	if (Main.stringPrazan(cijena)) {
	    ispravniPodaci = false;
	    poruka += "Niste unijeli cijenu stana!\n";
	}
	if (ispravniPodaci == true) {
	    List<Artikl> listaArtikala = Datoteke.dohvatiArtikle();
	    OptionalLong maxId = listaArtikala.stream().mapToLong(Entitet::getId).max();
	    Stan stan = new Stan(maxId.getAsLong() + 1, txtFieldNaslov.getText(), txtFieldOpis.getText(),
		    Integer.parseInt(txtFieldKvadratura.getText()), choiceBoxStanje.getValue(),
		    new BigDecimal(txtFieldCijena.getText()));
	    listaArtikala.add(stan);
	    Datoteke.zapisiUDatotekuArtikl(listaArtikala);
	    Main.alertPopup("Uspjeh", "Uspješno je unesen novi stan", "Èestitamo!");
	} else {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Nepravilan unos!");
	    alert.setHeaderText("Niste unijeli slijedeæe podatke:");
	    alert.setContentText(poruka);
	    alert.showAndWait();
	}
    }

}
