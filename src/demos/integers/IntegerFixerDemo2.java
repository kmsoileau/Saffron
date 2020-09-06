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

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IInteger;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class IntegerFixerDemo2 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(6);

		IInteger ntgr = new integers.Integer("kerry");

		INaturalNumber nn = ntgr.getAbsValue();
		IBooleanVariable bv = ntgr.getSign();
		IProblem problem = new Conjunction(new NaturalNumberFixer(nn, 56),
				new BitFixer(bv, false));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("nn = " + nn);
			System.out.println("bv = " + bv);
			System.out.println("ntge = " + ntgr);
		}
		else
			System.out.println("No solution.");
	}
}
