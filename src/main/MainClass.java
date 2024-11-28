package main;

public class MainClass {
	// Modify variables to set the desired mechanics and behavior of the game
	public static int framesPerSecond = 144; // Normally 60 or 144
	public static double gameWindowScale = 1; // Normally between 1 or 2
	public static int numberOfLevels = 3; // How many levels are going to be in your game?
	public static double gravity = 0.04; // Normally between 0.04 and 0.08

	// Player related variables. Health is always 100
	public static double jumpHeight = 2.5; // How high the player can jump? Normally between 2 and 3
	public static int playerAttackDamage = 50; // How much damage the player does? Normally 10
	public static double playerWalkSpeed = 1.0; // How fast the player can walk? Normally 1.0

	// Enemy related variables. Health is always 100
	public static boolean areThereEnemies = false; // Are there enemies in the game? If not, it'll be considered parkour
	public static int enemyDamage = 10; // How much damage the enemy does? Normally 10

	public static void main(String[] args) {
		new Game();
	}

}
