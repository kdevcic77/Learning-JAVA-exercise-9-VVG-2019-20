package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class PretragaUslugaController {
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
    private TextField txtFieldStanje;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonPretraga;

    @FXML
    private TableView<Usluga> uslugaTableView;
    @FXML
    private TableColumn<Usluga, String> naslovTableColumn;
    @FXML
    private TableColumn<Usluga, String> opisTableColumn;
    @FXML
    private TableColumn<Usluga, Stanje> stanjeTableColumn;
    @FXML
    private TableColumn<Usluga, BigDecimal> cijenaTableColumn;
    
    public void initialize() {
	naslovTableColumn.setCellValueFactory(new PropertyValueFactory<Usluga, String>("naslov"));
	opisTableColumn.setCellValueFactory(new PropertyValueFactory<Usluga, String>("opis"));
	stanjeTableColumn.setCellValueFactory(new PropertyValueFactory<Usluga, Stanje>("stanje"));
	cijenaTableColumn.setCellValueFactory(new PropertyValueFactory<Usluga, BigDecimal>("cijena"));

	List<Usluga> listaUsluga = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Usluga).map(sc -> (Usluga) sc)
		.collect(Collectors.toList());

	ObservableList<Usluga> listaUslugaObservable = FXCollections.observableArrayList(listaUsluga);

	uslugaTableView.setItems(listaUslugaObservable);
    }
    
    public void pretraziUsluge() {
	List<Usluga> listaUsluga = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Usluga).map(sc -> (Usluga) sc)
		.collect(Collectors.toList());
	List<Usluga> privremenaLista = new ArrayList<>();
	List<Usluga> filtriraneUsluge = new ArrayList<>();
	
	if (txtFieldNaslov.getText().isEmpty() == false) {
	    privremenaLista = (listaUsluga.stream()
		    .filter(a -> a.getNaslov().toLowerCase().contains(txtFieldNaslov.getText().toLowerCase()))
		    .collect(Collectors.toList()));
	    filtriraneUsluge.clear();
	    filtriraneUsluge.addAll(privremenaLista);
	}
	if (txtFieldOpis.getText().isEmpty() == false) {
	    privremenaLista = listaUsluga.stream()
		    .filter(a -> a.getOpis().toLowerCase().contains(txtFieldOpis.getText().toLowerCase()))
		    .collect(Collectors.toList());
	    filtriraneUsluge.clear();
	    filtriraneUsluge.addAll(privremenaLista);
	}
	if (txtFieldStanje.getText().isEmpty() == false) {
	    privremenaLista = listaUsluga.stream()
		    .filter(a -> a.getStanje().toString().contains(txtFieldStanje.getText()))
		    .collect(Collectors.toList());
	    filtriraneUsluge.clear();
	    filtriraneUsluge.addAll(privremenaLista);
	}

	if (txtFieldCijena.getText().isEmpty() == false) {
	    privremenaLista = listaUsluga.stream()
		    .filter(a -> a.getCijena().toString().contains(txtFieldCijena.getText()))
		    .collect(Collectors.toList());
	    filtriraneUsluge.clear();
	    filtriraneUsluge.addAll(privremenaLista);
	}
	if (filtriraneUsluge.size() == 0) {
	    filtriraneUsluge.addAll(listaUsluga);
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Upozorenje");
	    alert.setHeaderText("Ne postoji ništa pod tim filterom");
	    alert.setContentText("Nije stavljen nijedan filter");
	    alert.showAndWait();

	}
	ObservableList<Usluga> listaFiltriranihUslugaObservable = FXCollections.observableArrayList(filtriraneUsluge);
	uslugaTableView.setItems(listaFiltriranihUslugaObservable);
    }
}
