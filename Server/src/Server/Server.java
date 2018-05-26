package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;	
	public boolean isRunning = true;
	private static String readedData;
	private static String[] arrayOfReaded;
	/** Переменная для типа игры:
	 * 	true - одиночная
	 *  false - мультиплеер
	 */
	public  boolean gameMode = true;
	public  int countOfPlayers = 0;
	public boolean pushFlag = false;
	public boolean buyingFlag = false;
	public boolean skipBuyingPhase = false;
	public String cardName;
	public int countOfCards;
	public boolean recentGameFlag = false;
	public String recentGameNews;
	
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
		System.out.println("Сервер закрыт");
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(1234);
			System.out.println("Сервер запущен");
			while(isRunning) {
				connection = server.accept();
				System.out.println("Связь установлена");				
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());	
				readedData = (String)input.readObject();
				System.out.println("Пришло: " + readedData);
				arrayOfReaded = readedData.split(" ");
				switch(arrayOfReaded[0])
				{
					case "Одиночная":
						gameMode = true;
						sendData("SinglePlayer");
						break;
					case "Мультиплеер":
						gameMode = false;
						sendData("Multiplayer");
						break;
					case "Игроки":
						if(arrayOfReaded[1].equals("2")){
							countOfPlayers = 2;
							sendData("Two Players");
						}else if(arrayOfReaded[1].equals("3")){
							countOfPlayers = 3;
							sendData("Three Players");
						}else if(arrayOfReaded[1].equals("4")){
							countOfPlayers = 4;
							sendData("Four Players");
						}else{
							countOfPlayers = 5;
							sendData("Five Players");
						}
						break;
					case "Карта":
						if(arrayOfReaded[1].equals("Стадион"))
								sendData("Card Stadium");
						else
								sendData("Unknown card");
						break;
					case "Бросок":
						pushFlag = true;
						sendData("Обработка броска");
						break;
					case "Купить":
						buyingFlag = true;
						cardName = arrayOfReaded[1];
						countOfCards = Integer.parseInt(arrayOfReaded[2]);
						sendData("Обработка покупки");
						break;
					case "Конец":
						skipBuyingPhase = true;
						sendData("Завершение хода");
						break;
					case "Сохранить":
						saveGame();
						sendData("Сохранено");
						System.exit(0);
						break;
					case "Воспроизвести":
						playRecentGame();
						sendData("Начало воспроизведения");
						break;
					default:
						sendData("Back");
						break;
				}
			}
		}catch(Exception e) {
			close();
		}	
	}
	
	public boolean IsReadyToPlay() {
		if(countOfPlayers != 0)
			return true;
		return false;
	}
	
	public void playRecentGame() {
		recentGameFlag = true;
			
		StringBuilder sb = new StringBuilder();

		File file = new File("C:\\Users\\Илья\\eclipse-workspace\\Server\\SavedGame.txt");
		
		try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
	            s = in.readLine();
	            if(s.equals("true"))
	            	gameMode = true;
	            else
	            	gameMode = false;
	            
	            s = in.readLine();
	            countOfPlayers = Integer.parseInt(s);
	            
	            while ((s = in.readLine()) != null) {
	                sb.append(s);
	                sb.append("\n");
	            }
	        } finally {
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
		
		recentGameNews = sb.toString();		
	}
	
	public void saveGame() {
//		System.out.println(readedData);	
		StringBuilder sb = new StringBuilder();
		sb.append(gameMode);
        sb.append("\n");
        sb.append(countOfPlayers);
        sb.append("\n");
        sb.append(readedData);
        
		File file = new File("C:\\Users\\Илья\\eclipse-workspace\\Server\\SavedGame.txt");

		    try {
		        //проверяем, что если файл не существует то создаем его
		        if(!file.exists()){
		            file.createNewFile();
		        }

		        //PrintWriter обеспечит возможности записи в файл
		        PrintWriter out = new PrintWriter(file.getAbsoluteFile());

		        try {
		            //Записываем текст у файл
		            out.print(sb.toString());
		        } finally {
		            //После чего мы должны закрыть файл
		            //Иначе файл не запишется
		            out.close();
		        }
		    } catch(IOException e) {
		        throw new RuntimeException(e);
		    }
	}
}
