/**
 * Created by Matanel on 05/12/2017.
 * This interface use to define an algorithm.
 * Every algorithm that claim to fit for this program - has to implement this interface.
 */
public interface Algorithm
{
	// The search method. Searches for the best move at a specific game board.
	void makeAMove();
}
