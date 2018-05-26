package Server;

import java.io.IOException;
import Gameplay.GameProcess;

public class Main {
	
	public static Server server= new Server();
	

	
	public static void main(String[] args) throws IOException, InterruptedException {		
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		while(!server.IsReadyToPlay())
			Thread.sleep(100);
			
		Thread.sleep(100);
		
		if(server.gameMode)
			server.sendData("Старт Одиночная " + server.countOfPlayers);
		else
			server.sendData("Старт Мультиплеер " + server.countOfPlayers);

		GameProcess game = new GameProcess(server.gameMode, server.countOfPlayers, server);
		
		Thread gameThread = new Thread(game);
		
		gameThread.start();
		
		serverThread.join();
		
		System.exit(0);
	}
}
