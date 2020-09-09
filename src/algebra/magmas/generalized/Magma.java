/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
 */
package algebra.magmas.generalized;

import java.util.HashMap;

import bits.INaturalNumber;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class Magma
{
	private static int magmaCount = 0;
	private HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition;
	// private String name;
	private int order;

	public Magma()
	{
		this("Magma-" + magmaCount++, new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Magma(HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{
		this("Magma-" + magmaCount++, composition);
	}

	public Magma(int[][] opTable) throws Exception
	{
		this("Magma-" + magmaCount++, opTable);
	}

	public Magma(String name)
	{
		this(name, new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>());
	}

	public Magma(String name, HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> composition)
	{

		// this.name = name;
		this.composition = composition;
		this.order = this.composition.size();
	}

	public Magma(String name, int[][] opTable) throws Exception
	{
		int len = opTable.length;
		int order = (int) Math.sqrt(len);

		INaturalNumber[] e = new INaturalNumber[order];
		for (int i = 0; i < order; i++)
			e[i] = new NaturalNumber(i);

		HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> comp = new HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>>();
		for (int i = 0; i < order; i++)
			comp.put(e[i], new HashMap<INaturalNumber, INaturalNumber>());

		for (int n = 0; n < len; n++)
		{
			int[] curr = opTable[n];
			comp.get(e[curr[0]]).put(e[curr[1]], e[curr[2]]);
		}

		Magma mnd = new Magma(comp);

		// this.name = name;
		this.composition = mnd.getComposition();
		this.order = mnd.getOrder();
	}

	public HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> getComposition()
	{
		return composition;
	}

	public INaturalNumber getComposition(INaturalNumber e1, INaturalNumber e2)
	{
		return composition.get(e1).get(e2);
	}

	public int getOrder()
	{
		return order;
	}

	public int[][] toOpTable()
	{
		int index = 0;

		int[][] ret = new int[this.order * this.order][];
		for (INaturalNumber inn : composition.keySet())
		{
			HashMap<INaturalNumber, INaturalNumber> qq = composition.get(inn);
			for (INaturalNumber jnn : qq.keySet())
			{
				INaturalNumber rr = qq.get(jnn);
				ret[index] = new int[]
				{ (int) inn.getValue(), (int) jnn.getValue(), (int) rr.getValue() };
				index++;
			}
		}

		return ret;
	}

	@Override
	public String toString()
	{
		return "Magma [composition=" + composition + ", order=" + order + "]";
	}
}
