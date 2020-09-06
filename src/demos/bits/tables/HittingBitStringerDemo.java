/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 20, 2019
 */
package demos.bits.tables;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.HittingBitStringer;
import bits.strings.lists.IBitStringList;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class HittingBitStringerDemo
{
	public static void main(String[] args) throws Exception
	{
		int K = 3;

		IBitStringList C = new BitStringList();
		for (int i = 0; i < 20; i++)
			C.add(new BitString(randomBitstring(8)));

		IBitString Y = new BitString(C.getBitString(0).size());

		INaturalNumber bitSum = new NaturalNumber(K);

		IProblem problem = new Conjunction(new BitStringListFixer(C),
				new NaturalNumberFixer(bitSum), new HittingBitStringer(C,
						bitSum, Y));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("INPUT\n");
			for (int i = 0; i < C.size(); i++)
				System.out.println(C.getBitString(i).toBits());
			System.out.println("\nSOLUTION\n");
			System.out.println(Y.toBits());
		}
		else
			System.out.println("No solution.");
	}

	static String randomBitstring(int n)
	{
		char[] bits = new char[n];
		for (int i = 0; i < n; i++)
			bits[i] = Math.random() > .5 ? '1' : '0';
		return new String(bits);
	}
}