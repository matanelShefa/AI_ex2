/**
 * Created by Matanel on 28/11/2017.
 * This Program reads from a file, and gets an algorithm name and a board.
 * The program search for the goal using the algorithm specified in the file.
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
		//System.out.println(game.getBoard()); //TODO - remove!
		game.play();
	}
}