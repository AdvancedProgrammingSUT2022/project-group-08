package View;

import Model.*;
import Model.FunctionsGson.EndGameGson;
import Model.Units.Civilian;
import Model.Units.SaveGameClass;
import Model.Units.Unit;
import Model.Units.Warrior;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.graph.GraphAdapterBuilder;
import com.jsoniter.output.JsonStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoPanel {
    public static Stage stage;
    public static Scene gameMenuScene;
    public Scene infoPanelScene;
    public static URL infoPanelURL;
    public static ArrayList<Tile> tiles;
    public static ArrayList<Civilization> civilizations;
    public static Civilization currentCivilization;

    public static Text goldAmount;
    public static Text happinessAmount;

    public void start () throws IOException {
        BorderPane borderPane = FXMLLoader.load(infoPanelURL);
        infoPanelScene = new Scene(borderPane);
        stage.setScene(infoPanelScene);
        stage.show();
    }

    public void backClicked(MouseEvent mouseEvent) {
        goldAmount.setText(" : " + currentCivilization.getGold());
        happinessAmount.setText(" : " + currentCivilization.getHappiness());
        stage.setScene(gameMenuScene);
    }

    public void exampleClicked(MouseEvent mouseEvent) throws IOException {
        ExamplePage.infoPanelScene = infoPanelScene;
        ExamplePage.stage = stage;
        new ExamplePage().start();
    }

    public void unitPanelClicked(MouseEvent mouseEvent) throws IOException {
        UnitPanel.infoPanelScene = infoPanelScene;
        UnitPanel.stage = stage;
        UnitPanel.doesEnteredFromInfoPanel = true;
        UnitPanel.map = Tile.map;
        UnitPanel.playingCivilization = PlayGameMenu.playingCivilization;
        new UnitPanel().start();
    }

    public void victoryImprovementClicked(MouseEvent mouseEvent) throws IOException {
        VictoryImprovement.infoPanelScene = infoPanelScene;
        VictoryImprovement.stage = stage;
        new VictoryImprovement().start();
    }

    public void buildingsClicked(MouseEvent mouseEvent) throws IOException {
        BuildingsPage.infoPanelScene = infoPanelScene;
        BuildingsPage.stage = stage;
        new BuildingsPage().start();
    }

    public void cheatClicked(MouseEvent mouseEvent) throws IOException {
        CheatMenu.infoPanelScene = infoPanelScene;
        CheatMenu.stage = stage;
        CheatMenu.doesEnteredFromInfoPanel = true;
        new CheatMenu().start();
    }

    public void tradeClicked(MouseEvent mouseEvent) throws IOException {
        TradePage.infoPanelScene = infoPanelScene;
        TradePage.stage = stage;
        new TradePage().start();
    }

    public void generalUnitPanelClicked(MouseEvent mouseEvent) throws IOException {
        GeneralUnitPanel.infoPanelScene = infoPanelScene;
        GeneralUnitPanel.stage = stage;
        new GeneralUnitPanel().start();
    }

    public void cityPanelClicked(MouseEvent mouseEvent) throws IOException {
        CityPanel.infoPanelScene = infoPanelScene;
        CityPanel.stage = stage;
        new CityPanel().start();
    }

    public void diplomaticsClicked(MouseEvent mouseEvent) throws IOException {
        Diplomatics.infoPanelScene = infoPanelScene;
        Diplomatics.stage = stage;
        new Diplomatics().start();
    }

    public void demographicsClicked(MouseEvent mouseEvent) throws IOException {
        Demographics.infoPanelScene = infoPanelScene;
        Demographics.stage = stage;
        new Demographics().start();
    }

    public void economicalReviewClicked(MouseEvent mouseEvent) throws IOException {
        EconomicalReview.infoPanelScene = infoPanelScene;
        EconomicalReview.stage = stage;
        new EconomicalReview().start();
    }

    public void saveTheGame(MouseEvent mouseEvent) throws IOException {
        EndGameGson endGameGson = new EndGameGson();
        endGameGson.member = PlayGameMenu.playingCivilization.getMember();
        Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        String request = gson.toJson(endGameGson);
        CreateHost.dataOutputStream.writeUTF("saveGame " + request);
        CreateHost.dataOutputStream.flush();

        Parent root = FXMLLoader.load(LoginMenu.mainMenuFxmlURL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void unitsInfo(MouseEvent mouseEvent) {
        UnitInformationPanel.playingCivilization = currentCivilization;
        UnitInformationPanel.stage = stage;
        new UnitInformationPanel().initializePanelTree();
    }

    public void autoSave(MouseEvent mouseEvent) throws IOException {
        EndGameGson endGameGson = new EndGameGson();
        endGameGson.member = PlayGameMenu.playingCivilization.getMember();
        Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        String request = gson.toJson(endGameGson);
        CreateHost.dataOutputStream.writeUTF("autoSave " + request);
        CreateHost.dataOutputStream.flush();

        Parent root = FXMLLoader.load(LoginMenu.mainMenuFxmlURL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
