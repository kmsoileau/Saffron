package showcase.setpacker;

import naturalnumbers.BitStringPacker;
import naturalnumbers.NaturalNumber;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;

/**
 * Let U be a nonempty set, S a family of subsets of U. Do there exist n subsets
 * in S which are pairwise disjoint?
 * 
 * This problem is clearly equivalent to the following: Given a set S of bit
 * strings of length len and c&gt;1, do there exist c bit strings in S such that
 * pairwise ORs of distinct bit strings all evaluate to the zero bit string of
 * length len? The following application solves this equivalent problem.
 */
public class SetPackerDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int c = 3;

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		IBitString[] S = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"),
				new BitString("10000000"), new BitString("00100000") };

		IBitString membership = new BitString(S.length);
		INaturalNumber K = new NaturalNumber(c);

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem p1 = Problem.newProblem();
		for (int i = 0; i < S.length; i++)
			p1 = new Conjunction(p1, new BitStringFixer(S[i]));

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(p1, new BitStringFixer(K),
				new BitStringPacker(S, K, membership));

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Source Data\n-----------");
			for (int i = 0; i < S.length; i++)
				System.out.println(S[i].toBits());
			System.out.println("\nSolution\n--------");
			for (int i = 0; i < S.length; i++)
				if (membership.getBooleanVariable(i).getValue())
					System.out.println(S[i].toBits());
		}
		else
			System.out.println("No solution.");

	}
}
