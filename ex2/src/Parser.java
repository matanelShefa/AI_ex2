import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Matanel on 29/11/2017.
 * The Parser that read the input file and pass all the parameters
 * of the world to the map.
 */
public class Parser
{
	// Members
	private int m_size;
	private String m_algorithm;
	private String m_typeString;

	/**
	 * Constructor.
	 * @param inputFile The name of the input file.
	 */
	Parser(String inputFile)
	{
		m_typeString = new String();
		// Create the reader.
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
		{
			String fileCurrentLine;
			m_algorithm = reader.readLine();
			m_size = Integer.parseInt(reader.readLine());

			// Generate the type string.
			while ((fileCurrentLine = reader.readLine()) != null)
			{
				for (int j = 0; j < m_size; j++)
				{
					m_typeString += fileCurrentLine.charAt(j);
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
	public String getTypeString() { return m_typeString; }

	/**
	 * Getter.
	 * @return The algorithm.
	 */
	public String getAlgorithm() { return m_algorithm; }

	/**
	 * Getter.
	 * @return The size of the world.
	 */
	public int getSize() { return m_size; }
}
