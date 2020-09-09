package in_development;

import bits.BooleanLiteral;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemDenierDemo10
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(3);
		INaturalNumber N = new NaturalNumber();

		IProblem problem = new Disjunction(new NaturalNumberFixer(N, 0), new NaturalNumberFixer(N, 1),
				new NaturalNumberFixer(N, 2));
		System.out.println(problem);

		IProblem problemDenial = new ProblemDenier(problem);
		System.out.println(problemDenial);

		IProblemMessage s = problemDenial.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("N=" + N);
		}
		else
			System.out.println("No solution.");
	}
}