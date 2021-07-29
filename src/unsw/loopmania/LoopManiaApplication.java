package unsw.loopmania;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the main application
 * run main method from this class
 */
public class LoopManiaApplication extends Application {

    /**
     * the controller for the game. Stored as a field so can terminate it when click exit button
     */
    // private LoopManiaWorldController mainController;

    // @Override
    // public void start(Stage primaryStage) throws IOException {


    //     // load the main game
    //     System.out.println("Yoo it happened here");


    //     // load the main menu
    //     MainMenuController mainMenuController = new MainMenuController();
    //     FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
    //     menuLoader.setController(mainMenuController);
    //     Parent mainMenuRoot = menuLoader.load();


    //     // create new scene with the main menu (so we start with the main menu)
    //     Scene scene = new Scene(mainMenuRoot);

        
        
    //     // set functions which are activated when button click to switch menu is pressed
    //     // e.g. from main menu to start the game, or from the game to return to main menu

        
    //     // deploy the main onto the stage
    //     mainMenuRoot.requestFocus();
    //     primaryStage.setScene(scene);
    //     primaryStage.show();
    // }

    // public static void main(String[] args) {
    //     launch(args);
    // }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // set title on top of window bar
        primaryStage.setTitle("Loop Mania - Select Map");

        // prevent human player resizing game window (since otherwise would see white space)
        primaryStage.setResizable(false);

        // load the map selector menu
        FXMLLoader mapSelectLoader = new FXMLLoader(getClass().getResource("MapSelectorMenu.fxml"));
        Parent mapSelectRoot = mapSelectLoader.load();

        // create new scene with the map selector menu (so we start with this menu)
        Scene mapSelectScene = new Scene(mapSelectRoot);

        // deploy the menu onto the stage
        mapSelectRoot.requestFocus();
        primaryStage.setScene(mapSelectScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
