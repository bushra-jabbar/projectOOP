package com.e;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionSystem extends Application {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private static final List<User> users = new ArrayList<>();
    private static final List<String> votedUsers = new ArrayList<>();
    private static final Map<String, String> partySlogans = new HashMap<>(); // Store party slogans

    // Sample parties with their names and passwords
    private static final String PARTY1_NAME = "Party1";
    private static final String PARTY1_PASSWORD = "party1pass";
    private static final String PARTY2_NAME = "Party2";
    private static final String PARTY2_PASSWORD = "party2pass";
    private static final String PARTY3_NAME = "Party3";
    private static final String PARTY3_PASSWORD = "party3pass";

    private void showLogin(UserType userType) {
        Stage loginStage = new Stage();
        loginStage.setTitle(userType + " Login");

        // Create components for login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Add components to layout
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

        // Handle login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Implement a simple login validation logic
            if (isValidLogin(username, password, userType)) {
                // Display a success message (for demonstration purposes)
                showAlert("Login Successful", "Welcome, " + userType + "!");
                // Close the login stage after successful login
                loginStage.close();
            } else {
                // Display an error message for invalid login
                showAlert("Invalid Login", "Invalid username or password. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private boolean isValidLogin(String username, String password, UserType userType) {
        // Basic validation logic for demonstration
        if (userType == UserType.ADMIN) {
            return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
        } else {
            for (User user : users) {
                if (user.getUsername().equals(username)
                        && user.getPassword().equals(password)
                        && user.getUserType() == userType) {
                    return true;
                }
            }
        }
        return false;
    }

    private void showPartyCandidateLogin() {
        Stage partyCandidateLoginStage = new Stage();
        partyCandidateLoginStage.setTitle("Party Candidate Login");

        // Create components for party candidate login
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        Label partyNameLabel = new Label("Party Name:");
        TextField partyNameField = new TextField();
        Label partyPasswordLabel = new Label("Party Password:");
        PasswordField partyPasswordField = new PasswordField();
        Button loginButton = new Button("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(ageLabel, 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(partyNameLabel, 0, 2);
        grid.add(partyNameField, 1, 2);
        grid.add(partyPasswordLabel, 0, 3);
        grid.add(partyPasswordField, 1, 3);
        grid.add(loginButton, 1, 5);

        loginButton.setOnAction(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String partyName = partyNameField.getText();
            String partyPassword = partyPasswordField.getText();
            String slogan = partySlogans.getOrDefault(partyName, "Default Slogan"); // Retrieve slogan or use default

            // Check age requirement
            if (age < 21) {
                showAlert("Invalid Age", "Candidate must be 21 or older.");
                return;
            }

            // Check party login validation
            if (isValidPartyLogin(name, age, partyName, partyPassword, slogan)) {
                // Open party candidate dashboard or perform further actions
                showCandidateDashboard(name, partyName);
                partyCandidateLoginStage.close();
            }
//            else {
//                // Display invalid login message
//                showAlert("Invalid Login", "Invalid candidate name, age, party name, or party password. Please try again.");
//            }
        });

        Scene scene = new Scene(grid, 400, 300);
        partyCandidateLoginStage.setScene(scene);
        partyCandidateLoginStage.show();
    }

    private void showCandidateDashboard(String candidateName, String partyName) {
        Stage candidateDashboardStage = new Stage();
        candidateDashboardStage.setTitle("Candidate Dashboard");

        // Create components for candidate dashboard
        Label welcomeLabel = new Label("Welcome, " + candidateName + "!");
        Label partyLabel = new Label("Party: " + partyName);
        String slogan = partySlogans.getOrDefault(partyName, "Default Slogan"); // Retrieve slogan or use default
        Label sloganLabel = new Label("Party Slogan: " + slogan);
        Button editSloganButton = new Button("Edit Slogan");
        Label votesLabel = new Label("Current Votes Gained: 0"); // TODO: Implement vote counting
        Label probabilityLabel = new Label("Probability of winning is 50%");
        Button backButton = new Button("Back");

        // Add complain button
        Button complainButton = new Button("Complain");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Set up the Candidate Dashboard UI
        grid.add(welcomeLabel, 0, 0);
        grid.add(partyLabel, 0, 1);
        grid.add(sloganLabel, 0, 2);
        grid.add(votesLabel, 0, 3);
        grid.add(probabilityLabel, 0, 4);
        grid.add(backButton, 0, 5);
        grid.add(editSloganButton, 1, 5);
        grid.add(complainButton, 2, 5); // Add complain button to the grid

        // Handle the back button action
        backButton.setOnAction(e -> {
            // Implement any actions needed before closing the dashboard
            candidateDashboardStage.close();
        });

        // Handle edit slogan button action
        editSloganButton.setOnAction(e -> {
            // Implement code to allow editing the party slogan
            // For simplicity, you can use a text field in a dialog for editing
            TextInputDialog dialog = new TextInputDialog(slogan);
            dialog.setTitle("Edit Party Slogan");
            dialog.setHeaderText("Edit your party slogan:");
            dialog.setContentText("Slogan:");

            // Get the new slogan
            dialog.showAndWait().ifPresent(newSlogan -> {
                partySlogans.put(partyName, newSlogan);
                sloganLabel.setText("Party Slogan: " + newSlogan);
            });
        });

        // Handle complain button action
        complainButton.setOnAction(e -> {
            // Implement code to handle complaints
            TextArea complainTextArea = new TextArea();
            complainTextArea.setPromptText("Enter your complaint here");

            // Create a dialog for entering complaints
            Dialog<String> complainDialog = new Dialog<>();
            complainDialog.setTitle("Complain");
            complainDialog.setHeaderText("Enter your complaint:");
            complainDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            complainDialog.getDialogPane().setContent(complainTextArea);

            // Handle the result of the dialog
            complainDialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    String complaint = complainTextArea.getText();
                    // Store the complaint (For simplicity, you can print it here)
                    System.out.println("Complaint: " + complaint);
                    return "Complaint recorded successfully";
                }
                return null;
            });

            // Show the dialog
            complainDialog.showAndWait().ifPresent(result -> {
                // Display a message indicating that the complaint has been recorded successfully
                showAlert("Complaint Recorded", result);
            });
        });

        Scene scene = new Scene(grid, 400, 300);
        candidateDashboardStage.setScene(scene);
        candidateDashboardStage.show();
    }

    private boolean isValidPartyLogin(String name, int age, String partyName, String partyPassword, String slogan) {
        // Check party login validation logic here
        // For simplicity, check against predefined party names and passwords
        if ((partyName.equals(PARTY1_NAME) && partyPassword.equals(PARTY1_PASSWORD)) ||
                (partyName.equals(PARTY2_NAME) && partyPassword.equals(PARTY2_PASSWORD)) ||
                (partyName.equals(PARTY3_NAME) && partyPassword.equals(PARTY3_PASSWORD))) {
            partySlogans.put(partyName, slogan); // Store the slogan
            return true;
        } else {
            showAlert("Invalid Party Login", "Invalid party name or password. Please try again.");
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("E-election System");

        GridPane grid = new GridPane();
        Text headingText = new Text("E Voting System");
        headingText.setFont(Font.font("Times new roman", 20));
        grid.add(headingText, 0, 0); // Added heading to the center column

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(23);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);

        users.add(new User("voter1", "voter123", UserType.VOTER));
        users.add(new User("partyCandidate1", "party123", UserType.PARTY_CANDIDATE));
        users.add(new User("admin", "admin123", UserType.ADMIN));

        // Voter login button
        Button voterLoginButton = new Button("Voter Login");
        grid.add(voterLoginButton, 1, 0);
        voterLoginButton.setOnAction(e -> showLogin(UserType.VOTER));

        // Party Candidate login button
        Button partyCandidateLoginButton = new Button("Party Candidate Login");
        grid.add(partyCandidateLoginButton, 2, 0);
        partyCandidateLoginButton.setOnAction(e -> showPartyCandidateLogin());

        // Admin login button
        Button adminLoginButton = new Button("Admin Login");
        grid.add(adminLoginButton, 3, 0);
        adminLoginButton.setOnAction(e -> showLogin(UserType.ADMIN));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

enum UserType {
    VOTER, PARTY_CANDIDATE, ADMIN
}

class User {
    private final String username;
    private final String password;
    private final UserType userType;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
