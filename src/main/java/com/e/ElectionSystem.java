package com.e;
import javafx.application.Application;
import javafx.css.Size;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionSystem extends Application {
    //list of polling stations
    private List<PollingStation> pollingStations = new ArrayList<>();
    private static final Map<String, String> partySlogans = new HashMap<>(); // Store party slogans

    private String findNearestPollingStation(User voter) {
        double minDistance = Double.MAX_VALUE;
        PollingStation nearestStation = null;

        for (PollingStation station : pollingStations) {
            double distance = calculateDistance(voter.getX(), voter.getY(), station.getX(), station.getY());

            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }

        if (nearestStation != null) {
            return nearestStation.getCenterName();
        } else {
            return "No Polling Station Found";
        }
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

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
//        ImageView logoImageView = createLogoImageView();
//        GridPane.setHalignment(logoImageView, HPos.RIGHT);
//        grid.add(logoImageView, 3, 0,2,1);

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
        voterSignUpButton.setOnAction(e -> showSignUp());
        voterSignUpButton.setFont(font);
        grid.add(voterSignUpButton, 1, 2);

        // Party Candidate login button
        Button partyCandidateLoginButton = createButton("Party Candidate Login");
        partyCandidateLoginButton.setFont(font);
        grid.add(partyCandidateLoginButton, 1, 3);
        partyCandidateLoginButton.setOnAction(e -> showPartyCandidateLogin());

        users.add(new User("voter1", "voter123", UserType.VOTER,"3520247623232","amna","0321",2,2));
        users.add(new User("partycandidate1", "party123", UserType.PARTY_CANDIDATE));
        users.add(new User("admin", "admin123", UserType.ADMIN));
        pollingStations.add(new PollingStation("Wapda Town", 1, 1));
        pollingStations.add(new PollingStation("Iqbal Town", 0, 1));
        pollingStations.add(new PollingStation("Johar Town", 1, 2));
        pollingStations.add(new PollingStation("Izmir Town", 0, 2));
        pollingStations.add(new PollingStation("Bahria Town", 1, 0));

        primaryStage.show();
    }
//
//    private ImageView createLogoImageView() {
//        try {
//            Image logoImage = new Image(new FileInputStream("C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\Screenshot 2023-11-24 193725.png"));
//            ImageView imageView = new ImageView(logoImage);
//            //Setting the position of the imagw
//            //setting the fit height and width of the image view
//            imageView.setFitHeight(50);
//            imageView.setFitWidth(50);
//
//            //Setting the preserve ratio of the image view
//            imageView.setPreserveRatio(true);
//            return imageView;
//        } catch (Exception e) {
//            System.err.println("Error loading the logo image: " + e.getMessage());
//            return new ImageView(); // Empty ImageView if image loading fails
//        }
//    }


    // Method to show "How to Register" information
    private void showHowToRegister() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Register");
        alert.setHeaderText(null);
        alert.setContentText("ELIGIBILITY TO BE A VOTER:\n\n"
                + "A person shall be entitled to be enrolled as a voter in an electoral area if he—\n\n"
                + "(a) is a citizen of Pakistan\n"
                + "(b) is not less than eighteen years of age\n"
                + "(c) possesses a National Identity Card issued by the National Database and Registration Authority");
        alert.showAndWait();
    }
    private  List<User> users = new ArrayList<>();
    private  List<String> votedUsers = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    private final String adminName = "admin";
    private final String adminPassword = "admin123";

    // Sample parties with their names and passwords
    private String Party1 = "PTI";
    private String Party1_pass = "123";
    private String Party2 = "PPP";
    private String Party2_pass = "456";
    private String Party3 = "PMLN";
    private String Party3_pass = "789";
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(150);
        button.setMinHeight(40);
        return button;
    }

    private User loggedInUser;
    // Declare loggedInUser

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
                loggedInUser = findUserByUsername(username); // Set loggedInUser here

                if (userType == UserType.VOTER && !votedUsers.contains(username)) {
                    System.out.println(userType + " login successful");
                    votedUsers.add(username);
                    loginStage.close();
                    showVoterOptions();
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
        findNearestPollingStationButton.setOnAction(e -> {
            String nearestStation = findNearestPollingStation(loggedInUser);
            showAlert("Nearest Polling Station", "The nearest polling station is: " + nearestStation);
        });
//        Button privacyButton = createButton("Privacy");
        Button castVoteButton = createButton("Cast Vote");
        accountButton.setOnAction(e -> showAccountInformation(loggedInUser));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(accountButton, 0, 0);
        grid.add(findNearestPollingStationButton, 0, 1);
        grid.add(castVoteButton, 0, 2); // Add the "Cast Vote" button


        Scene scene = new Scene(grid, 330, 220);
        optionsStage.setScene(scene);
        optionsStage.show();
    }

    private void showAccountInformation(User user) {
        Stage accountStage = new Stage();
        accountStage.setTitle("Account Information");

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username: " + user.getUsername());
        Label cnicLabel = new Label("CNIC: " + user.getCnic());
        Label nameLabel = new Label("Name: " + user.getName());
        Label contactLabel = new Label("Contact: " + user.getContact());
// Add x and y coordinates labels
        int xCoordinate = voterIdCounter / 3;  // Adjust the divisor based on the number of columns
        int yCoordinate = voterIdCounter % 3;  // Adjust the divisor based on the number of columns
        Label xCoordinateLabel = new Label("X Coordinate: " + xCoordinate);
        Label yCoordinateLabel = new Label("Y Coordinate: " + yCoordinate);

        vBox.getChildren().addAll(usernameLabel, cnicLabel, nameLabel, contactLabel, xCoordinateLabel, yCoordinateLabel);

        Scene scene = new Scene(vBox, 300, 200);
        accountStage.setScene(scene);
        accountStage.show();
    }
    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }
    private void showSignUp() {
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Voter Sign Up");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signUpButton = new Button("Sign Up");

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
                // Show additional signup confirmation dialog
                showSignUpConfirmationDialog(username, password);
                signUpStage.close();
            } else {
                showAlert("Invalid Sign Up", "Username already exists. Please choose another username.");
            }
        });

        Scene scene = new Scene(grid, 350, 300);
        signUpStage.setScene(scene);
        signUpStage.show();
    }

    private ToggleGroup ageGroup;  // Defined ToggleGroup
    private ToggleGroup citizenshipGroup;  // Defined ToggleGroup
