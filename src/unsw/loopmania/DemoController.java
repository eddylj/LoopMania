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
import javafx.stage.Stage;

public class DemoController {

    @FXML
    private FileSelector fileSelector;

    @FXML
    private Label errorLabel;

    private StringProperty selectedProperty = new SimpleStringProperty();

    @FXML
    void submit(ActionEvent event) throws IOException {
        System.out.print(selectedProperty.get());

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

    @FXML
    void initialize() {
        fileSelector.setDirectory(new File("worlds"));
        selectedProperty.bind(fileSelector.getSelectedProperty());
    }
}
