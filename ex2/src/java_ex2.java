/**
 * Created by Matanel on 28/11/2017.
 * This Program is simulating the "REVERSI" game.
 * It's reads a game board from a file and uses the "Minimax" algorithm
 * in order to search for the best move for every player.
 */
public class java_ex2
{
	// Final
	private static final String INPUT_FILE = "input.txt";

	/**
	 * The main function of the program.
	 * @param args none.
	 */
	public static void main(String [ ] args)
	{
		// Create the game from the file.
		Game game = new Game(INPUT_FILE);
		// Play
		game.play();
	}
}