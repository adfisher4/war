package war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import war.Card;

public class Player {
	
	List<Card> hand = new ArrayList<Card>();
	int score;
	String name;
	
	
	public Player (String name) {
		this.name = name;
		this.score = 0;
		this.hand = new ArrayList<Card>();
	}
	
	public void describe() {
		System.out.println(this.name + " has " + this.score + " points, and " + this.hand.size() + " cards in-hand.");
		
	}
	
	public Card flip() {
		return hand.remove(0);
	}
	
	public void draw(Deck deck) {
		this.hand.add(deck.draw());
	}
	
	public void incrementScore(int points) {
		this.score += points;
	}
	
	public void shuffleHand() {
		Collections.shuffle(hand);
	}

}
