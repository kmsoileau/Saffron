package naturalnumbers.intervals;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
/**
 * <p>An IProblem which constrains the endpoints of an INaturalNumberInterval 
 * to equal particular values or particular INaturalNumbers.</p>
 * <p>Copyright (c) 2019 Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 * 2019/02/02
 */
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.intervals.exceptions.NaturalNumberIntervalFixerException;

public class NaturalNumberIntervalFixer extends Problem implements IProblem
{
	public NaturalNumberIntervalFixer(INaturalNumberInterval interval,
			INaturalNumber Lower, INaturalNumber Upper) throws Exception
	{
		if (interval == null)
			throw new NaturalNumberIntervalFixerException(
					"A null INaturalNumberInterval was passed to the constructor.");

		INaturalNumber NNLower = interval.getLower();
		INaturalNumber NNUpper = interval.getUpper();

		if (NNLower == null || NNUpper == null)
			throw new NaturalNumberIntervalFixerException(
					"The INaturalNumberInterval passed to the constructor has a null endpoint.");

		this.setClauses(new Conjunction(new NaturalNumberOrderer(Lower, Upper),
				new NaturalNumberEqualizer(NNLower, Lower),
				new NaturalNumberEqualizer(NNUpper, Upper)).getClauses());
	}

	public NaturalNumberIntervalFixer(INaturalNumberInterval interval,
			int lower, int upper) throws Exception
	{
		if (interval == null)
			throw new NaturalNumberIntervalFixerException(
					"A null INaturalNumberInterval was passed to the constructor.");
		if (lower > upper)
			throw new NaturalNumberIntervalFixerException(
					"Error: upper < lower.");

		INaturalNumber NNLower = interval.getLower();
		INaturalNumber NNUpper = interval.getUpper();

		if (NNLower == null || NNUpper == null)
			throw new NaturalNumberIntervalFixerException(
					"The INaturalNumberInterval passed to the constructor has a null endpoint.");

		this.setClauses(new Conjunction(new NaturalNumberFixer(NNLower, lower),
				new NaturalNumberFixer(NNUpper, upper)).getClauses());
	}
}