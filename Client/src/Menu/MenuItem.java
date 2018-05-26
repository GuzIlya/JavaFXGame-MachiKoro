package Menu;
import javafx.scene.paint.Color;
import javafx.animation.StrokeTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
/** Класс формирует элемент меню
 * 
 * @author Илья
 * @version 1.0
 */
public class MenuItem extends StackPane{ 			// StackPane - last added element will viewed first
	/** Создает элемент меню с заданным именем
	 * 
	 * @param name
	 */
	public static final int Item_WIDTH = 178;
	public static final int Item_HEIGHT = 260;
	public MenuItem(String name) {
		Rectangle rec = new Rectangle(Item_WIDTH,Item_HEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.4);
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(3);
		rec.setArcHeight(40);
		rec.setArcWidth(40);
		Text text = new Text(name);
		text.setFill(Color.rgb(225, 213, 184));
		text.setFont(Font.font("Monotype Corsiva", 32));

		setAlignment(Pos.CENTER);
		getChildren().addAll(rec, text);		
		
		StrokeTransition st = new StrokeTransition(Duration.seconds(0.1), rec);
		setOnMouseEntered(event -> {
			st.setFromValue(Color.BLACK);
			st.setToValue(Color.DARKGRAY);
			rec.setOpacity(0.6);
			st.play();
		});
		setOnMouseExited(event -> {
			st.stop();
			rec.setOpacity(0.4);
			rec.setStroke(Color.BLACK);
		});
	}
}
