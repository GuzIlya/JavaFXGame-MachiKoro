package Cards;

public class ReserveCard {
	public String name;
	public int[] ActivationNumbers;
	public int cost;
	public int count = 0;
	public ReserveCard(String name, int[] ActivationNumbers, int cost) {
		this.ActivationNumbers = ActivationNumbers;
		this.cost = cost;
		this.name = name;
		this.count = 1;
	}
	public void AddCard() {
		count++;
	}
}
