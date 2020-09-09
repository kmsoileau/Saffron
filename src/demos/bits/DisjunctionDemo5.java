package demos.bits;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * <pre>
 *  DisjunctionDemo1
 *  Copyright (c) 2005 Positronic Software
 * </pre>
 * 
 * @author Kerry Michael Soileau
 * @version 2.0
 */

public class DisjunctionDemo5
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");

		INaturalNumber hit = new NaturalNumber("hit");

		IProblem problem = new Conjunction(new NaturalNumberFixer(A, 1), new NaturalNumberFixer(B, 1),
				new NaturalNumberFixer(C, 1), new naturalnumbers.Disjunction(new IProblem[]
				{ new NaturalNumberFixer(A, 1), new NaturalNumberFixer(B, 1), new NaturalNumberFixer(C, 1) }, hit));

		/**
		 * X Y 0 0 0 No solution. 0 0 1 F F C 0 1 0 F T B 0 1 1 F F C 1 0 0 T T A 1 0 1
		 * F F C 1 1 0 F T B 1 1 1 F F C
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(hit);

			System.out.println("hit= " + hit);

			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
		}
		else
			System.out.println("No solution.");
	}
}