/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 11, 2019
 */
package algebra.monoids.generalized;

import java.util.HashMap;

import algebra.semigroups.generalized.Semigroup;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class Monoid extends Semigroup
{
	private static int monoidCount = 0;

	public Monoid()
	{
		this(
				"Monoid-" + monoidCount++,
				new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Monoid(
			HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		this("Monoid-" + monoidCount++, composition);
	}

	public Monoid(int[][] opTable) throws Exception
	{
		this("Monoid-" + monoidCount++, opTable);
	}

	public Monoid(String name)
	{
		this(
				name,
				new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Monoid(
			String name,
			HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		super(name, composition);
	}

	public Monoid(String name, int[][] opTable) throws Exception
	{
		super(name, opTable);
	}

	@Override
	public HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> getComposition()
	{
		return super.getComposition();
	}

	@Override
	public INaturalNumber getComposition(INaturalNumber e1, INaturalNumber e2)
	{
		return super.getComposition().get(e1).get(e2);
	}

	@Override
	public String toString()
	{
		return "Monoid [composition=" + super.getComposition() + ", order="
				+ super.getOrder() + "]";
	}
}