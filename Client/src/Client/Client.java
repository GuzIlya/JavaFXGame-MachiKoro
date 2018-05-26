package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {
	
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private static Socket connection;
	private static boolean isRunning = true;

	private static String readedData;
	private static String[] arrayOfReaded;
	
	public  boolean gameMode = true;
	public  int countOfPlayers = 0;
	public int currentPlayer = 0;
	public boolean recentGameFlag = false;
	
	@Override
	public void run() {		
		try {
				while(isRunning)
				{
					connection = new Socket(InetAddress.getByName("127.0.0.1"), 1234);			
					output = new ObjectOutputStream(connection.getOutputStream());
					input = new ObjectInputStream(connection.getInputStream());
					readedData = (String)input.readObject();
					System.out.println(readedData);
					arrayOfReaded = readedData.split(" ");
					switch(arrayOfReaded[0]) {
						case "Установить":
							GameInterface.Game.setPushDice();
							break;
						case "Старт":
							if(arrayOfReaded[1].equals("Одиночная"))
								gameMode = true;
							else
								gameMode = false;
							
							if(arrayOfReaded[2].equals("2")){
								countOfPlayers = 2;
							}else if(arrayOfReaded[2].equals("3")){
								countOfPlayers = 3;
							}else if(arrayOfReaded[2].equals("4")){
								countOfPlayers = 4;
							}else{
								countOfPlayers = 5;
							}
							break;
						case "Бросок":
							currentPlayer = Integer.parseInt(arrayOfReaded[2]);
							GameInterface.Game.showPushResult(arrayOfReaded[1]);
							break;
						case "Новость":
							readedData = "";
							for(int i = 1; i < arrayOfReaded.length; i++) {
								readedData = readedData.concat(arrayOfReaded[i] + " ");
							}
							System.out.println(readedData);
							GameInterface.Game.refreshNewsText(readedData);
							break;
						case "Баланс":
							currentPlayer = Integer.parseInt(arrayOfReaded[2]);
							GameInterface.Game.setPlayersLineOfCards();
							GameInterface.Game.refreshBalanceOfCurrentPlayer(Integer.parseInt(arrayOfReaded[1]));
							GameInterface.Game.refreshNewsNewStep(Integer.parseInt(arrayOfReaded[3]));
							break;
						case "Успех":
							GameInterface.Game.refreshNewsText("Покупка " + arrayOfReaded[1] + " прошла успешно.");
							GameInterface.Game.viewingBuildingName = arrayOfReaded[1];
							GameInterface.Game.addCardToPlayer();
							GameInterface.Game.decreaseCardInStore();
							break;
						case "Неудача":
							GameInterface.Game.refreshNewsText("Покупка невозможна");
							break;
						case "Победа":
							GameInterface.Game.endOfGame();
							break;
						case "Сохранено":
							System.exit(0);
						case "Начало":
							recentGameFlag = true;
							break;
						case "Конец":
							GameInterface.Game.recentGameEnd();
							recentGameFlag = false;
							break;
						default:
							break;
					}
					sendData("Данные приняты");
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
	}

	public void sendData(String s){
		try {
			output.flush();
			output.writeObject(s);
			output.flush();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void close(){
		try{
			output.close();
			input.close();
			connection.close();
			isRunning = false;
		}catch(Exception e){e.printStackTrace();}
	}
}
