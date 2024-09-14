package war;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	// Global variable to store cards-in-play.
	public static ArrayList<Card> cardsInPlay = new ArrayList<Card>(); 
	
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		// Intro and Game Rules
		System.out.println();
		System.out.println("Welcome to the game of WAR!");
		System.out.println("Each player will start with half the deck.");
		System.out.println("A battle consists of each player flipping a card. The player with the higher card wins the battle and wins both cards for their hand.");
		System.out.println("If the cards have the same value, each player will contribute one card to the battle and also flip another card to compare");
		System.out.println("The player with the higher card receives ALL of the cards to put with their hand.");
		System.out.println("Players receive 1 point for each battle won (regardless of the number of cards won");
		System.out.println("Players decide how many rounds of war they want to play and whomever has the most points after that round wins!");
		System.out.println("If a player should run out of cards before the last round is played, the other player automatically wins!");
		System.out.println();
		
		// Create players
		System.out.println("Player 1, enter your name: ");
		Player player1 = new Player(sc.nextLine());
		
		System.out.println("Player 2, enter your name: ");
		Player player2 = new Player(sc.nextLine());
		
		// Get deck
		Deck deck = new Deck();
		deck.shuffle();
		
		// Deal cards to players
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				player1.draw(deck);
			} else {
				player2.draw(deck);
			}
		}		
		
		
		int round = 1;
		boolean playRound = true;
		
		//  MAIN LOOP  //
		while (playRound && player1.hand.size() > 0 && player2.hand.size() > 0) {
			// Shuffle hands every 5 rounds
			if (round % 5 == 0) {
				System.out.println("*".repeat(30));
				System.out.println("The players will now shuffle their hands to keep things interesting!");
				System.out.println("*".repeat(30));
				player1.shuffleHand();
				player2.shuffleHand();
			}
			// Determine number of 
			int numInRound = player2.hand.size();
			if (player1.hand.size() > player2.hand.size()) {
				numInRound = player1.hand.size();
			}
			
			// Play round
			for (int i = 0; i < numInRound; i++) {
				System.out.println();
				System.out.println("~".repeat(20));
				System.out.println();
				if (i == 0) {
					System.out.println("Let's go to war! Press enter to start:");
				} else {
				System.out.println("Press enter for next battle:");
				}
				sc.nextLine();
				battle(player1, player2);
			}
			
			System.out.println();
			System.out.println("THE RESULTS ARE IN");
			System.out.println("-".repeat(20));
			if (player1.score > player2.score) {
				System.out.println("After round " + round + ", " + player1.name + " leads with " + player1.score + " points and " + player1.hand.size() + " cards in hand.");
				System.out.println(player2.name + " has " + player2.score + " points and " + player2.hand.size() + " cards in hand.");
			} else if (player1.score < player2.score) {
				System.out.println("After " + round + " round/s, " + player2.name + " leads with " + player2.score + " points and " + player2.hand.size() + " cards in hand.");
				System.out.println(player1.name + " has " + player1.score + " points and " + player1.hand.size() + " cards in hand.");
			} else {
				System.out.println("It's a tie. Both players have " + player1.score + " points after round " + round + ".");
				System.out.println(player1.name + " has " + player1.hand.size() + " cards in hand.");
				System.out.println(player2.name + " has " + player2.hand.size() + " cards in hand.");
			}
			
			System.out.println("Do you want to play another round? ");
			String again = sc.nextLine();
			again = again.toLowerCase();
			round += 1;
			if (!again.equals("yes") && !again.equals("y")) {
				playRound = false;
				System.out.println("Thanks for playing WAR!");
			}
	}
		


}
	// Method to check for definite winner
	public static void battle(Player player1, Player player2) {
		// Flip and compare cards.
		Card player1card = player1.flip();
		System.out.print(player1.name + " flips: ");
		player1card.describe();
		Card player2card = player2.flip();
		System.out.print(player2.name + " flips: ");
		player2card.describe();
		cardsInPlay.add(player1card);
		cardsInPlay.add(player2card);
		int numCards = cardsInPlay.size();
		int points = numCards / 2;
		// Player 1 wins
		if (player1card.value > player2card.value) {
			
			System.out.println(player1.name + " wins " + numCards + " cards" + "!".repeat(numCards));
			
			player1.incrementScore(points);
			// Distribute cards in-play to player 1.
			for (int i = 0; i < numCards; i++) {
				player1.hand.add(cardsInPlay.remove(0));
			} 	
		// Player 2 wins
		} else if (player1card.value < player2card.value) {
			System.out.println(player2.name + " wins " + numCards + " cards" + "!".repeat(numCards));
			player2.incrementScore(points);
			// Distribute cards in-play to player 2.
				for (int i = 0; i < numCards; i++) {
					player2.hand.add(cardsInPlay.remove(0));
				} 	
		}
		//Draw
		else {
			System.out.println("Tie ~ This battle continues!");
			// Make sure both players have enough cards in hand to continue battle.
			if (player1.hand.size() < 2) {
				System.out.println("Except, " + player1.name + " doesn't have enough cards. " + player2.name + " has WON the war!");
			} else if (player2.hand.size() < 2) {
				System.out.println("Except, " + player2.name + " doesn't have enough cards. " + player1.name + " has WON the war!");
			} else {
				// Additional cards up for grabs
				Card bonusCard = player1.flip();
				Card bonusCard2 = player2.flip();
				System.out.println(".".repeat(20));
				System.out.println("-- Additional cards up for grabs --");
				System.out.print("From " + player1.name + ": ");
				bonusCard.describe();
				System.out.print("From " + player2.name + ": ");
				bonusCard2.describe();
				cardsInPlay.add(bonusCard);
				cardsInPlay.add(bonusCard2);
				System.out.println(".".repeat(20));
				// Check a new pair of cards.
				battle(player1, player2);
			}
		}
	}

}
	

