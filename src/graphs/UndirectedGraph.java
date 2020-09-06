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

/**
 * 
 *
 */
public class UndirectedGraph extends graphs.Graph implements IUndirectedGraph
{
	public UndirectedGraph(int numberVertices) throws Exception
	{
		super(numberVertices);
//		for (int i = 0; i < numberVertices; i++)
//			this.connect(i, i);
	}

	@Override
	public boolean areConnected(int i, int j)
	{
		return this.getData(i, j).getValue() && this.getData(j, i).getValue();
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
		super.getData(i, j).setValue(false);
		super.getData(j, i).setValue(false);
	}

	@Override
	public String toString()
	{
		String ret = "{\n";
		for (int i = 0; i < this.size(); i++)
		{
			ret += "\t";
			for (int j = 0; j < this.size(); j++)
			{
				if (i > j)
					continue;
				if (this.getData(i, j).getValue())
				{
					ret += i + "--" + j + " ";
				}

			}
			ret += "\n";
		}

		return ret + "}";
	}
}
