package bits;

import java.util.ArrayList;

import bits.exceptions.MapperException;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 21, 2019
 */
public class Mapper extends Problem implements IProblem
{
	private static IProblem map(IProblem[] p1, IProblem[] p2,
			IBooleanVariable[] b) throws Exception
	{
		if (p1 == null || p2 == null || b == null)
			throw new MapperException(
					"A null IProblem[] was passed to a constructor.");
		if (p1.length == 0 || p2.length == 0)
			throw new MapperException(
					"An IProblem[] of zero length was passed to a constructor.");
		if (p1.length != p2.length)
			throw new MapperException(
					"IProblem[]'s of differing lengths were passed to a constructor.");
		if (b.length != p1.length - 1)
			throw new MapperException(
					"IBooleanVariable[] of improper length was passed to a constructor. Proper length in this case is "
							+ (p1.length - 1) + ".");
		IProblem[] d = new IProblem[p1.length];
		for (int i = 0; i < d.length; i++)
			d[i] = new Conjunction(p1[i], p2[i]);
		IProblem e = new Disjunction(b, d);
		return e;
	}

	private IProblem[] codomain;
	private IProblem[] domain;

	public Mapper(ArrayList<ProblemPair> list) throws Exception
	{
		if (list == null)
			throw new MapperException(
					"Null ProblemPair ArrayList passed to constructor.");

		int len = list.size();

		// if (len == 0)
		// throw new MapperException(
		// "ProblemPair ArrayList of zero size passed to constructor.");

		this.domain = new IProblem[len];
		this.codomain = new IProblem[len];

		for (int i = 0; i < len; i++)
		{
			this.domain[i] = list.get(i).getFirst();
			this.codomain[i] = list.get(i).getSecond();
		}

		this.setClauses(new Mapper(this.domain, this.codomain).getClauses());
	}

	public Mapper(IProblem[] domain, IProblem[] codomain) throws Exception
	{
		if (domain == null || codomain == null)
			throw new MapperException(
					"Null IProblem array passed to constructor.");

		int dlength = domain.length;
		if (dlength != codomain.length)
			throw new MapperException(
					"IProblem arrays of unequal length passed to constructor.");
		// if (dlength == 0)
		// throw new MapperException(
		// "IProblem arrays of zero length passed to constructor.");

		this.domain = domain;
		this.codomain = codomain;

		IProblem[] q = new IProblem[dlength];
		for (int i = 0; i < dlength; i++)
		{
			q[i] = new Conjunction(domain[i], codomain[i]);
		}

		this.setClauses(new Disjunction(q).getClauses());
	}

	public Mapper(IProblem[] p1, IProblem[] p2, IBooleanVariable[] b)
			throws Exception
	{
		IProblem p = map(p1, p2, b);
		if (p != null)
		{
			this.domain = p1;
			this.codomain = p2;
			this.setClauses(p.getClauses());
		}
	}

	/**
	 * @throws Exception
	 *             "Null ProblemPair passed to constructor." or
	 *             "ProblemPair of zero length passed to constructor."
	 * @param array
	 *            A ProblemPair array
	 * 
	 */
	public Mapper(ProblemPair[] array) throws Exception
	{
		if (array == null)
			throw new MapperException(
					"Null ProblemPair array passed to constructor.");

		int len = array.length;

		// if (len == 0)
		// throw new MapperException(
		// "ProblemPair array of zero length passed to constructor.");

		this.domain = new IProblem[len];
		this.codomain = new IProblem[len];

		for (int i = 0; i < len; i++)
		{
			this.domain[i] = array[i].getFirst();
			this.codomain[i] = array[i].getSecond();
		}

		this.setClauses(new Mapper(this.domain, this.codomain).getClauses());
	}

	public Mapper(ProblemPair[] array, IBooleanVariable[] b) throws Exception
	{
		IProblem[] left = new IProblem[array.length];
		IProblem[] right = new IProblem[array.length];
		for (int i = 0; i < array.length; i++)
		{
			left[i] = array[i].getFirst();
			right[i] = array[i].getSecond();
		}
		Mapper qq = new Mapper(left, right, b);

		this.domain = qq.domain;
		this.codomain = qq.codomain;
		this.setClauses(qq.getClauses());
	}

	public Mapper(ProblemTriple[] array) throws Exception
	{
		if (array == null)
			throw new MapperException(
					"Null ProblemTriple array passed to constructor.");

		int len = array.length;

		this.domain = new IProblem[len];
		this.codomain = new IProblem[len];

		for (int i = 0; i < len; i++)
		{
			this.domain[i] = array[i].getFirst();
			this.codomain[i] = new Conjunction(array[i].getSecond(),
					array[i].getThird());
		}
	}

	public IProblem getCodomain(int i)
	{
		return this.codomain[i];
	}

	public IProblem getDomain(int i)
	{
		return this.domain[i];
	}

	@Override
	public int size()
	{
		return this.domain.length;
	}
}
