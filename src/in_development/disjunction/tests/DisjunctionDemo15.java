package in_development.disjunction.tests;

import bits.BooleanLiteral;
import bits.Disjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <pre>
 *  DisjunctionDemo1
 *  Copyright (c) 2005 Positronic Software
 * </pre>
 * 
 * @author Kerry Michael Soileau
 * @version 2.0
 */

public class DisjunctionDemo15
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem = new Disjunction(Problem.unsolvableProblem(),
				Problem.unsolvableProblem(), Problem.unsolvableProblem(),
				Problem.unsolvableProblem());

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Solved.");
		}
		else
			System.out.println("No solution.");
	}
}