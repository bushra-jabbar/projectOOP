package com.e;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ElectionSystem extends Application {
    private ToggleGroup partyToggleGroup;

    //list of polling stations
    private List<PollingStation> pollingStations = new ArrayList<>();
    //method to calculate the nearest polling station to the voter
    private String findNearestPollingStation(User voter) {
        double nearDistance = Double.MAX_VALUE;
        PollingStation nearestStation = null;

        for (PollingStation station : pollingStations) {
            double distance = calculateDistance(voter.getX(), voter.getY(),
                    station.getX(), station.getY());
          if (distance < nearDistance) {
           nearDistance = distance;
           nearestStation = station; }
        }
        if (nearestStation != null) {
            return nearestStation.getCenterName();
        } else {
            return "No Polling Station Found";
        }
    }
// distance calculating method
    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
//mainscreen start stage
    @Override
    public void start(Stage mainscreen) {

        mainscreen.setTitle("E-election System");
//        StackPane stackPane = new StackPane();

        //alignments and layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(20);
        Scene scene = new Scene(grid, 500, 300);
        mainscreen.setScene(scene);
        grid.setAlignment(Pos.CENTER_RIGHT);
        BackgroundSize backgroundSize = new BackgroundSize(700, 200, true,true,true,true);

        //background image
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
        voterLoginButton.setBackground(Background.fill(Color.DARKBLUE));
        voterLoginButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        voterLoginButton.setOnAction(e -> showLogin(UserType.VOTER));
        grid.add(voterLoginButton, 1, 1);

//        Button partyCandidateLoginButton = createButton("Party Candidate Login");
//        partyCandidateLoginButton.setOnAction(e -> showLogin(UserType.PARTY_CANDIDATE));
//        grid.add(partyCandidateLoginButton, 1, 3);

        Button adminLoginButton = createButton("Admin Login");
        adminLoginButton.setFont(font);
        adminLoginButton.setBackground(Background.fill(Color.DARKBLUE));
        adminLoginButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        adminLoginButton.setOnAction(e -> showLogin(UserType.ADMIN));
        grid.add(adminLoginButton, 1, 4);

        Button voterSignUpButton = createButton("Voter Sign Up");
//        voterSignUpButton.setBackground(Background.fill(Color.DARKBLUE));
        voterSignUpButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        voterSignUpButton.setOnAction(e -> showSignUp());
        voterSignUpButton.setFont(font);
        grid.add(voterSignUpButton, 1, 2);

        // Party Candidate login button
        Button partyCandidateLoginButton = createButton("Party Candidate Login");
        partyCandidateLoginButton.setFont(font);
        grid.add(partyCandidateLoginButton, 1, 3);
        partyCandidateLoginButton.setBackground(Background.fill(Color.DARKBLUE));
        partyCandidateLoginButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        partyCandidateLoginButton.setOnAction(e -> showPartyCandidateLogin());


        // add voters in the users
        users.add(new User("voter1", "voter123", UserType.VOTER,"3520247623231","bushra","03232132311",2,2,004));
        users.add(new User("voter2", "voter123", UserType.VOTER,"3520247624232","areeha","03144224542",1,1,025));
        users.add(new User("voter3", "voter123", UserType.VOTER,"3520247654233","faiza","03144213242",2,0,066));
        users.add(new User("voter4", "voter123", UserType.VOTER,"3520247654234","ayesha","03234213242",3,2,013));
        users.add(new User("voter5", "voter123", UserType.VOTER,"3520247654235","anam","03214216542",4,2,012));
        users.add(new User("voter6", "voter123", UserType.VOTER,"3520247654236","amna","03265213212",4,1,023));
        users.add(new User("voter7", "voter123", UserType.VOTER,"3520247654237","azeema","03023643242",2,1,007));
        users.add(new User("voter8", "voter123", UserType.VOTER,"3520247654238","halima","03184213242",3,1,016));

        //        users.add(new User("partycandidate1", "party123", UserType.PARTY_CANDIDATE));
        //        users.add(new User("partycandidate2", "party123", UserType.PARTY_CANDIDATE));
        //        users.add(new User("partycandidate3", "party123", UserType.PARTY_CANDIDATE));

        // ADMIN (1)
        users.add(new User("admin", "admin123", UserType.ADMIN));

        // polling stations (6)
        pollingStations.add(new PollingStation("Wapda Town", 1, 1));
        pollingStations.add(new PollingStation("Iqbal Town", 2, 1));
        pollingStations.add(new PollingStation("Johar Town", 4, 2));
        pollingStations.add(new PollingStation("Izmir Town", 3, 2));
        pollingStations.add(new PollingStation("Bahria Town", 1, 0));

        // add parties to the parties list (3)
        parties.add(new Party(Party1,partySlogan1));
        parties.add(new Party(Party2,partySlogan2));
        parties.add(new Party(Party3,partySlogan3));

        mainscreen.show();
    }

    //
//    private ImageView createLogoImageView() {
    private static final Map<String, String> partySlogans = new HashMap<>();

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

    private void createDataFile() {
        StringBuilder dataLines = new StringBuilder();

        // Voters data
        dataLines.append("Voters:\n");
        for (User user : users) {
            if (user.getUserType() == UserType.VOTER) {
            dataLines.append(String.format("%s,%s,%s,%s,%s,%s\n",
            user.getUsername(), user.getPassword(), user.getUserType(),
          user.getCnic(), user.getName(), user.getContact())); }
        }
//
//        // Candidates data
//        dataLines.append("\nCandidates:\n");
//        for (User user : users) {
//            if (user.getUserType() == UserType.PARTY_CANDIDATE) {
//                dataLines.append(String.format("%s,%s,%s\n",
//                        user.getUsername(), user.getPassword(), user.getUserType()));
//            }
//        }

//         Admins data
        dataLines.append("\nAdmins:\n");
        for (User user : users) {
            if (user.getUserType() == UserType.ADMIN) {
             dataLines.append(String.format("%s,%s,%s\n",  user.getUsername(), user.getPassword(), user.getUserType()));
            }
        }

        // Parties data
        dataLines.append("\nParties:\n");
        for (Party party : parties) { dataLines.append(String.format("%s\n", party.getName()));
        }

        // Polling Stations data
        dataLines.append("\nPolling Stations:\n");
        for (PollingStation station : pollingStations) {
            dataLines.append(String.format("%s,%d,%d\n",
                    station.getCenterName(), station.getX(), station.getY()));
        }

     //Display the data in lines
        System.out.println(dataLines);

     //Now writing the data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("electionData.txt"))) {
            writer.write(dataLines.toString());
            showAlert("Data Saved", "Data has been saved to data.txt");
        } catch (IOException e) {
          e.printStackTrace();
          showAlert("Error", "An error occurred while saving data to the file.");
        }
    }


    //button omitted
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
    //users arraylist
    private  List<User> users = new ArrayList<>();
    private  List<String> votedUsers = new ArrayList<>();
    public static void main(String[] args) {
        launch();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(150);
        button.setMinHeight(40);
//        button.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        return button;
    }

    //Declare loggedInUser
    private User loggedInUser;
    private void showLogin(UserType userType) {

        Stage loginStage = new Stage();
        // background image
        Image backgroundImage = new Image("file:C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\hamid-roshaan-9Kj1C8iuX84-unsplash.jpg");

        // Create a background
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

//        Image i = new Image("file:C:\\Users\\Amnah\\IdeaProjects\\projectOOP1\\src\\main\\java\\com\\e\\hamid-roshaan-9Kj1C8iuX84-unsplash.jpg");
//        BackgroundImage i ;
//        i= new BackgroundImage(iIma, BackgroundRepeat.SPACE, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, backgroundSize);

        //attributes for the showlogin Portal for voter and admin usertypes
        //title
        loginStage.setTitle(userType + " Login");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        //Alignments of grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //add the labels and fields to grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        // Set the background
        grid.setBackground(new Background(background));
        //check for the voter and admin if it exists in the user list or not
        // if it does not exist display error message
        if (userType == UserType.VOTER) {
            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();

                //validity method called
                if (isValidLogin(username, password, userType)) {
                    loggedInUser = findUserByUsername(username); // Set loggedInUser here

                  if (userType == UserType.VOTER) {
                        System.out.println(userType + " login successful");
                        loginStage.close();
                        showVoterOptions();
                  } else {
                        System.out.println(userType + " login successful");
                        loginStage.close();
                  }
                } else {
                    showAlert("Invalid Login", "Invalid username or password. Please try again.");
                }
            });

            //for admin
             } else if (userType == UserType.ADMIN) {
            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (isValidLogin(username, password, userType)) {
                    loggedInUser = findUserByUsername(username); // Set loggedInUser here
                    System.out.println(userType + " login successful");
                    loginStage.close();
                    showAdminDashboard();
                } else {
                    showAlert("Invalid Login", "Invalid username or password. Please try again.");
                }
            });
        }
        Scene scene = new Scene(grid, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    //voterOption portal
    private void showVoterOptions() {

        Stage optionsStage = new Stage();
        optionsStage.setTitle("Voter Options");

        //buttons for the options for voter
        Button accountButton = createButton("Account");
        Button findNearestPollingStationButton = createButton("Find Nearest \n Polling Station");
        findNearestPollingStationButton.setOnAction(e -> {
            //call the method to findnearest polling station
            String nearestStation = findNearestPollingStation(loggedInUser);
            showAlert("Nearest Polling Station", "The nearest polling station is: " + nearestStation);
        });

//        Button privacyButton = createButton("Privacy");

        //castvote button
        Button castVoteButton = createButton("Cast Vote");
//        castVoteButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius:2px;");

        castVoteButton.setOnAction(e -> {
        //method call to check if the user has already voted or not
            if (!loggedInUser.hasVoted()) {
                //casting window
                showVoteCastingWindow();
            } }
        );

        //set actions for castvote and account button
        castVoteButton.setOnAction(e -> showVoteCastingWindow());

        accountButton.setOnAction(e -> showAccountInformation(loggedInUser));

        // Party selection section
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //add buttons to grid
        grid.add(accountButton, 0, 0);
        grid.add(findNearestPollingStationButton, 0, 1);
        grid.add(castVoteButton, 0, 2);

        Scene scene = new Scene(grid, 330, 220);
        optionsStage.setScene(scene);
        optionsStage.show();
    }

    //list of parties stored in arrayList
    private List<Party> parties = new ArrayList<>();

    /*radio buttons for selecting party*/
    private RadioButton ptiRadioButton;
    private RadioButton pppRadioButton;
    private RadioButton pmlnRadioButton;

    /*vote casting window*/
    private void showVoteCastingWindow() {
        Stage voteCastingStage = new Stage();

        //set title
        voteCastingStage.setTitle("Vote Casting");
        //vertical alignment
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        //radio buttons
        ptiRadioButton = new RadioButton("PTI");
        pppRadioButton = new RadioButton("PPP");
        pmlnRadioButton = new RadioButton("PMLN");

        //add radio buttons to togglegroup
        ToggleGroup voteToggleGroup = new ToggleGroup();
        ptiRadioButton.setToggleGroup(voteToggleGroup);
        pppRadioButton.setToggleGroup(voteToggleGroup);
        pmlnRadioButton.setToggleGroup(voteToggleGroup);

        // Create a button to submit the vote inside the casting window
        Button castVoteButtonInWindow = new Button("Cast Vote");
        //set action on it
        castVoteButtonInWindow.setOnAction(event -> {
        RadioButton selectedRadioButton = (RadioButton) voteToggleGroup.getSelectedToggle();
            if (selectedRadioButton != null) {
                String selectedParty = selectedRadioButton.getText();
                castVote(selectedParty);
                // increment the vote count for the selected party
//                showAlert("Casted", "You have successfully cast your vote for " + selectedParty);

                //Update the voted status for the logged-in user
                loggedInUser.setVoted(true);
                //Call the method to update the status of the logged-in voter
                updateVoterStatus(loggedInUser);

                //Disable radio buttons after voting
                ptiRadioButton.setDisable(true);
                pppRadioButton.setDisable(true);
                pmlnRadioButton.setDisable(true);
                //close the casting window
                voteCastingStage.close();
            } else {
                showAlert("No Party Selected", "Please select a party before casting your vote.");
            }
        });

        //add the radiobuttons and cast button to the vertical box
        vbox.getChildren().addAll(ptiRadioButton, pppRadioButton, pmlnRadioButton, castVoteButtonInWindow);
        //set scene height and width
        Scene scene = new Scene(vbox, 300, 200);
        voteCastingStage.setScene(scene);
        voteCastingStage.show();
    }

    /*set the status of the Voted voter to "casted" by
     setting the Voted attribute in voter class to true*/
    private void updateVoterStatus(User voter) {
        voter.setVoted(true);
    }

    // Modify the existing castVote method to increment the vote count for the selected party
//        // Implement logic to increment the vote count for the selected party
//        if ("PTI".equals(selectedParty)) {
//            // Increment PTI vote count
//            System.out.println("PTI Vote Casted");
//        } else if ("PPP".equals(selectedParty)) {
//            // Increment PPP vote count
//            System.out.println("PPP Vote Casted");
//        } else if ("PMLN".equals(selectedParty)) {
//            // Increment PMLN vote count
//            System.out.println("PMLN Vote Casted");
//        }

    //not used anywhere
    //method for updating result
    private void updateResults() {
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Election Results");

        VBox resultsVBox = new VBox(10);
        resultsVBox.setAlignment(Pos.CENTER);

        Label resultsLabel = new Label("Election Results:");

        // Display the results for each party
        for (Party party : parties) {
            Label partyResultLabel = new Label(party.getName() + ": " + party.getVoteCount() + " votes");
            resultsVBox.getChildren().add(partyResultLabel);
        }

        resultsVBox.getChildren().add(resultsLabel);

        Scene resultsScene = new Scene(resultsVBox, 300, 200);
        resultsStage.setScene(resultsScene);

        // Create a button to close the results window
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> resultsStage.close());
        resultsVBox.getChildren().add(closeButton);

        // Show the results window without blocking interactions with other windows
        resultsStage.show();
    }


    //Modify the existing castVote method to increment the vote count for the selected party
    private void castVote(String selectedParty) {
        //get the current logged-in user
        User loggedInUser = getCurrentLoggedInUser();

        if (loggedInUser != null) {
            if (!loggedInUser.hasVoted()) {
            //find the party the voter voted
                Party party = findPartyByName(selectedParty);

            //increment that party's vote count by +1
                if (party != null) {

           // Increment the vote count for the selected party
           party.incrementVoteCount();
           System.out.println(selectedParty + " Vote Casted");
                    // Update the user's vote status
                    loggedInUser.setVoted(true);
                }
                else { System.out.println("Error: Party not found");
                } }
            else {
                showAlert("Already Voted", "You have already cast your vote. Multiple votes are not allowed.");}}
                else {
                System.out.println("Error: Logged-in user not found");}}

    //not used
    private void showConfirmVoteDialog() {
        Stage confirmVoteStage = new Stage();
        confirmVoteStage.setTitle("Confirm Vote");

        // Get the selected party
        RadioButton selectedRadioButton = (RadioButton) partyToggleGroup.getSelectedToggle();
        String selectedParty = selectedRadioButton.getText();

        Label confirmationLabel = new Label("Are you sure you want to cast your vote for " + selectedParty + "?");

        Button confirmButton = createButton("Confirm");
        confirmButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");
        confirmButton.setOnAction(e -> {
            // Logic to record the vote for the selected party
            castVote(selectedParty);
            confirmVoteStage.close();
        });

        VBox confirmVoteVBox = new VBox(10);
        confirmVoteVBox.setAlignment(Pos.CENTER);
        confirmVoteVBox.getChildren().addAll(confirmationLabel, confirmButton);

        Scene confirmVoteScene = new Scene(confirmVoteVBox, 300, 150);
        confirmVoteStage.setScene(confirmVoteScene);
        confirmVoteStage.show();
    }

    //method to get logged-in user
    private User getCurrentLoggedInUser() {
        return loggedInUser;
    }
    //to find party by name
    private Party findPartyByName(String partyName) {
        for (Party party : parties) {
            if (party.getName().equals(partyName)) {
             return party;
         } } return null; }

    //method to view voters information
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
// Load the background image
        Image backgroundImage = new Image("file:C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\hamid-roshaan-9Kj1C8iuX84-unsplash.jpg");

        // Create a background with the loaded image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius:5px;");

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
        grid.setBackground(new Background(background)); // Set the background

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

        Scene scene = new Scene(grid, 300, 200);
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
                errorMessage += "-> Age must be 18 or above.\n";
            }
            else if (!isCitizenshipConfirmed) {
                errorMessage += "-> Citizenship must be Pakistani.\n";
            }
            else if (!isNameFilled) {
                errorMessage += "-> Name cannot be empty.\n";
            }
            else if (!isContactFilled) {
                errorMessage += "-> Contact cannot be empty.\n";
            }
            else if (!isIdCardFilled) {
                errorMessage += "-> ID Card cannot be empty.\n";
            }

            return errorMessage; // Return the error message
        }
    }

    private boolean isToggleGroupSelected(ToggleGroup toggleGroup) {
        return toggleGroup.getSelectedToggle() != null;
    }
    private Button createFinalSignUpButton(String username, String password, Stage confirmationStage) {
        Button signUpButton = new Button("Create Account");
        signUpButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius:5px;");
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
    // Sample parties with their names and passwords
    private String Party1 = "PTI";
    private String partySlogan1="Tabdeeli aye gi";
    private String Party1_pass = "123";

    private String Party2 = "PPP";
    private String partySlogan2="Bhutto zinda hai";

    private String Party2_pass = "456";
    private String Party3 = "PMLN";
    private String partySlogan3="Sher aya sher";
    private String Party3_pass = "789";

    private void showPartyCandidateLogin() {
        // Load the background image
        Image backgroundImage = new Image("file:C:\\Users\\Amnah\\IdeaProjects\\projectOOP\\src\\main\\java\\com\\e\\hamid-roshaan-9Kj1C8iuX84-unsplash.jpg");

        // Create a background with the loaded image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );


        Stage partyCandidateLoginStage = new Stage();
        partyCandidateLoginStage.setTitle("Party Login");
        // Create components for party candidate login
        Label nameLabel = new Label("Leader Name:");
        TextField nameField = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        Label partyNameLabel = new Label("Party Name:");
        TextField partyNameField = new TextField();
        Label partyPasswordLabel = new Label("Party Password:");
        PasswordField partyPasswordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");
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
        grid.setBackground(new Background(background)); // Set the background

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
        Scene scene = new Scene(grid, 300, 200);
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
        editSloganButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-radius:5px;");
        Button backButton = new Button("Back");

        backButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius:5px;");
        // Add complain button
//        Button complainButton = new Button("Complain");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        // Set up the Candidate Dashboard UI
        grid.add(welcomeLabel, 0, 0);
        grid.add(partyLabel, 0, 1);
        grid.add(sloganLabel, 0, 2);
//        grid.add(votesLabel, 0, 3);
//        grid.add(probabilityLabel, 0, 4);
        grid.add(backButton, 0, 5);
        grid.add(editSloganButton, 1, 5);
//        grid.add(complainButton, 2, 5); // Add complain button to the grid
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
//        complainButton.setOnAction(e -> {
        // Implement code to handle complaints
//            TextArea complainTextArea = new TextArea();
//            complainTextArea.setPromptText("Enter your complaint here");
//            // Create a dialog for entering complaints
//            Dialog<String> complainDialog = new Dialog<>();
//            complainDialog.setTitle("Complain");
//            complainDialog.setHeaderText("Enter your complaint:");
//            complainDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//            complainDialog.getDialogPane().setContent(complainTextArea);
//            // Handle the result of the dialog
//            complainDialog.setResultConverter(dialogButton -> {
//                if (dialogButton == ButtonType.OK) {
//                    String complaint = complainTextArea.getText();
//                    // Store the complaint (For simplicity, you can print it here)
//                    System.out.println("Complaint: " + complaint);
//                    return "Complaint recorded successfully";
//                }
//                return null;
//            });
//            // Show the dialog
//            complainDialog.showAndWait().ifPresent(result -> {
//                // Display a message indicating that the complaint has been recorded successfully
//                showAlert("Complaint Recorded", result);
//            });
//        });

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
    }
    // Add these fields to your class
    private List<String> votersList; // Placeholder for the list of voters
    private List<String> partiesList; // Placeholder for the list of parties
    private List<String> electionResults; // Placeholder for election results

    private void showAdminDashboard() {
        Stage adminDashboardStage = new Stage();
        adminDashboardStage.setTitle("Admin Dashboard");

        // Create components for admin dashboard
        Label adminLabel = new Label("Welcome, Admin!");

        Button viewVotersButton = new Button("View Voters");
//        viewVotersButton.setBackground(Background.fill(Color.YELLOW));
        viewVotersButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");
        Button viewCandidatesButton = new Button("View Parties");
        viewCandidatesButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        Button viewResultsButton = new Button("View Results");
        viewResultsButton.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");


//    // Set actions for the buttons
//    viewVotersButton.setOnAction(e -> displayList("Voters", votersList));
//
//    viewCandidatesButton.setOnAction(e -> displayCandidates());
//
//    viewResultsButton.setOnAction(e -> displayResults());

        backButton.setOnAction(e -> adminDashboardStage.close());

        // Set up the Admin Dashboard UI
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(adminLabel, 0, 0);
        grid.add(viewVotersButton, 0, 1);
        grid.add(viewCandidatesButton, 0, 2);
        grid.add(viewResultsButton, 0, 3);
        grid.add(backButton, 0, 5);
        viewVotersButton.setOnAction(e -> viewAllVoters());
        Button viewElectionDataButton = new Button("Save Election Data");
        viewElectionDataButton.setStyle("-fx-text-fill: black; -fx-border-color: black; -fx-border-width: 2px; -fx-background-radius: 5px;");

        viewElectionDataButton.setOnAction(e -> createDataFile());
        grid.add(viewElectionDataButton, 0, 4);
        viewCandidatesButton.setOnAction(e->viewAllCandidates());
        viewResultsButton.setOnAction(e->updateResults1());
        Scene scene = new Scene(grid, 400, 300);
        adminDashboardStage.setScene(scene);
        adminDashboardStage.show();
    }
    private void viewAllVoters() {
        Stage votersStage = new Stage();
        votersStage.setTitle("View Voters");

        // Create a TableView
        TableView<User> tableView = new TableView<>();

        // Create columns
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> cnicColumn = new TableColumn<>("CNIC");
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("cnic"));

        TableColumn<User, Integer> contactColumn = new TableColumn<>("Contact");
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        TableColumn<User, String> voteStatusColumn = new TableColumn<>("Vote Status");
        voteStatusColumn.setCellValueFactory(user -> {
            String status = user.getValue().hasVoted() ? "Casted" : "Not Casted";
            return new SimpleStringProperty(status);
        });

        // Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, cnicColumn, contactColumn, voteStatusColumn);

        // Create an ObservableList to hold data
        ObservableList<User> votersData = FXCollections.observableArrayList();

        // Add declared voters to the ObservableList
        for (User user : users) {
            if (user.getUserType() == UserType.VOTER) {
                votersData.add(user);
            }
        }

        // Set the data to the TableView
        tableView.setItems(votersData);

        // Set up the scene
        Scene scene = new Scene(tableView, 400, 400);
        votersStage.setScene(scene);

        // Show the stage
        votersStage.show();

//        // Add a ChangeListener to detect changes in the vote status
//        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                if (!newValue.hasVoted()) {
//                    newValue.setVoted(true);
//                    tableView.refresh();  // Refresh the TableView to update the displayed data
//                    showAlert("Status Updated", "Voter status has been updated to 'Casted'.");
//                } else {
//                    showAlert("Invalid Operation", "Selected voter has already casted their vote.");
//                }
//            }
//        });
//    }
    }
    //     Sample parties with their names, passwords, and slogans
