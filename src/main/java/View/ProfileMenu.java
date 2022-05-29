package View;

import Controller.ProfileMenuController;
import Model.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu {
    public static Member loggedInMember;
    public static URL mainMenuFxml;
    public static URL profileMenuFxml;
    public static URL changeNicknameFxml;
    public static URL changePasswordFxml;
    public static URL changeProfilePicFxml;
    public static URL firstPic;
    public static URL secondPic;
    public static URL thirdPic;
    public static URL forthPic;
    public Stage stage;
    public Scene scene;
    public Parent root;
    public TextField nicknameTF;
    public TextField passwordTF;
    public Label passwordLabel;
    public Label nicknameLabel;

    private Matcher getMatcher(String command, String regex) {
        return Pattern.compile(regex).matcher(command);
    }

    public void changeProfilePicture(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(changeProfilePicFxml);
//        VBox vbox = (VBox) root.getChildrenUnmodifiable().get(0);
//        Rectangle first = new Rectangle();
//        Rectangle second = new Rectangle();
//        Rectangle third = new Rectangle();
//        Rectangle forth = new Rectangle();
//        first.setFill(new ImagePattern(new Image(firstPic.toExternalForm())));
//        second.setFill(new ImagePattern(new Image(secondPic.toExternalForm())));
//        third.setFill(new ImagePattern(new Image(thirdPic.toExternalForm())));
//        forth.setFill(new ImagePattern(new Image(forthPic.toExternalForm())));
//        HBox hbox1 = new HBox(50);
//        HBox hbox2 = new HBox(50);
//        hbox1.getChildren().add(first);
//        hbox1.getChildren().add(second);
//        hbox2.getChildren().add(third);
//        hbox2.getChildren().add(forth);
//        vbox.getChildren().add(hbox1);
//        vbox.getChildren().add(hbox2);
        scene = new Scene(root);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(changePasswordFxml);
        scene = new Scene(root);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changeNickname(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(changeNicknameFxml);
        scene = new Scene(root);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(mainMenuFxml);
        scene = new Scene(root);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    public void profileChangeNickname(MouseEvent mouseEvent) throws IOException {

        String newNickname = nicknameTF.getText();
        String nicknameRegex = "[^ \\t]+";

        if(!newNickname.matches(nicknameRegex)) {
            nicknameLabel.setText("invalid nickname");
            return;
        }
        if(newNickname.equals(loggedInMember.getNickname())) {
            nicknameLabel.setText("please enter a new nickname");
            return;
        }

        String username = loggedInMember.getUsername();

        boolean inUse = false;
        File file = new File("users.txt");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();
        String date = "", image = "";

        String fileRegex = "(?<username>.*) (?<nickname>.*) (?<password>.*) (?<score>\\d+) (?<image>\\d) (?<date>.+)";
        while (line != null && !line.equals("")) {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");
            String fileNickname = fileMatcher.group("nickname");
            date = fileMatcher.group("date");

            if(!Objects.equals(fileUsername, username)) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if(Objects.equals(fileNickname, newNickname)){
                inUse = true;
            }
            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(String.valueOf(stringBuilder));
        if(!inUse)
            bufferedWriter.write(loggedInMember.getUsername() + " " + newNickname + " " + loggedInMember.getPassword() + " " + loggedInMember.getScore() + " " + loggedInMember.getImageNumber() + " " + date);
        else
            bufferedWriter.write(loggedInMember.getUsername() + " " + loggedInMember.getNickname() + " " + loggedInMember.getPassword() + " " + loggedInMember.getScore() + " " + loggedInMember.getImageNumber() + " " + date);

        bufferedWriter.newLine();

        bufferedWriter.close();
        //
        if(inUse){
            nicknameLabel.setText("user with this nickname already exists!");
            return;
        }

        loggedInMember.setNickname(newNickname);
        switchToProfileMenu(mouseEvent);
    }

    public void profileChangePassword(MouseEvent mouseEvent) throws IOException {

        String newPassword = passwordTF.getText();
        String oldPassword = loggedInMember.getPassword();
        String date = "";

        String passwordRegex = "[^ \\t]+";
        if(!newPassword.matches(passwordRegex)) {
            passwordLabel.setText("invalid password type");
            return;
        }

        if(!Objects.equals(oldPassword, loggedInMember.getPassword())) {
            passwordLabel.setText("current password is invalid");
            return;
        }

        if(Objects.equals(oldPassword, loggedInMember.getPassword()) && Objects.equals(oldPassword, newPassword)) {
            passwordLabel.setText("please enter a new password");
            return;
        }

        String username = loggedInMember.getUsername();
        File file = new File("users.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();
        String fileRegex = "(?<username>.*) (?<nickname>.*) (?<password>.*) (?<score>\\d+) (?<image>\\d) (?<date>.+)";
        while (line != null && !line.equals("")) {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");
            date = fileMatcher.group("date");

            if(!Objects.equals(fileUsername, username)) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(String.valueOf(stringBuilder));
        bufferedWriter.write(loggedInMember.getUsername() + " " + loggedInMember.getNickname() + " " + newPassword + " " + loggedInMember.getScore() + " " + loggedInMember.getImageNumber() + " " + date);
        bufferedWriter.newLine();
        bufferedWriter.close();
        loggedInMember.setPassword(newPassword);
        switchToProfileMenu(mouseEvent);
    }

    public void changePass(MouseEvent mouseEvent) throws IOException {
        profileChangePassword(mouseEvent);
    }

    public void switchToProfileMenu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(profileMenuFxml);
        scene = new Scene(root);
        stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changeNick(MouseEvent mouseEvent) throws IOException {
        profileChangeNickname(mouseEvent);
    }


    public void changeProf(int imageNumber, MouseEvent mouseEvent) throws IOException {
        String username = loggedInMember.getUsername();
        File file = new File("users.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();
        String date = "";
        String fileRegex = "(?<username>.*) (?<nickname>.*) (?<password>.*) (?<score>\\d+) (?<image>\\d) (?<date>.+)";
        while (line != null && !line.equals("")) {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");
            date = fileMatcher.group("date");

            if(!Objects.equals(fileUsername, username)) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(String.valueOf(stringBuilder));
        bufferedWriter.write(loggedInMember.getUsername() + " " + loggedInMember.getNickname() + " " + loggedInMember.getPassword() + " " + loggedInMember.getScore() + " " + imageNumber + " " + date);
        bufferedWriter.newLine();
        bufferedWriter.close();
        switchToProfileMenu(mouseEvent);
    }

    public void setFirstPic(MouseEvent mouseEvent) throws IOException {
        loggedInMember.setImageNumber(0);
        changeProf(0, mouseEvent);
    }

    public void setSecondPic(MouseEvent mouseEvent) throws IOException {
        loggedInMember.setImageNumber(1);
        changeProf(1, mouseEvent);
    }

    public void setThirdPic(MouseEvent mouseEvent) throws IOException {
        loggedInMember.setImageNumber(2);
        changeProf(2, mouseEvent);
    }

    public void setForthPic(MouseEvent mouseEvent) throws IOException {
        loggedInMember.setImageNumber(3);
        changeProf(3, mouseEvent);
    }

//    public ProfileMenu(Member loggedInMember){
//        this.loggedInMember = loggedInMember;
//    }
    /*
    public void run(Scanner scan) throws IOException {
        System.out.println("entered Profile Menu!");

        ProfileMenuController profileMenuController = new ProfileMenuController(loggedInMember);

        String changeNicknameRegex1 = "(profile change --nickname )(?<nickname>.*)";
        String changeNicknameRegex2 = "(profile change -n )(?<nickname>.*)";
        String changePasswordRegex1 = "(profile change --password --current )(?<oldPassword>.*) --new (?<newPassword>.*)";
        String changePasswordRegex2 = "(profile change --password --new )(?<newPassword>.*) --current (?<oldPassword>.*)";
        String changePasswordRegex3 = "(profile change --password -cp )(?<oldPassword>.*) -np (?<newPassword>.*)";
        String menuShowCurrentRegex = "menu show-current";

        String command;
        command = scan.nextLine();

        while(!Objects.equals(command, "menu exit")){
            if(command.matches(changeNicknameRegex1) || command.matches(changeNicknameRegex2)){
                if(command.matches(changeNicknameRegex1))
                    System.out.println(profileMenuController.profileChangeNickname(command, changeNicknameRegex1));
                else if(command.matches(changeNicknameRegex2))
                    System.out.println(profileMenuController.profileChangeNickname(command, changeNicknameRegex2));
            }
            else if(command.matches(changePasswordRegex1) || command.matches(changePasswordRegex2) || command.matches(changePasswordRegex3)){
                if(command.matches(changePasswordRegex1))
                    System.out.println(profileMenuController.profileChangePassword(command, changePasswordRegex1));
                else if(command.matches(changePasswordRegex2))
                    System.out.println(profileMenuController.profileChangePassword(command, changePasswordRegex2));
                else if(command.matches(changePasswordRegex3))
                    System.out.println(profileMenuController.profileChangePassword(command, changePasswordRegex3));
            }
            else if(command.matches(menuShowCurrentRegex))
                System.out.println("Profile Menu");
            else
                System.out.println("invalid command");

            command = scan.nextLine();
        }
    }
     */
}