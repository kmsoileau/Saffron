/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package demos.integers;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IInteger;
import integers.IntegerFixer;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class IntegerFixerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);

		IInteger ntgr = new integers.Integer("kerry");

		IProblem problem = new IntegerFixer(ntgr, -56);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("ntge = " + ntgr);
		}
		else
			System.out.println("No solution.");
	}
}
