package demos.bits.strings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.BitStringUnequalizer;

public class BitStringUnequalizerDemo2
{
	public static void main(String[] args) throws Exception
	{
		// IBitString[] C = new IBitString[]
		// { new BitString("01001010"), new BitString("01001010"),
		// new BitString("01001010"), new BitString("01001010"),
		// new BitString("00101000") };

		IBitString C1 = new BitString("01001010");
		IBitString C2 = new BitString("01001010");

		IProblem problem = new Conjunction(new BitStringFixer(C1),
				new BitStringFixer(C2), new BitStringUnequalizer(C1, C2));
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

		}
		else
			System.out.println("No solution.");
	}
}