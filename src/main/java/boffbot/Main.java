package boffbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private BoffBot boffBot = new BoffBot("data/boffbot.txt");

    /**
     * Starts the JavaFX application and initializes the GUI.
     *<p>
     * This method loads the main window layout from FXML,
     * creates a single {@link boffbot.BoffBot} instance,
     * and injects it into the GUI controller for handling user input.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            MainWindow controller = fxmlLoader.getController();
            controller.setBoffBot(boffBot);

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(
                    Main.class.getResource("/view/style.css").toExternalForm()
            );

            stage.setScene(scene);
            stage.setTitle("BoffBot");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
