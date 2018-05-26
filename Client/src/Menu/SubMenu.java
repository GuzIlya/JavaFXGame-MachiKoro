package Menu;
import javafx.scene.layout.HBox;
/** Класс служит для объединения элементов меню в одну панель
 * 
 * 
 * @author Илья
 * @version 1.0
 */
public class SubMenu extends HBox {
	public static final int Item_WIDTH = MenuItem.Item_WIDTH;
	public static final int Item_HEIGHT = MenuItem.Item_HEIGHT;
	public int count;
	/** Добавляет в панель все элементы, отправленные в конструктор
	 *  
	 * @param items
	 */
	public SubMenu(MenuItem...items) {
		count = 0;
		setSpacing(15);
		for(MenuItem item: items) {
			count++;
			getChildren().add(item);
		}
	}
}
