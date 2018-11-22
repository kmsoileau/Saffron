package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class UnsatisfiedProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] bva = new IBooleanVariable[3];
		bva[0] = BooleanVariable.getBooleanVariable("foo30");
		bva[1] = BooleanVariable.getBooleanVariable("foo11");
		bva[2] = BooleanVariable.getBooleanVariable("foo23");
		IProblem problem = Problem.randomProblem(bva, 10);

		System.out.println(problem);

		IProblem unsat = ((Problem) problem).unsatisfiedProblem();

		System.out.println(unsat);

		List<?> s = unsat.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(bva[0]);
			System.out.println(bva[1]);
			System.out.println(bva[2]);
		}
		else
			System.out.println("No solution.");
	}
}