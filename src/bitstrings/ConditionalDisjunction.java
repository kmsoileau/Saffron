package bitstrings;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.ConditionalDisjunctionException;

/**
 * Returns an IProblem that is satisfied if and only if at least one of the
 * following IProblems is satisfied:
 * 
 * <pre>
 * new BitFixer(bitString[0], true) is satisfied and  problemArray[0] is satisfied
 * new BitFixer(bitString[1], true) is satisfied and  problemArray[1] is satisfied
 * new BitFixer(bitString[2], true) is satisfied and  problemArray[2] is satisfied
 * .
 * .
 * .
 * new BitFixer(bitString[n-1], true) is satisfied and  problemArray[n-1] is satisfied
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
 * @since 2018/12/20
 */
public class ConditionalDisjunction extends Problem implements IProblem
{
	public ConditionalDisjunction(IProblem[] problemArray, IBitString bitString)
			throws Exception
	{
		if (problemArray == null)
			throw new ConditionalDisjunctionException(
					"Null IProblem array passed to constructor.");
		if (bitString == null)
			throw new ConditionalDisjunctionException(
					"Null IBitString passed to constructor.");
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			throw new ConditionalDisjunctionException(
					"IProblem array of zero length passed to constructor.");
		if (numberOfProblems == 1)
			this.setClauses(problemArray[0].getClauses());
		if (numberOfProblems != bitString.size())
			throw new ConditionalDisjunctionException(
					"IProblem array and IBooleanVariable array of unequal length passed to constructor.");

		this.setClauses(new bits.ConditionalDisjunction(problemArray, bitString
				.getBVArray()).getClauses());
	}
}