package showcase.subsetsum;

import java.util.ArrayList;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;

/**
 * Finds a subset of integers that sums to <code>desiredSum,</code> which in
 * this example is 171.
 */
public class SubsetSumDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		int B = 171;

		Integer[] A = new Integer[]
		{ 99, 92, 93, 85, 35, 27, 9, 2, 88, 90, 90, 1, 83, 45, 63, 83, 33, 21 };

		int maxSum = 0;
		for (int i = 0; i < A.length; i++)
			maxSum += A[i];

		/**
		 * Set globals:
		 */

		NaturalNumber.setLargestNaturalNumber(maxSum);

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumber[] dataNNarry = new INaturalNumber[A.length];
		IBitString membership = new BitString(A.length);
		IProblem[] r = new IProblem[A.length];
		INaturalNumber W = new NaturalNumber();
		for (int i = 0; i < A.length; i++)
		{
			dataNNarry[i] = new NaturalNumber();
		}

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		for (int i = 0; i < A.length; i++)
		{
			r[i] = new NaturalNumberFixer(dataNNarry[i], A[i]);
		}
		IProblem rArray = new Conjunction(r);
		IProblem fixW = new NaturalNumberFixer(W, B);
		IProblem cAdd = new ConditionalAdder(dataNNarry, membership, W);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(rArray, fixW, cAdd);

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			ArrayList<Integer> Aprime = new ArrayList<Integer>();
			for (int i = 0; i < membership.size() - 1; i++)
				if (membership.getBooleanVariable(i).getValue())
					Aprime.add(A[i]);
			for (int i = 0; i < Aprime.size() - 1; i++)
				System.out.print(Aprime.get(i) + " + ");
			System.out.print(Aprime.get(Aprime.size() - 1) + " = " + B);
		}
		else
			System.out.println("No solution.");
	}
}