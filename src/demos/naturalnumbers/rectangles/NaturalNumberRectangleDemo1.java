package demos.naturalnumbers.rectangles;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalFixer;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

public class NaturalNumberRectangleDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval A = new NaturalNumberInterval("A");
		INaturalNumberInterval B = new NaturalNumberInterval("B");

		INaturalNumberRectangle rect = new NaturalNumberRectangle("rect");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberIntervalFixer(A, 3, 6), new NaturalNumberIntervalFixer(B, 6, 10),
				new NaturalNumberRectangleFixer(rect, A, B) };

		IProblem problem = new Conjunction(p);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("rect1 = " + rect);

		}
		else
			System.out.println("No solution.");
	}
}
