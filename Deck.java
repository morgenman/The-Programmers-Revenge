
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 2
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 */

public class Deck {
	private Card[]	thisDeck;
	private int		currentPos;

	public Deck() { // initialize null card
		currentPos = 0;
		thisDeck = new Card[30];
		for (int i = 0; i < thisDeck.length; i++) {
			thisDeck[i] = new Card(0);
		}
	}

	public Deck(int common, int uncommon, int rare) { // initialize normal card
		currentPos = 0;
		thisDeck = new Card[common + uncommon + rare];
		for (int i = 0; i < common; i++, currentPos++) {
			thisDeck[currentPos] = new Card(1);
		}
		for (int i = 0; i < uncommon; i++, currentPos++) {
			thisDeck[currentPos] = new Card(2);
		}
		for (int i = 0; i < rare; i++, currentPos++) {
			thisDeck[currentPos] = new Card(3);
		}

	}

	public Card[] getDeck() { // return deck
		return thisDeck;
	}

	public void addCard(Card add) { // add card
		for (int i = 0; i < thisDeck.length; i++) {
			if (thisDeck[i].getName() == "null") {
				thisDeck[i] = add;
				break;
			}
			else continue;
		}
	}

	public Card randCard(Card[] hand) { // adds a random card, making sure it's not already in the Card array
		Card rand;
		boolean isUnique;
		do {
			isUnique = true;
			rand = thisDeck[(int) (Math.random() * thisDeck.length)];
			for (int j = 0; j < hand.length; j++) {
				if (hand[j] == rand) isUnique = false;
				if (rand.getName() == "null") isUnique = false;
			}

		}
		while (isUnique == false);
		return rand;
	}

	public int count() { // counts number of non null cards
		int i = 0;
		for (Card j : thisDeck) {
			if (j.getName() != "null") i++;
		}
		return i;
	}

	public int getTotalAttack() { // returns total attack in deck
		int attack = 0;
		for (Card i : thisDeck)
			attack += i.getAttack();
		return attack;
	}

	public void removeCard(Card remove) { // remove a card
		for (int i = 0; i < thisDeck.length; i++) {
			if (thisDeck[i] == remove) {
				thisDeck[i] = new Card(0);
				break;
			}
		}
	}

	public Card getCard(int index) {// return a card
		return thisDeck[index];
	}

	public boolean inDeck(Card card) { // see if a card's in the deck
		boolean indeck = false;
		for (int i = 0; i < thisDeck.length; i++) {
			if (thisDeck[i] == card) {
				indeck = true;
				break;
			}
		}
		return indeck;
	}

	public String toString() { // Nice card output
		String a = "";
		int count = count();
		for (int i = 0; i <= count; i += 3) {
			if (thisDeck[i].getName() != "null") {
				String[] Row = { "", "", "", "", "", "", "", "", "", "" };
				if (thisDeck[i].getName() != "null") Row = (thisDeck[i].niceCards(Row, i + 1));
				if (thisDeck[i + 1].getName() != "null") Row = (thisDeck[i + 1].niceCards(Row, i + 2));
				if (thisDeck[i + 2].getName() != "null") Row = (thisDeck[i + 2].niceCards(Row, i + 3));
				a += (Row[6] + "\n");
				a += (Row[5] + "\n");
				a += (Row[7] + "\n");
				a += (Row[0] + "\n");
				a += (Row[1] + "\n");
				a += (Row[2] + "\n");
				a += (Row[3] + "\n");
				a += (Row[4] + "\n");
				a += (Row[9] + "\n");
				a += (Row[5] + "\n");
				a += (Row[8] + "\n");
				a += ("\n");
			}

		}
		return a;
	}
}
