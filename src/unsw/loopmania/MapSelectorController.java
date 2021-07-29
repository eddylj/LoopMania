package unsw.loopmania;

import java.io.File;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MapSelectorController {

    @FXML
    private FileSelector fileSelector;

    @FXML
    private Label errorLabel;

    @FXML
    private Label titleLabel;

    private StringProperty selectedProperty = new SimpleStringProperty();

    private String folder;

    public MapSelectorController(String folder) {
        this.folder = folder;
    }
    
    @FXML
    void initialize() {
        if (folder == "worlds") {
            titleLabel.setText("Select Map");
        }
        else if (folder == "backup") {
            titleLabel.setText("Select Save");
        }
        fileSelector.setDirectory(new File(folder));
        selectedProperty.bind(fileSelector.getSelectedProperty());
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        if (folder == "worlds") {
            if (selectedProperty.get() == null) {
                errorLabel.setText("Please choose a map");
                errorLabel.setTextFill(Color.RED);
                errorLabel.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 20));
            }
            else {
            // load the main menu
                MainMenuController mainMenuController = new MainMenuController(selectedProperty.get());
                FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
                menuLoader.setController(mainMenuController);
                Parent mainMenuRoot = menuLoader.load();

                Scene scene = this.errorLabel.getScene();
                Stage primaryStage = (Stage) scene.getWindow();
                
                primaryStage.setTitle("Loop Mania - Select Mode");
                
                // deploy the main onto the stage
                scene.setRoot(mainMenuRoot);
                mainMenuRoot.requestFocus();
                primaryStage.setScene(scene);
                primaryStage.sizeToScene();
                primaryStage.show();
            }
        }
        else if (folder == "backup") {

        }
    }
}
