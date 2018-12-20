/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 20, 2018
 */
package showcase.minimumtestset;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class MinimumTestSetterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("101"), new BitString("011"), new BitString("010") };

		int cLength = C.length;

		IBitString includedInTestSet = new BitString(cLength);

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new MinimumTestSetter(C, includedInTestSet));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < cLength; i++)
				if (includedInTestSet.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}
}
