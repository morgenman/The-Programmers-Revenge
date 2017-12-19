
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 2
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 * 
 * NOTE: FILE IS IN UNICODE
 * 
 */

import java.util.Scanner;

public class Battle {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		int in;
		// Initialize new players
		Player Player1 = new Player();
		Player Player2 = new Player();
		// Used for the new card generation
		Card newP1;
		Card newP2;
		// print title
		System.out.println(printTitle());
		// one round of showing player's their cards
		System.out.println("Player 1's turn:");
		wait(1); // timeout
		System.out.println("Player2, Look away from the screen!\n");
		wait(4);
		System.out.println("Player1, here is your hand of randomly drawn cards!");
		System.out.println((Player1.getPlayer_Hand()));
		System.out.println(Player1 + "\n");
		cont(); // press enter to continue
		clear();
		System.out.println("Player 2's turn:");
		wait(1);
		System.out.println("Player1, Look away from the screen!\n");
		wait(4);
		System.out.println("Player2, here is your hand of randomly drawn cards!");
		System.out.println((Player2.getPlayer_Hand()));
		System.out.println(Player2 + "\n");
		cont();
		clear();
		do {
			i++;
			// print round indicator
			System.out.println("╔" + Card.rpCh(("Round " + i).length() + 2, "═") + "╗\n" + "║ Round " + i + " ║\n" + "╚"
			        + Card.rpCh(("Round " + i).length() + 2, "═") + "╝" + "\n");
			// add new cards to hand
			newP1 = Player1.getPlayer_Deck().randCard(Player1.getPlayer_Hand().getDeck());
			Player1.getPlayer_Hand().addCard(newP1);
			Player1.getPlayer_Deck().removeCard(newP1);
			newP2 = Player2.getPlayer_Deck().randCard(Player2.getPlayer_Hand().getDeck());
			Player2.getPlayer_Hand().addCard(newP2);
			Player2.getPlayer_Deck().removeCard(newP2);
			// add resource points
			Player1.setResourcePoints(Player1.getResourcePoints() + 1);
			Player2.setResourcePoints(Player2.getResourcePoints() + 1);
			System.out.println("Player 1's turn:");
			wait(1);// timeouts for effect
			System.out.println("Player2, Look away from the screen!\n");
			wait(4);
			System.out.println("Drawing one card from deck.\n");
			wait(1);
			// give player their deck
			System.out.println("Player1, here is your deck: \n");
			System.out.println((Player1.getPlayer_Hand()));
			System.out.println(Player1 + "\n");
			Player1.playcards();
			scan.nextLine(); // needed to make continue work here
			cont();
			clear();// clear the console so other player doesn't see cards
			System.out.println("Player 2's turn:");
			wait(1);
			System.out.println("Player1, Look away from the screen!\n");
			wait(4);
			System.out.println("Drawing one card from deck.\n");
			wait(1);
			System.out.println("Player2, here is your deck: \n");
			System.out.println((Player2.getPlayer_Hand()));
			System.out.println(Player2);
			Player2.playcards();
			scan.nextLine();
			cont();
			clear();
			// show both hands
			System.out.println("Player 1's attack hand: \n" + Player1.getPlayer_BattleHand());
			wait(1);
			System.out.println("Player 2's attack hand: \n" + Player2.getPlayer_BattleHand());
			wait(2);
			// attack
			Player2.attack(Player1);
			wait(1);
			Player1.attack(Player2);
			wait(1);
			Player1.roundResults(Player2); // results
			System.out.println("");
			Player.discard(Player1, Player2);
			System.out.println(Player1 + "\n");
			System.out.println(Player2 + "\n");
			in = 1;
			switch (Player.results(Player1, Player2)) {// did anyone win?
			case -1:
				break;
			case 0:
				in = 0;
				System.out.println("Draw!");
				break;
			case 1:
				in = 0;
				System.out.println("Player 1 won!");
				break;
			case 2:
				in = 0;
				System.out.println("Player 2 won!");
				break;
			}
			if (in != 0) {
				System.out.println("Enter 1 to continue (or 0 to quit)");
				in = verifyInput(1);
			}
			// check to see if main deck has any cards left, recycle deck if so NOTE, cannot handle if no cards in
			// discarded pile
			Player1.recycleDecks();
			Player2.recycleDecks();
			if (in != 0) clear();
		}
		while (in != 0);
		scan.close();
	}

	public static void cont() {
		System.out.println("Press enter to continue: ");
		scan.nextLine();
	}

	public static int verifyInput(int range) {// check input values
		int in; // = range + 1;
		do {
			in = scan.nextInt();
			if (in > range) System.out.println("Input out of range, Try again:");
		}
		while (in > range);

		return in;
	}

	public static String printTitle() {
		return "  _____ _            ____                                                          _      \n |_   _| |__   ___  |  _ \\ _ __ ___   __ _ _ __ __ _ _ __ ___  _ __ ___   ___ _ __( )___  \n   | | | '_ \\ / _ \\ | |_) | '__/ _ \\ / _` | '__/ _` | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|// __| \n   | | | | | |  __/ |  __/| | | (_) | (_| | | | (_| | | | | | | | | | | |  __/ |    \\__ \\ \n   |_|_|_| |_|\\___| |_|   |_|  \\___/ \\__, |_|  \\__,_|_| |_| |_|_| |_| |_|\\___|_|    |___/ \n  |  _ \\ _____   _____ _ __   __ _  _|___/                                                \n  | |_) / _ \\ \\ / / _ \\ '_ \\ / _` |/ _ \\                                                  \n  |  _ <  __/\\ V /  __/ | | | (_| |  __/                                                  \n  |_| \\_\\___| \\_/ \\___|_| |_|\\__, |\\___|                                                  \n                             |___/             \nBy Cole Morgen\n";
	}

	public static int[] verifyInputList(int range) {// take in a comma separated list, check values
		int l; // used as a boolean, too lazy to change
		String in; // user inputted comma separated string
		int j; // temp for number
		String[] inA; // array to hold cardnumbers
		int[] k = new int[30];
		do {
			in = "";
			l = 0;
			in = scan.next();
			if (in == "") in = "0";
			inA = in.split(",");
			for (int i : k)// fill k with 0's
				i = 0;
			for (int i = 0; i < inA.length; i++) {
				j = Integer.parseInt(inA[i]);
				if (j > range) {
					l = 1;
					System.out.println("Invalid Card\nPlease try again: ");
				}
				else k[i] = j;
			}
		}
		while (l == 1);
		return k;

	}

	public static void wait(int time) throws InterruptedException {
		// timeout function
		Thread.sleep(1000 * time);
	}

	public static void clear() {
		System.out.print(
		        "_____________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n_____________________________\n\n");
	}

}
