package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;

    private LoopManiaWorldController mainController;

    private String folder;

    private String map;

    @FXML
    private VBox rootBox;

    public MainMenuController(String folder, String map) {
        this.folder = folder;
        this.map = map;
    }

    private void loadWorld(String map) throws IOException {
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader(folder, map);
        mainController = loopManiaLoader.loadController();
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();

        Scene scene = rootBox.getScene();
        Stage primaryStage = (Stage) scene.getWindow();

        primaryStage.setTitle("Loop Mania");
        // mainController.setMainMenuSwitcher((String mode) -> {switchToRoot(scene, mainMenuRoot, primaryStage);
        //     stop();
        // });
        this.setGameSwitcher((String mode) -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
            mainController.setMode(mode);
            mainController.play();
        });

        gameRoot.requestFocus();
    }

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException {
        loadWorld(map);
        gameSwitcher.switchMenu("standard");
    }

    @FXML
    private void switchToSurvival() throws IOException {
        loadWorld(map);
        gameSwitcher.switchMenu("survival");
    }

    @FXML
    private void switchToBerserker() throws IOException {
        loadWorld(map);
        gameSwitcher.switchMenu("beserker");
    }

    @FXML
    private void switchToConfusing() throws IOException {
        loadWorld(map);
        gameSwitcher.switchMenu("confusing");
    }


    /**
     * switch to a different Root
     */
    private void switchToRoot(Scene scene, Parent root, Stage stage){
        scene.setRoot(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void stop(){
        // wrap up activities when exit program
        mainController.terminate();
    }
}
