import java.io.*;

/**
 * Created by Matanel on 20/12/2017.
 * This class represents the 'game' in this program. It's managing the game flow,
 * and prints the winner name to the file at the end of it.
 */
public class Game
{
	// Final
	private static final String OUTPUT_FILE = "output.txt";

	// Members
	private Board m_board;
	private MinimaxAlgorithm m_algorithm;

	/**
	 * Constructor. Creates the map and the players.
	 * @param inputFile The name of the input file to read from.
	 */
	public Game(String inputFile)
	{
		m_board = new Board(new Parser(inputFile));
		m_algorithm = new MinimaxAlgorithm(m_board);
	}

	/**
	 * The game flow. After every turn - check if the board is full.
	 * If it's full - print the winner name to the output file.
	 */
	public void play()
	{
		while (!m_board.isFull())
		{
			// Black player turn.
			//TODO - REMOVE!!
			System.out.println("========================= BLACK ============================");
			m_board = new Board(m_algorithm.minimax(m_board.getBoardString(), MinimaxAlgorithm.SEARCH_DEPTH, true).getKey());

			if (!m_board.isFull())
			{
				// White player turn.
				//TODO - REMOVE!!
				System.out.println("========================= WHITE ============================");
				m_board = new Board(m_algorithm.minimax(m_board.getBoardString(), MinimaxAlgorithm.SEARCH_DEPTH, false).getKey());
			}
		}

		// Board is full - the game is over.
		printToOutput(m_board.getWinner());
	}

	/**
	 * Prints the winner-color-char to the output file.
	 * @param winner The winner-color-char to print.
	 */
	public void printToOutput(char winner)
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(OUTPUT_FILE), "utf-8"))) {
			writer.write(winner);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
