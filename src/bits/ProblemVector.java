package bits;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 24, 2018
 */
public class ProblemVector
{
	private IProblem[] backing;

	public ProblemVector(IProblem[] backing)
	{
		super();
		this.backing = backing;
	}

	public IProblem[] getBacking()
	{
		return backing;
	}
}
