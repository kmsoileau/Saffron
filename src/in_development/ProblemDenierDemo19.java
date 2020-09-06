package in_development;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;
import naturalnumbers.NaturalNumber;

public class ProblemDenierDemo19
{
	/*
	 * { BooleanVariable-0 $NaturalNumber-0_0 } .. { BooleanVariable-0
	 * NaturalNumber-0_1 }
	 */

	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b0 = BooleanVariable.getBooleanVariable();
		IBooleanVariable b2 = BooleanVariable.getBooleanVariable();
		INaturalNumber N0 = new NaturalNumber();

		IBooleanVariable N00 = N0.getBooleanVariable(0);
		IBooleanVariable N01 = N0.getBooleanVariable(1);

		IClause c1 = new Clause().or(b0).nor(N00);
		IClause c2 = new Clause().or(b0).or(N01);

		Problem problem = new Problem(new IClause[]
		{ c1, c2 });

		System.out.println(problem);

		IProblem problemDenial = new ProblemDenier(problem);
		System.out.println("Problem denial is " + problemDenial);

		IProblemMessage s = problemDenial.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b2=" + b2.getName());
			System.out.println("N00=" + N00.getName());
			System.out.println("N01=" + N01.getName());
		}
		else
			System.out.println("No solution.");
	}
}