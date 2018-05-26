package GameInterface;

import java.util.Vector;

import CardsView.Card;
import CardsView.LineOfReserveCards;
import CardsView.StoreOfReserveCards;
import Client.Client;
import Menu.MenuBox;
import Menu.MenuItem;
import Menu.SubMenu;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/** ����� ��������� ����� ��� ����
 * 
 * @author ����
 * @version 1.0
 */
public class Game {
	
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 950;
	public static final int CardWIDTH = CardsView.Card.CardWIDTH;
	public static final int CardHEIGHT = CardsView.Card.CardHEIGHT;

	/**
	 *  ����� ��� ����
	 */
	private static Scene gameScene;
	/**
	 *  ������ ��� ����
	 */
	private static Pane gamePane;
	static RecView winLabel;
	
	static RecView pushReview;
	/** �������� ������ ����*/ 
	static RecView cardsLeft;
	/** ������ �������� ������ */
	static RecView coins;
	/** ������ �������� �� ������ ����������� �����*/
	static RecView yourCity;
	/** ������ �������� �� ������ ����������� �����*/
	static RecButton close;
	/** ������ ������� �����*/
	static RecButton buyButton;
	/** ����������� ����� ��� ������ �����������*/
	static RecButton pushDice;
	
	static RecButton finishStep;
	
	static RecButton startRecent;

	
	static ImageView ImageToView;
	/** ��� ��� ������ ����������� �����*/
	static Rectangle background  = new Rectangle(WIDTH, HEIGHT);

	static Vector<PlayerInfo> players = new Vector<PlayerInfo>();
	
	static Vector<LineOfReserveCards> linesOfReserveCards = new Vector<LineOfReserveCards>();
	
	static Images images = new Images();
	
	static Client client;
	
	static TextArea newsText;
	
	public static String viewingBuildingName;
	public static int currentCountOfCards;
	public static Card Stadium;
	public static Card Telecentre;
	public static Card BusinessCenter;
	public static Card Cafe;
	public static Card Restaurant;
	public static Card WheatField;
	public static Card Farm;
	public static Card Forest;
	public static Card AppleOrchard;
	public static Card Mine;
	public static Card Bakery;
	public static Card Shop;
	public static Card FruitMarket;
	public static Card FurnitureFactory;
	public static Card Dairy;
	
	static MenuBox menuBox;
	
