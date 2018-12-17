package demos.bits;

import java.util.List;

import bits.AlternativeDenial;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class AlternativeDenialDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x1 = BooleanVariable.getBooleanVariable("x1");
		IBooleanVariable x2 = BooleanVariable.getBooleanVariable("x2");

		IProblem problem = new AlternativeDenial(new IProblem[]
		{ new BitFixer(x1, true), new BitFixer(x1, false),
				new BitFixer(x2, true), new BitFixer(x2, false) });
		
		System.out.println("The alternative denial was\n" + problem);

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(x1);
			System.out.println(x2);
		}
		else
			System.out.println("There is no solution.");
	}
}