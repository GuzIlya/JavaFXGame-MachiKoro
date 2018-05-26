package Cards;

public class SightCard {
	public int cost;
	public String name;
	public boolean buyFlag = false;
	public SightCard(String name, int cost) {
		this.cost = cost;
		this.name = name;
	}
	public void setBuyFlag() {
		buyFlag = true;
	}
}
