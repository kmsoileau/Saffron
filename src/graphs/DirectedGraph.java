package graphs;

import java.util.ArrayList;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import graphs.exceptions.GraphException;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 31, 2006
 */

public class DirectedGraph extends Graph implements IDirectedGraph
{
	public DirectedGraph(int numberOfVertices) throws Exception
	{
		this("Graph-" + bSGCount++, numberOfVertices);
	}

	public DirectedGraph(String name, int numberOfVertices) throws Exception
	{
		if (numberOfVertices < 1)
			throw new GraphException(
					"numberOfVertices less than 1 passed to constructor.");
		super.setData(new IBooleanVariable[numberOfVertices][numberOfVertices]);
		this.setName(name);
		for (int i = 0; i < numberOfVertices; i++)
			for (int j = 0; j < numberOfVertices; j++)
				super.setData(i, j, BooleanVariable.getBooleanVariable());
	}

	@Override
	public void biconnect(int i, int j)
	{
		super.getData(i, j).setValue(true);
		super.getData(j, i).setValue(true);
	}

	@Override
	public void disconnect(int i, int j)
	{
		super.getData(i, j).setValue(false);
		super.getData(j, i).setValue(false);
	}

	@Override
	public void monoconnect(int i, int j)
	{
		super.getData(i, j).setValue(true);
	}

	@Override
	public ArrayList<Integer> neighbors(int i)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int k = 0; k < this.size(); k++)
			if (this.areConnected(i, k))
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
