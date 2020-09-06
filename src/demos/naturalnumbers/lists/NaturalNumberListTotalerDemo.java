/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 20, 2019
 */
package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListTotaler;

/**
 * 
 *
 */
public class NaturalNumberListTotalerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberList list = new NaturalNumberList(new INaturalNumber[]
		{ new NaturalNumber(2), new NaturalNumber(1), new NaturalNumber(3),
				new NaturalNumber(2), new NaturalNumber(1),
				new NaturalNumber(5), new NaturalNumber(0),
				new NaturalNumber(2), new NaturalNumber(3) });

		INaturalNumber total = new NaturalNumber();
		IProblemMessage s = new Conjunction(new NaturalNumberListFixer(list),
				new NaturalNumberListTotaler(list, total)).findModel(Problem
				.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("list = " + list);
			System.out.println("total = " + total);
		}
		else
			System.out.println("No solution.");
	}
}
