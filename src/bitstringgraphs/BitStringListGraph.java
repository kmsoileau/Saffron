package bitstringgraphs;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bitstringlists.BitStringList;
import bitstringlists.IBitStringList;
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
public class BitStringListGraph implements IBitStringListGraph
{
	private static int bSGCount;
	private IBitStringList data;
	private String name;

	public BitStringListGraph() throws Exception
	{
		this(1);
	}

	public BitStringListGraph(int numberOfVertices) throws Exception
	{
		this("BitStringGraph-" + bSGCount++, numberOfVertices);
	}

	public BitStringListGraph(String name) throws Exception
	{
		this(name, 1);
	}

	public BitStringListGraph(String name, int numberOfVertices)
			throws Exception
	{
		this.setData(new IBooleanVariable[numberOfVertices][numberOfVertices]);
		this.setName(name);
		for (int i = 0; i < numberOfVertices; i++)
			for (int j = 0; j < numberOfVertices; j++)
				this.setData(i, j, BooleanVariable.getBooleanVariable());
	}

	@Override
	public IBitStringList getData()
	{
		return this.data;
	}

	@Override
	public IBooleanVariable getData(int i, int j) throws Exception
	{
		return this.getData().getBitString(i).getBooleanVariable(j);
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public int getNumberOfVertices()
	{
		return this.getData().size();
	}

	@Override
	public void setData(IBooleanVariable[][] iBooleanVariables)
			throws Exception
	{
		this.data = new BitStringList(iBooleanVariables);

	}

	public void setData(int i, int j, IBooleanVariable booleanVariable)
			throws Exception
	{
		this.getData().getBitString(i).setBooleanVariable(j, booleanVariable);
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
	public String toString()
	{
		String ret = "$" + this.getName() + ":";
		for (int i = 0; i < this.getNumberOfVertices(); i++)
			for (int j = 0; j < this.getNumberOfVertices(); j++)
				try
				{
					if (this.getData().getBitString(i).getBooleanVariable(j)
							.getValue())
						ret += "{" + i + "-" + j + "}";
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		return ret + "$";
	}
}