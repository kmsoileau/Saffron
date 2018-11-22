/**
 * An extension of the Problem class which determines the maximum value found in 
 * an INaturalNumberList.
 *
 * To use this class, one passes an INaturalNumberList list and an INaturalNumber 
 * max to the appropriate constructor. The NaturalNumberListMaxer object produced 
 * is a Problem, and one may manipulate it using any of the methods provided by 
 * the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p><tt>Problem p = new NaturalNumberListMaxer(max,list);</code></p>
 *
 * is satisfied, the following truth equations will be satisfied:
 *
 * <p><tt>max >= X</code> for every member <tt>X</code> in <tt>list</code></p>
 * 
 * and
 * 
 * <p><tt>max</code> is a member of <tt>list</code></p>
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/11/23
 * @see Conjunction
 * @see IProblem
 * @see Problem
 * @see INaturalNumber
 * @see NaturalNumberOrderer
 */
package naturalnumberlists;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberOrderer;

public class NaturalNumberListMaxer extends Problem implements IProblem
{
	public NaturalNumberListMaxer(INaturalNumberList list, INaturalNumber max)
			throws Exception
	{
		IProblem[] p = new NaturalNumberOrderer[list.size()];
		for (int i = 0; i < p.length; i++)
			p[i] = new NaturalNumberOrderer(list.getNaturalNumber(i), max);
		IProblem result = new Conjunction(new NaturalNumberListMembership(max,
				list), new Conjunction(p));
		this.setClauses(result.getClauses());
	}
}
