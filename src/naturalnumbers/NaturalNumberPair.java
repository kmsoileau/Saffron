package naturalnumbers;

import bits.INaturalNumber;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2019
 */
public class NaturalNumberPair
{
	private INaturalNumber first;
	private INaturalNumber second;

	public NaturalNumberPair() throws Exception
	{
		this(new NaturalNumber(), new NaturalNumber());
	}

	public NaturalNumberPair(INaturalNumber first, INaturalNumber second)
	{
		this.first = first;
		this.second = second;
	}
	
	public INaturalNumber getFirst()
	{
		return first;
	}

	public INaturalNumber getSecond()
	{
		return second;
	}

	@Override
	public String toString()
	{
		return "NaturalNumberPair [first=" + first + ", second=" + second + "]";
	}
}