
/*
 * Dylan Cole Morgen
 * dmorgen2
 * Lab TA: NOAH HELTERBRAND
 * Project 2
 * Wed 12:30 Lab
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Random;

public class Card {
	private String	name;
	private int		rarity;
	private int		attack;
	private int		health;
	private int		healthnow;

	public void setHealth(int health) {
		this.healthnow = health;
	}

	private int	resourceCost;
	Random		rand	= new Random();

	private void genName() { // generate name

		String[] firstName = { "Kindly", "Evil", "Intimidating", "Demonic", "Ivory", "Blackhearted", "Ferocious",
		        "Pompous", "Noble", "Fabulous", "Flirtatious", "Alabaster", "Divine", "Lacerated", "Holy", "Flaming",
		        "Timid", "Brave", "Rash", "Terrifying", "Ancient", "Legendary", "Sinful", "Crafty", "Weak", "Ordinary",
		        "Elementary", "Elemental", "Amorphous", "Tiny", "Malnourished", "Serious", "Brave", "Lonely",
		        "Terrified", "Yellow-Bellied", "Starving", "Fearless", "Apprentice", "Master", "Orphan", "Rich",
		        "Charismatic", "Sneaking", "Pathetic", "Miserable", "Undaunted", "Spritely", "Enthusiastic",
		        "Skillful" };
		String[] lastName = { "Minion", "Fiend", "Djinn", "Warrior", "Sentinel", "Official", "Farmer", "Mutant",
		        "Hell-Spawn", "Demon", "King", "Mage", "Wizard", "Pixie", "Fairie", "Elf", "Orc", "Prince", "Bard",
		        "Legend", "Spriggan", "Brigand", "Thief", "Loner", "Cleric", "Hobgoblin", "Student", "Master", "Lord",
		        "Lycanthrope", "Malthrope", "Gargoyle", "Kender", "Halfling", "Druid", "Augury", "Criminal",
		        "Hive-Mind", "Wraith", "Ghoul", "Charlatan", "Merchant", "Draconian", "Dwarf", "Khajiit" };
		name = firstName[rand.nextInt(firstName.length)] + " " + lastName[rand.nextInt(lastName.length)];
	}

	public Card(int rarity) {
		genName();
		this.rarity = rarity;
		switch (rarity) {
		case 0:
			health = 0;
			attack = 0;
			resourceCost = 0;
			name = "null";
			break;
		case 1:
			health = rand.nextInt(6) + 1;
			attack = 8 - health;
			resourceCost = rand.nextInt(2) + 1;
			break;
		case 2:
			health = rand.nextInt(10) + 1;
			attack = 12 - health;
			resourceCost = rand.nextInt(3) + 2;
			break;
		case 3:
			health = rand.nextInt(14) + 1;
			attack = 16 - health;
			resourceCost = rand.nextInt(3) + 4;
			break;
		}
		healthnow = health;
	}

	public int getHealth() {
		return healthnow;
	}

	public int getorigHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public String getName() {
		return name;
	}

	private static String rpWsp(int n) {
		return new String(new char[n]).replace("\0", " ");
	}

	public static String rpCh(int n, String x) {
		return new String(new char[n]).replace("\0", x);
	}

	public String[] niceCards(String[] row, int num) { // return cards in a string array
		String rare = "";
		String filler = " ";
		String star = "   ";
		switch (rarity) {
		case 1:
			rare = "Common";
			filler = "░";
			star = "  *";
			break;
		case 2:
			rare = "Uncommon";
			filler = "▒";
			star = " **";
			break;
		case 3:
			rare = "Rare";
			filler = "▓";
			star = "***";
			break;
		default:
			rare = "null";
		}
		String wsp = " ";
		row[0] += "║" + filler + rpWsp(4) + name + rpWsp(29 - name.length()) + filler + "║ ";
		row[1] += "║" + filler + rpWsp(4) + rare + rpWsp(29 - rare.length()) + filler + "║ ";
		row[2] += "║" + filler + rpWsp(4) + "HP: " + Integer.toString(healthnow) + "/" + Integer.toString(health)
		        + rpWsp(29 - 4 - 1 - Integer.toString(health).length() - Integer.toString(healthnow).length()) + filler
		        + "║ ";
		row[3] += "║" + filler + rpWsp(4) + "Attack: " + Integer.toString(attack) + rpWsp(29 - 8 - Integer.toString(
		        attack).length()) + filler + "║ ";
		row[4] += "║" + filler + rpWsp(4) + "Cost: " + Integer.toString(resourceCost) + rpWsp(29 - 6 - Integer.toString(
		        resourceCost).length()) + filler + "║ ";
		row[5] += "║" + rpCh(35, filler) + "║ ";
		row[6] += "╔" + rpCh(35, "═") + "╗ ";
		row[7] += "║" + filler + rpWsp(30 - Integer.toString(num).length()) + "(" + num + ") " + filler + "║ ";
		row[8] += "╚" + rpCh(35, "═") + "╝ ";
		row[9] += "║" + filler + rpWsp(29) + star + " " + filler + "║ ";
		return row;
	}

	public String toString() { // not used, good to have though
		String rare = "";
		switch (rarity) {
		case 1:
			rare = "Common";
			break;
		case 2:
			rare = "Uncommon";
			break;
		case 3:
			rare = "Rare";
			break;
		default:
			rare = "null";
		}
		return (name + "\n" + rare + "\nHealth: " + health + "\nAttack: " + attack + "\nResource Cost: " + resourceCost
		        + "\n\n");
	}

	public int getResourceCost() {
		return resourceCost;
	}
}
