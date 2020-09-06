package integers.intervals;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import integers.IInteger;
import integers.IntegerEqualizer;
import integers.IntegerFixer;
import integers.IntegerOrderer;
import integers.intervals.exceptions.IntegerIntervalFixerException;

/**
 * <p>
 * An IProblem which constrains the endpoints of an IIntegerInterval to equal
 * particular values or particular IIntegers.
 * </p>
 * <p>
 * Copyright (c) 2019 Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0 2019/02/02
 */

public class IntegerIntervalFixer extends Problem implements IProblem
{
	public IntegerIntervalFixer(IIntegerInterval interval, IInteger Lower,
			IInteger Upper) throws Exception
	{
		if (interval == null)
			throw new IntegerIntervalFixerException(
					"A null IIntegerInterval was passed to the constructor.");

		IInteger IILower = interval.getLower();
		IInteger IIUpper = interval.getUpper();

		if (IILower == null || IIUpper == null)
			throw new IntegerIntervalFixerException(
					"The IIntegerInterval passed to the constructor has a null endpoint.");

		this.setClauses(new Conjunction(new IntegerOrderer(Lower, Upper),
				new IntegerEqualizer(IILower, Lower), new IntegerEqualizer(
						IIUpper, Upper)).getClauses());
	}

	public IntegerIntervalFixer(IIntegerInterval interval, int lower, int upper)
			throws Exception
	{
		if (interval == null)
			throw new IntegerIntervalFixerException(
					"A null IIntegerInterval was passed to the constructor.");
		if (lower > upper)
			throw new IntegerIntervalFixerException("Error: upper < lower.");

		IInteger IILower = interval.getLower();
		IInteger IIUpper = interval.getUpper();

		if (IILower == null || IIUpper == null)
			throw new IntegerIntervalFixerException(
					"The IIntegerInterval passed to the constructor has a null endpoint.");

		this.setClauses(new Conjunction(new IntegerFixer(IILower, lower),
				new IntegerFixer(IIUpper, upper)).getClauses());
	}
}