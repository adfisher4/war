package war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	List<Card> cards = new ArrayList<Card>();
	//int numCards;
	
	
	public Deck() {
		String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
		
		for (int i = 2; i <= 14; i++) {
			String name = names[i-2];
				for (String suit: suits) {
					Card card = new Card(name, suit, i);
					this.cards.add(card);
				
			}
		}
		
		
	}
	
	public void describe() {
		//System.out.println("The deck has " + this.cards.length + " in it.");
		System.out.println("Cards in deck:");
		
		for (Card card: cards) {
			card.describe();
			
		}
	}
	
	public int getDeckSize() {
		return cards.size();
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card draw() {
		Card card = cards.remove(0);
		//System.out.print("You drew a ");
		//card.describe();
		return card;
	}

}