//    private Party party1 = new Party("PTI", "123","Tabdeeli aye gi");
//    private Party party2 = new Party("PPP", "456","Bhutto zinda hai");
//    private Party party3 = new Party("PMLN","789", "Sher aya sher");
    private void viewAllCandidates() {
        Stage candidatesStage = new Stage();
        candidatesStage.setTitle("View Candidates");

        // Create a TableView for combined Party and PartyData
        TableView<CombinedData> combinedTableView = new TableView<>();

        // Create columns for combined data
        TableColumn<CombinedData, String> partyNumberColumn = new TableColumn<>("Number");
        partyNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(combinedTableView.getItems().indexOf(cellData.getValue()) + 1)));

        TableColumn<CombinedData, String> partyNameColumn = new TableColumn<>("Party");
        partyNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParty().getName()));

        TableColumn<CombinedData, String> yearsColumn = new TableColumn<>("Years");
        yearsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPartyData().getYears()));

        TableColumn<CombinedData, String> sloganColumn = new TableColumn<>("Slogan");
        sloganColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPartyData().getSlogan()));

        // Add columns to the combined TableView
        combinedTableView.getColumns().addAll(partyNumberColumn, partyNameColumn, yearsColumn, sloganColumn);

        // Create sample data
        List<CombinedData> combinedDataList = new ArrayList<>();
        combinedDataList.add(new CombinedData(new Party("PTI"), new PartyData("2015-2018", "Tabdeeli aye gi")));
        combinedDataList.add(new CombinedData(new Party("PPP"), new PartyData("2012-2015", "Bhutto zinda hai")));
        combinedDataList.add(new CombinedData(new Party("PMLN"), new PartyData("2018 onwards", "Sher aya sher")));

        // Create an ObservableList for combined data
        ObservableList<CombinedData> combinedData = FXCollections.observableArrayList(combinedDataList);

        // Set the data to the combined TableView
        combinedTableView.setItems(combinedData);

        // Set up the scene
        Scene scene = new Scene(combinedTableView, 300, 200);
        candidatesStage.setScene(scene);

        // Show the stage
        candidatesStage.show();
    }

    // Create a CombinedData class to hold Party and PartyData together
    private static class CombinedData {
        private final Party party;
        private final PartyData partyData;

        public CombinedData(Party party, PartyData partyData) {
            this.party = party;
            this.partyData = partyData;
        }

        public Party getParty() {
            return party;
        }

        public PartyData getPartyData() {
            return partyData;
        }
    }



    private void updateResults1() {
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Election Results");

        // Create a TableView
        TableView<Party> resultsTableView = new TableView<>();

        // Create columns
        TableColumn<Party, String> partyNameColumn = new TableColumn<>("Party Name");
        partyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Party, Integer> voteCountColumn = new TableColumn<>("Vote Count");
        voteCountColumn.setCellValueFactory(new PropertyValueFactory<>("voteCount"));

        // Add columns to the TableView
        resultsTableView.getColumns().addAll(partyNameColumn, voteCountColumn);

        // Create an ObservableList to hold data
        ObservableList<Party> resultsData = FXCollections.observableArrayList(parties);

        // Set the data to the TableView
        resultsTableView.setItems(resultsData);

        // Create a button to close the results window
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> resultsStage.close());

        // Create a VBox to hold TableView and close button
        VBox resultsVBox = new VBox(10);
        resultsVBox.setAlignment(Pos.CENTER);
        resultsVBox.getChildren().addAll(resultsTableView, closeButton);

        Scene resultsScene = new Scene(resultsVBox, 300, 200);
        resultsStage.setScene(resultsScene);

        // Show the results window without blocking interactions with other windows
        resultsStage.show();
    }

}