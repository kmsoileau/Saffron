package in_development;

import bits.BitEqualizer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringConditionalAnder;
import bits.strings.BitStringFixer;
import utility.Clock;
import utility.Clocks;

/**
 * Given a collection <code>C</code> of <code>IBitString</code>s each of size
 * <code>n</code>, and a bit position <code>i</code>, constrain
 * <code>targetBitString</code> to be the <code>AND</code> of the members of
 * <code>C</code> for which bit position <code>i</code> is <code>true</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/15
 */
public class BitStringBitAnder2
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("011110000000000"), new BitString("000011110000000"),
				new BitString("000000010000111"),
				new BitString("001100000111000"),
				new BitString("100000000000000") };

		Clocks.addClock("Kerry");
		Clocks.addClock("John");

		Clock Kerry = Clocks.getClock("Kerry");
		Kerry.start();

		Clock John = Clocks.getClock("John");

		IBitString membership = new BitString(C.length);
		IBitString targetBitString = new BitString(C[0].size());
		Kerry.stop();

		int pos = 8;
		IProblem[] p = new IProblem[C.length];
		for (int i = 0; i < C.length; i++)
		{
			John.start();
			p[i] = new BitEqualizer(membership.getBooleanVariable(i),
					C[i].getBooleanVariable(pos));
			John.stop();
		}
		IProblem problem = new Conjunction(new BitStringFixer(C),
				new Conjunction(p), new BitStringConditionalAnder(C,
						membership, targetBitString));
		Kerry.start();
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < C.length; i++)
				System.out.println(C[i].toBits());
			System.out.println("membership\n" + membership.toBits());
			System.out.println("targetBitString\n" + targetBitString.toBits());
		}
		else
			System.out.println("No solution.");

		Kerry.stop();
		System.out.println(Kerry.getTotalElapsedTime());
		System.out.println(John.getTotalElapsedTime());
	}
}
