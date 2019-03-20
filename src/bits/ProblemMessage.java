/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 7, 2019
 */
package bits;

import java.util.ArrayList;

public class ProblemMessage implements IProblemMessage
{
	private ArrayList<IBooleanLiteral> literals;
	private int status;

	public ProblemMessage(int status, ArrayList<IBooleanLiteral> literals)
	{
		super();
		this.status = status;
		this.literals = literals;
	}

	public ArrayList<IBooleanLiteral> getLiterals()
	{
		return literals;
	}

	public int getStatus()
	{
		return status;
	}

	public void setLiterals(ArrayList<IBooleanLiteral> literals)
	{
		this.literals = literals;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
