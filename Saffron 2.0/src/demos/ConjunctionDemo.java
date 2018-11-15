package demos;

import java.util.List;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class ConjunctionDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		IProblem fixY = new BitFixer(Y, true);
		IProblem fixZ = new BitFixer(Y, true);
		IProblem conjunction1 = new Conjunction(new BitEqualizer(X, Y),
				new BitEqualizer(Y, Z));
		IProblem problem = new Conjunction(fixY, fixZ, conjunction1);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
		} else
			System.out.println("No solution.");
	}
}