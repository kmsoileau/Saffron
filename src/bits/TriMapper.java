/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 8, 2019
 */
package bits;

import java.util.ArrayList;

import bits.exceptions.MapperException;

/**
 * 
 *
 */
public class TriMapper extends Problem implements IProblem
{
	private static IProblem map(IProblem[] p1, IProblem[] p2, IProblem[] p3, IBooleanVariable[] b) throws Exception
	{
		if (p1 == null || p2 == null || p3 == null || b == null)
			throw new MapperException("A null IProblem[] was passed to a constructor.");
		if (p1.length == 0 || p2.length == 0 || p3.length == 0)
			throw new MapperException("An IProblem[] of zero length was passed to a constructor.");
		if (p1.length != p2.length || p2.length != p3.length)
			throw new MapperException("IProblem[]'s of differing lengths were passed to a constructor.");
		if (b.length != p1.length - 1)
			throw new MapperException(
					"IBooleanVariable[] of improper length was passed to a constructor. Proper length in this case is "
							+ (p1.length - 1) + ".");
		IProblem[] d = new IProblem[p1.length];
		for (int i = 0; i < d.length; i++)
			d[i] = new Conjunction(p1[i], p2[i], p3[i]);
		IProblem e = new Disjunction(b, d);
		return e;
	}

	private IProblem[] codomain;
	private IProblem[] domain;
	private IProblem[] tridomain;

	public TriMapper(ArrayList<ProblemTriplet> list) throws Exception
	{
		if (list == null)
			throw new MapperException("Null ProblemTriplet ArrayList passed to constructor.");

		int len = list.size();

		if (len == 0)
			throw new MapperException("ProblemTriplet ArrayList of zero size passed to constructor.");

		this.domain = new IProblem[len];
		this.codomain = new IProblem[len];
		this.tridomain = new IProblem[len];

		for (int i = 0; i < len; i++)
		{
			this.domain[i] = list.get(i).getFirst();
			this.codomain[i] = list.get(i).getSecond();
			this.tridomain[i] = list.get(i).getThird();
		}

		this.setClauses(new TriMapper(this.domain, this.codomain, this.tridomain).getClauses());
	}

	public TriMapper(IProblem[] domain, IProblem[] codomain, IProblem[] tridomain) throws Exception
	{
		if (domain == null || codomain == null || tridomain == null)
			throw new MapperException("Null IProblem array passed to constructor.");

		int dlength = domain.length;
		if (dlength != codomain.length || codomain.length != tridomain.length)
			throw new MapperException("IProblem arrays of unequal length passed to constructor.");
		if (dlength == 0)
			throw new MapperException("IProblem arrays of zero length passed to constructor.");

		this.domain = domain;
		this.codomain = codomain;
		this.tridomain = tridomain;

		IProblem[] q = new IProblem[dlength];
		for (int i = 0; i < dlength; i++)
		{
			q[i] = new Conjunction(domain[i], codomain[i], tridomain[i]);
		}

		this.setClauses(new Disjunction(q).getClauses());
	}

	public TriMapper(IProblem[] p1, IProblem[] p2, IProblem[] p3, IBooleanVariable[] b) throws Exception
	{
		IProblem p = map(p1, p2, p3, b);
		if (p != null)
		{
			this.domain = p1;
			this.codomain = p2;
			this.tridomain = p3;
			this.setClauses(p.getClauses());
		}
	}

	/**
	 * @throws Exception "Null ProblemTriplet passed to constructor." or
	 *                   "ProblemTriplet of zero length passed to constructor."
	 * @param array A ProblemTriplet array
	 * 
	 */
	public TriMapper(ProblemTriplet[] array) throws Exception
	{
		if (array == null)
			throw new MapperException("Null ProblemTriplet array passed to constructor.");

		int len = array.length;

		if (len == 0)
			throw new MapperException("ProblemTriplet array of zero length passed to constructor.");

		this.domain = new IProblem[len];
		this.codomain = new IProblem[len];
		this.tridomain = new IProblem[len];

		for (int i = 0; i < len; i++)
		{
			this.domain[i] = array[i].getFirst();
			this.codomain[i] = array[i].getSecond();
			this.tridomain[i] = array[i].getThird();
		}

		this.setClauses(new TriMapper(this.domain, this.codomain, this.tridomain).getClauses());
	}

	public TriMapper(ProblemTriplet[] array, IBooleanVariable[] b) throws Exception
	{
		IProblem[] left = new IProblem[array.length];
		IProblem[] right = new IProblem[array.length];
		IProblem[] composition = new IProblem[array.length];

		for (int i = 0; i < array.length; i++)
		{
			left[i] = array[i].getFirst();
			right[i] = array[i].getSecond();
			composition[i] = array[i].getThird();
		}

		TriMapper qq = new TriMapper(left, right, composition, b);

		this.domain = qq.domain;
		this.codomain = qq.codomain;
		this.tridomain = qq.tridomain;

		this.setClauses(qq.getClauses());
	}

	public IProblem getCodomain(int i)
	{
		return this.codomain[i];
	}

	public IProblem getDomain(int i)
	{
		return this.domain[i];
	}

	public IProblem getTridomain(int i)
	{
		return this.tridomain[i];
	}

	@Override
	public int size()
	{
		return this.domain.length;
	}
}
