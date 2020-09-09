package graphs.generalized;

import java.util.ArrayList;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberPair;
import naturalnumbers.NaturalNumberUnequalizer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 10, 2019
 */

public class DirectedGraph extends Graph implements IDirectedGraph
{
	private static int dgGCount;

	public DirectedGraph(int[][] data) throws Exception
	{
		super("DirectedGeneralizedGraph-" + dgGCount++, data);
	}

	public DirectedGraph(String name, int[][] data) throws Exception
	{
		super(name, data);
	}

	public IProblem areBiconnected(INaturalNumber startVertex, INaturalNumber endVertex) throws Exception
	{
		return new Conjunction(areMonoConnected(startVertex, endVertex), areMonoConnected(endVertex, startVertex));
	}

	public IProblem areMonoConnected(INaturalNumber startVertex, INaturalNumber endVertex) throws Exception
	{
		ArrayList<NaturalNumberPair> pairsList = this.getBacking().getPairs();

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < this.getBacking().size(); i++)
		{
			NaturalNumberPair plisti = pairsList.get(i);
			p.add(new Conjunction(new NaturalNumberEqualizer(startVertex, plisti.getFirst()),
					new NaturalNumberEqualizer(endVertex, plisti.getSecond())));
		}
		return new Disjunction(p);
	}

	public IProblem areNotBiconnected(INaturalNumber startVertex, INaturalNumber endVertex) throws Exception
	{
		return new Disjunction(areNotMonoconnected(startVertex, endVertex),
				areNotMonoconnected(endVertex, startVertex));
	}

	public IProblem areNotMonoconnected(INaturalNumber startVertex, INaturalNumber endVertex) throws Exception
	{
		ArrayList<NaturalNumberPair> pairsList = this.getBacking().getPairs();

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < this.getBacking().size(); i++)
		{
			NaturalNumberPair plisti = pairsList.get(i);
			p.add(new Disjunction(new NaturalNumberUnequalizer(startVertex, plisti.getFirst()),
					new NaturalNumberUnequalizer(endVertex, plisti.getSecond())));
		}
		return new Conjunction(p);
	}

	public void biconnect(INaturalNumber i, INaturalNumber j) throws Exception
	{
		monoconnect(i, j);
		monoconnect(j, i);
	}

	@Override
	public void monoconnect(INaturalNumber i, INaturalNumber j) throws Exception
	{
		this.getLookup().get(i).get(j).setValue(true);
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
