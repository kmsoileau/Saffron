package demos.bits;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
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

public class DisjunctionDemo4
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] b = new IBooleanVariable[2];
		for (int i = 0; i < b.length; i++)
			b[i] = BooleanVariable.getBooleanVariable("b" + i);

		INaturalNumber A = new NaturalNumber("AC");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");

		IProblem problem = new Conjunction(new NaturalNumberFixer(A, 1), new NaturalNumberFixer(B, 1),
				new NaturalNumberFixer(C, 1), new Disjunction(b, new IProblem[]
				{ new NaturalNumberFixer(A, 1), new NaturalNumberFixer(B, 1), new NaturalNumberFixer(C, 1) }));

		/**
		 * X Y 0 0 0 No solution. 0 0 1 F F C 0 1 0 F T B 0 1 1 F F C 1 0 0 T T A 1 0 1
		 * F F C 1 1 0 F T B 1 1 1 F F C
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < b.length; i++)
				System.out.println(b[i].getName() + "=" + b[i].getValue());

			int hit = -1;
			for (int i = 0; i < b.length; i++)
			{
				if (b[i].getValue())
				{
					hit = i;
					break;
				}
			}
			if (hit == -1)
				hit = b.length;

			System.out.println("hit= " + hit);

			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
		}
		else
			System.out.println("No solution.");
	}
}