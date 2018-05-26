package Gameplay;

import java.util.Vector;


import Player.Player;
import Server.Server;

public class GameProcess implements Runnable {
	private Vector<Player> players = new Vector<Player>();
	
	public int pushResult;
	public Server server;
	public boolean startFlag = false;
	public boolean validFlag = false;
	public int cardCost;
	

	public boolean playMode;
	public int countOfPlayers;
	public Server serv;
	public String[] linesOfGameNews;
	public String[] wordOfGameNewsInLine;
	public String[][] wordsOfGameNews; 


	public GameProcess(boolean playMode, int countOfPlayers, Server serv) throws InterruptedException{
		this.serv = serv;
		this.playMode = playMode;
		this.countOfPlayers = countOfPlayers;
	}
	
	public void CheckOwnBuildings(int pushResult, int currentPlayer){
		int countOfBonuses;
		for(int i = 0; i < players.get(currentPlayer).reserveCards.size(); i++) {
			for(int j = 0; j < players.get(currentPlayer).reserveCards.get(i).ActivationNumbers.length; j++)
				if(players.get(currentPlayer).reserveCards.get(i).ActivationNumbers[j] == pushResult) {
//					System.out.println("���� ���������");
					switch(players.get(currentPlayer).reserveCards.get(i).name) {
						case "WheatField":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� WheatField � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Farm":
							System.out.println("���� ��������� �����");

							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Farm � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Forest":
							System.out.println("���� ��������� ����");

							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Forest � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Mine":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 3;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Mine � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 3 + " �����.");
							break;
						case "AppleOrchard":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� AppleOrchard � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Bakery":
							if(players.get(currentPlayer).amusementPark.buyFlag)
							{	
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 2;
								server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Bakery � ���������� " + 
								players.get(currentPlayer).reserveCards.get(i).count + " ����." +" ����� " + 
								players.get(currentPlayer).reserveCards.get(i).count * 2 + " �����.");
							}
							else
							{
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
								server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Bakery � ���������� " + 
								players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� " + 
								players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");		
							}
							break;
						case "Shop":
							System.out.println("���� ��������� ��������");
							if(players.get(currentPlayer).amusementPark.buyFlag)
							{	
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 4;
								server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Shop � ���������� " + 
								players.get(currentPlayer).reserveCards.get(i).count + " ����." +" ����� " + 
								players.get(currentPlayer).reserveCards.get(i).count * 4 + " �����.");
							}
							else
							{
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 3;
								server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Shop � ���������� " + 
								players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� " + 
								players.get(currentPlayer).reserveCards.get(i).count * 3 + " �����.");		
							}
							break;
						case "FurnitureFactory":
							countOfBonuses = 0;
							for(int k = 0; k < players.get(currentPlayer).reserveCards.size(); k++) {
								if(players.get(currentPlayer).reserveCards.get(k).name.equals("Mine"))
									countOfBonuses += players.get(currentPlayer).reserveCards.get(k).count;

								if(players.get(currentPlayer).reserveCards.get(k).name.equals("Forest"))
									countOfBonuses += players.get(currentPlayer).reserveCards.get(k).count;
							}

							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� FurnitureFactory � ���������� " + 
							players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� " + 
							players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3 + " �����.");		
							break;
						case "FruitMarket":
							countOfBonuses = 0;
							for(int k = 0; k < players.get(currentPlayer).reserveCards.size(); k++) {
								if(players.get(currentPlayer).reserveCards.get(k).name.equals("WheatField"))
									countOfBonuses += players.get(currentPlayer).reserveCards.get(k).count;

								if(players.get(currentPlayer).reserveCards.get(k).name.equals("AppleOrchard"))
									countOfBonuses += players.get(currentPlayer).reserveCards.get(k).count;
							}

							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� FruitMarket � ���������� " + 
							players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� " + 
							players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3 + " �����.");		
							break;
						case "Dairy":
							countOfBonuses = 0;
							for(int k = 0; k < players.get(currentPlayer).reserveCards.size(); k++) {
								if(players.get(currentPlayer).reserveCards.get(k).name.equals("Farm"))
									countOfBonuses += players.get(currentPlayer).reserveCards.get(k).count;
							}

							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Dairy � ���������� " + 
							players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� " + 
							players.get(currentPlayer).reserveCards.get(i).count * countOfBonuses * 3 + " �����.");		
							break;
						case "BusinessCenter":
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� BusinessCenter � ���������� " +
									players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� 0 �����.");	
							break;
						case "Telecentre":
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Telecentre � ���������� " +
									players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� 0 �����.");	
							break;
						case "Stadium":
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Stadium � ���������� " +
									players.get(currentPlayer).reserveCards.get(i).count + " ����." + " ����� 0 �����.");	
							break;
						default:
							break;
					}
				}
		}
	}
	
	public void CheckOtherPlayersBuildings(int pushResult, int currentPlayer){
		int i = currentPlayer + 1;
		while(i < players.size()) {
			CheckOtherMovePlayerBuildings(i, currentPlayer);
			i++;
		}
		i = 0;
		while(i < currentPlayer) {
			CheckOtherMovePlayerBuildings(i, currentPlayer);
			i++;
		}
	}
	
	public void CheckOtherMovePlayerBuildings(int currentPlayer, int playerWhoPushes){
		for(int i = 0; i < players.get(currentPlayer).reserveCards.size(); i++) {
			for(int j = 0; j < players.get(currentPlayer).reserveCards.get(i).ActivationNumbers.length; j++)
				if(players.get(currentPlayer).reserveCards.get(i).ActivationNumbers[j] == pushResult) {			
					switch(players.get(currentPlayer).reserveCards.get(i).name) {
						case "WheatField":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� WheatField � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Farm":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Farm � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Forest":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Forest � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Mine":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 3;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Mine � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 3 + " �����.");
							break;
						case "AppleOrchard":
							players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
							server.sendData("������� � " + players.get(currentPlayer).name + " ������������� AppleOrchard � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
							" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							break;
						case "Cafe":
							if((!players.get(currentPlayer).shoppingCenter.buyFlag && players.get(playerWhoPushes).money <= players.get(currentPlayer).reserveCards.get(i).count * 1)
									|| (players.get(currentPlayer).shoppingCenter.buyFlag && players.get(playerWhoPushes).money <= players.get(currentPlayer).reserveCards.get(i).count * 2)) {
								players.get(currentPlayer).money += players.get(playerWhoPushes).money;
								server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Cafe � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(playerWhoPushes).money + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(playerWhoPushes).money + " �����.");
								players.get(playerWhoPushes).money = 0;
								break;
							}
							if(players.get(currentPlayer).shoppingCenter.buyFlag) {
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 2;
								players.get(playerWhoPushes).money -= players.get(currentPlayer).reserveCards.get(i).count * 2;
								server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Cafe � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 2 + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(currentPlayer).reserveCards.get(i).count * 2 + " �����.");
								break;
							}
							if(!players.get(currentPlayer).shoppingCenter.buyFlag){
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 1;
								players.get(playerWhoPushes).money -= players.get(currentPlayer).reserveCards.get(i).count * 1;
								server.sendData("������� � " + players.get(currentPlayer).name + " �������������� Cafe � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							}					
							break;
						case "Restaurant":
							if((!players.get(currentPlayer).shoppingCenter.buyFlag && players.get(playerWhoPushes).money <= players.get(currentPlayer).reserveCards.get(i).count * 2)
									|| (players.get(currentPlayer).shoppingCenter.buyFlag && players.get(playerWhoPushes).money <= players.get(currentPlayer).reserveCards.get(i).count * 3)) {
								players.get(currentPlayer).money += players.get(playerWhoPushes).money;
								server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Restaurant � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(playerWhoPushes).money + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(playerWhoPushes).money + " �����.");
								players.get(playerWhoPushes).money = 0;
								break;
							}
							if(players.get(currentPlayer).shoppingCenter.buyFlag) {
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 3;
								players.get(playerWhoPushes).money -= players.get(currentPlayer).reserveCards.get(i).count * 3;
								server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Restaurant � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 3 + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(currentPlayer).reserveCards.get(i).count * 3 + " �����.");
								break;
							}
							if(!players.get(currentPlayer).shoppingCenter.buyFlag){
								players.get(currentPlayer).money += players.get(currentPlayer).reserveCards.get(i).count * 2;
								players.get(playerWhoPushes).money -= players.get(currentPlayer).reserveCards.get(i).count * 2;
								server.sendData("������� � " + players.get(currentPlayer).name + " ������������� Restaurant � ���������� " + players.get(currentPlayer).reserveCards.get(i).count + " ����." +
										" ����� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.\n" + players.get(playerWhoPushes).name
										+ " ������� " + players.get(currentPlayer).reserveCards.get(i).count * 1 + " �����.");
							}					
							break;
							
					}
				}
		}
	}

	public void CheckValidForBuying(int needToPay, int currentPlayer) {
		if(players.get(currentPlayer).money >= needToPay)
			validFlag = true;
		else validFlag = false;
	}

	public void PlayRecentGame() throws InterruptedException {
		System.out.println("��������������� ���� �����");
		
		while(!server.pushFlag)
				Thread.sleep(100);
		
		int stepsCounter = 0;
		int i = 0;
		linesOfGameNews = server.recentGameNews.split("\n");
		wordsOfGameNews = new String[linesOfGameNews.length][];
		for(i = 0; i < linesOfGameNews.length; i++)
			wordsOfGameNews[i] = linesOfGameNews[i].split(" ");	
		
		i = 1;
		
		while(i < wordsOfGameNews.length) {
			if(wordsOfGameNews[i][0].equals("\t�����"))
			{
				stepsCounter++;
				System.out.println("������� ������ ���� ������");
			}
			i++;
			
		}
		
		i = 1;
		int currentPlayer = 0;
		
		System.out.println(stepsCounter);
		while(true) {
			
			Thread.sleep(1000);
			
			if(wordsOfGameNews[i][0].equals("\t�����"))
			{	
				stepsCounter--;
				if(stepsCounter != 0 && i != 1)
					currentPlayer++;
				if(currentPlayer == countOfPlayers && i != 1)
					currentPlayer = 0;
				
				System.out.println(stepsCounter);
				
				if(stepsCounter == 0)
					break;
				
				if(i != 1)			
					server.sendData("������ " + players.get(currentPlayer).money + " " + currentPlayer + " 0");				
				
				if(players.get(currentPlayer).CheckForWin())
				{
					server.sendData("������");
					break;
				}
			}

			if(wordsOfGameNews[i][0].equals("\t�����"))
			{
				server.sendData("������ " + players.get(currentPlayer).money + " " + currentPlayer + " 2");
			}
			
			if(wordsOfGameNews[i][0].equals("��"))
			{
				pushResult = Integer.parseInt(wordsOfGameNews[i][3]);
				System.out.println("��������� ������ " + pushResult);
				server.sendData("������ " + wordsOfGameNews[i][3] + " " + currentPlayer);

				Thread.sleep(100);
				
				server.sendData("������� �� ������� ������ " + wordsOfGameNews[i][3]);	
				
				Thread.sleep(100);
				
				CheckOtherPlayersBuildings(pushResult, currentPlayer);

				Thread.sleep(100);
				
				CheckOwnBuildings(pushResult, currentPlayer);

				Thread.sleep(100);
				
				server.sendData("������ " + players.get(currentPlayer).money + " " + currentPlayer + " 1");
			}
		
			int[] a = new int[2];

			
			if(wordsOfGameNews[i][0].equals("�������"))
			{
				if(players.get(currentPlayer).reserveCards.size() != 6) {
					System.out.println("������� ������ " + wordsOfGameNews[i][1]);

					switch(wordsOfGameNews[i][1]) {
						case "Stadium":
							CheckValidForBuying(6, currentPlayer);
							a[0] = 6;
							a[1] = 0;
							cardCost = 6;
							break;
						case "Telecentre":
							CheckValidForBuying(7, currentPlayer);
							a[0] = 6;
							a[1] = 0;						
							cardCost = 7;
							break;
						case "BusinessCenter":
							CheckValidForBuying(8, currentPlayer);
							a[0] = 6;
							a[1] = 0;
							cardCost = 8;
							break;
						case "Cafe":
							CheckValidForBuying(2, currentPlayer);
							a[0] = 3;
							a[1] = 0;
							cardCost = 2;
							break;
						case "Restaurant":
							CheckValidForBuying(3, currentPlayer);
							a[0] = 9;
							a[1] = 10;
							cardCost = 3;
							break;
						case "WheatField":
							CheckValidForBuying(1, currentPlayer);
							a[0] = 1;
							a[1] = 0;
							cardCost = 1;
							break;
						case "Farm":
							CheckValidForBuying(1, currentPlayer);
							a[0] = 2;
							a[1] = 0;
							cardCost = 1;
							break;
						case "Forest":
							CheckValidForBuying(3, currentPlayer);
							a[0] = 5;
							a[1] = 0;
							cardCost = 3;
							break;
						case "AppleOrchard":
							CheckValidForBuying(3, currentPlayer);
							a[0] = 10;
							a[1] = 0;
							cardCost = 3;
							break;
						case "Mine":
							CheckValidForBuying(6, currentPlayer);
							a[0] = 9;
							a[1] = 0;
							cardCost = 6;
							break;
						case "Bakery":
							CheckValidForBuying(1, currentPlayer);
							a[0] = 2;
							a[1] = 3;
							cardCost = 1;
							break;
						case "Shop":
							System.out.println("����� � �������� ���������� shop");
							CheckValidForBuying(2, currentPlayer);
							a[0] = 4;
							a[1] = 0;
							cardCost = 2;
							break;
						case "FruitMarket":
							CheckValidForBuying(2, currentPlayer);
							a[0] = 11;
							a[1] = 12;
							cardCost = 2;
							break;
						case "FurnitureFactory":
							CheckValidForBuying(3, currentPlayer);
							a[0] = 8;
							a[1] = 0;
							cardCost = 3;
							break;
						case "Dairy":
							CheckValidForBuying(5, currentPlayer);
							a[0] = 7;
							a[1] = 0;
							cardCost = 5;
							break;
						case "RailwayStation":
							CheckValidForBuying(4, currentPlayer);
							a[0] = 0;
							a[1] = 0;
							cardCost = 4;
							break;
						case "ShoppingCenter":
							CheckValidForBuying(10, currentPlayer);
							a[0] = 0;
							a[1] = 0;
							cardCost = 10;
							break;
						case "AmusementPark":
							CheckValidForBuying(16, currentPlayer);
							a[0] = 0;
							a[1] = 0;
							cardCost = 16;
							break;					
						case "RadioTower":
							CheckValidForBuying(22, currentPlayer);
							a[0] = 0;
							a[1] = 0;
							cardCost = 22;
							break;
						default:
							validFlag = false;
					}				}	
				
				if(validFlag)
				{
					System.out.println("��������� ����� �������: " + wordsOfGameNews[i][1] + " " + a[0] + " " + a[1] + " " + cardCost);
					players.get(currentPlayer).money -= cardCost;
					players.get(currentPlayer).addCard(wordsOfGameNews[i][1], a, cardCost);
					server.sendData("����� " + wordsOfGameNews[i][1]);
				}
				else {
					server.sendData("�������");
				}	
			}

			i++;
		}
		
		server.pushFlag = false;
		startFlag = true;
		Thread.sleep(100);
		
		server.sendData("����� ���������������");
		
		currentPlayer++;
		
		if(currentPlayer == countOfPlayers)
			currentPlayer = 0;

		StartGameFromEnteredPlayer(currentPlayer);
		
		System.out.println(wordsOfGameNews[2][3]);
	}
	
	public void StartGameFromEnteredPlayer(int numberOfPlayer) {
		int i = numberOfPlayer;
		while(true) {
			validFlag = false;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			if(startFlag)
				server.sendData("����������");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			if(startFlag)			
				server.sendData("������ " + players.get(i).money + " " + i + " 0");
			
			System.out.println("������ " + players.get(i).name);
			
			while(!server.pushFlag)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e5) {}
			
			if(players.get(i).railwayStation.buyFlag)
				pushResult = 1 + (int)(Math.random() * 12);
			else
				pushResult = 1 + (int)(Math.random() * 6);
			//	pushResult = 1;
			
			server.pushFlag = false;
			System.out.println("��������� ������ " + pushResult);
			
			server.sendData("������ " + pushResult + " " + i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			server.sendData("������� �� ������� ������ " + pushResult);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			CheckOtherPlayersBuildings(pushResult, i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			CheckOwnBuildings(pushResult, i);			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			server.sendData("������ " + players.get(i).money + " " + i + " 1");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			while(true) {
				server.buyingFlag = false;
				
				while(!server.buyingFlag && !server.skipBuyingPhase)
					try {
						Thread.sleep(100);
					} catch (InterruptedException e5) {}
				
				System.out.println("���������� �������� ����������� �������:" + server.cardName);
				
				if(server.skipBuyingPhase)
					break;
				
				if(players.get(i).reserveCards.size() == 6)
					break;
				
				int[] a = new int[2];

				switch(server.cardName) {
					case "Stadium":
						CheckValidForBuying(6, i);
						a[0] = 6;
						a[1] = 0;
						cardCost = 6;
						break;
					case "Telecentre":
						CheckValidForBuying(7, i);
						a[0] = 6;
						a[1] = 0;						
						cardCost = 7;
						break;
					case "BusinessCenter":
						CheckValidForBuying(8, i);
						a[0] = 6;
						a[1] = 0;
						cardCost = 8;
						break;
					case "Cafe":
						CheckValidForBuying(2, i);
						a[0] = 3;
						a[1] = 0;
						cardCost = 2;
						break;
					case "Restaurant":
						CheckValidForBuying(3, i);
						a[0] = 9;
						a[1] = 10;
						cardCost = 3;
						break;
					case "WheatField":
						CheckValidForBuying(1, i);
						a[0] = 1;
						a[1] = 0;
						cardCost = 1;
						break;
					case "Farm":
						CheckValidForBuying(1, i);
						a[0] = 2;
						a[1] = 0;
						cardCost = 1;
						break;
					case "Forest":
						CheckValidForBuying(3, i);
						a[0] = 5;
						a[1] = 0;
						cardCost = 3;
						break;
					case "AppleOrchard":
						CheckValidForBuying(3, i);
						a[0] = 10;
						a[1] = 0;
						cardCost = 3;
						break;
					case "Mine":
						CheckValidForBuying(6, i);
						a[0] = 9;
						a[1] = 0;
						cardCost = 6;
						break;
					case "Bakery":
						CheckValidForBuying(1, i);
						a[0] = 2;
						a[1] = 3;
						cardCost = 1;
						break;
					case "Shop":
						System.out.println("����� � �������� ���������� shop");
						CheckValidForBuying(2, i);
						a[0] = 4;
						a[1] = 0;
						cardCost = 2;
						break;
					case "FruitMarket":
						CheckValidForBuying(2, i);
						a[0] = 11;
						a[1] = 12;
						cardCost = 2;
						break;
					case "FurnitureFactory":
						CheckValidForBuying(3, i);
						a[0] = 8;
						a[1] = 0;
						cardCost = 3;
						break;
					case "Dairy":
						CheckValidForBuying(5, i);
						a[0] = 7;
						a[1] = 0;
						cardCost = 5;
						break;
					case "RailwayStation":
						CheckValidForBuying(4, i);
						a[0] = 0;
						a[1] = 0;
						cardCost = 4;
						break;
					case "ShoppingCenter":
						CheckValidForBuying(10, i);
						a[0] = 0;
						a[1] = 0;
						cardCost = 10;
						break;
					case "AmusementPark":
						CheckValidForBuying(16, i);
						a[0] = 0;
						a[1] = 0;
						cardCost = 16;
						break;					
					case "RadioTower":
						CheckValidForBuying(22, i);
						a[0] = 0;
						a[1] = 0;
						cardCost = 22;
						break;
					default:
						validFlag = false;
				}
				
				if(validFlag)
				{
					players.get(i).money -= cardCost;
					players.get(i).addCard(server.cardName, a, cardCost);
					server.sendData("����� " + server.cardName);
					break;
				}
				else {
					server.sendData("�������");
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e5) {}
			
			server.sendData("������ " + players.get(i).money + " " + i + " 2");

			
			while(!server.skipBuyingPhase)
				try {
					Thread.sleep(100);
				} catch (InterruptedException e5) {}
			
			if(players.get(i).CheckForWin())
			{
				server.sendData("������");
				break;
			}
			
			server.skipBuyingPhase = false;			
			startFlag = true;
			i++;
			if(i == countOfPlayers) i = 0;
		}	
	}
	
	@Override
	public void run() {	
		System.out.println("���� ��������");
		this.server = serv;
		int i = 0;
		if(playMode)
			for(i = 0; i < countOfPlayers; i++)	
				players.addElement(new Player("����� " + (i+1), 3, false));
		else
		{
			players.addElement(new Player("����� 1", 3, false));
			for(i = 1; i < countOfPlayers; i++)
				players.addElement(new Player("��� " + i, 3, true));
		}
		
		if(server.recentGameFlag)
			try {
				PlayRecentGame();
			} catch (InterruptedException e) {}
		else
			StartGameFromEnteredPlayer(0);		
	}
}