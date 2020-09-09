package naturalnumbers;

import java.util.ArrayList;
import java.util.HashMap;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Mapper;
import bits.ProblemPair;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2019
 */
public class NNtoNNMapper extends Problem implements IProblem
{
	public static ArrayList<NaturalNumberPair> createPairs(int[][] data) throws Exception
	{
		HashMap<Integer, INaturalNumber> index = generateIndex(data);

		ArrayList<NaturalNumberPair> pairs = new ArrayList<NaturalNumberPair>();
		for (int i = 0; i < data.length; i++)
		{
			INaturalNumber first = index.get(data[i][0]);
			INaturalNumber second = index.get(data[i][1]);
			NaturalNumberPair np = new NaturalNumberPair(first, second);
			pairs.add(np);
		}

		return pairs;
	}

	public static HashMap<Integer, INaturalNumber> generateIndex(int[][] data) throws Exception
	{
		HashMap<Integer, INaturalNumber> index = new HashMap<Integer, INaturalNumber>();
		for (int i = 0; i < data.length; i++)
		{
			if (index.get(data[i][0]) == null)
				index.put(data[i][0], new NaturalNumber("NN-" + data[i][0], data[i][0]));
			if (index.get(data[i][1]) == null)
				index.put(data[i][1], new NaturalNumber("NN-" + data[i][1], data[i][1]));
		}

		return index;
	};

	private HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> lookup = new HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>>();

	private ArrayList<NaturalNumberPair> pairs;
	private INaturalNumber X = new NaturalNumber();

	private INaturalNumber Y = new NaturalNumber();

	public NNtoNNMapper() throws Exception
	{
		this(new ArrayList<NaturalNumberPair>());
	}

	public NNtoNNMapper(ArrayList<NaturalNumberPair> pairs) throws Exception
	{
		if (pairs == null)
			throw new NNtoNNMapperException("Null NaturalNumberPair[] was passed to a constructor.");

		this.pairs = pairs;

		ProblemPair[] pp = new ProblemPair[pairs.size()];
		for (int i = 0; i < pairs.size(); i++)
		{
			INaturalNumber first = pairs.get(i).getFirst();
			INaturalNumber second = pairs.get(i).getSecond();
			pp[i] = new ProblemPair(new NaturalNumberEqualizer(X, first), new NaturalNumberEqualizer(Y, second));

			HashMap<INaturalNumber, IBooleanVariable> _first = lookup.get(first);
			if (_first == null)
			{
				_first = new HashMap<INaturalNumber, IBooleanVariable>();
				lookup.put(first, _first);
			}
			_first.put(second, BooleanVariable.getBooleanVariable(true));
		}

		this.setClauses(new Mapper(pp).getClauses());
	}

	public NNtoNNMapper(int[][] data) throws Exception
	{
		this(createPairs(data));
	}

	public HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> getLookup()
	{
		return lookup;
	}

	public ArrayList<NaturalNumberPair> getPairs()
	{
		return pairs;
	}

	public INaturalNumber getX()
	{
		return X;
	}

	public INaturalNumber getY()
	{
		return Y;
	}

	public void setLookup(HashMap<INaturalNumber, HashMap<INaturalNumber, IBooleanVariable>> lu)
	{
		this.lookup = lu;
	}

	public void setPairs(ArrayList<NaturalNumberPair> prs)
	{
		this.pairs = prs;
	}

	public void setX(INaturalNumber x)
	{
		X = x;
	}

	public void setY(INaturalNumber y)
	{
		Y = y;
	}
}
