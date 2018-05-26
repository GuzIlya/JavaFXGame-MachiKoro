package CardsView;

import javafx.scene.layout.VBox;

public class StoreOfReserveCards extends VBox{
	public StoreOfReserveCards(LineOfReserveCards...lines ) {
		setSpacing(15);
		for(LineOfReserveCards line: lines) {
			getChildren().add(line);
		}
	}
}
