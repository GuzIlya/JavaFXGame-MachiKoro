package GameInterface;


import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Пользовательский класс для отображения строки
 * @author Илья
 * @version 1.0
 */
public class RecView extends StackPane {
	
	public Rectangle rec;
	public Text text;
	
	public RecView(String name, int ButtonWIDTH, int ButtonHEIGHT) {
		rec = new Rectangle(ButtonWIDTH,ButtonHEIGHT);
		rec.setFill(Color.BLACK);
		rec.setOpacity(0.4);
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(3);
		rec.setArcHeight(20);
		rec.setArcWidth(20);
		
		text = new Text(name);
		text.setFill(Color.rgb(225, 213, 184));
		text.setFont(Font.font("Monotype Corsiva", 26));

		setAlignment(Pos.CENTER);
		getChildren().addAll(rec, text);		
		
	}
	public void SetText(String newText) {
		text.setText(newText);
	}
}
