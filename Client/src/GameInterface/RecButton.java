package GameInterface;

import javafx.animation.StrokeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
/** Класс реализует пользовательский вид кнопки
 * 
 * @author Илья
 * @version
 */
public class RecButton extends StackPane{
	public RecButton(String name, int ButtonWIDTH, int ButtonHEIGHT) {
		Rectangle rec = new Rectangle(ButtonWIDTH,ButtonHEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.4);
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(3);
		rec.setArcHeight(40);
		rec.setArcWidth(40);
		
		Text text = new Text(name);
		text.setFill(Color.rgb(225, 213, 184));
		text.setFont(Font.font("Monotype Corsiva", 26));

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
