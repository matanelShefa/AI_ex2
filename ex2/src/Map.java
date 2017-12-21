import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Map //TODO - Make it a 'Board' class instead of a 'Map'.
{
	// An offset array. Uses to create the children list for each node.
	private static final int offsetArray[][] = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

	// Members
	private int m_size;
	private String m_typeString;

	/**
	 * Constructor. Creates the type-string from the file.
	 * @param inputFile The name of the input file to read from.
	 */
	Map(String inputFile)
	{
		// Get the information from the parser
		Parser parser = new Parser(inputFile);
		m_size = parser.getSize();
		m_typeString = parser.getTypeString();
	}

	/**
	 * Create the children list for the cells.
	 * @param cell The cell to create the children list to.
	 * @return The children list.
	 */
	public ArrayList<Cell> addChildrenList(Cell cell)
	{
		ArrayList<Cell> childrenList = new ArrayList<>();
		Cell child;
		// Go over the offset array and get all of the option to move to.
		for (int offset[] : offsetArray)
		{
			child = getCell(cell.getXVal() + offset[0], cell.getYVal() + offset[1]);
			// If this is a valid cell - add it to the children list.
			if (child != null)
			{
				childrenList.add(child);
			}
		}
		return childrenList;
	}

	/**
	 * Check if going from 'from' to 'to' is a valid move.
	 * @param from The source.
	 * @param to The destination.
	 * @return If this move is a valid move - true , else - false.
	 */
	public boolean isValidMove(Cell from, Cell to)
	{
		int fromX = from.getXVal();
		int fromY = from.getYVal();
		int toX = to.getXVal();
		int toY = to.getYVal();

		// If the 'to' cell is water - return false.
		if (to.getType() != Cell.EMPTY)
		{
			return false;
		}
/*
		// Check the move according to the coordinates. If it's diagonally to water - return false.
		if ((fromX - toX == 1))
		{
			if (fromY - toY == 1)
			{
				return (getType(fromX - 1, fromY) != Cell.WATER) && (getType(fromX, fromY - 1) != Cell.WATER);
			}
			else if (fromY - toY == -1)
			{
				return (getType(fromX - 1, fromY) != Cell.WATER) && (getType(fromX, fromY + 1) != Cell.WATER);
			}
		}

		if (fromX - toX == -1)
		{
			if (fromY - toY == -1)
			{
				return (getType(fromX + 1, fromY) != Cell.WATER) && (getType(fromX, fromY + 1) != Cell.WATER);
			}
			else if ((fromY - toY == 1))
			{
				return (getType(fromX + 1, fromY) != Cell.WATER) && (getType(fromX, fromY - 1) != Cell.WATER);
			}
		}
		*/
		return true;
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
		if ((xVal < 0) || (yVal < 0) || (xVal >= m_size) || (yVal >= m_size))
		{
			return Cell.UNKNOWN_TYPE;
		}
		return m_typeString.charAt((xVal * m_size) + yVal);
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
		if ((xVal < 0) || (yVal < 0) || (xVal >= m_size) || (yVal >= m_size))
		{
			return null;
		}
		return new Cell(new Point(xVal, yVal), m_typeString.charAt((xVal * m_size) + yVal));
	}

	/**
	 * Getter.
	 * @return The map size.
	 */
	public int getSize() { return m_size; }
}
