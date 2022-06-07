package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller  {
    public ListView<Korisnik> listView;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldKorisnickoIme;
    public PasswordField fldLozinka;
    public Button btnDodaj;
    public Button btnKraj;

    private KorisniciModel model;

    public Controller(KorisniciModel m){
        model = m;
    }

    public void initialize() {
        listView.setItems(model.getKorisnici());

        //Ovdje cemo napraviti dvosmjerno povezivanje pomocu Listenera!!!
        model.trenutniKorisnikProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    if(oldKorisnik != null){
                        fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                        fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                        fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                        fldKorisnickoIme.textProperty().unbindBidirectional(oldKorisnik.korisnickoImeProperty());
                        fldLozinka.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
                    }
                    if(newKorisnik == null){
                        fldIme.setText("");
                        fldPrezime.setText("");
                        fldEmail.setText("");
                        fldKorisnickoIme.setText("");
                        fldLozinka.setText("");
                    }
                    else{
                        fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                        fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                        fldEmail.textProperty().bindBidirectional(newKorisnik.emailProperty());
                        fldKorisnickoIme.textProperty().bindBidirectional(newKorisnik.korisnickoImeProperty());
                        fldLozinka.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
                    }
                }
        );

        //Caka kako povezati list view i ove ostale komponente texta sa trenutnim odabranim korisnikom
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obsr, o, n) -> {
                    model.setTrenutniKorisnik(n);
                }
        );
    }


    public void btnDodajClick(ActionEvent actionEvent) {
        ObservableList<Korisnik> temp = model.getKorisnici();
        Korisnik novi = new Korisnik();
        temp.add(novi);
        model.setKorisnici(temp);
        model.setTrenutniKorisnik(novi);
    }

    public void btnKrajClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnKraj.getScene().getWindow();
        stage.close();
    }
}
