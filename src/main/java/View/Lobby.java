package View;

import Model.FunctionsGson.ScoreboardGson;
import Model.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Lobby {
    public static URL createHostURL;
    public static URL hostsURL;
    public static URL scoreBoardFxmlURL;
    public static Member member;

    public Pane root;
    public Stage stage;
    public Scene scene;

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(LoginMenu.mainMenuFxmlURL);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void createHostClicked(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(createHostURL);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void hostsClicked(MouseEvent mouseEvent) throws IOException, InterruptedException {
        Hosts.run(mouseEvent);
    }

    public void friendListClicked(MouseEvent mouseEvent) throws IOException {
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        FriendsList.stage = stage;
        new FriendsList().run();
    }

    public void sendFriendRequest(MouseEvent mouseEvent) throws IOException {
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        SendFriendRequest.stage = stage;
        new SendFriendRequest().run();
    }

    public void showFriendRequestsList(MouseEvent mouseEvent) throws IOException {
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        FriendRequestsList.stage = stage;
        new FriendRequestsList().run();
    }

    public void switchToChatBox(MouseEvent mouseEvent) throws IOException {
        PreChatBox preChatBox = new PreChatBox();
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        preChatBox.stage = this.stage;
        preChatBox.run();
    }
}
