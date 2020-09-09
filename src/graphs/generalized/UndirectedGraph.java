/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package graphs.generalized;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import naturalnumbers.NaturalNumberPair;

/**
 * 
 *
 */
public class UndirectedGraph extends Graph implements IUndirectedGraph
{
	private static int udGCount;

	public static int getUdGCount()
	{
		return udGCount;
	}

	public static void setUdGCount(int udGCount)
	{
		UndirectedGraph.udGCount = udGCount;
	}

	public UndirectedGraph(int[][] data) throws Exception
	{
		super("UndirectedGeneralizedGraph-" + udGCount++, data);
	}

	public UndirectedGraph(String string, int[][] data) throws Exception
	{
		super(string, data);
	}

	@Override
	public void connect(INaturalNumber i, INaturalNumber j) throws Exception
	{
		monoconnect(i, j);
		monoconnect(j, i);
	}

	@Override
	public void disconnect(INaturalNumber i, INaturalNumber j)
	{
		super.getBacking().getLookup().get(i).get(j).setValue(false);
		super.getBacking().getLookup().get(j).get(i).setValue(false);
	}

	private void monoconnect(INaturalNumber i, INaturalNumber j) throws Exception
	{
		HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> qq = super.getBacking().getLookup();
		HashMap<INaturalNumber, IBooleanVariable> qq2 = qq.get(i);
		if (qq2 == null)
		{
			HashMap<INaturalNumber, IBooleanVariable> vv = new HashMap<INaturalNumber, IBooleanVariable>();
			qq.put(i, vv);
			qq2 = vv;
		}

		IBooleanVariable qq1 = qq2.get(j);
		if (qq1 == null)
		{
			IBooleanVariable ww = BooleanVariable.getBooleanVariable();
			qq2.put(j, ww);
			qq1 = ww;
		}
		qq1.setValue(true);

		this.getPairs().add(new NaturalNumberPair(i, j));
	}

	public ArrayList<INaturalNumber> neighbors(INaturalNumber i)
	{
		ArrayList<INaturalNumber> ret = new ArrayList<INaturalNumber>();
		for (INaturalNumber k : this.getVertices().values())
			if (super.getBacking().getLookup().get(i).get(k).getValue())
			{
				ret.add(k);
			}
		return ret;
	}

	@Override
	public int size()
	{
		return super.size();
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
}
