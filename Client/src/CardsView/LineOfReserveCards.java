package CardsView;

import javafx.scene.layout.HBox;

public class LineOfReserveCards extends HBox {
	public LineOfReserveCards(Card... cards){
		setSpacing(15);
		for(Card card: cards) {
//			if (card.isCardExists)
				getChildren().add(card);
		}
	}
	public void Refresh(Card card) {		
		getChildren().add(card);
	}
}
