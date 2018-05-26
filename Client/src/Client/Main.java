package Client;

import java.io.File;
import java.io.IOException;

import GameInterface.Game;
import Menu.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int WIDTH = 1400;
	public static final int HEIGHT = 950;
	
	public static Client client = new Client();

	public static Stage primaryStage;
	
	public static Scene scene;
	
	public static void main(String[] args) throws IOException {
		new Thread(client).start();
		launch(args);
	}
	@Override
	public void start(Stage primarStage) throws Exception {
		primaryStage = primarStage;
		Pane root = new Pane();
		Image image = new Image(getClass().getResourceAsStream("/resources/MainMenuImage.jpg"));
		ImageView img = new ImageView(image);
		img.setFitHeight(HEIGHT);
		img.setFitWidth(WIDTH);
		root.getChildren().addAll(img);
		
		
		scene = new Scene(root, WIDTH, HEIGHT);
		
		File file = new File("C:\\Users\\Илья\\eclipse-workspace\\Server\\SavedGame.txt");
		
		MenuItem recentGame = null;
	    if(file.exists()) 
	    	recentGame = new MenuItem("Возобновить"+ System.getProperty("line.separator") +"      игру");
	    
	    MenuItem newGame = new MenuItem("Новая"+ System.getProperty("line.separator") +"  игра");
		MenuItem gameRules = new MenuItem("Правила" + System.getProperty("line.separator") + "   игры");
		MenuItem exit = new MenuItem("Выйти");
		SubMenu mainMenu;
		
		if(file.exists())
			 mainMenu = new SubMenu(recentGame, newGame, gameRules, exit);
		else
			 mainMenu = new SubMenu(newGame, gameRules, exit);
		
		MenuItem singleGame = new MenuItem("Одиночная"+ System.getProperty("line.separator") + "      игра");
		MenuItem groupGame = new MenuItem("    Игра"+ System.getProperty("line.separator") +"       с"+ System.getProperty("line.separator") +"друзьями");
		MenuItem back = new MenuItem("Назад");
		SubMenu newGameMenu = new SubMenu(singleGame, groupGame, back);
		
		MenuItem players2 = new MenuItem("2 игрока");
		MenuItem players3 = new MenuItem("3 игрока");
		MenuItem players4 = new MenuItem("4 игрока");
		MenuItem players5 = new MenuItem("5 игроков");
		MenuItem back2 = new MenuItem("Назад");
		SubMenu choicePlayersMenu = new SubMenu(players2, players3, players4, players5, back2);
		
		MenuBox menuBox = new MenuBox(mainMenu);
		menuBox.setVisible(true);
		
		if(file.exists())
			recentGame.setOnMouseClicked(event -> {
				client.sendData("Воспроизвести");
				while(client.countOfPlayers == 0)
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {}
				primaryStage.setScene(new Game(client).GetScene());
			});
		
		exit.setOnMouseClicked(event -> System.exit(0));
		newGame.setOnMouseClicked(event -> {
			menuBox.setSubMenu(newGameMenu);
		});
		singleGame.setOnMouseClicked(event -> {
			client.sendData("Одиночная");
			menuBox.setSubMenu(choicePlayersMenu);
		});
		groupGame.setOnMouseClicked(event -> {
			client.sendData("Мультиплеер");
			menuBox.setSubMenu(choicePlayersMenu);
		});
		back.setOnMouseClicked(event -> {
			menuBox.setSubMenu(mainMenu);
		});
		back2.setOnMouseClicked(event -> {
			client.sendData("Назад");
			menuBox.setSubMenu(newGameMenu);
		});
		players2.setOnMouseClicked(event -> {
			client.sendData("Игроки 2");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		players3.setOnMouseClicked(event -> {
			client.sendData("Игроки 3");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		
		players4.setOnMouseClicked(event -> {
			client.sendData("Игроки 4");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		players5.setOnMouseClicked(event -> {
			client.sendData("Игроки 5");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		
		root.getChildren().add(menuBox);
		

		primaryStage.setOnCloseRequest(event -> System.exit(0));
		primaryStage.setTitle("Мачи коро");
		primaryStage.setScene(scene);
		primaryStage.setMaxHeight(HEIGHT);
		primaryStage.setMaxWidth(WIDTH);
		primaryStage.setMinHeight(HEIGHT);
		primaryStage.setMinWidth(WIDTH);
		primaryStage.show();		
	}
	
	public static void setMenuScene() {
		primaryStage.setScene(scene);
	}

}
