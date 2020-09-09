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
package algebra.groups.generalized;

import java.util.HashMap;

import algebra.monoids.generalized.Monoid;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class Group extends Monoid
{
	private static int groupCount = 0;

	public Group()
	{
		this("Group-" + groupCount++, new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Group(HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		this("Group-" + groupCount++, composition);
	}

	public Group(int[][] opTable) throws Exception
	{
		this("Group-" + groupCount++, opTable);
	}

	public Group(String name)
	{
		this(name, new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Group(String name, HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		super(name, composition);
	}

	public Group(String name, int[][] opTable) throws Exception
	{
		super(name, opTable);
	}

	@Override
	public String toString()
	{
		return "Group [composition=" + super.getComposition() + ", order=" + super.getOrder() + "]";
	}
}
