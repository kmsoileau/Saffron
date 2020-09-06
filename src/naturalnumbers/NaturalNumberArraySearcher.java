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
package naturalnumbers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class NaturalNumberArraySearcher extends Problem implements IProblem
{
	public NaturalNumberArraySearcher(INaturalNumber[] testList,
			INaturalNumber test, IBooleanVariable[] result) throws Exception
	{
		IProblem[] p = new IProblem[testList.length];
		for (int i = 0; i < testList.length; i++)
		{
			// if currRef==test then result[i]=true
			// if currRef!=test then result[i]=false
//			p[i] = new Conjunction(new Disjunction(
//					new NaturalNumberUnequalizer(testList[i], test),
//					new BitFixer(result[i], true)), new Disjunction(
//					new NaturalNumberEqualizer(testList[i], test),
//					new BitFixer(result[i], false)));
			p[i] = new Disjunction(
					new Conjunction(
							new NaturalNumberUnequalizer(testList[i], test),
							new BitFixer(result[i], false)), 
					new Conjunction(
							new NaturalNumberEqualizer(testList[i], test),
							new BitFixer(result[i], true)));
		}
		
		this.setClauses(new Conjunction(p).getClauses());
	}
}
