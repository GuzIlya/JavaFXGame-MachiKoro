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
	/** ���������� ��� ���� ����:
	 * 	true - ���������
	 *  false - �����������
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
		System.out.println("������ ������");
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(1234);
			System.out.println("������ �������");
			while(isRunning) {
				connection = server.accept();
				System.out.println("����� �����������");				
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());	
				readedData = (String)input.readObject();
				System.out.println("������: " + readedData);
				arrayOfReaded = readedData.split(" ");
				switch(arrayOfReaded[0])
				{
					case "���������":
						gameMode = true;
						sendData("SinglePlayer");
						break;
					case "�����������":
						gameMode = false;
						sendData("Multiplayer");
						break;
					case "������":
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
					case "�����":
						if(arrayOfReaded[1].equals("�������"))
								sendData("Card Stadium");
						else
								sendData("Unknown card");
						break;
					case "������":
						pushFlag = true;
						sendData("��������� ������");
						break;
					case "������":
						buyingFlag = true;
						cardName = arrayOfReaded[1];
						countOfCards = Integer.parseInt(arrayOfReaded[2]);
						sendData("��������� �������");
						break;
					case "�����":
						skipBuyingPhase = true;
						sendData("���������� ����");
						break;
					case "���������":
						saveGame();
						sendData("���������");
						System.exit(0);
						break;
					case "�������������":
						playRecentGame();
						sendData("������ ���������������");
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

		File file = new File("C:\\Users\\����\\eclipse-workspace\\Server\\SavedGame.txt");
		
		try {
	        //������ ��� ������ ����� � �����
	        BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
	        try {
	            //� ����� ��������� ��������� ����
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
        
		File file = new File("C:\\Users\\����\\eclipse-workspace\\Server\\SavedGame.txt");

		    try {
		        //���������, ��� ���� ���� �� ���������� �� ������� ���
		        if(!file.exists()){
		            file.createNewFile();
		        }

		        //PrintWriter ��������� ����������� ������ � ����
		        PrintWriter out = new PrintWriter(file.getAbsoluteFile());

		        try {
		            //���������� ����� � ����
		            out.print(sb.toString());
		        } finally {
		            //����� ���� �� ������ ������� ����
		            //����� ���� �� ���������
		            out.close();
		        }
		    } catch(IOException e) {
		        throw new RuntimeException(e);
		    }
	}
}
