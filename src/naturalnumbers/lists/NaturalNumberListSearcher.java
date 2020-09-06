/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 22, 2019
 */
package naturalnumbers.lists;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberUnequalizer;

/**
 * 
 *
 */
public class NaturalNumberListSearcher extends Problem implements IProblem
{
	public NaturalNumberListSearcher(INaturalNumberList testList,
			INaturalNumber test, IBooleanVariable[] result) throws Exception
	{
		IProblem[] p = new IProblem[testList.size()];
		for (int i = 0; i < testList.size(); i++)
		{
			// if currRef==test then result[i]=true
			// if currRef!=test then result[i]=false
			p[i] = new Conjunction(new Disjunction(
					new NaturalNumberUnequalizer(testList.getNaturalNumber(i),
							test), new BitFixer(result[i], true)),
					new Disjunction(new NaturalNumberEqualizer(testList
							.getNaturalNumber(i), test), new BitFixer(
							result[i], false)));
		}
		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}
}
