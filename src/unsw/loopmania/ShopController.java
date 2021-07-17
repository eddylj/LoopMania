package unsw.loopmania;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.ColorUIResource;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ShopController {
    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private GridPane shopItems;

    @FXML
    private AnchorPane itemCosts;

    private LoopManiaWorldController worldController;

    private String[] itemList;

    private int itemHeight;

    private int itemWidth;

    public ShopController(LoopManiaWorldController worldController) {
        this.worldController = worldController;
        itemList = new String[] {};
    }

    @FXML
    public void initialize() {
        addItems(itemList);
        addDoneButton();
    }

    public void addDoneButton() {
        Button done = new Button("Done");
        done.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD,40));
        done.setTextFill(Color.GREEN);
        done.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                doneButtonAction(done);
            }
        });

        itemCosts.getChildren().add(done);
        AnchorPane.setTopAnchor(done, (double)550);
        AnchorPane.setLeftAnchor(done, (double)200);
    }

    public void doneButtonAction(Button done) {
        worldController.play();
        worldController.setShopOpen(false);
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public void addItems(String[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            String itemName = itemList[i];
            ImageView view = new ImageView(new Image((new File(String.format("src/images/%s.png", itemName))).toURI().toString()));
            int row = i / 3;
            int col = i % 3;
            view.setFitHeight(100);
            view.setFitWidth(100);
            shopItems.add(view, col, row);
            GridPane.setHalignment(view, HPos.CENTER);
            GridPane.setValignment(view, VPos.CENTER);
            
            ImageView goldView = new ImageView(new Image((new File("src/images/gold_pile.png")).toURI().toString()));
            goldView.setFitHeight(40);
            goldView.setFitWidth(40);

            Button item = makeItemButton(itemName);

            GridPane gridPane = new GridPane();
            gridPane.add(goldView, 0, 0);
            gridPane.add(item, 1, 0);
            itemCosts.getChildren().add(gridPane);

            AnchorPane.setTopAnchor(gridPane, getTopAnchor(i));
            AnchorPane.setLeftAnchor(gridPane, getLeftAnchor(i));
        }
    }

    public Button makeItemButton(String itemName) {
        Button button = new Button();
        return button;
    }

    public double getTopAnchor(int i) {
        if (i < 3) {
            return 170;
        }
        else if (i > 2 && i < 6) {
            return 187*2;
        }
        else {
            return 189*3;
        }
    }

    public double getLeftAnchor(int i) {
        if (i % 3 == 0) {
            return 10;
        }
        else if (i == 1 || i == 4) {
            return 160;
        }
        else {
            return 315;
        }
    }
}