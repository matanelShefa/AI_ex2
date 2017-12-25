/**
 * Created by Matanel on 20/12/2017.
 */
public class Player
{
	// Members
	private MinimaxAlgorithm m_algorithm;
	private boolean m_isMaxPlayer;

	public Player(boolean isMaxPlayer, MinimaxAlgorithm algorithm)
	{
		m_algorithm = algorithm;
		m_isMaxPlayer = isMaxPlayer;
	}

	public Cell playOneTurn()
	{
		return m_algorithm.makeAMove(m_isMaxPlayer);
	}
}
