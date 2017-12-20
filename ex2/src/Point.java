/**
 * Created by Matanel on 28/11/2017.
 * This class represent the location in the world. The x & y values
 * are the coordinates language of the world.
 */
public class Point
{
	// Members
	private final int m_xVal;
	private final int m_yVal;

	/**
	 * Constructor
	 * @param xVal The x value.
	 * @param yVal The y value.
	 */
	public Point(int xVal, int yVal)
	{
		m_xVal = xVal;
		m_yVal = yVal;
	}

	/**
	 * Getter.
	 * @return The x value.
	 */
	public int getXVal() { return m_xVal; }

	/**
	 * Getter.
	 * @return The y value.
	 */
	public int getYVal() { return m_yVal; }
}
