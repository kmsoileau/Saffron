package in_development;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo9
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b1 = BooleanVariable.getBooleanVariable("b1");

		IProblem p = new Disjunction(new BitFixer(b1, true), new BitFixer(b1, false));
		System.out.println(p);

		IProblem p2 = new ProblemDenier(p);
		System.out.println(p2);

		IProblemMessage s = p2.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			// System.out.println("b1=" + b1.getValue());
		}
		else
			System.out.println("No solution.");

		System.out.println(Problem.dump(s.getLiterals()));
	}
}