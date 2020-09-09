package demos.sets;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringArraySplitter;
import bits.strings.BitStringFixer;

public class SetSplittingDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] data = new IBitString[]
		{ new BitString(new boolean[]
				{ true, true, true, true }), new BitString(new boolean[]
				{ true, false, true, false }), new BitString(new boolean[]
				{ true, true, false, true }) };

		int degree = data[0].size();

		IBitString S1 = new BitString(degree);
		IBitString S2 = new BitString(degree);

		IProblem problem = new BitStringArraySplitter(data, S1, S2);

		for (int i = 0; i < data.length; i++)
		{
			problem = new Conjunction(problem, new BitStringFixer(data[i]));
		}

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < data.length; i++)
				System.out.println("data[" + i + "]=" + data[i].toBits());
			System.out.println("S1= " + S1.toBits());
			System.out.println("S2= " + S2.toBits());
		}
		else
			System.out.println("No solution.");

	}
}