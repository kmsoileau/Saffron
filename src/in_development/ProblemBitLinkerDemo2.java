package in_development;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemBitLinkerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		INaturalNumber N = new NaturalNumber();
		IProblem p = new Disjunction(new NaturalNumberFixer(N, 3), new NaturalNumberFixer(N, 13));

		IProblem pl = new Conjunction(p, new BitFixer(b, true));
		IProblem pr = new Conjunction(new ProblemDenier(p), new BitFixer(b, false));
		IProblem result = new Disjunction(pl, pr);

		IProblem testProblem = new Conjunction(result, new NaturalNumberFixer(N, 3));

		System.out.println("p");
		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");

		System.out.println("problemDenier");
		s = new ProblemDenier(p).findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");

		System.out.println("result");
		s = result.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");

		System.out.println("testProblem");
		s = testProblem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b=" + b.getValue());
			System.out.println("N=" + N);
		}
		else
			System.out.println("No solution.");
	}
}
