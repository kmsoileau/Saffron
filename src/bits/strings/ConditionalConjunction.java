package bits.strings;

import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.ConditionalConjunctionException;

/**
 * Returns an IProblem that is satisfied if and only if all of the following
 * IProblems are satisfied:
 * 
 * <pre>
 * new BitFixer(bitString[0], true) and problemArray[0] are both satisfied, 
 * or new BitFixer(bitString[0], false) is satisfied
 * and
 * new BitFixer(bitString[1], true) and problemArray[1] are both satisfied, 
 * or new BitFixer(bitString[1], false) is satisfied
 * .
 * .
 * .
 * new BitFixer(bitString[n-1], true) and problemArray[n-1] are both satisfied, 
 * or new BitFixer(bitString[n-1], false) is satisfied
 * 
 * where n=the length of problemArray. Roughly speaking, the bits corresponding to bitString
 * determine which members of problemArray go into the Conjunction.
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
public class ConditionalConjunction extends Problem implements IProblem
{
	public ConditionalConjunction(IProblem[] problemArray, IBitString bitString)
			throws Exception
	{
		if (problemArray == null)
			throw new ConditionalConjunctionException(
					"Null IProblem array passed to constructor.");
		if (bitString == null)
			throw new ConditionalConjunctionException(
					"Null IBitString passed to constructor.");
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			throw new ConditionalConjunctionException(
					"IProblem array of zero length passed to constructor.");
		if (numberOfProblems == 1)
			this.setClauses(problemArray[0].getClauses());
		if (numberOfProblems != bitString.size())
			throw new ConditionalConjunctionException(
					"IProblem array and IBitString of unequal length passed to constructor.");

		this.setClauses(new bits.ConditionalConjunction(problemArray, bitString
				.getBVArray()).getClauses());
	}
}