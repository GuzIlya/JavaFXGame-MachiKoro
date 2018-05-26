package Menu;
import Client.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/** ����� ��������� �������-����, ������������ ������ � ��������� ����������
 * 
 * @author ����
 * @version 1.0
 */
public class MenuBox extends Pane {
	public static final int WIDTH = Main.WIDTH;
	public static final int HEIGHT = Main.HEIGHT;
	public static final int Item_WIDTH = SubMenu.Item_WIDTH;
	public static final int Item_HEIGHT = SubMenu.Item_HEIGHT;
	public int count;
	/** ����� ����*/
	static SubMenu subMenu;
	/** ��������� �������-���� �� ��������� ������ � ������������� ������� �� ������
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
	 * �������� ����� ���� � �������
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
