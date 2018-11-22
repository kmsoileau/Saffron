package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x_1 = BooleanVariable.getBooleanVariable("x_1");
		IBooleanVariable x_2 = BooleanVariable.getBooleanVariable("x_2");
		IBooleanVariable x_3 = BooleanVariable.getBooleanVariable("x_3");
		IBooleanVariable x_4 = BooleanVariable.getBooleanVariable("x_4");

		IProblem ip = new Problem(new IClause[]
		{ new Clause().or(x_1).orNot(x_2).or(x_3),
				new Clause().orNot(x_2).orNot(x_3).or(x_4),
				new Clause().or(x_1).orNot(x_3) });

		System.out.println("The given IProblem was \n" + ip + ".");
		System.out
				.println("The denial of this IProblem is the following Problem:");
		IProblem problem = new ProblemDenier(ip);
		System.out.println(problem);

		List<?> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("x_1 = " + x_1.getValue());
			System.out.println("x_2 = " + x_2.getValue());
			System.out.println("x_3 = " + x_3.getValue());
			System.out.println("x_4 = " + x_4.getValue());
		}
		else
			System.out.println("No solution.");
	}
}