//    private ToggleGroup idCardGroup;  // Defined ToggleGroup
    private TextField idCardTextField;  // Defined TextField
    private TextField name;
    private TextField contact;
    private int voterIdCounter = 1;  // Counter to generate unique voter IDs

    private void showSignUpConfirmationDialog(String username, String password) {
        Stage confirmationStage = new Stage();
        confirmationStage.setTitle("Sign Up Confirmation");

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(25, 25, 25, 25));

        ageGroup = new ToggleGroup();
        citizenshipGroup = new ToggleGroup();
//        idCardGroup = new ToggleGroup();
        idCardTextField = new TextField();

        name = new TextField();
        contact = new TextField();
        TextField voterId = new TextField("#" + String.format("%03d", voterIdCounter));  // Generate voter ID
        // Generate unique x and y coordinates based on the voterIdCounter
        int xCoordinate = voterIdCounter / 3;  // Adjust the divisor based on the number of columns
        int yCoordinate = voterIdCounter % 3;  // Adjust the divisor based on the number of columns

        vBox.getChildren().addAll(
                createConfirmationSection("Age is 18 or above?", ageGroup),
                createConfirmationSection("Citizenship: Are you Pakistani or not?", citizenshipGroup),
                new Label("Enter National ID Card (CNIC):"),
                idCardTextField,
                new Label("Enter Name:"),
                name,
                new Label("Enter Contact:"),
                contact,
                new Label("Voter ID:"),
                voterId,
                new Label("X Coordinate: " + xCoordinate),
                new Label("Y Coordinate: " + yCoordinate),

                createFinalSignUpButton(username, password, confirmationStage)
        );
