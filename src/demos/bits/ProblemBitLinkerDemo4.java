package demos.bits;

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
import in_development.ProblemBitLinker;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class ProblemBitLinkerDemo4
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");

		INaturalNumber N = new NaturalNumber();
		IProblem p = new Disjunction(new NaturalNumberFixer(N, 3), new NaturalNumberFixer(N, 13));

		IProblem testProblem = new Conjunction(new ProblemBitLinker(p, b), new BitFixer(b, true));

		System.out.println(testProblem);

		IProblemMessage s = testProblem.findModel(Problem.defaultSolver());

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
