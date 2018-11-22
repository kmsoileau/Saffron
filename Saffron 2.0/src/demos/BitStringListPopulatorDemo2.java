package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListFixer;
import bitstringlists.BitStringListPopulator;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class BitStringListPopulatorDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList bins = new BitStringList("source", new IBitString[]
		{ new BitString("000"), new BitString("001"), new BitString("010"),
				new BitString("011"), new BitString("100"),
				new BitString("101") });

		IBitStringList binassignments = new BitStringList("target",
				new IBitString[]
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

		IProblem problem = new Conjunction(new BitStringListFixer(bins),
				new BitStringListPopulator(binassignments, bins));

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(bins.toBits());
			System.out.println(binassignments.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
