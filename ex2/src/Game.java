import java.io.*;

/**
 * Created by Matanel on 20/12/2017.
 */
public class Game
{
	// Final
	private static final String OUTPUT_FILE = "output.txt";

	// Members
	private Map m_map;
	private Player m_blackPlayer;
	private Player m_whitePlayer;
	private Algorithm m_algorithm;

	/**
	 * Constructor. Creates the map and the players.
	 * @param inputFile The name of the input file to read from.
	 */
	public Game(String inputFile)
	{
		m_algorithm = new Minimax();
		m_map = new Map(inputFile);
		m_blackPlayer = new Player(m_algorithm);
		m_whitePlayer = new Player(m_algorithm);
	}

	public void play()
	{
		//TODO
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
}
