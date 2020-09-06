package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringListPopulator;
import bits.strings.lists.IBitStringList;

public class BitStringListPopulatorDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList source = new BitStringList("source", new IBitString[]
		{ new BitString("000"), new BitString("001"), new BitString("010"),
				new BitString("011"), new BitString("100"),
				new BitString("101") });

		IBitStringList target = new BitStringList("target", new IBitString[]
		{ new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3), new BitString(3), new BitString(3),
				new BitString(3) });

		IProblem problem = new Conjunction(new BitStringListFixer(source),
				new BitStringListPopulator(target, source));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(source.toBits());
			System.out.println(target.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
