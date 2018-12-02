package naturalnumberintervals;

/**
 * <p>An IProblem which constrains an INaturalNumber to a
 * particular value.</p>
 * <p>Copyright (c) 2005 Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 * 2009/05/21
 */

import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.exceptions.NaturalNumberFixerException;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberIntervalFixer extends Problem implements IProblem
{
	public NaturalNumberIntervalFixer(INaturalNumberInterval b, long lower,
			long upper) throws Exception
	{
		if (b == null)
			throw new NaturalNumberFixerException(
					"A null INaturalNumberInterval was passed to the constructor.");
		if (lower > upper)
			throw new NaturalNumberFixerException("Error: upper<lower.");
		INaturalNumber NNLower = b.getLower();
		INaturalNumber NNUpper = b.getUpper();
		if (NNLower == null || NNUpper == null)
			throw new NaturalNumberFixerException(
					"The INaturalNumberInterval passed to the constructor has a null endpoint.");

		this.setClauses(new Conjunction(new NaturalNumberFixer(NNLower, lower),
				new NaturalNumberFixer(NNUpper, upper)).getClauses());
	}
}