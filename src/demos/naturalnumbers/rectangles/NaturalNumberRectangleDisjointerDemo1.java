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
import naturalnumbers.rectangles.NaturalNumberRectangleDisjointer;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

public class NaturalNumberRectangleDisjointerDemo1
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval A = new NaturalNumberInterval("A");
		INaturalNumberInterval B = new NaturalNumberInterval("B");

		INaturalNumberInterval C = new NaturalNumberInterval("C");
		INaturalNumberInterval D = new NaturalNumberInterval("D");

		INaturalNumberRectangle rect1 = new NaturalNumberRectangle("rect1");
		INaturalNumberRectangle rect2 = new NaturalNumberRectangle("rect2");

		IProblem[] p = new IProblem[]
		{ new NaturalNumberIntervalFixer(A, 3, 6), new NaturalNumberIntervalFixer(B, 6, 10),
				new NaturalNumberIntervalFixer(C, 7, 8), new NaturalNumberIntervalFixer(D, 4, 8),
				new NaturalNumberRectangleFixer(rect1, A, B), new NaturalNumberRectangleFixer(rect2, C, D),
				new NaturalNumberRectangleDisjointer(rect1, rect2) };

		IProblem problem = new Conjunction(p);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("rect1 = " + rect1);
			System.out.println("rect2 = " + rect2);
		}
		else
			System.out.println("No solution.");
	}
}
