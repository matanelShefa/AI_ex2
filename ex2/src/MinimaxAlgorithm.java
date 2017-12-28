import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

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

	public Pair<String, Integer> minimax(String boardString, int searchDepth , boolean isMaxPlayer)
	{
		Pair<String, Integer> nextMove = null;
		Board gameBoard = new Board(boardString);
		String nextBoard = null;

		//TODO - REMOVE
		//System.out.println("++++++++++++++++++++++ MINIMAX START +++++++++++++++++++++++");

		if (searchDepth == 0 || gameBoard.isFull())
		{
			return new Pair<String, Integer>(boardString, gameBoard.getHeuristic());
		}

		int value;
		int bestValue;
		if (isMaxPlayer)
		{
			bestValue = Integer.MIN_VALUE;
			ArrayList<Board> successorsList = gameBoard.getSuccessors(Cell.BLACK);

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

		else
		{
			bestValue = Integer.MAX_VALUE;

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
