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

    private String[] itemList;

    public ShopSellController(LoopManiaWorld world) {
        this.world = world;
        itemList = setItemList();
    }

    @FXML
    public void initialize() {
        addItems(itemList);
        for (ColumnConstraints columnConstraint: shopItems.getColumnConstraints()) {
            columnConstraint.setPercentWidth(25);
        }
        for (RowConstraints rowConstraint: shopItems.getRowConstraints()) {
            rowConstraint.setPercentHeight(25);
        }
        addDoneButton();
    }

    private String[] setItemList() {
        ArrayList<String> itemNameArrayList = new ArrayList<String>();
        for (Item item: world.getItems()) {
            if (item instanceof Sword) {
                itemNameArrayList.add("sword");
            }
            else if (item instanceof Stake) {
                itemNameArrayList.add("stake");
            }
            else if (item instanceof Staff) {
                itemNameArrayList.add("staff");
            }
            else if (item instanceof Armour) {
                itemNameArrayList.add("armour");
            }
            else if (item instanceof Shield) {
                itemNameArrayList.add("shield");
            }
            else if (item instanceof Helmet) {
                itemNameArrayList.add("helmet");
            }
            else if (item instanceof HealthPotion) {
                itemNameArrayList.add("healthpotion");
            }
            else if (item instanceof TheOneRing) {
                itemNameArrayList.add("theonering");
            }
        }
        return itemNameArrayList.toArray(new String[itemNameArrayList.size()]);
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
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

    public void addItems(String[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            String itemName = itemList[i];
            ImageView view = new ImageView(new Image((new File(String.format("src/images/%s.png", itemName))).toURI().toString()));
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

            Button item = makeItemButton(itemName, i);

            GridPane gridPane = new GridPane();
            gridPane.add(goldView, 0, 0);
            gridPane.add(item, 1, 0);
            itemCosts.getChildren().add(gridPane);

            AnchorPane.setTopAnchor(gridPane, getTopAnchor(i));
            AnchorPane.setLeftAnchor(gridPane, getLeftAnchor(i));
        }
    }

    public Button makeItemButton(String itemName, int position) {
        int price = +2; // TODO: make this according to each item
        Button sellButton = new Button(Integer.toString(price));
        sellButton.setFont(Font.font ("Bauhaus 93", FontWeight.BOLD, 18));
        
        // TODO: link button to buying item
        sellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    world.removeUnequippedInventoryItemByCoordinates(position % 4, position / 4);
                    sellButton.setTextFill(Color.DARKRED);
                    sellButton.setDisable(true);
                    // TODO: give money
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