package com.e;


import javafx.application.Application;
import javafx.css.Size;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ElectionSystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("E-election System");
//        StackPane stackPane = new StackPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(20);
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        grid.setAlignment(Pos.CENTER_RIGHT);
        BackgroundSize backgroundSize = new BackgroundSize(700, 200, true,true,true,true);

        Image backgroundImage = new Image("file:C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\Screenshot 2023-11-24 193725.png");
        BackgroundImage background ;
                background= new BackgroundImage(backgroundImage, BackgroundRepeat.SPACE, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, backgroundSize);
//        // Blur effect
//        GaussianBlur blur = new GaussianBlur(5);  // You can adjust the blur radius
//        stackPane.setEffect(blur);

        //Setting the background image to the grid
        grid.setBackground(new Background(background));

        Text headingText = new Text("E Voting System ");
        // Add the logo
        ImageView logoImageView = createLogoImageView();
        GridPane.setHalignment(logoImageView, HPos.RIGHT);
        grid.add(logoImageView, 3, 0,2,1);

        headingText.setFont(Font.font("Times new roman", 25));
        GridPane.setHalignment(headingText, HPos.CENTER);
        grid.add(headingText, 0, 0, 3, 1);

        Button voterLoginButton = createButton("Voter Login");
        Font font = Font.font("Times New Roman", 14);
        voterLoginButton.setFont(font);
        voterLoginButton.setOnAction(e -> showLogin(UserType.VOTER));
        grid.add(voterLoginButton, 1, 1);

