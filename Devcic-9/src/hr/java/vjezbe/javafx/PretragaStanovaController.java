package hr.java.vjezbe.javafx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
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

public class PretragaStanovaController {
    @FXML
    private Label naslov;
    @FXML
    private Label opis;
    @FXML
    private Label kvadratura;
    @FXML
    private Label cijena;
    @FXML
    private TextField txtFieldNaslov;
    @FXML
    private TextField txtFieldOpis;
    @FXML
    private TextField txtFieldKvadratura;
    @FXML
    private TextField txtFieldCijena;
    @FXML
    private Button buttonPretraga;
    @FXML
    private TableView<Stan> stanTableView;
    @FXML
    private TableColumn<Stan, String> naslovTableColumn;
    @FXML
    private TableColumn<Stan, String> opisTableColumn;
    @FXML
    private TableColumn<Stan, Integer> kvadraturaTableColumn;
    @FXML
    private TableColumn<Stan, Stanje> stanjeTableColumn;
    @FXML
    private TableColumn<Stan, BigDecimal> cijenaTableColumn;

    public void initialize() {
	naslovTableColumn.setCellValueFactory(new PropertyValueFactory<Stan, String>("naslov"));
	opisTableColumn.setCellValueFactory(new PropertyValueFactory<Stan, String>("opis"));
	kvadraturaTableColumn.setCellValueFactory(new PropertyValueFactory<Stan, Integer>("kvadratura"));
	stanjeTableColumn.setCellValueFactory(new PropertyValueFactory<Stan, Stanje>("stanje"));
	cijenaTableColumn.setCellValueFactory(new PropertyValueFactory<Stan, BigDecimal>("cijena"));

	List<Stan> listaStanova = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Stan).map(sc -> (Stan) sc)
		.collect(Collectors.toList());

	ObservableList<Stan> listaStanovaObservable = FXCollections.observableArrayList(listaStanova);

	stanTableView.setItems(listaStanovaObservable);
    }
    
    public void pretragaStanova() {
	List<Stan> listaStanova = Datoteke.dohvatiArtikle().stream().filter(p -> p instanceof Stan).map(sc -> (Stan) sc)
		.collect(Collectors.toList());
	List<Stan> privremenaLista = new ArrayList<>();
	List<Stan> filtriraniStanovi = new ArrayList<>();
	filtriraniStanovi.addAll(listaStanova);

	if (txtFieldNaslov.getText().isEmpty() == false) {
	    privremenaLista = (filtriraniStanovi.stream()
		    .filter(a -> a.getNaslov().toLowerCase().contains(txtFieldNaslov.getText().toLowerCase()))
		    .collect(Collectors.toList()));
	    filtriraniStanovi.clear();
	    filtriraniStanovi.addAll(privremenaLista);
	}
	if (txtFieldOpis.getText().isEmpty() == false) {
	    privremenaLista = filtriraniStanovi.stream()
		    .filter(a -> a.getOpis().toLowerCase().contains(txtFieldOpis.getText().toLowerCase()))
		    .collect(Collectors.toList());
	    filtriraniStanovi.clear();
	    filtriraniStanovi.addAll(privremenaLista);
	}
	if (txtFieldKvadratura.getText().isEmpty() == false) {
	    privremenaLista = filtriraniStanovi.stream()
		    .filter(a -> String.valueOf(a.getKvadratura()).contains(txtFieldKvadratura.getText()))
		    .collect(Collectors.toList());
	    filtriraniStanovi.clear();
	    filtriraniStanovi.addAll(privremenaLista);
	}

	if (txtFieldCijena.getText().isEmpty() == false) {
	    privremenaLista = filtriraniStanovi.stream()
		    .filter(a -> a.getCijena().toString().contains(txtFieldCijena.getText()))
		    .collect(Collectors.toList());
	    filtriraniStanovi.clear();
	    filtriraniStanovi.addAll(privremenaLista);
	}

	if (filtriraniStanovi.size() == 0) {
	    filtriraniStanovi.addAll(listaStanova);
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Upozorenje");
	    alert.setHeaderText("Ne postoji ništa pod tim filterom");
	    alert.setContentText("Nije stavljen nijedan filter");
	    alert.showAndWait();

	}

	ObservableList<Stan> listaFiltriranihStanovaObservable = FXCollections.observableArrayList(filtriraniStanovi);
	stanTableView.setItems(listaFiltriranihStanovaObservable);
    }
    
}
