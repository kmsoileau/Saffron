package naturalnumbers;

import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import naturalnumbers.exceptions.NaturalNumberMapperException;

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
public class NaturalNumberMapper extends Problem implements IProblem
{
	private INaturalNumber domainVariable;
	private INaturalNumber rangeVariable;

	public NaturalNumberMapper(INaturalNumber domainVariable, long[] domainData, INaturalNumber rangeVariable,
			long[] rangeData) throws Exception
	{
		if (domainData == null || rangeData == null)
			throw new NaturalNumberMapperException("A null long[] was passed to a constructor.");
		if (domainVariable == null || rangeVariable == null)
			throw new NaturalNumberMapperException("A null INaturalNumber was passed to a constructor.");
		if (domainData.length == 0 || rangeData.length == 0)
			throw new NaturalNumberMapperException("An array of length zero was passed to a constructor.");
		if (domainData.length != rangeData.length)
			throw new NaturalNumberMapperException("Arrays of unequal length were passed to a constructor.");
		this.domainVariable = domainVariable;
		this.rangeVariable = rangeVariable;
		IProblem[] px = new Problem[domainData.length];
		IProblem[] py = new Problem[rangeData.length];
		for (int i = 0; i < domainData.length; i++)
		{
			px[i] = new NaturalNumberFixer(this.domainVariable, domainData[i]);
			py[i] = new NaturalNumberFixer(this.rangeVariable, rangeData[i]);
		}
		IProblem pcomb = new Mapper(px, py);
		this.setClauses(pcomb.getClauses());
	}

	public NaturalNumberMapper(long[] x, long[] y, INaturalNumber X, INaturalNumber Y, IBooleanVariable[] b)
			throws Exception
	{
		if (x == null || y == null)
			throw new NaturalNumberMapperException("A null long[] was passed to a constructor.");
		if (X == null || Y == null)
			throw new NaturalNumberMapperException("A null INaturalNumber was passed to a constructor.");
		if (b == null)
			throw new NaturalNumberMapperException("A null IBooleanVariable[] was passed to a constructor.");
		if (x.length == 0 || y.length == 0)
			throw new NaturalNumberMapperException("An array of length zero was passed to a constructor.");
		if (x.length != y.length)
			throw new NaturalNumberMapperException("Arrays of unequal length were passed to a constructor.");
		this.domainVariable = X;
		this.rangeVariable = Y;
		IProblem[] px = new Problem[x.length];
		IProblem[] py = new Problem[y.length];
		for (int i = 0; i < x.length; i++)
		{
			px[i] = new NaturalNumberFixer(this.domainVariable, x[i]);
			py[i] = new NaturalNumberFixer(this.rangeVariable, y[i]);
		}
		IProblem pcomb = new Mapper(px, py, b);
		this.setClauses(pcomb.getClauses());
	}

	public INaturalNumber getDomainVariable()
	{
		return domainVariable;
	}

	public INaturalNumber getRangeVariable()
	{
		return rangeVariable;
	}
}