//        Button partyCandidateLoginButton = createButton("Party Candidate Login");
//        partyCandidateLoginButton.setOnAction(e -> showLogin(UserType.PARTY_CANDIDATE));
//        grid.add(partyCandidateLoginButton, 1, 3);

        Button adminLoginButton = createButton("Admin Login");
        adminLoginButton.setFont(font);
        adminLoginButton.setOnAction(e -> showLogin(UserType.ADMIN));
        grid.add(adminLoginButton, 1, 4);

        Button voterSignUpButton = createButton("Voter Sign Up");
        voterSignUpButton.setOnAction(e -> showSignUp(UserType.VOTER));
        voterSignUpButton.setFont(font);
        grid.add(voterSignUpButton, 1, 2);

        // Party Candidate login button
        Button partyCandidateLoginButton = createButton("Party Candidate Login");
        partyCandidateLoginButton.setFont(font);
        grid.add(partyCandidateLoginButton, 1, 3);
        partyCandidateLoginButton.setOnAction(e -> showPartyCandidateLogin());

        users.add(new User("voter1", "voter123", UserType.VOTER));
        users.add(new User("partycandidate1", "party123", UserType.PARTY_CANDIDATE));
        users.add(new User("admin", "admin123", UserType.ADMIN));

        primaryStage.show();
    }

    private ImageView createLogoImageView() {
        try {
            Image logoImage = new Image(new FileInputStream("C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\Screenshot 2023-11-24 193725.png"));
            ImageView imageView = new ImageView(logoImage);
            //Setting the position of the imagw
            //setting the fit height and width of the image view
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Error loading the logo image: " + e.getMessage());
            return new ImageView(); // Empty ImageView if image loading fails
        }
    }


    private static final List<User> users = new ArrayList<>();
    private static final List<String> votedUsers = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    // Sample parties with their names and passwords
    private String Party1 = "Party1";
    private String Party1_pass = "party1pass";
    private String Party2 = "Party2";
    private String Party2_pass = "party2pass";
    private String Party3 = "Party3";
    private String Party3_pass = "party3pass";
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(150);
        button.setMinHeight(40);
        return button;
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
                    System.out.println(userType + " login successful");
                    votedUsers.add(username);
                    loginStage.close();
                    showVoterOptions(); // Show additional options for the voter
                } else if (userType != UserType.VOTER) {
                    System.out.println(userType + " login successful");
                    loginStage.close();
                } else {
                    showAlert("Invalid Login", userType + " has already voted.");
                }
            } else {
                showAlert("Invalid Login", "Invalid username or password. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void showVoterOptions() {
        Stage optionsStage = new Stage();
        optionsStage.setTitle("Voter Options");

        Button accountButton = createButton("Account");
        Button findNearestPollingStationButton = createButton("Find Nearest \n Polling Station");
        Button privacyButton = createButton("Privacy");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(accountButton, 0, 0);
        grid.add(findNearestPollingStationButton, 0, 1);
        grid.add(privacyButton, 0, 2);

        Scene scene = new Scene(grid, 300, 200);
        optionsStage.setScene(scene);
        optionsStage.show();
    }

    private void showSignUp(UserType userType) {
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Voter Sign Up");
        // Create components for login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signUpButton = new Button("Sign Up");
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
        grid.add(signUpButton, 1, 2);

        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (isValidSignUp(username, password)) {
                users.add(new User(username, password, UserType.VOTER));
                showAlert("Voter account created successfully: " + username);
                signUpStage.close();
            } else {
                showAlert("Invalid Sign Up", "Username already exists. Please choose another username.");
            }
        });

        Scene scene = new Scene(grid, 350, 300);
        signUpStage.setScene(scene);
        signUpStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean isValidSignUp(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }
        return true; // Valid sign up
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
        alert.setTitle(null);
        alert.setContentText(message);
        //show(); overrides the alerts it first displays the 1st alert then overlaps it with the second alert
        alert.showAndWait();
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
        Label sloganLabel = new Label("Party Slogan:");
        TextField sloganTextField = new TextField("Editable Slogan");
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
        grid.add(sloganLabel, 0, 4);
        grid.add(sloganTextField, 1, 4);
        grid.add(loginButton, 1, 5);

        loginButton.setOnAction(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String partyName = partyNameField.getText();
            String partyPassword = partyPasswordField.getText();
            String slogan = sloganTextField.getText();

            // Check age requirement
            if (age < 21) {
                showAlert("Invalid Age", "Candidate must be 21 or older.");
                return;
            }

            // Check party login validation
            if (isValidPartyLogin(name, age, partyName, partyPassword)) {
                // Open party candidate dashboard or perform further actions
                showCandidateDashboard(name, partyName, slogan);
                partyCandidateLoginStage.close();
            } else {
                // Display invalid login message
                showAlert("Invalid Login", "Invalid candidate name, age, party name, or party password. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        partyCandidateLoginStage.setScene(scene);
        partyCandidateLoginStage.show();
    }
    private void showCandidateDashboard(String candidateName, String partyName, String initialSlogan) {
        Stage candidateDashboardStage = new Stage();
        candidateDashboardStage.setTitle("Candidate Dashboard");

        // Create components for candidate dashboard
        Label welcomeLabel = new Label("Welcome, " + candidateName + "!");
        Label partyLabel = new Label("Party: " + partyName);
        Label sloganLabel = new Label("Party Slogan:");
        TextField sloganTextField = new TextField(initialSlogan); // Use TextField for editable slogan
        Label votesLabel = new Label("Current Votes Gained: 0"); // TODO: Implement vote counting
        Label probabilityLabel = new Label("Probability of winning is 50%");
        Button saveSloganButton = new Button("Save Slogan"); // Button to save the edited slogan
        Button backButton = new Button("Back");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Set up the Candidate Dashboard UI
        grid.add(welcomeLabel, 0, 0);
        grid.add(partyLabel, 0, 1);
        grid.add(sloganLabel, 0, 2);
        grid.add(sloganTextField, 1, 2);
        grid.add(votesLabel, 0, 3);
        grid.add(probabilityLabel, 0, 4);
        grid.add(saveSloganButton, 1, 5);
        grid.add(backButton, 0, 5);

        // Handle the save slogan button action
        saveSloganButton.setOnAction(e -> {
            String editedSlogan = sloganTextField.getText();
            // Implement logic to save the edited slogan
            // For now, you can just print it
            System.out.println("Slogan saved: " + editedSlogan);
        });

        // Handle the back button action
        backButton.setOnAction(e -> {
            // Implement any actions needed before closing the dashboard
            candidateDashboardStage.close();
        });

        Scene scene = new Scene(grid, 400, 300);
        candidateDashboardStage.setScene(scene);
        candidateDashboardStage.show();
    }

    private boolean isValidPartyLogin(String name, int age, String partyName, String partyPassword) {
        // Check party login validation logic here
        // For simplicity, check against predefined party names and passwords
        return (partyName.equals(Party1) && partyPassword.equals(Party1_pass)) ||
                (partyName.equals(Party2) && partyPassword.equals(Party2_pass)) ||
                (partyName.equals(Party3) && partyPassword.equals(Party3_pass));
    }
}