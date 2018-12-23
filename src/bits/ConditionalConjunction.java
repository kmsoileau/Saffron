package bits;

import exceptions.bits.ConditionalConjunctionException;

/**
 * Returns an IProblem that is satisfied if and only if all of the following
 * IProblems are satisfied:
 * 
 * <pre>
 * new BitFixer(booleanVariableArray[0], true) and problemArray[0] are both satisfied, 
 * or new BitFixer(booleanVariableArray[0], false) is satisfied
 * and
 * new BitFixer(booleanVariableArray[1], true) and problemArray[1] are both satisfied, 
 * or new BitFixer(booleanVariableArray[1], false) is satisfied
 * .
 * .
 * .
 * new BitFixer(booleanVariableArray[n-1], true) and problemArray[n-1] are both satisfied, 
 * or new BitFixer(booleanVariableArray[n-1], false) is satisfied
 * 
 * where n=the length of problemArray. Roughly speaking, the bits corresponding to booleanVariableArray
 * determine which members of problemArray go into the Conjunction.
 * </pre>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/04
 */
public class ConditionalConjunction extends Problem implements IProblem
{
	public ConditionalConjunction(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null)
			throw new ConditionalConjunctionException(
					"Null IProblem array passed to constructor.");
		if (booleanVariableArray == null)
			throw new ConditionalConjunctionException(
					"Null IBooleanVariable array passed to constructor.");
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			throw new ConditionalConjunctionException(
					"IProblem array of zero length passed to constructor.");
		if (numberOfProblems == 1)
			this.setClauses(problemArray[0].getClauses());
		if (numberOfProblems != booleanVariableArray.length)
			throw new ConditionalConjunctionException(
					"IProblem array and IBooleanVariable array of unequal length passed to constructor.");

		IProblem[] subProblems = new IProblem[numberOfProblems];
		for (int i = 0; i < numberOfProblems; i++)
			subProblems[i] = new Disjunction(problemArray[i],
					Problem.trivialProblem(), booleanVariableArray[i]);

		this.setClauses(new Conjunction(subProblems).getClauses());
	}
}