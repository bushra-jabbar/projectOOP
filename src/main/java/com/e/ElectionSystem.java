package com.e;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ElectionSystem extends Application {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";


    private static final List<User> users = new ArrayList<>();
    private static final List<String> votedUsers = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("E-election System");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);

        users.add(new User("voter1", "voter123", UserType.VOTER));
        users.add(new User("partyCandidate1", "party123", UserType.PARTY_CANDIDATE));
        users.add(new User("admin", "admin123", UserType.ADMIN));


        Button voterLoginButton = new Button("Voter Login");
        grid.add(voterLoginButton, 0, 0);
        voterLoginButton.setOnAction(e -> showLogin(UserType.VOTER));


        Button partyCandidateLoginButton = new Button("Party Candidate Login");
        grid.add(partyCandidateLoginButton, 1, 0);
        partyCandidateLoginButton.setOnAction(e -> showLogin(UserType.PARTY_CANDIDATE));

        Button adminLoginButton = new Button("Admin Login");
        grid.add(adminLoginButton, 2, 0);
        adminLoginButton.setOnAction(e -> showLogin(UserType.ADMIN));

        primaryStage.show();
    }

    private void showLogin(UserType userType) {
        Stage loginStage = new Stage();
        loginStage.setTitle(userType + " Login");


        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);


        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();


            if (isValidLogin(username, password, userType)) {
                if (userType == UserType.VOTER && !votedUsers.contains(username)) {
                    // Open voter dashboard or perform further actions
                    System.out.println(userType + " login successful");
                    votedUsers.add(username);
                    loginStage.close();
                } else if (userType != UserType.VOTER) {
                    // Open party candidate or admin dashboard
                    System.out.println(userType + " login successful");
                    loginStage.close();
                } else {
                    showAlert("Invalid Login", userType + " has already voted.");
                }
            } else {
                // Display invalid login message
                // it displays the at second showAlert message in which override is written
                // first
                showAlert("Invalid Login", "Invalid username or password. Please try again.");
               // second
//                 showAlert(" override ", " override username or password. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private boolean isValidLogin(String username, String password, UserType userType) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getUserType() == userType) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
//        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
//        alert.show();
    }

}