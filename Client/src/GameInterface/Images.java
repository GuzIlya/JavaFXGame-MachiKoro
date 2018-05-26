package GameInterface;

import javafx.scene.image.Image;

public class Images {
	public Image RailwayStationImage;
	public Image RailwayStationBacksideImage;
	public Image ShoppingCenterImage;
	public Image ShoppingCenterBacksideImage;
	public Image AmusementParkImage;
	public Image AmusementParkBacksideImage;
	public Image RadioTowerImage;
	public Image RadioTowerBacksideImage;
	public Image StadiumImage;	
	public Image TelecentreImage;
	public Image BusinessCenterImage;	
	public Image CafeImage;
	public Image RestaurantImage;	
	public Image WheatFieldImage;
	public Image FarmImage;	
	public Image ForestImage;
	public Image AppleOrchardImage;	
	public Image MineImage;
	public Image BakeryImage;	
	public Image ShopImage;
	public Image FruitMarketImage;	
	public Image FurnitureFactoryImage;
	public Image DairyImage;
	
	public Images() {
		RailwayStationImage = new Image(getClass().getResourceAsStream("/resources/Railway station.jpg"));
		RailwayStationBacksideImage = new Image(getClass().getResourceAsStream("/resources/Railway station backside.jpg"));	
		ShoppingCenterImage = new Image(getClass().getResourceAsStream("/resources/Shopping center.jpg"));	
		ShoppingCenterBacksideImage = new Image(getClass().getResourceAsStream("/resources/Shopping center backside.jpg"));	
		AmusementParkImage = new Image(getClass().getResourceAsStream("/resources/Amusement park.jpg"));
		AmusementParkBacksideImage = new Image(getClass().getResourceAsStream("/resources/Amusement park backside.jpg"));
		RadioTowerImage = new Image(getClass().getResourceAsStream("/resources/Radio tower.jpg"));
		RadioTowerBacksideImage = new Image(getClass().getResourceAsStream("/resources/Radio tower backside.jpg"));
		StadiumImage = new Image(getClass().getResourceAsStream("/resources/Stadium.jpg"));	
		TelecentreImage = new Image(getClass().getResourceAsStream("/resources/Telecentre.jpg"));
		BusinessCenterImage = new Image(getClass().getResourceAsStream("/resources/Business center.jpg"));	
		CafeImage = new Image(getClass().getResourceAsStream("/resources/Cafe.jpg"));
		RestaurantImage = new Image(getClass().getResourceAsStream("/resources/Restaurant.jpg"));	
		WheatFieldImage = new Image(getClass().getResourceAsStream("/resources/Wheat field.jpg"));
		FarmImage = new Image(getClass().getResourceAsStream("/resources/Farm.jpg"));	
		ForestImage = new Image(getClass().getResourceAsStream("/resources/Forest.jpg"));
		AppleOrchardImage = new Image(getClass().getResourceAsStream("/resources/Apple orchard.jpg"));	
		MineImage = new Image(getClass().getResourceAsStream("/resources/Mine.jpg"));
		BakeryImage = new Image(getClass().getResourceAsStream("/resources/Bakery.jpg"));	
		ShopImage = new Image(getClass().getResourceAsStream("/resources/Shop.jpg"));
		FruitMarketImage = new Image(getClass().getResourceAsStream("/resources/Fruit market.jpg"));	
		FurnitureFactoryImage = new Image(getClass().getResourceAsStream("/resources/Furniture factory.jpg"));
		DairyImage = new Image(getClass().getResourceAsStream("/resources/Dairy.jpg"));
	}
	
	public Image foundByName(String name) {
			if(name.equals("Stadium"))
				return StadiumImage;
			if(name.equals("Telecentre"))
				return TelecentreImage;
			if(name.equals("BusinessCenter"))
				return BusinessCenterImage;
			if(name.equals("Cafe"))
				return CafeImage;
			if(name.equals("Restaurant"))
				return RestaurantImage;
			if(name.equals("Farm"))
				return FarmImage;
			if(name.equals("Forest"))
				return ForestImage;
			if(name.equals("AppleOrchard"))
				return AppleOrchardImage;
			if(name.equals("Mine"))
				return MineImage;
			if(name.equals("Shop"))
				return ShopImage;
			if(name.equals("FruitMarket"))
				return FruitMarketImage;
			if(name.equals("FurnitureFactory"))
				return FurnitureFactoryImage;
			if(name.equals("Dairy"))
				return DairyImage;
			
			return AmusementParkBacksideImage;
	}

}
