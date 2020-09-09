/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 6, 2019
 */
package graphs;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import graphs.exceptions.GraphException;

/**
 * 
 *
 */
public class Graph implements IGraph
{
	protected static int bSGCount;

	private IBooleanVariable[][] data;

	private String name;

	public Graph() throws Exception
	{
		this(1);
	}

	public Graph(int numberOfVertices) throws Exception
	{
		this("Graph-" + bSGCount++, numberOfVertices);
	}

	public Graph(String name) throws Exception
	{
		this(name, 1);
	}

	public Graph(String name, int numberOfVertices) throws Exception
	{
		if (numberOfVertices < 1)
			throw new GraphException("numberOfVertices less than 1 passed to constructor.");
		this.setData(new IBooleanVariable[numberOfVertices][numberOfVertices]);

		for (int i = 0; i < numberOfVertices; i++)
			for (int j = 0; j < numberOfVertices; j++)
				this.setData(i, j, BooleanVariable.getBooleanVariable());
	}

	@Override
	public boolean areConnected(int i, int j)
	{
		return this.getData(i, j).getValue();
	}

	@Override
	public void connect(int i, int j)
	{
		this.getData(i, j).setValue(true);
		this.getData(j, i).setValue(true);
	}

	@Override
	public void disconnect(int i, int j)
	{
		this.getData(i, j).setValue(false);
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
		return name;
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
	public void setName(String name)
	{
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
		String ret = "{\n";
		for (int i = 0; i < this.size(); i++)
		{
			ret += "\t";
			for (int j = i; j < this.size(); j++)
			{
				if (this.getData(i, j).getValue())
				{
					ret += i + "--" + j + " ";
				}

			}
			ret += "\n";
		}

		return ret + "}";
	}

	public String toTable()
	{
		String ret = "\n   ";
		for (int j = 0; j < this.size(); j++)
		{
			ret += j / 10;
		}
		ret += "\n   ";
		for (int j = 0; j < this.size(); j++)
		{
			ret += j % 10;
		}
		ret += "\n   ";
		for (int j = 0; j < this.size(); j++)
		{
			ret += "-";
		}
		ret += "\n";
		for (int i = 0; i < this.size(); i++)
		{

			ret += i / 10 + "" + (i % 10) + "|";
			for (int j = 0; j < this.size(); j++)
			{
				ret += this.getData(i, j).getValue() ? "1" : "0";
			}
			ret += "\n";
		}

		return ret;
	}

}
