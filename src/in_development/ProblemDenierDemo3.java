package in_development;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo3
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b0 = BooleanVariable.getBooleanVariable();
		IBooleanVariable a = BooleanVariable.getBooleanVariable("a");
		IBooleanVariable b = BooleanVariable.getBooleanVariable("b");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");
		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");

		IProblem p = new Problem(new IClause[]
		{ new Clause().or(b0).or(a), new Clause().or(b0).or(b), new Clause().or(b0).or(c), new Clause().orNot(b0).or(w),
				new Clause().orNot(b0).or(x) });

		System.out.println(p);

		IProblem problem = new ProblemDenier(p);
		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("b0 = " + b0.getName());
			System.out.println("a = " + a.getName());
			System.out.println("b = " + b.getName());
			System.out.println("c = " + c.getName());
			System.out.println("w = " + w.getName());
			System.out.println("x = " + x.getName());
		}
		else
			System.out.println("No solution.");

		System.out.println(Problem.dump(s.getLiterals()));
	}
}