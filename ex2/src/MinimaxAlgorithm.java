import javafx.util.Pair;

import java.io.*;

/**
 * Created by Matanel on 05/12/2017.
 * This class represents the minimax algorithm.
 */
public class MinimaxAlgorithm
{
	// Final
	public static int SEARCH_DEPTH = 3;
	// Members
	Board m_gameBoard;

	public MinimaxAlgorithm(Board gameBoard)
	{
		m_gameBoard = gameBoard;
	}

	// TODO *********************************************************************
	// TODO * check what to do when there is 2 states with the same heuristic. 	*
	// TODO * Now I take the first one - apparently according to the index.		*
	// TODO *********************************************************************
	public Pair<Board, Integer> minimax(Board gameBoard, int searchDepth , boolean isMaxPlayer)
	{
		Pair<Board, Integer> nextMove = null;
		Board nextBoard = null;

		if (searchDepth == 0 || gameBoard.isFull())
		{
			return new Pair<Board, Integer>(gameBoard, gameBoard.getHeuristic());
		}

		int value;
		int bestValue;
		if (isMaxPlayer)
		{
			bestValue = Integer.MIN_VALUE;

			for (Board successor : gameBoard.getSuccessors(Cell.BLACK))
			{
				nextMove = minimax(successor, searchDepth - 1, false);
				value = nextMove.getValue();

				if (value > bestValue)
				{
					bestValue = value;
					nextBoard = nextMove.getKey();
				}
			}
			return new Pair<Board, Integer>(nextBoard, bestValue);
		}

		else
		{
			bestValue = Integer.MAX_VALUE;

			for (Board successor : gameBoard.getSuccessors(Cell.WHITE))
			{
				nextMove = minimax(successor, searchDepth - 1, true);
				value = nextMove.getValue();

				if (value < bestValue)
				{
					bestValue = value;
					nextBoard = nextMove.getKey();
				}
			}
			return new Pair<Board, Integer>(nextBoard, bestValue);
		}
	}
}
