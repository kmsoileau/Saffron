/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 8, 2019
 */
package bits;

/**
 * 
 *
 */
public class ProblemTriplet
{
	private IProblem first;
	private IProblem second;
	private IProblem third;

	public ProblemTriplet(IProblem first, IProblem second, IProblem third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public IProblem getFirst()
	{
		return first;
	}

	public IProblem getSecond()
	{
		return second;
	}

	public IProblem getThird()
	{
		return third;
	}

	public void setFirst(IProblem first)
	{
		this.first = first;
	}

	public void setSecond(IProblem second)
	{
		this.second = second;
	}

	public void setThird(IProblem third)
	{
		this.third = third;
	}

	@Override
	public String toString()
	{
		return "ProblemTriplet [first=" + first + ", second=" + second + ", third=" + third + "]";
	}
}
