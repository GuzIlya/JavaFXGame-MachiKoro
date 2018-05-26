package Menu;
import Client.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/** Класс формирует коробку-меню, предоставляя доступ её различным изменениям
 * 
 * @author Илья
 * @version 1.0
 */
public class MenuBox extends Pane {
	public static final int WIDTH = Main.WIDTH;
	public static final int HEIGHT = Main.HEIGHT;
	public static final int Item_WIDTH = SubMenu.Item_WIDTH;
	public static final int Item_HEIGHT = SubMenu.Item_HEIGHT;
	public int count;
	/** Образ меню*/
	static SubMenu subMenu;
	/** Формирует коробку-меню по заданному образу и устанавливает позицию на экране
	 * @param subMenu
	 */
	public MenuBox(SubMenu subMenu) {
		MenuBox.subMenu = subMenu;
		count = subMenu.count;
		setVisible(false);
		subMenu.setTranslateX(WIDTH/2 - (count * Item_WIDTH + (count-1) * 15) / 2);
		subMenu.setTranslateY(HEIGHT/2- Item_HEIGHT/2 + 40);
		getChildren().addAll(subMenu);
	}
	/**
	 * Изменяет образ меня в коробке
	 * @param subMenu
	 */
	public void changeSetToInGameParametres() {
		subMenu.setTranslateX(WIDTH/2 - (count * Item_WIDTH + (count-1) * 15) / 2);
		subMenu.setTranslateY(HEIGHT/2- Item_HEIGHT/2 + 40 - 100);
	}
	public void setSubMenu(SubMenu subMenu) {
		getChildren().remove(MenuBox.subMenu);
		MenuBox.subMenu = subMenu;
		count = subMenu.count;
		subMenu.setTranslateX(WIDTH/2 - (count * Item_WIDTH + (count-1) * 15) /2);
		subMenu.setTranslateY(HEIGHT/2- Item_HEIGHT/2 + 40);
		getChildren().add(MenuBox.subMenu);		
	}
}