	public Game(Client clent) {
		this.client = clent;
		int i = 0;
		if(client.gameMode)
			for(i = 0; i < client.countOfPlayers; i++)	
				players.addElement(new PlayerInfo("����� " + (i+1), 3, images));
		else
		{
			players.addElement(new PlayerInfo("����� 1", 3, images));
			for(i = 1; i < client.countOfPlayers; i++)
				players.addElement(new PlayerInfo("��� " + i, 3, images));
		}
		
		for(i = 0; i < client.countOfPlayers; i++)
		{	linesOfReserveCards.addElement(new LineOfReserveCards(players.get(i).RailwayStation, players.get(i).ShoppingCenter, 
				players.get(i).AmusementPark, players.get(i).RadioTower, players.get(i).Bakery, players.get(i).WheatField, 
				players.get(i).slot7, players.get(i).slot8, players.get(i).slot9, players.get(i).slot10));
			linesOfReserveCards.get(i).setTranslateX(45);
			linesOfReserveCards.get(i).setTranslateY(685);
			linesOfReserveCards.get(i).setVisible(false);
		}
		linesOfReserveCards.get(client.currentPlayer).setVisible(true);
				
		ImageToView = new ImageView();
		
		gamePane = new Pane(); 
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		
		Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/gameBackground.jpg"));
		ImageView backgroundImg = new ImageView(backgroundImage);
		backgroundImg.setFitHeight(HEIGHT);
		backgroundImg.setFitWidth(WIDTH);
		
	
		Stadium = new Card("Stadium", images.StadiumImage, false);
		Telecentre = new Card("Telecentre", images.TelecentreImage, false);
		BusinessCenter = new Card("BusinessCenter", images.BusinessCenterImage, false);
		Cafe = new Card("Cafe", images.CafeImage, false);
		Restaurant = new Card("Restaurant", images.RestaurantImage, false);
		WheatField = new Card("WheatField", images.WheatFieldImage, false);
		Farm = new Card("Farm", images.FarmImage, false);
		Forest = new Card("Forest", images.ForestImage, false);
		AppleOrchard = new Card("AppleOrchard", images.AppleOrchardImage, false);
		Mine = new Card("Mine", images.MineImage, false);
		Bakery = new Card("Bakery", images.BakeryImage, false);
		Shop = new Card("Shop", images.ShopImage, false);
		FruitMarket = new Card("FruitMarket", images.FruitMarketImage, false);
		FurnitureFactory = new Card("FurnitureFactory", images.FurnitureFactoryImage, false);
		Dairy = new Card("Dairy", images.DairyImage, false);
		
		LineOfReserveCards line1 = new LineOfReserveCards(Stadium, Telecentre, BusinessCenter, Cafe, Restaurant);	
		LineOfReserveCards line2 = new LineOfReserveCards(WheatField, Farm, Forest, AppleOrchard, Mine);		
		LineOfReserveCards line3 = new LineOfReserveCards(Bakery, Shop, FruitMarket,FurnitureFactory, Dairy);
		
		StoreOfReserveCards store = new StoreOfReserveCards(line1, line2, line3);
		store.setTranslateX(45);
		store.setTranslateY(40);		
	
		Rectangle backgroundRec1 = new Rectangle((CardWIDTH*5 + 15*6), (CardHEIGHT*3 + 15*4));
		backgroundRec1.setFill(Color.BLACK);
		backgroundRec1.setOpacity(0.4);
		backgroundRec1.setArcHeight(40);
		backgroundRec1.setArcWidth(40);
		backgroundRec1.setTranslateX(30);
		backgroundRec1.setTranslateY(25);
		
		Rectangle backgroundRec2 = new Rectangle((CardWIDTH*10 + 15*11), (CardHEIGHT + 30));
		backgroundRec2.setFill(Color.BLACK);
		backgroundRec2.setOpacity(0.4);
		backgroundRec2.setArcHeight(40);
		backgroundRec2.setArcWidth(40);
		backgroundRec2.setTranslateX(30);
		backgroundRec2.setTranslateY(670);
		

		
		
		
		RecButton news = new RecButton("�������", 100, 40);
		
		news.setTranslateX(1200);
		news.setTranslateY(520);
		
		newsText = new TextArea("�������:\n\t����� ����� 1.");
		newsText.setTranslateX(700);
		newsText.setTranslateY(50);
		newsText.setPrefSize(650, 400);
		newsText.setOpacity(0.7);
		newsText.setBackground(null);
		newsText.setFont(Font.font("Monotype Corsiva", 20));
		newsText.setEditable(false);
		newsText.setVisible(false);

		finishStep = new RecButton("��������� ���", 140, 40);
		finishStep.setTranslateX(1200);
		finishStep.setTranslateY(470);
		finishStep.setVisible(false);
		finishStep.setOnMouseClicked(event ->{
			client.sendData("�����");
			pushDice.setVisible(true);
			finishStep.setVisible(false);
		});
		
		
		startRecent = new RecButton("�������������", 150, 40);
		startRecent.setTranslateX(1200);
		startRecent.setTranslateY(470);
		if(client.recentGameFlag)
			startRecent.setVisible(true);
		else
			startRecent.setVisible(false);
		startRecent.setOnMouseClicked(event ->{
			client.sendData("������");
			startRecent.setVisible(false);
		});
		
		pushDice = new RecButton("�������", 100, 40);	
		pushDice.setTranslateX(1200);
		pushDice.setTranslateY(470);
		
		if(client.recentGameFlag)
			pushDice.setVisible(false);
		
		pushDice.setOnMouseClicked(event ->{
			client.sendData("������");
			pushDice.setVisible(false);
			finishStep.setVisible(true);
		});
		
		winLabel = new RecView(players.get(client.currentPlayer).name + " �������! �����������!!!\n����� ����� ������� EXC...", 1000, 200);
		winLabel.setTranslateX(200);
		winLabel.setTranslateY(300);
		winLabel.setAlignment(Pos.CENTER);
		winLabel.setVisible(false);
		winLabel.text.setFont(Font.font("Monotype Corsiva", 46));

		
		pushReview = new RecView("", 60, 60);
		pushReview.setTranslateX(1000);
		pushReview.setTranslateY(500);
		pushReview.setAlignment(Pos.CENTER);
		pushReview.text.setFont(Font.font("Monotype Corsiva", 46));
		
		coins = new RecView("������: " + players.get(client.currentPlayer).money, 120, 40); 
		coins.setTranslateX(1200);
		coins.setTranslateY(600);
		coins.setAlignment(Pos.CENTER_LEFT);
		
		yourCity = new RecView("��� �����: ", 120, 40); 
		yourCity.setTranslateX(30);
		yourCity.setTranslateY(610);
		
		close = new RecButton("�������", 100, 40);
		
		close.setTranslateX(1000);
		close.setTranslateY(430);
		
		buyButton = new RecButton("������", 100, 40);
		
		buyButton.setTranslateX(1000);
		buyButton.setTranslateY(370);
		
		cardsLeft = new RecView("�������� ", 150, 40);
		cardsLeft.setTranslateX(640);
		cardsLeft.setTranslateY(835);
		
		buyButton.setOnMouseClicked(event ->{
			client.sendData("������ " + viewingBuildingName + " " + currentCountOfCards);
			buyButton.setVisible(false);
		});
		
		news.setOnMouseClicked(event ->{
			if(newsText.isVisible())
				newsText.setVisible(false);
			else
				newsText.setVisible(true);
		});
		
		close.setOnMouseClicked(event ->{
			background.setVisible(false);
			ImageToView.setVisible(false);
			buyButton.setVisible(false);
			close.setVisible(false);
			cardsLeft.setVisible(false);
		});

//		pushReview.setVisible(false);
		background.setVisible(false);
		ImageToView.setVisible(false);
		buyButton.setVisible(false);
		close.setVisible(false);
		cardsLeft.setVisible(false);
        
		MenuItem saveGame = new MenuItem("���������" + System.getProperty("line.separator") + "	�" + System.getProperty("line.separator") + "   �����");
		MenuItem exit = new MenuItem("�����");
		MenuItem back = new MenuItem("���������" + System.getProperty("line.separator") + "	�" + System.getProperty("line.separator") + "    ����");

		saveGame.setOnMouseClicked(event -> {
			client.sendData("��������� " + newsText.getText());
		});
		
		exit.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
		back.setOnMouseClicked(event -> {
			background.setVisible(false);
			menuBox.setVisible(false);
		});
		
		SubMenu choicePlayersMenu = new SubMenu(back, saveGame, exit);
		menuBox = new MenuBox(choicePlayersMenu);
		menuBox.changeSetToInGameParametres();
		gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
    		@Override
			public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ESCAPE)) 
                	viewGameMenu(); 
            }
        });
		
		gamePane.getChildren().addAll(backgroundImg, backgroundRec1, backgroundRec2,Game.coins, store,
				yourCity, news, pushDice, startRecent, pushReview, newsText, finishStep);
		for(i = 0; i < client.countOfPlayers; i++)
			gamePane.getChildren().add(linesOfReserveCards.get(i));
		gamePane.getChildren().addAll(background, menuBox, ImageToView, buyButton, close, cardsLeft, winLabel);
		
	}
	
	public static void recentGameEnd() {
		pushDice.setVisible(true);
		finishStep.setVisible(false);
	}
	
	public static void viewGameMenu() {
		background.setFill(Color.BLACK);
		background.setOpacity(0.7);
		background.setVisible(true);
		menuBox.setVisible(true);
	}
	/** ���������� �� ������ ��������� ����� 
	 * 
	 * @param img
	 */
	public static void viewCard(String name,ImageView img, boolean isSight, int countOfCards, boolean isAlreadyBought) {	
		viewingBuildingName = name;
		currentCountOfCards = countOfCards;
		background.setFill(Color.BLACK);
		background.setOpacity(0.7);
		cardsLeft.SetText("� �������: " + countOfCards);

		ImageToView.setImage(img.getImage());
		ImageToView.setFitWidth(CardWIDTH*4);
		ImageToView.setFitHeight(CardHEIGHT*4);
		ImageToView.setTranslateX(WIDTH/2-CardWIDTH*2);
		ImageToView.setTranslateY(HEIGHT/2-CardHEIGHT*2);
		
		background.setVisible(true);
		ImageToView.setVisible(true);
		close.setVisible(true);
		
		if(!isAlreadyBought)
		{	
			buyButton.setVisible(true);
			cardsLeft.SetText("��������: " + countOfCards);
		}
		if(!isSight)
			cardsLeft.setVisible(true);				
	}
	
	public Scene GetScene() {
		return gameScene;
	}	
	public static void showPushResult(String pushResult) {
		pushReview.SetText(pushResult);
	}
	public static void refreshNewsText(String newTextLine) {
		newsText.setText(newsText.getText() + "\n" +  newTextLine);
		newsText.setScrollTop(Double.MAX_VALUE);
	}
	public static void refreshBalanceOfCurrentPlayer(int newBalance) {
		players.get(client.currentPlayer).money = newBalance;
		setBalanceOfCurrentPlayer();
	}
	public static void setBalanceOfCurrentPlayer() {
		coins.SetText("������: " + players.get(client.currentPlayer).money);
	}
	public static void setPushDice() {
		pushDice.setVisible(true);
	}
	
	public static void refreshNewsNewStep(int flag) {		
		if(flag == 0)
			newsText.setText(newsText.getText() + "\n\t����� " +  players.get(client.currentPlayer).name + ":");
		if(flag == 1)
			newsText.setText(newsText.getText() + "\n\t������ " +  players.get(client.currentPlayer).name + ":");
		if (flag == 2)
			newsText.setText(newsText.getText() + "\n\t����� ���� " +  players.get(client.currentPlayer).name + ".");
		
		newsText.setScrollTop(Double.MAX_VALUE);
	}
	
	public static void endOfGame() {
		background.setVisible(true);
		winLabel.SetText(players.get(client.currentPlayer).name + " �������! �����������!!!\n����� ����� ������� EXC...");
		winLabel.setVisible(true);
	}	
	
	public static void decreaseCardInStore() {
		if(Stadium.name.equals(viewingBuildingName))
			Stadium.countOfCards--;
		if(Telecentre.name.equals(viewingBuildingName))
			Telecentre.countOfCards--;
		if(BusinessCenter.name.equals(viewingBuildingName))
			BusinessCenter.countOfCards--;
		if(Cafe.name.equals(viewingBuildingName))
			Cafe.countOfCards--;
		if(Restaurant.name.equals(viewingBuildingName))
			Restaurant.countOfCards--;
		if(WheatField.name.equals(viewingBuildingName))
			WheatField.countOfCards--;
		if(Farm.name.equals(viewingBuildingName))
			Farm.countOfCards--;
		if(Forest.name.equals(viewingBuildingName))
			Forest.countOfCards--;
		if(AppleOrchard.name.equals(viewingBuildingName))
			AppleOrchard.countOfCards--;
		if(Mine.name.equals(viewingBuildingName))
			Mine.countOfCards--;
		if(Bakery.name.equals(viewingBuildingName))
			Bakery.countOfCards--;
		if(Shop.name.equals(viewingBuildingName))
			Shop.countOfCards--;
		if(FruitMarket.name.equals(viewingBuildingName))
			FruitMarket.countOfCards--;
		if(FurnitureFactory.name.equals(viewingBuildingName))
			FurnitureFactory.countOfCards--;
		if(Dairy.name.equals(viewingBuildingName))
			Dairy.countOfCards--;
	}
	
	public static void addCardToPlayer() {
		if(players.get(client.currentPlayer).RailwayStation.name.equals(viewingBuildingName)){	
			players.get(client.currentPlayer).RailwayStation.SetNewImage(images.RailwayStationImage);
			players.get(client.currentPlayer).RailwayStation.isAlreadyBought = true;
			return;
		}
		if(players.get(client.currentPlayer).ShoppingCenter.name.equals(viewingBuildingName)){	
			players.get(client.currentPlayer).ShoppingCenter.SetNewImage(images.ShoppingCenterImage);
			players.get(client.currentPlayer).ShoppingCenter.isAlreadyBought = true;
			return;
		}
		if(players.get(client.currentPlayer).AmusementPark.name.equals(viewingBuildingName)){	
			players.get(client.currentPlayer).AmusementPark.SetNewImage(images.AmusementParkImage);
			players.get(client.currentPlayer).AmusementPark.isAlreadyBought = true;
			return;
		}
		if(players.get(client.currentPlayer).RadioTower.name.equals(viewingBuildingName)){	
			players.get(client.currentPlayer).RadioTower.SetNewImage(images.RadioTowerImage);
			players.get(client.currentPlayer).RadioTower.isAlreadyBought = true;
			return;
		}
		if(players.get(client.currentPlayer).Bakery.name.equals(viewingBuildingName)) {
			players.get(client.currentPlayer).Bakery.countOfCards++;
			return;
		}
		if(players.get(client.currentPlayer).WheatField.name.equals(viewingBuildingName)) {
			players.get(client.currentPlayer).WheatField.countOfCards++;
			return;
		}
		
		if(players.get(client.currentPlayer).slot7.isCardExists && players.get(client.currentPlayer).slot7.name.equals(viewingBuildingName)){			 	
			players.get(client.currentPlayer).slot7.countOfCards++;
			return;
		}
		if(!players.get(client.currentPlayer).slot7.isCardExists){
			System.out.println("����� � ����7");
			players.get(client.currentPlayer).slot7.AddBoughtCard(viewingBuildingName, images.foundByName(viewingBuildingName), false);
			return;		
		}
		
		if(players.get(client.currentPlayer).slot8.isCardExists && players.get(client.currentPlayer).slot8.name.equals(viewingBuildingName)){			 	
			players.get(client.currentPlayer).slot8.countOfCards++;
			return;
		}
		if(!players.get(client.currentPlayer).slot8.isCardExists){
			System.out.println("����� � ����8");
			players.get(client.currentPlayer).slot8.AddBoughtCard(viewingBuildingName,images.foundByName(viewingBuildingName), false);
			return;		
		}
		
		if(players.get(client.currentPlayer).slot9.isCardExists && players.get(client.currentPlayer).slot9.name.equals(viewingBuildingName)) {
			players.get(client.currentPlayer).slot9.countOfCards++;
			return;
		}
		if(!players.get(client.currentPlayer).slot9.isCardExists){
			System.out.println("����� � ����9");
			players.get(client.currentPlayer).slot9.AddBoughtCard(viewingBuildingName, images.foundByName(viewingBuildingName), false);
			return;		
		}
		if(players.get(client.currentPlayer).slot10.isCardExists && players.get(client.currentPlayer).slot10.name.equals(viewingBuildingName)) {
			players.get(client.currentPlayer).slot10.countOfCards++;
			return;
		}
		if(!players.get(client.currentPlayer).slot10.isCardExists){
			players.get(client.currentPlayer).slot10.AddBoughtCard(viewingBuildingName, images.foundByName(viewingBuildingName), false);
			return;		
		}
		System.out.println("������ �� �����");
	}
	public static void setPlayersLineOfCards() {
		if(client.currentPlayer != 0)
			linesOfReserveCards.get(client.currentPlayer - 1).setVisible(false);
		else
			linesOfReserveCards.get(linesOfReserveCards.size() -1 ).setVisible(false);
		
		linesOfReserveCards.get(client.currentPlayer).setVisible(true);
	}
}
