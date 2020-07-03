// initial imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//layout imports
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//Import components
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

//import image components
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class cardsHiLoGUI extends Application {

	//instantiate the components
	//Labels
	Label labelFirstCardDealt;
	Label labelSecondCardDealt;
	Label labelNextCard;
	Label result;
	Label labelProgress;

	//Images
	ImageView firstCard;
	ImageView secondCard;
	Image imageFirstCard;
	Image imageSecondCard;

	//Buttons
	RadioButton higher;
	RadioButton lower;
	Button buttonLeftCard;
	Button buttonRigthCard;
	ToggleGroup toggleGroup;

	//Menu
	MenuBar menuBar;
	Menu menuFile;
	Menu menuHelp;
	MenuItem menuItemNewGame;
	MenuItem menuItemShuffle;
	MenuItem menuItemExit;
	MenuItem menuAbout;

	//Objects
	DeckOfCards deck;
	Card cardLeft;
	Card cardRight;

	//Progress bar
	ProgressBar progBar;
	ProgressIndicator progInd;

	//Auxiliary variables
	int count;
	double progValue;


	@Override
	public void init() {

		//initialize the components
		//labels
		labelFirstCardDealt = new Label("First Card Dealt:");
		labelSecondCardDealt = new Label("Second Card Dealt:");;
		labelNextCard = new Label("Next Card will be");;
		result = new Label("");
		labelProgress = new Label("Progress:");

		//images
		firstCard = new ImageView();
		secondCard = new ImageView();

		//buttons
		higher = new RadioButton("Higher");
		lower = new RadioButton("Lower");
		buttonLeftCard = new Button("<- Deal First Card");
		buttonRigthCard = new Button("Deal Second Card ->");
		toggleGroup = new ToggleGroup();

		//menu
		menuBar = new MenuBar();
		menuFile = new Menu("File");
		menuHelp = new Menu("Help");
		menuItemNewGame = new MenuItem("New Game");
		menuItemShuffle = new MenuItem("Shuffle");
		menuItemExit = new MenuItem("Exit");
		menuAbout = new MenuItem("About");

		//Objects
		deck = new DeckOfCards();
		cardLeft = deck.dealTopCard();
		cardRight = deck.dealTopCard();

		//images
		imageFirstCard = cardToImage(cardLeft);
		firstCard = new ImageView(imageFirstCard);
		imageSecondCard = cardToImage(cardRight);;
		secondCard = new ImageView(imageSecondCard);

		//Progress bar
		progBar = new ProgressBar(0);
		progInd = new ProgressIndicator(0);

		//Auxiliary variables
		progValue = progBar.getProgress();
		count = 0;
	}//init()

	private Image cardToImage(Card card) {
		return new Image("/cards/" + card.toString() + ".png");
	}

	@Override
	public void start(Stage pStage) throws Exception {

		//set the stage
		pStage.setTitle("Hi-Lo Card Game {Renata Covisi Pereira 3021651}");
		pStage.setHeight(350);
		pStage.setWidth(430);

		//set the layout 
		VBox vbox = new VBox();
		GridPane gp = new GridPane();

		//Set menu bar
		vbox.getChildren().add(menuBar);
		menuBar.getMenus().add(menuFile);
		menuBar.getMenus().add(menuHelp);
		menuFile.getItems().add(menuItemNewGame);
		menuFile.getItems().add(menuItemShuffle);
		menuFile.getItems().add(menuItemExit);
		menuHelp.getItems().add(menuAbout);

		//set menu bar actions
		menuItemExit.setOnAction(ae -> pStage.close());
		menuAbout.setOnAction(ae -> {
			//Show an alert with application information.
			Alert alert = new Alert(AlertType.INFORMATION);

			//Add a heading for the title bar.
			alert.setHeaderText("About cardsHiLoGUI");

			//Print a message into the alert.
			alert.setContentText("Renata Covisi Pereira 3021651" );

			alert.showAndWait();
		});

		//Shuffle cards
		menuItemShuffle.setOnAction(ae -> deck.shuffle());

		//Start a new game
		menuItemNewGame.setOnAction(ae -> {
			//reinitialize components
			result.setText("");
			deck = new DeckOfCards();
			count = 0;
			progValue = 0;
			progBar.setProgress(0); 
			progInd = new ProgressIndicator(0);
			cardLeft = deck.dealTopCard();
			cardRight = deck.dealTopCard();
			imageFirstCard = cardToImage(cardLeft);
			firstCard.imageProperty().set(imageFirstCard);
			imageSecondCard = cardToImage(cardRight);
			secondCard.imageProperty().set(imageSecondCard);
		});

		buttonLeftCard.setOnAction(ae ->{
			setResult(cardLeft, cardRight, imageFirstCard, firstCard);
		});

		buttonRigthCard.setOnAction(ae ->{
			setResult(cardRight, cardLeft, imageSecondCard, secondCard);
		});

		//set other elements
		vbox.getChildren().add(gp);
		gp.add(labelFirstCardDealt, 0, 0);
		gp.add(firstCard, 0, 1, 1, 5);
		gp.add(labelNextCard, 1, 1);
		gp.add(higher, 1, 2);
		gp.add(lower, 1, 3);
		gp.add(buttonLeftCard, 1, 4);
		gp.add(buttonRigthCard, 1, 5);
		gp.add(labelSecondCardDealt, 2, 0);
		gp.add(secondCard, 2, 1, 1, 5);
		gp.add(result, 2, 6);
		gp.add(labelProgress, 0, 6);
		gp.add(progBar, 0, 7);

		//progbar size
		progBar.setMinWidth(125);

		//set padding and gaps of the grid pane
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);

		//button size
		buttonLeftCard.setMinWidth(130);
		buttonRigthCard.setMinWidth(130);

		//set toggle group
		higher.setToggleGroup(toggleGroup);
		lower.setToggleGroup(toggleGroup);

		//set the scene
		Scene s = new Scene(vbox);

		// add a style to the scene
		s.getStylesheets().add("mainStyle.css");

		//show the scene
		pStage.setScene(s);
		pStage.show();
	}//start()

	private void setResult(Card card1, Card card2, Image cardImage, ImageView imageViewCard ) {
		result.setText("");
		card1 = deck.dealTopCard();
		cardImage = cardToImage(card1);
		imageViewCard.imageProperty().set(cardImage);

		if (higher.isSelected() && card1.rankIsGreaterThan(card2)) {
			countPoints();

			if (count == 5)
				result.setText("You win!");
			else
				result.setText("Correct guess!");
		}//if
		
		else if(lower.isSelected() && card1.rankIsLessThan(card2)) {
			countPoints();

			if (count == 5)
				result.setText("You win!");
			else
				result.setText("Correct guess!");
		}//else if
		
		else {
			result.setText("Wrong guess!");
			count = 0;
			progValue = 0;
			progBar.setProgress(progValue);
			progInd.setProgress(progValue);
		}//else
	}

	private void countPoints() {
		count++;
		progValue = progValue + 0.2;
		progBar.setProgress(progValue);
		progInd.setProgress(progValue);
	}

	public static void main(String[] args) {
		launch();
	}//main()
}//class
