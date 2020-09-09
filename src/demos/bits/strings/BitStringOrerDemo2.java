package demos.bits.strings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
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
import bits.strings.BitStringOrer;

public class BitStringOrerDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] X = new IBitString[]
		{ new BitString("01001010"), new BitString("10101010"), new BitString("00001000"), new BitString("00100000") };

		IBitString Z = new BitString(8);

		IProblem problem = new Conjunction(new BitStringOrer(X, Z), new BitStringFixer(X));
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < X.length; i++)
				System.out.println("X[" + i + "] =\t" + X[i]);
			System.out.println("Z =\t" + Z);
		}
		else
			System.out.println("No solution.");
	}
}