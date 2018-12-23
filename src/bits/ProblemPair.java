package bits;

/**
 * A wrapper class which encloses two IProblems.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.3
 * @since 2005/10/07
 * 
 * @see naturalnumberlists.Permuter
 * @see naturalnumbers.Mapper
 */
public class ProblemPair
{
	private IProblem first;
	private IProblem second;

	public ProblemPair(IProblem first, IProblem second)
	{
		this.first = first;
		this.second = second;
	}

	public IProblem getFirst()
	{
		return first;
	}

	public IProblem getSecond()
	{
		return second;
	}

	public void setFirst(IProblem first)
	{
		this.first = first;
	}

	public void setSecond(IProblem second)
	{
		this.second = second;
	}
}
