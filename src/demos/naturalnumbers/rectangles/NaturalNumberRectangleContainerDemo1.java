package demos.naturalnumbers.rectangles;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleContainer;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

public class NaturalNumberRectangleContainerDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberRectangle L = new NaturalNumberRectangle("L");
		INaturalNumberRectangle R = new NaturalNumberRectangle("R");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberRectangleFixer(L, 2, 4, 2, 2),
				new NaturalNumberRectangleFixer(R, 1, 2, 3, 5),
				new NaturalNumberRectangleContainer(L, R) };

		IProblem problem = new Conjunction(p);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("L = " + L);
			System.out.println("R = " + R);
		}
		else
			System.out.println("No solution.");
	}
}
