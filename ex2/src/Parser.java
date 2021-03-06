import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Matanel on 29/11/2017.
 * The Parser that reads the input file and passes the board of the game
 * to the 'Game' class.
 */
public class Parser
{
	// Members
	private String m_boardString;

	/**
	 * Constructor. Reads the file.
	 * @param inputFile The name of the input file.
	 */
	Parser(String inputFile)
	{
		m_boardString = new String();
		// Create the reader.
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
		{
			String fileCurrentLine;
			while ((fileCurrentLine = reader.readLine()) != null)
			{
				// Generate the board string.
				for (int i = 0; i < (Board.SIZE) ; i++)
				{
					m_boardString += fileCurrentLine.charAt(i);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Getter.
	 * @return The type string
	 */
	public String getBoardString() { return m_boardString; }
}
