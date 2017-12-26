import java.io.*;

/**
 * Created by Matanel on 20/12/2017.
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

	public void play()
	{
		while (!m_board.isFull())
		{
			// Black player turn
			m_algorithm.minimax(m_board, MinimaxAlgorithm.SEARCH_DEPTH, true);

			if (!m_board.isFull())
			{
				// White player turn
				m_algorithm.minimax(m_board, MinimaxAlgorithm.SEARCH_DEPTH, false);
			}
		}
	}

	/**
	 * Print the winner-color-char to the output file.
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

	public Board getBoard() { return m_board; }
}
