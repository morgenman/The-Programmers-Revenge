
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 2
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 */

public class Player {
	private Deck		player_Deck;
	private Deck		player_Hand;
	private Deck		player_BattleHand;
	private Deck		player_DiscardPile;
	private int			resourcePoints;
	private int			remainingRP;
	private int			score;
	private static int	pnum			= 1;	// Player number. every time a new player is added, this is incremented
	private int			playernum;				// player number per person
	private int			attackpoints	= 0;

	public Player() { // initialization
		playernum = pnum;
		pnum += 1;
		player_Deck = new Deck(16, 8, 6);
		player_Hand = new Deck();
		player_DiscardPile = new Deck();
		score = 30;
		resourcePoints = 0;
		player_BattleHand = new Deck();
		Card temp;
		for (int i = 0; i < 4; i++) {
			do {
				temp = player_Deck.randCard(player_Hand.getDeck());
			}
			while (player_Hand.inDeck(temp));
			player_Hand.addCard(temp);
		}

	}

	public void playcards() { // play a set of cards.
		int i = 0;
		int k = 0;
		int in;
		int[] j = new int[30]; // j is the array that comes from the comma separated list the user inputs.
		if (resourcePoints > 0) {
			System.out.println("Choose cards you wish to play, separated by commas. (press 0 to pass round) ");
			j = Battle.verifyInputList(player_Hand.count()); // get user input
		}
		remainingRP = resourcePoints;

		if (j[0] != 0) {
			while (remainingRP > 0 && i < j.length && j[i] != 0) {
				if (player_Hand.getCard(j[i] - 1).getResourceCost() <= remainingRP) {
					player_BattleHand.addCard(player_Hand.getCard(j[i] - 1));
					remainingRP -= player_Hand.getCard(j[i] - 1).getResourceCost();
					player_Hand.removeCard((player_Hand.getCard(j[i] - 1)));
				}
				else {
					System.out.println("Cannot play card(s), not enough points");
					playcards();
					k = 1;
				}
				i++;
			}
			if (k == 0) System.out.println("\nCards in play: \n" + player_BattleHand);
		}
	}

	public void attack(Player otherPlayer) { // attack other player
		attackpoints = 0;
		for (Card i : player_BattleHand.getDeck()) {
			attackpoints += i.getAttack();
		}
		for (Card j : otherPlayer.player_BattleHand.getDeck()) {
			if (j.getHealth() <= attackpoints && j.getName() != "null") {
				System.out.println("Player " + otherPlayer.playernum + "'s " + j.getName() + " takes " + j.getHealth()
				        + " points of damage and is defeated.");
				attackpoints -= j.getHealth();
				j.setHealth(0);
			}
			else if (j.getHealth() > attackpoints) {
				System.out.println("Player " + otherPlayer.playernum + "'s " + j.getName() + " takes " + attackpoints
				        + " points of damage. It remains in play.");
				j.setHealth(j.getHealth() - attackpoints);
				attackpoints = 0;
			}
		}
		if (attackpoints > 0) {
			otherPlayer.setScore(otherPlayer.getScore() - attackpoints);
			System.out.println("All of Player " + otherPlayer.playernum + "'s cards have been defeated.\n"
			        + attackpoints + " points have been deducted from their score\n");
		}
	}

	public void roundResults(Player otherPlayer) { // print results
		if (attackpoints == 0 && otherPlayer.attackpoints == 0 && (getPlayer_BattleHand().count() > 0) && (otherPlayer
		        .getPlayer_BattleHand().count() > 0)) {
			System.out.println("Draw (one point added to score)");
			otherPlayer.setScore(otherPlayer.getScore() + 1);
			setScore(getScore() + 1);
		}
		else if (attackpoints > 0 && otherPlayer.attackpoints > 0) {
			System.out.println("\nBoth Players were defeated (no points added to score)");
		}
		else if (attackpoints > 0) {
			System.out.println("\nPlayer" + playernum + " won this round. 2 points have been added to their score.");
			setScore(getScore() + 2);
		}
		else if (otherPlayer.attackpoints > 0) {
			System.out.println("\nPlayer" + otherPlayer.playernum
			        + " won this round. 2 points have been added to their score.");
			otherPlayer.setScore(otherPlayer.getScore() + 2);
		}
	}

	public void setScore(int score) {
		if (score < 0) this.score = 0;
		else this.score = score;
	}

	public void recycleDecks() { // recycle decks
		Boolean unique = false;
		for (Card i : player_Deck.getDeck()) {
			if (player_Hand.inDeck(i) == false && player_BattleHand.inDeck(i) == false) unique = true;
		}
		if (unique == false) {
			System.out.println("Deck empty, adding used cards back into deck");
			for (Card i : player_DiscardPile.getDeck()) {
				i.setHealth(i.getorigHealth());
				player_Deck.addCard(i);
				player_DiscardPile.removeCard(i);
			}
		}
	}

	public static void discard(Player player1, Player player2) { // put card into discard pile
		for (Card j : player1.player_BattleHand.getDeck()) {
			if (j.getHealth() == 0 && j.getName() != "null") {
				player1.player_BattleHand.removeCard(j);
				player1.player_DiscardPile.addCard(j);
			}
		}
		for (Card j : player2.player_BattleHand.getDeck()) {
			if (j.getHealth() == 0 && j.getName() != "null") {
				player2.player_BattleHand.removeCard(j);
				player2.player_DiscardPile.addCard(j);
			}
		}
	}

	public void setResourcePoints(int resourcePoints) {
		if (this.resourcePoints < 10) {
			this.resourcePoints = resourcePoints;
		}
	}

	public Deck getPlayer_Deck() {
		return player_Deck;
	}

	public Deck getPlayer_Hand() {
		return player_Hand;
	}

	public Deck getPlayer_BattleHand() {
		return player_BattleHand;
	}

	public int getResourcePoints() {
		return resourcePoints;
	}

	public int getRemainingRP() {
		return remainingRP;
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return "Player " + playernum + " has " + player_Hand.count() + " cards in their hand\n" + resourcePoints
		        + " resource points availiable.\n" + player_BattleHand.count() + " cards in play right now.\n" + score
		        + " points";
	}

	public Boolean playcard(Card card) {
		if ((player_Deck.inDeck(card) == false) && (player_DiscardPile.inDeck(card) == false) && (player_Hand.inDeck(
		        card) == true) && (player_BattleHand.inDeck(card) == false)) {
			player_Hand.removeCard(card);
			player_BattleHand.addCard(card);
			return true;
		}
		else {
			return false;
		}
	}

	public static int results(Player player1, Player player2) { // return results
		if (player1.getScore() == 0 && player2.getScore() == 0) return 0;
		else if (player1.getScore() == 0) return 2;
		else if (player2.getScore() == 0) return 1;
		else return -1;

	}
}
