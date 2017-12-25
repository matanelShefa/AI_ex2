import java.io.*;

/**
 * Created by Matanel on 05/12/2017.
 * This class represents the minimax algorithm.
 */
public class MinimaxAlgorithm
{
	// Members
	Board m_gameBoard;

	public MinimaxAlgorithm(Board gameBoard)
	{
		m_gameBoard = gameBoard;
	}
	public Cell makeAMove(boolean isMaxPlayer)
	{
		//TODO
		return new Cell(new Point(0,0), 'B');
	}
}
