import org.omg.CORBA.UNKNOWN;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Board
{
	// An offset array. Uses to create the children list for each node.
	private static final int offsetArray[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
	private static final char UNKNOWN_WINNER = ' ';
	public static final int SIZE = 5;

	// Members
	private String m_boardString;

	/**
	 * Constructor. Creates the board-string.
	 * @param parser The parser that reads the board-string from the file.
	 */
	Board(Parser parser)
	{
		// Get the information from the parser
		m_boardString = parser.getBoardString();
	}

	/**
	 * Constructor. Creates the board-string.
	 * @param boardString The parser that reads the board-string from the file.
	 */
	Board(String boardString)
	{
		// Get the information from the parser
		m_boardString = boardString;
	}

	/**
	 * Create the neighbours list for the cells.
	 * @param cell The cell to create the neighbours list to.
	 * @return The neighbours list.
	 */
	public ArrayList<Cell> addNeighboursList(Cell cell)
	{
		ArrayList<Cell> neighboursList = new ArrayList<>();
		Cell neighbour;
		// Go over the offset array and get all of the neighbours.
		for (int offset[] : offsetArray)
		{
			neighbour = getCell(cell.getXVal() + offset[0], cell.getYVal() + offset[1]);
			// If this is a valid cell - add it to the children list.
			if (neighbour != null)
			{
				neighboursList.add(neighbour);
			}
		}
		return neighboursList;
	}

	/**
	 * Check if going from 'from' to 'to' is a valid move.
	 * @param cell The destination.
	 * @return If this move is a valid move - true , else - false.
	 */
	public boolean isValidCell(Cell cell)
	{
		ArrayList<Cell> neighboursList = addNeighboursList(cell);

		// If the cell isn't empty - return false.
		if (cell.getType() != Cell.EMPTY)
		{
			return false;
		}

		// If the cell had any neighbour - return true.
		for (Cell neighbour : neighboursList)
		{
			if ((neighbour.getType() == Cell.BLACK) || (neighbour.getType() == Cell.WHITE))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter.
	 * @param xVal The x value.
	 * @param yVal The y value.
	 * @return The cell type.
	 */
	public char getType(int xVal, int yVal)
	{
		// Not a valid cell - return unknown type.
		if ((xVal < 0) || (yVal < 0) || (xVal >= SIZE) || (yVal >= SIZE))
		{
			return Cell.UNKNOWN_TYPE;
		}
		return m_boardString.charAt((xVal * SIZE) + yVal);
	}

	/**
	 * Generating a new cell according to it's type & values.
	 * @param xVal The x value.
	 * @param yVal The y value.
	 * @return A new cell.
	 */
	public Cell getCell(int xVal, int yVal)
	{
		// Not a valid cell - return null.
		if ((xVal < 0) || (yVal < 0) || (xVal >= SIZE) || (yVal >= SIZE))
		{
			return null;
		}
		return new Cell(new Point(xVal, yVal), m_boardString.charAt((xVal * SIZE) + yVal));
	}

	public int getHeuristic()
	{
		int black = 0;
		int white = 0;
		int sideBlack = 0;
		int sideWhite = 0;
		char type;

		if (isFull())
		{
			char winner = getWinner();
			if (winner == Cell.BLACK)
			{
				return Integer.MAX_VALUE;
			}
			else
			{
				return Integer.MIN_VALUE;
			}
		}

		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				type = getCell(i, j).getType();

				if (type == Cell.BLACK)
				{
					if ((i == 0) || (i == (SIZE -1)) || (j == 0) || (j ==(SIZE -1)))
					{
						sideBlack++;
					}
					black++;
				}
				else if (type == Cell.WHITE)
				{
					if ((i == 0) || (i == (SIZE -1)) || (j == 0) || (j ==(SIZE -1)))
					{
						sideWhite++;
					}
					white++;
				}
			}
		}
		return ((black - white) + (sideBlack - sideWhite));
	}

	public ArrayList<Board> getSuccessors(char color)
	{
		//TODO - REMOVE
		//System.out.println("Board:\n" + this);
		ArrayList<Board> successorsList = new ArrayList();
		String newBoardString = new String();
		Board successor;

		int index;

		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				index = (i * SIZE) + j;
				if (m_boardString.charAt(index) == Cell.EMPTY && isValidCell(new Cell(new Point(i, j), Cell.EMPTY)))
				{
					newBoardString = m_boardString.substring(0, index) + color + m_boardString.substring(index + 1);
					successorsList.add(new Board(newBoardString).UpdatePlayerMove(color, i, j));
				}
			}
		}
		return successorsList;
	}

	public Board UpdatePlayerMove(char playerColor, int row, int col)
	{
		char opponentColor;
		int index;
		int newRow;
		int newCol;

		if (playerColor == Cell.BLACK)
		{
			opponentColor = Cell.WHITE;
		}
		else
		{
			opponentColor = Cell.BLACK;
		}
		for (int i = 0; i < offsetArray.length; i++)
		{
			if (CheckDirection(row, col, i, playerColor))
			{
				newRow = row + offsetArray[i][1];
				newCol = col + offsetArray[i][0];
				index = (newRow * SIZE) + newCol;

				while (((newRow >= 0) && (newRow < SIZE) && (newCol >= 0) && (newCol < SIZE)) &&
						m_boardString.charAt(index) == opponentColor)
				{
					m_boardString = m_boardString.substring(0, index) + playerColor + m_boardString.substring(index + 1);

					newRow += offsetArray[i][1];
					newCol += offsetArray[i][0];
					index = (newRow * SIZE) + newCol;
				}
			}
		}
		return new Board(m_boardString);
	}

	public boolean CheckDirection(int row, int col, int offsetIndex, char playerColor)
	{
		// Set the new values.
		int newRow = row + offsetArray[offsetIndex][1];
		int newCol = col + offsetArray[offsetIndex][0];

		int index = (newRow * SIZE) + newCol;

		// Check validation.
		if (!((newRow >= 0) && (newRow < SIZE) && (newCol >= 0) && (newCol < SIZE)))
		{
			return false;
		}

		// Set the next square.
		char nextSquare = m_boardString.charAt(index);

		// Not valid square.
		if (nextSquare == playerColor)
		{
			return false;
		}
		if (nextSquare == Cell.EMPTY)
		{
			return false;
		}

		while ((nextSquare != playerColor) && (nextSquare != Cell.EMPTY))
		{
			// Set the new values.
			newRow += offsetArray[offsetIndex][1];
			newCol += offsetArray[offsetIndex][0];
			index = (newRow * SIZE) + newCol;

			// Check validation.
			if (!((newRow >= 0) && (newRow < SIZE) && (newCol >= 0) && (newCol < SIZE)))
			{
				return false;
			}
			// Set the next square.
			nextSquare = m_boardString.charAt(index);
		}
		// Valid square.
		if (nextSquare == playerColor)
		{
			return true;
		}
		// Not valid square.
		return false;
	}

	public boolean isFull()
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			if (m_boardString.charAt(i) == Cell.EMPTY)
			{
				return false;
			}
		}
		return true;
	}

	public char getWinner()
	{
		if (!isFull())
		{
			return UNKNOWN_WINNER;
		}

		char type;
		int black = 0;
		int white = 0;

		for (int i = 0; i < SIZE * SIZE; i++)
		{
			type = m_boardString.charAt(i);

			if (type == Cell.BLACK)
			{
				black++;
			} else
			{
				white++;
			}
		}

		if (black > white)
		{
			return Cell.BLACK;
		}
		if (white > black)
		{
			return Cell.WHITE;
		}
		return UNKNOWN_WINNER;
	}

	// TODO - REMOVE!
	public String toString()
	{
		String board = new String();
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				board += m_boardString.charAt((i * SIZE) + j);
			}
			board += '\n';
		}
		return board;
	}

	/**
	 * Getter.
	 * @return The board string.
	 */
	public String getBoardString() { return m_boardString; }
}
