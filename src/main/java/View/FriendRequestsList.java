package View;

import Model.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FriendRequestsList {
    public static Stage stage;
    public Scene scene;
    public static URL lobbyURL;
    private Pane pane = new Pane();
    private Button back = new Button("back");
    private ArrayList<Button> accept = new ArrayList<>();
    private ArrayList<Button> deny = new ArrayList<>();
    private ArrayList<HBox> hBoxes = new ArrayList<>();
    private ArrayList<Label> usernamesLabel = new ArrayList<>();
    private VBox vBox = new VBox(50);
    private VBox mainVBox = new VBox(50);
    private ScrollPane container = new ScrollPane();

    public void run(){
        vBox.setAlignment(Pos.CENTER);
        String request = "friend requests list";
        String response = "";
        try {
            CreateHost.dataOutputStream.writeUTF(request);
            CreateHost.dataOutputStream.flush();
            response = CreateHost.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] usernames = response.split("\n");
        for (int i = 0; i < usernames.length; i++) {
            usernamesLabel.add(new Label(usernames[i]));
            accept.add(new Button("accept"));
            deny.add(new Button("deny"));
            accept.get(i).setStyle("-fx-pref-width: 200;\n" +
                    "    -fx-pref-height: 20;\n" +
                    "    -fx-effect: dropshadow( one-pass-box , #d54444, 8 , 0.0 , 2 , 0 );\n" +
                    "    -fx-font-style: italic;\n" +
                    "    -fx-background-color:\n" +
                    "            linear-gradient(from 0% 93% to 0% 100%, rgba(115, 42, 213, 0.83) 0%, #326dda 100%),\n" +
                    "            #1a9bda,\n" +
                    "            #31a3e0,\n" +
                    "            radial-gradient(center 50% 50%, radius 100%, rgba(255, 84, 84, 0.7), #f11111);");
            deny.get(i).setStyle("-fx-pref-width: 200;\n" +
                    "    -fx-pref-height: 20;\n" +
                    "    -fx-effect: dropshadow( one-pass-box , #d54444, 8 , 0.0 , 2 , 0 );\n" +
                    "    -fx-font-style: italic;\n" +
                    "    -fx-background-color:\n" +
                    "            linear-gradient(from 0% 93% to 0% 100%, rgba(115, 42, 213, 0.83) 0%, #326dda 100%),\n" +
                    "            #1a9bda,\n" +
                    "            #31a3e0,\n" +
                    "            radial-gradient(center 50% 50%, radius 100%, rgba(255, 84, 84, 0.7), #f11111);");
            int finalI = i;
            accept.get(i).setOnMouseClicked(event -> {
                try {
                    CreateHost.dataOutputStream.writeUTF("accept friendRequest " + usernames[finalI]);
                    CreateHost.dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                accept = new ArrayList<>();
                deny = new ArrayList<>();
                hBoxes = new ArrayList<>();
                usernamesLabel = new ArrayList<>();
                this.run();
            });
            deny.get(i).setOnMouseClicked(event -> {
                try {
                    CreateHost.dataOutputStream.writeUTF("deny friendRequest " + usernames[finalI]);
                    CreateHost.dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                accept = new ArrayList<>();
                deny = new ArrayList<>();
                hBoxes = new ArrayList<>();
                usernamesLabel = new ArrayList<>();
                this.run();
            });
            hBoxes.add(new HBox(50));
            hBoxes.get(i).getChildren().add(usernamesLabel.get(i));
            hBoxes.get(i).getChildren().add(accept.get(i));
            hBoxes.get(i).getChildren().add(deny.get(i));
            vBox.getChildren().add(hBoxes.get(i));
        }
        back.setStyle("-fx-pref-width: 200;\n" +
                "    -fx-pref-height: 20;\n" +
                "    -fx-effect: dropshadow( one-pass-box , #d54444, 8 , 0.0 , 2 , 0 );\n" +
                "    -fx-font-style: italic;\n" +
                "    -fx-background-color:\n" +
                "            linear-gradient(from 0% 93% to 0% 100%, rgba(115, 42, 213, 0.83) 0%, #326dda 100%),\n" +
                "            #1a9bda,\n" +
                "            #31a3e0,\n" +
                "            radial-gradient(center 50% 50%, radius 100%, rgba(255, 84, 84, 0.7), #f11111);");
        back.setOnMouseClicked(event -> {
            try {
                pane = FXMLLoader.load(lobbyURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        });
        container.setPrefSize(1280, 600);
        container.setContent(vBox);
        mainVBox.getChildren().add(back);
        mainVBox.getChildren().add(vBox);
        pane.getChildren().add(mainVBox);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
