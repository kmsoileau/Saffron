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
package algebra.semigroups.generalized;

import java.util.HashMap;

import algebra.magmas.generalized.Magma;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class Semigroup extends Magma
{
	private static int semigroupCount = 0;

	public Semigroup()
	{
		this(
				"Semigroup-" + semigroupCount++,
				new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Semigroup(
			HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		this("Semigroup-" + semigroupCount++, composition);
	}

	public Semigroup(int[][] opTable) throws Exception
	{
		this("Semigroup-" + semigroupCount++, opTable);
	}

	public Semigroup(String name)
	{
		this(
				name,
				new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Semigroup(
			String name,
			HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		super(name, composition);
		
	}

	public Semigroup(String name, int[][] opTable) throws Exception
	{
		super(name,opTable);
		
	}

	@Override
	public String toString()
	{
		return "Semigroup [composition=" + super.getComposition() + ", order=" + super.getOrder()
				+ "]";
	}
}
