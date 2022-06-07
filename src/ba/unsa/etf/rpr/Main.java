package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {


    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        KorisniciModel model = new KorisniciModel();
        model.napuni();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        loader.setController(new Controller(model));

        Parent root = loader.load();


        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Korisnici");
        stage.setMinWidth(500);
        stage.setMinHeight(300);
        stage.toFront();
        stage.show();
    }
}
