package in_development;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo18
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b2 = BooleanVariable.getBooleanVariable("b2");

		IProblem problem = Problem.newProblem().and(new BitFixer(b2, true))
				.and(new BitFixer(b2, false));

		System.out.println(problem);

		IProblem problemDenial = new ProblemDenier(problem);
		System.out.println("Problem denial is " + problemDenial);

		IProblemMessage s = problemDenial.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("b2=" + b2.getValue());
		}
		else
			System.out.println("No solution.");
	}
}