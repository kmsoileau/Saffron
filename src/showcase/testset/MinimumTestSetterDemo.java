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
package showcase.testset;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.MinimumTestSetter;

/**
 * Let U be a nonempty set, S a family of subsets of U. Does there exist a sub
 * family S' of K subsets in S which have the following property: For any
 * distinct elements x and y, for every subset T in S', we have either x is in T
 * and y is not, or vice versa.
 * 
 * This problem is clearly equivalent to the following: Given a set S of bit
 * strings of equal length, do there exist K bit strings in S such that for any
 * distinct bit positions i and j, for every subset T in S' we have T[i] !=
 * T[j]? The following application solves this equivalent problem.
 */
public class MinimumTestSetterDemo
{
	private static String cstring(int i, int j, char c)
	{
		char[] s = new char[j + 1];
		for (int k = 0; k <= j; k++)
			if (k == i || k == j)
				s[k] = c;
			else
				s[k] = ' ';
		return new String(s);
	}

	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int K = 3;

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		IBitString[] S = new IBitString[]
		{ new BitString("01000"), new BitString("01011"), new BitString("10100"), new BitString("01100"),
				new BitString("11010"), new BitString("10010"), new BitString("01010") };

		int cLength = S.length;
		int cSize = S[0].size();

		IBitString includedInTestSet = new BitString(cLength);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem bsf = new BitStringFixer(S);
		IProblem mts = new MinimumTestSetter(S, K, includedInTestSet);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(bsf, mts);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("PROBLEM");
			for (int i = 0; i < cLength; i++)
				System.out.println("C[" + i + "]=" + S[i].toBits());

			System.out.println("\nSOLUTION");
			for (int i = 0; i < cLength; i++)
				if (includedInTestSet.getBooleanVariable(i).getValue())
					System.out.println("C[" + i + "]=" + S[i].toBits());

			System.out.println("\nVERIFICATION");
			for (int i = 0; i < cSize; i++)
				for (int j = i + 1; j < cSize; j++)
				{
					if (i == j)
						continue;
					for (int k = 0; k < cLength; k++)
					{
						boolean ci = S[k].getBooleanVariable(i).getValue();
						boolean cj = S[k].getBooleanVariable(j).getValue();

						boolean v1 = ci && !cj;
						boolean v2 = !ci && cj;
						boolean v3 = v1 || v2;
						if (v3)
						{

							System.out.println(S[k].toBits());

							System.out.println(cstring(i, j, '^'));
							break;
						}
					}
				}
		}
		else
			System.out.println("No solution.");

	}
}