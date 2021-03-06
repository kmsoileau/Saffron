/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 13, 2019
 */
package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.TestSetter;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.IBitStringList;

public class TestSetterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList C = new BitStringList(new IBitString[]
		{ new BitString("01000"), new BitString("01011"), new BitString("10100"), new BitString("01100"),
				new BitString("11010"), new BitString("10010"), new BitString("01010") });

		int cLength = C.size();
		int cSize = C.getBitString(0).size();

		IBitString includedInTestSet = new BitString(cLength);

		IProblem problem = new Conjunction(new BitStringListFixer(C), new TestSetter(C.toArray(), includedInTestSet));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			for (int i = 0; i < cLength; i++)
				if (includedInTestSet.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + C.getBitString(i).toBits());
			// (C[k][i] && !C[k][j]) || (!C[k][i] && C[k][j])
			System.out.println("\ni\tj\tTest\t");
			System.out.println("--------------------");
			for (int i = 0; i < cSize; i++)
				for (int j = i + 1; j < cSize; j++)
				{
					if (i == j)
						continue;
					for (int k = 0; k < cLength; k++)
					{
						boolean ci = C.getBitString(k).getBooleanVariable(i).getValue();
						boolean cj = C.getBitString(k).getBooleanVariable(j).getValue();

						boolean v1 = ci && !cj;
						boolean v2 = !ci && cj;
						boolean v3 = v1 || v2;
						if (v3)
						{
							System.out.println(i + "\t" + j + "\t" + C.getBitString(k).toBits());
							break;
						}
					}
				}
		}
		else
			System.out.println("No solution.");
	}
}