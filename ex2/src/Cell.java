import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Matanel on 28/11/2017.
 * This class represents a cell in the map. Every cell have a point - it's coordinates,
 * and a type. Each cell type have different cost.
 */
public class Cell
{
	// Finals - the cell types.
	public static final char START = 'S';
	public static final char ROAD = 'R';
	public static final char GOAL = 'G';
	public static final char DESERT = 'D';
	public static final char HILL = 'H';
	public static final char WATER = 'W';
	public static final char UNKNOWN_TYPE = ' ';

	// Members
	private Point m_point;
	private char m_type;
	private int m_cost;
	private Cell m_parent;
	private int m_creationTime;
	private int m_heuristic;

	/**
	 * Constructor.
	 * @param point The coordinates of the new cell.
	 * @param type The type of the new cell.
	 */
	public Cell(Point point, char type)
	{
		m_point = point;
		m_type = type;
		m_cost = typeToCost(m_type);
	}

	@Override
	//Defines a hashCode function for the Cell class.
	public int hashCode()
	{
		return m_point.getXVal() * 17 + m_point.getYVal();
	}

	@Override
	/**
	 * Defines a comparator function for the Cell class.
	 * @param other The object to compare to.
	 * @return If the objects identical - true. Else - false.
	 */

	public boolean equals(Object other)
	{
		return (((Cell)other).getXVal() == m_point.getXVal()) &&
				(((Cell)other).getYVal() == m_point.getYVal());
	}

	/**
	 * Getter.
	 * @return The x value.
	 */
	public int getXVal() { return m_point.getXVal(); }

	/**
	 * Getter.
	 * @return The y value.
	 */
	public int getYVal() { return m_point.getYVal(); }

	/**
	 * Getter.
	 * @return The cell type.
	 */
	public char getType() { return m_type; }

	/**
	 * Getter.
	 * @return The cell cost.
	 */
	public int getCost() { return m_cost; }

	/**
	 * Getter.
	 * @return The cell parent.
	 */
	public Cell getParent() { return m_parent; }

	/**
	 * Getter.
	 * @return The cell creation time.
	 */
	public int getCreationTime() { return m_creationTime; }

	/**
	 * Getter.
	 * @return The cell heuristic.
	 */
	public int getHeuristic() { return m_heuristic; }

	/**
	 * Return the real cost for each cell type.
	 * @param type The type.
	 * @return The cost.
	 */
	public int typeToCost(char type)
	{
		switch (type)
		{
			case ROAD:
				return 1;
			case DESERT:
				return 3;
			case HILL:
				return 10;
			case GOAL:
				return 0;
			default:
				return 0;
		}
	}

	/**
	 * Setter.
	 * @param cost The new cost.
	 */
	public void setCost(int cost) { m_cost = cost; }

	/**
	 * Setter.
	 * @param parent The new parent cell.
	 */
	public void setParent(Cell parent) { m_parent = parent; }

	/**
	 * Setter.
	 * @param heuristicValue The new heuristic value.
	 */
	public void setHeuristic(int heuristicValue) { m_heuristic = heuristicValue; }

	/**
	 * Setter.
	 * @param creationTime The creation time of the cell.
	 */
	public void setCreationTime(int creationTime) { m_creationTime = creationTime; }
}