//
//        Button signUpButton = new Button("Create Account");
//        signUpButton.setOnAction(event -> {
//            if (isConfirmationValid()) {
//                confirmationStage.close();
//                // Add the CNIC, name, and contact to the user object
//                users.add(new User(username, password, UserType.VOTER, idCardTextField.getText(), name.getText(), contact.getText()));
//
//                showAlert("Sign Up Successful", "Voter account created successfully: " + username);
//            } else {
//                showAlert("Can't create account for you", "You must fulfill all requirements.");
//            }
//        });


        Scene dialogScene = new Scene(vBox, 400, 560);
        confirmationStage.setScene(dialogScene);
        confirmationStage.showAndWait();

        // Increment the counter for the next user
        voterIdCounter++;
    }

    private VBox createConfirmationSection(String title, ToggleGroup toggleGroup) {
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        RadioButton yesRadioButton = new RadioButton("Yes");
        RadioButton noRadioButton = new RadioButton("No");

        yesRadioButton.setToggleGroup(toggleGroup);
        noRadioButton.setToggleGroup(toggleGroup);

        vBox.getChildren().addAll(titleLabel, yesRadioButton, noRadioButton);

        return vBox;
    }

    private String isConfirmationValid() {
        // Check if age and citizenship are confirmed as "Yes"
        boolean isAgeConfirmed = isToggleGroupSelected(ageGroup) && ((RadioButton) ageGroup.getSelectedToggle()).getText().equals("Yes");
        boolean isCitizenshipConfirmed = isToggleGroupSelected(citizenshipGroup) && ((RadioButton) citizenshipGroup.getSelectedToggle()).getText().equals("Yes");

        // Check if all fields are filled
        boolean isNameFilled = !name.getText().isEmpty();
        boolean isContactFilled = !contact.getText().isEmpty();
        boolean isIdCardFilled = !idCardTextField.getText().isEmpty();

        // Check if age and citizenship are confirmed as "Yes", and all fields are filled
        if (isAgeConfirmed && isCitizenshipConfirmed && isNameFilled && isContactFilled && isIdCardFilled) {
            return null; // Validation successful, return null
        } else {
            // Build an error message based on the failed conditions
            String errorMessage = "Please correct the following issues:\n";
            if (!isAgeConfirmed) {
                errorMessage += "- Age must be confirmed as 'Yes'.\n";
            }
            else if (!isCitizenshipConfirmed) {
                errorMessage += "- Citizenship must be confirmed as 'Yes'.\n";
            }
            else if (!isNameFilled) {
                errorMessage += "- Name cannot be empty.\n";
            }
            else if (!isContactFilled) {
                errorMessage += "- Contact cannot be empty.\n";
            }
            else if (!isIdCardFilled) {
                errorMessage += "- ID Card cannot be empty.\n";
            }

            return errorMessage; // Return the error message
        }
    }

    private boolean isToggleGroupSelected(ToggleGroup toggleGroup) {
        return toggleGroup.getSelectedToggle() != null;
    }
    private Button createFinalSignUpButton(String username, String password, Stage confirmationStage) {
        Button signUpButton = new Button("Create Account");
        signUpButton.setOnAction(event -> {
            String validationMessage = isConfirmationValid();
            if (validationMessage == null) {

                // Check CNIC uniqueness for voters
                if (!isUniqueCnic(idCardTextField.getText())) {
                    showAlert1("This CNIC is already associated with another voter. Please use a unique CNIC.");
                    return;
                }

                // Add the CNIC to the list if the signup is successful
                voterCnics.add(idCardTextField.getText());

                users.add(new User(username, password, UserType.VOTER, idCardTextField.getText(), name.getText(), contact.getText()));
                showAlert("Sign Up Successful", "Voter account created successfully: " + username);
            } else {
                showAlert("Can't create account for you", validationMessage);
            }
        });
        return signUpButton;
    }

    private List<String> voterCnics = new ArrayList<>();
    private boolean isUniqueCnic(String cnic) {
        return !voterCnics.contains(cnic);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert1(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("This CNIC is already associated with another voter. Please use a unique CNIC.");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            if (isValidPartyLogin(name, age, partyName, partyPassword,slogan)) {
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
        Scene scene = new Scene(grid, 300, 250);
        candidateDashboardStage.setScene(scene);
        candidateDashboardStage.show();
    }


    private boolean isValidPartyLogin(String name, int age, String partyName, String partyPassword, String slogan) {
        // Check party login validation logic here
        // For simplicity, check against predefined party names and passwords
        if ((partyName.equals(Party1) && partyPassword.equals(Party1_pass)) ||
                (partyName.equals(Party2) && partyPassword.equals(Party2_pass)) ||
                (partyName.equals(Party3) && partyPassword.equals(Party3_pass))) {
            partySlogans.put(partyName, slogan); // Store the slogan
            return true;
        } else {
            showAlert("Invalid Party Login", "Invalid party name or password. Please try again.");
            return false;
        }
    }}