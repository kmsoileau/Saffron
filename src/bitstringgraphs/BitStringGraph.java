package bitstringgraphs;

import java.util.ArrayList;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import exceptions.bitstrings.BitStringGraphException;

/**
 * A class which represents a graph. An IBitStringGraph is essentially a table
 * of IBooleanVariables. A call to getData(i, j) returns an IBooleanVariable
 * indicating the truth value of the statement
 * "vertex i is connected to vertex j."
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2006/03/31
 */
public class BitStringGraph implements IBitStringGraph
{
	private static int bSGCount;
	private IBooleanVariable[][] data;
	private String name;

	public BitStringGraph() throws Exception
	{
		this(1);
	}

	public BitStringGraph(int numberOfVertices) throws Exception
	{
		this("BitStringGraph-" + bSGCount++, numberOfVertices);
	}

	public BitStringGraph(String name) throws Exception
	{
		this(name, 1);
	}

	public BitStringGraph(String name, int numberOfVertices) throws Exception
	{
		if (numberOfVertices < 1)
			throw new BitStringGraphException(
					"numberOfVertices less than 1 passed to constructor.");
		this.setData(new IBooleanVariable[numberOfVertices][numberOfVertices]);
		this.setName(name);
		for (int i = 0; i < numberOfVertices; i++)
			for (int j = 0; j < numberOfVertices; j++)
				this.setData(i, j, BooleanVariable.getBooleanVariable());
	}

	public boolean areConnected(int i, int j)
	{
		return this.getData(i, j).getValue();
	}

	public void biconnect(int i, int j)
	{
		this.getData(i, j).setValue(true);
		this.getData(j, i).setValue(true);
	}

	public void disconnect(int i, int j)
	{
		this.getData(i, j).setValue(false);
		this.getData(j, i).setValue(false);
	}

	@Override
	public IBooleanVariable[][] getData()
	{
		return this.data;
	}

	@Override
	public IBooleanVariable getData(int i, int j)
	{
		return this.getData()[i][j];
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public void monoconnect(int i, int j)
	{
		this.getData(i, j).setValue(true);
	}

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
	public void setData(IBooleanVariable[][] iBooleanVariables)
	{
		this.data = iBooleanVariables;
	}

	@Override
	public void setData(int i, int j, IBooleanVariable booleanVariable)
	{
		this.getData()[i][j] = booleanVariable;
	}

	@Override
	public void setName(String name) throws BitStringGraphException
	{
		if (name == null)
			throw new BitStringGraphException(
					"Passed null String to setName method.");

		this.name = name;
	}

	@Override
	public int size()
	{
		return this.getData().length;
	}

	@Override
	public String toString()
	{
		String ret = ">" + this.getName() + ":";
		for (int i = 0; i < this.size(); i++)
			for (int j = 0; j < this.size(); j++)
				if (this.getData(i, j).getValue())
					ret += "{" + i + "->" + j + "}";
		return ret + "<";
	}
}