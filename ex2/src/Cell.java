import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Matanel on 28/11/2017.
 * This class represents a cell in the game. Every cell have a point - it's coordinates,
 * and a type - Black, White or Empty.
 */
public class Cell
{
	// Finals - the cell types.
	public static final char BLACK = 'B';
	public static final char WHITE = 'W';
	public static final char EMPTY = 'E';
	public static final char UNKNOWN_TYPE = ' ';

	// Members
	private Point m_point;
	private char m_type;

	/**
	 * Constructor.
	 * @param point The coordinates of the new cell.
	 * @param type The type of the new cell.
	 */
	public Cell(Point point, char type)
	{
		m_point = point;
		m_type = type;
	}

	@Override
	//Defines a hashCode function for the Cell class.
	public int hashCode() { return m_point.getXVal() * 17 + m_point.getYVal(); }

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
	 * Setter.
	 * @param type The new type of the cell.
	 */
	public void setType(char type) { m_type = type; }
}
