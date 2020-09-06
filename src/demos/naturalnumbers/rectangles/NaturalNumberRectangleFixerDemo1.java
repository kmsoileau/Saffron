package demos.naturalnumbers.rectangles;

import bits.BooleanLiteral;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

public class NaturalNumberRectangleFixerDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberRectangle rect = new NaturalNumberRectangle("A");

		int base = 3;
		int altitude = 3;

		IProblem problem = new NaturalNumberRectangleFixer(rect, base, altitude);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("rect= " + rect);

		}
		else
			System.out.println("No solution.");
	}
}
