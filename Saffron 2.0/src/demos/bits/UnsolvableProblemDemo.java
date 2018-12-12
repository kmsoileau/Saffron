package demos.bits;

import java.util.List;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class UnsolvableProblemDemo
{
	public static void main(String[] args) throws Exception
	{
		IProblem unsat = Problem.unsolvableProblem();

		System.out.println(unsat);

		List<?> s = unsat.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
		}
		else
			System.out.println("No solution.");
	}
}