package unsw.loopmania;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.ColorUIResource;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
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

public class ShopSellController extends ShopController {

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private GridPane shopItems;

    @FXML
    private AnchorPane itemCosts;

    private LoopManiaWorldController worldController;

    private LoopManiaWorld world;

    ShopBuyController buyController;

    public ShopSellController(LoopManiaWorldController worldController, LoopManiaWorld world) {
        super(worldController);
        this.world = world;
        this.worldController = worldController;
    }

    @FXML
    public void initialize() {
        super.addItems();
        super.addDoneButton();
        // addBuyModeButton();
    }

    // private void addDoneButton() {
    //     Button done = new Button("Done");
    //     done.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 60));
    //     done.setTextFill(Color.GREEN);
    //     done.setOnAction(new EventHandler<ActionEvent>(){
    //         @Override
    //         public void handle(ActionEvent arg0) {
    //             worldController.play();
    //             worldController.setShopOpen(false);
    //             Stage stage = (Stage) done.getScene().getWindow();
    //             stage.close();
    //         }
    //     });

    //     itemCosts.getChildren().add(done);
    //     AnchorPane.setTopAnchor(done, (double)470);
    //     AnchorPane.setLeftAnchor(done, (double)170);
    // }


    // public void addPossibleBuyItems() {
    //     String[] itemList = new String[] {"sword", "stake", "staff", "armour", "shield", "helmet", "healthpotion"};

    //     for (int i = 0; i < 7; i++) {
    //         String itemName = itemList[i];
    //         ImageView view = new ImageView(new Image((new File(String.format("src/images/%s.png", itemName))).toURI().toString()));
    //         int row = i / 3;
    //         int col = i % 3;
    //         view.setFitHeight(100);
    //         view.setFitWidth(100);
    //         shopItems.add(view, col, row);
    //         GridPane.setHalignment(view, HPos.CENTER);
    //         GridPane.setValignment(view, VPos.CENTER);
            
    //         ImageView goldView = new ImageView(new Image((new File("src/images/gold_pile.png")).toURI().toString()));
    //         goldView.setFitHeight(40);
    //         goldView.setFitWidth(40);

            
    //         Button buyButton = makeBuyButton(itemName);


    //         GridPane gridPane = new GridPane();
    //         gridPane.add(goldView, 0, 0);
    //         gridPane.add(buyButton, 1, 0);
    //         itemCosts.getChildren().add(gridPane);

    //         AnchorPane.setTopAnchor(gridPane, getTopAnchor(i));
    //         AnchorPane.setLeftAnchor(gridPane, getLeftAnchor(i));
    //     }
    // }

    @Override
    public Button makeItemButton(String itemName) {
        int price = -2; // TODO: make this according to each item
        Button buyButton = new Button(Integer.toString(price));
        buyButton.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 25));

        // TODO: link button to buying item
        if (world.getGold() >= price) {
            buyButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    
                }
            });
        }
        else {
            buyButton.setTextFill(Color.RED);
        }
        return buyButton;
    }

    // public double getTopAnchor(int i) {
    //     if (i < 3) {
    //         return 170;
    //     }
    //     else if (i > 2 && i < 6) {
    //         return 187*2;
    //     }
    //     else {
    //         return 189*3;
    //     }
    // }

    // public double getLeftAnchor(int i) {
    //     if (i % 3 == 0) {
    //         return 10;
    //     }
    //     else if (i == 1 || i == 4) {
    //         return 160;
    //     }
    //     else {
    //         return 315;
    //     }
    // }

    // private void inventory() {
    //     Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());

    //     for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
    //         for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
    //             ImageView emptySlotView = new ImageView(inventorySlotImage);
    //             unequippedInventory.add(emptySlotView, x, y);
    //         }
    //     }

    //     for (Item i : world.getItems()) {
    //         onLoad(i);
    //     }
    // }
}