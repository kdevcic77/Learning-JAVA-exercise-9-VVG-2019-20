package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class PretragaAutomobilaController {
    @FXML
    private Label naslov;
    @FXML
    private Label opis;
    @FXML
    private Label snagaKs;
    @FXML
    private Label cijena;
    @FXML
    private TextField txtFieldNaslov;
    @FXML
    private TextField txtFieldOpis;
    @FXML
    private TextField txtFieldSnagaKs;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonPretraga;

    @FXML
    private TableView<Automobil> automobilTableView;
    @FXML
    private TableColumn<Automobil, String> naslovTableColumn;
    @FXML
    private TableColumn<Automobil, String> opisTableColumn;
    @FXML
    private TableColumn<Automobil, BigDecimal> snagaKsTableColumn;
    @FXML
    private TableColumn<Automobil, Stanje> stanjeTableColumn;
    @FXML
    private TableColumn<Automobil, BigDecimal> cijenaTableColumn;


    List<Automobil> listaAutomobila = new ArrayList<>();

    public void initialize() {
	naslovTableColumn.setCellValueFactory(new PropertyValueFactory<Automobil, String>("naslov"));
	opisTableColumn.setCellValueFactory(new PropertyValueFactory<Automobil, String>("opis"));
	snagaKsTableColumn.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("snagaKs"));
	stanjeTableColumn.setCellValueFactory(new PropertyValueFactory<Automobil, Stanje>("stanje"));
	cijenaTableColumn.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("cijena"));

	List<Automobil> listaAutomobila = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Automobil)
		.map(sc -> (Automobil) sc).collect(Collectors.toList());

	ObservableList<Automobil> listaAutomobilaObservable = FXCollections.observableArrayList(listaAutomobila);

	automobilTableView.setItems(listaAutomobilaObservable);
    }

    public void pretraziAutomobile() {
	List<Automobil> listaAutomobila = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Automobil)
		.map(sc -> (Automobil) sc).collect(Collectors.toList());
	List<Automobil> privremenaLista = new ArrayList<>();
	List<Automobil> filtriraniAutomobili = new ArrayList<>();

	if (txtFieldNaslov.getText().isEmpty() == false) {
	    privremenaLista = (listaAutomobila.stream()
		    .filter(a -> a.getNaslov().toLowerCase().contains(txtFieldNaslov.getText().toLowerCase()))
		    .collect(Collectors.toList()));
	    filtriraniAutomobili.clear();
	    filtriraniAutomobili.addAll(privremenaLista);
	}
	if (txtFieldOpis.getText().isEmpty() == false) {
	    privremenaLista = listaAutomobila.stream()
		    .filter(a -> a.getOpis().toLowerCase().contains(txtFieldOpis.getText().toLowerCase()))
		    .collect(Collectors.toList());
	    filtriraniAutomobili.clear();
	    filtriraniAutomobili.addAll(privremenaLista);
	}
	if (txtFieldSnagaKs.getText().isEmpty() == false) {
	    privremenaLista = listaAutomobila.stream()
		    .filter(a -> a.getSnagaKs().toString().contains(txtFieldSnagaKs.getText()))
		    .collect(Collectors.toList());
	    filtriraniAutomobili.clear();
	    filtriraniAutomobili.addAll(privremenaLista);
	}

	if (txtFieldCijena.getText().isEmpty() == false) {
	    privremenaLista = listaAutomobila.stream()
		    .filter(a -> a.getCijena().toString().contains(txtFieldCijena.getText()))
		    .collect(Collectors.toList());
	    filtriraniAutomobili.clear();
	    filtriraniAutomobili.addAll(privremenaLista);
	}

	if (filtriraniAutomobili.size() == 0) {
	    filtriraniAutomobili.addAll(listaAutomobila);
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Upozorenje");
	    alert.setHeaderText("Ne postoji ništa pod tim filterom");
	    alert.setContentText("Nije stavljen nijedan filter");
	    alert.showAndWait();

	}

	ObservableList<Automobil> listaFiltriranihAutomobilaObservable = FXCollections.observableArrayList(filtriraniAutomobili);
	automobilTableView.setItems(listaFiltriranihAutomobilaObservable);
    }

}
