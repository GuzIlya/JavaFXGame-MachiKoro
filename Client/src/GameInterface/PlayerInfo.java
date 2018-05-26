package GameInterface;

import CardsView.Card;
import javafx.scene.image.Image;

public class PlayerInfo {
	public int money = 3;
	public Card RailwayStation;
	public Card ShoppingCenter;
	public Card AmusementPark;
	public Card RadioTower;
	public Card WheatField;
	public Card Bakery;
	public Card slot7;
	public Card slot8;
	public Card slot9;
	public Card slot10;
	public String name;
	
	public PlayerInfo(String name, int money, Images images) {
		this.name = name;
		this.money = money;
		
		RailwayStation = new Card("RailwayStation", images.RailwayStationBacksideImage, true);
		ShoppingCenter = new Card("ShoppingCenter", images.ShoppingCenterBacksideImage, true);
		AmusementPark = new Card("AmusementPark", images.AmusementParkBacksideImage, true);
		RadioTower = new Card("RadioTower", images.RadioTowerBacksideImage, true);
		WheatField = new Card("WheatField", images.WheatFieldImage, false);
		WheatField.isAlreadyBought = true;
		WheatField.countOfCards = 1;
		Bakery = new Card("Bakery", images.BakeryImage, false);
		Bakery.isAlreadyBought = true;
		Bakery.countOfCards = 1;

		slot7 = new Card();
		slot8 = new Card();
		slot9 = new Card();
		slot10 = new Card();
	}
}
