package unsw.loopmania;

import java.io.File;
import java.io.IOException;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ShopBuyController {

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private GridPane shopItems;

    @FXML
    private AnchorPane itemCosts;

    private LoopManiaWorldController worldController;

    private LoopManiaWorld world;

    private String[] itemList;

    private Shop shop;

    public ShopBuyController(LoopManiaWorldController worldController, LoopManiaWorld world) {
        this.world = world;
        this.worldController = worldController;
        itemList = new String[] {"sword", "stake", "staff", "armour", "shield", "helmet", "healthpotion"};
        shop = new Shop(world.getCharacter());
    }

    @FXML
    public void initialize() {
        addItems(itemList);
        addDoneButton();
        addSellButton();
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
        worldController.setBuyShopOpen(false);
        worldController.tryToPlay();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public void addItems(String[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            String itemName = itemList[i];
            String itemString;
            int itemLevel = shop.getItemBuyLevel(itemName);
            if (itemName.equals("healthpotion")) {
                itemString = itemName;
            }
            else {
                itemString = itemName + itemLevel;
            }
            ImageView view = new ImageView(new Image((new File(String.format("src/images/%s.png", itemString))).toURI().toString()));
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
        int price = shop.getBuyPrice(itemName);
        Button buyButton = new Button(Integer.toString(price));
        buyButton.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 25));
        buyButton.disableProperty().bind(Bindings.lessThan(world.getGold(), price));
        buyButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                StaticEntity item = (StaticEntity) shop.buy(itemName);
                worldController.loadItem((Item)item);
            }
        });
        return buyButton;
    }

    public void addSellButton() {
        Button sellButton = new Button("Sell");
        sellButton.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD,40));
        sellButton.setTextFill(Color.ORANGE);
        sellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                ShopSellController shopSellController = new ShopSellController(world, worldController);
                FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("ShopView.fxml"));
                shopLoader.setController(shopSellController);
                
                try {
                    Scene scene = new Scene(shopLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Shop-Sell");
                    worldController.setSellShopOpen(true);
                    stage.setOnCloseRequest(closeEvent -> {
                        worldController.setSellShopOpen(false);
                        worldController.tryToPlay();
                    });
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        itemCosts.getChildren().add(sellButton);
        AnchorPane.setTopAnchor(sellButton, (double)450);
        AnchorPane.setLeftAnchor(sellButton, (double)200);
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