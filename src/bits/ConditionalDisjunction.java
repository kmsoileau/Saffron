package bits;

import bits.exceptions.ConditionalDisjunctionException;

/**
 * Returns an IProblem that is satisfied if and only if at least one of the
 * following IProblems is satisfied:
 * 
 * <pre>
 * new BitFixer(booleanVariableArray[0], true) is satisfied and  problemArray[0] is satisfied
 * new BitFixer(booleanVariableArray[1], true) is satisfied and  problemArray[1] is satisfied
 * new BitFixer(booleanVariableArray[2], true) is satisfied and  problemArray[2] is satisfied
 * .
 * .
 * .
 * new BitFixer(booleanVariableArray[n-1], true) is satisfied and  problemArray[n-1] is satisfied
 * 
 * where n=the length of problemArray.
 * </pre>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Nov 4, 2018
 */
public class ConditionalDisjunction extends Problem implements IProblem
{
	public ConditionalDisjunction(IProblem[] problemArray, IBitString bitString) throws Exception
	{
		this(problemArray, bitString.toBooleanVariableArray());
	}

	public ConditionalDisjunction(IProblem[] problemArray, IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null)
			throw new ConditionalDisjunctionException("Null IProblem array passed to constructor.");
		if (booleanVariableArray == null)
			throw new ConditionalDisjunctionException("Null IBooleanVariable array passed to constructor.");
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			throw new ConditionalDisjunctionException("IProblem array of zero length passed to constructor.");
		if (numberOfProblems == 1)
			this.setClauses(problemArray[0].getClauses());
		if (numberOfProblems != booleanVariableArray.length)
			throw new ConditionalDisjunctionException(
					"IProblem array and IBooleanVariable array of unequal length passed to constructor.");

		IProblem[] subProblems = new IProblem[numberOfProblems];
		for (int i = 0; i < numberOfProblems; i++)
			subProblems[i] = new Conjunction(new BitFixer(booleanVariableArray[i], true), problemArray[i]);

		this.setClauses(new Disjunction(subProblems).getClauses());
	}
}