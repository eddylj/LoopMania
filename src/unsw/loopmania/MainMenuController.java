package unsw.loopmania;

import java.io.FileNotFoundException;
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

    private String map;


    @FXML
    private VBox rootBox;

    private void loadWorld(String map) throws IOException {
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader(map);
        mainController = loopManiaLoader.loadController();
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();

        Scene scene = rootBox.getScene();
        Stage primaryStage = (Stage) scene.getWindow();
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
        loadWorld("world_with_twists_and_turns.json");
        gameSwitcher.switchMenu("standard");
    }

    @FXML
    private void switchToSurvival() throws IOException {
        loadWorld("one_ring_grind.json");
        gameSwitcher.switchMenu("survival");
    }

    @FXML
    private void switchToBerserker() throws IOException {
        loadWorld("world_with_complex_goal.json");
        gameSwitcher.switchMenu("beserker");
    }

    @FXML
    private void switchToConfusing() throws IOException {
        loadWorld("three_by_three_world.json");
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
