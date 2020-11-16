package in_development;

import bits.BooleanLiteral;
import bits.Disjunction;
import bits.EnhancedProblem;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

//FIXME
public class ProblemDenierDemo21
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(3);
		INaturalNumber N = new NaturalNumber("N");

		IBooleanVariable N0 = N.getBooleanVariable(0);
		IBooleanVariable N1 = N.getBooleanVariable(0);

		IProblem problem = new Disjunction(new NaturalNumberFixer(N, 0), new NaturalNumberFixer(N, 1),
				new NaturalNumberFixer(N, 2));
		System.out.println("Deny " + problem);

		IProblem problemDenial = new ProblemDenier(problem);
		System.out.println(problemDenial);

		IProblem r1 = ((EnhancedProblem) problemDenial).resolve(N0, true);
		IProblem r2 = ((EnhancedProblem) r1).resolve(N1, true);
		System.out.println("Resolved: " + r2);

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