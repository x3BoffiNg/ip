package boffbot;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI window of BoffBot.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    private BoffBot boffBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image boffImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the BoffBot instance into the GUI controller.
     * <p>
     * This allows the GUI to delegate command processing to the chatbot
     * without creating or managing the bot instance itself.
     *
     * @param d The BoffBot instance used to process user commands.
     */
    public void setBoffBot(BoffBot d) {
        boffBot = d;
    }

    /**
     * Handles user input from the GUI.
     * <p>
     * This method retrieves the text entered by the user,
     * sends it to {@link boffbot.BoffBot} for processing,
     * and displays both the user input and bot response
     * as dialog boxes in the main window.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = boffBot.getResponse(input);

        boolean isExit = false;

        if (response.contains("__EXIT__")) {
            isExit = true;
            response = response.replace("__EXIT__", "");
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, boffImage)
        );

        userInput.clear();

        if (isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
