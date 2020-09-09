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
import bits.strings.lists.BitStringListReverser;
import bits.strings.lists.IBitStringList;

public class BitStringListReverserDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList A = new BitStringList("A", new IBitString[]
		{ new BitString("000"), new BitString("110"), new BitString("010"), new BitString("011") });

		IBitStringList B = new BitStringList("B", new IBitString[]
		{ new BitString("000"), new BitString("000"), new BitString("000"), new BitString("000") });

		System.out.println(B);

		IProblem p1 = new BitStringListReverser(A, B);
		System.out.println(p1);

		IProblem problem = new Conjunction(new BitStringListReverser(A, B), new BitStringListFixer(A));

		System.out.println(problem);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("A = " + A);
			System.out.println("B = " + B);
		}
		else
			System.out.println("No solution.");
	}

}
