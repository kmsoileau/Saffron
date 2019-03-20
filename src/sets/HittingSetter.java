package sets;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/13
 */
public class HittingSetter extends Problem implements IProblem
{
	public HittingSetter(Set[] C, Set hittingSet) throws Exception
	{
		int problemIndex = 0;
		IProblem[] problemArray = new IProblem[C.length];
		for (int i = 0; i < C.length; i++)
			problemArray[problemIndex++] = new SetMeeter(C[i], hittingSet);
		this.setClauses(new Conjunction(problemArray).getClauses());
	}
}