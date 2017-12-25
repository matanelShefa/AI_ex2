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
	private Player m_blackPlayer;
	private Player m_whitePlayer;
	private MinimaxAlgorithm m_algorithm;
	private boolean m_gameOver;

	/**
	 * Constructor. Creates the map and the players.
	 * @param inputFile The name of the input file to read from.
	 */
	public Game(String inputFile)
	{
		m_board = new Board(new Parser(inputFile));
		m_algorithm = new MinimaxAlgorithm(m_board);
		m_blackPlayer = new Player(true, m_algorithm);
		m_whitePlayer = new Player(false, m_algorithm);
		m_gameOver = false;
	}

	public void play()
	{
		while (!m_gameOver)
		{
			m_blackPlayer.playOneTurn();
			if (!m_gameOver)
			{
				m_whitePlayer.playOneTurn();
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
