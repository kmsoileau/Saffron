package demos.naturalnumbers.intervals;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.intervals.INaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberInterval;
import naturalnumbers.intervals.NaturalNumberIntervalDisjointSelector;

public class NaturalNumberIntervalDisjointSelectorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumberInterval I1 = new NaturalNumberInterval("I1");
		INaturalNumberInterval I2 = new NaturalNumberInterval("I2");
		INaturalNumberInterval I3 = new NaturalNumberInterval("I3");
		INaturalNumberInterval I4 = new NaturalNumberInterval("I4");

		INaturalNumberInterval[] I = new INaturalNumberInterval[]
		{ I1, I2, I3, I4 };
		IBooleanVariable[] bool = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("bool1"), BooleanVariable.getBooleanVariable("bool2"),
				BooleanVariable.getBooleanVariable("bool3"), BooleanVariable.getBooleanVariable("bool4") };

		IProblem[] p = new IProblem[]
		{ new NaturalNumberFixer(I1.getLower(), 3), new NaturalNumberFixer(I1.getUpper(), 4),
				new NaturalNumberFixer(I2.getLower(), 2), new NaturalNumberFixer(I2.getUpper(), 7),
				new NaturalNumberFixer(I3.getLower(), 0), new NaturalNumberFixer(I3.getUpper(), 7),
				new NaturalNumberFixer(I4.getLower(), 6), new NaturalNumberFixer(I4.getUpper(), 9),
				new BitFixer(bool[0], true), new BitFixer(bool[3], true),
				new NaturalNumberIntervalDisjointSelector(I, bool) };

		IProblem problem = new Conjunction(p);

		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			if (bool[0].getValue())
				System.out.println("I1 = " + I1);
			if (bool[1].getValue())
				System.out.println("I2 = " + I2);
			if (bool[2].getValue())
				System.out.println("I3 = " + I3);
			if (bool[3].getValue())
				System.out.println("I4 = " + I4);
		}
		else
			System.out.println("No solution.");
	}
}
