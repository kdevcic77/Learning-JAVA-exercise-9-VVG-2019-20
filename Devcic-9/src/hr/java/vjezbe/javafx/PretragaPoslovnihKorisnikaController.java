package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.PoslovniKorisnik;
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

public class PretragaPoslovnihKorisnikaController {
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
    private Button buttonPretraga;
    @FXML
    private TableView<PoslovniKorisnik> poslovniKorisnikTableView;
    @FXML
    private TableColumn<PoslovniKorisnik, String> nazivTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik, String> webTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik, String> emailTableColumn;
    @FXML
    private TableColumn<PoslovniKorisnik, String> telefonTableColumn;

    public void initialize() {
	nazivTableColumn.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("naziv"));
	webTableColumn.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("web"));
	emailTableColumn.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("email"));
	telefonTableColumn.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("telefon"));

	List<PoslovniKorisnik> listaPoslovnihKorisnika = Datoteke.dohvatiKorisnike().stream()
		.filter(p -> p instanceof PoslovniKorisnik).map(sc -> (PoslovniKorisnik) sc)
		.collect(Collectors.toList());
	ObservableList<PoslovniKorisnik> listaPoslovnihKorisnikaObservable = FXCollections
		.observableArrayList(listaPoslovnihKorisnika);
	poslovniKorisnikTableView.setItems(listaPoslovnihKorisnikaObservable);
    }

    public void pretraziPoslovneKorisnike() {
	List<PoslovniKorisnik> listaPoslovnihKorisnika = Datoteke.dohvatiKorisnike().stream()
		.filter(p -> p instanceof PoslovniKorisnik).map(sc -> (PoslovniKorisnik) sc)
		.collect(Collectors.toList());
	List<PoslovniKorisnik> privremenaLista = new ArrayList<>();
	List<PoslovniKorisnik> filtiraniPoslovniKorisnici = new ArrayList<>();
	if (txtFieldNaziv.getText().isEmpty() == false) {
	    privremenaLista = (listaPoslovnihKorisnika.stream()
		    .filter(a -> a.getNaziv().toLowerCase().contains(txtFieldNaziv.getText().toLowerCase()))
		    .collect(Collectors.toList()));
	    filtiraniPoslovniKorisnici.clear();
	    filtiraniPoslovniKorisnici.addAll(privremenaLista);
	}
	if (txtFieldWeb.getText().isEmpty() == false) {
	    privremenaLista = listaPoslovnihKorisnika.stream()
		    .filter(a -> a.getWeb().toLowerCase().contains(txtFieldWeb.getText().toLowerCase()))
		    .collect(Collectors.toList());
	    filtiraniPoslovniKorisnici.clear();
	    filtiraniPoslovniKorisnici.addAll(privremenaLista);
	}
	if (txtFieldEmail.getText().isEmpty() == false) {
	    privremenaLista = listaPoslovnihKorisnika.stream()
		    .filter(a -> a.getEmail().toLowerCase().contains(txtFieldEmail.getText()))
		    .collect(Collectors.toList());
	    filtiraniPoslovniKorisnici.clear();
	    filtiraniPoslovniKorisnici.addAll(privremenaLista);
	}

	if (txtFieldTelefon.getText().isEmpty() == false) {
	    privremenaLista = listaPoslovnihKorisnika.stream()
		    .filter(a -> a.getTelefon().contains(txtFieldTelefon.getText())).collect(Collectors.toList());
	    filtiraniPoslovniKorisnici.clear();
	    filtiraniPoslovniKorisnici.addAll(privremenaLista);
	}

	if (filtiraniPoslovniKorisnici.size() == 0) {
	    filtiraniPoslovniKorisnici.addAll(listaPoslovnihKorisnika);
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Upozorenje");
	    alert.setHeaderText("Ne postoji ništa pod tim filterom");
	    alert.setContentText("Nije stavljen nijedan filter");
	    alert.showAndWait();

	}

	ObservableList<PoslovniKorisnik> listaFiltriranihPoslovnihKorisnikaObservable = FXCollections
		.observableArrayList(filtiraniPoslovniKorisnici);
	poslovniKorisnikTableView.setItems(listaFiltriranihPoslovnihKorisnikaObservable);
    }
}
