package View;

import Controller.PlayGameMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitPanel {
    public static URL untPanelURL;
    public static Scene infoPanelScene;
    public static Stage stage;
    public Scene scene;
    public static boolean doesEnteredFromInfoPanel;
    public TextField moveDes;
    public TextField attackTileDes;
    public TextField attackCityDes;
    public TextField roadDes;
    public TextField railDes;
    public TextField removeRailDes;
    public TextField removeRoadDes;
    public TextField repairRoadDes;
    public TextField repairRailDes;


    public void start () throws IOException {
        BorderPane borderPane = FXMLLoader.load(untPanelURL);
        scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }


    public void back(MouseEvent mouseEvent) throws IOException {
        if (doesEnteredFromInfoPanel) new InfoPanel().start();
        else stage.setScene(infoPanelScene);
    }

    public void sleepUnit(MouseEvent mouseEvent) {
    }

    public void alertUnit(MouseEvent mouseEvent) {
    }

    public void fortifyUnit(MouseEvent mouseEvent) {
    }

    public void healUnit(MouseEvent mouseEvent) {
    }

    public void deployUnit(MouseEvent mouseEvent) {
    }

    public void rangeUnit(MouseEvent mouseEvent) {
    }

    public void wakeUpUnit(MouseEvent mouseEvent) {
    }

    public void recoverUnit(MouseEvent mouseEvent) {
    }

    public void deleteUnit(MouseEvent mouseEvent) {
    }

    public void moveUnit(MouseEvent mouseEvent) {
    }

    public void attackCityUnit(MouseEvent mouseEvent) {
    }

    public void attackTileUnit(MouseEvent mouseEvent) {
    }

    public void createUnit(MouseEvent mouseEvent) {
    }

    public void createRoadUnit(MouseEvent mouseEvent) {
    }

    public void createRailUnit(MouseEvent mouseEvent) {
    }

    public void removeRailUnit(MouseEvent mouseEvent) {
    }

    public void removeRoadUnit(MouseEvent mouseEvent) {
    }

    public void repairRoadUnit(MouseEvent mouseEvent) {
    }

    public void repairRailUnit(MouseEvent mouseEvent) {
    }
}