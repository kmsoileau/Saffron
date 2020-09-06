package demos.integers.intervals;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IntegerFixer;
import integers.intervals.IIntegerInterval;
import integers.intervals.IntegerInterval;
import integers.intervals.IntegerIntervalDisjointSelector;

public class IntegerIntervalDisjointSelectorDemo
{
	public static void main(String[] args) throws Exception
	{
		IIntegerInterval I1 = new IntegerInterval("I1");
		IIntegerInterval I2 = new IntegerInterval("I2");
		IIntegerInterval I3 = new IntegerInterval("I3");
		IIntegerInterval I4 = new IntegerInterval("I4");

		IIntegerInterval[] I = new IIntegerInterval[]
		{ I1, I2, I3, I4 };
		IBooleanVariable[] bool = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("bool1"),
				BooleanVariable.getBooleanVariable("bool2"),
				BooleanVariable.getBooleanVariable("bool3"),
				BooleanVariable.getBooleanVariable("bool4") };

		IProblem[] p = new IProblem[]
		{ new IntegerFixer(I1.getLower(), 3),
				new IntegerFixer(I1.getUpper(), 4),
				new IntegerFixer(I2.getLower(), 2),
				new IntegerFixer(I2.getUpper(), 7),
				new IntegerFixer(I3.getLower(), 0),
				new IntegerFixer(I3.getUpper(), 7),
				new IntegerFixer(I4.getLower(), 6),
				new IntegerFixer(I4.getUpper(), 9),
				new BitFixer(bool[0], true), new BitFixer(bool[3], true),
				new IntegerIntervalDisjointSelector(I, bool) };

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
