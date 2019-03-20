package in_development.disjunction.tests;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
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

public class DisjunctionDemo9
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");

		IProblem problem = new Conjunction(new Disjunction(new BitFixer(X,
				false), new BitFixer(Y, false), Problem.unsolvableProblem()),
				new BitFixer(X, true), new BitFixer(Y, true));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(X);
			System.out.println(Y);
		}
		else
			System.out.println("No solution.");
	}
}