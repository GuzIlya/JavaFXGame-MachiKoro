package Player;

import java.util.Vector;

import Cards.ReserveCard;
import Cards.SightCard;

public class Player {
	public String name;
	public int money;
	public boolean botFlag;
	public SightCard railwayStation;
	public SightCard shoppingCenter;
	public SightCard amusementPark;
	public SightCard radioTower;
	public Vector<ReserveCard> reserveCards;
	
	public Player(String name, int startMoney, boolean botFlag) {
		this.name = name;
		this.money = startMoney;
		this.botFlag = botFlag;
		this.railwayStation = new SightCard("RailwayStation", 4);
		this.shoppingCenter = new SightCard("ShoppingCenter", 10);
		this.amusementPark = new SightCard("AmusementPark", 16);
		this.radioTower = new SightCard("RadioTower", 22);
		this.reserveCards = new Vector<ReserveCard>();
		int a[] = {1};
		int b[] = {2,3};
		
		reserveCards.addElement(new ReserveCard("WheatField", a, 1));
		reserveCards.addElement(new ReserveCard("Bakery", b, 1));

	}
	public boolean CheckForWin() {
		if(railwayStation.buyFlag && shoppingCenter.buyFlag && amusementPark.buyFlag && radioTower.buyFlag)
			return true;
		else
			return false;
	}
	
	public void addCard(String name, int[] activationNumbers, int cost) {
		switch(name) {
			case "RailwayStation":
				railwayStation.setBuyFlag();
				break;
			case "ShoppingCenter":
				shoppingCenter.setBuyFlag();
				break;
			case "AmusementPark":
				amusementPark.setBuyFlag();
				break;
			case "RadioTower":
				radioTower.setBuyFlag();
				break;
			default:
				for(int i = 0; i < reserveCards.size(); i++)
					if(reserveCards.get(i).name.equals(name))
					{	
						reserveCards.get(i).count++;
						return;
					}
				reserveCards.addElement(new ReserveCard(name, activationNumbers, cost));
				break;
		}
	}
	
}
