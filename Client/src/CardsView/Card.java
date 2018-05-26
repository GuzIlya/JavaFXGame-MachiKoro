package CardsView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/** Класс карты резерва
 * 
 * @author Илья
 * @version 1.0
 */
public class Card extends StackPane {
	public static final int CardWIDTH = 110;
	public static final int CardHEIGHT = 170;
	
	public boolean isCardExists;
	public boolean isSight;
	public int countOfCards;
	public boolean isAlreadyBought = false;
	public String name;
	ImageView img = new ImageView();

	public Card(){
		isCardExists = false;
		this.name = "";
		getChildren().add(img);
		
		setOnMouseClicked(event->{
		GameInterface.Game.viewCard(name, img, isSight, countOfCards, isAlreadyBought);
		});
	}
	
	public Card(String name, Image image, boolean isSightCard){
		isCardExists = true;
		this.name = name;
		img.setImage(image);
		img.setFitWidth(CardWIDTH);
		img.setFitHeight(CardHEIGHT);

		this.isSight = isSightCard;
		this.countOfCards = 5;
		getChildren().add(img);
		
		
		setOnMouseClicked(event->{
			GameInterface.Game.viewCard(name, img, isSight, countOfCards, isAlreadyBought);
		});
	}
	public void SetNewImage(Image image) {		
		img.setImage(image);
		img.setFitWidth(CardWIDTH);
		img.setFitHeight(CardHEIGHT);

	}
	public void AddBoughtCard(String name, Image image, boolean isSightCard) {
		this.name = name;
		
		isAlreadyBought = true;
		isCardExists = true;
		isSight = isSightCard;

		img.setImage(image);
		img.setFitWidth(CardWIDTH);
		img.setFitHeight(CardHEIGHT);
		
		
		countOfCards = 1;
	}
}
