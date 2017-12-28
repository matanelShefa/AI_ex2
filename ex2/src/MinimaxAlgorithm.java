import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by Matanel on 05/12/2017.
 * This class implements the minimax algorithm.
 */
public class MinimaxAlgorithm
{
	// Final
	public static int SEARCH_DEPTH = 3;
	// Members
	private Board m_gameBoard;

	/**
	 * Constructor.
	 * @param gameBoard the game board.
	 */
	public MinimaxAlgorithm(Board gameBoard)
	{
		m_gameBoard = gameBoard;
	}

	/**
	 * Find the best flay for this state of game, using the minimax algorithm.
	 * @param boardString the string represent of the game board.
	 * @param searchDepth the maximum depth to search for.
	 * @param isMaxPlayer the type of the player.
	 * @return The best play for this state of game.
	 */
	public Pair<String, Integer> minimax(String boardString, int searchDepth , boolean isMaxPlayer)
	{
		Pair<String, Integer> nextMove = null;
		Board gameBoard = new Board(boardString);
		String nextBoard = null;

		//TODO - REMOVE
		//System.out.println("++++++++++++++++++++++ MINIMAX START +++++++++++++++++++++++");

		// Stop condition - return the heuristic value for this board.
		if (searchDepth == 0 || gameBoard.isFull())
		{
			return new Pair<String, Integer>(boardString, gameBoard.getHeuristic());
		}

		int value;
		int bestValue;
		// Search for the best play for the maximum player.
		if (isMaxPlayer)
		{
			bestValue = Integer.MIN_VALUE;
			ArrayList<Board> successorsList = gameBoard.getSuccessors(Cell.BLACK);

			// Go over the successor list and find the best successor to go to.
			for (Board successor : successorsList)
			{
				nextMove = minimax(successor.getBoardString(), searchDepth - 1, false);

				value = nextMove.getValue();

				if (value >= bestValue)
				{
					bestValue = value;
					nextBoard = nextMove.getKey();
				}
			}
			//TODO - REMOVE
			System.out.println("BLACK:\n" + new Board(nextBoard));
			//System.out.println("++++++++++++++++++++++ MINIMAX END +++++++++++++++++++++++");
			return new Pair<String, Integer>(nextBoard, bestValue);
		}

		// Search for the best play for the minimum player.
		else
		{
			bestValue = Integer.MAX_VALUE;

			// Go over the successor list and find the best successor to go to.
			for (Board successor : gameBoard.getSuccessors(Cell.WHITE))
			{
				nextMove = minimax(successor.getBoardString(), searchDepth - 1, true);
				value = nextMove.getValue();

				if (value <= bestValue)
				{
					bestValue = value;
					nextBoard = nextMove.getKey();
				}
			}
			//TODO - REMOVE
			System.out.println("WHITE:\n" + new Board(nextBoard));
			//System.out.println("++++++++++++++++++++++ MINIMAX END +++++++++++++++++++++++");
			return new Pair<String, Integer>(nextBoard, bestValue);
		}
	}
}
