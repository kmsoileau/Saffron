package graphs.generalized;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import graphs.exceptions.GraphException;
import naturalnumbers.NNtoNNMapper;
import naturalnumbers.NaturalNumberPair;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 4, 2019
 */
public class Graph implements IGraph
{
	private static int gGCount;

	private static String strLabel = "GeneralizedGraph";

	public static void setStrLabel(String strLabel)
	{
		Graph.strLabel = strLabel;
	}

	private NNtoNNMapper backing;

	private String name;

	private HashMap<Integer, INaturalNumber> vertices;

	public Graph(int[][] data) throws Exception
	{
		this("DirectedGeneralizedGraph-" + gGCount++, data);
	}

	public Graph(String name, int[][] data) throws Exception
	{
		if (data == null)
			throw new GraphException("Null int[][] passed to constructor.");

		ArrayList<NaturalNumberPair> pairs = NNtoNNMapper.createPairs(data);

		this.vertices = new HashMap<Integer, INaturalNumber>();
		this.setName(name);
		this.setBacking(new NNtoNNMapper(pairs));
		this.doVertices(pairs);
		this.addMissingEdges();
	}

	void addMissingEdges() throws Exception
	{
		for (INaturalNumber vertex1 : this.getVertices().values())
		{
			for (INaturalNumber vertex2 : this.getVertices().values())
			{
				HashMap<INaturalNumber, IBooleanVariable> aa = this.getBacking().getLookup().get(vertex1);
				if (aa == null)
				{
					this.getBacking().getLookup().put(vertex1, new HashMap<INaturalNumber, IBooleanVariable>());
				}
				aa = this.backing.getLookup().get(vertex1);
				IBooleanVariable bb = aa.get(vertex2);
				if (bb == null)
					aa.put(vertex2, BooleanVariable.getBooleanVariable(false));
			}
		}
	}

	public void disconnect(INaturalNumber i, INaturalNumber j) throws Exception
	{
		this.getLookup().get(i).get(j).setValue(false);
		this.getLookup().get(j).get(i).setValue(false);
	}

	void doVertices(ArrayList<NaturalNumberPair> pairs)
	{
		for (int i = 0; i < pairs.size(); i++)
		{
			INaturalNumber first = pairs.get(i).getFirst();
			INaturalNumber second = pairs.get(i).getSecond();

			if (!this.vertices.containsValue(first))
				this.vertices.put((int) first.getValue(), first);
			if (!this.vertices.containsValue(second))
				this.vertices.put((int) second.getValue(), second);
		}
	}

	@Override
	public NNtoNNMapper getBacking()
	{
		return this.backing;
	}

	@Override
	public HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> getData()
	{
		return this.getLookup();
	}

	/**
	 * 
	 * If i is connected to j, then its connection IBooleanVariable is returned,
	 * else null is returned.
	 */
	@Override
	public IBooleanVariable getData(INaturalNumber i, INaturalNumber j) throws Exception
	{
		return this.getLookup().get(i).get(j);
	}

	@Override
	public HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> getLookup()
	{
		return this.getBacking().getLookup();
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public ArrayList<NaturalNumberPair> getPairs()
	{
		return this.backing.getPairs();
	}

	@Override
	public HashMap<Integer, INaturalNumber> getVertices()
	{
		return this.vertices;
	}

	public INaturalNumber getX()
	{
		return this.backing.getX();
	}

	public INaturalNumber getY()
	{
		return this.backing.getY();
	}

	/**
	 * @param mapper nNtoNNMapper
	 * 
	 */
	public void setBacking(NNtoNNMapper mapper)
	{
		this.backing = mapper;
	}

	/**
	 * 
	 * @param name String
	 */
	public void setName(String name)
	{
		this.name = name;

	}

	@Override
	public int size()
	{
		return this.backing.getPairs().size();
	}

	@Override
	public String toString()
	{
		String ret = strLabel + " [pairs=";
		for (INaturalNumber curr1 : this.vertices.values())
			for (INaturalNumber curr2 : this.vertices.values())
			{
				try
				{
					ret += "\n" + curr1 + "--" + curr2 + "\t" + this.getData(curr1, curr2).getValue();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}

		ret += ",\nname=" + name + "]";

		return ret;
	}
}
