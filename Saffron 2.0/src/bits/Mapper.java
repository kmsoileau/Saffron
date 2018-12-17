package bits;

import exceptions.bits.MapperException;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

public class Mapper extends Problem implements IProblem
{
	private IProblem[] codomain;
	private IProblem[] domain;

	public Mapper(IProblem[] domain, IProblem[] codomain) throws Exception
	{
		if (domain.length == 0 || codomain.length == 0)
			throw new MapperException(
					"IProblem array of zero length passed to constructor.");
		this.domain = domain;
		this.codomain = codomain;

		IProblem[] q = new IProblem[this.domain.length];
		for (int i = 0; i < this.domain.length; i++)
		{
			q[i] = new Conjunction(domain[i], codomain[i]);
		}
		this.setClauses(new Disjunction(q).getClauses());
	}

	public IProblem getCodomain(int i)
	{
		return codomain[i];
	}

	public IProblem getDomain(int i)
	{
		return domain[i];
	}
}
