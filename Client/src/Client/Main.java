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
		
		File file = new File("C:\\Users\\����\\eclipse-workspace\\Server\\SavedGame.txt");
		
		MenuItem recentGame = null;
	    if(file.exists()) 
	    	recentGame = new MenuItem("�����������"+ System.getProperty("line.separator") +"      ����");
	    
	    MenuItem newGame = new MenuItem("�����"+ System.getProperty("line.separator") +"  ����");
		MenuItem gameRules = new MenuItem("�������" + System.getProperty("line.separator") + "   ����");
		MenuItem exit = new MenuItem("�����");
		SubMenu mainMenu;
		
		if(file.exists())
			 mainMenu = new SubMenu(recentGame, newGame, gameRules, exit);
		else
			 mainMenu = new SubMenu(newGame, gameRules, exit);
		
		MenuItem singleGame = new MenuItem("���������"+ System.getProperty("line.separator") + "      ����");
		MenuItem groupGame = new MenuItem("    ����"+ System.getProperty("line.separator") +"       �"+ System.getProperty("line.separator") +"��������");
		MenuItem back = new MenuItem("�����");
		SubMenu newGameMenu = new SubMenu(singleGame, groupGame, back);
		
		MenuItem players2 = new MenuItem("2 ������");
		MenuItem players3 = new MenuItem("3 ������");
		MenuItem players4 = new MenuItem("4 ������");
		MenuItem players5 = new MenuItem("5 �������");
		MenuItem back2 = new MenuItem("�����");
		SubMenu choicePlayersMenu = new SubMenu(players2, players3, players4, players5, back2);
		
		MenuBox menuBox = new MenuBox(mainMenu);
		menuBox.setVisible(true);
		
		if(file.exists())
			recentGame.setOnMouseClicked(event -> {
				client.sendData("�������������");
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
			client.sendData("���������");
			menuBox.setSubMenu(choicePlayersMenu);
		});
		groupGame.setOnMouseClicked(event -> {
			client.sendData("�����������");
			menuBox.setSubMenu(choicePlayersMenu);
		});
		back.setOnMouseClicked(event -> {
			menuBox.setSubMenu(mainMenu);
		});
		back2.setOnMouseClicked(event -> {
			client.sendData("�����");
			menuBox.setSubMenu(newGameMenu);
		});
		players2.setOnMouseClicked(event -> {
			client.sendData("������ 2");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		players3.setOnMouseClicked(event -> {
			client.sendData("������ 3");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		
		players4.setOnMouseClicked(event -> {
			client.sendData("������ 4");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		players5.setOnMouseClicked(event -> {
			client.sendData("������ 5");
			while(client.countOfPlayers == 0)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			primaryStage.setScene(new Game(client).GetScene());
		});
		
		root.getChildren().add(menuBox);
		

		primaryStage.setOnCloseRequest(event -> System.exit(0));
		primaryStage.setTitle("���� ����");
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
