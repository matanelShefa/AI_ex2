/**
 * Created by Matanel on 20/12/2017.
 */
public class Player
{
	// Members
	Algorithm m_algorithm;

	public Player(Algorithm algorithm)
	{
		m_algorithm = algorithm;
	}

	public void playOneTurn()
	{
		m_algorithm.makeAMove();
	}
}
