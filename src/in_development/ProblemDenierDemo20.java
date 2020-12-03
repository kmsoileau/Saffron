package in_development;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;
import naturalnumbers.NaturalNumber;

//FIXME
public class ProblemDenierDemo20
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(3);
		INaturalNumber N = new NaturalNumber();

		// { $NaturalNumber-0_0 }
		// { $NaturalNumber-0_1 }

		IProblem problem = new Problem(new IClause[]
		{ Clause.newClause().orNot(BooleanVariable.getBooleanVariable("NaturalNumber-0_0")),
				Clause.newClause().orNot(BooleanVariable.getBooleanVariable("NaturalNumber-0_1")) });
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