package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Stan;
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

public class PretragaPrivatnihKorisnikaController {
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
    private Button buttonPretraga;
    @FXML
    private TableView<PrivatniKorisnik> privatniKorisnikTableView;
    @FXML
    private TableColumn<PrivatniKorisnik, String> imeTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik, String> prezimeTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik, String> emailTableColumn;
    @FXML
    private TableColumn<PrivatniKorisnik, String> telefonTableColumn;

    public void initialize() {
	imeTableColumn.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("ime"));
	prezimeTableColumn.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("prezime"));
	emailTableColumn.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("email"));
	telefonTableColumn.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("telefon"));

	List<PrivatniKorisnik> listaPrivatnihKorisnika = Datoteke.dohvatiKorisnike().stream()
		.filter(p -> p instanceof PrivatniKorisnik).map(sc -> (PrivatniKorisnik) sc)
		.collect(Collectors.toList());

	ObservableList<PrivatniKorisnik> listaPrivatnihKorisnikaObservable = FXCollections
		.observableArrayList(listaPrivatnihKorisnika);

	privatniKorisnikTableView.setItems(listaPrivatnihKorisnikaObservable);
    }

    public void pretraziPrivatneKorisnike() {

	List<PrivatniKorisnik> listaPrivatnihKorisnika = Datoteke.dohvatiKorisnike().stream()
		.filter(p -> p instanceof PrivatniKorisnik).map(sc -> (PrivatniKorisnik) sc)
		.collect(Collectors.toList());
	List<PrivatniKorisnik> privremenaLista = new ArrayList<>();
	List<PrivatniKorisnik> filtiraniPrivatniKorisnici = new ArrayList<>();
//	filtiraniPrivatniKorisnici.addAll(listaPrivatnihKorisnika);

	if (txtFieldIme.getText().isEmpty() == false) {
	    privremenaLista = (listaPrivatnihKorisnika.stream()
		    .filter(a -> a.getIme().toLowerCase().contains(txtFieldIme.getText().toLowerCase()))
		    .collect(Collectors.toList()));
	    filtiraniPrivatniKorisnici.clear();
	    filtiraniPrivatniKorisnici.addAll(privremenaLista);
	}
	if (txtFieldPrezime.getText().isEmpty() == false) {
	    privremenaLista = listaPrivatnihKorisnika.stream()
		    .filter(a -> a.getPrezime().toLowerCase().contains(txtFieldPrezime.getText().toLowerCase()))
		    .collect(Collectors.toList());
	    filtiraniPrivatniKorisnici.clear();
	    filtiraniPrivatniKorisnici.addAll(privremenaLista);
	}
	if (txtFieldEmail.getText().isEmpty() == false) {
	    privremenaLista = listaPrivatnihKorisnika.stream()
		    .filter(a -> a.getEmail().contains(txtFieldEmail.getText())).collect(Collectors.toList());
	    filtiraniPrivatniKorisnici.clear();
	    filtiraniPrivatniKorisnici.addAll(privremenaLista);
	}

	if (txtFieldTelefon.getText().isEmpty() == false) {
	    privremenaLista = listaPrivatnihKorisnika.stream()
		    .filter(a -> a.getTelefon().contains(txtFieldTelefon.getText())).collect(Collectors.toList());
	    filtiraniPrivatniKorisnici.clear();
	    filtiraniPrivatniKorisnici.addAll(privremenaLista);
	}

	if (filtiraniPrivatniKorisnici.size() == 0) {
	    filtiraniPrivatniKorisnici.addAll(listaPrivatnihKorisnika);
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Upozorenje");
	    alert.setHeaderText("Ne postoji ništa pod tim filterom");
	    alert.setContentText("Nije stavljen nijedan filter");
	    alert.showAndWait();

	}

	ObservableList<PrivatniKorisnik> listaFiltriranihPrivatnihKorisnikaObservable = FXCollections
		.observableArrayList(filtiraniPrivatniKorisnici);
	privatniKorisnikTableView.setItems(listaFiltriranihPrivatnihKorisnikaObservable);
    }
}
