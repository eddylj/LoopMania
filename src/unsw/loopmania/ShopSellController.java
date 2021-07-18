package unsw.loopmania;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ShopSellController {

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private GridPane shopItems;

    @FXML
    private AnchorPane itemCosts;

    private LoopManiaWorld world;

    private LoopManiaWorldController worldController;

    private Shop shop;

    public ShopSellController(LoopManiaWorld world, LoopManiaWorldController worldController) {
        this.world = world;
        this.worldController = worldController;
        shop = new Shop(world.getCharacter());
    }

    @FXML
    public void initialize() {
        for (ColumnConstraints columnConstraint: shopItems.getColumnConstraints()) {
            columnConstraint.setPercentWidth(25);
        }
        for (RowConstraints rowConstraint: shopItems.getRowConstraints()) {
            rowConstraint.setPercentHeight(25);
        }
        addItems();
        addDoneButton();
    }

    public void addDoneButton() {
        Button done = new Button("Done");
        done.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 25));
        done.setTextFill(Color.GREEN);
        done.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                doneButtonAction(done);
            }
        });

        itemCosts.getChildren().add(done);
        AnchorPane.setTopAnchor(done, (double)580);
        AnchorPane.setLeftAnchor(done, (double)180);
    }

    public void doneButtonAction(Button done) {
        worldController.setSellShopOpen(false);
        worldController.tryToPlay();
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public void addItems() {
        int i = 0;
        for (Item item: world.getItems()) {
            int itemLevel = 0;
            if (item instanceof Weapon) {
                itemLevel = ((Weapon)item).getLevel();
            }
            else if (item instanceof Protection) {
                itemLevel = ((Protection)item).getLevel();
            }
            String itemName = ((StaticEntity) item).getType();
            String itemString;
            if (item instanceof HealthPotion || item instanceof TheOneRing) {
                itemString = itemName;
            }
            else {
                itemString = itemName + itemLevel;
            }
            ImageView view = new ImageView(new Image((new File(String.format("src/images/%s.png", itemString))).toURI().toString()));
            int row = i / 4;
            int col = i % 4;
            view.setFitHeight(70);
            view.setFitWidth(70);
            shopItems.add(view, col, row);
            GridPane.setHalignment(view, HPos.CENTER);
            GridPane.setValignment(view, VPos.CENTER);
            
            ImageView goldView = new ImageView(new Image((new File("src/images/gold_pile.png")).toURI().toString()));
            goldView.setFitHeight(28);
            goldView.setFitWidth(28);

            Button itemButton = makeItemButton(item, i);

            GridPane gridPane = new GridPane();
            gridPane.add(goldView, 0, 0);
            gridPane.add(itemButton, 1, 0);
            itemCosts.getChildren().add(gridPane);

            AnchorPane.setTopAnchor(gridPane, getTopAnchor(i));
            AnchorPane.setLeftAnchor(gridPane, getLeftAnchor(i));
            i++;
        }
    }

    public Button makeItemButton(Item item, int position) {
        int price = shop.getSellPrice(item);
        Button sellButton = new Button(Integer.toString(price));
        sellButton.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 18));
        
        sellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    shop.sell(item);
                    sellButton.setTextFill(Color.DARKRED);
                    sellButton.setDisable(true);
                }
            });
        return sellButton;
    }

    public double getTopAnchor(int i) {
        if (i < 4) {
            return 133;
        }
        if (i < 8) {
            return 288;
        }
        if (i < 12) {
            return 435;
        }
        else {
            return 540;
        }
    }

    public double getLeftAnchor(int i) {
        if (i % 4 == 0) {
            return 10;
        }
        else if (i % 4 == 1) {
            return 130;
        }
        else if (i % 4 == 2) {
            return 245;
        }
        else {
            return 345;
        }
    }
}