package war;

public class Card { 
	
	String name;
	String suit;
	int value;
	
	public Card (String name, String suit, int value) {
		this.name = name;
		this.suit = suit;
		this.value = value;
	}
	
	public void describe() {
		System.out.println(this.name + " of " + this.suit + ".");
	}
	
	//Getters
	public int getValue() {
		return value;
	}
	
	public String[] getNameAndSuit() {
		String[] nameSuit = {this.name, this.suit};
		return nameSuit;
	}
	
	//Setters
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}

}
